package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.ImplicitScanner;
import org.yecht.MapPart;

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
import org.jruby.util.TypeConverter;

public class Resolver {
    // syck_const_find
    public static IRubyObject const_find(IRubyObject self, IRubyObject const_name) {
        RubyModule tclass = self.getRuntime().getObject();
        RubyArray tparts = ((RubyString)const_name).split(self.getRuntime().getCurrentContext(), self.getRuntime().newString("::"));
        for(int i=0; i < tparts.getLength(); i++) {
            String tpart = tparts.entry(i).toString();
            try {
                tclass = (RubyModule)tclass.getConstant(tpart);
            } catch(Exception e) {
                return self.getRuntime().getNil();
            }
        }
        return tclass;
    }

    // syck_resolver_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self) {
        ((RubyObject)self).fastSetInstanceVariable("@tags", RubyHash.newHash(self.getRuntime()));
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
        ((RubyObject)self).fastSetInstanceVariable("@tags", hsh);
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
            IRubyObject target_class = ((RubyHash)tags).op_aref(ctx, type);
            IRubyObject subclass = target_class;
            IRubyObject obj = runtime.getNil();
                
            if(target_class.isNil()) {
                RubyArray subclass_parts = runtime.newArray();
                RubyArray parts = ((RubyString)type).split(ctx, colon);
                while(parts.getLength() > 1) {
                    subclass_parts.unshift(parts.pop(ctx));
                    IRubyObject partial = parts.join(ctx, colon);
                    target_class = ((RubyHash)tags).op_aref(ctx, partial);
                    if(target_class.isNil()) {
                        ((RubyString)partial).append(colon);
                        target_class = ((RubyHash)tags).op_aref(ctx, partial);
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
        //             System.err.println("syck_resolver_node_import()");
        final Ruby runtime = self.getRuntime();
        final ThreadContext ctx = runtime.getCurrentContext();
        org.yecht.Node n = (org.yecht.Node)node.dataGetStructChecked();
        YAMLExtra x = ((Node)node).x;
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
                IRubyObject obj2 = (IRubyObject)n.seqRead(i);
                ((RubyArray)obj).store(i, obj2);
            }
            break;
        case Map:
            Data.Map dm = (Data.Map)n.data;
            obj = RubyHash.newHash(runtime);
            RubyClass cMergeKey = x.MergeKey;
            RubyClass cDefaultKey = x.DefaultKey;
            RubyClass cHash = runtime.getHash();
            RubyClass cArray = runtime.getArray();
                
            for(int i = 0; i < dm.idx; i++) {
                IRubyObject k = (IRubyObject)n.mapRead(MapPart.Key, i);
                IRubyObject v = (IRubyObject)n.mapRead(MapPart.Value, i);
                if(null == v) {
                    v = runtime.getNil();
                }
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
