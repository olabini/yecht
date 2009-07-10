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
    private Parser() {}

    long root, root_on_error;
    boolean implicit_typing, taguri_expansion;
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

    // syck_parser_set_root_on_error
    public void setRootOnError(long roer) {
        root_on_error = roer;
    }

    // syck_new_parser
    public static Parser newParser() {
        Parser p = new Parser();
        p.lvl_capa = YAML.ALLOC_CT;
        p.levels = new Level[p.lvl_capa];
        p.input_type = ParserInput.YAML_UTF8;
        p.io_type = IOType.Str;
        p.io = null;
        p.syms = null;
        p.anchors = null;
        p.bad_anchors = null;
        p.implicit_typing = true;
        p.taguri_expansion = false;
        p.bufsize = YAML.BUFFERSIZE;
        p.buffer = null;
        p.lvl_idx = 0;
        p.resetLevels();
        return p;
    }

    // syck_add_sym
    public int addSym(Object data) {
        // TODO: implement when I know how the symbols are actually used
        return -1;
    }

    // syck_lookup_sym
    public Object lookupSym(long id) {
        // TODO: implement when I know how the symbols are actually used
        return null;
    }

    // syck_parser_handler
    public void handler(NodeHandler hdlr) {
        handler = hdlr;
    }

    // syck_parser_implicit_typing
    public void implicitTyping(boolean flag) {
        implicit_typing = flag;
    }

    // syck_parser_taguri_expansion
    public void taguriExpansion(boolean flag) {
        taguri_expansion = flag;
    }
    
    // syck_parser_error_handler
    public void errorHandler(ErrorHandler hdlr) {
        error_handler = hdlr;
    }

    // syck_parser_bad_anchor_handler
    public void badAnchorHandler(BadAnchorHandler hdlr) {
        bad_anchor_handler = hdlr;
    }

    // syck_parser_set_input_type
    public void setInputType(ParserInput input_type) {
        this.input_type = input_type;
    }

    // syck_parser_file
    public void file(java.io.InputStream fp, IoFileRead read) {
        resetCursor();
        io_type = IOType.File;
        if(read == null) {
            read = new IoFileRead.Default();
        }

        io = new JechtIO.File(fp, read);
    }

    // syck_parser_str
    public void str(Pointer ptr, int len, IoStrRead read) {
        resetCursor();
        io_type = IOType.Str;
        JechtIO.Str ss = new JechtIO.Str();
        io = ss;
        ss.beg = ptr.start;
        ss.ptr = ptr;
        ss.end = ptr.start + len;
        if(read == null) {
            ss.read = new IoStrRead.Default();
        } else {
            ss.read = read;
        }
    }

    // syck_parser_str_auto
    public void str(Pointer ptr, IoStrRead read) {
        str(ptr, ptr.buffer.length - ptr.start, read);
    }

    // syck_parser_current_level
    public Level currentLevel() {
        return levels[lvl_idx-1];
    }
    
    // syck_parser_add_level
    public void addLevel(int len, LevelStatus status) {
        if(lvl_idx + 1 > lvl_capa) {
            lvl_capa += YAML.ALLOC_CT;
            levels = YAML.realloc(levels, lvl_capa);
        }
        levels[lvl_idx] = new Level();
        levels[lvl_idx].spaces = len;
        levels[lvl_idx].ncount = 0;
        levels[lvl_idx].domain = levels[lvl_idx-1].domain;
        levels[lvl_idx].status = status;
        lvl_idx++;
    }

    // syck_move_tokens
    public int moveTokens() {
        int count;
        if(token == null) {
            return 0;
        }

        int skip = limit.start - token.start;
        if(skip < 0) {
            return 0;
        }

        if((count = token.start - buffer.start) != 0) {
            System.arraycopy(token.buffer, token.start, buffer.buffer, buffer.start, skip);
            token.start = buffer.start;
            marker.start -= count;
            cursor.start -= count;
            toktmp.start -= count;
            limit.start -= count;
            lineptr.start -= count;
            linectptr.start -= count;
        }
        return skip;
    }

    // syck_check_limit
    public void checkLimit(int len) {
        if(cursor == null) {
            cursor = Pointer.create(buffer.buffer, buffer.start);
            lineptr = Pointer.create(buffer.buffer, buffer.start);
            linectptr = Pointer.create(buffer.buffer, buffer.start);
            marker = Pointer.create(buffer.buffer, buffer.start);
        }
        limit.start = buffer.start + len;
    }

    // syck_parser_read
    public int read() throws java.io.IOException {
        int len = 0;
        int skip = 0;
        
        switch(io_type) {
        case Str:
            skip = moveTokens();
            len = ((JechtIO.Str)io).read.read(buffer, ((JechtIO.Str)io), YAML.BUFFERSIZE-1, skip);
            break;
        case File:
            skip = moveTokens();
            len = ((JechtIO.File)io).read.read(buffer, ((JechtIO.File)io), YAML.BUFFERSIZE-1, skip);
            break;
        default:
            break;
        }
        checkLimit(len);
        return len;
    }

    // syck_parser_readlen
    public int read(int max_size) throws java.io.IOException {
        int len = 0;
        int skip = 0;
        
        switch(io_type) {
        case Str:
            skip = moveTokens();
            len = ((JechtIO.Str)io).read.read(buffer, ((JechtIO.Str)io), max_size, skip);
            break;
        case File:
            skip = moveTokens();
            len = ((JechtIO.File)io).read.read(buffer, ((JechtIO.File)io), max_size, skip);
            break;
        default:
            break;
        }
        checkLimit(len);
        return len;
    }

    // syck_parse
    public long parse() {
        resetLevels();
        // TODO: add real parse call here
        //        yechtparse();
        return root;
    }
}// Parser
