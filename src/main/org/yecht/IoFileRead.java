/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public interface IoFileRead {
    public int read(Pointer buf, JechtIO.File file, int max_size, int skip) throws IOException;

    public static class Default implements IoFileRead {
        // syck_io_file_read
        public int read(Pointer buf, JechtIO.File file, int max_size, int skip) throws IOException {
            max_size -= skip;
            int len = file.ptr.read(buf.buffer, buf.start + skip, max_size);
            len += skip;
            return len;
        }
    }
}// IoFileRead
