// line 1 "src/main/org/yecht/TokenScanner.rl"
package org.yecht;

// Equivalent to token.re
public class TokenScanner {
// line 58 "src/main/org/yecht/TokenScanner.rl"



// line 11 "src/main/org/yecht/TokenScanner.java"
private static byte[] init__TokenScanner_key_offsets_0()
{
	return new byte [] {
	    0,    0,   23,   24,   25,   33,   36,   37,   44,   55,   56,   57,
	   58,   59,   62,   63,   64,   65,   65,   67,   71,   74,   82,   83,
	   83,   85,   89,   92
	};
}

private static final byte _TokenScanner_key_offsets[] = init__TokenScanner_key_offsets_0();


private static char[] init__TokenScanner_trans_keys_0()
{
	return new char [] {
	    0,    9,   10,   13,   32,   38,   39,   42,   55,   59,   62,   63,
	   91,   93,  124,   33,   35,   44,   45,   48,   51,  123,  125,   10,
	   10,   45,   95,   48,   57,   65,   90,   97,  122,   10,   13,   32,
	   10,   10,   13,   32,   43,   45,   48,   57,    0,    9,   10,   13,
	   32,   35,   45,   46,   55,   48,   51,   10,   10,   45,   45,   10,
	   13,   32,   10,   46,   46,    9,   32,    9,   10,   13,   32,   10,
	   13,   32,   45,   95,   48,   57,   65,   90,   97,  122,   32,    9,
	   32,    9,   10,   13,   32,   10,   13,   32,   32,    0
	};
}

private static final char _TokenScanner_trans_keys[] = init__TokenScanner_trans_keys_0();


private static byte[] init__TokenScanner_single_lengths_0()
{
	return new byte [] {
	    0,   15,    1,    1,    2,    3,    1,    5,    9,    1,    1,    1,
	    1,    3,    1,    1,    1,    0,    2,    4,    3,    2,    1,    0,
	    2,    4,    3,    1
	};
}

private static final byte _TokenScanner_single_lengths[] = init__TokenScanner_single_lengths_0();


private static byte[] init__TokenScanner_range_lengths_0()
{
	return new byte [] {
	    0,    4,    0,    0,    3,    0,    0,    1,    1,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,    0,    0,
	    0,    0,    0,    0
	};
}

private static final byte _TokenScanner_range_lengths[] = init__TokenScanner_range_lengths_0();


private static short[] init__TokenScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,   20,   22,   24,   30,   34,   36,   43,   54,   56,   58,
	   60,   62,   66,   68,   70,   72,   73,   76,   81,   85,   91,   93,
	   94,   97,  102,  106
	};
}

private static final short _TokenScanner_index_offsets[] = init__TokenScanner_index_offsets_0();


private static byte[] init__TokenScanner_trans_targs_0()
{
	return new byte [] {
	   17,   18,   19,    3,   18,    4,   17,    4,   17,    5,    7,    5,
	   17,   17,    7,   17,    5,   17,   17,    0,   20,    0,   19,    0,
	   21,   21,   21,   21,   21,    0,   17,    6,   22,    0,   17,    0,
	   17,    6,   22,    7,    7,    7,    0,   23,   24,   25,   10,   24,
	   23,   11,   15,   23,   23,    0,   26,    0,   25,    0,   12,    0,
	   13,    0,   23,   14,   27,    0,   23,    0,   16,    0,   13,    0,
	    0,   18,   18,    0,   19,   20,    2,   20,    0,   20,    2,   20,
	    0,   21,   21,   21,   21,   21,    0,   22,    0,    0,   24,   24,
	    0,   25,   26,    9,   26,    0,   26,    9,   26,    0,   27,    0,
	    0
	};
}

private static final byte _TokenScanner_trans_targs[] = init__TokenScanner_trans_targs_0();


static final int TokenScanner_start = 1;
static final int TokenScanner_error = 0;

static final int TokenScanner_en_Header = 8;
static final int TokenScanner_en_Document = 1;

// line 61 "src/main/org/yecht/TokenScanner.rl"
}
