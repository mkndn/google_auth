package com.google.android.gms.common.config;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GservicesValue {
    private static final Object lock = new Object();
    private static GservicesReader gservicesReader = null;
    private static int sharedUserId = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface GservicesReader {
    }

    public static int getSharedUserId() {
        return sharedUserId;
    }

    public static boolean isInitialized() {
        boolean z;
        synchronized (lock) {
            z = gservicesReader != null;
        }
        return z;
    }
}
