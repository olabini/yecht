// line 1 "src/main/org/yecht/ImplicitScanner.rl"
package org.yecht;

// Equivalent to implicit.re
public class ImplicitScanner {

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

// line 113 "src/main/org/yecht/ImplicitScanner.rl"



// line 37 "src/main/org/yecht/ImplicitScanner.java"
private static byte[] init__ImplicitScanner_actions_0()
{
	return new byte [] {
	    0,    1,    0,    1,    1,    1,    2,    1,    3,    1,    4,    1,
	    5,    1,    6,    1,    7,    1,    8,    1,    9,    1,   10,    1,
	   11,    1,   12,    1,   13,    1,   14,    1,   15,    1,   17,    1,
	   18,    1,   19,    1,   20,    1,   21,    1,   22,    1,   23,    1,
	   24,    1,   25,    2,    6,   19,    2,   14,   16
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
	  465,  476,  476,  476,  476,  476,  499,  499,  499,  507,  514,  520,
	  523,  525,  529,  532,  534,  541,  546,  546,  546,  554,  561,  568,
	  576,  580,  581,  581,  582,  582,  582,  587,  592,  597,  603,  608,
	  608,  608,  608,  608
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
	    1,    1,    2,    1,    1,    1,    2,    2,    1,    1,    0,    0,
	    5,    0,    0,    0,    0,   19,    0,    0,    4,    3,    4,    1,
	    0,    2,    1,    2,    1,    3,    0,    0,    4,    3,    3,    4,
	    4,    1,    0,    1,    0,    0,    3,    3,    3,    4,    3,    0,
	    0,    0,    0,    0
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    3,    0,    0,    0,    0,    2,    0,    0,    2,    2,    1,    1,
	    1,    1,    1,    0,    3,    1,    0,    0,    2,    2,    2,    2,
	    0,    0,    0,    0,    0,    0,    1,    1,    1,    1,    1,    0,
	    0,    0,    0,    0
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
	  487,  496,  497,  498,  499,  500,  522,  523,  524,  531,  537,  543,
	  546,  548,  552,  555,  558,  563,  568,  569,  570,  577,  583,  589,
	  596,  601,  603,  604,  606,  607,  608,  613,  618,  623,  629,  634,
	  635,  636,  637,  638
	};
}

private static final short _ImplicitScanner_index_offsets[] = init__ImplicitScanner_index_offsets_0();


private static short[] init__ImplicitScanner_indicies_0()
{
	return new short [] {
	    0,    1,    3,    4,    5,    6,    3,    4,    4,    4,    2,    7,
	    2,    8,    2,    9,    2,   10,    2,   11,   12,    2,   13,    2,
	   14,    2,   15,   12,    2,   16,    2,   17,    2,   12,    2,   18,
	    4,    4,    4,    4,    2,   19,   18,   20,   21,    4,    4,    4,
	    4,    2,    4,    4,    4,    4,    2,   19,   18,   20,   21,    4,
	   22,    4,    4,    4,    2,   19,   18,   20,   21,    4,   23,    4,
	    4,    4,    2,   19,   18,   20,   21,   24,    4,    4,    4,    4,
	    2,   25,   25,   25,   25,    2,   26,   27,   24,   25,   25,   25,
	   25,    2,   28,    2,   29,    2,   30,    2,   31,    2,   32,   33,
	    2,   34,    2,   35,    2,   36,   33,    2,   37,    2,   38,    2,
	   33,    2,   27,   25,   25,   25,   25,    2,   19,   39,   20,   21,
	    4,    4,    4,    4,    2,   18,    4,   40,    4,    4,    4,    2,
	   19,   18,   20,   21,    4,   41,    4,    4,    4,    2,   19,   18,
	   20,   21,    4,   42,    4,    4,    4,    2,   19,   18,   20,   21,
	    4,   43,    4,    4,    4,    2,   19,   18,   20,   21,    4,   44,
	    4,    4,    4,    2,   19,   18,   20,   21,    4,   45,    4,    4,
	    4,    2,   19,   18,   20,   21,    4,   46,    4,    4,    4,    2,
	   19,   18,   20,   21,   47,    4,    4,    4,    4,    2,   48,   49,
	   50,    2,   51,   52,    2,   53,   54,    2,   55,    2,   55,    2,
	   54,    2,   56,   57,   57,   56,    2,   58,   58,    2,   59,    2,
	   60,   61,   62,   60,    2,   63,   64,    2,   65,   65,   65,   65,
	    2,   66,   49,   50,    2,   67,   68,    2,   69,   70,    2,   71,
	    2,   71,    2,   70,    2,   51,   72,   52,   73,    2,   74,   74,
	    2,   75,    2,   76,    2,   75,    2,   77,    2,   78,    2,   79,
	    2,   80,    2,   81,    2,   82,   82,   83,    2,   84,    2,   85,
	    2,   86,    2,   87,    2,   88,    2,   89,    2,   90,    2,   91,
	   91,   92,    2,   91,   91,   93,   93,   94,    2,   95,    2,   96,
	    2,   97,    2,   94,    2,   91,   91,   92,    2,   98,    2,   99,
	    2,  100,    2,  101,    2,  102,    2,  103,    2,  104,    2,  105,
	    2,  106,  106,  107,  108,    2,  109,    2,  110,    2,  111,    2,
	  112,    2,  106,  106,  107,  112,  113,    2,  106,  106,  107,  108,
	  113,    2,  114,    2,  115,    2,  116,    2,  117,    2,  118,    2,
	  119,    2,  120,    2,  121,    2,  106,  106,  122,  112,    2,  106,
	  106,  112,  122,    2,   60,  123,   61,   62,   60,    2,   60,   61,
	   62,  124,    2,   60,   61,   62,  125,    2,  126,    2,  127,  128,
	    2,  129,    2,  130,    2,  131,    2,  132,    2,  133,    2,  131,
	    2,  131,  134,  131,  135,    2,  136,    2,  137,    2,  138,    2,
	  137,    2,  139,  140,  141,  140,    2,  131,    2,  131,    2,  142,
	  143,    2,  144,    2,  140,    2,  145,    2,  140,    2,  146,  147,
	    2,  140,    2,  140,    2,  128,    2,  131,  135,    2,  141,  140,
	    2,  143,    2,  147,    2,    2,    2,   19,   18,   20,   21,    4,
	    4,    4,    4,    2,    2,    2,    2,    2,  148,  149,  150,  151,
	  152,  153,  155,  156,  157,  158,  159,  160,  161,  162,  163,  164,
	  165,  166,  137,  153,  154,    2,    2,    2,  167,   61,   62,  168,
	  167,   60,    2,  167,   61,   62,  167,   60,    2,  169,   56,   57,
	   57,   61,    2,  169,  169,    2,   59,    2,  170,   62,   64,    2,
	  170,  170,    2,  170,   62,    2,   65,   65,   65,   65,    2,   50,
	   61,   62,   50,    2,    2,    2,  167,   61,   62,  168,  171,  172,
	    2,  167,   61,   62,  173,  125,    2,  167,   61,   62,  174,  124,
	    2,  167,  123,   61,   62,  167,   60,    2,   82,   82,  175,  176,
	    2,  177,    2,    2,  178,    2,    2,    2,   50,   61,   62,  179,
	    2,   50,   61,   62,  180,    2,   50,   61,   62,  181,    2,   50,
	  123,   61,   62,   50,    2,   50,   61,   62,  179,    2,    2,    2,
	    2,    2,    2,    0
	};
}

private static final short _ImplicitScanner_indicies[] = init__ImplicitScanner_indicies_0();


private static short[] init__ImplicitScanner_trans_targs_0()
{
	return new short [] {
	  142,  143,    0,  144,   14,   16,   33,    3,    4,    5,    6,    7,
	  145,    8,    9,   10,   11,   12,   13,    2,   15,  146,   17,   18,
	   19,   20,   21,   32,   22,   23,   24,   25,   26,  147,   27,   28,
	   29,   30,   31,   34,   35,   36,   37,   38,   39,   40,   41,  148,
	   43,  152,  161,   44,   47,   45,   46,  151,   48,   49,   50,  156,
	   51,  154,   52,  157,  159,  160,   55,   56,   59,   57,   58,  162,
	   61,   63,   62,  163,   64,   66,   67,   68,   69,  168,   70,   71,
	   72,   73,   74,   75,   76,   77,   78,   79,   84,   80,  170,   81,
	  169,   83,   86,   87,   88,   89,   90,   91,   92,   93,   94,   98,
	  173,   95,  171,   97,  172,   99,  101,  102,  103,  104,  105,  106,
	  107,  108,  109,   65,  110,  111,  179,  115,  118,  116,  117,  181,
	  119,  120,  122,  124,  123,  182,  125,  127,  183,  128,  130,  132,
	  131,  133,  135,  136,  150,   42,   54,   60,  164,  174,  178,  113,
	  180,  114,  121,  126,  129,  134,  137,  138,  139,  140,  141,  153,
	   53,  155,  158,  165,  112,  166,  167,   85,  100,   82,   96,  175,
	  176,  177
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
	    0,    0
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
	    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,   49,   43,
	   49,   47,   45,   39,   41,    1,   37,   21,   13,    9,   15,   15,
	   17,   11,   19,   11,    7,   13,   23,   25,   51,    9,    9,    9,
	   27,   31,   31,   29,   29,   54,   51,   13,   13,   13,   13,   35,
	   33,    5,    1,    3
	};
}

private static final byte _ImplicitScanner_eof_actions[] = init__ImplicitScanner_eof_actions_0();


static final int ImplicitScanner_start = 1;
static final int ImplicitScanner_error = 0;

static final int ImplicitScanner_en_Implicit = 149;
static final int ImplicitScanner_en_TypeId = 1;

// line 116 "src/main/org/yecht/ImplicitScanner.rl"
   // syck_match_implicit
   public static String matchImplicit(Pointer ptr, int len) {
       String type_id = null; // unused
       byte[] data = ptr.buffer;
       int start = ptr.start;
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


// line 376 "src/main/org/yecht/ImplicitScanner.java"
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
// line 67 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "null"; }
	break;
	case 1:
// line 68 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#yes"; }
	break;
	case 2:
// line 69 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#no"; }
	break;
	case 3:
// line 70 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#hex"; }
	break;
	case 4:
// line 71 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#oct"; }
	break;
	case 5:
// line 72 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#base60"; }
	break;
	case 6:
// line 73 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int"; }
	break;
	case 7:
// line 74 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#fix"; }
	break;
	case 8:
// line 75 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#exp"; }
	break;
	case 9:
// line 76 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#base60"; }
	break;
	case 10:
// line 77 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#inf"; }
	break;
	case 11:
// line 78 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#neginf"; }
	break;
	case 12:
// line 79 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#nan"; }
	break;
	case 13:
// line 80 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#ymd"; }
	break;
	case 14:
// line 81 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#iso8601"; }
	break;
	case 15:
// line 82 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#spaced"; }
	break;
	case 16:
// line 83 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp"; }
	break;
	case 17:
// line 84 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "default"; }
	break;
	case 18:
// line 85 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "merge"; }
	break;
	case 19:
// line 86 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "str"; }
	break;
	case 20:
// line 98 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = type_id; }
	break;
	case 21:
// line 99 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = type_id; }
	break;
	case 22:
// line 100 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = Parser.xprivate(type_id.substring(1)); }
	break;
	case 23:
// line 101 "src/main/org/yecht/ImplicitScanner.rl"
	{   
                    String domain = type_id.substring(0, p-1) + "." + YAML.DOMAIN;
                    String uri = Parser.taguri( domain, type_id.substring(p));
                    tag = uri;
                }
	break;
	case 24:
// line 106 "src/main/org/yecht/ImplicitScanner.rl"
	{   
                               String domain = type_id.substring(0, p-1);
                               String uri = Parser.taguri(domain, type_id.substring(p));
                               tag = uri;
                            }
	break;
	case 25:
// line 111 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = Parser.taguri(YAML.DOMAIN, type_id); }
	break;
// line 575 "src/main/org/yecht/ImplicitScanner.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 135 "src/main/org/yecht/ImplicitScanner.rl"

       return tag;
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

   // syck_type_id_to_uri
   public static String typeIdToUri(String type_id) {
       byte[] data = null;
       try {
         data = type_id.getBytes("ISO8859-1");
       } catch(Exception e) {}

       int start = 0;
       int cs;
       int act;
       int have = 0;
       int nread = 0;
       int p=start;
       int pe = p+data.length;
       int tokstart = -1;
       int tokend = -1;
       int eof = pe;
       String tag = null;
              
       cs = ImplicitScanner_en_TypeId;       


// line 623 "src/main/org/yecht/ImplicitScanner.java"
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
// line 67 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "null"; }
	break;
	case 1:
// line 68 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#yes"; }
	break;
	case 2:
// line 69 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "bool#no"; }
	break;
	case 3:
// line 70 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#hex"; }
	break;
	case 4:
// line 71 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#oct"; }
	break;
	case 5:
// line 72 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int#base60"; }
	break;
	case 6:
// line 73 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "int"; }
	break;
	case 7:
// line 74 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#fix"; }
	break;
	case 8:
// line 75 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#exp"; }
	break;
	case 9:
// line 76 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#base60"; }
	break;
	case 10:
// line 77 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#inf"; }
	break;
	case 11:
// line 78 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#neginf"; }
	break;
	case 12:
// line 79 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "float#nan"; }
	break;
	case 13:
// line 80 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#ymd"; }
	break;
	case 14:
// line 81 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#iso8601"; }
	break;
	case 15:
// line 82 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp#spaced"; }
	break;
	case 16:
// line 83 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "timestamp"; }
	break;
	case 17:
// line 84 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "default"; }
	break;
	case 18:
// line 85 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "merge"; }
	break;
	case 19:
// line 86 "src/main/org/yecht/ImplicitScanner.rl"
	{   tag = "str"; }
	break;
	case 20:
// line 98 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = type_id; }
	break;
	case 21:
// line 99 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = type_id; }
	break;
	case 22:
// line 100 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = Parser.xprivate(type_id.substring(1)); }
	break;
	case 23:
// line 101 "src/main/org/yecht/ImplicitScanner.rl"
	{   
                    String domain = type_id.substring(0, p-1) + "." + YAML.DOMAIN;
                    String uri = Parser.taguri( domain, type_id.substring(p));
                    tag = uri;
                }
	break;
	case 24:
// line 106 "src/main/org/yecht/ImplicitScanner.rl"
	{   
                               String domain = type_id.substring(0, p-1);
                               String uri = Parser.taguri(domain, type_id.substring(p));
                               tag = uri;
                            }
	break;
	case 25:
// line 111 "src/main/org/yecht/ImplicitScanner.rl"
	{ tag = Parser.taguri(YAML.DOMAIN, type_id); }
	break;
// line 822 "src/main/org/yecht/ImplicitScanner.java"
		}
	}
	}

case 5:
	}
	break; }
	}
// line 173 "src/main/org/yecht/ImplicitScanner.rl"

       return tag;
   }
}
