package org.yecht;

// Equivalent to token.re
public class TokenScanner {
%%{
        machine TokenScanner;

        YWORDC = [A-Za-z0-9_\-] ;
        YWORDP = [A-Za-z0-9_-\.] ;
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
}%%

%% write data nofinal;
}
