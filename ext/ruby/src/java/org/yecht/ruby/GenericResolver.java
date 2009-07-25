package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.MapStyle;
import org.yecht.MapPart;
import org.yecht.SeqStyle;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.MethodIndex;

public class GenericResolver {
    public static class Extra {
        public IRubyObject quote1;
        public IRubyObject quote2;
        public IRubyObject fold;
        public IRubyObject literal;
        public IRubyObject plain;
        public IRubyObject map;
        public IRubyObject seq;
        public IRubyObject inline;
        public IRubyObject Scalar;
        public IRubyObject Seq;
        public IRubyObject Map;
        public Ruby runtime;
        private final CallSite newScalarAdapter = MethodIndex.getFunctionalCallSite("new");
        private final CallSite newSeqAdapter = MethodIndex.getFunctionalCallSite("new");
        private final CallSite newMapAdapter = MethodIndex.getFunctionalCallSite("new");

        public Extra(Ruby runtime) {
            quote1 = runtime.newSymbol("quote1");
            quote2 = runtime.newSymbol("quote2");
            fold = runtime.newSymbol("fold");
            literal = runtime.newSymbol("literal");
            plain = runtime.newSymbol("plain");
            map = runtime.newSymbol("map");
            seq = runtime.newSymbol("seq");
            inline = runtime.newSymbol("inline");
            Scalar = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Scalar");
            Seq = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Seq");
            Map = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Map");
            this.runtime = runtime;
        }

        public IRubyObject scalar(IRubyObject t, org.yecht.Node n, ThreadContext ctx) {
            Data.Str dd = (Data.Str)n.data;
            IRubyObject v = RubyString.newStringShared(runtime, dd.ptr.buffer, dd.ptr.start, dd.len);
            IRubyObject style = runtime.getNil();
            switch(dd.style) {
            case OneQuote:
                style = quote1;
                break;
            case TwoQuote:
                style = quote2;
                break;
            case Fold:
                style = fold;
                break;
            case Literal:
                style = literal;
                break;
            case Plain:
                style = plain;
                break;
            }
            return newScalarAdapter.call(ctx, Scalar, Scalar, t, v, style);
        }

        public IRubyObject sequence(IRubyObject t, org.yecht.Node n, ThreadContext ctx) {
            Data.Seq ds = (Data.Seq)n.data;
            Object[] items = ds.items;
            IRubyObject v = RubyArray.newArray(runtime, ds.idx);
            for(int i = 0; i < ds.idx; i++) {
                ((RubyArray)v).store(i, (IRubyObject)items[i]);
            }
            IRubyObject style = runtime.getNil();
            if(((Data.Seq)n.data).style == SeqStyle.Inline) {
                style = inline;
            }
            IRubyObject obj = newSeqAdapter.call(ctx, Seq, Seq, t, v, style);
            ((RubyObject)obj).fastSetInternalVariable("@kind", seq);
            return obj;
        }

        public IRubyObject mapping(IRubyObject t, org.yecht.Node n, ThreadContext ctx) {
            Data.Map dm = (Data.Map)n.data;
            Object[] keys = dm.keys;
            Object[] vals = dm.values;
            IRubyObject v = RubyHash.newHash(runtime);
            for(int i = 0; i < dm.idx; i++) {
                IRubyObject k3 = (IRubyObject)keys[i];
                IRubyObject v3 = (IRubyObject)vals[i];
                if(null == v3) {
                    v3 = runtime.getNil();
                }

                ((RubyHash)v).fastASet(k3, v3);
            }
            IRubyObject style = runtime.getNil();
            if(((Data.Map)n.data).style == MapStyle.Inline) {
                style = inline;
            }
            IRubyObject obj = newMapAdapter.call(ctx, Map, Map, t, v, style);
            ((RubyObject)obj).fastSetInternalVariable("@kind", map);
            return obj;
        }
    }

    // syck_genericresolver_node_import
    @JRubyMethod
    public static IRubyObject node_import(IRubyObject self, IRubyObject node) {
        //             System.err.println("syck_genericresolver_node_import()");
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        org.yecht.Node n = (org.yecht.Node)node.dataGetStructChecked();
        IRubyObject t = runtime.getNil();
        Extra x = (Extra)self.dataGetStruct();

        if(n.type_id != null) {
            t = runtime.newString(n.type_id);
        }

        switch(n.kind) {
        case Str:
            return x.scalar(t, n, ctx);
        case Seq:
            return x.sequence(t, n, ctx);
        case Map:
            return x.mapping(t, n, ctx);
        }

        return runtime.getNil();
    }        
}
