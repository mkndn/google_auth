package com.google.android.apps.authenticator.time;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SystemWallClock implements Clock {
    @Override // com.google.android.apps.authenticator.time.Clock
    public long nowMillis() {
        return System.currentTimeMillis();
    }
}
