package org.yecht;

// Equivalent to bytecode.re
public class BytecodeScanner {
%%{
        machine BytecodeScanner;

        LF = ( "\n" | "\r\n" ) ;
        NULL = [\000] ;
        ANY = [\001-\377] ;
        YWORDC = [A-Za-z0-9_\-] ;
        YWORDP = [A-Za-z0-9_\-\.] ;

        DOC = "D" LF ;
        DIR = "V" YWORDP+ ":" YWORDP+ LF ;
        PAU = "P" LF ;
        MAP = "M" LF ;
        SEQ = "Q" LF ;
        END = "E" LF ;
        SCA = "S" ;
        SCC = "C" ;
        NNL = "N" [0-9]*;
        NLZ = "Z" ;
        ANC = "A" ;
        REF = "R" ;
        TAG = "T" ;

        COM = "c" ;

        Header :=
             DOC
          |  ANY
          ;

        Document :=
             (DOC | PAU)
          |  MAP
          |  SEQ
          |  END
          |  SCA
          |  ANC
          |  REF
          |  TAG
          |  COM
          |  LF
          |  NULL
          ;

        Directive :=
             DIR
          |  ANY
          ;

        Comment :=
             LF
          |  ANY
          ;

        Scalar :=
             (LF SCC)
          |  (LF NNL)
          |  (LF NLZ)
          |  LF
          |  NULL
          |  ANY
          ;

        Inline :=
             LF
          |  NULL
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
       
       cs = BytecodeScanner_en_Header;
%% write exec;
   }
}
