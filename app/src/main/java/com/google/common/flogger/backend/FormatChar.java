package com.google.common.flogger.backend;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum FormatChar {
    STRING('s', FormatType.GENERAL, "-#", true),
    BOOLEAN('b', FormatType.BOOLEAN, "-", true),
    CHAR('c', FormatType.CHARACTER, "-", true),
    DECIMAL('d', FormatType.INTEGRAL, "-0+ ,(", false),
    OCTAL('o', FormatType.INTEGRAL, "-#0(", false),
    HEX('x', FormatType.INTEGRAL, "-#0(", true),
    FLOAT('f', FormatType.FLOAT, "-#0+ ,(", false),
    EXPONENT('e', FormatType.FLOAT, "-#0+ (", true),
    GENERAL('g', FormatType.FLOAT, "-0+ ,(", true),
    EXPONENT_HEX('a', FormatType.FLOAT, "-#0+ ", true);
    
    private static final FormatChar[] MAP = new FormatChar[26];
    private final int allowedFlags;
    private final String defaultFormatString;
    private final char formatChar;
    private final FormatType type;

    static {
        FormatChar[] values;
        for (FormatChar formatChar : values()) {
            MAP[indexOf(formatChar.getChar())] = formatChar;
        }
    }

    FormatChar(char c, FormatType formatType, String str, boolean z) {
        this.formatChar = c;
        this.type = formatType;
        this.allowedFlags = FormatOptions.parseValidFlags(str, z);
        this.defaultFormatString = "%" + c;
    }

    private boolean hasUpperCaseVariant() {
        return (this.allowedFlags & 128) != 0;
    }

    private static int indexOf(char c) {
        return (c | ' ') - 97;
    }

    private static boolean isLowerCase(char c) {
        return (c & ' ') != 0;
    }

    public static FormatChar of(char c) {
        FormatChar formatChar = MAP[indexOf(c)];
        if (isLowerCase(c)) {
            return formatChar;
        }
        if (formatChar == null || !formatChar.hasUpperCaseVariant()) {
            return null;
        }
        return formatChar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getAllowedFlags() {
        return this.allowedFlags;
    }

    public char getChar() {
        return this.formatChar;
    }

    public String getDefaultFormatString() {
        return this.defaultFormatString;
    }

    public FormatType getType() {
        return this.type;
    }
}
