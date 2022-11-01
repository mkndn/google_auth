package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userTikTokTraceConfigProvider;
    private final Provider userTraceConfigProvider;

    public PrimesTraceDaggerModule_MetricServiceFactory(Provider provider, Provider provider2, Provider provider3) {
        this.userTraceConfigProvider = provider;
        this.userTikTokTraceConfigProvider = provider2;
        this.implProvider = provider3;
    }

    public static PrimesTraceDaggerModule_MetricServiceFactory create(Provider provider, Provider provider2, Provider provider3) {
        return new PrimesTraceDaggerModule_MetricServiceFactory(provider, provider2, provider3);
    }

    public static TraceMetricService metricService(Optional optional, Optional optional2, Provider provider) {
        return (TraceMetricService) Preconditions.checkNotNullFromProvides(PrimesTraceDaggerModule.metricService(optional, optional2, provider));
    }

    @Override // javax.inject.Provider
    public TraceMetricService get() {
        return metricService((Optional) this.userTraceConfigProvider.get(), (Optional) this.userTikTokTraceConfigProvider.get(), this.implProvider);
    }
}
