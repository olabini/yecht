/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author <a href="mailto:ola.bini@gmail.com">Ola Bini</a>
 */
public class Emitter {
    public static class Node {
        public int pos;
        public int indent;
        public boolean is_shortcut;
    }

    public boolean headless;
    public boolean use_header;
    public boolean use_version;
    public boolean sort_keys;
    public String anchor_format;
    public boolean explicit_typing;
    public int best_width;
    public ScalarStyle style;
    public DocStage stage;
    public int level;
    public int indent;
    public long ignore_id;
    Map<Long, Long> markers;
    Map<Long, String> anchors;
    Map<String, Object> anchored;
    int bufsize;
    byte[] buffer;
    int marker;
    int bufpos;
    EmitterHandler emitter_handler;
    OutputHandler output_handler;
    Level[] levels;
    int lvl_idx;
    int lvl_capa;
    Object bonus;

    // syck_new_emitter
    public Emitter() {
        this.headless = false;
        this.use_header = false;
        this.use_version = false;
        this.sort_keys = false;
        this.anchor_format = null;
        this.explicit_typing = false;
        this.best_width = 80;
        this.style = ScalarStyle.None;
        this.stage = DocStage.open;
        this.indent = 2;
        this.level = -1;
        this.anchors = null;
        this.markers = null;
        this.anchored = null;
        this.bufsize = YAML.BUFFERSIZE;
        this.buffer = null;
        this.marker = -1;
        this.bufpos = 0;
        this.emitter_handler = null;
        this.output_handler = null;
        this.lvl_idx = 0;
        this.lvl_capa = YAML.ALLOC_CT;
        this.levels = new Level[this.lvl_capa];
        resetLevels();
        this.bonus = null;
    }


    // syck_emitter_current_level
    public Level currentLevel() {
        return levels[lvl_idx-1];
    }

    // syck_emitter_parent_level
    public Level parentLevel() {
        return levels[lvl_idx-2];
    }

    // syck_emitter_pop_level
    public void popLevel() {
        if(lvl_idx <= 1) {
            return;
        }
        lvl_idx--;

    }
    // syck_emitter_add_level
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
        levels[lvl_idx].anctag = 0;
        lvl_idx++;
    }

    // syck_emitter_reset_levels
    public void resetLevels() {
        while(lvl_idx > 1) {
            popLevel();
        }

        if(lvl_idx < 1) {
            lvl_idx = 1;
            levels[0] = new Level();
            levels[0].spaces = -1;
            levels[0].ncount = 0;
            levels[0].domain = "";
            levels[0].anctag = 0;
        }
        levels[0].status = LevelStatus.header;
    }

    // syck_emitter_handler
    public void handler(EmitterHandler hdlr) {
        this.emitter_handler = hdlr;
    }

    // syck_output_handler
    public void outputHandler(OutputHandler hdlr) {
        this.output_handler = hdlr;
    }

    // syck_emitter_clear
    public void clear() {
        if(this.buffer == null) {
            this.buffer = new byte[this.bufsize];
        }
        this.buffer[0] = 0;
        this.marker = 0;
        this.bufpos = 0;
    }

    // syck_emitter_write
    public void write(Pointer _str, final int _len) {
        int len = _len;
        byte[] bstr = _str.buffer;
        int str = _str.start;

        if(this.buffer == null) {
            clear();
        }

        int at = this.marker;
        if(len + at >= this.bufsize - 1) {
            flush(0);
            for(;;) {
                int rest = (this.bufsize - 1) - this.marker;
                if(len <= rest) break;
                System.arraycopy(bstr, str, this.buffer, this.marker, rest);
                this.marker += rest;
                str += rest;
                len -= rest;
                flush(0);
            }
        }
        
        System.arraycopy(bstr, str, this.buffer, this.marker, len);
        this.marker += len;
        this.buffer[this.marker] = 0;
    }

    // syck_emitter_flush
    public void flush(int check_room ){
        if(check_room > 0) {
            if((this.bufsize - 1) > (this.marker + check_room)) {
                return;
            }
        } else {
            check_room = this.bufsize - 1;
        }

        if(check_room > this.marker ) {
            check_room = this.marker;
        }

        this.output_handler.handle(this, this.buffer, check_room);
        this.bufpos += check_room;
        this.marker -= check_room;
    }

    private final static Pointer NEWLINE = Pointer.create("\n");
    private final static Pointer TWO_NEWLINES = Pointer.create("\n\n");
    private final static Pointer SPACE = Pointer.create(" ");
    private final static Pointer SLASH = Pointer.create("/");
    private final static Pointer THREE_DASHES = Pointer.create("--- ");
    private final static Pointer QUESTION_MARK_SPACE = Pointer.create("? ");
    private final static Pointer BANG = Pointer.create("!");
    private final static Pointer BANG_SPACE = Pointer.create("! ");
    private final static Pointer TWO_BANGS = Pointer.create("!!");
    private final static Pointer COLON_SPACE = Pointer.create(": ");
    private final static Pointer BACKSLASH = Pointer.create("\\");
    private final static Pointer ZERO = Pointer.create("0");
    private final static Pointer X = Pointer.create("x");
    private final static Pointer SINGLE_QUOTE = Pointer.create("'");
    private final static Pointer DOUBLE_QUOTE = Pointer.create("\"");

    /*
     * Start emitting from the given node, check for anchoring and then
     * issue the callback to the emitter handler.
     */
    // syck_emit
    public void emit(long n) {
        int indent = 0;
        int x = 0;
        Level lvl = currentLevel();

        if(stage == DocStage.open && (!headless || use_header)) {
            if(use_version) {
                String header = "--- %YAML:" + YAML.YAML_MAJOR + "." + YAML.YAML_MINOR + " ";
                write(Pointer.create(header), header.length());

            } else {
                write(THREE_DASHES, 4);
            }
            stage = DocStage.processing;
        }

        if(lvl.spaces >= 0) {
            indent = lvl.spaces + this.indent;
        }
        addLevel(indent, LevelStatus.open);
        Level parent = lvl;
        lvl = currentLevel();

        boolean handle = true;

        if(this.anchors != null && this.markers.containsKey(n)) {
            long oid = this.markers.get(n);
            if(this.anchors.containsKey(oid)) {
                String anchor_name = this.anchors.get(oid);
                if(this.anchored == null) {
                    anchored = new HashMap<String, Object>();
                }

                if(!anchored.containsKey(anchor_name)) {
                    String an = "&" + anchor_name + " ";
                    if(parent.status == LevelStatus.map && parent.ncount % 2 == 1) {
                        write(QUESTION_MARK_SPACE, 2);
                        parent.status = LevelStatus.mapx;
                    }

                    write(Pointer.create(an), an.length());

                    this.anchored.put(anchor_name, null);
                    lvl.anctag = 1;
                } else {
                    String an = "*" + anchor_name;
                    write(Pointer.create(an), an.length());
                    handle = false;
                }
            }
        }
        
        if(handle) {
            this.emitter_handler.handle(this, n);
        }

        popLevel();
        if(lvl_idx == 1) {
            write(NEWLINE, 1);
            this.headless = false;
            this.stage = DocStage.open;
        }
    }

    // syck_emit_tag
    public void emitTag(String tag, String ignore) {
        if(tag == null) {
            return;
        }

        if(ignore != null && ImplicitScanner.tagcmp(tag, ignore) && !this.explicit_typing) {
            return;
        }

        Level lvl = currentLevel();

        if(tag.length() == 0) {
            write(BANG_SPACE, 2);
        } else if(tag.startsWith("tag:")) {
            int taglen = tag.length();
            Pointer ptag = Pointer.create(tag);
            write(BANG, 1);
            if(tag.substring(4).startsWith(YAML.DOMAIN)) {
                int skip = 4 + YAML.DOMAIN.length() + 1;
                write(ptag.withStart(skip), taglen - skip);
            } else {
                int subd = 4;
                while(subd < taglen && tag.charAt(subd) != ':') {
                    subd++;
                }
                if(subd < taglen && tag.charAt(subd) == ':') {
                    if(subd > (YAML.DOMAIN.length() + 5) &&
                       tag.substring(subd - YAML.DOMAIN.length()).startsWith(YAML.DOMAIN)) {
                        write(ptag.withStart(4), (subd - YAML.DOMAIN.length()) - 5);
                        write(SLASH, 1);
                        write(ptag.withStart(subd+1), taglen - (subd + 1));
                    } else {
                        write(ptag.withStart(4), subd - 4);
                        write(SLASH, 1);
                        write(ptag.withStart(subd + 1), taglen - (subd + 1));
                    }
                } else {
                    /* TODO: Invalid tag (no colon after domain) */
                    return;
                }
            }
            write(SPACE, 1);
        } else if(tag.startsWith("x-private:")) {
            write(TWO_BANGS, 2);
            write(Pointer.create(tag.substring(10)), tag.length()-10);
            write(SPACE, 1);
        }
        lvl.anctag = 1;
    }

    // syck_emit_indent
    public void emitIndent() {
        Level lvl = currentLevel();
        if(bufpos == 0 && marker == 0) {
            return;
        }
        if(lvl.spaces >= 0) {
            byte[] spcs = new byte[lvl.spaces + 2];
            spcs[0] = '\n';
            spcs[lvl.spaces + 1] = 0;
            for(int i=0; i<lvl.spaces; i++) {
                spcs[i+1] = ' ';
            }
            write(Pointer.create(spcs, 0), lvl.spaces + 1);
        }
    }

    /* Clear the scan */
    private final static int SCAN_NONE       =0;
    /* All printable characters? */
    private final static int SCAN_NONPRINT   =1;
    /* Any indented lines? */
    private final static int SCAN_INDENTED   =2;
    /* Larger than the requested width? */
    private final static int SCAN_WIDE       =4;
    /* Opens or closes with whitespace? */
    private final static int SCAN_WHITEEDGE  =8;
    /* Contains a newline */
    private final static int SCAN_NEWLINE    =16;
    /* Contains a single quote */
    private final static int SCAN_SINGLEQ    =32;
    /* Contains a double quote */
    private final static int SCAN_DOUBLEQ    =64;
    /* Starts with a token */
    private final static int SCAN_INDIC_S    =128;
    /* Contains a flow indicator */
    private final static int SCAN_INDIC_C    =256;
    /* Ends without newlines */
    private final static int SCAN_NONL_E     =512;
    /* Ends with many newlines */
    private final static int SCAN_MANYNL_E   =1024;
    /* Contains flow map indicators */
    private final static int SCAN_FLOWMAP    =2048;
    /* Contains flow seq indicators */
    private final static int SCAN_FLOWSEQ    =4096;
    /* Contains a valid doc separator */
    private final static int SCAN_DOCSEP     =8192;

    // syck_scan_scalar
    public int scanScalar(int req_width, Pointer _cursor, int len) {
        byte[] cursorb = _cursor.buffer;
        int cursor = _cursor.start;

        int start = 0;
        int flags = SCAN_NONE;
        if(len < 1) {
            return flags;
        }

        switch(cursorb[cursor]) {
        case '[': case ']':
        case '{': case '}':
        case '!': case '*':
        case '&': case '|':
        case '>': case '\'':
        case '"': case '#':
        case '%': case '@':
        case '`':
            flags |= SCAN_INDIC_S;
            break;
        case '-': case ':':
        case '?': case ',':
            if(len == 1 || cursorb[cursor+1] == ' ' || cursorb[cursor+1] == '\n') {
                flags |= SCAN_INDIC_S;
            }
            break;
        }

        if(cursorb[cursor + len - 1] != '\n') {
            flags |= SCAN_NONL_E;
        } else if(len > 1 && cursorb[cursor + len - 2] == '\n') {
            flags |= SCAN_MANYNL_E;
        }

        if(
           (len>0 && (cursorb[cursor] == ' ' || cursorb[cursor] == '\t')) ||
           (len>1 && (cursorb[cursor + len - 1] == ' ' || cursorb[cursor + len - 1] == '\t'))
           ) {
            flags |= SCAN_WHITEEDGE;
        }

        if(len >= 3 && cursorb[cursor] == '-' && cursorb[cursor+1] == '-' && cursorb[cursor+2] == '-') {
            flags |= SCAN_DOCSEP;
        }

        for(int i=0; i<len; i++) {
            int ci = (int)(cursorb[cursor+i]&0xFF);
            if(! ( ci == 0x9 ||
                   ci == 0xA ||
                   ci == 0xD ||
                   (ci >= 0x20 && ci <= 0x7E))) {
                flags |= SCAN_NONPRINT;
            } else if(ci == '\n') {
                flags |= SCAN_NEWLINE;
                if(len - i >= 3 && cursorb[cursor+i+1] == '-' && cursorb[cursor+i+2] == '-' && cursorb[cursor+i+3] == '-' ) {
                    flags |= SCAN_DOCSEP;
                }
                if(cursorb[cursor+i+1] == ' ' || cursorb[cursor+i+1] == '\t') {
                    flags |= SCAN_INDENTED;
                }
                if(req_width > 0 && (i - start) > req_width) {
                    flags |= SCAN_WIDE;
                }
                start = i;
            } else if(ci == '\'') {
                flags |= SCAN_SINGLEQ;
            } else if(ci == '"') {
                flags |= SCAN_DOUBLEQ;
            } else if(ci == ']') {
                flags |= SCAN_FLOWSEQ;
            } else if(ci == '}') {
                flags |= SCAN_FLOWMAP;
            } else if((ci == ' ' && cursorb[cursor+i+1] == '#' ) ||
                      (ci == ':' && (cursorb[cursor+i+1] == ' ' || 
                                     cursorb[cursor+i+1] == '\n' || 
                                     i == len - 1 ))) {
                flags |= SCAN_INDIC_C;
            } else if(ci == ',' && (cursorb[cursor+i+1] == ' ' || 
                                    cursorb[cursor+i+1] == '\n' || 
                                    i == len - 1 )) {
                flags |= SCAN_FLOWMAP;
                flags |= SCAN_FLOWSEQ;
            }
        }

        return flags;
    }

    private final static Pointer EMPTY = Pointer.create(new byte[0], 0);
    private final static Pointer TILDE = Pointer.create("~");

    // syck_emit_scalar
    public void emitScalar(String tag, ScalarStyle force_style, int force_indent, int force_width, int keep_nl, Pointer _str, int len) {
        if(_str == null) {
            _str = EMPTY;
        }

        byte[] bstr = _str.buffer;
        int str = _str.start;

        ScalarStyle favor_style = ScalarStyle.Literal;
        Level parent = parentLevel();
        Level lvl = currentLevel();

        if(len == 0 && (parent.status == LevelStatus.map || parent.status == LevelStatus.imap) && parent.ncount % 2 == 1 && ImplicitScanner.tagcmp(tag, "tag:yaml.org,2002:null")) {
            _str = TILDE;
            bstr = _str.buffer;
            str = _str.start;
            len = 1;
        }
        
        int scan = scanScalar(force_width, _str, len);
        String implicit = Parser.taguri(YAML.DOMAIN, ImplicitScanner.matchImplicit(_str, len));
        if(!ImplicitScanner.tagcmp(tag, implicit) && ImplicitScanner.tagcmp(tag, "tag:yaml.org,2002:str")) {
            force_style = ScalarStyle.TwoQuote;
        } else {
            if(parent.status == LevelStatus.map && parent.ncount % 2 == 1 && ( !(tag == null || (implicit != null && ImplicitScanner.tagcmp(tag, implicit) && !explicit_typing)))) {
                write(QUESTION_MARK_SPACE, 2);
                parent.status = LevelStatus.mapx;
            }
            emitTag(tag, implicit);
        }

        if(force_style == ScalarStyle.None) {
            if((scan & SCAN_NEWLINE) != 0) {
                force_style = ScalarStyle.Literal;
            } else {
                force_style = ScalarStyle.Plain;
            }
        }

        if(this.style == ScalarStyle.Fold) {
            favor_style = ScalarStyle.Fold;
        }

        if((scan & SCAN_NONPRINT) != 0) {
            force_style = ScalarStyle.TwoQuote;
        } else if((scan & SCAN_WHITEEDGE) != 0) {
            force_style = ScalarStyle.TwoQuote;
        } else if(force_style != ScalarStyle.Fold && (scan & SCAN_INDENTED) != 0) {
            force_style = ScalarStyle.Literal;
        } else if(force_style == ScalarStyle.Plain && (scan & SCAN_NEWLINE) != 0) {
            force_style = favor_style;
        } else if(force_style == ScalarStyle.Plain && parent.status == LevelStatus.iseq && (scan & SCAN_FLOWSEQ) != 0) {
            force_style = ScalarStyle.TwoQuote;
        } else if(force_style == ScalarStyle.Plain && parent.status == LevelStatus.imap && (scan & SCAN_FLOWMAP) != 0) {
            force_style = ScalarStyle.TwoQuote;
        } else if(force_style == ScalarStyle.Plain && ((scan & SCAN_INDIC_S) != 0 || (scan & SCAN_INDIC_C) != 0)) {
            if((scan & SCAN_NEWLINE) != 0) {
                force_style = favor_style;
            } else {
                force_style = ScalarStyle.TwoQuote;
            }
        }

        if(force_indent > 0) {
            lvl.spaces = parent.spaces + force_indent;
        } else if((scan & SCAN_DOCSEP) != 0) {
            lvl.spaces = parent.spaces + this.indent;
        }
        
        if((parent.status == LevelStatus.map || parent.status == LevelStatus.mapx) && parent.ncount % 2 == 1) {
            if(force_style != ScalarStyle.Plain) {
                force_style = ScalarStyle.TwoQuote;
            }
        }

        if(parent.status == LevelStatus.imap || parent.status == LevelStatus.iseq) {
            if(force_style != ScalarStyle.Plain && force_style != ScalarStyle.OneQuote) {
                force_style = ScalarStyle.TwoQuote;
            }
        }

        if((scan & SCAN_NONL_E) != 0) {
            keep_nl = YAML.NL_CHOMP;
        } else if((scan & SCAN_MANYNL_E) != 0) {
            keep_nl = YAML.NL_KEEP;
        }

        switch(force_style) {
        case OneQuote:
            emit1Quoted(force_width, _str, len);
            break;
        case None:
        case TwoQuote:
//             syck_emit_2quoted( e, force_width, str, len );
            break;
        case Fold:
//             syck_emit_folded( e, force_width, keep_nl, str, len );
            break;
        case Literal:
//             syck_emit_literal( e, keep_nl, str, len );
            break;
        case Plain:
            write(_str, len);
            break;
        }

        if(parent.status == LevelStatus.mapx) {
            write(NEWLINE, 1);
        }
    }

    private final static Pointer hex_table = Pointer.create("0123456789ABCDEF");

    // syck_emitter_escape
    public void escape(Pointer _src, int len) {
        byte[] bsrc = _src.buffer;
        int src = _src.start;
        for(int i=0; i<len; i++) {
            int curr = (int)bsrc[src+i]&0xFF;

            if(curr < 0x20 || (0x7E < curr)) {
                write(BACKSLASH, 1);
                if(curr == 0) {
                    write(ZERO, 1);
                } else {
                    write(X, 1);
                    write(hex_table.withStart((curr & 0xF0) >> 4), 1);
                    write(hex_table.withStart(curr & 0x0F), 1);
                }
            } else {
                write(_src.withStart(src+i), 1);
                if(curr == '\\') {
                    write(BACKSLASH, 1);
                }
            }
        }
    }

    // syck_emit_1quoted
    public void emit1Quoted(int width, Pointer _str, final int len) {
        byte[] bstr = _str.buffer;
        int str = _str.start;

        boolean do_indent = false;
        int mark = str;
        int start = str;
        int end = str;
        write(SINGLE_QUOTE, 1);
        while(mark < str + len) {
            if(do_indent) {
                emitIndent();
                do_indent = false;
            }
            switch(bstr[mark]) {
            case '\'':
                write(SINGLE_QUOTE, 1);
                break;
            case '\n':
                end = mark + 1;
                if(bstr[start] != ' ' && bstr[start] != '\n' && bstr[end] != '\n' && bstr[end] != ' ' ) {
                    write(TWO_NEWLINES, 2);
                } else {
                    write(NEWLINE, 1);
                }
                do_indent = true;
                start = mark + 1;
                break;
            case ' ':
                if(width > 0 && bstr[start] != ' ' && mark - end > width ) {
                    do_indent = true;
                    end = mark + 1;
                } else {
                    write(SPACE, 1);
                }
                break;
            default:
                write(_str.withStart(mark), 1);
                break;
            }


        }
        write(SINGLE_QUOTE, 1);
    }
}// Emitter
