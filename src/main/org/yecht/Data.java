/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public abstract class Data {
    public abstract Data copy();

    public static class Map extends Data {
        public MapStyle style;
        public long[] keys;
        public long[] values;
        public int capa;
        public int idx;

        public String toString() {
            return "{}";
        }

        public Map copy() {
            Map m = new Map();
            m.style = this.style;
            m.keys = new long[this.keys.length];
            System.arraycopy(this.keys, 0, m.keys, 0, this.keys.length);
            m.values = new long[this.values.length];
            System.arraycopy(this.values, 0, m.values, 0, this.values.length);
            m.capa = this.capa;
            m.idx = this.idx;
            return m;
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

        public Seq copy() {
            Seq m = new Seq();
            m.style = this.style;
            m.items = new long[this.items.length];
            System.arraycopy(this.items, 0, m.items, 0, this.items.length);
            m.capa = this.capa;
            m.idx = this.idx;
            return m;
        }
    }

    public static class Str extends Data {
        public ScalarStyle style;
        public Pointer ptr;
        public int len;

        public String toString() {
            return "\"" + new String(ptr.buffer, ptr.start, len) + "\"";
        }

        public Str copy() {
            Str m = new Str();
            m.ptr = Pointer.create(this.ptr.buffer, this.ptr.start);
            m.len = this.len;
            return m;
        }
    }
}// Data
