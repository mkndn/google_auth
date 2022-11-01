package com.google.android.gms.common.util;

import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsVersionParser {
    public static final int UNKNOWN = -1;
    private static Pattern versionNamePattern = null;

    public static int parseBuildVersion(int i) {
        if (i == -1) {
            return -1;
        }
        return i / 1000;
    }
}
