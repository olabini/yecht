package org.yecht.ruby;

import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class YObject {
    // syck_yobject_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject klass, IRubyObject ivars) {
        ((RubyObject)self).fastSetInternalVariable("@class", klass);
        ((RubyObject)self).fastSetInternalVariable("@ivars", ivars);
        return self;
    }

    // syck_yobject_initialize
    @JRubyMethod
    public static IRubyObject yaml_initialize(IRubyObject self, IRubyObject klass, IRubyObject ivars) {
        ((RubyObject)self).fastSetInternalVariable("@class", klass);
        ((RubyObject)self).fastSetInternalVariable("@ivars", ivars);
        return self;
    }
}
