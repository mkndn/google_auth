package com.google.android.apps.authenticator.testability.android.os;

import android.os.PowerManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PowerManager {
    public static final int ACQUIRE_CAUSES_WAKEUP = 268435456;
    @Deprecated
    public static final int FULL_WAKE_LOCK = 26;
    public static final int ON_AFTER_RELEASE = 536870912;
    public static final int PARTIAL_WAKE_LOCK = 1;
    private final android.os.PowerManager powerManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class WakeLock {
        private final PowerManager.WakeLock wakeLock;

        public WakeLock(PowerManager.WakeLock wakeLock) {
            this.wakeLock = wakeLock;
        }

        public static WakeLock wrap(PowerManager.WakeLock wakeLock) {
            if (wakeLock != null) {
                return new WakeLock(wakeLock);
            }
            return null;
        }

        public void acquire(long j) {
            this.wakeLock.acquire(j);
        }
    }

    public PowerManager(android.os.PowerManager powerManager) {
        this.powerManager = powerManager;
    }

    public static PowerManager wrap(android.os.PowerManager powerManager) {
        if (powerManager != null) {
            return new PowerManager(powerManager);
        }
        return null;
    }

    public boolean equals(Object obj) {
        return this.powerManager.equals(obj);
    }

    public int hashCode() {
        return this.powerManager.hashCode();
    }

    public WakeLock newWakeLock(int i, String str) {
        return WakeLock.wrap(this.powerManager.newWakeLock(i, str));
    }

    public String toString() {
        return this.powerManager.toString();
    }
}
