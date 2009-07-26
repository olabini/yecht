package org.yecht.ruby;

import java.util.List;
import java.util.LinkedList;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;

public class BadAlias extends RubyObject implements PossibleLinkNode {
    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                return new BadAlias(runtime, klass);
            }
        };
        
    public BadAlias(Ruby runtime, RubyClass metaClass) {
        super(runtime, metaClass);
    }

    private List<StorageLink> links = new LinkedList<StorageLink>();
    public void addLink(StorageLink link) {
        links.add(link);
    }

    public void replaceLinks(IRubyObject newObject) {
        for(StorageLink sl : links) {
            sl.replaceLinkWith(newObject);
        }
        links.clear();
    }

    // syck_badalias_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject val) {
        ((RubyObject)self).fastSetInstanceVariable("@name", val);
        return self;
    }

    // syck_badalias_cmp
    @JRubyMethod(name = "<=>")
    public static IRubyObject cmp(IRubyObject alias1, IRubyObject alias2) {
        IRubyObject str1 = (IRubyObject)((RubyObject)alias1).fastGetInstanceVariable("@name");
        IRubyObject str2 = (IRubyObject)((RubyObject)alias2).fastGetInstanceVariable("@name");
        return str1.callMethod(alias1.getRuntime().getCurrentContext(), "<=>", str2);
    }
}
