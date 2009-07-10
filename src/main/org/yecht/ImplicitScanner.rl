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

%%{
        machine ImplicitScanner;

        NULL = [\000] ;
        ANY = [\001-\377] ;
        DIGIT = [0-9] ;
        DIGITSC = [0-9,] ;
        DIGITSP = [0-9.] ;
        YEAR = DIGIT DIGIT DIGIT DIGIT ;
        MON = DIGIT DIGIT ;
        SIGN = [\-+] ;
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
        FLOATNEGINF = [\-] "." INF ;
        FLOATNAN = "." ( "nan" | "NaN" | "NAN" ) ;
        NULLTYPE = ( "~" | "null" | "Null" | "NULL" )? ;
        BOOLYES = ( "yes" | "Yes" | "YES" | "true" | "True" | "TRUE" | "on" | "On" | "ON" ) ;
        BOOLNO = ( "no" | "No" | "NO" | "false" | "False" | "FALSE" | "off" | "Off" | "OFF" ) ;
        TIMEZ = ( "Z" | [\-+] DIGIT DIGIT ( ":" DIGIT DIGIT )? ) ;
        TIMEYMD = YEAR "-" MON "-" MON ;
        TIMEISO = YEAR "-" MON "-" MON [Tt] MON ":" MON ":" MON ( "." DIGIT* )? TIMEZ ;
        TIMESPACED = YEAR "-" MON "-" MON [ \t]+ MON ":" MON ":" MON ( "." DIGIT* )? [ \t]+ TIMEZ ;
        TIMECANON = YEAR "-" MON "-" MON "T" MON ":" MON ":" MON ( "." DIGIT* [1-9]+ )? "Z" ;
        MERGE = "<<" ;
        DEFAULTKEY = "=" ;

        Implicit :=
             NULLTYPE       %/{   tag = "null"; }
          |  BOOLYES        %/{   tag = "bool#yes"; }
          |  BOOLNO         %/{   tag = "bool#no"; }
          |  INTHEX         %/{   tag = "int#hex"; }
          |  INTOCT         %/{   tag = "int#oct"; }
          |  INTSIXTY       %/{   tag = "int#base60"; }
          |  INTCANON       %/{   tag = "int"; }
          |  FLOATFIX       %/{   tag = "float#fix"; }
          |  FLOATEXP       %/{   tag = "float#exp"; }
          |  FLOATSIXTY     %/{   tag = "float#base60"; }
          |  FLOATINF       %/{   tag = "float#inf"; }
          |  FLOATNEGINF    %/{   tag = "float#neginf"; }
          |  FLOATNAN       %/{   tag = "float#nan"; }
          |  TIMEYMD        %/{   tag = "timestamp#ymd"; }
          |  TIMEISO        %/{   tag = "timestamp#iso8601"; }
          |  TIMESPACED     %/{   tag = "timestamp#spaced"; }
          |  TIMECANON      %/{   tag = "timestamp"; }
          |  DEFAULTKEY     %/{   tag = "default"; }
          |  MERGE          %/{   tag = "merge"; }
          |  ANY             %{   tag = "str"; }
          ;

        TAG = "tag" ;
        XPRIVATE = "x-private" ;
        WD = [A-Za-z0-9_] ;
        WDD = [A-Za-z0-9_\-] ;
        DNSCOMPRE = WD ( WDD* WD )? ;
        DNSNAMERE = ( ( DNSCOMPRE "." )+ DNSCOMPRE | DNSCOMPRE ) ;
        TAGDATE = YEAR ( "-" MON )? ( "-" MON )? ;

        TypeId :=
             (TAG ":" DNSNAMERE "," TAGDATE ":") %/{ tag = type_id; }
          |  (XPRIVATE ":")                      %/{ tag = type_id; }
          |  "!"                                 %/{ tag = Parser.xprivate(type_id.substring(1)); }
          |  (DNSNAMERE "/")                     %/{   
                    String domain = type_id.substring(0, p-1) + "." + YAML.DOMAIN;
                    String uri = Parser.taguri( domain, type_id.substring(p));
                    tag = uri;
                }
          |  (DNSNAMERE "," TAGDATE "/")         %/{   
                               String domain = type_id.substring(0, p-1);
                               String uri = Parser.taguri(domain, type_id.substring(p));
                               tag = uri;
                            }
          |  ANY                                 %/{ tag = Parser.taguri(YAML.DOMAIN, type_id); }
          ;          
}%%

%% write data nofinal;
   // syck_match_implicit
   public static String matchImplicit(Pointer ptr, int len) {
       String type_id = null; // unused
       byte[] data = ptr.buffer;
       int start = ptr.start;
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+len;
       int tokstart = -1;
       int tokend = -1;
       int eof = pe;
       String tag = "str";
              
       cs = ImplicitScanner_en_Implicit;       

%% write exec;

       return tag;
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

   // syck_type_id_to_uri
   public static String typeIdToUri(String type_id) {
       byte[] data = null;
       try {
         data = type_id.getBytes("ISO8859-1");
       } catch(Exception e) {}

       int start = 0;
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+data.length;
       int tokstart = -1;
       int tokend = -1;
       int eof = pe;
       String tag = null;
              
       cs = ImplicitScanner_en_TypeId;       

%% write exec;

       return tag;
   }
}
