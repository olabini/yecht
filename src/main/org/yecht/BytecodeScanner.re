package org.yecht;

import java.io.IOException;

// Equivalent to bytecode.re
public class BytecodeScanner implements YAMLGrammarTokens, Scanner {
   public final static int QUOTELEN = 128;
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public BytecodeScanner(Parser parser) {
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

   private void YYPOS(int n) {
       parser.cursor = parser.token + n;
   }

   private void FORCE_NEXT_TOKEN(int n) {
       parser.force_token = n;
   }

   private void CHK_NL(int ptr) {
       if(parser.buffer.buffer[ptr - 1] == '\n' && ptr > parser.linectptr) {
           parser.lineptr = ptr;
           parser.linect++;
           parser.linectptr = parser.lineptr;
       }
   }

   private boolean ADD_BYTE_LEVEL(Level lvl, int len, LevelStatus s) {
       switch(lvl.status) {
           case seq:
               lvl.ncount++;
               parser.addLevel(len, LevelStatus.open);
               YYPOS(0);
               return true;
           case map:
               lvl.ncount++;
               parser.addLevel(len, s);
               return false;
           case open:
               lvl.status = s;
               return false;
           default:
               parser.addLevel(len, s);
               return false;
       }
   }

   private final static int Start = 1;
   private final static int Document = 2;
   private final static int Directive = 3;
   private final static int Comment = 4;
   private final static int Scalar = 5;
   private final static int Scalar2 = 6;
   private final static int ScalarEnd = 7;

   // sycklex_bytecode_utf8
   private int real_yylex() throws IOException {
       Level lvl = null;
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

LF = ( "\n" | "\r\n" ) ;
NULL = [\000] ;
ANY = [\001-\377] ;
YWORDC = [A-Za-z0-9_-] ;
YWORDP = [A-Za-z0-9_-\.] ;

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
*/
       int mainLoopGoto = Start;
       lvl = parser.currentLevel();
       if(lvl.status == LevelStatus.doc) {
           mainLoopGoto = Document;
       }

       parser.token = parser.cursor;
       {
/*!re2j

DOC     {   if(lvl.status == LevelStatus.header) {
                CHK_NL(parser.cursor);
                mainLoopGoto = Directive;
            } else  {
                if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }
        }

ANY     {   YYPOS(0);
            mainLoopGoto = Document;
        }
*/
       }

       do {
           gotoSomething: while(true) {
               switch(mainLoopGoto) {
               case Start: {
               }
               case Document: {
                   lvl = parser.currentLevel();
                   if(lvl.status == LevelStatus.header) {
                       lvl.status = LevelStatus.doc;
                   }
                   parser.token = parser.cursor;

/*!re2j

DOC | PAU   {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
                }
                YYPOS(0);
                return 0;
            }

MAP     {   boolean complex = false;
            if(lvl.ncount % 2 == 0 && ( lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                complex = true;
            }
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.map)) {
                return '-';
            }
            CHK_NL(parser.cursor);
            if(complex) {
                FORCE_NEXT_TOKEN( YAML_IOPEN );
                return '?';
            }
            return YAML_IOPEN;
        }

SEQ     {   boolean complex = false;
            if(lvl.ncount % 2 == 0 && ( lvl.status == LevelStatus.map || lvl.status == LevelStatus.seq)) {
                complex = true;
            }
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.seq)) {
                return '-';
            }
            CHK_NL(parser.cursor);
            if(complex) {
                FORCE_NEXT_TOKEN( YAML_IOPEN );
                return '?';
            }
            return YAML_IOPEN;
        }

END     {   if(lvl.status == LevelStatus.seq && lvl.ncount == 0) {
                lvl.ncount++;
                YYPOS(0);
                FORCE_NEXT_TOKEN( ']' );
                return '[';
            } else if(lvl.status == LevelStatus.map && lvl.ncount == 0) {
                lvl.ncount++;
                YYPOS(0);
                FORCE_NEXT_TOKEN( '}' );
                return '{';
            }
            parser.popLevel();
            lvl = parser.currentLevel();
            if(lvl.status == LevelStatus.seq) {
                FORCE_NEXT_TOKEN(YAML_INDENT);   
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) {
                    FORCE_NEXT_TOKEN(':');
                } else {
                    FORCE_NEXT_TOKEN(YAML_INDENT);
                }
            }
            CHK_NL(parser.cursor);
            return YAML_IEND;
        }

SCA     {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            mainLoopGoto = Scalar; break gotoSomething;
        }

ANC     {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                return '-';
            }
            lval = getInline();
            parser.removeAnchor((String)lval);
            CHK_NL(parser.cursor);
            return YAML_ANCHOR;
        }

REF     {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            lval = getInline();
            parser.popLevel();
            if( parser.buffer.buffer[parser.cursor - 1] == '\n') parser.cursor--;
            return YAML_ALIAS;
        }

TAG     {   
            if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.open)) {
                return '-';
            }
            String qstr = getInline();
            CHK_NL(parser.cursor);
            if(qstr.charAt(0) == '!' ) {
                int qidx = qstr.length();
                if(qidx == 1) {
                    return YAML_ITRANSFER;
                }

                lvl = parser.currentLevel();

                /*
                 * URL Prefixing
                 */
                if(qstr.charAt(1) == '^') {
                    lval = lvl.domain + qstr.substring(2);
                } else {
                    int carat = qstr.indexOf('^');
                    if(carat != -1) {
                        lvl.domain = qstr.substring(1, carat);
                        lval = lvl.domain + qstr.substring(carat + 1);
                    } else {
                        lval = qstr.substring(1);
                    }
                }
                return YAML_TRANSFER;
            }
            lval = qstr;
            return YAML_TAGURI;
        }

COM     { mainLoopGoto = Comment; break gotoSomething; }

LF      {   CHK_NL(parser.cursor);
            if(lvl.status == LevelStatus.seq) {
                return YAML_INDENT; 
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) return ':';
                else                    return YAML_INDENT;
            }
            mainLoopGoto = Document; break gotoSomething;
        }

NULL    {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return YAML_IEND;
            }
            YYPOS(0);
            return 0;
        }

*/
               }
               case Directive:
               case Comment:
               case Scalar:
               case Scalar2:
               case ScalarEnd:
               }
           }
       } while(true);
   }

   private String getInline() throws IOException {
       String str = "";
       int tok = -1;
       
       while(true) {
           tok = parser.cursor;
/*!re2j

LF          {   CHK_NL(parser.cursor);
                return str; }

NULL        {   parser.cursor = tok;
                return str;
            }

ANY         {   
                str = str + (char)parser.buffer.buffer[tok];
            }
*/
       }
   }
}
