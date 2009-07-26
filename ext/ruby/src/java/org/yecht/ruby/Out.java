package org.yecht.ruby;

import org.yecht.Emitter;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public class Out {
    // syck_out_mark
    public static void outMark(IRubyObject emitter, IRubyObject node) {
        Emitter emitterPtr = (Emitter)emitter.dataGetStructChecked();
        YEmitter.Extra bonus = (YEmitter.Extra)emitterPtr.bonus;
        ((RubyObject)node).fastSetInstanceVariable("@emitter", emitter);
        if(!bonus.oid.isNil()) {
            ((RubyHash)bonus.data).fastASet(bonus.oid, node);
        }
    }

    // syck_out_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject emitter) {
        ((RubyObject)self).fastSetInstanceVariable("@emitter", emitter);
        return self;
    }

    // syck_out_map
    @JRubyMethod(required = 1, optional = 1, frame = true)
    public static IRubyObject map(IRubyObject self, IRubyObject[] args, Block block) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        IRubyObject type_id = args[0];
        IRubyObject style = args.length == 1 ? runtime.getNil() : args[1];
        IRubyObject map = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Map").callMethod(ctx, "new", new IRubyObject[]{type_id, RubyHash.newHash(runtime), style});
        outMark((IRubyObject)((RubyObject)self).fastGetInstanceVariable("@emitter"), map);
        block.yield(ctx, map);
        return map;
    }

    // syck_out_seq
    @JRubyMethod(required = 1, optional = 1, frame = true)
    public static IRubyObject seq(IRubyObject self, IRubyObject[] args, Block block) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        IRubyObject type_id = args[0];
        IRubyObject style = args.length == 1 ? runtime.getNil() : args[1];
        IRubyObject seq = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Seq").callMethod(ctx, "new", new IRubyObject[]{type_id, RubyArray.newArray(runtime), style});
        outMark((IRubyObject)((RubyObject)self).fastGetInstanceVariable("@emitter"), seq);
        block.yield(ctx, seq);
        return seq;
    }

    // syck_out_scalar
    @JRubyMethod(required = 2, optional = 1, frame = true)
    public static IRubyObject scalar(IRubyObject self, IRubyObject[] args, Block block) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        IRubyObject type_id = args[0];
        IRubyObject str = args[1];
        IRubyObject style = args.length == 2 ? runtime.getNil() : args[2];
        IRubyObject scalar = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Scalar").callMethod(ctx, "new", new IRubyObject[]{type_id, str, style});
        outMark((IRubyObject)((RubyObject)self).fastGetInstanceVariable("@emitter"), scalar);
        return scalar;
    }
}
