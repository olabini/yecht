// line 1 "src/main/org/yecht/BytecodeScanner.rl"
package org.yecht;

// Equivalent to bytecode.re
public class BytecodeScanner {
// line 74 "src/main/org/yecht/BytecodeScanner.rl"



// line 11 "src/main/org/yecht/BytecodeScanner.java"
private static byte[] init__BytecodeScanner_key_offsets_0()
{
	return new byte [] {
	    0,    0,    6,    7,   12,   14,   15,   28,   29,   31,   36,   45,
	   55,   64,   75,   76,   82,   83,   89,   90,   90,   90,   90,   90,
	   90,   90,   93
	};
}

private static final byte _BytecodeScanner_key_offsets[] = init__BytecodeScanner_key_offsets_0();


private static char[] init__BytecodeScanner_trans_keys_0()
{
	return new char [] {
	    0,   10,   13,   55,   48,   51,   10,    0,   55,   68,   48,   51,
	   10,   13,   10,    0,   10,   13,   48,   65,   77,   99,   68,   69,
	   80,   81,   82,   84,   10,   10,   13,    0,   55,   86,   48,   51,
	   95,   45,   46,   48,   57,   65,   90,   97,  122,   58,   95,   45,
	   46,   48,   57,   65,   90,   97,  122,   95,   45,   46,   48,   57,
	   65,   90,   97,  122,   10,   13,   95,   45,   46,   48,   57,   65,
	   90,   97,  122,   10,    0,   10,   13,   55,   48,   51,   10,    0,
	   10,   13,   55,   48,   51,   10,   67,   78,   90,   48,   57,    0
	};
}

private static final char _BytecodeScanner_trans_keys[] = init__BytecodeScanner_trans_keys_0();


private static byte[] init__BytecodeScanner_single_lengths_0()
{
	return new byte [] {
	    0,    4,    1,    3,    2,    1,    7,    1,    2,    3,    1,    2,
	    1,    3,    1,    4,    1,    4,    1,    0,    0,    0,    0,    0,
	    0,    3,    0
	};
}

private static final byte _BytecodeScanner_single_lengths[] = init__BytecodeScanner_single_lengths_0();


private static byte[] init__BytecodeScanner_range_lengths_0()
{
	return new byte [] {
	    0,    1,    0,    1,    0,    0,    3,    0,    0,    1,    4,    4,
	    4,    4,    0,    1,    0,    1,    0,    0,    0,    0,    0,    0,
	    0,    0,    1
	};
}

private static final byte _BytecodeScanner_range_lengths[] = init__BytecodeScanner_range_lengths_0();


private static byte[] init__BytecodeScanner_index_offsets_0()
{
	return new byte [] {
	    0,    0,    6,    8,   13,   16,   18,   29,   31,   34,   39,   45,
	   52,   58,   66,   68,   74,   76,   82,   84,   85,   86,   87,   88,
	   89,   90,   94
	};
}

private static final byte _BytecodeScanner_index_offsets[] = init__BytecodeScanner_index_offsets_0();


private static byte[] init__BytecodeScanner_trans_targs_0()
{
	return new byte [] {
	   19,   19,    2,   19,   19,    0,   19,    0,   20,   20,    4,   20,
	    0,   20,    5,    0,   20,    0,   21,   21,    7,   21,   21,    8,
	   21,    8,    8,   21,    0,   21,    0,   21,    7,    0,   22,   22,
	   10,   22,    0,   11,   11,   11,   11,   11,    0,   12,   11,   11,
	   11,   11,   11,    0,   13,   13,   13,   13,   13,    0,   22,   14,
	   13,   13,   13,   13,   13,    0,   22,    0,   23,   23,   16,   23,
	   23,    0,   23,    0,   24,   25,   18,   24,   24,    0,   25,    0,
	    0,    0,    0,    0,    0,    0,   24,   26,   24,    0,   26,    0,
	    0
	};
}

private static final byte _BytecodeScanner_trans_targs[] = init__BytecodeScanner_trans_targs_0();


static final int BytecodeScanner_start = 1;
static final int BytecodeScanner_error = 0;

static final int BytecodeScanner_en_Header = 3;
static final int BytecodeScanner_en_Document = 6;
static final int BytecodeScanner_en_Directive = 9;
static final int BytecodeScanner_en_Comment = 15;
static final int BytecodeScanner_en_Scalar = 17;
static final int BytecodeScanner_en_Inline = 1;

// line 77 "src/main/org/yecht/BytecodeScanner.rl"

   public void recognize(byte[] data, int start, int len) {
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+len;
       int tokstart = -1;
       int tokend = -1;
       
       cs = BytecodeScanner_en_Header;

// line 119 "src/main/org/yecht/BytecodeScanner.java"
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
	_keys = _BytecodeScanner_key_offsets[cs];
	_trans = _BytecodeScanner_index_offsets[cs];
	_klen = _BytecodeScanner_single_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + _klen - 1;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + ((_upper-_lower) >> 1);
			if ( data[p] < _BytecodeScanner_trans_keys[_mid] )
				_upper = _mid - 1;
			else if ( data[p] > _BytecodeScanner_trans_keys[_mid] )
				_lower = _mid + 1;
			else {
				_trans += (_mid - _keys);
				break _match;
			}
		}
		_keys += _klen;
		_trans += _klen;
	}

	_klen = _BytecodeScanner_range_lengths[cs];
	if ( _klen > 0 ) {
		int _lower = _keys;
		int _mid;
		int _upper = _keys + (_klen<<1) - 2;
		while (true) {
			if ( _upper < _lower )
				break;

			_mid = _lower + (((_upper-_lower) >> 1) & ~1);
			if ( data[p] < _BytecodeScanner_trans_keys[_mid] )
				_upper = _mid - 2;
			else if ( data[p] > _BytecodeScanner_trans_keys[_mid+1] )
				_lower = _mid + 2;
			else {
				_trans += ((_mid - _keys)>>1);
				break _match;
			}
		}
		_trans += _klen;
	}
	} while (false);

	cs = _BytecodeScanner_trans_targs[_trans];

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
// line 90 "src/main/org/yecht/BytecodeScanner.rl"
   }
}
