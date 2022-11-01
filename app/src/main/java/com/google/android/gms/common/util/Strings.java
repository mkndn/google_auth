package com.google.android.gms.common.util;

import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Strings {
    private static final Pattern FORMAT_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");

    public static boolean isEmptyOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
