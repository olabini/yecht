/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public interface ErrorHandler {
    void handle(Parser p, String msg);

    public static class Default implements ErrorHandler {
        // syck_default_error_handler
        public void handle(Parser p, String msg) {
            System.err.println("Error at [Line " + p.linect + ", Col " + (p.cursor.start - p.lineptr.start) + "]: " + msg);
        }
    }
}// ErrorHandler
