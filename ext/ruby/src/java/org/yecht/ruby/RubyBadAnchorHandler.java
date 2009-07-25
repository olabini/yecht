package org.yecht.ruby;

import org.yecht.BadAnchorHandler;
import org.yecht.Parser;

import org.jruby.Ruby;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyBadAnchorHandler implements BadAnchorHandler {
    private Ruby runtime;

    public RubyBadAnchorHandler(Ruby runtime) {
        this.runtime = runtime;
    }

    // rb_syck_bad_anchor_handler
    public org.yecht.Node handle(Parser p, String a) {
        IRubyObject anchor_name = runtime.newString(a);
        IRubyObject nm = runtime.newString("name");
        org.yecht.Node badanc = org.yecht.Node.newMap(nm, anchor_name);
        badanc.type_id = "tag:ruby.yaml.org,2002:object:YAML::Yecht::BadAlias";
        return badanc;
    }
}
