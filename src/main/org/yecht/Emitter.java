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
}// Emitter
