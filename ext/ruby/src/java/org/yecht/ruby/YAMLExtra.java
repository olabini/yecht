package org.yecht.ruby;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.MethodIndex;

public class YAMLExtra {
    public IRubyObject quote1;
    public IRubyObject quote2;
    public IRubyObject fold;
    public IRubyObject literal;
    public IRubyObject plain;
    public IRubyObject map;
    public IRubyObject seq;
    public IRubyObject scalar;
    public IRubyObject inline;
    public RubyClass Scalar;
    public RubyClass Seq;
    public RubyClass Map;
    public IRubyObject DefaultResolver;
    public RubyClass Node;
    public RubyClass MergeKey;
    public RubyClass DefaultKey;
    public final CallSite type_id_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("type_id=");
    public final CallSite value_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("value=");
    public final CallSite style_set_ScalarAdapter = MethodIndex.getFunctionalCallSite("style=");

    public final CallSite type_id_set_MapAdapter = MethodIndex.getFunctionalCallSite("type_id=");
    public final CallSite value_set_MapAdapter = MethodIndex.getFunctionalCallSite("value=");
    public final CallSite style_set_MapAdapter = MethodIndex.getFunctionalCallSite("style=");

    public final CallSite type_id_set_SeqAdapter = MethodIndex.getFunctionalCallSite("type_id=");
    public final CallSite value_set_SeqAdapter = MethodIndex.getFunctionalCallSite("value=");
    public final CallSite style_set_SeqAdapter = MethodIndex.getFunctionalCallSite("style=");

    public final CallSite node_export_EmitterAdapter = MethodIndex.getFunctionalCallSite("node_export");
    public final CallSite keys_HashAdapter = MethodIndex.getFunctionalCallSite("keys");

    public Ruby runtime;

    public YAMLExtra(Ruby runtime) {
        quote1 = runtime.newSymbol("quote1");
        quote2 = runtime.newSymbol("quote2");
        fold = runtime.newSymbol("fold");
        literal = runtime.newSymbol("literal");
        plain = runtime.newSymbol("plain");
        map = runtime.newSymbol("map");
        seq = runtime.newSymbol("seq");
        scalar = runtime.newSymbol("scalar");
        inline = runtime.newSymbol("inline");
        Scalar = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Scalar");
        Seq = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Seq");
        Map = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Map");
        DefaultResolver = ((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("DefaultResolver");
        Node = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("Node");
        MergeKey = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("MergeKey");
        DefaultKey = (RubyClass)((RubyModule)((RubyModule)runtime.getModule("YAML")).getConstant("Yecht")).getConstant("DefaultKey");
        this.runtime = runtime;
    }
}
