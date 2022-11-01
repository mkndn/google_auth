package com.google.android.gms.common.util;

import android.os.SystemClock;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultClock implements Clock {
    private static final DefaultClock instance = new DefaultClock();

    private DefaultClock() {
    }

    public static Clock getInstance() {
        return instance;
    }

    @Override // com.google.android.gms.common.util.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.google.android.gms.common.util.Clock
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
