package org.yecht.ruby;

import org.yecht.BytecodeNodeHandler;
import org.yecht.Bytestring;
import org.yecht.IoStrRead;
import org.yecht.JechtIO;
import org.yecht.Parser;
import org.yecht.Pointer;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyNumeric;
import org.jruby.RubyObject;
import org.jruby.RubyString;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.util.ByteList;

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
//     rb_define_method( cResolver, "initialize", syck_resolver_initialize, 0 );
//     rb_define_method( cResolver, "add_type", syck_resolver_add_type, 2 );
//     rb_define_method( cResolver, "use_types_at", syck_resolver_use_types_at, 1 );
//     rb_define_method( cResolver, "detect_implicit", syck_resolver_detect_implicit, 1 );
//     rb_define_method( cResolver, "transfer", syck_resolver_transfer, 2 );
//     rb_define_method( cResolver, "node_import", syck_resolver_node_import, 1 );
//     rb_define_method( cResolver, "tagurize", syck_resolver_tagurize, 1 );
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

