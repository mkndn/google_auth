package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory implements Factory {
    private final Provider implProvider;

    public PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory(Provider provider) {
        this.implProvider = provider;
    }

    public static Set applicationExitMetricService(Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesApplicationExitDaggerModule.applicationExitMetricService(provider));
    }

    public static PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory create(Provider provider) {
        return new PrimesApplicationExitDaggerModule_ApplicationExitMetricServiceFactory(provider);
    }

    @Override // javax.inject.Provider
    public Set get() {
        return applicationExitMetricService(this.implProvider);
    }
}
