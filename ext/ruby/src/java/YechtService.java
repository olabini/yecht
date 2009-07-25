
import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

import org.yecht.YAML;
import org.yecht.ruby.*;

public class YechtService implements BasicLibraryService {
    public boolean basicLoad(final Ruby runtime) throws IOException {
        ThreadContext ctx = runtime.getCurrentContext();

        RubyModule rb_yaml = runtime.getOrCreateModule("YAML");
        RubyModule rb_yecht = rb_yaml.defineModuleUnder("Yecht");
        rb_yecht.defineConstant("VERSION", runtime.newString(YAML.YECHT_VERSION));
        rb_yecht.defineAnnotatedMethods(Module.class);

        RubyClass cResolver = rb_yecht.defineClassUnder("Resolver", runtime.getObject(), runtime.getObject().getAllocator());
        cResolver.defineAnnotatedMethods(Resolver.class);
        cResolver.addReadWriteAttribute(ctx, "tags");

        IRubyObject oDefaultResolver = cResolver.callMethod(ctx, "new");
        oDefaultResolver.getSingletonClass().defineAnnotatedMethods(DefaultResolver.class);
        rb_yecht.defineConstant("DefaultResolver", oDefaultResolver);
        
        IRubyObject oGenericResolver = cResolver.callMethod(ctx, "new");
        oGenericResolver.getSingletonClass().defineAnnotatedMethods(GenericResolver.class);
        rb_yecht.defineConstant("GenericResolver", oGenericResolver);

        RubyClass cParser = rb_yecht.defineClassUnder("Parser", runtime.getObject(), YParser.Allocator);
        cParser.defineAnnotatedMethods(YParser.class);
        cParser.addReadWriteAttribute(ctx, "options");
        cParser.addReadWriteAttribute(ctx, "resolver");
        cParser.addReadWriteAttribute(ctx, "input");

        RubyClass cNode = rb_yecht.defineClassUnder("Node", runtime.getObject(), Node.Allocator);
        cNode.defineAnnotatedMethods(Node.class);
        cNode.addReadWriteAttribute(ctx, "emitter");
        cNode.addReadWriteAttribute(ctx, "resolver");
        cNode.addReadAttribute(ctx, "kind");
        cNode.addReadAttribute(ctx, "type_id");
        cNode.addReadAttribute(ctx, "kind");
        cNode.addReadAttribute(ctx, "value");

        RubyClass cScalar = rb_yecht.defineClassUnder("Scalar", cNode, Scalar.Allocator);
        cScalar.defineAnnotatedMethods(Scalar.class);
        cScalar.addReadAttribute(ctx, "value");

        RubyClass cSeq = rb_yecht.defineClassUnder("Seq", cNode, Seq.Allocator);
        cSeq.defineAnnotatedMethods(Seq.class);

        RubyClass cMap = rb_yecht.defineClassUnder("Map", cNode, Map.Allocator);
        cMap.defineAnnotatedMethods(Map.class);

        RubyClass cPrivateType = rb_yecht.defineClassUnder("PrivateType", runtime.getObject(), runtime.getObject().getAllocator());
        cPrivateType.defineAnnotatedMethods(PrivateType.class);
        cPrivateType.addReadWriteAttribute(ctx, "type_id");
        cPrivateType.addReadWriteAttribute(ctx, "value");

        RubyClass cDomainType = rb_yecht.defineClassUnder("DomainType", runtime.getObject(), runtime.getObject().getAllocator());
        cDomainType.defineAnnotatedMethods(DomainType.class);
        cDomainType.addReadWriteAttribute(ctx, "domain");
        cDomainType.addReadWriteAttribute(ctx, "type_id");
        cDomainType.addReadWriteAttribute(ctx, "value");

        RubyClass cYObject = rb_yaml.defineClassUnder("Object", runtime.getObject(), runtime.getObject().getAllocator());
        cYObject.defineAnnotatedMethods(YObject.class);
        cYObject.addReadWriteAttribute(ctx, "class");
        cYObject.addReadWriteAttribute(ctx, "ivars");

        RubyClass cBadAlias = rb_yecht.defineClassUnder("BadAlias", runtime.getObject(), BadAlias.Allocator);
        cBadAlias.defineAnnotatedMethods(BadAlias.class);
        cBadAlias.addReadWriteAttribute(ctx, "name");
        cBadAlias.includeModule(runtime.getComparable());

        rb_yecht.defineClassUnder("MergeKey", runtime.getObject(), runtime.getObject().getAllocator());
        rb_yecht.defineClassUnder("DefaultKey", runtime.getObject(), runtime.getObject().getAllocator());

        RubyClass cOut = rb_yecht.defineClassUnder("Out", runtime.getObject(), runtime.getObject().getAllocator());
        cOut.defineAnnotatedMethods(Out.class);
        cOut.addReadWriteAttribute(ctx, "emitter");

        RubyClass cEmitter = rb_yecht.defineClassUnder("Emitter", runtime.getObject(), YEmitter.Allocator);
        cEmitter.defineAnnotatedMethods(YEmitter.class);
        cEmitter.addReadWriteAttribute(ctx, "level");

        oGenericResolver.dataWrapStruct(new GenericResolver.Extra(runtime));
        rb_yaml.dataWrapStruct(new YAMLExtra(runtime));

        return true;
    }
}
