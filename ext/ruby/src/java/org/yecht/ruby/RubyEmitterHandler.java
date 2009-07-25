package org.yecht.ruby;

import org.yecht.Data;
import org.yecht.Emitter;
import org.yecht.EmitterHandler;
import org.yecht.MapPart;

import org.jruby.Ruby;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class RubyEmitterHandler implements EmitterHandler { 
    private Ruby runtime;

    public RubyEmitterHandler(Ruby runtime) {
        this.runtime = runtime;
    }

    // rb_syck_emitter_handler
    public void handle(Emitter e, Object data) {
        org.yecht.Node n = (org.yecht.Node)((IRubyObject)data).dataGetStructChecked();
        switch(n.kind) {
        case Map:
            Data.Map dm = (Data.Map)n.data;
            e.emitMap(n.type_id, dm.style);
            for(int i = 0; i < dm.idx; i++) {
                e.emitItem(n.mapRead(MapPart.Key, i));
                e.emitItem(n.mapRead(MapPart.Value, i));
            }
            e.emitEnd();
            break;
        case Seq:
            Data.Seq ds = (Data.Seq)n.data;
            e.emitSeq(n.type_id, ds.style);
            for(int i = 0; i < ds.idx; i++) {
                e.emitItem(n.seqRead(i));
            }
            e.emitEnd();
            break;
        case Str:
            Data.Str dss = (Data.Str)n.data;
            e.emitScalar(n.type_id, dss.style, 0, 0, 0, dss.ptr, dss.len);
            break;
        }
    }
}
