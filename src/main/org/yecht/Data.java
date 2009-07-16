/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public abstract class Data {
    public static class Map extends Data {
        public MapStyle style;
        public long[] keys;
        public long[] values;
        public int capa;
        public int idx;

        public String toString() {
            return "{}";
        }
    }

    public static class Seq extends Data {
        public SeqStyle style;
        public long[] items;
        public int capa;
        public int idx;

        public String toString() {
            return "[]";
        }
    }

    public static class Str extends Data {
        public ScalarStyle style;
        public Pointer ptr;
        public int len;

        public String toString() {
            return "\"" + new String(ptr.buffer, ptr.start, len) + "\"";
        }
    }
}// Data
