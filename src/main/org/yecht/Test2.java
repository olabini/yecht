package org.yecht;

import java.io.InputStream;
import java.io.FileInputStream;

public class Test2 {
    public static void main(String[] args) throws Exception {
        String filename = args[0];
        int len = 8000;
        int read = 0;
        int currRead = 0;
        byte[] buffer = new byte[1024];
        byte[] input = new byte[len];
        InputStream is = new FileInputStream(filename);
        while((currRead = is.read(buffer, 0, 1024)) != -1) {
            if(read + currRead >= len) {
                len *= 2;
                input = YAML.realloc(input, len);
            }
            System.arraycopy(buffer, 0, input, read, currRead);
            read += currRead;
        }
        int times = 100;
        long before = System.currentTimeMillis();
        for(int i=0; i<times; i++) {
            Parser parser = Parser.newParser();
            parser.str(Pointer.create(input, 0), read, null);
            parser.handler(new NullNodeHandler());
            parser.errorHandler(null);
            parser.implicitTyping(true);
            parser.taguriExpansion(true);
            Scanner s = TokenScanner.createScanner(parser);
            int tok = -1;
            while(tok != YAMLGrammarTokens.ENDINPUT) {
                tok = s.yylex();
//                 Object lval = s.getLVal();
//                 System.err.println("tok: " + TokenScanner.tnames[tok] + " lval: " + lval);
            }
        }
        long after = System.currentTimeMillis();
        System.err.println("scanning " + filename + " " + times + " times took " + (after-before) + "ms");
    }
}
