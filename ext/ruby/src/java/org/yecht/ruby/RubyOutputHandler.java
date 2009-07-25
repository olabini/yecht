package org.yecht.ruby;

import org.yecht.Emitter;
import org.yecht.OutputHandler;

import org.jruby.Ruby;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;

public class RubyOutputHandler implements OutputHandler {
    private Ruby runtime;

    public RubyOutputHandler(Ruby runtime) {
        this.runtime = runtime;
    }

    // rb_syck_output_handler
    public void handle(Emitter emitter, byte[] str, int len) {
        YEmitter.Extra bonus = (YEmitter.Extra)emitter.bonus;
        IRubyObject dest = bonus.port;
        if(dest instanceof RubyString) {
            ((RubyString)dest).cat(new ByteList(str, 0, len, false));
        } else {
            dest.callMethod(runtime.getCurrentContext(), "write", RubyString.newStringShared(runtime, str, 0, len));
        }
    }
}
