package org.yecht.ruby;

import org.yecht.MapPart;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyString;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public class Node extends RubyObject.Data {
    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                return new Node(runtime, klass, null, (YAMLExtra)runtime.getModule("YAML").dataGetStruct());
            }
        };

    public YAMLExtra x;

    public Node(Ruby runtime, RubyClass metaClass, Object data, YAMLExtra x) {
        super(runtime, metaClass, data);
        this.x = x;
    }

    public Node(RubyClass metaClass, Object data, YAMLExtra x) {
        super(metaClass, data);
        this.x = x;
    }

    // syck_node_init_copy
    @JRubyMethod
    public static IRubyObject initialize_copy(IRubyObject copy, IRubyObject orig) {
        if(copy == orig) {
            return copy;
        }

        if(orig.getClass() != RubyObject.class) {
            throw copy.getRuntime().newTypeError("wrong argument type");
        }

        org.yecht.Node orig_n = (org.yecht.Node)orig.dataGetStructChecked();
        org.yecht.Node copy_n = (org.yecht.Node)copy.dataGetStructChecked();

        copy_n.id = orig_n.id;
        copy_n.kind = orig_n.kind;
        copy_n.type_id = orig_n.type_id;
        copy_n.anchor = orig_n.anchor;
        copy_n.data = orig_n.data.copy();

        return copy;
    }        

    // syck_node_type_id_set
    @JRubyMethod(name = "type_id=")
    public static IRubyObject set_type_id(IRubyObject self, IRubyObject type_id) {
        org.yecht.Node node = (org.yecht.Node)self.dataGetStructChecked();
        if(!type_id.isNil()) {
            node.type_id = type_id.convertToString().toString();
        }
        ((RubyObject)self).fastSetInternalVariable("@type_id", type_id);
        return type_id;
    }        

    // syck_node_transform
    @JRubyMethod
    public static IRubyObject transform(IRubyObject self) {
        //             System.err.println("syck_node_transform()");
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        org.yecht.Node orig_n = (org.yecht.Node)self.dataGetStructChecked();
        YAMLExtra x = ((Node)self).x;
        IRubyObject t = new Node(runtime, self.getType(), null, x);
        org.yecht.Node n = null;

        switch(orig_n.kind) {
        case Map:
            n = org.yecht.Node.allocMap();
            t.dataWrapStruct(n);
            org.yecht.Data.Map dm = (org.yecht.Data.Map)orig_n.data;
            for(int i=0; i < dm.idx; i++) {
                IRubyObject k = ((IRubyObject)orig_n.mapRead(MapPart.Key, i)).callMethod(ctx, "transform");
                IRubyObject v = ((IRubyObject)orig_n.mapRead(MapPart.Value, i)).callMethod(ctx, "transform");
                n.mapAdd(k, v);
            }
            break;
        case Seq:
            n = org.yecht.Node.allocSeq();
            t.dataWrapStruct(n);
            org.yecht.Data.Seq ds = (org.yecht.Data.Seq)orig_n.data;
            for(int i=0; i < ds.idx; i++) {
                IRubyObject itm = ((IRubyObject)orig_n.seqRead(i)).callMethod(ctx, "transform");
                n.seqAdd(itm);
            }
            break;
        case Str:
            org.yecht.Data.Str dss = (org.yecht.Data.Str)orig_n.data;
            n = org.yecht.Node.newStr(dss.ptr, dss.len, dss.style);
            t.dataWrapStruct(n);
            break;
        }

        if(orig_n.type_id != null) {
            n.type_id = orig_n.type_id;
        }

        if(orig_n.anchor != null) {
            n.anchor = orig_n.anchor;
        }

        n.id = t;
        //             System.err.println("syck_node_transform(), setting id of object on: " + n);
        IRubyObject result = x.DefaultResolver.callMethod(ctx, "node_import", t);
        return result;
    }
}
