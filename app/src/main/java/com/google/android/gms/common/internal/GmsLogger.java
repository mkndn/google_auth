package com.google.android.gms.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsLogger {
    public static final int MAX_TAG_LENGTH = 23;
    private final String mLogTag;
    private final String mPrefix;
    public static final int MAX_PII_TAG_LENGTH = 23 - " PII_LOG".length();
    private static final String NO_PREFIX = null;

    public GmsLogger(String str) {
        this(str, NO_PREFIX);
    }

    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, 23);
        this.mLogTag = str;
        if (str2 != null && str2.length() > 0) {
            this.mPrefix = str2;
        } else {
            this.mPrefix = NO_PREFIX;
        }
    }
}
