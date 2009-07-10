package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner {
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
             (TAG ":" DNSNAMERE "," TAGDATE ":")
          |  (XPRIVATE ":")
          |  "!"
          |  (DNSNAMERE "/")
          |  (DNSNAMERE "," TAGDATE "/")         
          |  ANY
          ;          
}%%

%% write data nofinal;

   public String recognize(byte[] data, int start, int len) {
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
}
