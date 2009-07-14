/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class BytecodeNodeHandler implements NodeHandler {
    private static byte[] bytes(String s) {
        try {
            return s.getBytes("ISO-8859-1");
        } catch(Exception e) {
            return null;
        }
    }

    // syck_yaml2byte_handler
    public long handle(Parser p, Node n) {
        Bytestring val = new Bytestring();
        if(n.anchor != null) {
            val.append(YAML.BYTE_ANCHOR, bytes(n.anchor), 0, -1);
        }
        if(n.type_id != null) {
            if(p.taguri_expansion) {
                val.append(YAML.BYTE_TRANSFER, bytes(n.type_id), 0, -1);
            } else {
                val.append(YAML.BYTE_TRANSFER, bytes("!" + n.type_id), 0, -1);
            }
        }

        switch(n.kind) {
        case Str: {
            byte nextcode = YAML.BYTE_SCALAR;
            Data.Str dd = (Data.Str)n.data;
            byte[] buf = dd.ptr.buffer;
            int start = dd.ptr.start;
            int finish = start + dd.len - 1;
            int current = start;
            byte ch;
            while(true) {
                ch = buf[current];
                if('\n' == ch || 0 == ch || current > finish) {
                    if(current >= start) {
                        val.append(nextcode, buf, start, current);
                        nextcode = YAML.BYTE_CONTINUE;
                    }
                    start = current + 1;
                    if(current > finish) {
                        break;
                    } else if('\n' == ch ) {
                        val.append(YAML.BYTE_NEWLINE,null,0,-1);
                    } else if(0 == ch) {
                        val.append(YAML.BYTE_NULLCHAR,null,0,-1);
                    }
                }
                current += 1;
            }
            break;
        }
        case Seq: {
            val.append(YAML.BYTE_SEQUENCE,null,0,-1);
            Data.Seq dd = (Data.Seq)n.data;
            for(int i = 0; i < dd.idx; i++) {
                long oid = n.seqRead(i);
                Bytestring sav = (Bytestring)p.lookupSym(oid);
                val.extend(sav);
            }
            val.append(YAML.BYTE_END_BRANCH,null,0,-1);
            break;
        }
        case Map: {
            val.append(YAML.BYTE_MAPPING,null,0,-1);
            Data.Map dd = (Data.Map)n.data;
            for(int i = 0; i < dd.idx; i++) {
                long oid = n.mapRead(MapPart.Key, i);
                Bytestring sav = (Bytestring)p.lookupSym(oid);
                val.extend(sav);
                oid = n.mapRead(MapPart.Value, i);
                sav = (Bytestring)p.lookupSym(oid);
                val.extend(sav);
            }
            val.append(YAML.BYTE_END_BRANCH,null,0,-1);
            break;
        }
        }
        return p.addSym(val);
    }
}// BytecodeNodeHandler
