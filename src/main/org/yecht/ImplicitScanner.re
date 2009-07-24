package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner {
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
        re2j:define:YYCTYPE  = "byte";
        re2j:define:YYCURSOR  = "cursor";
        re2j:define:YYMARKER  = "marker";
        re2j:define:YYLIMIT  = "limit";
        re2j:define:YYDATA  = "data";
        re2j:yyfill:enable  = 0;

DIGIT = [0-9] ;
ANY = [\001-\377] ;
YEAR = DIGIT DIGIT DIGIT DIGIT ;
MON = DIGIT DIGIT ;
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
