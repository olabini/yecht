package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.MapStyle;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyHash;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.TypeConverter;

public class Map {
    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            // syck_map_alloc
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                //                     System.err.println("ALLOCATING MAP");
                org.yecht.Node node = org.yecht.Node.allocMap();
                IRubyObject obj = new Node(runtime, klass, node, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
                node.id = obj;
                //                     System.err.println("syck_map_alloc() -> setting id");
                return obj;
            }
        };

    // syck_map_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        Data.Map ds = (Data.Map)node.data;

        if(!val.isNil()) {
            IRubyObject hsh = TypeConverter.convertToTypeWithCheck(val, runtime.getHash(), "to_hash");

            if(hsh.isNil()) {
                throw runtime.newTypeError("wrong argument type");
            }
            IRubyObject keys = hsh.callMethod(ctx, "keys");
            for(int i = 0; i < ((RubyArray)keys).getLength(); i++) {
                IRubyObject key = ((RubyArray)keys).entry(i);
                node.mapAdd(key, ((RubyHash)hsh).op_aref(ctx, key));
            }
        }

        ((RubyObject)self).fastSetInstanceVariable("@kind", ((Node)self).x.seq); // NOT A TYPO - Syck does the same
        self.callMethod(ctx, "type_id=", type_id);
        self.callMethod(ctx, "value=", val);
        self.callMethod(ctx, "style=", style);
        return self;
    }

    // syck_map_value_set
    @JRubyMethod(name = "value=")
    public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();

        if(!val.isNil()) {
            IRubyObject hsh = TypeConverter.convertToTypeWithCheck(val, runtime.getHash(), "to_hash");

            if(hsh.isNil()) {
                throw runtime.newTypeError("wrong argument type");
            }
            node.mapEmpty();
            IRubyObject keys = hsh.callMethod(ctx, "keys");
            for(int i = 0; i < ((RubyArray)keys).getLength(); i++) {
                IRubyObject key = ((RubyArray)keys).entry(i);
                node.mapAdd(key, ((RubyHash)hsh).op_aref(ctx, key));
            }
        }

        ((RubyObject)self).fastSetInstanceVariable("@value", val);
        return val;
    }

    // syck_map_add_m
    @JRubyMethod
    public static IRubyObject add(IRubyObject self, IRubyObject key, IRubyObject val) {
        IRubyObject emitter = (IRubyObject)((RubyObject)self).fastGetInstanceVariable("@emitter");
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        if(emitter.respondsTo("node_export")) {
            key = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", key);
            val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
        }
        node.mapAdd(key, val);
        ((RubyHash)((RubyObject)self).fastGetInstanceVariable("@value")).fastASet(key, val);
        return self;
    }

    // syck_map_style_set
    @JRubyMethod(name = "style=")
    public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        Ruby runtime = self.getRuntime();
        Data.Map ds = (Data.Map)node.data;
        if(style == ((Node)self).x.inline) {
            ds.style = MapStyle.Inline;
        } else {
            ds.style = MapStyle.None;
        }

        ((RubyObject)self).fastSetInstanceVariable("@style", style);
        return self;
    }
}
