// created by jay 1.1.0 (c) 2002-2006 ats@cs.rit.edu
// skeleton Java 1.1.0 (c) 2002-2006 ats@cs.rit.edu

					// line 2 "src/main/org/yecht/DefaultYAMLParser.y"
package org.yecht;

public class DefaultYAMLParser {

					// line 10 "-"
  // %token constants
  public static final int YAML_ANCHOR = 257;
  public static final int YAML_ALIAS = 258;
  public static final int YAML_TRANSFER = 259;
  public static final int YAML_TAGURI = 260;
  public static final int YAML_ITRANSFER = 261;
  public static final int YAML_WORD = 262;
  public static final int YAML_PLAIN = 263;
  public static final int YAML_BLOCK = 264;
  public static final int YAML_DOCSEP = 265;
  public static final int YAML_IOPEN = 266;
  public static final int YAML_INDENT = 267;
  public static final int YAML_IEND = 268;
  public static final int yyErrorCode = 256;

  /** number of final state.
    */
  protected static final int yyFinal = 13;

  /** parser tables.
      Order is mandated by <i>jay</i>.
    */
  protected static final short[] yyLhs = {
//yyLhs 79
    -1,     0,     0,     0,     1,     1,     4,     4,     4,     4,
     4,     2,     2,     8,     8,     8,     8,     8,     8,     6,
     6,     9,    10,     7,     7,     3,     3,     3,     3,     3,
     3,     3,     3,     5,     5,     5,     5,     5,    11,    11,
    17,    15,    15,    15,    15,    15,    15,    16,    16,    16,
    12,    12,    18,    18,    19,    19,    13,    13,    21,    21,
    21,    21,    21,    21,    23,    23,    24,    25,    22,    22,
    22,    22,    20,    14,    14,    26,    26,    27,    27,
    }, yyLen = {
//yyLen 79
     2,     1,     2,     0,     1,     1,     1,     2,     2,     2,
     3,     1,     1,     3,     0,     2,     2,     2,     2,     1,
     2,     1,     1,     1,     2,     2,     2,     2,     2,     1,
     1,     1,     3,     1,     1,     1,     1,     1,     3,     3,
     2,     3,     2,     3,     2,     3,     2,     1,     3,     2,
     3,     2,     1,     3,     1,     1,     3,     3,     3,     2,
     3,     2,     3,     2,     1,     3,     1,     3,     1,     3,
     3,     2,     3,     3,     2,     1,     3,     1,     1,
    }, yyDefRed = {
//yyDefRed 127
     0,     0,    29,     0,     0,     0,    30,    31,    33,     0,
    19,     0,     0,     0,     1,     4,     5,     6,     0,    34,
    35,    36,    37,    28,     9,    25,     7,    26,     8,     0,
     0,     0,    27,     0,     0,     0,     0,     0,    11,     2,
     0,    12,    51,     0,     0,    52,    55,    74,     0,    78,
     0,    75,     0,     0,     0,    20,     0,     0,     0,     0,
     0,     0,    47,     0,     0,     0,    68,     0,    18,    16,
    17,     0,     0,     0,     0,    15,     0,     0,     0,     0,
     0,    50,     0,    73,     0,    22,     0,    46,    63,     0,
    42,    59,     0,    44,    61,    40,     0,    23,    32,     0,
    10,    21,    38,    39,     0,    56,    57,     0,     0,    13,
    72,    53,    76,    64,     0,     0,     0,     0,     0,     0,
    65,    24,    48,    69,    70,    66,    67,
    }, yyDgoto = {
//yyDgoto 28
    13,    38,    39,    15,    16,    17,    18,    98,    41,   102,
    99,    19,    20,    21,    22,    60,    61,    62,    44,    45,
    46,    63,    64,    65,   126,    66,    50,    51,
    }, yySindex = {
//yySindex 127
   -65,    -1,     0,    -1,    -1,   170,     0,     0,     0,    13,
     0,   -55,   -12,     0,     0,     0,     0,     0,   -45,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,   170,
   170,   170,     0,  -128,    13,    13,    13,   180,     0,     0,
   -34,     0,     0,   -48,   -38,     0,     0,     0,   -48,     0,
   -40,     0,   -89,   -89,   -89,     0,    13,    -1,  -226,  -226,
  -249,  -224,     0,  -249,  -224,   -35,     0,  -226,     0,     0,
     0,   180,   180,   180,   159,     0,   -76,   -76,   -76,  -249,
    13,     0,    -1,     0,    -1,     0,   -23,     0,     0,   -23,
     0,     0,   -23,     0,     0,     0,  -234,     0,     0,  -226,
     0,     0,     0,     0,   -10,     0,     0,   -23,    13,     0,
     0,     0,     0,     0,  -234,  -234,  -234,  -234,  -234,  -234,
     0,     0,     0,     0,     0,     0,     0,
    }, yyRindex = {
//yyRindex 127
    37,     0,     0,     0,     0,     0,     0,     0,     0,    39,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     1,     1,     1,     1,     0,     0,
  -220,     0,     0,   -32,     0,     0,     0,     0,   -39,     0,
     0,     0,     0,     0,     0,     0,  -205,     0,    -2,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     1,     1,     1,  -220,     0,  -220,  -220,  -220,     0,
   -41,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,     0,     0,     0,     0,     0,     0,
     0,     0,     0,     0,  -196,     0,     0,  -185,  -205,     0,
     0,     0,     0,     0,  -214,  -208,  -203,  -199,  -192,  -181,
     0,     0,     0,     0,     0,     0,     0,
    }, yyGindex = {
//yyGindex 28
     0,     9,   -49,   277,   284,     0,    72,   -46,   337,   -47,
   280,     0,     0,     0,     0,    -3,   -62,   -79,     0,     6,
    -4,    43,   -22,     0,     0,   -15,     0,    14,
    }, yyTable = {
//yyTable 447
    56,    14,    11,    14,    84,    77,    82,    95,    49,    14,
    80,    56,    54,   100,   103,    11,   105,   106,    57,   101,
    43,    48,    56,   108,   114,   122,    11,   116,   123,    57,
   118,   110,   109,    85,    12,    56,    11,     3,    42,    14,
    57,    85,    97,    85,   101,    14,    11,    12,    14,    87,
    90,    93,    14,   121,    45,    81,    64,    11,    12,   125,
    62,    54,    14,    14,   115,    41,    96,   117,    12,    58,
   119,    49,    49,    87,    90,    93,    43,    33,    12,    11,
    49,    40,    71,    71,    14,    83,    77,    60,   111,    12,
    11,    43,   124,    48,    14,    88,    91,    94,   112,     0,
     0,    33,    33,    33,    11,    33,    40,    40,    40,    74,
     0,    12,    40,    47,     0,     0,     0,     0,     0,    88,
    91,    94,    12,     0,     0,     0,    14,     0,    40,    29,
     2,    30,    31,     5,     6,     7,    12,     0,    10,    55,
     0,     0,     0,    74,    74,    74,    74,     0,    40,    40,
    40,     0,    40,     0,     0,     0,     0,     0,    33,     0,
     0,    33,     0,     0,    33,     0,     0,     0,    52,     2,
    53,    54,     5,     6,     7,     8,     0,    10,    85,    33,
    40,    76,     2,    77,    78,    37,     6,     7,     8,     0,
    10,    85,     1,     2,     3,     4,     5,     6,     7,     8,
     9,    10,     1,     2,     3,     4,     5,     6,     7,     8,
     0,    10,    52,     2,    53,    54,     5,     6,     7,     8,
     0,    10,    55,    76,     2,    77,    78,    37,     6,     7,
     8,     0,    10,    55,    29,     2,    30,    31,     5,     6,
     7,     0,     0,    10,     0,     1,     2,     3,     4,     5,
     6,     7,     8,     0,    10,     0,     1,     2,     3,     4,
     5,     6,     7,     8,     0,    10,     0,     0,    14,    14,
    34,     2,    35,    36,    37,     6,     7,     8,    23,    10,
    25,    27,    32,     0,     0,    24,     0,    26,    28,     0,
     0,     0,     0,     0,     0,    58,     0,     0,     0,     0,
     0,     0,    59,     0,     0,     0,    23,    25,    27,     0,
    67,    23,    25,    27,    32,     0,     0,    58,    24,    26,
    28,     0,     0,     0,    59,     0,     0,     0,     0,    23,
    25,    27,    86,    89,    92,     0,    24,    26,    28,     0,
     0,   104,     0,     0,   107,     0,     0,     0,    23,    25,
    27,    67,     0,    23,    25,    27,    86,    89,    92,     0,
    24,    26,    28,   113,     0,     0,   113,     0,     0,   113,
     0,    68,    69,    70,    75,     0,   120,    79,     0,     0,
     0,     0,     0,     0,   113,     0,     0,     0,     0,     0,
     0,     0,     0,     0,   104,   107,   104,   107,   104,   107,
     0,     0,     0,     0,     0,     0,     0,     0,    68,    69,
    70,    79,     0,    68,    69,    70,    71,     2,    72,    73,
    37,     6,     7,     0,     0,    10,    55,    29,     2,    30,
    31,     5,     6,     7,     0,     0,    10,    71,     2,    72,
    73,    37,     6,     7,     0,     0,    10,
    }, yyCheck = {
//yyCheck 447
    45,     0,    91,    44,    44,    44,    44,    56,    12,     0,
    58,    45,    44,    59,    61,    91,    63,    64,    63,   268,
    11,    12,    45,    58,    86,   104,    91,    89,   107,    63,
    92,    80,    79,   267,   123,    45,    91,     0,    93,     0,
    63,   267,   268,   267,   268,    44,    91,   123,   268,    52,
    53,    54,    93,    99,   268,    93,    58,    91,   123,   108,
   268,    93,   267,   268,    86,   268,    57,    89,   123,   268,
    92,   267,   268,    76,    77,    78,   268,     5,   123,    91,
    84,     9,   267,   268,   125,   125,   125,   268,    82,   123,
    91,    82,   107,    84,    93,    52,    53,    54,    84,    -1,
    -1,    29,    30,    31,    91,    33,    34,    35,    36,    37,
    -1,   123,    40,   125,    -1,    -1,    -1,    -1,    -1,    76,
    77,    78,   123,    -1,    -1,    -1,   125,    -1,    56,   257,
   258,   259,   260,   261,   262,   263,   123,    -1,   266,   267,
    -1,    -1,    -1,    71,    72,    73,    74,    -1,    76,    77,
    78,    -1,    80,    -1,    -1,    -1,    -1,    -1,    86,    -1,
    -1,    89,    -1,    -1,    92,    -1,    -1,    -1,   257,   258,
   259,   260,   261,   262,   263,   264,    -1,   266,   267,   107,
   108,   257,   258,   259,   260,   261,   262,   263,   264,    -1,
   266,   267,   257,   258,   259,   260,   261,   262,   263,   264,
   265,   266,   257,   258,   259,   260,   261,   262,   263,   264,
    -1,   266,   257,   258,   259,   260,   261,   262,   263,   264,
    -1,   266,   267,   257,   258,   259,   260,   261,   262,   263,
   264,    -1,   266,   267,   257,   258,   259,   260,   261,   262,
   263,    -1,    -1,   266,    -1,   257,   258,   259,   260,   261,
   262,   263,   264,    -1,   266,    -1,   257,   258,   259,   260,
   261,   262,   263,   264,    -1,   266,    -1,    -1,   267,   268,
   257,   258,   259,   260,   261,   262,   263,   264,     1,   266,
     3,     4,     5,    -1,    -1,     1,    -1,     3,     4,    -1,
    -1,    -1,    -1,    -1,    -1,    18,    -1,    -1,    -1,    -1,
    -1,    -1,    18,    -1,    -1,    -1,    29,    30,    31,    -1,
    33,    34,    35,    36,    37,    -1,    -1,    40,    34,    35,
    36,    -1,    -1,    -1,    40,    -1,    -1,    -1,    -1,    52,
    53,    54,    52,    53,    54,    -1,    52,    53,    54,    -1,
    -1,    61,    -1,    -1,    64,    -1,    -1,    -1,    71,    72,
    73,    74,    -1,    76,    77,    78,    76,    77,    78,    -1,
    76,    77,    78,    86,    -1,    -1,    89,    -1,    -1,    92,
    -1,    34,    35,    36,    37,    -1,    96,    40,    -1,    -1,
    -1,    -1,    -1,    -1,   107,    -1,    -1,    -1,    -1,    -1,
    -1,    -1,    -1,    -1,   114,   115,   116,   117,   118,   119,
    -1,    -1,    -1,    -1,    -1,    -1,    -1,    -1,    71,    72,
    73,    74,    -1,    76,    77,    78,   257,   258,   259,   260,
   261,   262,   263,    -1,    -1,   266,   267,   257,   258,   259,
   260,   261,   262,   263,    -1,    -1,   266,   257,   258,   259,
   260,   261,   262,   263,    -1,    -1,   266,
    };

  /** maps symbol value to printable name.
      @see #yyExpecting
    */
  protected static final String[] yyNames = {
    "end-of-file",null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,"','","'-'",null,null,null,null,null,null,null,
    null,null,null,null,null,"':'",null,null,null,null,"'?'",null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,"'['",null,
    "']'",null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,null,null,
    null,null,null,null,null,null,null,null,null,null,null,null,
    "YAML_ANCHOR","YAML_ALIAS","YAML_TRANSFER","YAML_TAGURI",
    "YAML_ITRANSFER","YAML_WORD","YAML_PLAIN","YAML_BLOCK","YAML_DOCSEP",
    "YAML_IOPEN","YAML_INDENT","YAML_IEND",
    };

//t  /** printable rules for debugging.
//t    */
//t  protected static final String [] yyRule = {
//t    "$accept : doc",
//t    "doc : atom",
//t    "doc : YAML_DOCSEP atom_or_empty",
//t    "doc :",
//t    "atom : word_rep",
//t    "atom : ind_rep",
//t    "ind_rep : struct_rep",
//t    "ind_rep : YAML_TRANSFER ind_rep",
//t    "ind_rep : YAML_TAGURI ind_rep",
//t    "ind_rep : YAML_ANCHOR ind_rep",
//t    "ind_rep : indent_open ind_rep indent_flex_end",
//t    "atom_or_empty : atom",
//t    "atom_or_empty : empty",
//t    "empty : indent_open empty indent_end",
//t    "empty :",
//t    "empty : YAML_ITRANSFER empty",
//t    "empty : YAML_TRANSFER empty",
//t    "empty : YAML_TAGURI empty",
//t    "empty : YAML_ANCHOR empty",
//t    "indent_open : YAML_IOPEN",
//t    "indent_open : indent_open YAML_INDENT",
//t    "indent_end : YAML_IEND",
//t    "indent_sep : YAML_INDENT",
//t    "indent_flex_end : YAML_IEND",
//t    "indent_flex_end : indent_sep indent_flex_end",
//t    "word_rep : YAML_TRANSFER word_rep",
//t    "word_rep : YAML_TAGURI word_rep",
//t    "word_rep : YAML_ITRANSFER word_rep",
//t    "word_rep : YAML_ANCHOR word_rep",
//t    "word_rep : YAML_ALIAS",
//t    "word_rep : YAML_WORD",
//t    "word_rep : YAML_PLAIN",
//t    "word_rep : indent_open word_rep indent_flex_end",
//t    "struct_rep : YAML_BLOCK",
//t    "struct_rep : implicit_seq",
//t    "struct_rep : inline_seq",
//t    "struct_rep : implicit_map",
//t    "struct_rep : inline_map",
//t    "implicit_seq : indent_open top_imp_seq indent_end",
//t    "implicit_seq : indent_open in_implicit_seq indent_end",
//t    "basic_seq : '-' atom_or_empty",
//t    "top_imp_seq : YAML_TRANSFER indent_sep in_implicit_seq",
//t    "top_imp_seq : YAML_TRANSFER top_imp_seq",
//t    "top_imp_seq : YAML_TAGURI indent_sep in_implicit_seq",
//t    "top_imp_seq : YAML_TAGURI top_imp_seq",
//t    "top_imp_seq : YAML_ANCHOR indent_sep in_implicit_seq",
//t    "top_imp_seq : YAML_ANCHOR top_imp_seq",
//t    "in_implicit_seq : basic_seq",
//t    "in_implicit_seq : in_implicit_seq indent_sep basic_seq",
//t    "in_implicit_seq : in_implicit_seq indent_sep",
//t    "inline_seq : '[' in_inline_seq ']'",
//t    "inline_seq : '[' ']'",
//t    "in_inline_seq : inline_seq_atom",
//t    "in_inline_seq : in_inline_seq ',' inline_seq_atom",
//t    "inline_seq_atom : atom",
//t    "inline_seq_atom : basic_mapping",
//t    "implicit_map : indent_open top_imp_map indent_end",
//t    "implicit_map : indent_open in_implicit_map indent_end",
//t    "top_imp_map : YAML_TRANSFER indent_sep in_implicit_map",
//t    "top_imp_map : YAML_TRANSFER top_imp_map",
//t    "top_imp_map : YAML_TAGURI indent_sep in_implicit_map",
//t    "top_imp_map : YAML_TAGURI top_imp_map",
//t    "top_imp_map : YAML_ANCHOR indent_sep in_implicit_map",
//t    "top_imp_map : YAML_ANCHOR top_imp_map",
//t    "complex_key : word_rep",
//t    "complex_key : '?' atom indent_sep",
//t    "complex_value : atom_or_empty",
//t    "complex_mapping : complex_key ':' complex_value",
//t    "in_implicit_map : complex_mapping",
//t    "in_implicit_map : in_implicit_map indent_sep basic_seq",
//t    "in_implicit_map : in_implicit_map indent_sep complex_mapping",
//t    "in_implicit_map : in_implicit_map indent_sep",
//t    "basic_mapping : atom ':' atom_or_empty",
//t    "inline_map : '{' in_inline_map '}'",
//t    "inline_map : '{' '}'",
//t    "in_inline_map : inline_map_atom",
//t    "in_inline_map : in_inline_map ',' inline_map_atom",
//t    "inline_map_atom : atom",
//t    "inline_map_atom : basic_mapping",
//t    };
//t
//t  /** debugging support, requires the package <tt>jay.yydebug</tt>.
//t      Set to <tt>null</tt> to suppress debugging messages.
//t    */
//t  protected jay.yydebug.yyDebug yydebug;
//t
//t  /** index-checked interface to {@link #yyNames}.
//t      @param token single character or <tt>%token</tt> value.
//t      @return token name or <tt>[illegal]</tt> or <tt>[unknown]</tt>.
//t    */
//t  public static final String yyName (int token) {
//t    if (token < 0 || token > yyNames.length) return "[illegal]";
//t    String name;
//t    if ((name = yyNames[token]) != null) return name;
//t    return "[unknown]";
//t  }
//t

  /** must be implemented by a scanner object to supply input to the parser.
      Nested for convenience, does not depend on parser class.
    */
  public interface yyInput {

    /** move on to next token.
        @return <tt>false</tt> if positioned beyond tokens.
        @throws IOException on input error.
      */
    boolean advance () throws java.io.IOException;

    /** classifies current token.
        Should not be called if {@link #advance()} returned <tt>false</tt>.
        @return current <tt>%token</tt> or single character.
      */
    int token ();

    /** associated with current token.
        Should not be called if {@link #advance()} returned <tt>false</tt>.
        @return value for {@link #token()}.
      */
    Object value ();
  }

  /** the generated parser, with debugging messages.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @param yydebug debug message writer implementing <tt>yyDebug</tt>, or <tt>null</tt>.
      @return result of the last reduction, if any.
    */
  public Object yyparse (yyInput yyLex, Object yydebug)
				throws java.io.IOException {
//t    this.yydebug = (jay.yydebug.yyDebug)yydebug;
    return yyparse(yyLex);
  }

  /** initial size and increment of the state/value stack [default 256].
      This is not final so that it can be overwritten outside of invocations
      of {@link #yyparse}.
    */
  protected int yyMax;

  /** executed at the beginning of a reduce action.
      Used as <tt>$$ = yyDefault($1)</tt>, prior to the user-specified action, if any.
      Can be overwritten to provide deep copy, etc.
      @param first value for <tt>$1</tt>, or <tt>null</tt>.
      @return first.
    */
  protected Object yyDefault (Object first) {
    return first;
  }

  /** the generated parser.
      Maintains a dynamic state and value stack.
      @param yyLex scanner.
      @return result of the last reduction, if any.
    */
  public Object yyparse (yyInput yyLex) throws java.io.IOException {
    if (yyMax <= 0) yyMax = 256;			// initial size
    int yyState = 0, yyStates[] = new int[yyMax];	// state stack
    Object yyVal = null, yyVals[] = new Object[yyMax];	// value stack
    int yyToken = -1;					// current input
    int yyErrorFlag = 0;				// #tokens to shift

    yyLoop: for (int yyTop = 0;; ++ yyTop) {
      if (yyTop >= yyStates.length) {			// dynamically increase
        int[] i = new int[yyStates.length+yyMax];
        System.arraycopy(yyStates, 0, i, 0, yyStates.length);
        yyStates = i;
        Object[] o = new Object[yyVals.length+yyMax];
        System.arraycopy(yyVals, 0, o, 0, yyVals.length);
        yyVals = o;
      }
      yyStates[yyTop] = yyState;
      yyVals[yyTop] = yyVal;
//t      if (yydebug != null) yydebug.push(yyState, yyVal);

      yyDiscarded: for (;;) {	// discarding a token does not change stack
        int yyN;
        if ((yyN = yyDefRed[yyState]) == 0) {	// else [default] reduce (yyN)
          if (yyToken < 0) {
            yyToken = yyLex.advance() ? yyLex.token() : 0;
//t            if (yydebug != null)
//t              yydebug.lex(yyState, yyToken, yyName(yyToken), yyLex.value());
          }
          if ((yyN = yySindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken) {
//t            if (yydebug != null)
//t              yydebug.shift(yyState, yyTable[yyN], yyErrorFlag > 0 ? yyErrorFlag-1 : 0);
            yyState = yyTable[yyN];		// shift to yyN
            yyVal = yyLex.value();
            yyToken = -1;
            if (yyErrorFlag > 0) -- yyErrorFlag;
            continue yyLoop;
          }
          if ((yyN = yyRindex[yyState]) != 0 && (yyN += yyToken) >= 0
              && yyN < yyTable.length && yyCheck[yyN] == yyToken)
            yyN = yyTable[yyN];			// reduce (yyN)
          else
            switch (yyErrorFlag) {
  
            case 0:
              yyerror("syntax error");
//t              if (yydebug != null) yydebug.error("syntax error");
  
            case 1: case 2:
              yyErrorFlag = 3;
              do {
                if ((yyN = yySindex[yyStates[yyTop]]) != 0
                    && (yyN += yyErrorCode) >= 0 && yyN < yyTable.length
                    && yyCheck[yyN] == yyErrorCode) {
//t                  if (yydebug != null)
//t                    yydebug.shift(yyStates[yyTop], yyTable[yyN], 3);
                  yyState = yyTable[yyN];
                  yyVal = yyLex.value();
                  continue yyLoop;
                }
//t                if (yydebug != null) yydebug.pop(yyStates[yyTop]);
              } while (-- yyTop >= 0);
//t              if (yydebug != null) yydebug.reject();
              yyerror("irrecoverable syntax error");
  
            case 3:
              if (yyToken == 0) {
//t                if (yydebug != null) yydebug.reject();
                yyerror("irrecoverable syntax error at end-of-file");
              }
//t              if (yydebug != null)
//t                yydebug.discard(yyState, yyToken, yyName(yyToken), yyLex.value());
              yyToken = -1;
              continue yyDiscarded;		// leave stack alone
            }
        }
        int yyV = yyTop + 1-yyLen[yyN];
//t        if (yydebug != null)
//t          yydebug.reduce(yyState, yyStates[yyV-1], yyN, yyRule[yyN], yyLen[yyN]);
        yyVal = yyDefault(yyV > yyTop ? null : yyVals[yyV]);
        switch (yyN) {
case 1:
					// line 19 "src/main/org/yecht/DefaultYAMLParser.y"
  {
           parser.root = parser.addNode((Node)yyVals[0+yyTop]);
        }
  break;
case 2:
					// line 23 "src/main/org/yecht/DefaultYAMLParser.y"
  {
           parser.root = parser.addNode((Node)yyVals[0+yyTop]);
        }
  break;
case 3:
					// line 27 "src/main/org/yecht/DefaultYAMLParser.y"
  {
           parser.eof = true;
        }
  break;
case 7:
					// line 38 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
            Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
            yyVal = yyVals[0+yyTop];
        }
  break;
case 8:
					// line 43 "src/main/org/yecht/DefaultYAMLParser.y"
  {
            Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], false);
            yyVal = yyVals[0+yyTop];
        }
  break;
case 9:
					// line 48 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
           /*
            * _Anchors_: The language binding must keep a separate symbol table
            * for anchors.  The actual ID in the symbol table is returned to the
            * higher nodes, though.
            */
           yyVal = parser.addAnchor((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop] );
        }
  break;
case 10:
					// line 57 "src/main/org/yecht/DefaultYAMLParser.y"
  {
           yyVal = yyVals[-1+yyTop];
        }
  break;
case 13:
					// line 67 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 14:
					// line 71 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    Node n = NULL_NODE( parser );
                    yyVal = n;
                }
  break;
case 15:
					// line 76 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                   if(parser.implicit_typing)
                   {
                      ImplicitScanner.tryTagImplicit((Node)yyVals[0+yyTop], parser.taguri_expansion);
                   }
                   yyVal = yyVals[0+yyTop];
                }
  break;
case 16:
					// line 84 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 17:
					// line 89 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], false);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 18:
					// line 94 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                   /*
                    * _Anchors_: The language binding must keep a separate symbol table
                    * for anchors.  The actual ID in the symbol table is returned to the
                    * higher nodes, though.
                    */
                   yyVal = parser.addAnchor((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop] );
                }
  break;
case 25:
					// line 127 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
               Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
               yyVal = yyVals[0+yyTop];
            }
  break;
case 26:
					// line 132 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
               Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], false);
               yyVal = yyVals[0+yyTop];
            }
  break;
case 27:
					// line 137 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
               if(parser.implicit_typing)
               {
                  ImplicitScanner.tryTagImplicit((Node)yyVals[0+yyTop], parser.taguri_expansion);
               }
               yyVal = yyVals[0+yyTop];
            }
  break;
case 28:
					// line 145 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
               yyVal = parser.addAnchor((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop] );
            }
  break;
case 29:
					// line 149 "src/main/org/yecht/DefaultYAMLParser.y"
  {
               /*
                * _Aliases_: The anchor symbol table is scanned for the anchor name.
                * The anchor's ID in the language's symbol table is returned.
                */
               yyVal = parser.getAnchor((String)yyVals[0+yyTop]);
            }
  break;
case 30:
					// line 157 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
               Node n = (Node)yyVals[0+yyTop];
               if(parser.taguri_expansion) {
                   n.type_id = Parser.taguri(YAML.DOMAIN, "str");
               } else {
                   n.type_id = "str";
               }
               yyVal = n;
            }
  break;
case 32:
					// line 168 "src/main/org/yecht/DefaultYAMLParser.y"
  {
               yyVal = yyVals[-1+yyTop];
            }
  break;
case 38:
					// line 188 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 39:
					// line 192 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 40:
					// line 198 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = parser.addNode((Node) yyVals[0+yyTop]);
                }
  break;
case 41:
					// line 204 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 42:
					// line 209 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 43:
					// line 214 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop], false);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 44:
					// line 219 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], false);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 45:
					// line 224 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = parser.addAnchor((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop] );
                }
  break;
case 46:
					// line 228 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = parser.addAnchor((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop] );
                }
  break;
case 47:
					// line 234 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = Node.newSeq(yyVals[0+yyTop]);
                }
  break;
case 48:
					// line 238 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    ((Node)yyVals[-2+yyTop]).seqAdd(yyVals[0+yyTop]);
                    yyVal = yyVals[-2+yyTop];
				}
  break;
case 49:
					// line 243 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = yyVals[-1+yyTop];
				}
  break;
case 50:
					// line 252 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 51:
					// line 256 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = Node.allocSeq();
                }
  break;
case 52:
					// line 262 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = Node.newSeq(parser.addNode((Node)yyVals[0+yyTop]));
                }
  break;
case 53:
					// line 266 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    ((Node)yyVals[-2+yyTop]).seqAdd(parser.addNode((Node)yyVals[0+yyTop]));
                    yyVal = yyVals[-2+yyTop];
				}
  break;
case 56:
					// line 280 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    applySeqInMap(parser, (Node)yyVals[-1+yyTop]);
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 57:
					// line 285 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    applySeqInMap(parser, (Node)yyVals[-1+yyTop]);
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 58:
					// line 292 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 59:
					// line 297 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], parser.taguri_expansion);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 60:
					// line 302 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop], false);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 61:
					// line 307 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    Parser.addTransfer((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop], false);
                    yyVal = yyVals[0+yyTop];
                }
  break;
case 62:
					// line 312 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = parser.addAnchor((String)yyVals[-2+yyTop], (Node)yyVals[0+yyTop]);
                }
  break;
case 63:
					// line 316 "src/main/org/yecht/DefaultYAMLParser.y"
  { 
                    yyVal = parser.addAnchor((String)yyVals[-1+yyTop], (Node)yyVals[0+yyTop]);
                }
  break;
case 65:
					// line 323 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 67:
					// line 332 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = Node.newMap( 
                        parser.addNode( (Node)yyVals[-2+yyTop] ), 
                        parser.addNode( (Node)yyVals[0+yyTop] ) );
                }
  break;
case 69:
					// line 341 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    if ( ((Node)yyVals[-2+yyTop]).shortcut == null )
                    {
                        ((Node)yyVals[-2+yyTop]).shortcut = Node.newSeq(yyVals[0+yyTop]);
                    }
                    else
                    {
                        ((Node)((Node)yyVals[-2+yyTop]).shortcut).seqAdd(yyVals[0+yyTop]);
                    }
                    yyVal = yyVals[-2+yyTop];
                }
  break;
case 70:
					// line 353 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    applySeqInMap( parser, (Node)yyVals[-2+yyTop] );
                    ((Node)yyVals[-2+yyTop]).mapUpdate((Node)yyVals[0+yyTop]);
                    yyVals[0+yyTop] = null;
                    yyVal = yyVals[-2+yyTop];
                }
  break;
case 71:
					// line 360 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 72:
					// line 369 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = Node.newMap( 
                        parser.addNode( (Node)yyVals[-2+yyTop] ), 
                        parser.addNode( (Node)yyVals[0+yyTop] ) );
                }
  break;
case 73:
					// line 377 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = yyVals[-1+yyTop];
                }
  break;
case 74:
					// line 381 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    yyVal = Node.allocMap();
                }
  break;
case 76:
					// line 388 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    ((Node)yyVals[-2+yyTop]).mapUpdate((Node)yyVals[0+yyTop]);
                    yyVals[0+yyTop] = null;
                    yyVal = yyVals[-2+yyTop];
				}
  break;
case 77:
					// line 396 "src/main/org/yecht/DefaultYAMLParser.y"
  {
                    Node n = NULL_NODE( parser );
                    yyVal = Node.newMap( 
                        parser.addNode( (Node)yyVals[0+yyTop] ), 
                        parser.addNode( n ));
                }
  break;
					// line 863 "-"
        }
        yyTop -= yyLen[yyN];
        yyState = yyStates[yyTop];
        int yyM = yyLhs[yyN];
        if (yyState == 0 && yyM == 0) {
//t          if (yydebug != null) yydebug.shift(0, yyFinal);
          yyState = yyFinal;
          if (yyToken < 0) {
            yyToken = yyLex.advance() ? yyLex.token() : 0;
//t            if (yydebug != null)
//t               yydebug.lex(yyState, yyToken,yyName(yyToken), yyLex.value());
          }
          if (yyToken == 0) {
//t            if (yydebug != null) yydebug.accept(yyVal);
            return yyVal;
          }
          continue yyLoop;
        }
        if ((yyN = yyGindex[yyM]) != 0 && (yyN += yyState) >= 0
            && yyN < yyTable.length && yyCheck[yyN] == yyState)
          yyState = yyTable[yyN];
        else
          yyState = yyDgoto[yyM];
//t        if (yydebug != null) yydebug.shift(yyStates[yyTop], yyState);
        continue yyLoop;
      }
    }
  }

					// line 405 "src/main/org/yecht/DefaultYAMLParser.y"

    private Parser parser;
    public DefaultYAMLParser(Parser parser) {
        this.parser = parser;
    }

    public static Node NULL_NODE(Parser parser) {
        Node n = Node.newStr(Pointer.create(new byte[0], 0), 0, ScalarStyle.Plain);
        if(parser.taguri_expansion) {
            n.type_id = Parser.taguri(YAML.DOMAIN, "null");
        } else {
            n.type_id = "null";
        }
        return n;
    }


    public static void applySeqInMap(Parser parser, Node n) {
        if(n.shortcut == null) {
            return;
        }

        int map_len = (int)n.mapCount();
        n.mapAssign(MapPart.Value, map_len - 1, parser.addNode((Node)n.shortcut));
        n.shortcut = null;
    }

    public void yyerror(String msg) {
        TokenScanner.error(msg, parser);
    }
}
					// line 925 "-"
