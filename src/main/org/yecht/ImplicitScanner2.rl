package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner2 {
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

%%{
        machine ImplicitScanner;

        DIGIT = [0-9] ;
        DIGITSC = [0-9,] ;
        DIGITSP = [0-9.] ;
        YEAR = DIGIT DIGIT DIGIT DIGIT ;
        MON = DIGIT DIGIT ;
        SIGN = [\-+] ;
        OCT = [0-7,] ;
        HEX = [0-9a-fA-F,] ;
        INF = ( "inf" | "Inf" | "INF" ) ;
        TIMEZ = ( "Z" | [\-+] DIGIT DIGIT ( ":" DIGIT DIGIT )? ) ;

        NULLTYPE = ( "~" | "null" | "Null" | "NULL" ) %/{tag = "null";} ;
        BOOLYES  = ( "yes" | "Yes" | "YES" | "true" | "True" | "TRUE" | "on" | "On" | "ON" ) %/{tag = "bool#yes";} ;
        BOOLNO   = ( "no" | "No" | "NO" | "false" | "False" | "FALSE" | "off" | "Off" | "OFF" ) %/{tag = "bool#no";} ;
        INTHEX   = (SIGN? "0x" HEX+) %/{tag = "int#hex";}; 
        INTOCT   = (SIGN? "0" OCT+) %/{tag = "int#oct";}; 
        INTSIXTY = (SIGN? DIGIT DIGITSC* ( ":" [0-5]? DIGIT )+ ) %/{tag = "int#base60";}; 
        INTCANON = (SIGN? ( "0" | [1-9] DIGITSC* )) %/{tag = "int";};
        FLOATFIX = (SIGN? DIGIT DIGITSC* "." DIGITSC*) %/{tag = "float#fix";};
        FLOATEXP = (SIGN? DIGIT DIGITSC* "." DIGITSP* [eE] SIGN DIGIT+) %/{tag = "float#exp";};
        FLOATSIXTY = (SIGN? DIGIT DIGITSC* ( ":" [0-5]? DIGIT )+ "." DIGITSC*) %/{tag = "float#base60";};
        FLOATINF = ([+]? "." INF) %/{tag = "float#inf";};
        FLOATNEGINF = ([\-] "." INF) %/{tag = "float#neginf";} ;
        FLOATNAN = ("." ( "nan" | "NaN" | "NAN" )) %/{tag = "float#nan";};
        TIMEYMD = (YEAR "-" MON "-" MON) %/{tag = "timestamp#ymd";};
        TIMEISO = (YEAR "-" MON "-" MON [Tt] MON ":" MON ":" MON ( "." DIGIT* )? TIMEZ) %/{tag = "timestamp#iso8601";};
        TIMESPACED = (YEAR "-" MON "-" MON [ \t]+ MON ":" MON ":" MON ( "." DIGIT* )? [ \t]+ TIMEZ) %/{tag = "timestamp#spaced";};
        TIMECANON = (YEAR "-" MON "-" MON "T" MON ":" MON ":" MON ( "." DIGIT* [1-9]+ )? "Z") %/{tag = "timestamp";};
        DEFAULTKEY = "=" %/{tag = "default";};
        MERGE = "<<" %/{tag = "merge";};

        Scalar = NULLTYPE | BOOLYES | BOOLNO | INTHEX | INTOCT | INTSIXTY | INTCANON | FLOATFIX | FLOATEXP | FLOATSIXTY | FLOATINF | FLOATNEGINF | FLOATNAN | TIMEYMD | TIMEISO | TIMESPACED | TIMECANON | DEFAULTKEY | MERGE;
        main := Scalar;
}%%

%% write data nofinal;

    // syck_match_implicit
    public static String matchImplicit(Pointer ptr, int len) {
       String tag = "str";
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=ptr.start;
       int pe = p+len;
       int eof = p+len;
       int tokstart = -1;
       int tokend = -1;

       byte[] data = ptr.buffer;
       if(len == 0) {
         data = new byte[]{(byte)'~'};
         p = 0;
         pe = 1;
         eof = 1;
       }
              
%% write init;

%% write exec;

       return tag;
    }
}
