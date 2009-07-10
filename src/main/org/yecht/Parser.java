/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

import java.util.Map;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Parser {
    long root, root_on_error;
    int implicit_typing, taguri_expansion;
    NodeHandler handler;
    ErrorHandler error_handler;
    BadAnchorHandler bad_anchor_handler;
    ParserInput input_type;
    IOType io_type;
    int bufsize;
    Pointer buffer, linectptr, lineptr, toktmp, token, cursor, marker, limit;
    int linect;
    int last_token;
    int force_token;
    int eof;
    JechtIO io;
    Map anchors, bad_anchors;
    Map syms;
    Level[] levels;
    int lvl_idx;
    int lvl_capa;
    Object bonus;

    // syck_parser_reset_levels
    public void resetLevels() {
        while(lvl_idx > 1) {
            popLevel();
        }

        if(lvl_idx < 1) {
            lvl_idx = 1;
            levels[0].spaces = -1;
            levels[0].ncount = 0;
            levels[0].domain = "";
        }
        levels[0].status = LevelStatus.header;
    }

    // syck_parser_pop_level
    public void popLevel() {
        if(lvl_idx <= 1) {
            return;
        }
        lvl_idx--;
    }

    // syck_parser_reset_cursor
    public void resetCursor() {
        if(buffer == null) {
            buffer = Pointer.create(new byte[bufsize], 0);
        }
        buffer.buffer[buffer.start] = 0;
        cursor = null;
        lineptr = null;
        linectptr = null;
        token = null;
        toktmp = null;
        marker = null;
        limit = null;

        root = 0;
        root_on_error = 0;
        linect = 0;
        eof = 0;
        last_token = 0;
        force_token = 0;
    }
}// Parser
