package org.yecht.ruby;

import org.yecht.Emitter;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyNumeric;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.TypeConverter;

public class YEmitter {
    public static class Extra {
        public IRubyObject oid;
        public IRubyObject data;
        public IRubyObject port;
    }

    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            // syck_emitter_s_alloc
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                //                     System.err.println("ALLOCATING EMITTER");
                Emitter emitter = new Emitter();
                emitter.bonus = new Extra();
                IRubyObject pobj = runtime.newData(klass, emitter);
                emitter.handler(new RubyEmitterHandler(runtime));
                emitter.outputHandler(new RubyOutputHandler(runtime));
                    
                ((RubyObject)pobj).fastSetInternalVariable("@out", ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Out").callMethod(runtime.getCurrentContext(), "new", pobj));
                return pobj;
            }
        };


    // syck_emitter_set_resolver
    @JRubyMethod
    public static IRubyObject set_resolver(IRubyObject self, IRubyObject resolver) {
        ((RubyObject)self).fastSetInternalVariable("@resolver", resolver);
        return self;
    }

    // syck_emitter_node_export
    @JRubyMethod
    public static IRubyObject node_export(IRubyObject self, IRubyObject node) {
        return node.callMethod(self.getRuntime().getCurrentContext(), "to_yaml", self);
    }

    // syck_emitter_reset
    @JRubyMethod(name = {"initialize", "reset"}, optional = 1)
    public static IRubyObject reset(IRubyObject self, IRubyObject[] args) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        Emitter emitter = (Emitter)self.dataGetStructChecked();
        Extra bonus = (Extra)emitter.bonus;
        bonus.oid = runtime.getNil();
        bonus.port = runtime.newString("");
        bonus.data = RubyHash.newHash(runtime);
            
        IRubyObject options = null;
        IRubyObject tmp;
        if(args.length == 1) {
            options = args[0];
            if(!(tmp = options.checkStringType()).isNil()) {
                bonus.port = tmp;
            } else if(options.respondsTo("write")) {
                bonus.port = options;
            } else {
                options = TypeConverter.convertToTypeWithCheck(options, runtime.getHash(), "to_hash");
                ((RubyObject)self).fastSetInternalVariable("@options", options);
            }
        } else {
            options = RubyHash.newHash(runtime);
            ((RubyObject)self).fastSetInternalVariable("@options", options);
        }


        emitter.headless = false;
        ((RubyObject)self).fastSetInternalVariable("@level", runtime.newFixnum(0));
        ((RubyObject)self).fastSetInternalVariable("@resolver", runtime.getNil());

        return self;
    }

    // syck_emitter_emit
    @JRubyMethod(optional = 1, frame = true)
    public static IRubyObject emit(IRubyObject self, IRubyObject[] _oid, Block proc) {
        Ruby runtime = self.getRuntime();
        int level = RubyNumeric.fix2int((IRubyObject)((RubyObject)self).fastGetInternalVariable("@level")) + 1;
        ((RubyObject)self).fastSetInternalVariable("@level", runtime.newFixnum(level));
        ThreadContext ctx = runtime.getCurrentContext();
        Emitter emitter = (Emitter)self.dataGetStructChecked();
        Extra bonus = (Extra)emitter.bonus;

        IRubyObject oid = _oid.length == 0 ? runtime.getNil() : _oid[0];
            
        bonus.oid = oid;
        IRubyObject symple;
        if(!oid.isNil() && bonus.data.callMethod(ctx, "has_key?", oid).isTrue()) {
            symple = ((RubyHash)bonus.data).op_aref(ctx, oid);
        } else {
            symple = proc.yield(ctx, (IRubyObject)((RubyObject)self).fastGetInternalVariable("@out"));
        }
        emitter.markNode(symple);

        level--;
        ((RubyObject)self).fastSetInternalVariable("@level", runtime.newFixnum(level));
        if(level == 0) {
            emitter.emit(symple);
            emitter.flush(0);
            return bonus.port;
        }
        return symple;
    }
}
