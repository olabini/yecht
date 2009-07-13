package org.yecht;

import java.io.IOException;

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

   private int isNewline(int ptr) {
     return newlineLen(ptr);
   }

   private int newlineLen(int ptr) {
     if(parser.buffer.buffer[ptr] == '\n')
       return 1;

     if(parser.buffer.buffer[ptr] == '\r' && parser.buffer.buffer[ptr+1] == '\n')
       return 2;
       
     return 0;
   }

   private void NEWLINE(int ptr) {
     parser.lineptr = ptr + newlineLen(ptr);
     if(parser.lineptr > parser.linectptr) {
       parser.linect++;
       parser.linectptr = parser.lineptr;
     }
   }

   private final static int Header = 1;
   private final static int Document = 2;
   private final static int Directive = 3;
   private final static int Plain = 4;
   private final static int Plain2 = 5;
   private final static int Plain3 = 6;
   private final static int SingleQuote = 7;
   private final static int SingleQuote2 = 8;
   private final static int DoubleQuote = 9;
   private final static int DoubleQuote2 = 10;
   private final static int TransferMethod = 11;
   private final static int TransferMethod2 = 12;
   private final static int ScalarBlock = 13;
   private final static int ScalarBlock2 = 14;

   private void YYPOS(int n) {
       parser.cursor = parser.token + n;
   }

   private int real_yylex() throws IOException {
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
        re2j:yyfill:parameter  = 0;
        re2j:define:YYFILL  = "parser.read()";

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
        int mainLoopGoto = Header;
        if( parser.lineptr != parser.cursor ) {
            mainLoopGoto = Document;
        }

        do {
            gotoSomething: while(true) {
                switch(mainLoopGoto) {
                case Header: {
                    parser.token = parser.cursor;
/*!re2j

"---" ENDSPC        { Level lvl = parser.currentLevel();
                      if(lvl.status == LevelStatus.header) {
                          YYPOS(3);
                          mainLoopGoto = Directive; break gotoSomething;
                      } else {
                          if(lvl.spaces > -1) {
                              parser.popLevel();
                              YYPOS(0);
                              return YAML_IEND;
                          }
                          YYPOS(0);
                          return 0;
                      }
                    }

"..." ENDSPC        {   Level lvl = parser.currentLevel();
                        if(lvl.status == LevelStatus.header) {
                          mainLoopGoto = Header; break gotoSomething;
                        } else {
                          if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return YAML_IEND;
                          }
                          YYPOS(0);
                          return 0; 
                        }
                    }

"#"                 {   eatComments(); 
                        mainLoopGoto = Header; break gotoSomething;
                    }

NULL                {   Level lvl = parser.currentLevel();
                        if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return YAML_IEND;
                        }
                        YYPOS(0);
                        return 0; 
                    }

YINDENT             {
                        int indent = parser.token;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                          if(parser.buffer.buffer[indent] == '\t') {
                            error("TAB found in your indentation, please remove",parser);
                          } else if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        doc_level = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          doc_level = -1;
                          parser.token = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          doc_level = parser.cursor - parser.lineptr;
                        }
                        mainLoopGoto = Header; break gotoSomething;
                    }

SPCTAB+             {   doc_level = parser.cursor - parser.lineptr;
                        mainLoopGoto = Header; break gotoSomething;
                    }

ANY                 {   YYPOS(0);
                        mainLoopGoto = Document; break gotoSomething;
                    }

*/
                }
                case Document: {
                    Level lvl = parser.currentLevel();
                    if(lvl.status == LevelStatus.header) {
                      lvl.status = LevelStatus.doc;
                    }

                    parser.token = parser.cursor;

/*!re2j

YINDENT             {   /* Isolate spaces */
                        int indt_len;
                        int indent = parser.token;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                          if(parser.buffer.buffer[indent] == '\t') {
                            error("TAB found in your indentation, please remove",parser);
                          } else if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        indt_len = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          indt_len = -1;
                          parser.token = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          indt_len = parser.cursor - parser.lineptr;
                        }

                        lvl = parser.currentLevel();
                        doc_level = 0;

                        /* XXX: Comment lookahead */
                        if( parser.buffer.buffer[parser.cursor] == '#' ) {
                            mainLoopGoto = Document; break gotoSomething;
                        }

                        /* Ignore indentation inside inlines */
                        if( lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap ) {
                            mainLoopGoto = Document; break gotoSomething;
                        }

                        /* Check for open indent */
                        if(lvl.spaces > indt_len) {
                           parser.popLevel();
                           YYPOS(0);
                           return YAML_IEND;
                        }
                        if(lvl.spaces < indt_len) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(indt_len, LevelStatus.doc);
                                return YAML_IOPEN;
                            }
                        }
                        if(indt_len == -1) {
                            return 0;
                        }
                        return YAML_INDENT;
                    }

ISEQO               {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        lvl = parser.currentLevel();
                        parser.addLevel(lvl.spaces + 1, LevelStatus.iseq);
                        return parser.buffer.buffer[parser.token];
                    }

IMAPO               {
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        lvl = parser.currentLevel();
                        parser.addLevel(lvl.spaces + 1, LevelStatus.imap);
                        return parser.buffer.buffer[parser.token];
                    }

CDELIMS             {   parser.popLevel();
                        return parser.buffer.buffer[parser.token];
                    }

[:,] ENDSPC         {   if( parser.buffer.buffer[parser.token] == ':' && lvl.status != LevelStatus.imap && lvl.status != LevelStatus.iseq ) {
                            lvl.status = LevelStatus.map;
                        }
                        YYPOS(1); 
                        return parser.buffer.buffer[parser.token];
                    }

[-?] ENDSPC         {   
                        if(lvl.spaces < (parser.token - parser.lineptr)) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel((parser.token - parser.lineptr), LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        parser.force_token = YAML_IOPEN;
                        if( parser.buffer.buffer[parser.cursor] == '#' || isNewline(parser.cursor) != 0 || isNewline(parser.cursor-1) != 0) {
                            parser.cursor--;
                            parser.addLevel(parser.token + 1 - parser.lineptr, LevelStatus.seq);
                        } else /* spaces followed by content uses the space as indentation */
                        {
                            parser.addLevel(parser.cursor - parser.lineptr, LevelStatus.seq);
                        }
                        return parser.buffer.buffer[parser.token];
                    }

"&" YWORDC+         {   lval = new String(parser.buffer.buffer, parser.token + 1, parser.cursor - (parser.token + 1), "ISO-8859-1");

                        /*
                         * Remove previous anchors of the same name.  Since the parser will likely
                         * construct deeper nodes first, we want those nodes to be placed in the
                         * queue for matching at a higher level of indentation.
                         */
                        parser.removeAnchor((String)lval);
                        return YAML_ANCHOR;
                    }

"*" YWORDC+         {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        lval = new String(parser.buffer.buffer, parser.token + 1, parser.cursor - (parser.token + 1), "ISO-8859-1");
                        return YAML_ALIAS;
                    }

"!"                 {   mainLoopGoto = TransferMethod; break gotoSomething; }

"'"                 {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        mainLoopGoto = SingleQuote; break gotoSomething; }

"\""                {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        mainLoopGoto = DoubleQuote; break gotoSomething; }

YBLOCK              {   if(isNewline(parser.cursor - 1) != 0) {
                            parser.cursor--;
                        }
                        mainLoopGoto = ScalarBlock; break gotoSomething;
                    }

"#"                 {   eatComments(); 
                        mainLoopGoto = Document; break gotoSomething;
                    }

SPCTAB+             {   
                        mainLoopGoto = Document; break gotoSomething;
                    }

NULL                {   
                        if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return YAML_IEND;
                        }
                        YYPOS(0);
                        return 0; 
                    }

ANY                 {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status == LevelStatus.iseq || lvl.status == LevelStatus.imap) {
                                mainLoopGoto = Document; break gotoSomething;
                            } else {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return YAML_IOPEN;
                            }
                        }
                        mainLoopGoto = Plain; break gotoSomething;
                    }

*/


                }
                case Directive:
                case Plain:
                case Plain2:
                case Plain3:
                case SingleQuote:
                case SingleQuote2:
                case DoubleQuote:
                case DoubleQuote2:
                case TransferMethod:
                case TransferMethod2:
                case ScalarBlock:
                case ScalarBlock2:
                }
                return 0;                
            }
        } while(true);
   }

   private void eatComments() throws IOException {
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
