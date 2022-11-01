package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesJankDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesJankDaggerModule_MetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static PrimesJankDaggerModule_MetricServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesJankDaggerModule_MetricServiceFactory(provider, provider2);
    }

    public static JankMetricService metricService(Optional optional, Provider provider) {
        return (JankMetricService) Preconditions.checkNotNullFromProvides(PrimesJankDaggerModule.metricService(optional, provider));
    }

    @Override // javax.inject.Provider
    public JankMetricService get() {
        return metricService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
