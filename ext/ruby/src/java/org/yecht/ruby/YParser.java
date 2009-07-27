package org.yecht.ruby;

import org.yecht.Parser;
import org.yecht.ParserInput;
import org.yecht.Pointer;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyNumeric;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;

public class YParser {
    public static class Extra {
        public IRubyObject data;
        public IRubyObject proc;
        public IRubyObject resolver;
        public boolean taint;
    }

    public static final ObjectAllocator Allocator = new ObjectAllocator() {
            // syck_parser_s_alloc
            public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                //                     System.err.println("ALLOCATING PARSER");
                Parser parser = Parser.newParser();
                parser.bonus = new Extra();
                IRubyObject pobj = runtime.newData(klass, parser);
                parser.setRootOnError(runtime.getNil());
                return pobj;
            }
        };

    // syck_parser_assign_io
    public static boolean assignIO(Ruby runtime, Parser parser, IRubyObject[] pport) {
        boolean taint = true;
        IRubyObject tmp, port = pport[0];
        if(!(tmp = port.checkStringType()).isNil()) {
            taint = port.isTaint();
            port = tmp;
            ByteList bl = ((RubyString)port).getByteList();
            parser.str(Pointer.create(bl.bytes, bl.begin), bl.realSize, null);
        } else if(port.respondsTo("read")) {
            if(port.respondsTo("binmode")) {
                port.callMethod(runtime.getCurrentContext(), "binmode");
            }
            parser.str(Pointer.empty(), 0, new RubyIoStrRead(port));
        } else {
            throw runtime.newTypeError("instance of IO needed");
        }
        pport[0] = port;
        return taint;
    }

    // syck_set_model
    public static void setModel(IRubyObject p, IRubyObject input, IRubyObject model) {
        Ruby runtime = p.getRuntime();
        Parser parser = (Parser)p.dataGetStructChecked();
        parser.handler(new RubyLoadHandler(runtime, ((YAMLExtra)runtime.getModule("YAML").dataGetStruct())));
        if(model == runtime.newSymbol("Generic")) {
            p.callMethod(runtime.getCurrentContext(), "set_resolver", ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("GenericResolver"));
        }
        parser.implicitTyping(true);
        parser.taguriExpansion(true);

        if(input.isNil()) {
            input = (IRubyObject)((RubyObject)p).fastGetInstanceVariable("@input");
        }

        if(input == runtime.newSymbol("bytecode")) {
            parser.setInputType(ParserInput.Bytecode_UTF8);
        } else {
            parser.setInputType(ParserInput.YAML_UTF8);
        }

        parser.errorHandler(new RubyErrHandler(runtime));
        parser.badAnchorHandler(new RubyBadAnchorHandler(runtime));
    }

    @JRubyMethod(optional = 1)
    public static IRubyObject initialize(IRubyObject self, IRubyObject[] args) {
        IRubyObject options = null;
        if(args.length == 0) {
            options = RubyHash.newHash(self.getRuntime());
        } else {
            options = args[0].convertToHash();
        }
        ((RubyObject)self).fastSetInstanceVariable("@options", options);
        ((RubyObject)self).fastSetInstanceVariable("@input", self.getRuntime().getNil());
        ((RubyObject)self).fastSetInstanceVariable("@resolver", self.getRuntime().getNil());

        return self;
    }
        
    // syck_parser_bufsize_set
    @JRubyMethod(name="bufsize=")
        public static IRubyObject bufsize_set(IRubyObject self, IRubyObject size) {
        if(size.respondsTo("to_i")) {
            int n = RubyNumeric.fix2int(size.callMethod(self.getRuntime().getCurrentContext(), "to_i"));
            Parser p = (Parser)self.dataGetStructChecked();
            p.bufsize = n;
        }
        return self;
    }        

    // syck_parser_bufsize_get
    @JRubyMethod
    public static IRubyObject bufsize(IRubyObject self) {
        Parser p = (Parser)self.dataGetStructChecked();
        return self.getRuntime().newFixnum(p.bufsize);
    }        
        
    // syck_parser_load
    @JRubyMethod(required = 1, optional = 1)
    public static IRubyObject load(IRubyObject self, IRubyObject[] args) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        IRubyObject port = args[0];
        IRubyObject proc = null;
        if(args.length > 1) {
            proc = args[1];
        } else {
            proc = runtime.getNil();
        }

        IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("input"));
        IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("Model"));

        Parser parser = (Parser)self.dataGetStructChecked();
        setModel(self, input, model);
            
        Extra bonus = (Extra)parser.bonus;
        bonus.taint = assignIO(runtime, parser, new IRubyObject[]{port});
        parser.setRootOnError(runtime.getNil());
        bonus.data = RubyHash.newHash(runtime);
        bonus.resolver = self.callMethod(ctx, "resolver");
        //             System.err.println("Parser resolver is : " + bonus.resolver);
        if(proc.isNil()) {
            bonus.proc = null;
        } else {
            bonus.proc = proc;
        }

        IRubyObject result = (IRubyObject)parser.parse();

        if(result == null) {
            result = runtime.getFalse();
        }

        return result;
    }

    // syck_parser_load_documents
    @JRubyMethod(frame=true)
    public static IRubyObject load_documents(IRubyObject self, IRubyObject port, Block proc) {
        Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();

        IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("input"));
        IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).op_aref(ctx, runtime.newSymbol("Model"));

        Parser parser = (Parser)self.dataGetStructChecked();
        setModel(self, input, model);

        Extra bonus = (Extra)parser.bonus;
        bonus.taint = assignIO(runtime, parser, new IRubyObject[]{port});
        parser.setRootOnError(runtime.getNil());
        bonus.resolver = self.callMethod(ctx, "resolver");
        bonus.proc = null;

        while(true) {
            bonus.data = RubyHash.newHash(runtime);
            IRubyObject v = (IRubyObject)parser.parse();
            if(parser.eof) {
                return runtime.getNil();
            }

            if(v == null) {
                v = runtime.getFalse();
            }
            
            proc.yield(ctx, v);
        }
    }

    // syck_parser_set_resolver
    @JRubyMethod
    public static IRubyObject set_resolver(IRubyObject self, IRubyObject resolver) {
        ((RubyObject)self).fastSetInstanceVariable("@resolver", resolver);
        return self;
    }        
}
