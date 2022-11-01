package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTimerDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider userConfigProvider;
    private final Provider withTracingImplProvider;

    public PrimesTimerDaggerModule_MetricServiceFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.userConfigProvider = provider;
        this.traceMetricServiceProvider = provider2;
        this.withTracingImplProvider = provider3;
        this.implProvider = provider4;
    }

    public static PrimesTimerDaggerModule_MetricServiceFactory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new PrimesTimerDaggerModule_MetricServiceFactory(provider, provider2, provider3, provider4);
    }

    public static TimerMetricService metricService(Optional optional, Optional optional2, Provider provider, Provider provider2) {
        return (TimerMetricService) Preconditions.checkNotNullFromProvides(PrimesTimerDaggerModule.metricService(optional, optional2, provider, provider2));
    }

    @Override // javax.inject.Provider
    public TimerMetricService get() {
        return metricService((Optional) this.userConfigProvider.get(), (Optional) this.traceMetricServiceProvider.get(), this.withTracingImplProvider, this.implProvider);
    }
}
