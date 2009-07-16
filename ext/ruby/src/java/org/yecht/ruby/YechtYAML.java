package org.yecht.ruby;

import org.yecht.BytecodeNodeHandler;
import org.yecht.Bytestring;
import org.yecht.Data;
import org.yecht.Emitter;
import org.yecht.EmitterHandler;
import org.yecht.IoStrRead;
import org.yecht.JechtIO;
import org.yecht.MapPart;
import org.yecht.Node;
import org.yecht.Parser;
import org.yecht.OutputHandler;
import org.yecht.Pointer;
import org.yecht.ImplicitScanner;
import org.yecht.MapStyle;
import org.yecht.SeqStyle;
import org.yecht.ScalarStyle;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyEnumerable;
import org.jruby.RubyHash;
import org.jruby.RubyModule;
import org.jruby.RubyNumeric;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.BlockCallback;
import org.jruby.runtime.ObjectSpace;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;
import org.jruby.util.TypeConverter;

public class YechtYAML {
    public static class RubyIoStrRead implements IoStrRead {
        private IRubyObject port;
        public RubyIoStrRead(IRubyObject port) {
            this.port = port;
        }

        // rb_syck_io_str_read
        public int read(Pointer buf, JechtIO.Str str, int max_size, int skip) {
            int len = 0;
            max_size -= skip;
            if(max_size <= 0) {
                max_size = 0;
            } else {
                IRubyObject src = port;
                IRubyObject n = RubyNumeric.int2fix(port.getRuntime(), max_size);
                IRubyObject str2 = src.callMethod(port.getRuntime().getCurrentContext(), "read", n);
                if(!str2.isNil()) {
                    ByteList res = str2.convertToString().getByteList();
                    len = res.realSize;
                    System.arraycopy(res.bytes, res.begin, buf.buffer, buf.start+skip, len);
                }
            }
            len =+ skip;
            buf.buffer[buf.start+len] = 0;
            return len;
        }
    }

    // yaml_org_handler
    public static boolean orgHandler(IRubyObject self, org.yecht.Node n, IRubyObject[] ref) {
        // TODO: implement
        return false;
    }

    // syck_set_model
    public static void setModel(IRubyObject p, IRubyObject input, IRubyObject model) {
        // TODO: implement
    }

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

    public static class Module {
        // rb_syck_compile
        @JRubyMethod(name = "compile", required = 1, module = true)
        public static IRubyObject compile(IRubyObject self, IRubyObject port) {
            Parser parser = Parser.newParser();
            boolean taint = assignIO(self.getRuntime(), parser, new IRubyObject[] {port});
            parser.handler(new BytecodeNodeHandler());
            parser.errorHandler(null);
            parser.implicitTyping(false);
            parser.taguriExpansion(false);
            long oid = parser.parse();
            Bytestring sav = (Bytestring)parser.lookupSym(oid);
            int len = Bytestring.strlen(sav.buffer);
            ByteList bl = new ByteList(new byte[len+2], false);
            bl.append(sav.buffer, 0, len);
            bl.append('D');
            bl.append('\n');
            IRubyObject iro = RubyString.newStringLight(self.getRuntime(), bl);
            if(taint) iro.setTaint(true);
            return iro;
        }
    }

    public static class Resolver {
        // syck_const_find
        public static IRubyObject const_find(IRubyObject self, IRubyObject const_name) {
            RubyModule tclass = self.getRuntime().getObject();
            RubyArray tparts = ((RubyString)const_name).split(self.getRuntime().getCurrentContext(), self.getRuntime().newString("::"));
            for(int i=0; i < tparts.getLength(); i++) {
                String tpart = tparts.entry(i).toString();
                if(!tclass.hasConstant(tpart)) {
                    return self.getRuntime().getNil();
                }
                tclass = (RubyModule)tclass.getConstant(tpart);
            }
            return tclass;
        }

        // syck_resolver_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self) {
            self.getInstanceVariables().setInstanceVariable("@tags", RubyHash.newHash(self.getRuntime()));
            return self;
        }

        // syck_resolver_add_type
        @JRubyMethod
        public static IRubyObject add_type(IRubyObject self, IRubyObject taguri, IRubyObject cls) {
            IRubyObject tags = self.callMethod(self.getRuntime().getCurrentContext(), "tags");
            ((RubyHash)tags).fastASet(taguri, cls);
            return self.getRuntime().getNil();
        }        

        // syck_resolver_use_types_at
        @JRubyMethod
        public static IRubyObject use_types_at(IRubyObject self, IRubyObject hsh) {
            self.getInstanceVariables().setInstanceVariable("@tags", hsh);
            return self.getRuntime().getNil();
        }        

        // syck_resolver_detect_implicit
        @JRubyMethod
        public static IRubyObject detect_implicit(IRubyObject self, IRubyObject val) {
            return RubyString.newEmptyString(self.getRuntime());
        }        

        // syck_resolver_transfer
        @JRubyMethod
        public static IRubyObject transfer(IRubyObject self, IRubyObject type, IRubyObject val) {
            final Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            if(type.isNil() || type.convertToString().getByteList().realSize == 0) {
                type = self.callMethod(ctx, "detect_implicit", val);
            }
            
            if(!(type.isNil() || type.convertToString().getByteList().realSize == 0)) {
                IRubyObject colon = runtime.newString(":");
                IRubyObject tags = self.callMethod(ctx, "tags");
                IRubyObject target_class = ((RubyHash)tags).fastARef(type);
                IRubyObject subclass = target_class;
                IRubyObject obj = runtime.getNil();
                
                if(target_class.isNil()) {
                    RubyArray subclass_parts = runtime.newArray();
                    RubyArray parts = ((RubyString)type).split(ctx, colon);
                    while(parts.getLength() > 1) {
                        subclass_parts.unshift(parts.pop(ctx));
                        IRubyObject partial = parts.join(ctx, colon);
                        target_class = ((RubyHash)tags).fastARef(partial);
                        if(target_class.isNil()) {
                            ((RubyString)partial).append(colon);
                            target_class = ((RubyHash)tags).fastARef(partial);
                        }
                        if(!target_class.isNil()) {
                            subclass = target_class;
                            if(subclass_parts.getLength() > 0 && target_class.respondsTo("yaml_tag_subclasses?") && target_class.callMethod(ctx, "yaml_tag_subclasses?").isTrue()) {
                                subclass = subclass_parts.join(ctx, colon);
                                subclass = target_class.callMethod(ctx, "yaml_tag_read_class", subclass);
                                IRubyObject subclass_v = const_find(self, subclass);
                                if(subclass_v != runtime.getNil()) {
                                    subclass = subclass_v;
                                } else if(target_class == runtime.getObject() && subclass_v == runtime.getNil()) {
                                    target_class = ((RubyModule)runtime.getModule("YAML")).getConstant("Object");
                                    type = subclass;
                                    subclass = target_class;
                                } else {
                                    throw runtime.newTypeError("invalid subclass");
                                }
                            }
                            break;
                        }
                    }
                }

                if(target_class.respondsTo("call")) {
                    obj = target_class.callMethod(ctx, "call", new IRubyObject[]{type, val});
                } else {
                    if(target_class.respondsTo("yaml_new")) {
                        obj = target_class.callMethod(ctx, "yaml_new", new IRubyObject[]{subclass, type, val});
                    } else if(!target_class.isNil()) {
                        if(subclass == runtime.getBignum()) {
                            obj = RubyNumeric.str2inum(runtime, val.convertToString(), 10);
                        } else {
                            obj = ((RubyClass)subclass).allocate();
                        }
                        
                        if(obj.respondsTo("yaml_initialize")) {
                            obj.callMethod(ctx, "yaml_initialize", new IRubyObject[]{type, val});
                        } else if(!obj.isNil() && val instanceof RubyHash) {
                            final IRubyObject _obj = obj;
                            RubyEnumerable.callEach(runtime, ctx, val, new BlockCallback() {
                                    public IRubyObject call(ThreadContext _ctx, IRubyObject[] largs, Block blk) {
                                        IRubyObject ivname = ((RubyArray)largs[0]).entry(0);
                                        String ivn = "@" + ivname.convertToString().toString();
                                        _obj.getInstanceVariables().setInstanceVariable(ivn, ((RubyArray)largs[0]).entry(1));
                                        return runtime.getNil();
                                    }
                                });
                        }
                    } else {
                        RubyArray parts = ((RubyString)type).split(ctx, colon);
                        IRubyObject scheme = parts.shift(ctx);
                        if(scheme.convertToString().toString().equals("x-private")) {
                            IRubyObject name = parts.join(ctx, colon);
                            obj = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("PrivateType").callMethod(ctx, "new", new IRubyObject[]{name, val});
                        } else {
                            IRubyObject domain = parts.shift(ctx);
                            IRubyObject name = parts.join(ctx, colon);
                            obj = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("DomainType").callMethod(ctx, "new", new IRubyObject[]{domain, name, val});
                        }
                    }
                }
                
                val = obj;
            }

            return val;

        }        

        // syck_resolver_node_import
        @JRubyMethod
        public static IRubyObject node_import(IRubyObject self, IRubyObject node) {
            final Ruby runtime = self.getRuntime();
            final ThreadContext ctx = runtime.getCurrentContext();
            org.yecht.Node n = (org.yecht.Node)node.dataGetStruct();
            ObjectSpace os = runtime.getObjectSpace();
            IRubyObject obj = null;
            switch(n.kind) {
            case Str:
                Data.Str dd = (Data.Str)n.data;
                obj = RubyString.newStringShared(runtime, dd.ptr.buffer, dd.ptr.start, dd.len);
                break;
            case Seq:
                Data.Seq ds = (Data.Seq)n.data;
                obj = RubyArray.newArray(runtime, ds.idx);
                for(int i = 0; i < ds.idx; i++) {
                    ((RubyArray)obj).store(i, os.id2ref(n.seqRead(i)));
                }
                break;
            case Map:
                Data.Map dm = (Data.Map)n.data;
                obj = RubyHash.newHash(runtime);
                RubyClass cMergeKey = (RubyClass)(((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("MergeKey"));
                RubyClass cDefaultKey = (RubyClass)(((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("DefaultKey"));
                RubyClass cHash = runtime.getHash();
                RubyClass cArray = runtime.getArray();
                
                for(int i = 0; i < dm.idx; i++) {
                    IRubyObject k = os.id2ref(n.mapRead(MapPart.Key, i));
                    IRubyObject v = os.id2ref(n.mapRead(MapPart.Value, i));
                    boolean skip_aset = false;
                    
                    if(cMergeKey.isInstance(k)) {
                        if(cHash.isInstance(v)) {
                            IRubyObject dup = v.callMethod(ctx, "dup");
                            dup.callMethod(ctx, "update", obj);
                            obj = dup;
                            skip_aset = true;
                        } else if(cArray.isInstance(v)) {
                            IRubyObject end = ((RubyArray)v).pop(ctx);
                            if(cHash.isInstance(end)) {
                                final IRubyObject dup = end.callMethod(ctx, "dup");
                                v = ((RubyArray)v).reverse();
                                ((RubyArray)v).append(obj);

                                RubyEnumerable.callEach(runtime, ctx, v, new BlockCallback() {
                                        // syck_merge_i
                                        public IRubyObject call(ThreadContext _ctx, IRubyObject[] largs, Block blk) {
                                            IRubyObject entry = largs[0];
                                            IRubyObject tmp = null;
                                            if(!(tmp = TypeConverter.convertToTypeWithCheck(entry, runtime.getHash(), "to_hash")).isNil()) {
                                                dup.callMethod(_ctx, "update", tmp);
                                            }
                                            return runtime.getNil();
                                        }
                                    });

                                obj = dup;
                                skip_aset = true;
                            }
                        }
                    } else if(cDefaultKey.isInstance(k)) {
                        obj.callMethod(ctx, "default=", v);
                        skip_aset = true;
                    }
                    
                    if(!skip_aset) {
                        ((RubyHash)obj).fastASet(k, v);
                    }
                }
                break;
            }
            
            if(n.type_id != null) {
                obj = self.callMethod(ctx, "transfer", new IRubyObject[]{runtime.newString(n.type_id), obj});
            }

            return obj;
        }

        // syck_resolver_tagurize
        @JRubyMethod
        public static IRubyObject tagurize(IRubyObject self, IRubyObject val) {
            IRubyObject tmp = val.checkStringType();
            if(!tmp.isNil()) {
                String taguri = ImplicitScanner.typeIdToUri(tmp.toString());
                val = self.getRuntime().newString(taguri);
            }
            return val;
        }        
    }

    public static class DefaultResolver {
        // syck_defaultresolver_node_import
        @JRubyMethod
        public static IRubyObject node_import(IRubyObject self, IRubyObject node) {
            org.yecht.Node n = (org.yecht.Node)node.dataGetStruct();
            IRubyObject[] _obj = new IRubyObject[]{null};
            if(!orgHandler(self, n, _obj)) {
                _obj[0] = self.callMethod(self.getRuntime().getCurrentContext(), "transfer", new IRubyObject[]{self.getRuntime().newString(n.type_id), _obj[0]});
            }
            return _obj[0];
        }        

        // syck_defaultresolver_detect_implicit
        @JRubyMethod
        public static IRubyObject detect_implicit(IRubyObject self, IRubyObject val) {
            IRubyObject tmp = TypeConverter.convertToTypeWithCheck(val, self.getRuntime().getString(), "to_str");
            if(!tmp.isNil()) {
                ByteList bl = ((RubyString)tmp).getByteList();
                String type_id = ImplicitScanner.matchImplicit(Pointer.create(bl.bytes, bl.begin), bl.realSize);
                return self.getRuntime().newString(type_id);
            }
            return RubyString.newEmptyString(self.getRuntime());
        }        
    }

    public static class GenericResolver {
        // syck_genericresolver_node_import
        @JRubyMethod
        public static IRubyObject node_import(IRubyObject self, IRubyObject node) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            org.yecht.Node n = (org.yecht.Node)node.dataGetStruct();
            ObjectSpace os = runtime.getObjectSpace();
            IRubyObject t = runtime.getNil();
            IRubyObject obj = t;
            IRubyObject v = t;
            IRubyObject style = t;

            if(n.type_id != null) {
                t = runtime.newString(n.type_id);
            }

            switch(n.kind) {
            case Str:
                Data.Str dd = (Data.Str)n.data;
                v = RubyString.newStringShared(runtime, dd.ptr.buffer, dd.ptr.start, dd.len);
                switch(dd.style) {
                case OneQuote:
                    style = runtime.newSymbol("quote1");
                    break;
                case TwoQuote:
                    style = runtime.newSymbol("quote2");
                    break;
                case Fold:
                    style = runtime.newSymbol("fold");
                    break;
                case Literal:
                    style = runtime.newSymbol("literal");
                    break;
                case Plain:
                    style = runtime.newSymbol("plain");
                    break;
                }
                obj = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Scalar").callMethod(ctx, "new", new IRubyObject[]{t, v, style});
                break;
            case Seq:
                v = RubyArray.newArray(runtime, n.seqCount());
                for(int i = 0; i < n.seqCount(); i++) {
                    ((RubyArray)v).store(i, os.id2ref(n.seqRead(i)));
                }
                if(((Data.Seq)n.data).style == SeqStyle.Inline) {
                    style = runtime.newSymbol("inline");
                }
                obj = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Seq").callMethod(ctx, "new", new IRubyObject[]{t, v, style});
                obj.getInstanceVariables().setInstanceVariable("@kind", runtime.newSymbol("seq"));
                break;
            case Map:
                v = RubyHash.newHash(runtime);
                for(int i = 0; i < n.mapCount(); i++) {
                    ((RubyHash)v).fastASet(os.id2ref(n.mapRead(MapPart.Key, i)), os.id2ref(n.mapRead(MapPart.Value, i)));
                }
                if(((Data.Map)n.data).style == MapStyle.Inline) {
                    style = runtime.newSymbol("inline");
                }
                obj = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Map").callMethod(ctx, "new", new IRubyObject[]{t, v, style});
                obj.getInstanceVariables().setInstanceVariable("@kind", runtime.newSymbol("map"));
                break;
            }

            return obj;
        }        
    }

    public static class YParser {
        public static class Extra {
            public IRubyObject data;
            public IRubyObject proc;
            public IRubyObject resolver;
            public boolean taint;
        }

        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_parser_s_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    Parser parser = Parser.newParser();
                    parser.bonus = new Extra();
                    IRubyObject pobj = new RubyObject(runtime, klass);
                    pobj.dataWrapStruct(parser);
                    parser.setRootOnError(runtime.getObjectSpace().idOf(runtime.getNil()));
                    return pobj;
                }
            };

        @JRubyMethod(optional = 1)
        public static IRubyObject initialize(IRubyObject self, IRubyObject[] args) {
            IRubyObject options = null;
            if(args.length == 0) {
                options = RubyHash.newHash(self.getRuntime());
            } else {
                options = args[0].convertToHash();
            }
            self.getInstanceVariables().setInstanceVariable("@options", options);
            self.getInstanceVariables().setInstanceVariable("@input", self.getRuntime().getNil());
            self.getInstanceVariables().setInstanceVariable("@resolver", self.getRuntime().getNil());

            return self;
        }
        
        // syck_parser_bufsize_set
        @JRubyMethod(name="bufsize=")
        public static IRubyObject bufsize_set(IRubyObject self, IRubyObject size) {
            if(size.respondsTo("to_i")) {
                int n = RubyNumeric.fix2int(size.callMethod(self.getRuntime().getCurrentContext(), "to_i"));
                Parser p = (Parser)self.dataGetStruct();
                p.bufsize = n;
            }
            return self;
        }        

        // syck_parser_bufsize_get
        @JRubyMethod
        public static IRubyObject bufsize(IRubyObject self) {
            Parser p = (Parser)self.dataGetStruct();
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

            IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).fastARef(runtime.newSymbol("input"));
            IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).fastARef(runtime.newSymbol("Model"));

            Parser parser = (Parser)self.dataGetStruct();
            setModel(self, input, model);
            
            Extra bonus = (Extra)parser.bonus;
            bonus.taint = assignIO(runtime, parser, new IRubyObject[]{port});
            bonus.data = RubyHash.newHash(runtime);
            bonus.resolver = self.callMethod(ctx, "resolver");
            if(proc.isNil()) {
                bonus.proc = null;
            } else {
                bonus.proc = proc;
            }

            return (IRubyObject)parser.lookupSym(parser.parse());
        }

        // syck_parser_load_documents
        @JRubyMethod(frame=true)
        public static IRubyObject load_documents(IRubyObject self, IRubyObject port, Block proc) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();

            IRubyObject input = ((RubyHash)self.callMethod(ctx, "options")).fastARef(runtime.newSymbol("input"));
            IRubyObject model = ((RubyHash)self.callMethod(ctx, "options")).fastARef(runtime.newSymbol("Model"));

            Parser parser = (Parser)self.dataGetStruct();
            setModel(self, input, model);

            Extra bonus = (Extra)parser.bonus;
            bonus.taint = assignIO(runtime, parser, new IRubyObject[]{port});
            bonus.resolver = self.callMethod(ctx, "resolver");
            bonus.proc = null;

            while(true) {
                bonus.data = RubyHash.newHash(runtime);
                IRubyObject v = (IRubyObject)parser.lookupSym(parser.parse());
                if(parser.eof) {
                    return runtime.getNil();
                }

                proc.yield(ctx, v);
            }
        }

        // syck_parser_set_resolver
        @JRubyMethod
        public static IRubyObject set_resolver(IRubyObject self, IRubyObject resolver) {
            self.getInstanceVariables().setInstanceVariable("@resolver", resolver);
            return self;
        }        
    }

    public static class Node {
        // syck_node_init_copy
        @JRubyMethod
        public static IRubyObject initialize_copy(IRubyObject copy, IRubyObject orig) {
            if(copy == orig) {
                return copy;
            }

            if(orig.getClass() != RubyObject.class) {
                throw copy.getRuntime().newTypeError("wrong argument type");
            }

            org.yecht.Node orig_n = (org.yecht.Node)orig.dataGetStruct();
            org.yecht.Node copy_n = (org.yecht.Node)copy.dataGetStruct();

            copy_n.id = orig_n.id;
            copy_n.kind = orig_n.kind;
            copy_n.type_id = orig_n.type_id;
            copy_n.anchor = orig_n.anchor;
            copy_n.data = orig_n.data.copy();
            copy_n.shortcut = orig_n.shortcut;

            return copy;
        }        

        // syck_node_type_id_set
        @JRubyMethod(name = "type_id=")
        public static IRubyObject set_type_id(IRubyObject self, IRubyObject type_id) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            if(!type_id.isNil()) {
                node.type_id = type_id.convertToString().toString();
            }
            self.getInstanceVariables().setInstanceVariable("@type_id", type_id);
            return type_id;
        }        

        // syck_node_transform
        @JRubyMethod
        public static IRubyObject transform(IRubyObject self) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            org.yecht.Node orig_n = (org.yecht.Node)self.dataGetStruct();
            IRubyObject t = new RubyObject(runtime, self.getType());
            org.yecht.Node n = null;
            ObjectSpace os = runtime.getObjectSpace();

            switch(orig_n.kind) {
            case Map:
                n = org.yecht.Node.allocMap();
                t.dataWrapStruct(n);
                Data.Map dm = (Data.Map)orig_n.data;
                for(int i=0; i < dm.idx; i++) {
                    n.mapAdd(os.idOf(os.id2ref(orig_n.mapRead(MapPart.Key, i)).callMethod(ctx, "transform")),
                             os.idOf(os.id2ref(orig_n.mapRead(MapPart.Value, i)).callMethod(ctx, "transform")));
                }
                break;
            case Seq:
                n = org.yecht.Node.allocSeq();
                t.dataWrapStruct(n);
                Data.Seq ds = (Data.Seq)orig_n.data;
                for(int i=0; i < ds.idx; i++) {
                    n.seqAdd(os.idOf(os.id2ref(orig_n.seqRead(i)).callMethod(ctx, "transform")));
                }
                break;
            case Str:
                Data.Str dss = (Data.Str)orig_n.data;
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

            n.id = os.idOf(t);
            n.shortcut = t;
            return ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("DefaultResolver").callMethod(ctx, "node_import", t);
        }
    }

    public static class Scalar {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_scalar_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    org.yecht.Node node = org.yecht.Node.allocStr();
                    IRubyObject obj = new RubyObject(runtime, klass);
                    obj.dataWrapStruct(node);
                    node.id = runtime.getObjectSpace().idOf(obj);
                    node.shortcut = obj;
                    return obj;
                }
            };

        // syck_scalar_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            self.getInstanceVariables().setInstanceVariable("@kind", runtime.newSymbol("scalar"));
            self.callMethod(ctx, "type_id=", type_id);
            self.callMethod(ctx, "value=", val);
            self.callMethod(ctx, "style=", style);
            return self;
        }

        // syck_scalar_style_set
        @JRubyMethod(name = "style=")
        public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            Data.Str ds = (Data.Str)node.data;
            if(style.isNil()) {
                ds.style = ScalarStyle.None;
            } else if(style == runtime.newSymbol("quote1")) {
                ds.style = ScalarStyle.OneQuote;
            } else if(style == runtime.newSymbol("quote2")) {
                ds.style = ScalarStyle.TwoQuote;
            } else if(style == runtime.newSymbol("fold")) {
                ds.style = ScalarStyle.Fold;
            } else if(style == runtime.newSymbol("literal")) {
                ds.style = ScalarStyle.Literal;
            } else if(style == runtime.newSymbol("plain")) {
                ds.style = ScalarStyle.Plain;
            }
            self.getInstanceVariables().setInstanceVariable("@style", style);
            return self;
        }

        // syck_scalar_value_set
        @JRubyMethod(name = "value=")
        public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            Data.Str ds = (Data.Str)node.data;
            
            val = val.convertToString();
            ByteList bl = ((RubyString)val).getByteList();
            byte[] bss = new byte[bl.realSize];
            System.arraycopy(bl.bytes, bl.begin, bss, 0, bss.length);
            ds.ptr = Pointer.create(bss, 0);
            ds.len = bss.length;
            ds.style = ScalarStyle.None;
            self.getInstanceVariables().setInstanceVariable("@value", val);
            return val;
        }
    }

    public static class Seq {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_seq_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    org.yecht.Node node = org.yecht.Node.allocSeq();
                    IRubyObject obj = new RubyObject(runtime, klass);
                    obj.dataWrapStruct(node);
                    node.id = runtime.getObjectSpace().idOf(obj);
                    node.shortcut = obj;
                    return obj;
                }
            };

        // syck_seq_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            self.getInstanceVariables().setInstanceVariable("@kind", runtime.newSymbol("seq"));
            self.callMethod(ctx, "type_id=", type_id);
            self.callMethod(ctx, "value=", val);
            self.callMethod(ctx, "style=", style);
            return self;
        }

        // syck_seq_value_set
        @JRubyMethod(name = "value=")
        public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            ObjectSpace os = runtime.getObjectSpace();

            val = val.checkArrayType();
            if(!val.isNil()) {
                node.seqEmpty();
                Data.Seq ds = (Data.Seq)node.data;
                for(int i=0; i<((RubyArray)val).getLength(); i++) {
                    node.seqAdd(os.idOf(((RubyArray)val).entry(i)));
                }
            }

            self.getInstanceVariables().setInstanceVariable("@value", val);
            return val;
        }

        // syck_seq_style_set
        @JRubyMethod(name = "style=")
        public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            Data.Seq ds = (Data.Seq)node.data;
            if(style == runtime.newSymbol("inline")) {
                ds.style = SeqStyle.Inline;
            } else {
                ds.style = SeqStyle.None;
            }

            self.getInstanceVariables().setInstanceVariable("@style", style);
            return self;
        }

        // syck_seq_add_m
        @JRubyMethod
        public static IRubyObject add(IRubyObject self, IRubyObject val) {
            IRubyObject emitter = self.getInstanceVariables().getInstanceVariable("@emitter");
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            if(emitter.respondsTo("node_export")) {
                val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
            }
            node.seqAdd(self.getRuntime().getObjectSpace().idOf(val));
            ((RubyArray)self.getInstanceVariables().getInstanceVariable("@value")).append(val);
            return self;
        }
    }

    public static class Map {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_map_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    org.yecht.Node node = org.yecht.Node.allocMap();
                    IRubyObject obj = new RubyObject(runtime, klass);
                    obj.dataWrapStruct(node);
                    node.id = runtime.getObjectSpace().idOf(obj);
                    node.shortcut = obj;
                    return obj;
                }
            };

        // syck_map_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val, IRubyObject style) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            ObjectSpace os = runtime.getObjectSpace();
            Data.Map ds = (Data.Map)node.data;

            if(!val.isNil()) {
                IRubyObject hsh = TypeConverter.convertToTypeWithCheck(val, runtime.getHash(), "to_hash");

                if(hsh.isNil()) {
                    throw runtime.newTypeError("wrong argument type");
                }
                IRubyObject keys = hsh.callMethod(ctx, "keys");
                for(int i = 0; i < ((RubyArray)keys).getLength(); i++) {
                    IRubyObject key = ((RubyArray)keys).entry(i);
                    node.mapAdd(os.idOf(key), os.idOf(((RubyHash)hsh).fastARef(key)));
                }
            }

            self.getInstanceVariables().setInstanceVariable("@kind", runtime.newSymbol("seq")); // NOT A TYPO - Syck does the same
            self.callMethod(ctx, "type_id=", type_id);
            self.callMethod(ctx, "value=", val);
            self.callMethod(ctx, "style=", style);
            return self;
        }

        // syck_map_value_set
        @JRubyMethod(name = "value=")
        public static IRubyObject value_set(IRubyObject self, IRubyObject val) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            ObjectSpace os = runtime.getObjectSpace();
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
                    node.mapAdd(os.idOf(key), os.idOf(((RubyHash)hsh).fastARef(key)));
                }
            }

            self.getInstanceVariables().setInstanceVariable("@value", val);
            return val;
        }

        // syck_map_add_m
        @JRubyMethod
        public static IRubyObject add(IRubyObject self, IRubyObject key, IRubyObject val) {
            IRubyObject emitter = self.getInstanceVariables().getInstanceVariable("@emitter");
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            if(emitter.respondsTo("node_export")) {
                key = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", key);
                val = emitter.callMethod(self.getRuntime().getCurrentContext(), "node_export", val);
            }
            ObjectSpace os = self.getRuntime().getObjectSpace();
            node.mapAdd(os.idOf(key), os.idOf(val));
            ((RubyHash)self.getInstanceVariables().getInstanceVariable("@value")).fastASet(key, val);
            return self;
        }

        // syck_map_style_set
        @JRubyMethod(name = "style=")
        public static IRubyObject style_set(IRubyObject self, IRubyObject style) {
            org.yecht.Node node = (org.yecht.Node)self.dataGetStruct();
            Ruby runtime = self.getRuntime();
            Data.Map ds = (Data.Map)node.data;
            if(style == runtime.newSymbol("inline")) {
                ds.style = MapStyle.Inline;
            } else {
                ds.style = MapStyle.None;
            }

            self.getInstanceVariables().setInstanceVariable("@style", style);
            return self;
        }
    }

    public static class PrivateType {
        // syck_privatetype_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject type_id, IRubyObject val) {
            self.getInstanceVariables().setInstanceVariable("@type_id", type_id);
            self.getInstanceVariables().setInstanceVariable("@value", val);
            return self;
        }
    }

    public static class DomainType {
        // syck_domaintype_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject domain, IRubyObject type_id, IRubyObject val) {
            self.getInstanceVariables().setInstanceVariable("@domain", domain);
            self.getInstanceVariables().setInstanceVariable("@type_id", type_id);
            self.getInstanceVariables().setInstanceVariable("@value", val);
            return self;
        }
    }

    public static class YObject {
        // syck_yobject_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject klass, IRubyObject ivars) {
            self.getInstanceVariables().setInstanceVariable("@class", klass);
            self.getInstanceVariables().setInstanceVariable("@ivars", ivars);
            return self;
        }

        // syck_yobject_initialize
        @JRubyMethod
        public static IRubyObject yaml_initialize(IRubyObject self, IRubyObject klass, IRubyObject ivars) {
            self.getInstanceVariables().setInstanceVariable("@class", klass);
            self.getInstanceVariables().setInstanceVariable("@ivars", ivars);
            return self;
        }
    }

    public static class BadAlias {
        // syck_badalias_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject val) {
            self.getInstanceVariables().setInstanceVariable("@name", val);
            return self;
        }

        // syck_badalias_cmp
        @JRubyMethod(name = "<=>")
        public static IRubyObject cmp(IRubyObject alias1, IRubyObject alias2) {
            IRubyObject str1 = alias1.getInstanceVariables().getInstanceVariable("@name");
            IRubyObject str2 = alias2.getInstanceVariables().getInstanceVariable("@name");
            return str1.callMethod(alias1.getRuntime().getCurrentContext(), "<=>", str2);
        }
    }

    public static class Out {
        // syck_out_initialize
        @JRubyMethod
        public static IRubyObject initialize(IRubyObject self, IRubyObject emitter) {
            self.getInstanceVariables().setInstanceVariable("@emitter", emitter);
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
            block.yield(ctx, seq);
            return seq;
        }

        // syck_out_str
        @JRubyMethod(required = 2, optional = 1, frame = true)
        public static IRubyObject str(IRubyObject self, IRubyObject[] args, Block block) {
            Ruby runtime = self.getRuntime();
            ThreadContext ctx = runtime.getCurrentContext();
            IRubyObject type_id = args[0];
            IRubyObject str = args[1];
            IRubyObject style = args.length == 2 ? runtime.getNil() : args[2];
            IRubyObject scalar = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Scalar").callMethod(ctx, "new", new IRubyObject[]{type_id, str, style});
            return scalar;
        }
    }

    public static class RubyEmitterHandler implements EmitterHandler { 
        private Ruby runtime;

        public RubyEmitterHandler(Ruby runtime) {
            this.runtime = runtime;
        }

        // rb_syck_emitter_handler
        public void handle(Emitter e, long data) {
            // TODO: implement
        }
    }

    public static class RubyOutputHandler implements OutputHandler {
        private Ruby runtime;

        public RubyOutputHandler(Ruby runtime) {
            this.runtime = runtime;
        }

        // rb_syck_output_handler
        public void handle(Emitter e, byte[] str, int len) {
            // TODO: implement
        }
    }

    public static class YEmitter {
        public static class Extra {
            public IRubyObject oid;
            public IRubyObject data;
            public IRubyObject port;
        }

        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_emitter_s_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    Emitter emitter = new Emitter();
                    emitter.bonus = new Extra();
                    IRubyObject pobj = new RubyObject(runtime, klass);
                    pobj.dataWrapStruct(emitter);
                    emitter.handler(new RubyEmitterHandler(runtime));
                    emitter.outputHandler(new RubyOutputHandler(runtime));
                    
                    pobj.getInstanceVariables().setInstanceVariable("@out", ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Out").callMethod(runtime.getCurrentContext(), "new", pobj));
                    return pobj;
                }
            };


        // syck_emitter_set_resolver
        @JRubyMethod
        public static IRubyObject set_resolver(IRubyObject self, IRubyObject resolver) {
            self.getInstanceVariables().setInstanceVariable("@resolver", resolver);
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
            Emitter emitter = (Emitter)self.dataGetStruct();
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
                    self.getInstanceVariables().setInstanceVariable("@options", options);
                }
            } else {
                options = RubyHash.newHash(runtime);
                self.getInstanceVariables().setInstanceVariable("@options", options);
            }


            emitter.headless = false;
            self.getInstanceVariables().setInstanceVariable("@level", runtime.newFixnum(0));
            self.getInstanceVariables().setInstanceVariable("@resolver", runtime.getNil());

            return self;
        }


//TODO:     rb_define_method( cEmitter, "emit", syck_emitter_emit, -1 );
    }
}

