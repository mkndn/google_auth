package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PendingResult {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StatusListener {
        void onComplete(Status status);
    }

    public void addStatusListener(StatusListener statusListener) {
        throw null;
    }

    public abstract Result await(long j, TimeUnit timeUnit);
}
