package org.yecht.ruby;

import java.util.List;
import java.util.LinkedList;

import org.yecht.BadAnchorHandler;
import org.yecht.BytecodeNodeHandler;
import org.yecht.Bytestring;
import org.yecht.Data;
import org.yecht.Emitter;
import org.yecht.EmitterHandler;
import org.yecht.ErrorHandler;
import org.yecht.IoStrRead;
import org.yecht.JechtIO;
import org.yecht.MapPart;
import org.yecht.NodeHandler;
import org.yecht.Parser;
import org.yecht.ParserInput;
import org.yecht.OutputHandler;
import org.yecht.Pointer;
import org.yecht.ImplicitScanner;
import org.yecht.ImplicitScanner2;
import org.yecht.MapStyle;
import org.yecht.SeqStyle;
import org.yecht.ScalarStyle;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyEnumerable;
import org.jruby.RubyHash;
import org.jruby.RubyKernel;
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

public class DefaultResolver {
    private static int extractInt(byte[] buff, int p, int pend) {
        int len = 0;
        while((p+len) < pend && Character.isDigit((char)buff[p+len])) {
            len++;
        }
        try {
            return Integer.parseInt(new String(buff, p, len, "ISO-8859-1"));
        } catch(java.io.UnsupportedEncodingException e) {return -1;}
    }
    
    // rb_syck_mktime
    public static IRubyObject makeTime(Ruby runtime, Pointer str, int len) {
//         System.err.println("makeTime(" + new String(str.buffer, str.start, len) + ")");
        int ptr = str.start;
        int pend = ptr + len;
        IRubyObject year = runtime.newFixnum(0);
        IRubyObject mon = runtime.newFixnum(0);
        IRubyObject day = runtime.newFixnum(0);
        IRubyObject hour = runtime.newFixnum(0);
        IRubyObject min = runtime.newFixnum(0);
        IRubyObject sec = runtime.newFixnum(0);
        long usec = 0;

        if(str.buffer[ptr] != 0 && ptr < pend) {
            year = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 4;
        if(str.buffer[ptr] != 0 && ptr < pend) {
            while(!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) ptr++;
            mon = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 2;
        if(str.buffer[ptr] != 0 && ptr < pend) {
            while(!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) ptr++;
            day = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 2;
        if(str.buffer[ptr] != 0 && ptr < pend) {
            while(!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) ptr++;
            hour = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 2;
        if(str.buffer[ptr] != 0 && ptr < pend) {
            while(!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) ptr++;
            min = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 2;
        if(str.buffer[ptr] != 0 && ptr < pend) {
            while(!Character.isDigit((char)str.buffer[ptr]) && ptr < pend) ptr++;
            sec = runtime.newFixnum(extractInt(str.buffer, ptr, pend));
        }

        ptr += 2;
        if(ptr < pend && str.buffer[ptr] == '.') {
            int end = ptr + 1;
            while(Character.isDigit((char)str.buffer[end]) && end < pend) end++;
            byte[] padded = new byte[]{'0', '0', '0', '0', '0', '0'};
            System.arraycopy(str.buffer, ptr+1, padded, 0, end - (ptr+1));
            try {
                usec = Long.parseLong(new String(padded, 0, 6, "ISO-8859-1"));
            } catch(java.io.UnsupportedEncodingException e) {}
        } else {
            usec = 0;
        }

        while(ptr < pend && str.buffer[ptr] != 'Z' && str.buffer[ptr] != '+' && str.buffer[ptr] != '-' && str.buffer[ptr] != 0) {
            ptr++;
        }

        if(ptr < pend && (str.buffer[ptr] == '-' || str.buffer[ptr] == '+')) {
            int lenx = 1;
            while(ptr+lenx < pend && Character.isDigit((char)str.buffer[ptr+lenx])) {
                lenx++;
            }
            if(str.buffer[ptr] == '+') {
                ptr++;
                lenx--;
            }
            try {
                long tz_offset = Long.parseLong(new String(str.buffer, ptr, lenx, "ISO-8859-1")) * 3600;
                ptr+=lenx;
                while(ptr < pend && str.buffer[ptr] != ':' && str.buffer[ptr] != 0 ) {
                    ptr++;
                }
                if(ptr < pend && str.buffer[ptr] == ':') {
                    ptr++;
                    if(tz_offset < 0) {
                        tz_offset -= extractInt(str.buffer, ptr, pend) * 60;
                    } else {
                        tz_offset += extractInt(str.buffer, ptr, pend) * 60;
                    }
                }
                
                IRubyObject time = runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "utc", new IRubyObject[]{year,mon,day,hour,min,sec});
                long tmp = RubyNumeric.num2long(time.callMethod(runtime.getCurrentContext(), "to_i")) - tz_offset;
                return runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "at", new IRubyObject[]{runtime.newFixnum(tmp), runtime.newFixnum(usec)});
            } catch(java.io.UnsupportedEncodingException e) {}
        } else {
            // Make UTC time
            return runtime.getClass("Time").callMethod(runtime.getCurrentContext(), "utc", new IRubyObject[]{year,mon,day,hour,min,sec,runtime.newFixnum(usec)});
        }
        System.err.println("oopsie, returning null");
        return null;
    }

    private static boolean handleScalar(Ruby runtime, org.yecht.Node n, String type_id, IRubyObject[] ref, YAMLExtra x) throws java.io.UnsupportedEncodingException {
        Data.Str ds = (Data.Str)n.data;
        ThreadContext ctx = runtime.getCurrentContext();
        boolean transferred = true;
        IRubyObject obj = null;

        if(type_id == null) {
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
        } else if(type_id.equals("null")) {
            obj = runtime.getNil();
        } else if(type_id.equals("binary")) {
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
            obj.callMethod(ctx, "tr!", new IRubyObject[]{runtime.newString("\n\t "), runtime.newString("")});
            IRubyObject arr = obj.callMethod(ctx, "unpack", runtime.newString("m"));
            obj = ((RubyArray)arr).shift(ctx);
        } else if(type_id.equals("bool#yes")) {
            obj = runtime.getTrue();
        } else if(type_id.equals("bool#no")) {
            obj = runtime.getFalse();
        } else if(type_id.equals("int#hex")) {
            n.strBlowAwayCommas();
            obj = RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len), 16, true);
        } else if(type_id.equals("int#oct")) {
            n.strBlowAwayCommas();
            obj = RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len),  8, true);
        } else if(type_id.equals("int#base60")) {
            long sixty = 1;
            long total = 0;
            n.strBlowAwayCommas();
            int ptr = ds.ptr.start;
            int end = ptr + ds.len;
            while(end > ptr) {
                long bnum = 0;
                int colon = end - 1;
                while(colon >= ptr && ds.ptr.buffer[colon] != ':' ) {
                    colon--;
                }
                bnum = Integer.parseInt(new String(ds.ptr.buffer, colon+1, end-(colon+1), "ISO-8859-1"));
                total += bnum * sixty;
                sixty *= 60;
                end = colon;
            }
            obj = runtime.newFixnum(total);
        } else if(type_id.startsWith("int")) {
            n.strBlowAwayCommas();
            obj = RubyNumeric.str2inum(runtime, RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len),  10, true);
        } else if(type_id.equals("float#base60")) {
            long sixty = 1;
            double total = 0.0;
            n.strBlowAwayCommas();
            int ptr = ds.ptr.start;
            int end = ptr + ds.len;
            while(end > ptr) {
                double bnum = 0;
                int colon = end - 1;
                while(colon >= ptr && ds.ptr.buffer[colon] != ':' ) {
                    colon--;
                }
                bnum = Double.parseDouble(new String(ds.ptr.buffer, colon+1, end-(colon+1), "ISO-8859-1"));
                total += bnum * sixty;
                sixty *= 60;
                end = colon;
            }
            obj = runtime.newFloat(total);
        } else if(type_id.equals("float#nan")) {
            obj = runtime.newFloat(Double.NaN);
        } else if(type_id.equals("float#inf")) {
            obj = runtime.newFloat(Double.POSITIVE_INFINITY);
        } else if(type_id.equals("float#neginf")) {
            obj = runtime.newFloat(Double.NEGATIVE_INFINITY);
        } else if(type_id.startsWith("float")) {
            n.strBlowAwayCommas();
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
            obj = obj.callMethod(ctx, "to_f");
        } else if(type_id.equals("timestamp#iso8601")) {
            obj = makeTime(runtime, ds.ptr, ds.len);
        } else if(type_id.equals("timestamp#spaced")) {
            obj = makeTime(runtime, ds.ptr, ds.len);
        } else if(type_id.equals("timestamp#ymd")) {
            IRubyObject year = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 0, 4, "ISO-8859-1")));
            IRubyObject mon = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 5, 2, "ISO-8859-1")));
            IRubyObject day = runtime.newFixnum(Integer.parseInt(new String(ds.ptr.buffer, 8, 2, "ISO-8859-1")));
                
            RubyKernel.require(runtime.getTopSelf(), runtime.newString("date"), Block.NULL_BLOCK);

            obj = runtime.getClass("Date").callMethod(ctx, "new", new IRubyObject[] {year, mon, day});
        } else if(type_id.startsWith("timestamp")) {
            obj = makeTime(runtime, ds.ptr, ds.len);
        } else if(type_id.startsWith("merge")) {
            obj = x.MergeKey.callMethod(ctx, "new");
        } else if(type_id.startsWith("default")) {
            obj = x.DefaultKey.callMethod(ctx, "new");
        } else if(ds.style == ScalarStyle.Plain && ds.len > 1 && ds.ptr.buffer[ds.ptr.start] == ':') {
            //                     System.err.println("houston, we have a symbol: " + n);
            obj = x.DefaultResolver.callMethod(ctx, "transfer", 
                                               new IRubyObject[]{runtime.newString("tag:ruby.yaml.org,2002:sym"),
                                                                 RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start+1, ds.len-1)
                                                                                                                                       });
            //                     System.err.println(" resulting in: " + obj);
        } else if(type_id.equals("str")) {
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
        } else {
            transferred = false;
            obj = RubyString.newStringShared(runtime, ds.ptr.buffer, ds.ptr.start, ds.len);
        }

        ref[0] = obj;
        return transferred;
    }

    // yaml_org_handler
    public static boolean orgHandler(IRubyObject self, org.yecht.Node n, IRubyObject[] ref, YAMLExtra x) {
//         System.err.println("orgHandler(" + self + ", " + n + ")");
        final Ruby runtime = self.getRuntime();
        ThreadContext ctx = runtime.getCurrentContext();
        String type_id = n.type_id;
        boolean transferred = false;
        IRubyObject obj = runtime.getNil();

        if(type_id != null && type_id.startsWith("tag:yaml.org,2002:")) {
            type_id = type_id.substring(18);
        }

        try {
            switch(n.kind) {
            case Str:
//                 System.err.println(" we have type id: " + type_id + " for: " + n);
                transferred = handleScalar(runtime, n, type_id, ref, x);
                break;
            case Seq:
                if(type_id == null || "seq".equals(type_id)) {
                    transferred = true;
                }
                Data.Seq dl = (Data.Seq)n.data;
                obj = RubyArray.newArray(runtime, dl.idx);
                for(int i = 0; i < dl.idx; i++) {
                    IRubyObject _obj = (IRubyObject)n.seqRead(i);
                    if(_obj instanceof PossibleLinkNode) {
                        ((PossibleLinkNode)_obj).addLink(new ArrayStorageLink(obj, i, _obj));
                    }
                    ((RubyArray)obj).store(i, _obj);
                }
                ref[0] = obj;
                break;
            case Map:
                if(type_id == null || "map".equals(type_id)) {
                    transferred = true;
                }
                Data.Map dm = (Data.Map)n.data;
                obj = RubyHash.newHash(runtime);
                RubyClass cMergeKey = x.MergeKey;
                RubyClass cDefaultKey = x.DefaultKey;
                for(int i = 0; i < dm.idx; i++) {
                    IRubyObject k = (IRubyObject)n.mapRead(MapPart.Key, i);
                    IRubyObject v = (IRubyObject)n.mapRead(MapPart.Value, i);
                    if(null == v) {
                        v = runtime.getNil();
                    }
                    boolean skip_aset = false;

                    if(cMergeKey.isInstance(k)) {
                        IRubyObject tmp = null;
                        if(!(tmp = TypeConverter.convertToTypeWithCheck(v, runtime.getHash(), "to_hash")).isNil()) {
                            IRubyObject dup = v.callMethod(ctx, "dup");
                            dup.callMethod(ctx, "update", obj);
                            obj = dup;
                            skip_aset = true;
                        } else if(!(tmp = v.checkArrayType()).isNil()) {
                            IRubyObject end = ((RubyArray)tmp).pop(ctx);
                            IRubyObject tmph = TypeConverter.convertToTypeWithCheck(end, runtime.getHash(), "to_hash");
                            if(!tmph.isNil()) {
                                final IRubyObject dup = tmph.callMethod(ctx, "dup");
                                tmp = ((RubyArray)tmp).reverse();
                                ((RubyArray)tmp).append(obj);
                                RubyEnumerable.callEach(runtime, ctx, tmp, new BlockCallback() {
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
                        if(v instanceof PossibleLinkNode) {
                            ((PossibleLinkNode)v).addLink(new HashStorageLink(obj, k, v));
                        }
                        ((RubyHash)obj).fastASet(k, v);
                    }
                }

                ref[0] = obj;
                break;
            }
        } catch(java.io.UnsupportedEncodingException e) {}
//         System.err.println(" - transferred: " + transferred);
        return transferred;
    }

    // syck_defaultresolver_node_import
    @JRubyMethod
    public static IRubyObject node_import(IRubyObject self, IRubyObject node) {
        //             System.err.println("syck_defaultresolver_node_import()");
        org.yecht.Node n = (org.yecht.Node)node.dataGetStructChecked();
        IRubyObject[] _obj = new IRubyObject[]{null};
        if(!orgHandler(self, n, _obj, ((Node)node).x)) {
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
            String type_id = ImplicitScanner2.matchImplicit(Pointer.create(bl.bytes, bl.begin), bl.realSize);
            return self.getRuntime().newString(type_id);
        }
        return RubyString.newEmptyString(self.getRuntime());
    }        
}

