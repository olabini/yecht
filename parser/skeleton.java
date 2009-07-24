#	jay skeleton for Java

#	character in column 1 determines outcome...
#		# is a comment
#		. is copied
#		t is copied as //t unless -t is set
#	other lines are interpreted to call jay procedures

 version	Java 1.1.0 (c) 2002-2006 ats@cs.rit.edu
.
 prolog		## %{ ... %} prior to the first %%

.  // %token constants
 tokens	public static final int
.
.  /** number of final state.
.    */
 yyFinal	protected static final int yyFinal =
.
.  /** parser tables.
.      Order is mandated by <i>jay</i>.
.    */
.  protected static final short[] yyLhs = {
 yyLhs
.    }, yyLen = {
 yyLen
.    }, yyDefRed = {
 yyDefRed
.    }, yyDgoto = {
 yyDgoto
.    }, yySindex = {
 yySindex
.    }, yyRindex = {
 yyRindex
.    }, yyGindex = {
 yyGindex
.    }, yyTable = {
 yyTable
.    }, yyCheck = {
 yyCheck
.    };
.
.  /** maps symbol value to printable name.
.      @see #yyExpecting
.    */
.  protected static final String[] yyNames = {
 yyNames-strings
.    };
.
.
.  /** must be implemented by a scanner object to supply input to the parser.
.      Nested for convenience, does not depend on parser class.
.    */
.  public interface yyInput {
.
.    /** move on to next token.
.        @return <tt>false</tt> if positioned beyond tokens.
.        @throws IOException on input error.
.      */
.    boolean advance () throws java.io.IOException;
.
.    /** classifies current token.
.        Should not be called if {@link #advance()} returned <tt>false</tt>.
.        @return current <tt>%token</tt> or single character.
.      */
.    int token ();
.
.    /** associated with current token.
.        Should not be called if {@link #advance()} returned <tt>false</tt>.
.        @return value for {@link #token()}.
.      */
.    Object value ();
.  }
.
.  /** the generated parser, with debugging messages.
.      Maintains a dynamic state and value stack.
.      @param yyLex scanner.
.      @param yydebug debug message writer implementing <tt>yyDebug</tt>, or <tt>null</tt>.
.      @return result of the last reduction, if any.
.    */
.  public Object yyparse (yyInput yyLex, Object yydebug)
.				throws java.io.IOException {
t    this.yydebug = (jay.yydebug.yyDebug)yydebug;
.    return yyparse(yyLex);
.  }
.
.  /** initial size and increment of the state/value stack [default 256].
.      This is not final so that it can be overwritten outside of invocations
.      of {@link #yyparse}.
.    */
.  protected int yyMax;
.
.  /** executed at the beginning of a reduce action.
.      Used as <tt>$$ = yyDefault($1)</tt>, prior to the user-specified action, if any.
.      Can be overwritten to provide deep copy, etc.
.      @param first value for <tt>$1</tt>, or <tt>null</tt>.
.      @return first.
.    */
.  protected Object yyDefault (Object first) {
.    return first;
.  }
.
.  /** the generated parser.
.      Maintains a dynamic state and value stack.
.      @param yyLex scanner.
.      @return result of the last reduction, if any.
.    */
.  public Object yyparse (yyInput yyLex) throws java.io.IOException {
.    if (yyMax <= 0) yyMax = 256;			// initial size
.    int yyState = 0, yyStates[] = new int[yyMax];	// state stack
.    Object yyVal = null, yyVals[] = new Object[yyMax];	// value stack
.    int yyToken = -1;					// current input
.    int yyErrorFlag = 0;				// #tokens to shift
.
 local		## %{ ... %} after the first %%

.    yyLoop: for (int yyTop = 0;; ++ yyTop) {
.      if (yyTop >= yyStates.length) {			// dynamically increase
.        int[] i = new int[yyStates.length+yyMax];
.        System.arraycopy(yyStates, 0, i, 0, yyStates.length);
.        yyStates = i;
.        Object[] o = new Object[yyVals.length+yyMax];
.        System.arraycopy(yyVals, 0, o, 0, yyVals.length);
.        yyVals = o;
.      }
.      yyStates[yyTop] = yyState;
.      yyVals[yyTop] = yyVal;
.
.      yyDiscarded: for (;;) {	// discarding a token does not change stack
.        int yyN;
.        if ((yyN = yyDefRed[yyState]) == 0) {	// else [default] reduce (yyN)
.          if (yyToken < 0) {
.            yyToken = yyLex.advance() ? yyLex.token() : 0;
.          }
.          if ((yyN = yySindex[yyState]) != 0 && (yyN += yyToken) >= 0
.              && yyN < yyTable.length && yyCheck[yyN] == yyToken) {
.            yyState = yyTable[yyN];		// shift to yyN
.            yyVal = yyLex.value();
.            yyToken = -1;
.            if (yyErrorFlag > 0) -- yyErrorFlag;
.            continue yyLoop;
.          }
.          if ((yyN = yyRindex[yyState]) != 0 && (yyN += yyToken) >= 0
.              && yyN < yyTable.length && yyCheck[yyN] == yyToken)
.            yyN = yyTable[yyN];			// reduce (yyN)
.          else
.            switch (yyErrorFlag) {
.  
.            case 0:
.              yyerror("syntax error");
.  
.            case 1: case 2:
.              yyErrorFlag = 3;
.              do {
.                if ((yyN = yySindex[yyStates[yyTop]]) != 0
.                    && (yyN += yyErrorCode) >= 0 && yyN < yyTable.length
.                    && yyCheck[yyN] == yyErrorCode) {
.                  yyState = yyTable[yyN];
.                  yyVal = yyLex.value();
.                  continue yyLoop;
.                }
.              } while (-- yyTop >= 0);
.              yyerror("irrecoverable syntax error");
.  
.            case 3:
.              if (yyToken == 0) {
.                yyerror("irrecoverable syntax error at end-of-file");
.              }
.              yyToken = -1;
.              continue yyDiscarded;		// leave stack alone
.            }
.        }
.        int yyV = yyTop + 1-yyLen[yyN];
.        yyVal = yyDefault(yyV > yyTop ? null : yyVals[yyV]);
.        switch (yyN) {
.// ACTIONS_BEGIN

 actions		## code from the actions within the grammar

.// ACTIONS_END
.        }
.        yyTop -= yyLen[yyN];
.        yyState = yyStates[yyTop];
.        int yyM = yyLhs[yyN];
.        if (yyState == 0 && yyM == 0) {
.          yyState = yyFinal;
.          if (yyToken < 0) {
.            yyToken = yyLex.advance() ? yyLex.token() : 0;
.          }
.          if (yyToken == 0) {
.            return yyVal;
.          }
.          continue yyLoop;
.        }
.        if ((yyN = yyGindex[yyM]) != 0 && (yyN += yyState) >= 0
.            && yyN < yyTable.length && yyCheck[yyN] == yyState)
.          yyState = yyTable[yyN];
.        else
.          yyState = yyDgoto[yyM];
.        continue yyLoop;
.      }
.    }
.  }
.
.// ACTION_BODIES
 epilog			## text following second %%
