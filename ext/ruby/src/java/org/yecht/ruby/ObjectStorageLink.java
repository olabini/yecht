package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;

class ObjectStorageLink extends StorageLink {
    final IRubyObject obj;
    final String ivarName;
    final IRubyObject originalObject;

    public ObjectStorageLink(IRubyObject obj, String ivarName, IRubyObject originalObject) {
        this.obj = obj;
        this.ivarName = ivarName;
        this.originalObject = originalObject;
    }

    public void replaceLinkWith(IRubyObject newObject) {
        obj.getInstanceVariables().setInstanceVariable(ivarName, newObject);
    }
}
