package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.Pointer;
import org.yecht.ScalarStyle;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;

public class Scalar {
    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            // syck_scalar_alloc
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                //                     System.err.println("ALLOCATING SCALAR");
                org.yecht.Node node = org.yecht.Node.allocStr();
                IRubyObject obj = new Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                node.id = obj;
                //                     System.err.println("syck_scalar_alloc() -> setting id: " + node.id);
                return obj;
            }
        };

    // syck_scalar_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        ((RubyObject)self).fastSetInternalVariable("@kind", ((Node)self).x.scalar);
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }

    // syck_scalar_style_set
    @JRubyMethod(name = "style=")
    public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
        YAMLExtra x = ((Node)self).x;
        Ruby runtime = self.getRuntime();
        Data.Str ds = (Data.Str)((org.yecht.Node)self.dataGetStructChecked()).data;
        if(style.isNil()) {
            ds.style = ScalarStyle.None;
        } else if(style == x.quote1) {
            ds.style = ScalarStyle.OneQuote;
        } else if(style == x.quote2) {
            ds.style = ScalarStyle.TwoQuote;
        } else if(style == x.fold) {
            ds.style = ScalarStyle.Fold;
        } else if(style == x.literal) {
            ds.style = ScalarStyle.Literal;
        } else if(style == x.plain) {
            ds.style = ScalarStyle.Plain;
        }
        ((RubyObject)self).fastSetInternalVariable("@style", style);
        return self;
    }

    // syck_scalar_value_set
    @JRubyMethod(name = "value=")
    public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();
        Data.Str ds = (Data.Str)node.data;
            
        val = val.convertToString();
        ByteList bl = ((RubyString)val).getByteList();
        byte[] bss = new byte[bl.realSize];
        System.arraycopy(bl.bytes, bl.begin, bss, 0, bss.length);
        ds.ptr = Pointer.create(bss, 0);
        ds.len = bss.length;
        ds.style = ScalarStyle.None;
        ((RubyObject)self).fastSetInternalVariable("@value", val);
        return val;
    }
}
