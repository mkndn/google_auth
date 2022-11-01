package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrintfMessageParser extends MessageParser {
    private static final String SYSTEM_NEWLINE = getSafeSystemNewline();

    private static int findFormatChar(String str, int i, int i2) {
        while (i2 < str.length()) {
            if (((char) ((str.charAt(i2) & 65503) - 65)) < 26) {
                return i2;
            }
            i2++;
        }
        throw ParseException.withStartPosition("unterminated parameter", str, i);
    }

    static String getSafeSystemNewline() {
        try {
            String property = System.getProperty("line.separator");
            if (!property.matches("\\n|\\r(?:\\n)?")) {
                return "\n";
            }
            return property;
        } catch (SecurityException e) {
            return "\n";
        }
    }

    static int nextPrintfTerm(String str, int i) {
        while (i < str.length()) {
            int i2 = i + 1;
            if (str.charAt(i) != '%') {
                i = i2;
            } else if (i2 < str.length()) {
                char charAt = str.charAt(i2);
                if (charAt == '%' || charAt == 'n') {
                    i = i2 + 1;
                } else {
                    return i2 - 1;
                }
            } else {
                throw ParseException.withStartPosition("trailing unquoted '%' character", str, i2 - 1);
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.flogger.parser.MessageParser
    public final void parseImpl(MessageBuilder messageBuilder) {
        int i;
        int i2;
        int i3;
        String message = messageBuilder.getMessage();
        int nextPrintfTerm = nextPrintfTerm(message, 0);
        int i4 = 0;
        int i5 = -1;
        while (nextPrintfTerm >= 0) {
            int i6 = nextPrintfTerm + 1;
            int i7 = i6;
            int i8 = 0;
            while (i7 < message.length()) {
                int i9 = i7 + 1;
                char charAt = message.charAt(i7);
                char c = (char) (charAt - '0');
                if (c < '\n') {
                    i8 = (i8 * 10) + c;
                    if (i8 < 1000000) {
                        i7 = i9;
                    } else {
                        throw ParseException.withBounds("index too large", message, nextPrintfTerm, i9);
                    }
                } else {
                    if (charAt == '$') {
                        if ((i9 - 1) - i6 != 0) {
                            if (message.charAt(i6) != '0') {
                                int i10 = i8 - 1;
                                if (i9 != message.length()) {
                                    message.charAt(i9);
                                    i2 = i4;
                                    i = i10;
                                    i3 = i9;
                                    i9++;
                                } else {
                                    throw ParseException.withStartPosition("unterminated parameter", message, nextPrintfTerm);
                                }
                            } else {
                                throw ParseException.withBounds("index has leading zero", message, nextPrintfTerm, i9);
                            }
                        } else {
                            throw ParseException.withBounds("missing index", message, nextPrintfTerm, i9);
                        }
                    } else if (charAt == '<') {
                        if (i5 != -1) {
                            if (i9 != message.length()) {
                                message.charAt(i9);
                                i2 = i4;
                                i = i5;
                                i3 = i9;
                                i9++;
                            } else {
                                throw ParseException.withStartPosition("unterminated parameter", message, nextPrintfTerm);
                            }
                        } else {
                            throw ParseException.withBounds("invalid relative parameter", message, nextPrintfTerm, i9);
                        }
                    } else {
                        i = i4;
                        i2 = i4 + 1;
                        i3 = i6;
                    }
                    nextPrintfTerm = nextPrintfTerm(message, parsePrintfTerm(messageBuilder, i, message, nextPrintfTerm, i3, findFormatChar(message, nextPrintfTerm, i9 - 1)));
                    i4 = i2;
                    i5 = i;
                }
            }
            throw ParseException.withStartPosition("unterminated parameter", message, nextPrintfTerm);
        }
    }

    abstract int parsePrintfTerm(MessageBuilder messageBuilder, int i, String str, int i2, int i3, int i4);

    @Override // com.google.common.flogger.parser.MessageParser
    public final void unescape(StringBuilder sb, String str, int i, int i2) {
        unescapePrintf(sb, str, i, i2);
    }

    static void unescapePrintf(StringBuilder sb, String str, int i, int i2) {
        int i3 = i;
        while (i < i2) {
            int i4 = i + 1;
            if (str.charAt(i) == '%') {
                if (i4 == i2) {
                    break;
                }
                char charAt = str.charAt(i4);
                if (charAt == '%') {
                    sb.append((CharSequence) str, i3, i4);
                } else if (charAt == 'n') {
                    sb.append((CharSequence) str, i3, i4 - 1);
                    sb.append(SYSTEM_NEWLINE);
                }
                i3 = i4 + 1;
                i = i3;
            }
            i = i4;
        }
        if (i3 < i2) {
            sb.append((CharSequence) str, i3, i2);
        }
    }
}
