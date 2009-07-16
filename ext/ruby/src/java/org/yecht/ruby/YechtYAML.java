package org.yecht.ruby;

import org.yecht.BytecodeNodeHandler;
import org.yecht.Bytestring;
import org.yecht.Data;
import org.yecht.IoStrRead;
import org.yecht.JechtIO;
import org.yecht.MapPart;
import org.yecht.Node;
import org.yecht.Parser;
import org.yecht.Pointer;
import org.yecht.ImplicitScanner;

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
    public static int orgHandler(Node n, IRubyObject ref) {
        // TODO: implement
        return -1;
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
            Parser parser = n.parser;
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
                    ((RubyArray)obj).store(i, (IRubyObject)parser.lookupSym(n.seqRead(i)));
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
                    IRubyObject k = (IRubyObject)parser.lookupSym(n.mapRead(MapPart.Key, i));
                    IRubyObject v = (IRubyObject)parser.lookupSym(n.mapRead(MapPart.Value, i));
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
//     rb_define_singleton_method( oDefaultResolver, "node_import", syck_defaultresolver_node_import, 1 );
//     rb_define_singleton_method( oDefaultResolver, "detect_implicit", syck_defaultresolver_detect_implicit, 1 );
    }

    public static class GenericResolver {
//     rb_define_singleton_method( oGenericResolver, "node_import", syck_genericresolver_node_import, 1 );
    }

    public static class YParser {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_parser_s_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    // TODO: implement
                    return null;
                }
            };

//     rb_define_method(cParser, "initialize", syck_parser_initialize, -1 );
//     rb_define_method(cParser, "bufsize=", syck_parser_bufsize_set, 1 );
//     rb_define_method(cParser, "bufsize", syck_parser_bufsize_get, 0 );
//     rb_define_method(cParser, "load", syck_parser_load, -1);
//     rb_define_method(cParser, "load_documents", syck_parser_load_documents, -1);
//     rb_define_method(cParser, "set_resolver", syck_parser_set_resolver, 1);
    }

    public static class Node {
//     rb_define_method( cNode, "initialize_copy", syck_node_init_copy, 1 );
//     rb_define_method( cNode, "type_id=", syck_node_type_id_set, 1 );
//     rb_define_method( cNode, "transform", syck_node_transform, 0);
    }

    public static class Scalar {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_scalar_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    // TODO: implement
                    return null;
                }
            };

//     rb_define_method( cScalar, "initialize", syck_scalar_initialize, 3 );
//     rb_define_method( cScalar, "value=", syck_scalar_value_set, 1 );
//     rb_define_method( cScalar, "style=", syck_scalar_style_set, 1 );
    }

    public static class Seq {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_seq_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    // TODO: implement
                    return null;
                }
            };

//     rb_define_method( cSeq, "initialize", syck_seq_initialize, 3 );
//     rb_define_method( cSeq, "value=", syck_seq_value_set, 1 );
//     rb_define_method( cSeq, "add", syck_seq_add_m, 1 );
//     rb_define_method( cSeq, "style=", syck_seq_style_set, 1 );
    }

    public static class Map {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_map_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    // TODO: implement
                    return null;
                }
            };

//     rb_define_method( cMap, "initialize", syck_map_initialize, 3 );
//     rb_define_method( cMap, "value=", syck_map_value_set, 1 );
//     rb_define_method( cMap, "add", syck_map_add_m, 2 );
//     rb_define_method( cMap, "style=", syck_map_style_set, 1 );
    }

    public static class PrivateType {
//     rb_define_method( cPrivateType, "initialize", syck_privatetype_initialize, 2);
    }

    public static class DomainType {
//     rb_define_method( cDomainType, "initialize", syck_domaintype_initialize, 3);
    }

    public static class YObject {
//     rb_define_method( cYObject, "initialize", syck_yobject_initialize, 2);
//     rb_define_method( cYObject, "yaml_initialize", syck_yobject_initialize, 2);
    }

    public static class BadAlias {
//     rb_define_method( cBadAlias, "initialize", syck_badalias_initialize, 1);
//     rb_define_method( cBadAlias, "<=>", syck_badalias_cmp, 1);
    }

    public static class Out {
//     rb_define_method( cOut, "initialize", syck_out_initialize, 1 );
//     rb_define_method( cOut, "map", syck_out_map, -1 );
//     rb_define_method( cOut, "seq", syck_out_seq, -1 );
//     rb_define_method( cOut, "scalar", syck_out_scalar, -1 );
    }

    public static class Emitter {
        public static final ObjectAllocator Allocator = new ObjectAllocator() {
                // syck_emitter_s_alloc
                public IRubyObject allocate(Ruby runtime, RubyClass klass) {
                    // TODO: implement
                    return null;
                }
            };

//     rb_define_method( cEmitter, "initialize", syck_emitter_reset, -1 );
//     rb_define_method( cEmitter, "reset", syck_emitter_reset, -1 );
//     rb_define_method( cEmitter, "emit", syck_emitter_emit, -1 );
//     rb_define_method( cEmitter, "set_resolver", syck_emitter_set_resolver, 1);
//     rb_define_method( cEmitter, "node_export", syck_emitter_node_export, 1);
    }
}

