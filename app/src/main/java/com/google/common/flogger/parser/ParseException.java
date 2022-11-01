package com.google.common.flogger.parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ParseException extends RuntimeException {
    private ParseException(String str, String str2) {
        super(str);
    }

    public static ParseException atPosition(String str, String str2, int i) {
        return new ParseException(msg(str, str2, i, i + 1), str2);
    }

    public static ParseException generic(String str, String str2) {
        return new ParseException(str, str2);
    }

    private static String msg(String str, String str2, int i, int i2) {
        if (i2 < 0) {
            i2 = str2.length();
        }
        StringBuilder append = new StringBuilder(str).append(": ");
        if (i > "...".length() + 5) {
            append.append("...").append((CharSequence) str2, i - 5, i);
        } else {
            append.append((CharSequence) str2, 0, i);
        }
        append.append('[').append(str2.substring(i, i2)).append(']');
        if (str2.length() - i2 > "...".length() + 5) {
            append.append((CharSequence) str2, i2, i2 + 5).append("...");
        } else {
            append.append((CharSequence) str2, i2, str2.length());
        }
        return append.toString();
    }

    public static ParseException withBounds(String str, String str2, int i, int i2) {
        return new ParseException(msg(str, str2, i, i2), str2);
    }

    public static ParseException withStartPosition(String str, String str2, int i) {
        return new ParseException(msg(str, str2, i, -1), str2);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
