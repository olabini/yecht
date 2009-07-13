package org.yecht;

// Equivalent to token.re
public class TokenScanner implements YAMLGrammarTokens, Scanner {
     private void YYPOS(int n) {
         parser.cursor.start = parser.token + n;
     }

     // Not to be used.
//     private void ENSURE_YAML_IEND(Level last_lvl, int to_len) {
//       if(last_lvl.spaces > to_len) {
//         parser.popLevel();
//         YYPOS(0);
//         return YAML_IEND;
//       }
//     }

%%{
        machine TokenScanner;

        action EnterHeader {
          parser.token = parser.cursor.start;
        }


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
           (("---" ENDSPC %/{
             Level lvl = parser.currentLevel();
             if(lvl.status == LevelStatus.header) {
               YYPOS(3);
               fgoto Directive;
             } else {
               if(lvl.spaces > -1) {
                 parser.popLevel();
                 YYPOS(0);
                 return YAML_IEND;
               }
               YYPOS(0);
               return 0;
             }
           })
         |  ("..." ENDSPC)
         |  ("#")
         |  NULL
         |  YINDENT
         |  (SPCTAB+)
         |  ANY  >EnterHeader)
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

   private byte[] data;
   private int start;
   private int len;

   private int cs;
   private int act;
   private int have = 0;
   private int nread = 0;
   private int p;
   private int pe;
   private int eof;
   private int tokstart = -1;
   private int tokend = -1;
 
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public static void error(String msg, Parser parser) {
   }

   public static Scanner createScanner(byte[] data, int start, int len, Parser parser) {
     switch(parser.input_type) {
       case YAML_UTF8:
         return new TokenScanner(data, start, len, parser);
       case Bytecode_UTF8:
         // TODO: fix
         return null;
       case YAML_UTF16:
         error("UTF-16 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
         return null;
       case YAML_UTF32:
         error("UTF-32 is not currently supported in Yecht.\nPlease contribute code to help this happen!", parser);
         return null;
     }
     return null;
   }

   public TokenScanner(byte[] data, int start, int len, Parser parser) {
     this.data = data;
     this.start = start;
     this.len = len;
     
     this.p = start;
     this.pe = p + len;
     this.eof = this.pe;

     this.cs = TokenScanner_en_Header;
     this.parser = parser;
   }

   public Object getLVal() {
     return lval;
   }

   public int currentToken() {
     return currentToken;
   }

   public int yylex() {
     try {
          currentToken = real_yylex();
          return currentToken;
     } catch(java.io.IOException ioe) {
          throw new RuntimeException(ioe);
     }
   }

   private int real_yylex() throws java.io.IOException {
     int doc_level = 0;
     if(parser.cursor == null) {
       parser.read();
     }

     if(parser.force_token != 0) {
       int t = parser.force_token;
       parser.force_token = 0;
       return t;
     }

     if(parser.lineptr.start != parser.cursor.start) {
       this.cs = TokenScanner_en_Document;
     }

%% write exec;

     return 0;
   }
}
