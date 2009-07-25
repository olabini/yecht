package org.yecht.ruby;

import org.yecht.Bytestring;
import org.yecht.BytecodeNodeHandler;
import org.yecht.Parser;

import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;

public class Module {
    // rb_syck_compile
    @JRubyMethod(name = "compile", required = 1, module = true)
    public static IRubyObject compile(IRubyObject self, IRubyObject port) {
        Parser parser = Parser.newParser();
        boolean taint = YParser.assignIO(self.getRuntime(), parser, new IRubyObject[] {port});
        parser.handler(new BytecodeNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(false);
        parser.taguriExpansion(false);
        Bytestring sav = (Bytestring)parser.parse();
        int len = Bytestring.strlen(sav.buffer);
        ByteList bl = new ByteList(new byte[len+2], false);
        bl.append(sav.buffer, 0, len);
        bl.append('D');
        bl.append('\n');
        IRubyObject iro = RubyString.newStringLight(self.getRuntime(), bl);
        if(taint) iro.setTaint(true);
        return iro;
    }
}
