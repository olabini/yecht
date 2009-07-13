package org.yecht;

// Equivalent to token.re
public class TokenScanner implements YAMLGrammarTokens, Scanner {
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public static void error(String msg, Parser parser) {
   }

   public static Scanner createScanner(Parser parser) {
     switch(parser.input_type) {
       case YAML_UTF8:
         return new TokenScanner(parser);
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

   public TokenScanner(Parser parser) {
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
     if(parser.cursor == -1) {
       parser.read();
     }

     if(parser.force_token != 0) {
       int t = parser.force_token;
       parser.force_token = 0;
       return t;
     }

/*!re2j
        re2j:define:YYCTYPE  = "byte";
        re2j:define:YYCURSOR  = "parser.cursor";
        re2j:define:YYMARKER  = "parser.marker";
        re2j:define:YYLIMIT  = "parser.limit";
        re2j:define:YYDATA  = "parser.buffer.buffer";

YWORDC = [A-Za-z0-9_-] ;
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
YBLOCK = [>|] [-+0-9]* ENDSPC ; 
HEX = [0-9A-Fa-f] ;
ESCSEQ = ["\\abefnrtv0] ;

*/
   }

   private void eatComments() {
     comment: while(true) {
       parser.token = parser.cursor;
/*!re2j

( LF+ | NULL )      {   parser.cursor = parser.token;
                        return;
                    }

ANY                 {   continue comment; 
                    }

*/
    }
  }
}
