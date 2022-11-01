package com.google.android.libraries.performance.primes.metrics.memory;

import android.content.Context;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetricServiceImpl_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider captureProvider;
    private final Provider clockProvider;
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider enableUnifiedInitProvider;
    private final Provider executorServiceProvider;
    private final Provider metricMonitorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider shutdownProvider;

    public MemoryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11) {
        this.metricRecorderFactoryProvider = provider;
        this.clockProvider = provider2;
        this.applicationProvider = provider3;
        this.metricMonitorProvider = provider4;
        this.executorServiceProvider = provider5;
        this.configsProvider = provider6;
        this.captureProvider = provider7;
        this.shutdownProvider = provider8;
        this.samplingParametersProvider = provider9;
        this.deferredExecutorProvider = provider10;
        this.enableUnifiedInitProvider = provider11;
    }

    public static MemoryMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11) {
        return new MemoryMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
    }

    public static MemoryMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Clock clock, Context context, Object obj, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Object obj2, Shutdown shutdown, Provider provider, Executor executor, Optional optional) {
        return new MemoryMetricServiceImpl(metricRecorderFactory, clock, context, (MemoryMetricMonitor) obj, listeningScheduledExecutorService, lazy, (MemoryUsageCapture) obj2, shutdown, provider, executor, optional);
    }

    @Override // javax.inject.Provider
    public MemoryMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Clock) this.clockProvider.get(), (Context) this.applicationProvider.get(), this.metricMonitorProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), DoubleCheck.lazy(this.configsProvider), this.captureProvider.get(), (Shutdown) this.shutdownProvider.get(), this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get(), (Optional) this.enableUnifiedInitProvider.get());
    }
}
