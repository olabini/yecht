/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class YAML {
    public final static int BLOCK_FOLD = 10;
    public final static int BLOCK_LIT = 20;
    public final static int BLOCK_PLAIN = 30;
    public final static int NL_CHOMP = 40;
    public final static int NL_KEEP = 50;

    public final static int YAML_MAJOR = 1;
    public final static int YAML_MINOR = 0;

    public final static String YECHT_VERSION = "0.0.1";
    public final static String DOMAIN = "yaml.org,2002";

    public final static int ALLOC_CT = 8;
    public final static int BUFFERSIZE = 4096;

    public static byte[] realloc(byte[] input, int size) {
        byte[] newArray = new byte[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }

    public static long[] realloc(long[] input, int size) {
        long[] newArray = new long[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }

    public static Level[] realloc(Level[] input, int size) {
        Level[] newArray = new Level[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }
}
