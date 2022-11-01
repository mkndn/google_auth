package com.google.android.libraries.performance.primes.sampling;

import dagger.internal.Factory;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProdSamplingModule_EnableSamplingFactory implements Factory {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InstanceHolder {
        private static final ProdSamplingModule_EnableSamplingFactory INSTANCE = new ProdSamplingModule_EnableSamplingFactory();
    }

    public static ProdSamplingModule_EnableSamplingFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean enableSampling() {
        return ProdSamplingModule.enableSampling();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(enableSampling());
    }
}
