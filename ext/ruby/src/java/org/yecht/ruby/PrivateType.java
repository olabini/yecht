package org.yecht.ruby;

import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class PrivateType {
    // syck_privatetype_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val) {
        ((RubyObject)self).fastSetInstanceVariable("@type_id", type_id);
        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return self;
    }
}
