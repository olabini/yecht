// line 1 "src/main/org/yecht/ImplicitScanner.rl"
package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner {
// line 79 "src/main/org/yecht/ImplicitScanner.rl"



// line 11 "src/main/org/yecht/ImplicitScanner.java"
private static byte[] init__ImplicitScanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   14,    1,   15,    1,   17,    1,
	   18,    1,   19,    2,    6,   19,    2,   14,   16
	};
}

private static final byte _ImplicitScanner_actions[] = init__ImplicitScanner_actions_0();


private static short[] init__ImplicitScanner_key_offsets_0()
{
	return new short [] {
	    0,    0,   14,   16,   18,   20,   22,   24,   26,   28,   30,   32,
	   34,   35,   43,   54,   61,   73,   85,   97,  104,  114,  116,  118,
	  120,  122,  124,  126,  128,  130,  132,  134,  135,  143,  154,  163,
	  175,  187,  199,  211,  223,  235,  247,  251,  253,  255,  256,  257,
	  258,  263,  265,  267,  272,  276,  283,  287,  289,  291,  292,  293,
	  294,  298,  300,  301,  302,  303,  305,  307,  308,  310,  312,  316,
	  318,  319,  321,  323,  324,  326,  328,  331,  336,  338,  340,  342,
	  344,  348,  350,  352,  353,  355,  357,  358,  360,  362,  366,  368,
	  370,  372,  374,  380,  386,  388,  390,  391,  393,  395,  396,  398,
	  400,  404,  409,  415,  420,  425,  426,  428,  429,  430,  431,  432,
	  433,  434,  438,  439,  440,  441,  442,  446,  447,  448,  450,  451,
	  452,  453,  454,  456,  457,  458,  459,  461,  463,  464,  465,  465,
	  476,  499,  499,  499,  507,  514,  520,  523,  525,  529,  532,  534,
	  541,  546,  546,  546,  554,  561,  568,  576,  580,  581,  581,  582,
	  582,  582,  587,  592,  597,  603,  608,  608,  608,  608,  608
	};
}

private static final short _ImplicitScanner_key_offsets[] = init__ImplicitScanner_key_offsets_0();


private static char[] init__ImplicitScanner_trans_keys_0()
{
	return new char [] {
	    0,   33,   55,   95,  116,  120,   48,   51,   52,   57,   65,   90,
	   97,  122,   48,   57,   48,   57,   48,   57,   48,   57,   45,   47,
	   48,   57,   48,   57,   45,   47,   48,   57,   48,   57,   47,   45,
	   95,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	   48,   57,   65,   90,   97,  122,   95,   48,   57,   65,   90,   97,
	  122,   44,   45,   46,   47,   95,   97,   48,   57,   65,   90,   98,
	  122,   44,   45,   46,   47,   95,  103,   48,   57,   65,   90,   97,
	  122,   44,   45,   46,   47,   58,   95,   48,   57,   65,   90,   97,
	  122,   95,   48,   57,   65,   90,   97,  122,   44,   45,   46,   95,
	   48,   57,   65,   90,   97,  122,   48,   57,   48,   57,   48,   57,
	   48,   57,   45,   58,   48,   57,   48,   57,   45,   58,   48,   57,
	   48,   57,   58,   45,   95,   48,   57,   65,   90,   97,  122,   44,
	   45,   46,   47,   95,   48,   57,   65,   90,   97,  122,   45,   95,
	  112,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	  114,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	  105,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	  118,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	   97,   48,   57,   65,   90,   98,  122,   44,   45,   46,   47,   95,
	  116,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   95,
	  101,   48,   57,   65,   90,   97,  122,   44,   45,   46,   47,   58,
	   95,   48,   57,   65,   90,   97,  122,   46,   48,   49,   57,   73,
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
	   83,  115,   97,  111,  117,  102,  110,  114,  101,   44,   45,   46,
	   47,   95,   48,   57,   65,   90,   97,  122,    0,   43,   45,   46,
	   48,   55,   60,   61,   70,   78,   79,   84,   89,  102,  110,  111,
	  116,  121,  126,   49,   51,   52,   57,   44,   46,   58,  120,   48,
	   55,   56,   57,   44,   46,   58,   48,   55,   56,   57,   44,   46,
	   69,  101,   48,   57,   44,   48,   57,   48,   57,   46,   58,   48,
	   57,   44,   48,   57,   46,   58,   44,   48,   57,   65,   70,   97,
	  102,   44,   46,   58,   48,   57,   44,   46,   58,  120,   48,   55,
	   56,   57,   44,   46,   58,   48,   55,   56,   57,   44,   46,   58,
	   48,   55,   56,   57,   44,   45,   46,   58,   48,   55,   56,   57,
	    9,   32,   84,  116,   58,   58,   44,   46,   58,   48,   57,   44,
	   46,   58,   48,   57,   44,   46,   58,   48,   57,   44,   45,   46,
	   58,   48,   57,   44,   46,   58,   48,   57,    0
	};
}

private static final char _ImplicitScanner_trans_keys[] = init__ImplicitScanner_trans_keys_0();


private static byte[] init__ImplicitScanner_single_lengths_0()
{
	return new byte [] {
	    0,    6,    0,    0,    0,    0,    2,    0,    0,    2,    0,    0,
	    1,    2,    5,    1,    6,    6,    6,    1,    4,    0,    0,    0,
	    0,    2,    0,    0,    2,    0,    0,    1,    2,    5,    3,    6,
	    6,    6,    6,    6,    6,    6,    2,    2,    2,    1,    1,    1,
	    3,    2,    0,    3,    0,    1,    2,    2,    2,    1,    1,    1,
	    4,    2,    1,    1,    1,    0,    0,    1,    0,    0,    2,    0,
	    1,    0,    0,    1,    0,    0,    3,    5,    0,    0,    0,    0,
	    2,    0,    0,    1,    0,    0,    1,    0,    0,    4,    0,    0,
	    0,    0,    4,    4,    0,    0,    1,    0,    0,    1,    0,    0,
	    4,    3,    4,    3,    3,    1,    2,    1,    1,    1,    1,    1,
	    1,    4,    1,    1,    1,    1,    4,    1,    1,    2,    1,    1,
	    1,    1,    2,    1,    1,    1,    2,    2,    1,    1,    0,    5,
	   19,    0,    0,    4,    3,    4,    1,    0,    2,    1,    2,    1,
	    3,    0,    0,    4,    3,    3,    4,    4,    1,    0,    1,    0,
	    0,    3,    3,    3,    4,    3,    0,    0,    0,    0,    0
	};
}

private static final byte _ImplicitScanner_single_lengths[] = init__ImplicitScanner_single_lengths_0();


private static byte[] init__ImplicitScanner_range_lengths_0()
{
	return new byte [] {
	    0,    4,    1,    1,    1,    1,    0,    1,    1,    0,    1,    1,
	    0,    3,    3,    3,    3,    3,    3,    3,    3,    1,    1,    1,
	    1,    0,    1,    1,    0,    1,    1,    0,    3,    3,    3,    3,
	    3,    3,    3,    3,    3,    3,    1,    0,    0,    0,    0,    0,
	    1,    0,    1,    1,    2,    3,    1,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    1,    1,    0,    1,    1,    1,    1,
	    0,    1,    1,    0,    1,    1,    0,    0,    1,    1,    1,    1,
	    1,    1,    1,    0,    1,    1,    0,    1,    1,    0,    1,    1,
	    1,    1,    1,    1,    1,    1,    0,    1,    1,    0,    1,    1,
	    0,    1,    1,    1,    1,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    3,
	    2,    0,    0,    2,    2,    1,    1,    1,    1,    1,    0,    3,
	    1,    0,    0,    2,    2,    2,    2,    0,    0,    0,    0,    0,
	    0,    1,    1,    1,    1,    1,    0,    0,    0,    0,    0
	};
}

private static final byte _ImplicitScanner_range_lengths[] = init__ImplicitScanner_range_lengths_0();


private static short[] init__ImplicitScanner_index_offsets_0()
{
	return new short [] {
	    0,    0,   11,   13,   15,   17,   19,   22,   24,   26,   29,   31,
	   33,   35,   41,   50,   55,   65,   75,   85,   90,   98,  100,  102,
	  104,  106,  109,  111,  113,  116,  118,  120,  122,  128,  137,  144,
	  154,  164,  174,  184,  194,  204,  214,  218,  221,  224,  226,  228,
	  230,  235,  238,  240,  245,  248,  253,  257,  260,  263,  265,  267,
	  269,  274,  277,  279,  281,  283,  285,  287,  289,  291,  293,  297,
	  299,  301,  303,  305,  307,  309,  311,  315,  321,  323,  325,  327,
	  329,  333,  335,  337,  339,  341,  343,  345,  347,  349,  354,  356,
	  358,  360,  362,  368,  374,  376,  378,  380,  382,  384,  386,  388,
	  390,  395,  400,  406,  411,  416,  418,  421,  423,  425,  427,  429,
	  431,  433,  438,  440,  442,  444,  446,  451,  453,  455,  458,  460,
	  462,  464,  466,  469,  471,  473,  475,  478,  481,  483,  485,  486,
	  495,  517,  518,  519,  526,  532,  538,  541,  543,  547,  550,  553,
	  558,  563,  564,  565,  572,  578,  584,  591,  596,  598,  599,  601,
	  602,  603,  608,  613,  618,  624,  629,  630,  631,  632,  633
	};
}

private static final short _ImplicitScanner_index_offsets[] = init__ImplicitScanner_index_offsets_0();


private static short[] init__ImplicitScanner_indicies_0()
{
	return new short [] {
	    0,    0,    2,    3,    4,    5,    2,    3,    3,    3,    1,    6,
	    1,    7,    1,    8,    1,    9,    1,   10,    0,    1,   11,    1,
	   12,    1,   13,    0,    1,   14,    1,   15,    1,    0,    1,   16,
	    3,    3,    3,    3,    1,   17,   16,   18,    0,    3,    3,    3,
	    3,    1,    3,    3,    3,    3,    1,   17,   16,   18,    0,    3,
	   19,    3,    3,    3,    1,   17,   16,   18,    0,    3,   20,    3,
	    3,    3,    1,   17,   16,   18,    0,   21,    3,    3,    3,    3,
	    1,   22,   22,   22,   22,    1,   23,   24,   21,   22,   22,   22,
	   22,    1,   25,    1,   26,    1,   27,    1,   28,    1,   29,    0,
	    1,   30,    1,   31,    1,   32,    0,    1,   33,    1,   34,    1,
	    0,    1,   24,   22,   22,   22,   22,    1,   17,   35,   18,    0,
	    3,    3,    3,    3,    1,   16,    3,   36,    3,    3,    3,    1,
	   17,   16,   18,    0,    3,   37,    3,    3,    3,    1,   17,   16,
	   18,    0,    3,   38,    3,    3,    3,    1,   17,   16,   18,    0,
	    3,   39,    3,    3,    3,    1,   17,   16,   18,    0,    3,   40,
	    3,    3,    3,    1,   17,   16,   18,    0,    3,   41,    3,    3,
	    3,    1,   17,   16,   18,    0,    3,   42,    3,    3,    3,    1,
	   17,   16,   18,    0,    0,    3,    3,    3,    3,    1,   43,   44,
	   45,    1,   46,   47,    1,   48,   49,    1,   50,    1,   50,    1,
	   49,    1,   51,   52,   52,   51,    1,   53,   53,    1,   54,    1,
	   55,   56,   57,   55,    1,   58,   59,    1,   60,   60,   60,   60,
	    1,   61,   44,   45,    1,   62,   63,    1,   64,   65,    1,   66,
	    1,   66,    1,   65,    1,   46,   67,   47,   68,    1,   69,   69,
	    1,   70,    1,   71,    1,   70,    1,   72,    1,   73,    1,   74,
	    1,   75,    1,   76,    1,   77,   77,   78,    1,   79,    1,   80,
	    1,   81,    1,   82,    1,   83,    1,   84,    1,   85,    1,   86,
	   86,   87,    1,   86,   86,   88,   88,   89,    1,   90,    1,   91,
	    1,   92,    1,   89,    1,   86,   86,   87,    1,   93,    1,   94,
	    1,   95,    1,   96,    1,   97,    1,   98,    1,   99,    1,  100,
	    1,  101,  101,  102,  103,    1,  104,    1,  105,    1,  106,    1,
	  107,    1,  101,  101,  102,  107,  108,    1,  101,  101,  102,  103,
	  108,    1,  109,    1,  110,    1,  111,    1,  112,    1,  113,    1,
	  114,    1,  115,    1,  116,    1,  101,  101,  117,  107,    1,  101,
	  101,  107,  117,    1,   55,  118,   56,   57,   55,    1,   55,   56,
	   57,  119,    1,   55,   56,   57,  120,    1,  121,    1,  122,  123,
	    1,  124,    1,  125,    1,  126,    1,  127,    1,  128,    1,  126,
	    1,  126,  129,  126,  130,    1,  131,    1,  132,    1,  133,    1,
	  132,    1,  134,  135,  136,  135,    1,  126,    1,  126,    1,  137,
	  138,    1,  139,    1,  135,    1,  140,    1,  135,    1,  141,  142,
	    1,  135,    1,  135,    1,  123,    1,  126,  130,    1,  136,  135,
	    1,  138,    1,  142,    1,    1,   17,   16,   18,    0,    3,    3,
	    3,    3,    1,  143,  144,  145,  146,  147,  148,  150,  151,  152,
	  153,  154,  155,  156,  157,  158,  159,  160,  161,  132,  148,  149,
	    1,    1,    1,  162,   56,   57,  163,  162,   55,    1,  162,   56,
	   57,  162,   55,    1,  164,   51,   52,   52,   56,    1,  164,  164,
	    1,   54,    1,  165,   57,   59,    1,  165,  165,    1,  165,   57,
	    1,   60,   60,   60,   60,    1,   45,   56,   57,   45,    1,    1,
	    1,  162,   56,   57,  163,  166,  167,    1,  162,   56,   57,  168,
	  120,    1,  162,   56,   57,  169,  119,    1,  162,  118,   56,   57,
	  162,   55,    1,   77,   77,  170,  171,    1,  172,    1,    1,  173,
	    1,    1,    1,   45,   56,   57,  174,    1,   45,   56,   57,  175,
	    1,   45,   56,   57,  176,    1,   45,  118,   56,   57,   45,    1,
	   45,   56,   57,  174,    1,    1,    1,    1,    1,    1,    0
	};
}

private static final short _ImplicitScanner_indicies[] = init__ImplicitScanner_indicies_0();


private static short[] init__ImplicitScanner_trans_targs_0()
{
	return new short [] {
	  142,    0,  143,   14,   16,   33,    3,    4,    5,    6,    7,    8,
	    9,   10,   11,   12,   13,    2,   15,   17,   18,   19,   20,   21,
	   32,   22,   23,   24,   25,   26,   27,   28,   29,   30,   31,   34,
	   35,   36,   37,   38,   39,   40,   41,   43,  147,  156,   44,   47,
	   45,   46,  146,   48,   49,   50,  151,   51,  149,   52,  152,  154,
	  155,   55,   56,   59,   57,   58,  157,   61,   63,   62,  158,   64,
	   66,   67,   68,   69,  163,   70,   71,   72,   73,   74,   75,   76,
	   77,   78,   79,   84,   80,  165,   81,  164,   83,   86,   87,   88,
	   89,   90,   91,   92,   93,   94,   98,  168,   95,  166,   97,  167,
	   99,  101,  102,  103,  104,  105,  106,  107,  108,  109,   65,  110,
	  111,  174,  115,  118,  116,  117,  176,  119,  120,  122,  124,  123,
	  177,  125,  127,  178,  128,  130,  132,  131,  133,  135,  136,  145,
	   42,   54,   60,  159,  169,  173,  113,  175,  114,  121,  126,  129,
	  134,  137,  138,  139,  140,  141,  148,   53,  150,  153,  160,  112,
	  161,  162,   85,  100,   82,   96,  170,  171,  172
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    1,   37,   21,   13,    9,   15,   15,   17,   11,   19,   11,    7,
	   13,   23,   25,   39,    9,    9,    9,   27,   31,   31,   29,   29,
	   42,   39,   13,   13,   13,   13,   35,   33,    5,    1,    3
	};
}

private static final byte _ImplicitScanner_eof_actions[] = init__ImplicitScanner_eof_actions_0();


static final int ImplicitScanner_start = 1;
static final int ImplicitScanner_error = 0;

static final int ImplicitScanner_en_Implicit = 144;
static final int ImplicitScanner_en_TypeId = 1;

// line 82 "src/main/org/yecht/ImplicitScanner.rl"

   public String recognize(byte[] data, int start, int len) {
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+len;
       int tokstart = -1;
       int tokend = -1;
       int eof = pe;
       String tag = "str";
              
       cs = ImplicitScanner_en_Implicit;       


// line 338 "src/main/org/yecht/ImplicitScanner.java"
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
// line 41 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "null"; }
	break;
	case 1:
// line 42 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#yes"; }
	break;
	case 2:
// line 43 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#no"; }
	break;
	case 3:
// line 44 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#hex"; }
	break;
	case 4:
// line 45 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#oct"; }
	break;
	case 5:
// line 46 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#base60"; }
	break;
	case 6:
// line 47 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int"; }
	break;
	case 7:
// line 48 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#fix"; }
	break;
	case 8:
// line 49 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#exp"; }
	break;
	case 9:
// line 50 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#base60"; }
	break;
	case 10:
// line 51 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#inf"; }
	break;
	case 11:
// line 52 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#neginf"; }
	break;
	case 12:
// line 53 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#nan"; }
	break;
	case 13:
// line 54 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#ymd"; }
	break;
	case 14:
// line 55 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#iso8601"; }
	break;
	case 15:
// line 56 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#spaced"; }
	break;
	case 16:
// line 57 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp"; }
	break;
	case 17:
// line 58 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "default"; }
	break;
	case 18:
// line 59 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "merge"; }
	break;
	case 19:
// line 60 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "str"; }
	break;
// line 505 "src/main/org/yecht/ImplicitScanner.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 98 "src/main/org/yecht/ImplicitScanner.rl"

       return tag;
   }
}
