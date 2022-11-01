package com.google.android.libraries.stitch.util;

import android.os.Handler;
import android.os.Looper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadUtil {
    private static Thread mainThread;
    private static volatile Handler sMainThreadHandler;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CalledOnWrongThreadException extends RuntimeException {
        public CalledOnWrongThreadException(String str) {
            super(str);
        }
    }

    public static void ensureBackgroundThread() {
        if (isMainThread()) {
            throw new CalledOnWrongThreadException("Must be called on a background thread");
        }
    }

    public static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        return sMainThreadHandler;
    }

    public static boolean isMainThread() {
        if (mainThread == null) {
            mainThread = Looper.getMainLooper().getThread();
        }
        return Thread.currentThread() == mainThread;
    }

    public static void postOnMainThread(Runnable runnable) {
        getMainThreadHandler().post(runnable);
    }

    public static void removeCallbacksOnMainThread(Runnable runnable) {
        getMainThreadHandler().removeCallbacks(runnable);
    }
}
