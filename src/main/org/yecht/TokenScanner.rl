package org.yecht;

// Equivalent to token.re
public class TokenScanner {
%%{
        machine TokenScanner;

        YWORDC = [A-Za-z0-9_\-] ;
        YWORDP = [A-Za-z0-9_\-\.] ;
        LF = ( "\n" | "\r\n" ) ;
        SPC = " " ;
        TAB = "\t" ;
        SPCTAB = ( SPC | TAB ) ;
        ENDSPC = ( SPC+ | LF ) ;
        YINDENT = LF TAB* ( SPC | LF )* ;
        NULL = [\000] ;
        ANY = [\001-\377] ;
        ISEQO = "[" ;
        ISEQC = "]" ;
        IMAPO = "{" ;
        IMAPC = "}" ;
        CDELIMS = ( ISEQC | IMAPC ) ;
        ICOMMA = ( "," ENDSPC ) ;
        ALLX = ( ":" ENDSPC ) ;
        DIR = "%" YWORDP+ ":" YWORDP+ ;
        YBLOCK = [>|] [\-+0-9]* ENDSPC ; 
        HEX = [0-9A-Fa-f] ;
        ESCSEQ = ["\\abefnrtv0] ;
                
        Header := 
            ("---" ENDSPC)
         |  ("..." ENDSPC)
         |  ("#")
         |  NULL
         |  YINDENT
         |  (SPCTAB+)
         |  ANY
         ;

        Document :=
            YINDENT
         |  ISEQO
         |  IMAPO
         |  CDELIMS
         |  ([;,] ENDSPC)
         |  ([\-?] ENDSPC)
         |  ("&" YWORDC+)
         |  ("*" YWORDC+)
         |  "!"
         |  "'"
         |  "\""
         |  YBLOCK
         |  "#"
         |  (SPCTAB+)
         |  NULL
         |  ANY
         ;

       Directive :=
            DIR
         |  (SPCTAB+)
         |  ANY
         ;

       Plain :=
            YINDENT
         |  ALLX
         |  ICOMMA
         |  IMAPC
         |  ISEQC
         |  " #"
         |  NULL
         |  SPCTAB
         |  NULL
         ;

       SingleQuote :=
            YINDENT
         |  "''"
         |  ("'" | NULL)
         |  ANY
         ;

       DoubleQuote :=
            YINDENT
         |  ("\\" ESCSEQ)
         |  ("\\x" HEX HEX)
         |  ("\\" SPC* LF)
         |  ("\"" | NULL)
         |  ANY
         ;

       TransferMethod :=
            ( ENDSPC | NULL )
         |  ("\\" ESCSEQ)
         |  ("\\x" HEX HEX)
         |  ANY
         ;

       ScalarBlock :=
            YINDENT
         |  "#"
         |  NULL
         |  ("---" ENDSPC)
         |  ANY
         ;

       Comment :=
            ( LF+ | NULL )
         |  ANY
         ;       
}%%

%% write data nofinal;

   public void recognize(byte[] data, int start, int len) {
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+len;
       int tokstart = -1;
       int tokend = -1;
              
       cs = TokenScanner_en_Header;
%% write exec;
   }
}
