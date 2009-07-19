/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public enum KindTag {
    Map, Seq, Str;
    
    // syck_alloc_node
    public Node allocNode() {
        Node s = new Node();
        s.kind = this;
        s.id = null;
        return s;
    }
}// KindTag
