/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Pointer {
    public byte[] buffer;
    public int start = -1;

    private Pointer() {
    }

    private final static byte[] emptyBuffer = new byte[0];
    public static Pointer empty() {
        return create(emptyBuffer, 0);
    }

    public static Pointer nullPointer() {
        return new Pointer();
    }

    public static Pointer create(byte[] buf, int start) {
        Pointer p = new Pointer();
        p.buffer = buf;
        p.start = start;
        return p;
    }

    public static Pointer create(String buf) {
        Pointer p = new Pointer();
        try {
            p.buffer = buf.getBytes("ISO-8859-1");
        } catch(Exception e) {}
        p.start = 0;
        return p;
    }

    public void memcpy(byte[] toBuffer, int toIndex, int len) {
        System.arraycopy(buffer, start, toBuffer, toIndex, len);
    }

    public void memcpy(Pointer to, int len) {
        System.arraycopy(buffer, start, to.buffer, to.start, len);
    }

    public Pointer withStart(int start) {
        return Pointer.create(this.buffer, start);
    }
}// Pointer
