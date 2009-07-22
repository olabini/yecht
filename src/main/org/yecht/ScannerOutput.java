package org.yecht;

import java.io.InputStream;
import java.io.FileInputStream;

public class ScannerOutput {
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

        Parser parser = Parser.newParser();
        parser.str(Pointer.create(input, 0), read, null);
        parser.handler(new NullNodeHandler());
        parser.errorHandler(null);
        parser.implicitTyping(true);
        parser.taguriExpansion(true);
        Scanner s = TokenScanner.createScanner(parser);
        int tok = -1;
        Object lval = null;
        int indent = 0;
        while(tok != YAMLGrammarTokens.ENDINPUT) {
            tok = s.yylex();
            if(tok == YAMLGrammarTokens.YAML_IOPEN) {
                for(int i=0; i < indent; i++) {
                    System.err.print(" ");
                }
                indent++;
            } else if(tok == YAMLGrammarTokens.YAML_IEND) {
                indent--;
                for(int i=0; i < indent; i++) {
                    System.err.print(" ");
                }
            } else {
                for(int i=0; i < indent; i++) {
                    System.err.print(" ");
                }
            }

            Object lval2 = s.getLVal();
            System.err.print("tok: " + TokenScanner.tnames[tok]);
            if(lval != lval2) {
                System.err.print(" lval: " + lval2);
                lval = lval2;
            }
            System.err.println();
        }
    }
}
