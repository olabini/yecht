package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;

abstract class StorageLink {
    public abstract void replaceLinkWith(IRubyObject object);
}
