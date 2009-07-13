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

// line 144 "src/main/org/yecht/TokenScanner.rl"



// line 24 "src/main/org/yecht/TokenScanner.java"
private static byte[] init__TokenScanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1
	};
}

private static final byte _TokenScanner_actions[] = init__TokenScanner_actions_0();


private static short[] init__TokenScanner_key_offsets_0()
{
	return new short [] {
	    0,    0,    6,    7,   18,   19,   20,   21,   22,   25,   26,   27,
	   28,   31,   32,   55,   56,   57,   65,   68,   69,   76,   83,   92,
	  102,  111,  121,  122,  123,  126,  127,  134,  135,  136,  144,  145,
	  146,  161,  162,  165,  171,  177,  185,  186,  198,  204,  210,  218,
	  219,  220,  221,  222,  225,  226,  226,  228,  228,  230,  234,  237,
	  237,  238,  239,  239,  241,  245,  248,  256,  257,  257,  259,  268,
	  268,  272,  275,  276,  277,  277,  281,  284,  285,  285,  289,  292,
	  292,  293,  293,  297,  300
	};
}

private static final short _TokenScanner_key_offsets[] = init__TokenScanner_key_offsets_0();


private static char[] init__TokenScanner_trans_keys_0()
{
	return new char [] {
	    0,   10,   13,   55,   48,   51,   10,    0,    9,   10,   13,   32,
	   35,   45,   46,   55,   48,   51,   10,   10,   45,   45,   10,   13,
	   32,   10,   46,   46,   10,   13,   32,   10,    0,    9,   10,   13,
	   32,   38,   39,   42,   55,   59,   62,   63,   91,   93,  124,   33,
	   35,   44,   45,   48,   51,  123,  125,   10,   10,   45,   95,   48,
	   57,   65,   90,   97,  122,   10,   13,   32,   10,   10,   13,   32,
	   43,   45,   48,   57,    0,    9,   32,   37,   55,   48,   51,   95,
	   45,   46,   48,   57,   65,   90,   97,  122,   58,   95,   45,   46,
	   48,   57,   65,   90,   97,  122,   95,   45,   46,   48,   57,   65,
	   90,   97,  122,    0,    9,   10,   13,   32,   44,   48,   58,   93,
	  125,   10,   10,   10,   13,   32,   10,    0,   10,   13,   39,   55,
	   48,   51,   10,   10,    0,   10,   13,   34,   55,   92,   48,   51,
	   10,   10,   10,   13,   32,   34,   48,   92,  110,  114,  116,  118,
	  120,   97,   98,  101,  102,   10,   10,   13,   32,   48,   57,   65,
	   70,   97,  102,   48,   57,   65,   70,   97,  102,    0,   10,   13,
	   32,   55,   92,   48,   51,   10,   34,   48,   92,  110,  114,  116,
	  118,  120,   97,   98,  101,  102,   48,   57,   65,   70,   97,  102,
	   48,   57,   65,   70,   97,  102,    0,   10,   13,   35,   45,   55,
	   48,   51,   10,   10,   45,   45,   10,   13,   32,   10,   10,   13,
	    9,   32,    9,   10,   13,   32,   10,   13,   32,   32,   32,    9,
	   32,    9,   10,   13,   32,   10,   13,   32,   45,   95,   48,   57,
	   65,   90,   97,  122,   32,    9,   32,   95,   45,   46,   48,   57,
	   65,   90,   97,  122,    9,   10,   13,   32,   10,   13,   32,   35,
	   32,    9,   10,   13,   32,   10,   13,   32,   39,    9,   10,   13,
	   32,   10,   13,   32,   32,    9,   10,   13,   32,   10,   13,   32,
	   32,    0
	};
}

private static final char _TokenScanner_trans_keys[] = init__TokenScanner_trans_keys_0();


private static byte[] init__TokenScanner_single_lengths_0()
{
	return new byte [] {
	    0,    4,    1,    9,    1,    1,    1,    1,    3,    1,    1,    1,
	    3,    1,   15,    1,    1,    2,    3,    1,    5,    5,    1,    2,
	    1,   10,    1,    1,    3,    1,    5,    1,    1,    6,    1,    1,
	   11,    1,    3,    0,    0,    6,    1,    8,    0,    0,    6,    1,
	    1,    1,    1,    3,    1,    0,    2,    0,    2,    4,    3,    0,
	    1,    1,    0,    2,    4,    3,    2,    1,    0,    2,    1,    0,
	    4,    3,    1,    1,    0,    4,    3,    1,    0,    4,    3,    0,
	    1,    0,    4,    3,    1
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
	    0,    0,    0,    0,    0,    0,    3,    0,    0,    0,    4,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0
	};
}

private static final byte _TokenScanner_range_lengths[] = init__TokenScanner_range_lengths_0();


private static short[] init__TokenScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,    6,    8,   19,   21,   23,   25,   27,   31,   33,   35,
	   37,   41,   43,   63,   65,   67,   73,   77,   79,   86,   93,   99,
	  106,  112,  123,  125,  127,  131,  133,  140,  142,  144,  152,  154,
	  156,  170,  172,  176,  180,  184,  192,  194,  205,  209,  213,  221,
	  223,  225,  227,  229,  233,  235,  236,  239,  240,  243,  248,  252,
	  253,  255,  257,  258,  261,  266,  270,  276,  278,  279,  282,  288,
	  289,  294,  298,  300,  302,  303,  308,  312,  314,  315,  320,  324,
	  325,  327,  328,  333,  337
	};
}

private static final short _TokenScanner_index_offsets[] = init__TokenScanner_index_offsets_0();


private static byte[] init__TokenScanner_indicies_0()
{
	return new byte [] {
	    0,    1,    3,    0,    0,    2,    1,    2,    4,    5,    6,    7,
	    5,    8,    9,   10,    4,    4,    2,   11,    2,    6,    2,   12,
	    2,   13,    2,   14,   15,   16,    2,   14,    2,   17,    2,   18,
	    2,    8,   19,   20,    2,    8,    2,   21,   22,   23,   24,   22,
	   25,   21,   25,   21,   26,   27,   26,   21,   21,   27,   21,   26,
	   21,   21,    2,   28,    2,   23,    2,   29,   29,   29,   29,   29,
	    2,   21,   30,   31,    2,   21,    2,   21,   30,   31,   27,   27,
	   27,    2,   32,   33,   33,   34,   32,   32,    2,   35,   35,   35,
	   35,   35,    2,   36,   35,   35,   35,   35,   35,    2,   37,   37,
	   37,   37,   37,    2,   38,   38,   39,   40,   41,   42,   38,   42,
	   38,   38,    2,   43,    2,   39,    2,   38,   44,   45,    2,   38,
	    2,   46,   47,   48,   49,   46,   46,    2,   50,    2,   47,    2,
	   51,   52,   53,   51,   51,   54,   51,    2,   55,    2,   52,    2,
	   51,   56,   57,   51,   51,   51,   51,   51,   51,   51,   58,   51,
	   51,    2,   51,    2,   51,   56,   57,    2,   59,   59,   59,    2,
	   51,   51,   51,    2,   60,   60,   61,   62,   60,   63,   60,    2,
	   60,    2,   60,   60,   60,   60,   60,   60,   60,   64,   60,   60,
	    2,   65,   65,   65,    2,   60,   60,   60,    2,   66,   67,   68,
	   66,   69,   66,   66,    2,   70,    2,   67,    2,   71,    2,   72,
	    2,   66,   73,   74,    2,   66,    2,    2,    1,    3,    2,    2,
	    5,    5,    2,    6,   11,   75,   11,    2,   11,   75,   11,    2,
	    2,   16,    2,   20,    2,    2,   22,   22,    2,   23,   28,   76,
	   28,    2,   28,   76,   28,    2,   29,   29,   29,   29,   29,    2,
	   31,    2,    2,   33,   33,    2,   37,   37,   37,   37,   37,    2,
	    2,   39,   43,   77,   43,    2,   43,   77,   43,    2,   38,    2,
	   45,    2,    2,   47,   50,   78,   50,    2,   50,   78,   50,    2,
	   46,    2,    2,   52,   55,   79,   55,    2,   55,   79,   55,    2,
	    2,   62,    2,    2,   67,   70,   80,   70,    2,   70,   80,   70,
	    2,   74,    2,    0
	};
}

private static final byte _TokenScanner_indicies[] = init__TokenScanner_indicies_0();


private static byte[] init__TokenScanner_trans_targs_0()
{
	return new byte [] {
	   53,   54,    0,    2,   55,   56,   57,    5,   55,    6,   10,   58,
	    7,    8,   59,    9,   60,   11,   12,   13,   61,   62,   63,   64,
	   16,   17,   18,   20,   65,   66,   19,   67,   68,   69,   22,   23,
	   24,   70,   71,   72,   27,   74,   28,   73,   29,   75,   76,   77,
	   32,   79,   78,   80,   81,   35,   36,   82,   37,   38,   39,   40,
	   83,   42,   84,   43,   44,   45,   85,   86,   48,   49,   87,   50,
	   51,   52,   88,    4,   15,   26,   31,   34,   47
	};
}

private static final byte _TokenScanner_trans_targs[] = init__TokenScanner_trans_targs_0();


private static byte[] init__TokenScanner_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,
	    3,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0
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

// line 147 "src/main/org/yecht/TokenScanner.rl"

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


// line 326 "src/main/org/yecht/TokenScanner.java"
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
// line 412 "src/main/org/yecht/TokenScanner.java"
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
// line 451 "src/main/org/yecht/TokenScanner.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 234 "src/main/org/yecht/TokenScanner.rl"

     return 0;
   }
}
