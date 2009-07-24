package org.yecht.debug;

import java.io.InputStream;
import java.io.FileInputStream;

import org.yecht.*;

public class TimeImplicit {
    public static void main(String[] args) throws Exception {
        Pointer ptr = Pointer.create("one little thing");
        Pointer ptr2 = Pointer.create("13455");
        int times = 10000000;
        long before = System.currentTimeMillis();
        for(int i=0; i<times; i++) {
            ImplicitScanner2.matchImplicit(ptr, 16);
            ImplicitScanner2.matchImplicit(ptr2, 5);
        }
        long after = System.currentTimeMillis();
        System.err.println("implicit a string and a number " + times + " times took " + (after-before) + "ms");
    }
}
