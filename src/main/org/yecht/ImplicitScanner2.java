// line 1 "src/main/org/yecht/ImplicitScanner2.rl"
package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner2 {
    // try_tag_implicit
    public static void tryTagImplicit(Node n, boolean taguri) {
      String tid = "";
      switch(n.kind) {
        case Str:
          Data.Str s = (Data.Str)n.data;
          tid = matchImplicit(s.ptr, s.len);
          break;
        case Seq:
          tid = "seq";
          break;
        case Map:
          tid = "map";
          break;
        default:
          break;
      }

      if(taguri) {
        n.type_id = Parser.taguri(YAML.DOMAIN, tid);
      } else {
        n.type_id = tid;
      }
    }

    // syck_tagcmp
    public static boolean tagcmp(String tag1, String tag2) {
        if(tag1 == tag2) return true;
        if(tag1 == null || tag2 == null) return false;
        if(tag1.equals(tag2)) return true;
        int slen1 = tag1.indexOf('#');
        if(slen1 == -1) slen1 = tag1.length();
        int slen2 = tag2.indexOf('#');
        if(slen2 == -1) slen2 = tag2.length();
        return tag1.substring(0, slen1).equals(tag2.substring(0,slen2));
    }

// line 78 "src/main/org/yecht/ImplicitScanner2.rl"



// line 48 "src/main/org/yecht/ImplicitScanner2.java"
private static byte[] init__ImplicitScanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   14,    1,   15,    1,   17,    1,
	   18,    2,   14,   16
	};
}

private static final byte _ImplicitScanner_actions[] = init__ImplicitScanner_actions_0();


private static short[] init__ImplicitScanner_key_offsets_0()
{
	return new short [] {
	    0,    0,   19,   23,   25,   27,   28,   29,   30,   35,   37,   39,
	   44,   48,   55,   59,   61,   63,   64,   65,   66,   70,   72,   73,
	   74,   75,   77,   79,   80,   82,   84,   88,   90,   91,   93,   95,
	   96,   98,  100,  103,  108,  110,  112,  114,  116,  120,  122,  124,
	  125,  127,  129,  130,  132,  134,  138,  140,  142,  144,  146,  152,
	  158,  160,  162,  163,  165,  167,  168,  170,  172,  176,  181,  187,
	  192,  197,  198,  200,  201,  202,  203,  204,  205,  206,  210,  211,
	  212,  213,  214,  218,  219,  220,  222,  223,  224,  225,  226,  228,
	  229,  230,  231,  233,  235,  236,  237,  237,  245,  252,  258,  261,
	  263,  267,  270,  272,  279,  284,  284,  284,  292,  299,  306,  314,
	  318,  319,  319,  320,  320,  320,  325,  330,  335,  341,  341,  341,
	  341,  341
	};
}

private static final short _ImplicitScanner_key_offsets[] = init__ImplicitScanner_key_offsets_0();


private static char[] init__ImplicitScanner_trans_keys_0()
{
	return new char [] {
	   43,   45,   46,   48,   60,   61,   70,   78,   79,   84,   89,  102,
	  110,  111,  116,  121,  126,   49,   57,   46,   48,   49,   57,   73,
	  105,   78,  110,   70,  102,  110,   46,   69,  101,   48,   57,   43,
	   45,   48,   57,   44,   46,   58,   48,   57,   48,   53,   54,   57,
	   44,   48,   57,   65,   70,   97,  102,   46,   48,   49,   57,   73,
	  105,   78,  110,   70,  102,  110,   73,   78,  105,  110,   65,   97,
	   78,   97,  110,   48,   57,   48,   57,   45,   48,   57,   48,   57,
	    9,   32,   48,   57,   48,   57,   58,   48,   57,   48,   57,   58,
	   48,   57,   48,   57,    9,   32,   46,    9,   32,   43,   45,   90,
	   48,   57,   48,   57,   48,   57,   48,   57,    9,   32,   48,   57,
	   48,   57,   48,   57,   58,   48,   57,   48,   57,   58,   48,   57,
	   48,   57,   43,   45,   46,   90,   48,   57,   48,   57,   48,   57,
	   48,   57,   43,   45,   48,   90,   49,   57,   43,   45,   48,   90,
	   49,   57,   48,   57,   48,   57,   58,   48,   57,   48,   57,   58,
	   48,   57,   48,   57,   43,   45,   46,   90,   43,   45,   90,   48,
	   57,   44,   45,   46,   58,   48,   57,   44,   46,   58,   48,   57,
	   44,   46,   58,   48,   57,   60,   65,   97,   76,   83,   69,  108,
	  115,  101,   79,   85,  111,  117,   76,   76,  108,  108,   70,   78,
	  102,  110,   70,  102,   82,  114,   85,   69,  117,  101,   69,  101,
	   83,  115,   97,  111,  117,  102,  110,  114,  101,   44,   46,   58,
	  120,   48,   55,   56,   57,   44,   46,   58,   48,   55,   56,   57,
	   44,   46,   69,  101,   48,   57,   44,   48,   57,   48,   57,   46,
	   58,   48,   57,   44,   48,   57,   46,   58,   44,   48,   57,   65,
	   70,   97,  102,   44,   46,   58,   48,   57,   44,   46,   58,  120,
	   48,   55,   56,   57,   44,   46,   58,   48,   55,   56,   57,   44,
	   46,   58,   48,   55,   56,   57,   44,   45,   46,   58,   48,   55,
	   56,   57,    9,   32,   84,  116,   58,   58,   44,   46,   58,   48,
	   57,   44,   46,   58,   48,   57,   44,   46,   58,   48,   57,   44,
	   45,   46,   58,   48,   57,    0
	};
}

private static final char _ImplicitScanner_trans_keys[] = init__ImplicitScanner_trans_keys_0();


private static byte[] init__ImplicitScanner_single_lengths_0()
{
	return new byte [] {
	    0,   17,    2,    2,    2,    1,    1,    1,    3,    2,    0,    3,
	    0,    1,    2,    2,    2,    1,    1,    1,    4,    2,    1,    1,
	    1,    0,    0,    1,    0,    0,    2,    0,    1,    0,    0,    1,
	    0,    0,    3,    5,    0,    0,    0,    0,    2,    0,    0,    1,
	    0,    0,    1,    0,    0,    4,    0,    0,    0,    0,    4,    4,
	    0,    0,    1,    0,    0,    1,    0,    0,    4,    3,    4,    3,
	    3,    1,    2,    1,    1,    1,    1,    1,    1,    4,    1,    1,
	    1,    1,    4,    1,    1,    2,    1,    1,    1,    1,    2,    1,
	    1,    1,    2,    2,    1,    1,    0,    4,    3,    4,    1,    0,
	    2,    1,    2,    1,    3,    0,    0,    4,    3,    3,    4,    4,
	    1,    0,    1,    0,    0,    3,    3,    3,    4,    0,    0,    0,
	    0,    0
	};
}

private static final byte _ImplicitScanner_single_lengths[] = init__ImplicitScanner_single_lengths_0();


private static byte[] init__ImplicitScanner_range_lengths_0()
{
	return new byte [] {
	    0,    1,    1,    0,    0,    0,    0,    0,    1,    0,    1,    1,
	    2,    3,    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    1,    1,    0,    1,    1,    1,    1,    0,    1,    1,    0,
	    1,    1,    0,    0,    1,    1,    1,    1,    1,    1,    1,    0,
	    1,    1,    0,    1,    1,    0,    1,    1,    1,    1,    1,    1,
	    1,    1,    0,    1,    1,    0,    1,    1,    0,    1,    1,    1,
	    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    2,    2,    1,    1,    1,
	    1,    1,    0,    3,    1,    0,    0,    2,    2,    2,    2,    0,
	    0,    0,    0,    0,    0,    1,    1,    1,    1,    0,    0,    0,
	    0,    0
	};
}

private static final byte _ImplicitScanner_range_lengths[] = init__ImplicitScanner_range_lengths_0();


private static short[] init__ImplicitScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,   19,   23,   26,   29,   31,   33,   35,   40,   43,   45,
	   50,   53,   58,   62,   65,   68,   70,   72,   74,   79,   82,   84,
	   86,   88,   90,   92,   94,   96,   98,  102,  104,  106,  108,  110,
	  112,  114,  116,  120,  126,  128,  130,  132,  134,  138,  140,  142,
	  144,  146,  148,  150,  152,  154,  159,  161,  163,  165,  167,  173,
	  179,  181,  183,  185,  187,  189,  191,  193,  195,  200,  205,  211,
	  216,  221,  223,  226,  228,  230,  232,  234,  236,  238,  243,  245,
	  247,  249,  251,  256,  258,  260,  263,  265,  267,  269,  271,  274,
	  276,  278,  280,  283,  286,  288,  290,  291,  298,  304,  310,  313,
	  315,  319,  322,  325,  330,  335,  336,  337,  344,  350,  356,  363,
	  368,  370,  371,  373,  374,  375,  380,  385,  390,  396,  397,  398,
	  399,  400
	};
}

private static final short _ImplicitScanner_index_offsets[] = init__ImplicitScanner_index_offsets_0();


private static short[] init__ImplicitScanner_indicies_0()
{
	return new short [] {
	    0,    2,    3,    4,    6,    7,    8,    9,   10,   11,   12,   13,
	   14,   15,   16,   17,   18,    5,    1,   19,   20,   21,    1,   22,
	   23,    1,   24,   25,    1,   26,    1,   26,    1,   25,    1,   27,
	   28,   28,   27,    1,   29,   29,    1,   30,    1,   31,   32,   33,
	   31,    1,   34,   35,    1,   36,   36,   36,   36,    1,   37,   20,
	   21,    1,   38,   39,    1,   40,   41,    1,   42,    1,   42,    1,
	   41,    1,   22,   43,   23,   44,    1,   45,   45,    1,   46,    1,
	   47,    1,   46,    1,   48,    1,   49,    1,   50,    1,   51,    1,
	   52,    1,   53,   53,   54,    1,   55,    1,   56,    1,   57,    1,
	   58,    1,   59,    1,   60,    1,   61,    1,   62,   62,   63,    1,
	   62,   62,   64,   64,   65,    1,   66,    1,   67,    1,   68,    1,
	   65,    1,   62,   62,   63,    1,   69,    1,   70,    1,   71,    1,
	   72,    1,   73,    1,   74,    1,   75,    1,   76,    1,   77,   77,
	   78,   79,    1,   80,    1,   81,    1,   82,    1,   83,    1,   77,
	   77,   78,   83,   84,    1,   77,   77,   78,   79,   84,    1,   85,
	    1,   86,    1,   87,    1,   88,    1,   89,    1,   90,    1,   91,
	    1,   92,    1,   77,   77,   93,   83,    1,   77,   77,   83,   93,
	    1,   31,   94,   32,   33,   31,    1,   31,   32,   33,   95,    1,
	   31,   32,   33,   96,    1,   97,    1,   98,   99,    1,  100,    1,
	  101,    1,  102,    1,  103,    1,  104,    1,  102,    1,  102,  105,
	  102,  106,    1,  107,    1,   18,    1,  108,    1,   18,    1,  109,
	  110,  111,  110,    1,  102,    1,  102,    1,  112,  113,    1,  114,
	    1,  110,    1,  115,    1,  110,    1,  116,  117,    1,  110,    1,
	  110,    1,   99,    1,  102,  106,    1,  111,  110,    1,  113,    1,
	  117,    1,    1,  118,   32,   33,  119,  118,   31,    1,  118,   32,
	   33,  118,   31,    1,  120,   27,   28,   28,   32,    1,  120,  120,
	    1,   30,    1,  121,   33,   35,    1,  121,  121,    1,  121,   33,
	    1,   36,   36,   36,   36,    1,   21,   32,   33,   21,    1,    1,
	    1,  118,   32,   33,  119,  122,  123,    1,  118,   32,   33,  124,
	   96,    1,  118,   32,   33,  125,   95,    1,  118,   94,   32,   33,
	  118,   31,    1,   53,   53,  126,  127,    1,  128,    1,    1,  129,
	    1,    1,    1,   21,   32,   33,  130,    1,   21,   32,   33,  131,
	    1,   21,   32,   33,  132,    1,   21,   94,   32,   33,   21,    1,
	    1,    1,    1,    1,    1,    0
	};
}

private static final short _ImplicitScanner_indicies[] = init__ImplicitScanner_indicies_0();


private static short[] init__ImplicitScanner_trans_targs_0()
{
	return new short [] {
	    2,    0,   14,   20,  115,  125,   73,  130,   74,   81,   86,   89,
	   94,   97,   98,   99,  100,  101,  132,    3,  103,  112,    4,    7,
	    5,    6,  102,    8,    9,   10,  107,   11,  105,   12,  108,  110,
	  111,   15,   16,   19,   17,   18,  113,   21,   23,   22,  114,   24,
	   26,   27,   28,   29,  119,   30,   31,   32,   33,   34,   35,   36,
	   37,   38,   39,   44,   40,  121,   41,  120,   43,   46,   47,   48,
	   49,   50,   51,   52,   53,   54,   58,  124,   55,  122,   57,  123,
	   59,   61,   62,   63,   64,   65,   66,   67,   68,   69,   25,   70,
	   71,  129,   75,   78,   76,   77,  131,   79,   80,   82,   84,   83,
	   85,   87,  133,   88,   90,   92,   91,   93,   95,   96,  104,   13,
	  106,  109,  116,   72,  117,  118,   45,   60,   42,   56,  126,  127,
	  128
	};
}

private static final short _ImplicitScanner_trans_targs[] = init__ImplicitScanner_trans_targs_0();


private static byte[] init__ImplicitScanner_trans_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0
	};
}

private static final byte _ImplicitScanner_trans_actions[] = init__ImplicitScanner_trans_actions_0();


private static byte[] init__ImplicitScanner_eof_actions_0()
{
	return new byte [] {
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,   21,   13,    9,   15,   15,   17,
	   11,   19,   11,    7,   13,   23,   25,   13,    9,    9,    9,   27,
	   31,   31,   29,   29,   37,   13,   13,   13,   13,   35,   33,    5,
	    1,    3
	};
}

private static final byte _ImplicitScanner_eof_actions[] = init__ImplicitScanner_eof_actions_0();


static final int ImplicitScanner_start = 1;
static final int ImplicitScanner_error = 0;

static final int ImplicitScanner_en_main = 1;

// line 81 "src/main/org/yecht/ImplicitScanner2.rl"

    // syck_match_implicit
    public static String matchImplicit(Pointer ptr, int len) {
       String tag = "str";
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=ptr.start;
       int pe = p+len;
       int eof = p+len;
       int tokstart = -1;
       int tokend = -1;

       byte[] data = ptr.buffer;
       if(len == 0) {
         data = new byte[]{(byte)'~'};
         p = 0;
         pe = 1;
         eof = 1;
       }
              

// line 319 "src/main/org/yecht/ImplicitScanner2.java"
	{
	cs = ImplicitScanner_start;
	}
// line 104 "src/main/org/yecht/ImplicitScanner2.rl"


// line 326 "src/main/org/yecht/ImplicitScanner2.java"
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
	_keys = _ImplicitScanner_key_offsets[cs];
	_trans = _ImplicitScanner_index_offsets[cs];
	_klen = _ImplicitScanner_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _ImplicitScanner_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _ImplicitScanner_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _ImplicitScanner_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _ImplicitScanner_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _ImplicitScanner_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	_trans = _ImplicitScanner_indicies[_trans];
	cs = _ImplicitScanner_trans_targs[_trans];

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
	int __acts = _ImplicitScanner_eof_actions[cs];
	int __nacts = (int) _ImplicitScanner_actions[__acts++];
	while ( __nacts-- > 0 ) {
		switch ( _ImplicitScanner_actions[__acts++] ) {
	case 0:
// line 56 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "null";}
	break;
	case 1:
// line 57 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "bool#yes";}
	break;
	case 2:
// line 58 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "bool#no";}
	break;
	case 3:
// line 59 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "int#hex";}
	break;
	case 4:
// line 60 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "int#oct";}
	break;
	case 5:
// line 61 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "int#base60";}
	break;
	case 6:
// line 62 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "int";}
	break;
	case 7:
// line 63 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#fix";}
	break;
	case 8:
// line 64 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#exp";}
	break;
	case 9:
// line 65 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#base60";}
	break;
	case 10:
// line 66 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#inf";}
	break;
	case 11:
// line 67 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#neginf";}
	break;
	case 12:
// line 68 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "float#nan";}
	break;
	case 13:
// line 69 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "timestamp#ymd";}
	break;
	case 14:
// line 70 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "timestamp#iso8601";}
	break;
	case 15:
// line 71 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "timestamp#spaced";}
	break;
	case 16:
// line 72 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "timestamp";}
	break;
	case 17:
// line 73 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "default";}
	break;
	case 18:
// line 74 "src/main/org/yecht/ImplicitScanner2.rl"
	{tag = "merge";}
	break;
// line 489 "src/main/org/yecht/ImplicitScanner2.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 106 "src/main/org/yecht/ImplicitScanner2.rl"

       return tag;
    }
}
