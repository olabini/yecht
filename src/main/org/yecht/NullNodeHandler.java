/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class NullNodeHandler implements NodeHandler {
    private long current = 0;

    public Object handle(Parser p, Node n) {
        return Long.valueOf(current++);
    }
}// NullNodeHandler
