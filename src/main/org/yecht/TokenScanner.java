// line 1 "src/main/org/yecht/TokenScanner.rl"
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

// line 169 "src/main/org/yecht/TokenScanner.rl"



// line 24 "src/main/org/yecht/TokenScanner.java"
private static byte[] init__TokenScanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4
	};
}

private static final byte _TokenScanner_actions[] = init__TokenScanner_actions_0();


private static short[] init__TokenScanner_key_offsets_0()
{
	return new short [] {
	    0,    0,    6,    7,   19,   20,   21,   22,   23,   26,   27,   28,
	   29,   32,   33,   56,   57,   58,   66,   69,   70,   77,   84,   93,
	  103,  112,  122,  123,  124,  127,  128,  135,  136,  137,  145,  146,
	  147,  162,  163,  166,  172,  178,  186,  187,  199,  205,  211,  219,
	  220,  221,  222,  223,  226,  227,  227,  229,  229,  231,  235,  238,
	  238,  238,  239,  239,  240,  240,  240,  242,  246,  249,  257,  258,
	  258,  260,  269,  269,  273,  276,  277,  278,  278,  282,  285,  286,
	  286,  290,  293,  293,  294,  294,  298,  301
	};
}

private static final short _TokenScanner_key_offsets[] = init__TokenScanner_key_offsets_0();


private static char[] init__TokenScanner_trans_keys_0()
{
	return new char [] {
	    0,   10,   13,   55,   48,   51,   10,    0,    9,   10,   13,   32,
	   35,   45,   46,   48,   55,   49,   51,   10,   10,   45,   45,   10,
	   13,   32,   10,   46,   46,   10,   13,   32,   10,    0,    9,   10,
	   13,   32,   38,   39,   42,   55,   59,   62,   63,   91,   93,  124,
	   33,   35,   44,   45,   48,   51,  123,  125,   10,   10,   45,   95,
	   48,   57,   65,   90,   97,  122,   10,   13,   32,   10,   10,   13,
	   32,   43,   45,   48,   57,    0,    9,   32,   37,   55,   48,   51,
	   95,   45,   46,   48,   57,   65,   90,   97,  122,   58,   95,   45,
	   46,   48,   57,   65,   90,   97,  122,   95,   45,   46,   48,   57,
	   65,   90,   97,  122,    0,    9,   10,   13,   32,   44,   48,   58,
	   93,  125,   10,   10,   10,   13,   32,   10,    0,   10,   13,   39,
	   55,   48,   51,   10,   10,    0,   10,   13,   34,   55,   92,   48,
	   51,   10,   10,   10,   13,   32,   34,   48,   92,  110,  114,  116,
	  118,  120,   97,   98,  101,  102,   10,   10,   13,   32,   48,   57,
	   65,   70,   97,  102,   48,   57,   65,   70,   97,  102,    0,   10,
	   13,   32,   55,   92,   48,   51,   10,   34,   48,   92,  110,  114,
	  116,  118,  120,   97,   98,  101,  102,   48,   57,   65,   70,   97,
	  102,   48,   57,   65,   70,   97,  102,    0,   10,   13,   35,   45,
	   55,   48,   51,   10,   10,   45,   45,   10,   13,   32,   10,   10,
	   13,    9,   32,    9,   10,   13,   32,   10,   13,   32,   32,   32,
	    9,   32,    9,   10,   13,   32,   10,   13,   32,   45,   95,   48,
	   57,   65,   90,   97,  122,   32,    9,   32,   95,   45,   46,   48,
	   57,   65,   90,   97,  122,    9,   10,   13,   32,   10,   13,   32,
	   35,   32,    9,   10,   13,   32,   10,   13,   32,   39,    9,   10,
	   13,   32,   10,   13,   32,   32,    9,   10,   13,   32,   10,   13,
	   32,   32,    0
	};
}

private static final char _TokenScanner_trans_keys[] = init__TokenScanner_trans_keys_0();


private static byte[] init__TokenScanner_single_lengths_0()
{
	return new byte [] {
	    0,    4,    1,   10,    1,    1,    1,    1,    3,    1,    1,    1,
	    3,    1,   15,    1,    1,    2,    3,    1,    5,    5,    1,    2,
	    1,   10,    1,    1,    3,    1,    5,    1,    1,    6,    1,    1,
	   11,    1,    3,    0,    0,    6,    1,    8,    0,    0,    6,    1,
	    1,    1,    1,    3,    1,    0,    2,    0,    2,    4,    3,    0,
	    0,    1,    0,    1,    0,    0,    2,    4,    3,    2,    1,    0,
	    2,    1,    0,    4,    3,    1,    1,    0,    4,    3,    1,    0,
	    4,    3,    0,    1,    0,    4,    3,    1
	};
}

private static final byte _TokenScanner_single_lengths[] = init__TokenScanner_single_lengths_0();


private static byte[] init__TokenScanner_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    4,    0,    0,    3,    0,    0,    1,    1,    4,    4,
	    4,    0,    0,    0,    0,    0,    1,    0,    0,    1,    0,    0,
	    2,    0,    0,    3,    3,    1,    0,    2,    3,    3,    1,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,    0,
	    0,    4,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final byte _TokenScanner_range_lengths[] = init__TokenScanner_range_lengths_0();


private static short[] init__TokenScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,    6,    8,   20,   22,   24,   26,   28,   32,   34,   36,
	   38,   42,   44,   64,   66,   68,   74,   78,   80,   87,   94,  100,
	  107,  113,  124,  126,  128,  132,  134,  141,  143,  145,  153,  155,
	  157,  171,  173,  177,  181,  185,  193,  195,  206,  210,  214,  222,
	  224,  226,  228,  230,  234,  236,  237,  240,  241,  244,  249,  253,
	  254,  255,  257,  258,  260,  261,  262,  265,  270,  274,  280,  282,
	  283,  286,  292,  293,  298,  302,  304,  306,  307,  312,  316,  318,
	  319,  324,  328,  329,  331,  332,  337,  341
	};
}

private static final short _TokenScanner_index_offsets[] = init__TokenScanner_index_offsets_0();


private static byte[] init__TokenScanner_indicies_0()
{
	return new byte [] {
	    0,    1,    3,    0,    0,    2,    1,    2,    4,    5,    6,    7,
	    5,    8,    9,   10,    4,   11,   11,    2,   12,    2,   13,    2,
	   14,    2,   15,    2,   16,   17,   18,    2,   16,    2,   19,    2,
	   20,    2,   21,   22,   23,    2,   21,    2,   24,   25,   26,   27,
	   25,   28,   24,   28,   24,   29,   30,   29,   24,   24,   30,   24,
	   29,   24,   24,    2,   31,    2,   26,    2,   32,   32,   32,   32,
	   32,    2,   24,   33,   34,    2,   24,    2,   24,   33,   34,   30,
	   30,   30,    2,   35,   36,   36,   37,   35,   35,    2,   38,   38,
	   38,   38,   38,    2,   39,   38,   38,   38,   38,   38,    2,   40,
	   40,   40,   40,   40,    2,   41,   41,   42,   43,   44,   45,   41,
	   45,   41,   41,    2,   46,    2,   42,    2,   41,   47,   48,    2,
	   41,    2,   49,   50,   51,   52,   49,   49,    2,   53,    2,   50,
	    2,   54,   55,   56,   54,   54,   57,   54,    2,   58,    2,   55,
	    2,   54,   59,   60,   54,   54,   54,   54,   54,   54,   54,   61,
	   54,   54,    2,   54,    2,   54,   59,   60,    2,   62,   62,   62,
	    2,   54,   54,   54,    2,   63,   63,   64,   65,   63,   66,   63,
	    2,   63,    2,   63,   63,   63,   63,   63,   63,   63,   67,   63,
	   63,    2,   68,   68,   68,    2,   63,   63,   63,    2,   69,   70,
	   71,   69,   72,   69,   69,    2,   73,    2,   70,    2,   74,    2,
	   75,    2,   69,   76,   77,    2,   69,    2,    2,    1,    3,    2,
	    2,   78,   78,    2,   13,   12,   79,   12,    2,   12,   79,   12,
	    2,    2,    2,   18,    2,    2,   23,    2,    2,    2,   25,   25,
	    2,   26,   31,   80,   31,    2,   31,   80,   31,    2,   32,   32,
	   32,   32,   32,    2,   34,    2,    2,   36,   36,    2,   40,   40,
	   40,   40,   40,    2,    2,   42,   46,   81,   46,    2,   46,   81,
	   46,    2,   41,    2,   48,    2,    2,   50,   53,   82,   53,    2,
	   53,   82,   53,    2,   49,    2,    2,   55,   58,   83,   58,    2,
	   58,   83,   58,    2,    2,   65,    2,    2,   70,   73,   84,   73,
	    2,   73,   84,   73,    2,   77,    2,    0
	};
}

private static final byte _TokenScanner_indicies[] = init__TokenScanner_indicies_0();


private static byte[] init__TokenScanner_trans_targs_0()
{
	return new byte [] {
	   53,   54,    0,    2,   55,   56,   57,    5,   59,    6,   10,   64,
	   58,   57,    7,    8,   60,    9,   61,   11,   12,   62,   13,   63,
	   65,   66,   67,   16,   17,   18,   20,   68,   69,   19,   70,   71,
	   72,   22,   23,   24,   73,   74,   75,   27,   77,   28,   76,   29,
	   78,   79,   80,   32,   82,   81,   83,   84,   35,   36,   85,   37,
	   38,   39,   40,   86,   42,   87,   43,   44,   45,   88,   89,   48,
	   49,   90,   50,   51,   52,   91,   56,    4,   15,   26,   31,   34,
	   47
	};
}

private static final byte _TokenScanner_trans_targs[] = init__TokenScanner_trans_targs_0();


private static byte[] init__TokenScanner_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    1,    1,    1,    1,    1,    1,    1,    1,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0
	};
}

private static final byte _TokenScanner_trans_actions[] = init__TokenScanner_trans_actions_0();


private static byte[] init__TokenScanner_eof_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    9,    0,    0,    0,    7,
	    3,    3,    5,    5,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0
	};
}

private static final byte _TokenScanner_eof_actions[] = init__TokenScanner_eof_actions_0();


static final int TokenScanner_start = 1;
static final int TokenScanner_error = 0;

static final int TokenScanner_en_Header = 3;
static final int TokenScanner_en_Document = 14;
static final int TokenScanner_en_Directive = 21;
static final int TokenScanner_en_Plain = 25;
static final int TokenScanner_en_SingleQuote = 30;
static final int TokenScanner_en_DoubleQuote = 33;
static final int TokenScanner_en_TransferMethod = 41;
static final int TokenScanner_en_ScalarBlock = 46;
static final int TokenScanner_en_Comment = 1;

// line 172 "src/main/org/yecht/TokenScanner.rl"

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


// line 328 "src/main/org/yecht/TokenScanner.java"
	{
	int _klen;
	int _trans = 0;
	int _acts;
	int _nacts;
	int _keys;
	int _goto_targ = 0;

	_goto: while (true) {
	switch ( _goto_targ ) {
	case 0:
	if ( p == pe ) {
		_goto_targ = 4;
		continue _goto;
	}
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
case 1:
	_match: do {
	_keys = _TokenScanner_key_offsets[cs];
	_trans = _TokenScanner_index_offsets[cs];
	_klen = _TokenScanner_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _TokenScanner_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _TokenScanner_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _TokenScanner_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _TokenScanner_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _TokenScanner_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _TokenScanner_indicies[_trans];
	cs = _TokenScanner_trans_targs[_trans];

	if ( _TokenScanner_trans_actions[_trans] != 0 ) {
		_acts = _TokenScanner_trans_actions[_trans];
		_nacts = (int) _TokenScanner_actions[_acts++];
		while ( _nacts-- > 0 )
	{
			switch ( _TokenScanner_actions[_acts++] )
			{
	case 0:
// line 21 "src/main/org/yecht/TokenScanner.rl"
	{
          parser.token = parser.cursor.start;
        }
	break;
// line 414 "src/main/org/yecht/TokenScanner.java"
			}
		}
	}

case 2:
	if ( cs == 0 ) {
		_goto_targ = 5;
		continue _goto;
	}
	if ( ++p != pe ) {
		_goto_targ = 1;
		continue _goto;
	}
case 4:
	if ( p == eof )
	{
	int __acts = _TokenScanner_eof_actions[cs];
	int __nacts = (int) _TokenScanner_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _TokenScanner_actions[__acts++] ) {
	case 1:
// line 49 "src/main/org/yecht/TokenScanner.rl"
	{
             Level lvl = parser.currentLevel();
             if(lvl.status == LevelStatus.header) {
               YYPOS(3);
               {cs = 21; _goto_targ = 2; if (true) continue _goto;}
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
	break;
	case 2:
// line 64 "src/main/org/yecht/TokenScanner.rl"
	{   
                        Level lvl = parser.currentLevel();
                        if(lvl.status == LevelStatus.header) {
                          {cs = 3; _goto_targ = 2; if (true) continue _goto;}
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
	break;
	case 3:
// line 78 "src/main/org/yecht/TokenScanner.rl"
	{
           eatComments();
           {cs = 3; _goto_targ = 2; if (true) continue _goto;}
         }
	break;
	case 4:
// line 82 "src/main/org/yecht/TokenScanner.rl"
	{
           Level lvl = parser.currentLevel();
           if(lvl.spaces > -1) {
              parser.popLevel();
              YYPOS(0);
              return YAML_IEND;
           }
           YYPOS(0);
           return 0;
         }
	break;
// line 490 "src/main/org/yecht/TokenScanner.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 259 "src/main/org/yecht/TokenScanner.rl"

     return 0;
   }
}
