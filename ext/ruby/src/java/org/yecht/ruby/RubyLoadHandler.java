package org.yecht.ruby;

import org.yecht.NodeHandler;
import org.yecht.Parser;

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.RubyHash;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.CallSite;
import org.jruby.runtime.MethodIndex;

public class RubyLoadHandler implements NodeHandler {
    private Ruby runtime;
    private YAMLExtra x;
    private final CallSite node_importAdapter = MethodIndex.getFunctionalCallSite("node_import");

    public RubyLoadHandler(Ruby runtime, YAMLExtra x) {
        this.runtime = runtime;
        this.x = x;
    }

    // rb_syck_load_handler
    public Object handle(Parser p, org.yecht.Node n) {
        //             System.err.println("load_handler for node: " + n.type_id + " with anchor: " + n.anchor);
        //             System.err.println(" id: " + n.id);
        //             if(n.id != null) {
        //                 System.err.println(" val: " + ((IRubyObject)n.id).inspect().toString());
        //             }

        //             System.err.println("rb_syck_load_handler(" + n + ")");
        YParser.Extra bonus = (YParser.Extra)p.bonus;
        IRubyObject resolver = bonus.resolver;
        if(resolver.isNil()) {
            resolver = x.DefaultResolver;
        }
            
        IRubyObject _n = new Node(runtime, x.Node, n, x);
            
        IRubyObject obj = node_importAdapter.call(runtime.getCurrentContext(), resolver, resolver, _n);
        //             System.err.println(" node_import -> " + obj);
        if(n.id != null && !obj.isNil()) {
            if(n.id instanceof PossibleLinkNode) {
                ((PossibleLinkNode)n.id).replaceLinks(obj);
            }
            n.id = obj;
            //                 System.err.println(" -- LoadHandler, setting id, yay!");
        }

        if(bonus.taint) {
            ((RubyObject)obj).taint(runtime.getCurrentContext());
        }

        if(bonus.proc != null) {
            bonus.proc.callMethod(runtime.getCurrentContext(), "call", obj);
        }
            
        ((RubyHash)bonus.data).fastASet(((RubyHash)bonus.data).rb_size(), obj);

        //             System.err.println(" -> rb_syck_load_handler=" + n.id);
        return obj;
    }
}

