package org.yecht.ruby;

import org.yecht.IoStrRead;
import org.yecht.JechtIO;
import org.yecht.Pointer;

import org.jruby.RubyNumeric;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.util.ByteList;

public class RubyIoStrRead implements IoStrRead {
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
        len += skip;
        buf.buffer[buf.start+len] = 0;
        return len;
    }
}
