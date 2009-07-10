/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public abstract class JechtIO {
    public static class File extends JechtIO {
        public java.io.FileInputStream ptr;
        public IoFileRead read;
    }

    public static class Str extends JechtIO {
        public Pointer ptr;
        public int beg;
        public int end;
        public IoStrRead read;
    }
}// JechtIO
