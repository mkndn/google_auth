package com.google.android.libraries.performance.primes.sampling;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProbabilitySamplerFactory_Factory implements Factory {
    private final Provider randomProvider;

    public ProbabilitySamplerFactory_Factory(Provider provider) {
        this.randomProvider = provider;
    }

    public static ProbabilitySamplerFactory_Factory create(Provider provider) {
        return new ProbabilitySamplerFactory_Factory(provider);
    }

    public static ProbabilitySamplerFactory newInstance(Provider provider) {
        return new ProbabilitySamplerFactory(provider);
    }

    @Override // javax.inject.Provider
    public ProbabilitySamplerFactory get() {
        return newInstance(this.randomProvider);
    }
}
