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
        MapStyle style;
        long[] keys;
        long[] values;
        int capa;
        int idx;

        public String toString() {
            return "{}";
        }
    }

    public static class Seq extends Data {
        SeqStyle style;
        long[] items;
        int capa;
        int idx;

        public String toString() {
            return "[]";
        }
    }

    public static class Str extends Data {
        ScalarStyle style;
        Pointer ptr;
        int len;

        public String toString() {
            return "\"" + new String(ptr.buffer, ptr.start, len) + "\"";
        }
    }
}// Data
