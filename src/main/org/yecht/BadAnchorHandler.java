/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public interface BadAnchorHandler {
    Node handle(Parser p, String anchor);

    public static class Default implements BadAnchorHandler {
        public Node handle(Parser p, String anchor) {
            System.err.println("existing anchors: " + p.anchors);
            System.err.println("Bad anchor at [Line " + p.linect + ", Col " + (p.cursor - p.lineptr) + "]: " + anchor);
            return null;
        }
    }
}// BadAnchorHandler
