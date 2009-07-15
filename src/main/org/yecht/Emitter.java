/*
 * See LICENSE file in distribution for copyright and licensing information.
 */
package org.yecht;

import java.util.Map;

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
    Map markers, anchors, anchored;
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
}// Emitter
