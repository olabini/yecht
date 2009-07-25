package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.SeqStyle;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public class Seq {
    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            // syck_seq_alloc
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                //                     System.err.println("ALLOCATING SEQ");
                org.yecht.Node node = org.yecht.Node.allocSeq();
                IRubyObject obj = new Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                node.id = obj;
                //                     System.err.println("syck_seq_alloc() -> setting id");
                return obj;
            }
        };

    // syck_seq_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        ((RubyObject)self).fastSetInternalVariable("@kind", ((Node)self).x.seq);
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }

    // syck_seq_value_set
    @JRubyMethod(name = "value=")
    public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();

        val = val.checkArrayType();
        if(!val.isNil()) {
            node.seqEmpty();
            Data.Seq ds = (Data.Seq)node.data;
            for(int i=0; i<((RubyArray)val).getLength(); i++) {
                node.seqAdd(((RubyArray)val).entry(i));
            }
        }

        ((RubyObject)self).fastSetInternalVariable("@value", val);
        return val;
    }

    // syck_seq_style_set
    @JRubyMethod(name = "style=")
    public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();
        Data.Seq ds = (Data.Seq)node.data;
        if(style == runtime.newSymbol("inline")) {
            ds.style = SeqStyle.Inline;
        } else {
            ds.style = SeqStyle.None;
        }

        ((RubyObject)self).fastSetInternalVariable("@style", style);
        return self;
    }

    // syck_seq_add_m
    @JRubyMethod
    public static IRubyObject add(IRubyObject self, IRubyObject val) {
        IRubyObject emitter = (IRubyObject)((RubyObject)self).fastGetInternalVariable("@emitter");
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        if(emitter.respondsTo("node_export")) {
            val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
        }
        node.seqAdd(val);
        ((RubyArray)((RubyObject)self).fastGetInternalVariable("@value")).append(val);
        return self;
    }
}
