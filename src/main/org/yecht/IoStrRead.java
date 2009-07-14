/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public interface IoStrRead {
    public int read(Pointer buf, JechtIO.Str str, int max_size, int skip);

    public static class Default implements IoStrRead {
        // syck_io_str_read
        public int read(Pointer buf, JechtIO.Str str, int max_size, int skip) {
            int beg = str.ptr.start;
            if(max_size >= 0) {
                max_size -= skip;
                if(max_size <= 0) {
                    max_size = 0;
                } else {
                    str.ptr.start += max_size;
                }
                
                if(str.ptr.start > str.end) {
                    str.ptr.start = str.end;
                }
            } else {
                while(str.ptr.start < str.end) {
                    if(str.ptr.buffer[str.ptr.start++] == '\n') {
                        break;
                    }
                }
            }
            int len = 0;
            if(beg < str.ptr.start) {
                len = str.ptr.start - beg;
                System.arraycopy(str.ptr.buffer, beg, buf.buffer, buf.start + skip, len);
            }
            len += skip;
            buf.buffer[buf.start+len] = 0;
            return len;
        }
    }
}// IoStrRead
