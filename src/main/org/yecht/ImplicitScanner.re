package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner {
    // try_tag_implicit
    public static void tryTagImplicit(Node n, boolean taguri) {
      String tid = "";
      switch(n.kind) {
        case Str:
          Data.Str s = (Data.Str)n.data;
          tid = matchImplicit(s.ptr, s.len);
          break;
        case Seq:
          tid = "seq";
          break;
        case Map:
          tid = "map";
          break;
        default:
          break;
      }

      if(taguri) {
        n.type_id = Parser.taguri(YAML.DOMAIN, tid);
      } else {
        n.type_id = tid;
      }
    }

    // syck_tagcmp
    public static boolean tagcmp(String tag1, String tag2) {
        if(tag1 == tag2) return true;
        if(tag1 == null || tag2 == null) return false;
        if(tag1.equals(tag2)) return true;
        int slen1 = tag1.indexOf('#');
        if(slen1 == -1) slen1 = tag1.length();
        int slen2 = tag2.indexOf('#');
        if(slen2 == -1) slen2 = tag2.length();
        return tag1.substring(0, slen1).equals(tag2.substring(0,slen2));
    }

    // syck_match_implicit
    public static String matchImplicit(Pointer ptr, int len) {
//        System.err.println("matchImplicit(" + new String(ptr.buffer, ptr.start, len) + ")");
        byte[] data = new byte[len + 1];
        System.arraycopy(ptr.buffer, ptr.start, data, 0, len);
        data[len] = 0;
        int cursor = 0;
        int limit = len;
        int marker = -1;       

/*!re2j
        re2j:define:YYCTYPE  = "byte";
        re2j:define:YYCURSOR  = "cursor";
        re2j:define:YYMARKER  = "marker";
        re2j:define:YYLIMIT  = "limit";
        re2j:define:YYDATA  = "data";
        re2j:yyfill:enable  = 0;

NULL = [\000] ;
ANY = [\001-\377] ;
DIGIT = [0-9] ;
DIGITSC = [0-9,] ;
DIGITSP = [0-9.] ;
YEAR = DIGIT DIGIT DIGIT DIGIT ;
MON = DIGIT DIGIT ;
SIGN = [-+] ;
HEX = [0-9a-fA-F,] ;
OCT = [0-7,] ;
INTHEX = SIGN? "0x" HEX+ ; 
INTOCT = SIGN? "0" OCT+ ;
INTSIXTY = SIGN? DIGIT DIGITSC* ( ":" [0-5]? DIGIT )+ ;
INTCANON = SIGN? ( "0" | [1-9] DIGITSC* ) ;
FLOATFIX = SIGN? DIGIT DIGITSC* "." DIGITSC* ;
FLOATEXP = SIGN? DIGIT DIGITSC* "." DIGITSP* [eE] SIGN DIGIT+ ;
FLOATSIXTY = SIGN? DIGIT DIGITSC* ( ":" [0-5]? DIGIT )+ "." DIGITSC* ;
INF = ( "inf" | "Inf" | "INF" ) ;
FLOATINF = [+]? "." INF ;
FLOATNEGINF = [-] "." INF ;
FLOATNAN = "." ( "nan" | "NaN" | "NAN" ) ;
NULLTYPE = ( "~" | "null" | "Null" | "NULL" )? ;
BOOLYES = ( "yes" | "Yes" | "YES" | "true" | "True" | "TRUE" | "on" | "On" | "ON" ) ;
BOOLNO = ( "no" | "No" | "NO" | "false" | "False" | "FALSE" | "off" | "Off" | "OFF" ) ;
TIMEZ = ( "Z" | [-+] DIGIT DIGIT ( ":" DIGIT DIGIT )? ) ;
TIMEYMD = YEAR "-" MON "-" MON ;
TIMEISO = YEAR "-" MON "-" MON [Tt] MON ":" MON ":" MON ( "." DIGIT* )? TIMEZ ;
TIMESPACED = YEAR "-" MON "-" MON [ \t]+ MON ":" MON ":" MON ( "." DIGIT* )? [ \t]+ TIMEZ ;
TIMECANON = YEAR "-" MON "-" MON "T" MON ":" MON ":" MON ( "." DIGIT* [1-9]+ )? "Z" ;
MERGE = "<<" ;
DEFAULTKEY = "=" ;

NULLTYPE NULL       {   return "null"; }

BOOLYES NULL        {   return "bool#yes"; }

BOOLNO NULL         {   return "bool#no"; }

INTHEX NULL         {   return "int#hex"; }

INTOCT NULL         {   return "int#oct"; }

INTSIXTY NULL       {   return "int#base60"; }

INTCANON NULL       {   return "int"; }

FLOATFIX NULL       {   return "float#fix"; }

FLOATEXP NULL       {   return "float#exp"; }

FLOATSIXTY NULL     {   return "float#base60"; }

FLOATINF NULL       {   return "float#inf"; }

FLOATNEGINF NULL    {   return "float#neginf"; }

FLOATNAN NULL       {   return "float#nan"; }

TIMEYMD NULL        {   return "timestamp#ymd"; }

TIMEISO NULL        {   return "timestamp#iso8601"; }

TIMESPACED NULL     {   return "timestamp#spaced"; }

TIMECANON NULL      {   return "timestamp"; }

DEFAULTKEY NULL     {   return "default"; }

MERGE NULL          {   return "merge"; }

ANY                 {   return "str"; }

*/
        return "str";
    }

    // syck_type_id_to_uri
    public static String typeIdToUri(String type_id) {
//        System.err.println("typeIdToUri(" + type_id + ")");
        byte[] data = null;
        try {
          data = type_id.getBytes("ISO8859-1");
        } catch(Exception e) {}
        byte[] dx = new byte[data.length+1];
        System.arraycopy(data, 0, dx, 0, data.length);
        dx[data.length] = 0;
        data = dx;
        int cursor = 0;
        int limit = data.length;
        int marker = -1;
/*!re2j

TAG = "tag" ;
XPRIVATE = "x-private" ;
WD = [A-Za-z0-9_] ;
WDD = [A-Za-z0-9_-] ;
DNSCOMPRE = WD ( WDD* WD )? ;
DNSNAMERE = ( ( DNSCOMPRE "." )+ DNSCOMPRE | DNSCOMPRE ) ;
TAGDATE = YEAR ( "-" MON )? ( "-" MON )? ;

TAG ":" DNSNAMERE "," TAGDATE ":"    {   return type_id; }

XPRIVATE ":"    {   return type_id; }

"!"             {   return Parser.xprivate(type_id.substring(1)); }

DNSNAMERE "/"   {   
                    return Parser.taguri(type_id.substring(0, cursor -1) + "." + YAML.DOMAIN, type_id.substring(cursor));
                }

DNSNAMERE "," TAGDATE "/"  {   
                               return Parser.taguri(type_id.substring(0, cursor-1), type_id.substring(cursor));
                            }

ANY             {   return Parser.taguri(YAML.DOMAIN, type_id); }

*/
        return null;
    }
}
