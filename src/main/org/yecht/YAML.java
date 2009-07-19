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

    public final static String DEFAULT_ANCHOR_FORMAT = "id%03d";

    /* specify list of bytecodes */
    public final static byte BYTE_FINISH          = (byte) 0;
    public final static byte BYTE_DOCUMENT        = (byte)'D';
    public final static byte BYTE_DIRECTIVE       = (byte)'V';
    public final static byte BYTE_PAUSE           = (byte)'P';
    public final static byte BYTE_MAPPING         = (byte)'M';
    public final static byte BYTE_SEQUENCE        = (byte)'Q';
    public final static byte BYTE_END_BRANCH      = (byte)'E';
    public final static byte BYTE_SCALAR          = (byte)'S';
    public final static byte BYTE_CONTINUE        = (byte)'C';
    public final static byte BYTE_NEWLINE         = (byte)'N';
    public final static byte BYTE_NULLCHAR        = (byte)'Z';
    public final static byte BYTE_ANCHOR          = (byte)'A';
    public final static byte BYTE_ALIAS           = (byte)'R';
    public final static byte BYTE_TRANSFER        = (byte)'T';
    /* formatting bytecodes */
    public final static byte BYTE_COMMENT         = (byte)'c';
    public final static byte BYTE_INDENT          = (byte)'i';
    public final static byte BYTE_STYLE           = (byte)'s';
    /* other bytecodes */
    public final static byte BYTE_LINE_NUMBER     = (byte)'#';
    public final static byte BYTE_WHOLE_SCALAR    = (byte)'<';
    public final static byte BYTE_NOTICE          = (byte)'!';
    public final static byte BYTE_SPAN            = (byte)')';
    public final static byte BYTE_ALLOC           = (byte)'@';

    /* second level style bytecodes, ie "s>" */
    public final static byte BYTE_FLOW            = (byte)'>';
    public final static byte BYTE_LITERAL         = (byte)'|';
    public final static byte BYTE_BLOCK           = (byte)'b';
    public final static byte BYTE_PLAIN           = (byte)'p';
    public final static byte BYTE_INLINE_MAPPING  = (byte)'{';
    public final static byte BYTE_INLINE_SEQUENCE = (byte)'[';
    public final static byte BYTE_SINGLE_QUOTED   = (byte)39;
    public final static byte BYTE_DOUBLE_QUOTED   = (byte)'"';



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

    public static Object[] realloc(Object[] input, int size) {
        Object[] newArray = new Object[size];
        System.arraycopy(input, 0, newArray, 0, input.length);
        return newArray;
    }

    // syck_yaml2byte
    public static byte[] yaml2byte(byte[] yamlstr) {
        Parser parser = Parser.newParser();
        parser.str(Pointer.create(yamlstr, 0), null);
        parser.handler(new BytecodeNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(true);
        parser.taguriExpansion(true);
        Bytestring sav = (Bytestring)parser.parse();
        if(null == sav) {
            return null;
        } else {
            byte[] ret = new byte[Bytestring.strlen(sav.buffer) + 2];
            ret[0] = 'D';
            ret[1] = '\n';
            System.arraycopy(sav.buffer, 0, ret, 2, ret.length-2);
            return ret;
        }
    }

    public static void main(String[] args) throws Exception {
        byte[] yaml = "test: 1\nand: \"with new\\nline\\n\"\nalso: &3 three\nmore: *3".getBytes("ISO-8859-1");
        System.out.println("--- # YAML ");
        System.out.print(new String(yaml, "ISO-8859-1"));
        System.out.print("\n...\n");
        System.out.print(new String(yaml2byte(yaml), "ISO-8859-1"));
    }
}
