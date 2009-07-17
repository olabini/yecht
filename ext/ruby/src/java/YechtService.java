
import java.io.IOException;

import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

import org.yecht.YAML;
import org.yecht.ruby.YechtYAML;

public class YechtService implements BasicLibraryService {
    public boolean basicLoad(final Ruby runtime) throws IOException {
        ThreadContext ctx = runtime.getCurrentContext();

        RubyModule rb_yaml = runtime.getOrCreateModule("YAML");
        RubyModule rb_yecht = rb_yaml.defineModuleUnder("Yecht");
        rb_yecht.defineConstant("VERSION", runtime.newString(YAML.YECHT_VERSION));
        rb_yecht.defineAnnotatedMethods(YechtYAML.Module.class);

        RubyClass cResolver = rb_yecht.defineClassUnder("Resolver", runtime.getObject(), runtime.getObject().getAllocator());
        cResolver.defineAnnotatedMethods(YechtYAML.Resolver.class);
        cResolver.addReadWriteAttribute(ctx, "tags");

        IRubyObject oDefaultResolver = cResolver.callMethod(ctx, "new");
        oDefaultResolver.getSingletonClass().defineAnnotatedMethods(YechtYAML.DefaultResolver.class);
        rb_yecht.defineConstant("DefaultResolver", oDefaultResolver);
        
        IRubyObject oGenericResolver = cResolver.callMethod(ctx, "new");
        oGenericResolver.getSingletonClass().defineAnnotatedMethods(YechtYAML.GenericResolver.class);
        rb_yecht.defineConstant("GenericResolver", oGenericResolver);

        RubyClass cParser = rb_yecht.defineClassUnder("Parser", runtime.getObject(), YechtYAML.YParser.Allocator);
        cParser.defineAnnotatedMethods(YechtYAML.YParser.class);
        cParser.addReadWriteAttribute(ctx, "options");
        cParser.addReadWriteAttribute(ctx, "resolver");
        cParser.addReadWriteAttribute(ctx, "input");

        RubyClass cNode = rb_yecht.defineClassUnder("Node", runtime.getObject(), runtime.getObject().getAllocator());
        cNode.defineAnnotatedMethods(YechtYAML.Node.class);
        cNode.addReadWriteAttribute(ctx, "emitter");
        cNode.addReadWriteAttribute(ctx, "resolver");
        cNode.addReadAttribute(ctx, "kind");
        cNode.addReadAttribute(ctx, "type_id");
        cNode.addReadAttribute(ctx, "kind");
        cNode.addReadAttribute(ctx, "value");

        RubyClass cScalar = rb_yecht.defineClassUnder("Scalar", cNode, YechtYAML.Scalar.Allocator);
        cScalar.defineAnnotatedMethods(YechtYAML.Scalar.class);
        cScalar.addReadAttribute(ctx, "value");

        RubyClass cSeq = rb_yecht.defineClassUnder("Seq", cNode, YechtYAML.Seq.Allocator);
        cSeq.defineAnnotatedMethods(YechtYAML.Seq.class);

        RubyClass cMap = rb_yecht.defineClassUnder("Map", cNode, YechtYAML.Map.Allocator);
        cMap.defineAnnotatedMethods(YechtYAML.Map.class);

        RubyClass cPrivateType = rb_yecht.defineClassUnder("PrivateType", runtime.getObject(), runtime.getObject().getAllocator());
        cPrivateType.defineAnnotatedMethods(YechtYAML.PrivateType.class);
        cPrivateType.addReadWriteAttribute(ctx, "type_id");
        cPrivateType.addReadWriteAttribute(ctx, "value");

        RubyClass cDomainType = rb_yecht.defineClassUnder("DomainType", runtime.getObject(), runtime.getObject().getAllocator());
        cDomainType.defineAnnotatedMethods(YechtYAML.DomainType.class);
        cDomainType.addReadWriteAttribute(ctx, "domain");
        cDomainType.addReadWriteAttribute(ctx, "type_id");
        cDomainType.addReadWriteAttribute(ctx, "value");

        RubyClass cYObject = rb_yaml.defineClassUnder("Object", runtime.getObject(), runtime.getObject().getAllocator());
        cYObject.defineAnnotatedMethods(YechtYAML.YObject.class);
        cYObject.addReadWriteAttribute(ctx, "class");
        cYObject.addReadWriteAttribute(ctx, "ivars");

        RubyClass cBadAlias = rb_yecht.defineClassUnder("BadAlias", runtime.getObject(), runtime.getObject().getAllocator());
        cBadAlias.defineAnnotatedMethods(YechtYAML.BadAlias.class);
        cBadAlias.addReadWriteAttribute(ctx, "name");
        cBadAlias.includeModule(runtime.getComparable());

        rb_yecht.defineClassUnder("MergeKey", runtime.getObject(), runtime.getObject().getAllocator());
        rb_yecht.defineClassUnder("DefaultKey", runtime.getObject(), runtime.getObject().getAllocator());

        RubyClass cOut = rb_yecht.defineClassUnder("Out", runtime.getObject(), runtime.getObject().getAllocator());
        cOut.defineAnnotatedMethods(YechtYAML.Out.class);
        cOut.addReadWriteAttribute(ctx, "emitter");

        RubyClass cEmitter = rb_yecht.defineClassUnder("Emitter", runtime.getObject(), YechtYAML.YEmitter.Allocator);
        cEmitter.defineAnnotatedMethods(YechtYAML.YEmitter.class);
        cEmitter.addReadWriteAttribute(ctx, "level");

        return true;
    }
}
