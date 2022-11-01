package com.google.android.libraries.performance.primes.flogger;

import dagger.internal.Factory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RecentLogs_Factory implements Factory {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InstanceHolder {
        private static final RecentLogs_Factory INSTANCE = new RecentLogs_Factory();
    }

    public static RecentLogs_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RecentLogs newInstance() {
        return new RecentLogs();
    }

    @Override // javax.inject.Provider
    public RecentLogs get() {
        return newInstance();
    }
}
