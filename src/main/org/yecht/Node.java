/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Node {
    public long id = 0;
    public KindTag kind;
    public String type_id;
    public String anchor;
    public Data data;
    public Object shortcut;

    public String toString() {
        switch(kind) {
        case Str:
            return "Str[id=" + id +", type=" + type_id +", val="+data+"]";
        case Seq:
            return "Seq[id=" + id +", type=" + type_id +", val="+data+"]";
        case Map:
            return "Map[id=" + id +", type=" + type_id +", val="+data+"]";
        }
        return "other";
    }

    // syck_replace_str
    // syck_replace_str2
    public void replaceStr(Pointer str, int len, ScalarStyle style) {
        Data.Str s = (Data.Str)data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
    }

    // syck_str_blow_away_commas
    public void strBlowAwayCommas() {
        Data.Str d = ((Data.Str)data);
        byte[] buf = d.ptr.buffer;
        int go = d.ptr.start;
        int end = go + d.len;
        for(;go < end;go++) {
            if(buf[go] == ',') {
                d.len--;
                end--;
                System.arraycopy(buf, go+1, buf, go, go-end);
            }
        }
    }

    // syck_str_read
    public Pointer strRead() {
        return ((Data.Str)data).ptr;
    }

    // syck_map_empty
    public void mapEmpty() {
        Data.Map m = (Data.Map)data;
        m.idx = 0;
        m.capa = YAML.ALLOC_CT;
        m.keys = new long[m.capa];
        m.values = new long[m.capa];
    }

    // syck_map_add
    public void mapAdd(long key, long value) {
        Data.Map m = (Data.Map)data;
        int idx = m.idx;
        m.idx++;

        if(m.idx > m.capa) {
            m.capa += YAML.ALLOC_CT;
            m.keys = YAML.realloc(m.keys, m.capa);
            m.values = YAML.realloc(m.values, m.capa);
        }
        m.keys[idx] = key;
        m.values[idx] = value;
    }

    // syck_map_update
    public void mapUpdate(Node map2) {
        Data.Map m1 = (Data.Map)data;
        Data.Map m2 = (Data.Map)map2.data;

        if(m2.idx < 1) {
            return;
        }

        int new_idx = m1.idx;
        new_idx += m2.idx;
        int new_capa = m1.capa;
        while(new_idx > new_capa) {
            new_capa += YAML.ALLOC_CT;
        }
        if(new_capa > m1.capa) {
            m1.capa = new_capa;
            m1.keys = YAML.realloc(m1.keys, m1.capa);
            m1.values = YAML.realloc(m1.values, m1.capa);
        }
        for(new_idx = 0; new_idx < m2.idx; m1.idx++, new_idx++) {
            m1.keys[m1.idx] = m2.keys[new_idx];
            m1.values[m1.idx] = m2.values[new_idx];
        }
    }

    // syck_map_count
    public long mapCount() {
        return ((Data.Map)data).idx;
    }

    // syck_map_assign
    public void mapAssign(MapPart p, int idx, long id) {
        Data.Map m = (Data.Map)data;
        if(p == MapPart.Key) {
            m.keys[idx] = id;
        } else {
            m.values[idx] = id;
        }
    }

    // syck_map_read
    public long mapRead(MapPart p, int idx) {
        Data.Map m = (Data.Map)data;
        if(p == MapPart.Key) {
            return m.keys[idx];
        } else {
            return m.values[idx];
        }
    }

    // syck_seq_empty
    public void seqEmpty() {
        Data.Seq s = (Data.Seq)data;
        s.idx = 0;
        s.capa = YAML.ALLOC_CT;
        s.items = new long[s.capa];
    }

    // syck_seq_add
    public void seqAdd(long value) {
        Data.Seq s = (Data.Seq)data;
        int idx = s.idx;
        s.idx++;
        if(s.idx > s.capa) {
            s.capa += YAML.ALLOC_CT;
            s.items = YAML.realloc(s.items, s.capa);
        }
        s.items[idx] = value;
    }

    // syck_seq_count
    public int seqCount() {
        return ((Data.Seq)data).idx;
    }

    // syck_seq_assign
    public void seqAssign(int idx, long id) {
        ((Data.Seq)data).items[idx] = id;
    }

    // syck_seq_read
    public long seqRead(int idx) {
        return ((Data.Seq)data).items[idx];
    }

    // syck_alloc_map
    public static Node allocMap() {
        Data.Map m = new Data.Map();
        m.style = MapStyle.None;
        m.idx = 0;
        m.capa = YAML.ALLOC_CT;
        m.keys = new long[m.capa];
        m.values = new long[m.capa];
        
        Node n = KindTag.Map.allocNode();
        n.data = m;
        return n;
    }

    // syck_alloc_seq
    public static Node allocSeq() {
        Data.Seq s = new Data.Seq();
        s.style = SeqStyle.None;
        s.idx = 0;
        s.capa = YAML.ALLOC_CT;
        s.items = new long[s.capa];
        
        Node n = KindTag.Seq.allocNode();
        n.data = s;
        return n;
    }

    // syck_alloc_str
    public static Node allocStr() {
        Data.Str s = new Data.Str();
        s.style = ScalarStyle.None;
        s.ptr = Pointer.nullPointer();
        s.len = 0;
        
        Node n = KindTag.Str.allocNode();
        n.data = s;
        return n;
    }

    // syck_new_str
    // syck_new_str2
    public static Node newStr(Pointer str, int len, ScalarStyle style) {
        Node n = allocStr();
        Data.Str s = (Data.Str)n.data;
        s.ptr = Pointer.create(new byte[len], 0);
        s.len = len;
        s.style = style;
        str.memcpy(s.ptr, len);
        return n;
    }

    // syck_new_map
    public static Node newMap(long key, long value) {
        Node n = allocMap();
        n.mapAdd(key, value);
        return n;
    }

    // syck_new_seq
    public static Node newSeq(long value) {
        Node n = allocSeq();
        n.seqAdd(value);
        return n;
    }
}// Node
