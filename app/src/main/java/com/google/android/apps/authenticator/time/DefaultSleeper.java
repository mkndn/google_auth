package com.google.android.apps.authenticator.time;

import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultSleeper implements Sleeper {
    @Override // com.google.android.apps.authenticator.time.Sleeper
    public void sleep(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException("time must not be negative: " + j);
        }
        timeUnit.sleep(j);
    }
}
