package com.google.common.flogger.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Checks {
    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static String checkMetadataIdentifier(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("identifier must not be empty");
        }
        if (!isLetter(str.charAt(0))) {
            throw new IllegalArgumentException("identifier must start with an ASCII letter: " + str);
        }
        for (int i = 1; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (!isLetter(charAt) && ((charAt < '0' || charAt > '9') && charAt != '_')) {
                throw new IllegalArgumentException("identifier must contain only ASCII letters, digits or underscore: " + str);
            }
        }
        return str;
    }

    public static Object checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str + " must not be null");
        }
        return obj;
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
