package org.yecht.ruby;

import org.jruby.RubyArray;
import org.jruby.runtime.builtin.IRubyObject;

class ArrayStorageLink extends StorageLink {
    private final RubyArray array;
    private final int index;
    private final IRubyObject originalObject;

    public ArrayStorageLink(IRubyObject arr, int index, IRubyObject originalObject) {
        this.array = (RubyArray)arr;
        this.index = index;
        this.originalObject = originalObject;
    }

    public void replaceLinkWith(IRubyObject newObject) {
        array.store(index, newObject);
    }
}

