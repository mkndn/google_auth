package com.google.android.apps.authenticator.time;

import android.os.SystemClock;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ElapsedRealtimeSinceBootClock implements Clock {
    @Override // com.google.android.apps.authenticator.time.Clock
    public long nowMillis() {
        return SystemClock.elapsedRealtime();
    }
}
