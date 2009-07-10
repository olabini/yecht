/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Node {
    long id;
    KindTag kind;
    String type_id;
    String anchor;
    Data data;
    Object shortcut;
    
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
        s.buffer = null;
        s.len = 0;
        s.ptr = -1;
        
        Node n = KindTag.Str.allocNode();
        n.data = s;
        return n;
    }
}// Node
