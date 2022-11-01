package com.google.android.libraries.performance.primes.sampling;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SamplerFactory_Factory implements Factory {
    private final Provider contextProvider;
    private final Provider enableSamplingProvider;
    private final Provider executorProvider;
    private final Provider samplingStrategyFactoryProvider;

    public SamplerFactory_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.contextProvider = provider;
        this.executorProvider = provider2;
        this.samplingStrategyFactoryProvider = provider3;
        this.enableSamplingProvider = provider4;
    }

    public static SamplerFactory_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new SamplerFactory_Factory(provider, provider2, provider3, provider4);
    }

    public static SamplerFactory newInstance(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new SamplerFactory(provider, provider2, provider3, provider4);
    }

    @Override // javax.inject.Provider
    public SamplerFactory get() {
        return newInstance(this.contextProvider, this.executorProvider, this.samplingStrategyFactoryProvider, this.enableSamplingProvider);
    }
}
