package com.google.android.gms.common.util;

import android.os.Looper;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ThreadUtils {
    private static boolean overrideIsMainThreadForTesting;

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper() && !overrideIsMainThreadForTesting;
    }
}
