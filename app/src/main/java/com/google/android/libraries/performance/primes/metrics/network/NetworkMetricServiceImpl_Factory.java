package com.google.android.libraries.performance.primes.metrics.network;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider configsProvider;
    private final Provider contextProvider;
    private final Provider deferredExecutorProvider;
    private final Provider executorServiceProvider;
    private final Provider metricCollectorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;

    public NetworkMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        this.metricRecorderFactoryProvider = provider;
        this.contextProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.executorServiceProvider = provider4;
        this.configsProvider = provider5;
        this.metricCollectorProvider = provider6;
        this.samplingParametersProvider = provider7;
        this.deferredExecutorProvider = provider8;
    }

    public static NetworkMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        return new NetworkMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static NetworkMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Lazy lazy2, Provider provider, Executor executor) {
        return new NetworkMetricServiceImpl(metricRecorderFactory, context, appLifecycleMonitor, listeningScheduledExecutorService, lazy, lazy2, provider, executor);
    }

    @Override // javax.inject.Provider
    public NetworkMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.contextProvider.get(), (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), DoubleCheck.lazy(this.configsProvider), DoubleCheck.lazy(this.metricCollectorProvider), this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get());
    }
}
