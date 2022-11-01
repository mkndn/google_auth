package com.google.android.libraries.clock.impl;

import android.os.Build;
import android.os.SystemClock;
import com.google.android.libraries.clock.Clock;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemClockImpl implements Clock {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ElapsedRealtimeNanosImpl {
        private static final boolean ELAPSED_REALTIME_NANOS_EXISTS = elapsedRealtimeNanosExists();

        static long elapsedRealtimeNanos() {
            if (ELAPSED_REALTIME_NANOS_EXISTS) {
                return SystemClock.elapsedRealtimeNanos();
            }
            return SystemClock.elapsedRealtime() * 1000000;
        }

        private static boolean elapsedRealtimeNanosExists() {
            try {
                if (Build.VERSION.SDK_INT >= 17) {
                    SystemClock.elapsedRealtimeNanos();
                    return true;
                }
                return false;
            } catch (Throwable th) {
                return false;
            }
        }
    }

    @Override // com.google.android.libraries.clock.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.google.android.libraries.clock.Clock
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.libraries.clock.Clock
    public long elapsedRealtimeNanos() {
        return ElapsedRealtimeNanosImpl.elapsedRealtimeNanos();
    }
}
