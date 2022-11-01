package com.google.common.flogger.backend;

import com.google.common.flogger.parser.ParseException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FormatOptions {
    public static final int ALL_FLAGS = 255;
    private static final FormatOptions DEFAULT;
    private static final long ENCODED_FLAG_INDICES;
    public static final int FLAG_LEFT_ALIGN = 32;
    public static final int FLAG_PREFIX_PLUS_FOR_POSITIVE_VALUES = 8;
    public static final int FLAG_PREFIX_SPACE_FOR_POSITIVE_VALUES = 1;
    public static final int FLAG_SHOW_ALT_FORM = 2;
    public static final int FLAG_SHOW_GROUPING = 16;
    public static final int FLAG_SHOW_LEADING_ZEROS = 64;
    public static final int FLAG_UPPER_CASE = 128;
    public static final int FLAG_USE_PARENS_FOR_NEGATIVE_VALUES = 4;
    public static final int UNSET = -1;
    private final int flags;
    private final int precision;
    private final int width;

    private FormatOptions(int i, int i2, int i3) {
        this.flags = i;
        this.width = i2;
        this.precision = i3;
    }

    static boolean checkFlagConsistency(int i, boolean z) {
        int i2;
        if ((i & 9) == 9 || (i2 = i & 96) == 96) {
            return false;
        }
        return i2 == 0 || z;
    }

    public static FormatOptions getDefault() {
        return DEFAULT;
    }

    private static int indexOfFlagCharacter(char c) {
        return ((int) ((ENCODED_FLAG_INDICES >>> ((c - ' ') * 3)) & 7)) - 1;
    }

    public static FormatOptions parse(String str, int i, int i2, boolean z) {
        if (i == i2 && !z) {
            return DEFAULT;
        }
        int i3 = z ? 128 : 0;
        while (i != i2) {
            int i4 = i + 1;
            char charAt = str.charAt(i);
            if (charAt >= ' ' && charAt <= '0') {
                int indexOfFlagCharacter = indexOfFlagCharacter(charAt);
                if (indexOfFlagCharacter >= 0) {
                    int i5 = 1 << indexOfFlagCharacter;
                    if ((i3 & i5) == 0) {
                        i3 |= i5;
                        i = i4;
                    } else {
                        throw ParseException.atPosition("repeated flag", str, i4 - 1);
                    }
                } else if (charAt == '.') {
                    return new FormatOptions(i3, -1, parsePrecision(str, i4, i2));
                } else {
                    throw ParseException.atPosition("invalid flag", str, i4 - 1);
                }
            } else {
                int i6 = i4 - 1;
                if (charAt > '9') {
                    throw ParseException.atPosition("invalid flag", str, i6);
                }
                int i7 = charAt - '0';
                while (i4 != i2) {
                    int i8 = i4 + 1;
                    char charAt2 = str.charAt(i4);
                    if (charAt2 != '.') {
                        char c = (char) (charAt2 - '0');
                        if (c < '\n') {
                            i7 = (i7 * 10) + c;
                            if (i7 <= 999999) {
                                i4 = i8;
                            } else {
                                throw ParseException.withBounds("width too large", str, i6, i2);
                            }
                        } else {
                            throw ParseException.atPosition("invalid width character", str, i8 - 1);
                        }
                    } else {
                        return new FormatOptions(i3, i7, parsePrecision(str, i8, i2));
                    }
                }
                return new FormatOptions(i3, i7, -1);
            }
        }
        return new FormatOptions(i3, -1, -1);
    }

    private static int parsePrecision(String str, int i, int i2) {
        if (i != i2) {
            int i3 = 0;
            for (int i4 = i; i4 < i2; i4++) {
                char charAt = (char) (str.charAt(i4) - '0');
                if (charAt < '\n') {
                    i3 = (i3 * 10) + charAt;
                    if (i3 > 999999) {
                        throw ParseException.withBounds("precision too large", str, i, i2);
                    }
                } else {
                    throw ParseException.atPosition("invalid precision character", str, i4);
                }
            }
            if (i3 == 0 && i2 != i + 1) {
                throw ParseException.withBounds("invalid precision", str, i, i2);
            }
            return i3;
        }
        throw ParseException.atPosition("missing precision", str, i - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int parseValidFlags(String str, boolean z) {
        int i = z ? 128 : 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            int indexOfFlagCharacter = indexOfFlagCharacter(str.charAt(i2));
            if (indexOfFlagCharacter >= 0) {
                i |= 1 << indexOfFlagCharacter;
            } else {
                throw new IllegalArgumentException("invalid flags: " + str);
            }
        }
        return i;
    }

    public StringBuilder appendPrintfOptions(StringBuilder sb) {
        if (!isDefault()) {
            int i = this.flags & (-129);
            int i2 = 0;
            while (true) {
                int i3 = 1 << i2;
                if (i3 > i) {
                    break;
                }
                if ((i3 & i) != 0) {
                    sb.append(" #(+,-0".charAt(i2));
                }
                i2++;
            }
            int i4 = this.width;
            if (i4 != -1) {
                sb.append(i4);
            }
            if (this.precision != -1) {
                sb.append('.').append(this.precision);
            }
        }
        return sb;
    }

    public boolean areValidFor(FormatChar formatChar) {
        return validate(formatChar.getAllowedFlags(), formatChar.getType().supportsPrecision());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FormatOptions) {
            FormatOptions formatOptions = (FormatOptions) obj;
            return formatOptions.flags == this.flags && formatOptions.width == this.width && formatOptions.precision == this.precision;
        }
        return false;
    }

    public FormatOptions filter(int i, boolean z, boolean z2) {
        if (isDefault()) {
            return this;
        }
        int i2 = this.flags;
        int i3 = i & i2;
        int i4 = z ? this.width : -1;
        int i5 = z2 ? this.precision : -1;
        if (i3 == 0 && i4 == -1 && i5 == -1) {
            return DEFAULT;
        }
        if (i3 == i2 && i4 == this.width && i5 == this.precision) {
            return this;
        }
        return new FormatOptions(i3, i4, i5);
    }

    public int getFlags() {
        return this.flags;
    }

    public int getPrecision() {
        return this.precision;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((this.flags * 31) + this.width) * 31) + this.precision;
    }

    public boolean isDefault() {
        return this == getDefault();
    }

    public boolean shouldUpperCase() {
        return (this.flags & 128) != 0;
    }

    public boolean validate(int i, boolean z) {
        if (isDefault()) {
            return true;
        }
        int i2 = this.flags;
        if (((i ^ (-1)) & i2) != 0) {
            return false;
        }
        if (z || this.precision == -1) {
            return checkFlagConsistency(i2, getWidth() != -1);
        }
        return false;
    }

    static {
        long j = 0;
        for (int i = 0; i < " #(+,-0".length(); i++) {
            j |= (i + 1) << ((int) ((" #(+,-0".charAt(i) - ' ') * 3));
        }
        ENCODED_FLAG_INDICES = j;
        DEFAULT = new FormatOptions(0, -1, -1);
    }
}
