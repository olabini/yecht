package org.yecht;

import java.io.IOException;

// Equivalent to token.re
public class TokenScanner implements DefaultYAMLParser.yyInput {
   public final static int QUOTELEN = 1024;
   private Parser parser;

   private Object lval;
   private int currentToken = -1;

   public static void error(String msg, Parser parser) {
       if(parser.error_handler == null) {
           parser.error_handler = new ErrorHandler.Default();
       }
       parser.root = parser.root_on_error;
       parser.error_handler.handle(parser, msg);
   }

   public static DefaultYAMLParser.yyInput createScanner(Parser parser) {
     switch(parser.input_type) {
       case YAML_UTF8:
         return new TokenScanner(parser);
       case Bytecode_UTF8:
         return new BytecodeScanner(parser);
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

   private int isNewline(byte[] buff, int ptr) {
     return newlineLen(buff, ptr);
   }

   private int newlineLen(byte[] buff, int ptr) {
     if(buff[ptr] == '\n')
       return 1;

     if(buff[ptr] == '\r' && buff[ptr+1] == '\n')
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

   private void RETURN_YAML_BLOCK(QuotedString q, int blockType, int nlDoWhat) {
       Node n = Node.allocStr();
       if(parser.taguri_expansion) {
           n.type_id = Parser.taguri(YAML.DOMAIN, "str");
       } else {
           n.type_id = "str";
       }

       Data.Str dd = (Data.Str)n.data;
       dd.ptr = Pointer.create(q.str, 0);
       dd.len = q.idx;
       if(blockType == YAML.BLOCK_LIT) {
           dd.style = ScalarStyle.Literal;
       } else {
           dd.style = ScalarStyle.Fold;
       }
       if(q.idx > 0) {
           if(nlDoWhat != YAML.NL_KEEP) {
               int fc = dd.len - 1;
               while(isNewline(dd.ptr.buffer, fc) > 0) {
                   fc--;
               }
               if(nlDoWhat != YAML.NL_CHOMP && fc < (dd.len-1)) {
                   fc += 1;
               }
               dd.len = fc + 1;
           }
       }
       lval = n;
   }

   private int GET_TRUE_YAML_INDENT() {
       Level lvl_deep = parser.currentLevel();
       int indt_len = lvl_deep.spaces;
       if(lvl_deep.status == LevelStatus.seq || (indt_len == parser.cursor - parser.lineptr && lvl_deep.status != LevelStatus.map)) {
            parser.lvl_idx--;
            Level lvl_over = parser.currentLevel();
            indt_len = lvl_over.spaces;
            parser.lvl_idx++;
        }

        return indt_len;       
   }
   
   public final static String[] tnames = new String[126];
   static {
       tnames[0] = "ENDINPUT";
       tnames[1] = "ALIAS";
       tnames[2] = "ANCHOR";
       tnames[3] = "BLOCK";
       tnames[4] = "DOCSEP";
       tnames[5] = "IEND";
       tnames[6] = "INDENT";
       tnames[7] = "IOPEN";
       tnames[8] = "ITRANSFER";
       tnames[9] = "PLAIN";
       tnames[10] = "TAGURI";
       tnames[11] = "TRANSFER";
       tnames[12] = "WORD";
       tnames[13] = "error";
       tnames[44] = "COMMA";
       tnames[45] = "DASH";
       tnames[58] = "COLON";
       tnames[63] = "QUESTION";
       tnames[91] = "SQUAREO";
       tnames[93] = "SQUAREC";
       tnames[123] = "CURLYO";
       tnames[125] = "CURLYC";
   }

   private void YYPOS(int n) {
       parser.cursor = parser.token + n;
   }

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

       public void cat(byte[] l, int cs, int cl) {
           while(idx + cl >= capa) {
               capa += QUOTELEN;
               str = YAML.realloc(str, capa);
           }
           System.arraycopy(l, cs, str, idx, cl);
           idx += cl;
           str[idx] = 0;
       }

       public void plain_is_inl() {
           int walker = idx - 1;
           while(walker > 0 && (str[walker] == '\n' || str[walker] == ' ' || str[walker] == '\t')) {
               idx--;
               str[walker] = 0;
               walker--;
          }
       }
   }

   public void RETURN_IMPLICIT(QuotedString q) {
       Node n = Node.allocStr();
       parser.cursor = parser.token;
       Data.Str dd = (Data.Str)n.data;
       dd.ptr = Pointer.create(q.str, 0);
       dd.len = q.idx;
       dd.style = ScalarStyle.Plain;                            
       lval = n;
       if(parser.implicit_typing) {
           ImplicitScanner.tryTagImplicit(n, parser.taguri_expansion);
       }
   }

    private int real_yylex() throws IOException {
        if(parser.cursor == -1) {
            parser.read();
        }

//     System.err.println("real_yylex(" + new String(parser.buffer.buffer, parser.buffer.start, parser.bufsize) + ")");
//        System.out.println("real_yylex()");
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
YINDENT = LF ( SPC | LF )* ;
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

        if(parser.lineptr != parser.cursor) {
            return document(0);
        } else {
            return header();
        }
    }

    private int header() throws java.io.IOException {
//        System.out.println("header()");
        Level lvl;
        int doc_level = 0;
        while(true) {
            parser.token = parser.cursor;
/*!re2j

"---" ENDSPC        { lvl = parser.currentLevel();
                      if(lvl.status == LevelStatus.header) {
                          YYPOS(3);
                          return directive();
                      } else {
                          if(lvl.spaces > -1) {
                              parser.popLevel();
                              YYPOS(0);
                              return DefaultYAMLParser.YAML_IEND;
                          }
                          YYPOS(0);
                          return 0;
                      }
                    }

"..." ENDSPC        {   lvl = parser.currentLevel();
                        if(lvl.status != LevelStatus.header) {
                          if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return DefaultYAMLParser.YAML_IEND;
                          }
                          YYPOS(0);
                          return 0; 
                        }
                        break;
                    }

"#"                 {   
                        eatComments(); 
                        break;
                    }

NULL                {   lvl = parser.currentLevel();
                        if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return DefaultYAMLParser.YAML_IEND;
                        }
                        YYPOS(0);
                        return 0; 
                    }

YINDENT             {
                        int indent = parser.token;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
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
                        break;
                    }

SPCTAB+             {   doc_level = parser.cursor - parser.lineptr;
                        break;
                    }

ANY                 {   YYPOS(0);
                        return document(doc_level);
                    }

*/
        }
    }

   private int document(int doc_level) throws java.io.IOException {
//        System.out.println("document()");
       while(true) {
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
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
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
                        /* Ignore indentation inside inlines */
                        if(parser.buffer.buffer[parser.cursor] != '#' && lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                            /* Check for open indent */
                            if(lvl.spaces > indt_len) {
                                parser.popLevel();
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IEND;
                            }
                            if(lvl.spaces < indt_len) {
                                if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                    parser.addLevel(indt_len, LevelStatus.doc);
                                    return DefaultYAMLParser.YAML_IOPEN;
                                }
                            } else {
                                if(indt_len == -1) {
                                    return 0;
                                }
                                return DefaultYAMLParser.YAML_INDENT;
                            }
                        }
                        break;
                    }

ISEQO               {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            lvl = parser.currentLevel();
                            parser.addLevel(lvl.spaces + 1, LevelStatus.iseq);
                            return parser.buffer.buffer[parser.token];
                        }
                        break;                    
                    }

IMAPO               {
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            lvl = parser.currentLevel();
                            parser.addLevel(lvl.spaces + 1, LevelStatus.imap);
                            return parser.buffer.buffer[parser.token];
                        }
                        break;
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
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel((parser.token - parser.lineptr), LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            parser.force_token = DefaultYAMLParser.YAML_IOPEN;
                            if( parser.buffer.buffer[parser.cursor] == '#' || isNewline(parser.cursor) != 0 || isNewline(parser.cursor-1) != 0) {
                                parser.cursor--;
                                parser.addLevel(parser.token + 1 - parser.lineptr, LevelStatus.seq);
                            } else /* spaces followed by content uses the space as indentation */
                            {
                                parser.addLevel(parser.cursor - parser.lineptr, LevelStatus.seq);
                            }
                            return parser.buffer.buffer[parser.token];
                        }
                        break;
                    }
"&" YWORDC+         {   lval = new String(parser.buffer.buffer, parser.token + 1, parser.cursor - (parser.token + 1), "ISO-8859-1");

                        /*
                         * Remove previous anchors of the same name.  Since the parser will likely
                         * construct deeper nodes first, we want those nodes to be placed in the
                         * queue for matching at a higher level of indentation.
                         */
                        parser.removeAnchor((String)lval);
                        return DefaultYAMLParser.YAML_ANCHOR;
                    }

"*" YWORDC+         {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            lval = new String(parser.buffer.buffer, parser.token + 1, parser.cursor - (parser.token + 1), "ISO-8859-1");
                            return DefaultYAMLParser.YAML_ALIAS;
                        }
                        break;
                    }

"!"                 {   return transferMethod(); }

"'"                 {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            return singleQuote(); 
                        }
                        break;
                    }

"\""                {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            return doubleQuote();
                        }
                        break;
                    }

YBLOCK              {   if(isNewline(parser.cursor - 1) != 0) {
                            parser.cursor--;
                        }
                        return scalarBlock();
                    }

"#"                 {   
                        eatComments(); 
                        break;
                    }

SPCTAB+             {
                        break;
                    }

NULL                {   
                        if(lvl.spaces > -1) {
                            parser.popLevel();
                            YYPOS(0);
                            return DefaultYAMLParser.YAML_IEND;
                        }
                        YYPOS(0);
                        return 0; 
                    }

ANY                 {   
                        if(lvl.spaces < doc_level) {
                            if(lvl.status != LevelStatus.iseq && lvl.status != LevelStatus.imap) {
                                parser.addLevel(doc_level, LevelStatus.doc);
                                YYPOS(0);
                                return DefaultYAMLParser.YAML_IOPEN;
                            }
                        } else {
                            return plain();
                        }
                        break;
                    }

*/
       }
   }

   private int directive() throws java.io.IOException {
//        System.out.println("directive()");
       while(true) {
           parser.toktmp = parser.cursor;
/*!re2j

DIR                 {  break; }

SPCTAB+             {  break; }

ANY                 {   parser.cursor = parser.toktmp;
                        return DefaultYAMLParser.YAML_DOCSEP;
                    }
*/
       }
   }

   private int plain() throws java.io.IOException {
//        System.out.println("plain()");
       QuotedString q = new QuotedString();

       parser.cursor = parser.token;
       Level plvl = parser.currentLevel();

       Level lvl_deep = parser.currentLevel();
       int parentIndent = lvl_deep.spaces;
       if(lvl_deep.status == LevelStatus.seq || ((parentIndent == parser.cursor - parser.lineptr) && lvl_deep.status != LevelStatus.map)) {
           parser.lvl_idx--;
           Level lvl_over = parser.currentLevel();
           parentIndent = lvl_over.spaces;
           parser.lvl_idx++;
       }

       boolean plain3 = false;

       while(true) {
           parser.token = parser.cursor;
           do {
               plain3 = false;
/*!re2j

YINDENT             {   
                        int indt_len, nl_count = 0;
                        int tok = parser.token;
                        int indent = tok;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        indt_len = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          indt_len = -1;
                          tok = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          indt_len = parser.cursor - parser.lineptr;
                        }

                        if(indt_len <= parentIndent) {
                            RETURN_IMPLICIT(q);
                            return DefaultYAMLParser.YAML_PLAIN;
                        }

                        while(parser.token < parser.cursor) {
                            int nl_len = newlineLen(parser.token++);
                            if(nl_len > 0) {
                              nl_count++;
                              parser.token += (nl_len - 1);
                            }
                        }

                        if(nl_count <= 1) {
                            q.cat(' ');
                        } else {
                            for(int i = 0; i < nl_count - 1; i++) {
                                q.cat('\n');
                            }
                        }
                        break;
                    }

ALLX                {   
                        RETURN_IMPLICIT(q);
                        return DefaultYAMLParser.YAML_PLAIN;
                    }

ICOMMA              {  
                        if(plvl.status != LevelStatus.iseq && plvl.status != LevelStatus.imap) {
                            // PLAIN_NOT_INL
                            if(parser.buffer.buffer[parser.cursor-1] == ' ' || isNewline(parser.cursor-1) > 0) {
                                parser.cursor--;
                            }
                            q.cat(parser.buffer.buffer, parser.token, parser.cursor - parser.token);
                        } else {
                            q.plain_is_inl();
                            RETURN_IMPLICIT(q);
                            return DefaultYAMLParser.YAML_PLAIN;
                        }

                        break;
                    }

IMAPC               {   
                        if(plvl.status != LevelStatus.imap) {
                            // PLAIN_NOT_INL
                            if(parser.buffer.buffer[parser.cursor-1] == ' ' || isNewline(parser.cursor-1) > 0) {
                                parser.cursor--;
                            }
                            q.cat(parser.buffer.buffer, parser.token, parser.cursor - parser.token);
                        } else {
                            q.plain_is_inl();
                            RETURN_IMPLICIT(q);
                            return DefaultYAMLParser.YAML_PLAIN;
                        }
                        break;
                    }

ISEQC               {
                        if(plvl.status != LevelStatus.iseq) {
                            // PLAIN_NOT_INL
                            if(parser.buffer.buffer[parser.cursor-1] == ' ' || isNewline(parser.cursor-1) > 0) {
                                parser.cursor--;
                            }
                            q.cat(parser.buffer.buffer, parser.token, parser.cursor - parser.token);
                        } else {
                            q.plain_is_inl();
                            RETURN_IMPLICIT(q);
                            return DefaultYAMLParser.YAML_PLAIN;
                        }
                        break;
                    }

" #"                {   
                        eatComments(); 
                        RETURN_IMPLICIT(q);
                        return DefaultYAMLParser.YAML_PLAIN;
                    }

NULL                {   
                        RETURN_IMPLICIT(q);
                        return DefaultYAMLParser.YAML_PLAIN;
                    }

SPCTAB              {   
                        if(q.idx != 0) {
                            plain3 = true;
                        }
                        break;
                    }

ANY                 {
                        q.cat(parser.buffer.buffer, parser.token, parser.cursor - parser.token);
                        break;
                    }

*/

//               if(!plain3) {
//                  System.out.println("plain2()");
//               } else {
//                  System.out.println("plain3()");
//               }
           } while(plain3);
       }
   }

   private int doubleQuote() throws java.io.IOException {
//        System.out.println("doubleQuote()");
       int keep_nl = 1;
       QuotedString q = new QuotedString();
       while(true) {
           parser.token = parser.cursor;
/*!re2j

YINDENT             {
                        // GOBBLE_UP_YAML_INDENT( indt_len, YYTOKEN )
                        int indent = parser.token;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        int indt_len = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          indt_len = -1;
                          parser.token = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          indt_len = parser.cursor - parser.lineptr;
                        }

                        int nl_count = 0;
                        Level lvl = parser.currentLevel();
                        if(lvl.status != LevelStatus.str) {
                            parser.addLevel(indt_len, LevelStatus.str);
                        } else if(indt_len < lvl.spaces) {
                            // Error!
                        }

                        if(keep_nl == 1) {
                            while(parser.token < parser.cursor) {
                                int nl_len = newlineLen(parser.token++);
                                if(nl_len > 0) {
                                    nl_count++;
                                    parser.token += (nl_len - 1);
                                }
                            }
                            if(nl_count <= 1) {
                                q.cat(' ');
                            } else {
                                for(int i = 0; i < nl_count - 1; i++) {
                                    q.cat('\n');
                                }
                            }
                        }

                        keep_nl = 1;
                        break;
                    }

"\\" ESCSEQ         {   
                        byte ch = parser.buffer.buffer[parser.cursor-1];
                        q.cat(escapeSeq(ch));
                        break;
                    }

"\\x" HEX HEX       {    
                        q.cat((byte)Integer.valueOf(new String(parser.buffer.buffer, parser.token+2, 2, "ISO-8859-1"), 16).intValue());
                        break;
                    }

"\\" SPC* LF        {   keep_nl = 0;
                        parser.cursor--;
                        break;
                    }

( "\"" | NULL )     {   
                        Node n = Node.allocStr();
                        Level lvl = parser.currentLevel();

                        if(lvl.status == LevelStatus.str) {
                            parser.popLevel();
                        }

                        if(parser.taguri_expansion) {
                            n.type_id = Parser.taguri(YAML.DOMAIN, "str");
                        } else {
                            n.type_id = "str";
                        }
                        Data.Str dd = (Data.Str)n.data;
                        dd.ptr = Pointer.create(q.str, 0);
                        dd.len = q.idx;
                        dd.style = ScalarStyle.TwoQuote;
                        lval = n;
                        return DefaultYAMLParser.YAML_PLAIN;
                    }

ANY                 {   q.cat(parser.buffer.buffer[parser.cursor-1]);
                        break;
                    }

*/
       }
   }

   private int singleQuote() throws java.io.IOException {
//        System.out.println("singleQuote()");
       QuotedString q = new QuotedString();
       while(true) {
           parser.token = parser.cursor;
/*!re2j

YINDENT             {
                        // GOBBLE_UP_YAML_INDENT( indt_len, YYTOKEN )
                        int indent = parser.token;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        int indt_len = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          indt_len = -1;
                          parser.token = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          indt_len = parser.cursor - parser.lineptr;
                        }

                        int nl_count = 0;
                        Level lvl = parser.currentLevel();
                        if(lvl.status != LevelStatus.str) {
                            parser.addLevel(indt_len, LevelStatus.str);
                        } else if(indt_len < lvl.spaces) {
                            // Error!
                        }

                        while(parser.token < parser.cursor) {
                          int nl_len = newlineLen(parser.token++);
                          if(nl_len > 0) {
                            nl_count++;
                            parser.token += (nl_len - 1);
                          }
                        }
                        if(nl_count <= 1) {
                            q.cat(' ');
                        } else {
                            for(int i = 0; i < nl_count - 1; i++) {
                                q.cat('\n');
                            }
                        }
                        break;
                    }

"''"                {   q.cat('\'');
                        break;
                    }

( "'" | NULL )      {   
                        Node n = Node.allocStr();
                        Level lvl = parser.currentLevel();
                        if(lvl.status == LevelStatus.str) {
                            parser.popLevel();
                        }
                        if(parser.taguri_expansion) {
                            n.type_id = Parser.taguri(YAML.DOMAIN, "str");
                        } else {
                            n.type_id = "str";
                        }
                        Data.Str dd = (Data.Str)n.data;
                        dd.ptr = Pointer.create(q.str, 0);
                        dd.len = q.idx;
                        dd.style = ScalarStyle.OneQuote;
                        lval = n;
                        return DefaultYAMLParser.YAML_PLAIN; 
                    }

ANY                 {   q.cat(parser.buffer.buffer[parser.cursor-1]);
                        break;
                    }

*/
       }
   }

   private int transferMethod() throws java.io.IOException {
//        System.out.println("transferMethod()");
       QuotedString q = new QuotedString();
       while(true) {
           parser.toktmp = parser.cursor;
/*!re2j

( ENDSPC | NULL )   {   
                        parser.cursor = parser.toktmp;
                        if(parser.cursor == parser.token + 1) {
                            return DefaultYAMLParser.YAML_ITRANSFER;
                        }

                        Level lvl = parser.currentLevel();

                        /*
                         * URL Prefixing
                         */
                        if(q.str[0] == '^') {
                            lval = lvl.domain + new String(q.str, 1, q.idx - 1, "ISO-8859-1");
                        } else {
                            int carat = 0;
                            int qend = q.idx;
                            while((++carat) < qend) {
                              if(q.str[carat] == '^') {
                                break;
                              }
                            }

                            if(carat < qend) {
                                lvl.domain = new String(q.str, 0, carat, "ISO-8859-1");
                                lval = lvl.domain + new String(q.str, carat + 1, (qend - carat) - 1, "ISO-8859-1");
                            } else {
                                lval = new String(q.str, 0, qend, "ISO-8859-1");
                            }
                        }

                        return DefaultYAMLParser.YAML_TRANSFER; 
                    }

/*
 * URL Escapes
 */
"\\" ESCSEQ          {  
                        byte ch = parser.buffer.buffer[parser.cursor-1];
                        q.cat(escapeSeq(ch));
                        break;
                    }

"\\x" HEX HEX       {   
                        q.cat((byte)Integer.valueOf(new String(parser.buffer.buffer, parser.toktmp+2, 2, "ISO-8859-1"), 16).intValue());
                        break;
                    }

ANY                 {   
                        q.cat(parser.buffer.buffer[parser.cursor-1]);
                        break;
                    }
*/
       }
   }

   private int scalarBlock() throws java.io.IOException {
//        System.out.println("scalarBlock()");
       QuotedString q = new QuotedString();
       q.str[0] = 0;

       int lastIndent = 0;
       int parentIndent = -1;
       int blockType = 0;
       int nlDoWhat = 0;
       int forceIndent = -1;
       int yyt = parser.token;
       Level lvl = parser.currentLevel();

       switch(parser.buffer.buffer[yyt]) {
           case '|': blockType = YAML.BLOCK_LIT; break;
           case '>': blockType = YAML.BLOCK_FOLD; break;
       }

       while( ++yyt <= parser.cursor ) {
           if(parser.buffer.buffer[yyt] == '-') {
               nlDoWhat = YAML.NL_CHOMP;
           } else if(parser.buffer.buffer[yyt] == '+' ) {
               nlDoWhat = YAML.NL_KEEP;
           } else if(Character.isDigit((char)parser.buffer.buffer[yyt])) {
               forceIndent = (char)parser.buffer.buffer[yyt] - '0';
           }
       }

       while(true) {
           parser.token = parser.cursor;
/*!re2j

YINDENT             {   
                        int tok = parser.token;
                        int nl_count = 0, fold_nl = 0, nl_begin = 0;

                        int indent = tok;
                        NEWLINE(indent);
                        while(indent < parser.cursor) {
                            // these pieces commented out to be compatible with Syck 0.60. 
//                          if(parser.buffer.buffer[indent] == '\t') {
//                            error("TAB found in your indentation, please remove",parser);
//                          } else if(isNewline(++indent) != 0) {
//                            NEWLINE(indent);
//                          }

                          if(isNewline(++indent) != 0) {
                            NEWLINE(indent);
                          }
                        }
                        int indt_len = 0;
                        if(parser.buffer.buffer[parser.cursor] == 0) {
                          indt_len = -1;
                          tok = parser.cursor-1;
                        } else if(parser.buffer.buffer[parser.lineptr] == ' ') {
                          indt_len = parser.cursor - parser.lineptr;
                        }

                        lvl = parser.currentLevel();
                        if(lvl.status != LevelStatus.block) {
                            parentIndent = GET_TRUE_YAML_INDENT();
                            if(forceIndent > 0) forceIndent += parentIndent;
                            if(indt_len > parentIndent) {
                                int new_spaces = forceIndent > 0 ? forceIndent : indt_len;
                                parser.addLevel(new_spaces, LevelStatus.block);
                                lastIndent = indt_len - new_spaces;
                                nl_begin = 1;
                                lvl = parser.currentLevel();
                            } else {
                                parser.cursor = parser.token;
                                RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                                return DefaultYAMLParser.YAML_BLOCK;
                            }
                        }

                        /*
                         * Fold only in the event of two lines being on the leftmost
                         * indentation.
                         */
                        if(blockType == YAML.BLOCK_FOLD && lastIndent == 0 && (indt_len - lvl.spaces) == 0) {
                            fold_nl = 1;
                        }

                        int pacer = parser.token;
                        while(pacer < parser.cursor) {
                            int nl_len = newlineLen(pacer++);
                            if(nl_len>0) {
                                nl_count++;
                                pacer += (nl_len - 1);
                            }
                        }

                        if(fold_nl == 1 || nl_begin == 1) {
                            nl_count--;
                        }

                        if(nl_count < 1 && nl_begin == 0) {
                            q.cat(' ');
                        } else {
                            for(int i = 0; i < nl_count; i++) {
                                q.cat('\n');
                            }
                        }

                        lastIndent = indt_len - lvl.spaces;
                        parser.cursor -= lastIndent;

                        if(indt_len < lvl.spaces) {
                            parser.popLevel();
                            parser.cursor = parser.token;
                            RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                            return DefaultYAMLParser.YAML_BLOCK;
                        }
                        break;
                    }


"#"                 {   lvl = parser.currentLevel();
                        if(lvl.status != LevelStatus.block) {
                            eatComments();
                            parser.token = parser.cursor;
                        } else {
                            q.cat(parser.buffer.buffer[parser.token]);
                        }
                        break;
                    }
              

NULL                {   parser.cursor--;
                        parser.popLevel();
                        RETURN_YAML_BLOCK(q, blockType, nlDoWhat); 
                        return DefaultYAMLParser.YAML_BLOCK;
                    }

"---" ENDSPC        {   if(parser.token == parser.lineptr) {
                            if(blockType == YAML.BLOCK_FOLD && q.idx > 0) {
                                q.idx--;
                            }
                            q.cat('\n');
                            parser.popLevel();
                            parser.cursor = parser.token;
                            RETURN_YAML_BLOCK(q, blockType, nlDoWhat);
                            return DefaultYAMLParser.YAML_BLOCK;
                        } else {
                            q.cat(parser.buffer.buffer[parser.token]);
                            parser.cursor = parser.token + 1;
                        }
                        break;
                    }

ANY                 {   q.cat(parser.buffer.buffer[parser.token]);
                        break;
                    }
*/
       }
   }

   private byte escapeSeq(byte ch) {
       switch(ch) {
        case '0': return '\0';
        case 'a': return 7;
        case 'b': return '\010';
        case 'e': return '\033';
        case 'f': return '\014';
        case 'n': return '\n';
        case 'r': return '\015';
        case 't': return '\t';
        case 'v': return '\013';
        default: return ch;
      }
   }

   private void eatComments() throws IOException {
     while(true) {
       parser.token = parser.cursor;
/*!re2j

( LF+ | NULL )      {   parser.cursor = parser.token;
                        return;
                    }

ANY                 { break; }

*/
    }
  }
}
