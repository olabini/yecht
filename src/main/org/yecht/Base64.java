/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Base64 {
    private final static byte[] b64_table = BytecodeNodeHandler.bytes("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    // syck_base64enc
    public static byte[] enc(Pointer _s, final int _len) {
        int len = _len;
        int i = 0;
        byte padding = (byte)'=';
        byte[] buff = new byte[1 + (len*4)/3 + 6];
        byte[] sb = _s.buffer;
        int s = _s.start;

        while(len >= 3) {
            buff[i++] = b64_table[077 & (sb[s] >> 2)];
            buff[i++] = b64_table[077 & (((sb[s] << 4) & 060) | ((sb[s+1] >> 4) & 017))];
            buff[i++] = b64_table[077 & (((sb[s+1] << 2) & 074) | ((sb[s+2] >> 6) & 03))];
            buff[i++] = b64_table[077 & sb[s+2]];
            s += 3;
            len -= 3;
        }

        if(len == 2) {
            buff[i++] = b64_table[077 & (sb[s] >> 2)];
            buff[i++] = b64_table[077 & (((sb[s] << 4) & 060) | ((sb[s+1] >> 4) & 017))];
            buff[i++] = b64_table[077 & (((sb[s+1] << 2) & 074) | ((0 >> 6) & 03))];
            buff[i++] = padding;
        } else if(len == 1) {
            buff[i++] = b64_table[077 & (sb[s] >> 2)];
            buff[i++] = b64_table[077 & (((sb[s] << 4) & 060) | ((0 >> 4) & 017))];
            buff[i++] = padding;
            buff[i++] = padding;
        }

        buff[i++] = '\n';

        buff[i] = 0;

        return buff;
    }

    private final static int[] b64_xtable = new int[256];
    static {
        for(int i = 0; i < 256; i++) {
            b64_xtable[i] = -1;
        }
        for(int i = 0; i < 64; i++) {
            b64_xtable[(int)b64_table[i]] = i;
        }
    }

    // syck_base64dec
    public static byte[] dec(Pointer _s, final int _len) {
        int len = _len;
        byte[] sb = _s.buffer;
        int s = _s.start;
        int a = -1,b = -1,c = 0,d;
        
        byte[] ptrb = new byte[len];
        System.arraycopy(sb, s, ptrb, 0, len);
        int ptr = 0;
        int end = 0;
        int send = s + len;

        while(s < send) {
            while(sb[s] == '\r' || sb[s] == '\n') { s++; }
            if((a = b64_xtable[(int)sb[s+0]]) == -1) break;
            if((b = b64_xtable[(int)sb[s+1]]) == -1) break;
            if((c = b64_xtable[(int)sb[s+2]]) == -1) break;
            if((d = b64_xtable[(int)sb[s+3]]) == -1) break;
            ptrb[end++] = (byte)((a << 2) | (b >> 4));
            ptrb[end++] = (byte)((b << 4) | (c >> 2));
            ptrb[end++] = (byte)((c << 6) | d);
            s += 4;
        }

        if(a != -1 && b != -1) {
            if(s + 2 < send && sb[s+2] == '=')
                ptrb[end++] = (byte)((a << 2) | (b >> 4));
            if(c != -1 && s + 3 < send && sb[s+3] == '=') {
                ptrb[end++] = (byte)((a << 2) | (b >> 4));
                ptrb[end++] = (byte)((b << 4) | (c >> 2));
            }
        }
        ptrb[end] = '\0';
        return ptrb;
    }
}// Base64
