package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceMetricServiceImpl_Factory implements Factory {
    private final Provider executorServiceProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider probabilitySamplerFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider tikTokTraceConfigurationsProvider;
    private final Provider traceConfigurationsProvider;

    public TraceMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6) {
        this.metricRecorderFactoryProvider = provider;
        this.executorServiceProvider = provider2;
        this.tikTokTraceConfigurationsProvider = provider3;
        this.traceConfigurationsProvider = provider4;
        this.samplingParametersProvider = provider5;
        this.probabilitySamplerFactoryProvider = provider6;
    }

    public static TraceMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6) {
        return new TraceMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static TraceMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Lazy lazy2, Provider provider, ProbabilitySamplerFactory probabilitySamplerFactory) {
        return new TraceMetricServiceImpl(metricRecorderFactory, listeningScheduledExecutorService, lazy, lazy2, provider, probabilitySamplerFactory);
    }

    @Override // javax.inject.Provider
    public TraceMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), DoubleCheck.lazy(this.tikTokTraceConfigurationsProvider), DoubleCheck.lazy(this.traceConfigurationsProvider), this.samplingParametersProvider, (ProbabilitySamplerFactory) this.probabilitySamplerFactoryProvider.get());
    }
}
