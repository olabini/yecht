package org.yecht.ruby;

import org.jruby.runtime.builtin.IRubyObject;

public interface PossibleLinkNode {
    void addLink(StorageLink link);
    void replaceLinks(IRubyObject newObject);
}
