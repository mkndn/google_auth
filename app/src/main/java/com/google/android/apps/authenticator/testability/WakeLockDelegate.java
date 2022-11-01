package com.google.android.apps.authenticator.testability;

import android.os.PowerManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WakeLockDelegate {
    private final PowerManager.WakeLock mWakeLock;

    public WakeLockDelegate(PowerManager.WakeLock wakeLock) {
        this.mWakeLock = wakeLock;
    }

    public void acquire() {
        this.mWakeLock.acquire();
    }

    public void release() {
        try {
            this.mWakeLock.release();
        } catch (RuntimeException e) {
        }
    }

    public void acquire(long j) {
        this.mWakeLock.acquire(j);
    }
}
