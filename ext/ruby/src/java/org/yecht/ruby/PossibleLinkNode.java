package org.yecht.ruby;

import java.util.List;
import org.jruby.runtime.builtin.IRubyObject;

public interface PossibleLinkNode {
    List<StorageLink> getLinks();
    void addLink(StorageLink link);
    void replaceLinks(IRubyObject newObject);
}
