package com.google.android.libraries.storage.file.common.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Preconditions {
    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(format(str, objArr));
        }
    }

    private static String format(String str, Object... objArr) {
        return String.format(str, objArr);
    }
}
