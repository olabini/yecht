// line 1 "src/main/org/yecht/TokenScanner.rl"
package org.yecht;

// Equivalent to token.re
public class TokenScanner {
// line 112 "src/main/org/yecht/TokenScanner.rl"



// line 11 "src/main/org/yecht/TokenScanner.java"
private static short[] init__TokenScanner_key_offsets_0()
{
	return new short [] {
	    0,    0,    6,    7,   18,   19,   20,   21,   22,   25,   26,   27,
	   28,   51,   52,   53,   61,   64,   65,   72,   79,   88,   98,  107,
	  117,  118,  119,  122,  123,  130,  131,  132,  140,  141,  142,  157,
	  158,  161,  167,  173,  181,  182,  194,  200,  206,  214,  215,  216,
	  217,  218,  221,  222,  222,  224,  224,  226,  230,  233,  234,  234,
	  236,  240,  243,  251,  252,  252,  254,  263,  263,  267,  270,  271,
	  272,  272,  276,  279,  280,  280,  284,  287,  287,  288,  288,  292,
	  295
	};
}

private static final short _TokenScanner_key_offsets[] = init__TokenScanner_key_offsets_0();


private static char[] init__TokenScanner_trans_keys_0()
{
	return new char [] {
	    0,   10,   13,   55,   48,   51,   10,    0,    9,   10,   13,   32,
	   35,   45,   46,   55,   48,   51,   10,   10,   45,   45,   10,   13,
	   32,   10,   46,   46,    0,    9,   10,   13,   32,   38,   39,   42,
	   55,   59,   62,   63,   91,   93,  124,   33,   35,   44,   45,   48,
	   51,  123,  125,   10,   10,   45,   95,   48,   57,   65,   90,   97,
	  122,   10,   13,   32,   10,   10,   13,   32,   43,   45,   48,   57,
	    0,    9,   32,   37,   55,   48,   51,   95,   45,   46,   48,   57,
	   65,   90,   97,  122,   58,   95,   45,   46,   48,   57,   65,   90,
	   97,  122,   95,   45,   46,   48,   57,   65,   90,   97,  122,    0,
	    9,   10,   13,   32,   44,   48,   58,   93,  125,   10,   10,   10,
	   13,   32,   10,    0,   10,   13,   39,   55,   48,   51,   10,   10,
	    0,   10,   13,   34,   55,   92,   48,   51,   10,   10,   10,   13,
	   32,   34,   48,   92,  110,  114,  116,  118,  120,   97,   98,  101,
	  102,   10,   10,   13,   32,   48,   57,   65,   70,   97,  102,   48,
	   57,   65,   70,   97,  102,    0,   10,   13,   32,   55,   92,   48,
	   51,   10,   34,   48,   92,  110,  114,  116,  118,  120,   97,   98,
	  101,  102,   48,   57,   65,   70,   97,  102,   48,   57,   65,   70,
	   97,  102,    0,   10,   13,   35,   45,   55,   48,   51,   10,   10,
	   45,   45,   10,   13,   32,   10,   10,   13,    9,   32,    9,   10,
	   13,   32,   10,   13,   32,   32,    9,   32,    9,   10,   13,   32,
	   10,   13,   32,   45,   95,   48,   57,   65,   90,   97,  122,   32,
	    9,   32,   95,   45,   46,   48,   57,   65,   90,   97,  122,    9,
	   10,   13,   32,   10,   13,   32,   35,   32,    9,   10,   13,   32,
	   10,   13,   32,   39,    9,   10,   13,   32,   10,   13,   32,   32,
	    9,   10,   13,   32,   10,   13,   32,   32,    0
	};
}

private static final char _TokenScanner_trans_keys[] = init__TokenScanner_trans_keys_0();


private static byte[] init__TokenScanner_single_lengths_0()
{
	return new byte [] {
	    0,    4,    1,    9,    1,    1,    1,    1,    3,    1,    1,    1,
	   15,    1,    1,    2,    3,    1,    5,    5,    1,    2,    1,   10,
	    1,    1,    3,    1,    5,    1,    1,    6,    1,    1,   11,    1,
	    3,    0,    0,    6,    1,    8,    0,    0,    6,    1,    1,    1,
	    1,    3,    1,    0,    2,    0,    2,    4,    3,    1,    0,    2,
	    4,    3,    2,    1,    0,    2,    1,    0,    4,    3,    1,    1,
	    0,    4,    3,    1,    0,    4,    3,    0,    1,    0,    4,    3,
	    1
	};
}

private static final byte _TokenScanner_single_lengths[] = init__TokenScanner_single_lengths_0();


private static byte[] init__TokenScanner_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    1,    0,    0,    0,    0,    0,    0,    0,    0,
	    4,    0,    0,    3,    0,    0,    1,    1,    4,    4,    4,    0,
	    0,    0,    0,    0,    1,    0,    0,    1,    0,    0,    2,    0,
	    0,    3,    3,    1,    0,    2,    3,    3,    1,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    3,    0,    0,    0,    4,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0
	};
}

private static final byte _TokenScanner_range_lengths[] = init__TokenScanner_range_lengths_0();


private static short[] init__TokenScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,    6,    8,   19,   21,   23,   25,   27,   31,   33,   35,
	   37,   57,   59,   61,   67,   71,   73,   80,   87,   93,  100,  106,
	  117,  119,  121,  125,  127,  134,  136,  138,  146,  148,  150,  164,
	  166,  170,  174,  178,  186,  188,  199,  203,  207,  215,  217,  219,
	  221,  223,  227,  229,  230,  233,  234,  237,  242,  246,  248,  249,
	  252,  257,  261,  267,  269,  270,  273,  279,  280,  285,  289,  291,
	  293,  294,  299,  303,  305,  306,  311,  315,  316,  318,  319,  324,
	  328
	};
}

private static final short _TokenScanner_index_offsets[] = init__TokenScanner_index_offsets_0();


private static byte[] init__TokenScanner_trans_targs_0()
{
	return new byte [] {
	   51,   52,    2,   51,   51,    0,   52,    0,   53,   54,   55,    5,
	   54,   53,    6,   10,   53,   53,    0,   56,    0,   55,    0,    7,
	    0,    8,    0,   53,    9,   57,    0,   53,    0,   11,    0,    8,
	    0,   58,   59,   60,   14,   59,   15,   58,   15,   58,   16,   18,
	   16,   58,   58,   18,   58,   16,   58,   58,    0,   61,    0,   60,
	    0,   62,   62,   62,   62,   62,    0,   58,   17,   63,    0,   58,
	    0,   58,   17,   63,   18,   18,   18,    0,   64,   65,   65,   20,
	   64,   64,    0,   21,   21,   21,   21,   21,    0,   22,   21,   21,
	   21,   21,   21,    0,   66,   66,   66,   66,   66,    0,   67,   67,
	   68,   25,   70,   26,   67,   26,   67,   67,    0,   69,    0,   68,
	    0,   67,   27,   71,    0,   67,    0,   72,   73,   30,   75,   72,
	   72,    0,   74,    0,   73,    0,   76,   77,   33,   76,   76,   34,
	   76,    0,   78,    0,   77,    0,   76,   35,   36,   76,   76,   76,
	   76,   76,   76,   76,   37,   76,   76,    0,   76,    0,   76,   35,
	   36,    0,   38,   38,   38,    0,   76,   76,   76,    0,   79,   79,
	   40,   80,   79,   41,   79,    0,   79,    0,   79,   79,   79,   79,
	   79,   79,   79,   42,   79,   79,    0,   43,   43,   43,    0,   79,
	   79,   79,    0,   81,   82,   46,   81,   47,   81,   81,    0,   83,
	    0,   82,    0,   48,    0,   49,    0,   81,   50,   84,    0,   81,
	    0,    0,   52,    2,    0,    0,   54,   54,    0,   55,   56,    4,
	   56,    0,   56,    4,   56,    0,   57,    0,    0,   59,   59,    0,
	   60,   61,   13,   61,    0,   61,   13,   61,    0,   62,   62,   62,
	   62,   62,    0,   63,    0,    0,   65,   65,    0,   66,   66,   66,
	   66,   66,    0,    0,   68,   69,   24,   69,    0,   69,   24,   69,
	    0,   67,    0,   71,    0,    0,   73,   74,   29,   74,    0,   74,
	   29,   74,    0,   72,    0,    0,   77,   78,   32,   78,    0,   78,
	   32,   78,    0,    0,   80,    0,    0,   82,   83,   45,   83,    0,
	   83,   45,   83,    0,   84,    0,    0
	};
}

private static final byte _TokenScanner_trans_targs[] = init__TokenScanner_trans_targs_0();


static final int TokenScanner_start = 1;
static final int TokenScanner_error = 0;

static final int TokenScanner_en_Header = 3;
static final int TokenScanner_en_Document = 12;
static final int TokenScanner_en_Directive = 19;
static final int TokenScanner_en_Plain = 23;
static final int TokenScanner_en_SingleQuote = 28;
static final int TokenScanner_en_DoubleQuote = 31;
static final int TokenScanner_en_TransferMethod = 39;
static final int TokenScanner_en_ScalarBlock = 44;
static final int TokenScanner_en_Comment = 1;

// line 115 "src/main/org/yecht/TokenScanner.rl"

   public void recognize(byte[] data, int start, int len) {
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+len;
       int tokstart = -1;
       int tokend = -1;
              
       cs = TokenScanner_en_Header;

// line 178 "src/main/org/yecht/TokenScanner.java"
	{
	int _klen;
	int _trans = 0;
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

	cs = _TokenScanner_trans_targs[_trans];

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
case 5:
	}
	break; }
	}
// line 128 "src/main/org/yecht/TokenScanner.rl"
   }
}
