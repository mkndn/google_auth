package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerMetricServiceImpl_Factory implements Factory {
    private final Provider deferredExecutorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider probabilitySamplerFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider timerConfigurationsProvider;

    public TimerMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.metricRecorderFactoryProvider = provider;
        this.deferredExecutorProvider = provider2;
        this.timerConfigurationsProvider = provider3;
        this.samplingParametersProvider = provider4;
        this.probabilitySamplerFactoryProvider = provider5;
    }

    public static TimerMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new TimerMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static TimerMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Executor executor, Lazy lazy, Provider provider, ProbabilitySamplerFactory probabilitySamplerFactory) {
        return new TimerMetricServiceImpl(metricRecorderFactory, executor, lazy, provider, probabilitySamplerFactory);
    }

    @Override // javax.inject.Provider
    public TimerMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Executor) this.deferredExecutorProvider.get(), DoubleCheck.lazy(this.timerConfigurationsProvider), this.samplingParametersProvider, (ProbabilitySamplerFactory) this.probabilitySamplerFactoryProvider.get());
    }
}
