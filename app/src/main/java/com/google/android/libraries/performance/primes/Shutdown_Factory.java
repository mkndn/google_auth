package com.google.android.libraries.performance.primes;

import dagger.internal.Factory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Shutdown_Factory implements Factory {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InstanceHolder {
        private static final Shutdown_Factory INSTANCE = new Shutdown_Factory();
    }

    public static Shutdown_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Shutdown newInstance() {
        return new Shutdown();
    }

    @Override // javax.inject.Provider
    public Shutdown get() {
        return newInstance();
    }
}
