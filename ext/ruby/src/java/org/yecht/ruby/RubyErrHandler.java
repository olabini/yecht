package org.yecht.ruby;

import org.yecht.Parser;
import org.yecht.ErrorHandler;

import org.jruby.Ruby;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyErrHandler implements ErrorHandler {
    private Ruby runtime;

    public RubyErrHandler(Ruby runtime) {
        this.runtime = runtime;
    }

    // rb_syck_err_handler
    public void handle(Parser p, String msg) {
        int endl = p.cursor;
        while(p.buffer.buffer[endl] != 0 && p.buffer.buffer[endl] != '\n') {
            endl++;
        }
        try {
            int lp = p.lineptr;
            if(lp < 0) {
                lp = 0;
            }
            int len = endl-lp;
            if(len < 0) {
                len = 0;
            }
            String line = new String(p.buffer.buffer, lp, len, "ISO-8859-1");
            String m1 = msg + " on line " + p.linect + ", col " + (p.cursor-lp) + ": `" + line + "'";
            throw runtime.newArgumentError(m1);
        } catch(java.io.UnsupportedEncodingException e) {
        }
            
    }
}
