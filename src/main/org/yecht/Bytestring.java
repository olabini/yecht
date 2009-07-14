/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 * Why says 'Reinvent the wheel...'. I'm reinventing it a few times over.
 *  
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Bytestring {
    int hash;
    byte[] buffer;
    int length;
    int remaining;
    boolean printed;

    public final static int HASH = 0xCAFECAFE;
    public final static int CHUNKSIZE = 64;

    private static int strlen(byte[] buf, int start) {
        int stop = buf.length;
        for(int ix = start; ix < stop; ix++) {
            if(buf[ix] == 0) {
                return ix-start;
            }
        }
        return stop-start;
    }

    // bytestring_alloc
    public Bytestring() {
        hash = HASH;
        length = CHUNKSIZE;
        remaining = length;
        buffer = new byte[length + 1];
        buffer[0] = 0;
        printed = false;
    }

    // bytestring_append
    public void append(byte code, byte[] inbuf, int start, int finish) {
        int length = 2;
        if(inbuf != null) {
            if(finish == -1) {
                finish = start + strlen(inbuf, start);
            }
            length += (finish - start);
        }
        
        if(length > remaining) {
            int grow = (length - remaining) + CHUNKSIZE;
            remaining += grow;
            length += grow;
            this.buffer = YAML.realloc(this.buffer, length+1);
        }

        int curr = length - remaining;
        this.buffer[curr] = code;
        curr++;
        if(inbuf != null) {
            while(start < finish) {
                buffer[curr++] = inbuf[start++];
            }
        }
        buffer[curr] = '\n';
        curr++;
        buffer[curr] = 0;
        remaining -= length;
    }

    // bytestring_extend
    public void extend(Bytestring ext) {
        if(ext.printed) {
            int curr = 0;
            while(ext.buffer[curr] != '\n') {
                curr++;
            }
            append(YAML.BYTE_ALIAS, ext.buffer, 1, curr);
        } else {
            ext.printed = true;
            int length = ext.length - ext.remaining;
            if(length > remaining) {
                int grow = (length - remaining) + CHUNKSIZE;
                remaining += grow;
                length += grow;
                this.buffer = YAML.realloc(this.buffer, length+1);
            }

            int curr = this.length - this.remaining;
            int from = 0;
            int stop = length;
            while(from < stop) {
                this.buffer[curr++] = ext.buffer[from++];
            }
            this.buffer[curr] = 0;
            this.remaining -= length;
        }
    }
}// Bytestring
