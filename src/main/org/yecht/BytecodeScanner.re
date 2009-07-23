package org.yecht;

import java.io.IOException;

// Equivalent to bytecode.re
public class BytecodeScanner implements DefaultYAMLParser.yyInput {
   public final static int QUOTELEN = 128;
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public BytecodeScanner(Parser parser) {
     this.parser = parser;
   }

   public Object value() {
     return lval;
   }

   public int token() {
     return currentToken;
   }

   public boolean advance() throws java.io.IOException {
     currentToken = real_yylex();
     return currentToken == 0 ? false : true;
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

   private static class QuotedString {
       public int idx = 0;
       public int capa = 100;
       public byte[] str;

       public QuotedString() {
           str = new byte[100];
       }

       public void cat(char l) {
           cat((byte)l);
       }
      
       public void cat(byte l) {
           if(idx + 1 >= capa) {
               capa += QUOTELEN;
               str = YAML.realloc(str, capa);
           }
           str[idx++] = l;
           str[idx] = 0;
       }
   }

   // sycklex_bytecode_utf8
   private int real_yylex() throws IOException {
       Level lvl = null;
       QuotedString q = null;
       int tok = -1;

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
                    return DefaultYAMLParser.YAML_IEND;
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
                    return DefaultYAMLParser.YAML_IEND;
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
                FORCE_NEXT_TOKEN( DefaultYAMLParser.YAML_IOPEN );
                return '?';
            }
            return DefaultYAMLParser.YAML_IOPEN;
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
                FORCE_NEXT_TOKEN( DefaultYAMLParser.YAML_IOPEN );
                return '?';
            }
            return DefaultYAMLParser.YAML_IOPEN;
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
                FORCE_NEXT_TOKEN(DefaultYAMLParser.YAML_INDENT);   
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) {
                    FORCE_NEXT_TOKEN(':');
                } else {
                    FORCE_NEXT_TOKEN(DefaultYAMLParser.YAML_INDENT);
                }
            }
            CHK_NL(parser.cursor);
            return DefaultYAMLParser.YAML_IEND;
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
            return DefaultYAMLParser.YAML_ANCHOR;
        }

REF     {   if(ADD_BYTE_LEVEL(lvl, lvl.spaces + 1, LevelStatus.str)) {
                return '-';
            }
            lval = getInline();
            parser.popLevel();
            if( parser.buffer.buffer[parser.cursor - 1] == '\n') parser.cursor--;
            return DefaultYAMLParser.YAML_ALIAS;
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
                    return DefaultYAMLParser.YAML_ITRANSFER;
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
                return DefaultYAMLParser.YAML_TRANSFER;
            }
            lval = qstr;
            return DefaultYAMLParser.YAML_TAGURI;
        }

COM     { mainLoopGoto = Comment; break gotoSomething; }

LF      {   CHK_NL(parser.cursor);
            if(lvl.status == LevelStatus.seq) {
                return DefaultYAMLParser.YAML_INDENT; 
            } else if(lvl.status == LevelStatus.map) {
                if(lvl.ncount % 2 == 1) return ':';
                else                    return DefaultYAMLParser.YAML_INDENT;
            }
            mainLoopGoto = Document; break gotoSomething;
        }

NULL    {   if(lvl.spaces > -1) {
                    parser.popLevel();
                    YYPOS(0);
                    return DefaultYAMLParser.YAML_IEND;
            }
            YYPOS(0);
            return 0;
        }

*/
               }
               case Directive: {
                   parser.token = parser.cursor;

/*!re2j

DIR        {   CHK_NL(parser.cursor);
               mainLoopGoto = Directive; break gotoSomething;
           }

ANY        {   parser.cursor = parser.token;
               return DefaultYAMLParser.YAML_DOCSEP;
           }
*/
}
               case Comment: {
                   parser.token = parser.cursor;

/*!re2j

LF          {   CHK_NL(parser.cursor);
                mainLoopGoto = Document; break gotoSomething; }

ANY         {   mainLoopGoto = Comment; break gotoSomething; }

*/
}
               case Scalar:
                   q = new QuotedString();
                   q.str[0] = 0;
               case Scalar2: {
                   tok = parser.cursor;

/*!re2j
LF SCC  {   CHK_NL(tok+1);
            mainLoopGoto = Scalar2; break gotoSomething; }

LF NNL  {   CHK_NL(tok+1);
            if(tok + 2 < parser.cursor) {
                int count = tok + 2;
                int total = Integer.valueOf(new String(parser.buffer.buffer, tok + 2, parser.cursor - (tok + 2)), 10).intValue();
                for(int i=0; i<total; i++) {
                    q.cat('\n');
                }
            } else {
                q.cat('\n');
            }
            mainLoopGoto = Scalar2; break gotoSomething;
        }

LF NLZ  {   CHK_NL(tok+1);
            q.cat((byte)0);
            mainLoopGoto = Scalar2; break gotoSomething;
        }

LF      {   parser.cursor = tok;
            mainLoopGoto = ScalarEnd; break gotoSomething;
        }

NULL    {   parser.cursor = tok;
            mainLoopGoto = ScalarEnd; break gotoSomething;
        }

ANY     {   q.cat(parser.buffer.buffer[tok]);
            mainLoopGoto = Scalar2; break gotoSomething;
        }

*/
}
               case ScalarEnd: {
                   Node n = Node.allocStr();
                   Data.Str dd = (Data.Str)n.data;
                   dd.ptr = Pointer.create(q.str, 0);
                   dd.len = q.idx;
                   lval = n;
                   parser.popLevel();
                   if(parser.implicit_typing) {
                       ImplicitScanner.tryTagImplicit(n, parser.taguri_expansion);
                   }          
                   return DefaultYAMLParser.YAML_PLAIN;
               }
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
