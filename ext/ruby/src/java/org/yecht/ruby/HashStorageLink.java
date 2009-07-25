package org.yecht.ruby;

import org.jruby.RubyHash;
import org.jruby.runtime.builtin.IRubyObject;

class HashStorageLink extends StorageLink {
    private final RubyHash hash;
    private final IRubyObject key;
    private final IRubyObject originalObject;

    public HashStorageLink(IRubyObject h, IRubyObject key, IRubyObject originalObject) {
        this.hash = (RubyHash)h;
        this.key = key;
        this.originalObject = originalObject;
    }

    public void replaceLinkWith(IRubyObject newObject) {
        hash.fastASet(key, newObject);
    }
}
