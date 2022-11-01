package com.google.android.apps.authenticator.testability.android.os;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Handler {
    private final android.os.Handler handler = new android.os.Handler();

    public boolean post(Runnable runnable) {
        return this.handler.post(runnable);
    }

    public boolean postDelayed(Runnable runnable, long j) {
        return this.handler.postDelayed(runnable, j);
    }
}
