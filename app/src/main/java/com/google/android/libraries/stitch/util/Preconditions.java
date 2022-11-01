package com.google.android.libraries.stitch.util;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public final class Preconditions {
    public static Object checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}
