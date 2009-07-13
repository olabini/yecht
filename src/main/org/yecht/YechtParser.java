// Output created by jacc on Mon Jul 13 12:00:30 CEST 2009

package org.yecht;

class YechtParser implements YAMLGrammarTokens {
    private int yyss = 100;
    private int yytok;
    private int yysp = 0;
    private int[] yyst;
    protected int yyerrno = (-1);
    private Object[] yysv;
    private Object yyrv;

    public boolean parse() {
        int yyn = 0;
        yysp = 0;
        yyst = new int[yyss];
        yysv = new Object[yyss];
        yytok = (lexer.currentToken()
                 );
    loop:
        for (;;) {
            switch (yyn) {
                case 0:
                    yyst[yysp] = 0;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 127:
                    yyn = yys0();
                    continue;

                case 1:
                    yyst[yysp] = 1;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 128:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = 254;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 2:
                    yyst[yysp] = 2;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 129:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr1();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 3:
                    yyst[yysp] = 3;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 130:
                    yyn = yys3();
                    continue;

                case 4:
                    yyst[yysp] = 4;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 131:
                    yyn = yys4();
                    continue;

                case 5:
                    yyst[yysp] = 5;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 132:
                    yyn = yys5();
                    continue;

                case 6:
                    yyst[yysp] = 6;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 133:
                    yyn = yys6();
                    continue;

                case 7:
                    yyst[yysp] = 7;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 134:
                    yyn = yys7();
                    continue;

                case 8:
                    yyst[yysp] = 8;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 135:
                    yyn = yys8();
                    continue;

                case 9:
                    yyst[yysp] = 9;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 136:
                    yyn = yys9();
                    continue;

                case 10:
                    yyst[yysp] = 10;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 137:
                    yyn = yys10();
                    continue;

                case 11:
                    yyst[yysp] = 11;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 138:
                    yyn = yys11();
                    continue;

                case 12:
                    yyst[yysp] = 12;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 139:
                    yyn = yys12();
                    continue;

                case 13:
                    yyst[yysp] = 13;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 140:
                    yyn = yys13();
                    continue;

                case 14:
                    yyst[yysp] = 14;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 141:
                    yyn = yys14();
                    continue;

                case 15:
                    yyst[yysp] = 15;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 142:
                    yyn = yys15();
                    continue;

                case 16:
                    yyst[yysp] = 16;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 143:
                    yyn = yys16();
                    continue;

                case 17:
                    yyst[yysp] = 17;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 144:
                    yyn = yys17();
                    continue;

                case 18:
                    yyst[yysp] = 18;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 145:
                    yyn = yys18();
                    continue;

                case 19:
                    yyst[yysp] = 19;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 146:
                    yyn = yys19();
                    continue;

                case 20:
                    yyst[yysp] = 20;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 147:
                    yyn = yys20();
                    continue;

                case 21:
                    yyst[yysp] = 21;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 148:
                    yyn = yys21();
                    continue;

                case 22:
                    yyst[yysp] = 22;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 149:
                    yyn = yys22();
                    continue;

                case 23:
                    yyst[yysp] = 23;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 150:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr47();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 24:
                    yyst[yysp] = 24;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 151:
                    switch (yytok) {
                        case ':':
                            yyn = 67;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 25:
                    yyst[yysp] = 25;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 152:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr68();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 26:
                    yyst[yysp] = 26;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 153:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = 70;
                            continue;
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 27:
                    yyst[yysp] = 27;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 154:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = 70;
                            continue;
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 28:
                    yyst[yysp] = 28;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 155:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = 76;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 29:
                    yyst[yysp] = 29;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 156:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = 70;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 30:
                    yyst[yysp] = 30;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 157:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = 70;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 31:
                    yyst[yysp] = 31;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 158:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = 76;
                            continue;
                        case ':':
                            yyn = yyr64();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 32:
                    yyst[yysp] = 32;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 159:
                    yyn = yys32();
                    continue;

                case 33:
                    yyst[yysp] = 33;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 160:
                    yyn = yys33();
                    continue;

                case 34:
                    yyst[yysp] = 34;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 161:
                    yyn = yys34();
                    continue;

                case 35:
                    yyst[yysp] = 35;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 162:
                    yyn = yys35();
                    continue;

                case 36:
                    yyst[yysp] = 36;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 163:
                    yyn = yys36();
                    continue;

                case 37:
                    yyst[yysp] = 37;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 164:
                    yyn = yys37();
                    continue;

                case 38:
                    yyst[yysp] = 38;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 165:
                    yyn = yys38();
                    continue;

                case 39:
                    yyst[yysp] = 39;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 166:
                    yyn = yys39();
                    continue;

                case 40:
                    yyst[yysp] = 40;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 167:
                    switch (yytok) {
                        case ENDINPUT:
                            yyn = yyr2();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 41:
                    yyst[yysp] = 41;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 168:
                    yyn = yys41();
                    continue;

                case 42:
                    yyst[yysp] = 42;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 169:
                    yyn = yys42();
                    continue;

                case 43:
                    yyst[yysp] = 43;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 170:
                    yyn = yys43();
                    continue;

                case 44:
                    yyst[yysp] = 44;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 171:
                    yyn = yys44();
                    continue;

                case 45:
                    yyst[yysp] = 45;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 172:
                    yyn = yys45();
                    continue;

                case 46:
                    yyst[yysp] = 46;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 173:
                    yyn = yys46();
                    continue;

                case 47:
                    yyst[yysp] = 47;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 174:
                    yyn = yys47();
                    continue;

                case 48:
                    yyst[yysp] = 48;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 175:
                    yyn = yys48();
                    continue;

                case 49:
                    yyst[yysp] = 49;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 176:
                    yyn = yys49();
                    continue;

                case 50:
                    yyst[yysp] = 50;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 177:
                    yyn = yys50();
                    continue;

                case 51:
                    yyst[yysp] = 51;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 178:
                    yyn = yys51();
                    continue;

                case 52:
                    yyst[yysp] = 52;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 179:
                    yyn = yys52();
                    continue;

                case 53:
                    yyst[yysp] = 53;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 180:
                    yyn = yys53();
                    continue;

                case 54:
                    yyst[yysp] = 54;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 181:
                    yyn = yys54();
                    continue;

                case 55:
                    yyst[yysp] = 55;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 182:
                    yyn = yys55();
                    continue;

                case 56:
                    yyst[yysp] = 56;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 183:
                    yyn = yys56();
                    continue;

                case 57:
                    yyst[yysp] = 57;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 184:
                    switch (yytok) {
                        case ',':
                        case ']':
                            yyn = yyr55();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 58:
                    yyst[yysp] = 58;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 185:
                    switch (yytok) {
                        case ':':
                            yyn = 104;
                            continue;
                        case ',':
                        case ']':
                            yyn = yyr54();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 59:
                    yyst[yysp] = 59;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 186:
                    switch (yytok) {
                        case ',':
                            yyn = 105;
                            continue;
                        case ']':
                            yyn = 106;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 60:
                    yyst[yysp] = 60;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 187:
                    switch (yytok) {
                        case ',':
                        case ']':
                            yyn = yyr52();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 61:
                    yyst[yysp] = 61;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 188:
                    yyn = yys61();
                    continue;

                case 62:
                    yyst[yysp] = 62;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 189:
                    switch (yytok) {
                        case ',':
                        case '}':
                            yyn = yyr78();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 63:
                    yyst[yysp] = 63;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 190:
                    switch (yytok) {
                        case ':':
                            yyn = 104;
                            continue;
                        case ',':
                        case '}':
                            yyn = yyr77();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 64:
                    yyst[yysp] = 64;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 191:
                    switch (yytok) {
                        case ',':
                            yyn = 107;
                            continue;
                        case '}':
                            yyn = 108;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 65:
                    yyst[yysp] = 65;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 192:
                    switch (yytok) {
                        case ',':
                        case '}':
                            yyn = yyr75();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 66:
                    yyst[yysp] = 66;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 193:
                    yyn = yys66();
                    continue;

                case 67:
                    yyst[yysp] = 67;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 194:
                    yyn = yys67();
                    continue;

                case 68:
                    yyst[yysp] = 68;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 195:
                    yyn = yys68();
                    continue;

                case 69:
                    yyst[yysp] = 69;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 196:
                    yyn = yys69();
                    continue;

                case 70:
                    yyst[yysp] = 70;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 197:
                    yyn = yys70();
                    continue;

                case 71:
                    yyst[yysp] = 71;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 198:
                    yyn = yys71();
                    continue;

                case 72:
                    yyst[yysp] = 72;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 199:
                    yyn = yys72();
                    continue;

                case 73:
                    yyst[yysp] = 73;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 200:
                    switch (yytok) {
                        case '-':
                            yyn = 36;
                            continue;
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr49();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 74:
                    yyst[yysp] = 74;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 201:
                    yyn = yys74();
                    continue;

                case 75:
                    yyst[yysp] = 75;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 202:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = 76;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 76:
                    yyst[yysp] = 76;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 203:
                    yyn = yys76();
                    continue;

                case 77:
                    yyst[yysp] = 77;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 204:
                    yyn = yys77();
                    continue;

                case 78:
                    yyst[yysp] = 78;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 205:
                    yyn = yys78();
                    continue;

                case 79:
                    yyst[yysp] = 79;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 206:
                    yyn = yys79();
                    continue;

                case 80:
                    yyst[yysp] = 80;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 207:
                    yyn = yys80();
                    continue;

                case 81:
                    yyst[yysp] = 81;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 208:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr63();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 82:
                    yyst[yysp] = 82;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 209:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr46();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 83:
                    yyst[yysp] = 83;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 210:
                    yyn = yys83();
                    continue;

                case 84:
                    yyst[yysp] = 84;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 211:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr61();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 85:
                    yyst[yysp] = 85;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 212:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr44();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 86:
                    yyst[yysp] = 86;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 213:
                    yyn = yys86();
                    continue;

                case 87:
                    yyst[yysp] = 87;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 214:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr59();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 88:
                    yyst[yysp] = 88;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 215:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = yyr42();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 89:
                    yyst[yysp] = 89;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 216:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr40();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 90:
                    yyst[yysp] = 90;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 217:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 91:
                    yyst[yysp] = 91;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 218:
                    switch (yytok) {
                        case YAML_IEND:
                            yyn = 70;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 92:
                    yyst[yysp] = 92;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 219:
                    yyn = yys92();
                    continue;

                case 93:
                    yyst[yysp] = 93;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 220:
                    yyn = yys93();
                    continue;

                case 94:
                    yyst[yysp] = 94;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 221:
                    yyn = yys94();
                    continue;

                case 95:
                    yyst[yysp] = 95;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 222:
                    yyn = yys95();
                    continue;

                case 96:
                    yyst[yysp] = 96;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 223:
                    yyn = yys96();
                    continue;

                case 97:
                    yyst[yysp] = 97;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 224:
                    yyn = yys97();
                    continue;

                case 98:
                    yyst[yysp] = 98;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 225:
                    yyn = yys98();
                    continue;

                case 99:
                    yyst[yysp] = 99;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 226:
                    yyn = yys99();
                    continue;

                case 100:
                    yyst[yysp] = 100;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 227:
                    yyn = yys100();
                    continue;

                case 101:
                    yyst[yysp] = 101;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 228:
                    yyn = yys101();
                    continue;

                case 102:
                    yyst[yysp] = 102;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 229:
                    yyn = yys102();
                    continue;

                case 103:
                    yyst[yysp] = 103;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 230:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = 76;
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 104:
                    yyst[yysp] = 104;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 231:
                    yyn = yys104();
                    continue;

                case 105:
                    yyst[yysp] = 105;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 232:
                    yyn = yys105();
                    continue;

                case 106:
                    yyst[yysp] = 106;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 233:
                    yyn = yys106();
                    continue;

                case 107:
                    yyst[yysp] = 107;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 234:
                    yyn = yys107();
                    continue;

                case 108:
                    yyst[yysp] = 108;
                    yysv[yysp] = (lexer.getLVal()
                                 );
                    yytok = (lexer.yylex()
                            );
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 235:
                    yyn = yys108();
                    continue;

                case 109:
                    yyst[yysp] = 109;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 236:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr66();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 110:
                    yyst[yysp] = 110;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 237:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr67();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 111:
                    yyst[yysp] = 111;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 238:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr69();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 112:
                    yyst[yysp] = 112;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 239:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr70();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 113:
                    yyst[yysp] = 113;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 240:
                    switch (yytok) {
                        case ':':
                            yyn = yyr64();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 114:
                    yyst[yysp] = 114;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 241:
                    switch (yytok) {
                        case YAML_INDENT:
                        case YAML_IEND:
                            yyn = yyr48();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 115:
                    yyst[yysp] = 115;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 242:
                    yyn = yys115();
                    continue;

                case 116:
                    yyst[yysp] = 116;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 243:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr62();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 117:
                    yyst[yysp] = 117;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 244:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr45();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 118:
                    yyst[yysp] = 118;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 245:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr60();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 119:
                    yyst[yysp] = 119;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 246:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr43();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 120:
                    yyst[yysp] = 120;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 247:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr58();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 121:
                    yyst[yysp] = 121;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 248:
                    switch (yytok) {
                        case YAML_INDENT:
                            yyn = 71;
                            continue;
                        case YAML_IEND:
                            yyn = yyr41();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 122:
                    yyst[yysp] = 122;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 249:
                    switch (yytok) {
                        case ':':
                            yyn = yyr65();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 123:
                    yyst[yysp] = 123;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 250:
                    yyn = yys123();
                    continue;

                case 124:
                    yyst[yysp] = 124;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 251:
                    switch (yytok) {
                        case ',':
                        case '}':
                        case ']':
                            yyn = yyr72();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 125:
                    yyst[yysp] = 125;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 252:
                    switch (yytok) {
                        case ',':
                        case ']':
                            yyn = yyr53();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 126:
                    yyst[yysp] = 126;
                    if (++yysp>=yyst.length) {
                        yyexpand();
                    }
                case 253:
                    switch (yytok) {
                        case ',':
                        case '}':
                            yyn = yyr76();
                            continue;
                    }
                    yyn = 257;
                    continue;

                case 254:
                    return true;
                case 255:
                    yyerror("stack overflow");
                case 256:
                    return false;
                case 257:
                    yyerror("syntax error");
                    return false;
            }
        }
    }

    protected void yyexpand() {
        int[] newyyst = new int[2*yyst.length];
        Object[] newyysv = new Object[2*yyst.length];
        for (int i=0; i<yyst.length; i++) {
            newyyst[i] = yyst[i];
            newyysv[i] = yysv[i];
        }
        yyst = newyyst;
        yysv = newyysv;
    }

    private int yys0() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_DOCSEP:
                return 14;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case ENDINPUT:
                return yyr3();
        }
        return 257;
    }

    private int yys3() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr36();
        }
        return 257;
    }

    private int yys4() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr34();
        }
        return 257;
    }

    private int yys5() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr5();
        }
        return 257;
    }

    private int yys6() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 32;
            case YAML_INDENT:
                return 33;
            case YAML_TAGURI:
                return 34;
            case YAML_TRANSFER:
                return 35;
            case '-':
                return 36;
            case '?':
                return 37;
        }
        return 257;
    }

    private int yys7() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr37();
        }
        return 257;
    }

    private int yys8() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr35();
        }
        return 257;
    }

    private int yys9() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr6();
        }
        return 257;
    }

    private int yys10() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr4();
        }
        return 257;
    }

    private int yys11() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr29();
        }
        return 257;
    }

    private int yys12() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys13() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr33();
        }
        return 257;
    }

    private int yys14() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
            case ENDINPUT:
                return yyr14();
        }
        return 257;
    }

    private int yys15() {
        switch (yytok) {
            case error:
            case ']':
            case '}':
            case ',':
            case ':':
            case YAML_DOCSEP:
            case ENDINPUT:
                return 257;
        }
        return yyr19();
    }

    private int yys16() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys17() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr31();
        }
        return 257;
    }

    private int yys18() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys19() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys20() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr30();
        }
        return 257;
    }

    private int yys21() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case ']':
                return 61;
        }
        return 257;
    }

    private int yys22() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case '}':
                return 66;
        }
        return 257;
    }

    private int yys32() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 32;
            case YAML_TAGURI:
                return 34;
            case YAML_TRANSFER:
                return 35;
            case YAML_INDENT:
                return 71;
        }
        return 257;
    }

    private int yys33() {
        switch (yytok) {
            case error:
            case ']':
            case '}':
            case ',':
            case ':':
            case YAML_DOCSEP:
            case ENDINPUT:
                return 257;
        }
        return yyr20();
    }

    private int yys34() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 32;
            case YAML_TAGURI:
                return 34;
            case YAML_TRANSFER:
                return 35;
            case YAML_INDENT:
                return 71;
        }
        return 257;
    }

    private int yys35() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 32;
            case YAML_TAGURI:
                return 34;
            case YAML_TRANSFER:
                return 35;
            case YAML_INDENT:
                return 71;
        }
        return 257;
    }

    private int yys36() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
            case YAML_INDENT:
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys37() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys38() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr9();
        }
        return 257;
    }

    private int yys39() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr28();
        }
        return 257;
    }

    private int yys41() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr11();
        }
        return 257;
    }

    private int yys42() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr12();
        }
        return 257;
    }

    private int yys43() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_INDENT:
                return 33;
            case '-':
                return 36;
            case '?':
                return 37;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 92;
            case YAML_TAGURI:
                return 93;
            case YAML_TRANSFER:
                return 94;
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys44() {
        switch (yytok) {
            case YAML_DOCSEP:
            case ':':
            case error:
            case '-':
            case '?':
                return 257;
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
        }
        return yyr14();
    }

    private int yys45() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 98;
            case YAML_TAGURI:
                return 99;
            case YAML_TRANSFER:
                return 100;
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr14();
        }
        return 257;
    }

    private int yys46() {
        switch (yytok) {
            case YAML_DOCSEP:
            case ':':
            case error:
            case '-':
            case '?':
                return 257;
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
        }
        return yyr14();
    }

    private int yys47() {
        switch (yytok) {
            case YAML_DOCSEP:
            case ':':
            case error:
            case '-':
            case '?':
                return 257;
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
        }
        return yyr14();
    }

    private int yys48() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_INDENT:
                return 33;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys49() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr27();
        }
        return 257;
    }

    private int yys50() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys51() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys52() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys53() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr8();
        }
        return 257;
    }

    private int yys54() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr26();
        }
        return 257;
    }

    private int yys55() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr7();
        }
        return 257;
    }

    private int yys56() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr25();
        }
        return 257;
    }

    private int yys61() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr51();
        }
        return 257;
    }

    private int yys66() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr74();
        }
        return 257;
    }

    private int yys67() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
            case YAML_INDENT:
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys68() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr57();
        }
        return 257;
    }

    private int yys69() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '-':
                return 36;
            case '?':
                return 37;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
            case YAML_INDENT:
            case YAML_IEND:
                return yyr71();
        }
        return 257;
    }

    private int yys70() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr21();
        }
        return 257;
    }

    private int yys71() {
        switch (yytok) {
            case ',':
            case ENDINPUT:
            case ']':
            case YAML_DOCSEP:
            case error:
            case YAML_BLOCK:
            case '}':
            case '{':
            case '[':
                return 257;
        }
        return yyr22();
    }

    private int yys72() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr39();
        }
        return 257;
    }

    private int yys74() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr10();
        }
        return 257;
    }

    private int yys76() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr23();
        }
        return 257;
    }

    private int yys77() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr56();
        }
        return 257;
    }

    private int yys78() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr38();
        }
        return 257;
    }

    private int yys79() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr32();
        }
        return 257;
    }

    private int yys80() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '-':
                return 36;
            case '?':
                return 37;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys83() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '-':
                return 36;
            case '?':
                return 37;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys86() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '-':
                return 36;
            case '?':
                return 37;
            case YAML_ANCHOR:
                return 50;
            case YAML_TAGURI:
                return 51;
            case YAML_TRANSFER:
                return 52;
        }
        return 257;
    }

    private int yys92() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ITRANSFER:
                return 45;
            case YAML_INDENT:
                return 71;
            case YAML_ANCHOR:
                return 92;
            case YAML_TAGURI:
                return 93;
            case YAML_TRANSFER:
                return 94;
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys93() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ITRANSFER:
                return 45;
            case YAML_INDENT:
                return 71;
            case YAML_ANCHOR:
                return 92;
            case YAML_TAGURI:
                return 93;
            case YAML_TRANSFER:
                return 94;
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys94() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ITRANSFER:
                return 45;
            case YAML_INDENT:
                return 71;
            case YAML_ANCHOR:
                return 92;
            case YAML_TAGURI:
                return 93;
            case YAML_TRANSFER:
                return 94;
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys95() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr18();
        }
        return 257;
    }

    private int yys96() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr15();
        }
        return 257;
    }

    private int yys97() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_INDENT:
                return 33;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 98;
            case YAML_TAGURI:
                return 99;
            case YAML_TRANSFER:
                return 100;
            case YAML_IEND:
                return yyr14();
        }
        return 257;
    }

    private int yys98() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 98;
            case YAML_TAGURI:
                return 99;
            case YAML_TRANSFER:
                return 100;
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr14();
        }
        return 257;
    }

    private int yys99() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 98;
            case YAML_TAGURI:
                return 99;
            case YAML_TRANSFER:
                return 100;
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr14();
        }
        return 257;
    }

    private int yys100() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case YAML_ITRANSFER:
                return 45;
            case YAML_ANCHOR:
                return 98;
            case YAML_TAGURI:
                return 99;
            case YAML_TRANSFER:
                return 100;
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr14();
        }
        return 257;
    }

    private int yys101() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr17();
        }
        return 257;
    }

    private int yys102() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr16();
        }
        return 257;
    }

    private int yys104() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_PLAIN:
                return 17;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
            case YAML_ANCHOR:
                return 44;
            case YAML_ITRANSFER:
                return 45;
            case YAML_TAGURI:
                return 46;
            case YAML_TRANSFER:
                return 47;
            case ',':
            case '}':
            case ']':
                return yyr14();
        }
        return 257;
    }

    private int yys105() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys106() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr50();
        }
        return 257;
    }

    private int yys107() {
        switch (yytok) {
            case YAML_ALIAS:
                return 11;
            case YAML_ANCHOR:
                return 12;
            case YAML_BLOCK:
                return 13;
            case YAML_IOPEN:
                return 15;
            case YAML_ITRANSFER:
                return 16;
            case YAML_PLAIN:
                return 17;
            case YAML_TAGURI:
                return 18;
            case YAML_TRANSFER:
                return 19;
            case YAML_WORD:
                return 20;
            case '[':
                return 21;
            case '{':
                return 22;
        }
        return 257;
    }

    private int yys108() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr73();
        }
        return 257;
    }

    private int yys115() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
            case ':':
                return yyr24();
        }
        return 257;
    }

    private int yys123() {
        switch (yytok) {
            case ',':
            case YAML_INDENT:
            case ENDINPUT:
            case '}':
            case YAML_IEND:
            case ']':
                return yyr13();
        }
        return 257;
    }

    private int yyr1() { // doc : atom
        {
           parser.root = parser.addNode((Node)yysv[yysp-1]);
        }
        yysv[yysp-=1] = yyrv;
        return 1;
    }

    private int yyr2() { // doc : YAML_DOCSEP atom_or_empty
        {
           parser.root = parser.addNode((Node)yysv[yysp-1]);
        }
        yysv[yysp-=2] = yyrv;
        return 1;
    }

    private int yyr3() { // doc : /* empty */
        {
           parser.eof = true;
        }
        yysv[yysp-=0] = yyrv;
        return 1;
    }

    private int yyr11() { // atom_or_empty : atom
        yysp -= 1;
        return yypatom_or_empty();
    }

    private int yyr12() { // atom_or_empty : empty
        yysp -= 1;
        return yypatom_or_empty();
    }

    private int yypatom_or_empty() {
        switch (yyst[yysp-1]) {
            case 67: return 109;
            case 36: return 89;
            case 14: return 40;
            default: return 124;
        }
    }

    private int yyr72() { // basic_mapping : atom ':' atom_or_empty
        {
                    yyrv = Node.newMap( 
                        parser.addNode( (Node)yysv[yysp-3] ), 
                        parser.addNode( (Node)yysv[yysp-1] ) );
                }
        yysv[yysp-=3] = yyrv;
        switch (yyst[yysp-1]) {
            case 105: return 57;
            case 21: return 57;
            default: return 62;
        }
    }

    private int yyr40() { // basic_seq : '-' atom_or_empty
        { 
                    yyrv = parser.addNode((Node) yysv[yysp-1]);
                }
        yysv[yysp-=2] = yyrv;
        switch (yyst[yysp-1]) {
            case 73: return 114;
            case 69: return 111;
            default: return 23;
        }
    }

    private int yyr64() { // complex_key : word_rep
        yysp -= 1;
        return 24;
    }

    private int yyr65() { // complex_key : '?' atom indent_sep
        {
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 24;
    }

    private int yyr67() { // complex_mapping : complex_key ':' complex_value
        {
                    yyrv = Node.newMap( 
                        parser.addNode( (Node)yysv[yysp-3] ), 
                        parser.addNode( (Node)yysv[yysp-1] ) );
                }
        yysv[yysp-=3] = yyrv;
        switch (yyst[yysp-1]) {
            case 69: return 112;
            default: return 25;
        }
    }

    private int yyr66() { // complex_value : atom_or_empty
        yysp -= 1;
        return 110;
    }

    private int yyr4() { // atom : word_rep
        yysp -= 1;
        return yypatom();
    }

    private int yyr5() { // atom : ind_rep
        yysp -= 1;
        return yypatom();
    }

    private int yypatom() {
        switch (yyst[yysp-1]) {
            case 107: return 63;
            case 105: return 58;
            case 37: return 90;
            case 22: return 63;
            case 21: return 58;
            case 0: return 2;
            default: return 41;
        }
    }

    private int yyr13() { // empty : indent_open empty indent_end
        {
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return yypempty();
    }

    private int yyr14() { // empty : /* empty */
        {
                    Node n = NULL_NODE( parser );
                    yyrv = n;
                }
        yysv[yysp-=0] = yyrv;
        return yypempty();
    }

    private int yyr15() { // empty : YAML_ITRANSFER empty
        { 
                   if(parser.implicit_typing)
                   {
                      ImplicitScanner.tryTagImplicit((Node)yysv[yysp-1], parser.taguri_expansion);
                   }
                   yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yypempty();
    }

    private int yyr16() { // empty : YAML_TRANSFER empty
        { 
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], parser.taguri_expansion);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yypempty();
    }

    private int yyr17() { // empty : YAML_TAGURI empty
        {
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], false);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yypempty();
    }

    private int yyr18() { // empty : YAML_ANCHOR empty
        { 
                   /*
                    * _Anchors_: The language binding must keep a separate symbol table
                    * for anchors.  The actual ID in the symbol table is returned to the
                    * higher nodes, though.
                    */
                   yyrv = parser.addAnchor((String)yysv[yysp-2], (Node)yysv[yysp-1] );
                }
        yysv[yysp-=2] = yyrv;
        return yypempty();
    }

    private int yypempty() {
        switch (yyst[yysp-1]) {
            case 100: return 102;
            case 99: return 101;
            case 98: return 95;
            case 97: return 91;
            case 94: return 102;
            case 93: return 101;
            case 92: return 95;
            case 47: return 102;
            case 46: return 101;
            case 45: return 96;
            case 44: return 95;
            case 43: return 91;
            default: return 42;
        }
    }

    private int yyr56() { // implicit_map : indent_open top_imp_map indent_end
        { 
                    applySeqInMap(parser, (Node)yysv[yysp-2]);
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr57() { // implicit_map : indent_open in_implicit_map indent_end
        { 
                    applySeqInMap(parser, (Node)yysv[yysp-2]);
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 3;
    }

    private int yyr38() { // implicit_seq : indent_open top_imp_seq indent_end
        { 
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 4;
    }

    private int yyr39() { // implicit_seq : indent_open in_implicit_seq indent_end
        { 
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 4;
    }

    private int yyr68() { // in_implicit_map : complex_mapping
        yysp -= 1;
        return yypin_implicit_map();
    }

    private int yyr69() { // in_implicit_map : in_implicit_map indent_sep basic_seq
        {
                    if ( ((Node)yysv[yysp-3]).shortcut == null )
                    {
                        ((Node)yysv[yysp-3]).shortcut = Node.newSeq( ((Long)yysv[yysp-1]).longValue() );
                    }
                    else
                    {
                        ((Node)((Node)yysv[yysp-3]).shortcut).seqAdd( ((Long)yysv[yysp-1]).longValue() );
                    }
                    yyrv = yysv[yysp-3];
                }
        yysv[yysp-=3] = yyrv;
        return yypin_implicit_map();
    }

    private int yyr70() { // in_implicit_map : in_implicit_map indent_sep complex_mapping
        {
                    applySeqInMap( parser, (Node)yysv[yysp-3] );
                    ((Node)yysv[yysp-3]).mapUpdate((Node)yysv[yysp-1]);
                    yysv[yysp-1] = null;
                    yyrv = yysv[yysp-3];
                }
        yysv[yysp-=3] = yyrv;
        return yypin_implicit_map();
    }

    private int yyr71() { // in_implicit_map : in_implicit_map indent_sep
        {
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=2] = yyrv;
        return yypin_implicit_map();
    }

    private int yypin_implicit_map() {
        switch (yyst[yysp-1]) {
            case 86: return 120;
            case 83: return 118;
            case 80: return 116;
            default: return 26;
        }
    }

    private int yyr47() { // in_implicit_seq : basic_seq
        {
                    yyrv = Node.newSeq(((Long)yysv[yysp-1]).longValue());
                }
        yysv[yysp-=1] = yyrv;
        return yypin_implicit_seq();
    }

    private int yyr48() { // in_implicit_seq : in_implicit_seq indent_sep basic_seq
        { 
                    ((Node)yysv[yysp-3]).seqAdd(((Long)yysv[yysp-1]).longValue());
                    yyrv = yysv[yysp-3];
                                }
        yysv[yysp-=3] = yyrv;
        return yypin_implicit_seq();
    }

    private int yyr49() { // in_implicit_seq : in_implicit_seq indent_sep
        { 
                    yyrv = yysv[yysp-2];
                                }
        yysv[yysp-=2] = yyrv;
        return yypin_implicit_seq();
    }

    private int yypin_implicit_seq() {
        switch (yyst[yysp-1]) {
            case 86: return 121;
            case 83: return 119;
            case 80: return 117;
            default: return 27;
        }
    }

    private int yyr75() { // in_inline_map : inline_map_atom
        yysp -= 1;
        return 64;
    }

    private int yyr76() { // in_inline_map : in_inline_map ',' inline_map_atom
        {
                    ((Node)yysv[yysp-3]).mapUpdate((Node)yysv[yysp-1]);
                    yysv[yysp-1] = null;
                    yyrv = yysv[yysp-3];
                                }
        yysv[yysp-=3] = yyrv;
        return 64;
    }

    private int yyr52() { // in_inline_seq : inline_seq_atom
        {
                    yyrv = Node.newSeq( parser.addNode((Node)yysv[yysp-1]));
                }
        yysv[yysp-=1] = yyrv;
        return 59;
    }

    private int yyr53() { // in_inline_seq : in_inline_seq ',' inline_seq_atom
        { 
                    ((Node)yysv[yysp-3]).seqAdd(parser.addNode((Node)yysv[yysp-1]));
                    yyrv = yysv[yysp-3];
                                }
        yysv[yysp-=3] = yyrv;
        return 59;
    }

    private int yyr6() { // ind_rep : struct_rep
        yysp -= 1;
        return yypind_rep();
    }

    private int yyr7() { // ind_rep : YAML_TRANSFER ind_rep
        { 
            Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], parser.taguri_expansion);
            yyrv = yysv[yysp-1];
        }
        yysv[yysp-=2] = yyrv;
        return yypind_rep();
    }

    private int yyr8() { // ind_rep : YAML_TAGURI ind_rep
        {
            Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], false);
            yyrv = yysv[yysp-1];
        }
        yysv[yysp-=2] = yyrv;
        return yypind_rep();
    }

    private int yyr9() { // ind_rep : YAML_ANCHOR ind_rep
        { 
           /*
            * _Anchors_: The language binding must keep a separate symbol table
            * for anchors.  The actual ID in the symbol table is returned to the
            * higher nodes, though.
            */
           yyrv = parser.addAnchor((String)yysv[yysp-2], (Node)yysv[yysp-1] );
        }
        yysv[yysp-=2] = yyrv;
        return yypind_rep();
    }

    private int yyr10() { // ind_rep : indent_open ind_rep indent_flex_end
        {
           yyrv = yysv[yysp-2];
        }
        yysv[yysp-=3] = yyrv;
        return yypind_rep();
    }

    private int yypind_rep() {
        switch (yyst[yysp-1]) {
            case 94: return 55;
            case 93: return 53;
            case 92: return 38;
            case 47: return 55;
            case 46: return 53;
            case 44: return 38;
            case 43: return 28;
            case 35: return 55;
            case 34: return 53;
            case 32: return 38;
            case 19: return 55;
            case 18: return 53;
            case 12: return 38;
            case 6: return 28;
            default: return 5;
        }
    }

    private int yyr21() { // indent_end : YAML_IEND
        yysp -= 1;
        switch (yyst[yysp-1]) {
            case 30: return 78;
            case 29: return 77;
            case 27: return 72;
            case 26: return 68;
            default: return 123;
        }
    }

    private int yyr23() { // indent_flex_end : YAML_IEND
        yysp -= 1;
        return yypindent_flex_end();
    }

    private int yyr24() { // indent_flex_end : indent_sep indent_flex_end
        yysp -= 2;
        return yypindent_flex_end();
    }

    private int yypindent_flex_end() {
        switch (yyst[yysp-1]) {
            case 75: return 115;
            case 28: return 74;
            default: return 79;
        }
    }

    private int yyr19() { // indent_open : YAML_IOPEN
        yysp -= 1;
        return yypindent_open();
    }

    private int yyr20() { // indent_open : indent_open YAML_INDENT
        yysp -= 2;
        return yypindent_open();
    }

    private int yypindent_open() {
        switch (yyst[yysp-1]) {
            case 104: return 43;
            case 100: return 97;
            case 99: return 97;
            case 98: return 97;
            case 97: return 97;
            case 94: return 43;
            case 93: return 43;
            case 92: return 43;
            case 86: return 48;
            case 83: return 48;
            case 80: return 48;
            case 69: return 48;
            case 67: return 43;
            case 52: return 48;
            case 51: return 48;
            case 50: return 48;
            case 48: return 48;
            case 47: return 43;
            case 46: return 43;
            case 45: return 97;
            case 44: return 43;
            case 43: return 43;
            case 36: return 43;
            case 16: return 48;
            case 14: return 43;
            default: return 6;
        }
    }

    private int yyr22() { // indent_sep : YAML_INDENT
        yysp -= 1;
        switch (yyst[yysp-1]) {
            case 120: return 69;
            case 118: return 69;
            case 116: return 69;
            case 103: return 75;
            case 94: return 86;
            case 93: return 83;
            case 92: return 80;
            case 90: return 122;
            case 75: return 75;
            case 35: return 86;
            case 34: return 83;
            case 32: return 80;
            case 31: return 75;
            case 28: return 75;
            case 26: return 69;
            default: return 73;
        }
    }

    private int yyr73() { // inline_map : '{' in_inline_map '}'
        {
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 7;
    }

    private int yyr74() { // inline_map : '{' '}'
        {
                    yyrv = Node.allocMap();
                }
        yysv[yysp-=2] = yyrv;
        return 7;
    }

    private int yyr77() { // inline_map_atom : atom
        {
                    Node n = NULL_NODE( parser );
                    yyrv = Node.newMap( 
                        parser.addNode( (Node)yysv[yysp-1] ), 
                        parser.addNode( n ) );
                }
        yysv[yysp-=1] = yyrv;
        return yypinline_map_atom();
    }

    private int yyr78() { // inline_map_atom : basic_mapping
        yysp -= 1;
        return yypinline_map_atom();
    }

    private int yypinline_map_atom() {
        switch (yyst[yysp-1]) {
            case 22: return 65;
            default: return 126;
        }
    }

    private int yyr50() { // inline_seq : '[' in_inline_seq ']'
        { 
                    yyrv = yysv[yysp-2];
                }
        yysv[yysp-=3] = yyrv;
        return 8;
    }

    private int yyr51() { // inline_seq : '[' ']'
        { 
                    yyrv = Node.allocSeq();
                }
        yysv[yysp-=2] = yyrv;
        return 8;
    }

    private int yyr54() { // inline_seq_atom : atom
        yysp -= 1;
        return yypinline_seq_atom();
    }

    private int yyr55() { // inline_seq_atom : basic_mapping
        yysp -= 1;
        return yypinline_seq_atom();
    }

    private int yypinline_seq_atom() {
        switch (yyst[yysp-1]) {
            case 21: return 60;
            default: return 125;
        }
    }

    private int yyr33() { // struct_rep : YAML_BLOCK
        yysp -= 1;
        return 9;
    }

    private int yyr34() { // struct_rep : implicit_seq
        yysp -= 1;
        return 9;
    }

    private int yyr35() { // struct_rep : inline_seq
        yysp -= 1;
        return 9;
    }

    private int yyr36() { // struct_rep : implicit_map
        yysp -= 1;
        return 9;
    }

    private int yyr37() { // struct_rep : inline_map
        yysp -= 1;
        return 9;
    }

    private int yyr58() { // top_imp_map : YAML_TRANSFER indent_sep in_implicit_map
        { 
                    Parser.addTransfer((String)yysv[yysp-3], (Node)yysv[yysp-1], parser.taguri_expansion);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_map();
    }

    private int yyr59() { // top_imp_map : YAML_TRANSFER top_imp_map
        { 
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], parser.taguri_expansion);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_map();
    }

    private int yyr60() { // top_imp_map : YAML_TAGURI indent_sep in_implicit_map
        { 
                    Parser.addTransfer((String)yysv[yysp-3], (Node)yysv[yysp-1], false);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_map();
    }

    private int yyr61() { // top_imp_map : YAML_TAGURI top_imp_map
        { 
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], false);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_map();
    }

    private int yyr62() { // top_imp_map : YAML_ANCHOR indent_sep in_implicit_map
        { 
                    yyrv = parser.addAnchor((String)yysv[yysp-3], (Node)yysv[yysp-1]);
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_map();
    }

    private int yyr63() { // top_imp_map : YAML_ANCHOR top_imp_map
        { 
                    yyrv = parser.addAnchor((String)yysv[yysp-2], (Node)yysv[yysp-1]);
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_map();
    }

    private int yyptop_imp_map() {
        switch (yyst[yysp-1]) {
            case 93: return 84;
            case 92: return 81;
            case 43: return 29;
            case 34: return 84;
            case 32: return 81;
            case 6: return 29;
            default: return 87;
        }
    }

    private int yyr41() { // top_imp_seq : YAML_TRANSFER indent_sep in_implicit_seq
        { 
                    Parser.addTransfer((String)yysv[yysp-3], (Node)yysv[yysp-1], parser.taguri_expansion);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyr42() { // top_imp_seq : YAML_TRANSFER top_imp_seq
        { 
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], parser.taguri_expansion);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyr43() { // top_imp_seq : YAML_TAGURI indent_sep in_implicit_seq
        { 
                    Parser.addTransfer((String)yysv[yysp-3], (Node)yysv[yysp-1], false);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyr44() { // top_imp_seq : YAML_TAGURI top_imp_seq
        { 
                    Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], false);
                    yyrv = yysv[yysp-1];
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyr45() { // top_imp_seq : YAML_ANCHOR indent_sep in_implicit_seq
        { 
                    yyrv = parser.addAnchor((String)yysv[yysp-3], (Node)yysv[yysp-1] );
                }
        yysv[yysp-=3] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyr46() { // top_imp_seq : YAML_ANCHOR top_imp_seq
        { 
                    yyrv = parser.addAnchor((String)yysv[yysp-2], (Node)yysv[yysp-1] );
                }
        yysv[yysp-=2] = yyrv;
        return yyptop_imp_seq();
    }

    private int yyptop_imp_seq() {
        switch (yyst[yysp-1]) {
            case 93: return 85;
            case 92: return 82;
            case 43: return 30;
            case 34: return 85;
            case 32: return 82;
            case 6: return 30;
            default: return 88;
        }
    }

    private int yyr25() { // word_rep : YAML_TRANSFER word_rep
        { 
               Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], parser.taguri_expansion);
               yyrv = yysv[yysp-1];
            }
        yysv[yysp-=2] = yyrv;
        return yypword_rep();
    }

    private int yyr26() { // word_rep : YAML_TAGURI word_rep
        { 
               Parser.addTransfer((String)yysv[yysp-2], (Node)yysv[yysp-1], false);
               yyrv = yysv[yysp-1];
            }
        yysv[yysp-=2] = yyrv;
        return yypword_rep();
    }

    private int yyr27() { // word_rep : YAML_ITRANSFER word_rep
        { 
               if(parser.implicit_typing)
               {
                  ImplicitScanner.tryTagImplicit((Node)yysv[yysp-1], parser.taguri_expansion);
               }
               yyrv = yysv[yysp-1];
            }
        yysv[yysp-=2] = yyrv;
        return yypword_rep();
    }

    private int yyr28() { // word_rep : YAML_ANCHOR word_rep
        { 
               yyrv = parser.addAnchor((String)yysv[yysp-2], (Node)yysv[yysp-1] );
            }
        yysv[yysp-=2] = yyrv;
        return yypword_rep();
    }

    private int yyr29() { // word_rep : YAML_ALIAS
        {
               /*
                * _Aliases_: The anchor symbol table is scanned for the anchor name.
                * The anchor's ID in the language's symbol table is returned.
                */
               yyrv = parser.getAnchor((String)yysv[yysp-1]);
            }
        yysv[yysp-=1] = yyrv;
        return yypword_rep();
    }

    private int yyr30() { // word_rep : YAML_WORD
        { 
               Node n = (Node)yysv[yysp-1];
               if(parser.taguri_expansion) {
                   n.type_id = Parser.taguri(YAML.DOMAIN, "str");
               } else {
                   n.type_id = "str";
               }
               yyrv = n;
            }
        yysv[yysp-=1] = yyrv;
        return yypword_rep();
    }

    private int yyr31() { // word_rep : YAML_PLAIN
        yysp -= 1;
        return yypword_rep();
    }

    private int yyr32() { // word_rep : indent_open word_rep indent_flex_end
        {
               yyrv = yysv[yysp-2];
            }
        yysv[yysp-=3] = yyrv;
        return yypword_rep();
    }

    private int yypword_rep() {
        switch (yyst[yysp-1]) {
            case 100: return 56;
            case 99: return 54;
            case 98: return 39;
            case 97: return 103;
            case 94: return 56;
            case 93: return 54;
            case 92: return 39;
            case 86: return 113;
            case 83: return 113;
            case 80: return 113;
            case 69: return 113;
            case 52: return 56;
            case 51: return 54;
            case 50: return 39;
            case 48: return 103;
            case 47: return 56;
            case 46: return 54;
            case 45: return 49;
            case 44: return 39;
            case 43: return 31;
            case 35: return 56;
            case 34: return 54;
            case 32: return 39;
            case 19: return 56;
            case 18: return 54;
            case 16: return 49;
            case 12: return 39;
            case 6: return 31;
            default: return 10;
        }
    }

    protected String[] yyerrmsgs = {
    };


     private Parser parser;
     private Scanner lexer;
     public YechtParser(Parser parser, Scanner lexer) {
       this.parser = parser;
       this.lexer  = lexer;
     }

public static Node NULL_NODE(Parser parser) {
    Node n = Node.newStr(Pointer.create(new byte[0], 0), 0, ScalarStyle.Plain);
    if(parser.taguri_expansion) {
      n.type_id = Parser.taguri(YAML.DOMAIN, "null");
    } else {
      n.type_id = "null";
    }
    return n;
}


public static void applySeqInMap(Parser parser, Node n) {
       if(n.shortcut == null) {
         return;
       }

       int map_len = (int)n.mapCount();
       n.mapAssign(MapPart.Value, map_len - 1, parser.addNode((Node)n.shortcut));
       n.shortcut = null;
}

public void yyerror(String msg) {
       System.err.println("PARSER ERROR: " + msg);
}

}
