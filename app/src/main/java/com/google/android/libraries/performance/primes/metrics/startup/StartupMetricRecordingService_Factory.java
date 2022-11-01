package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMetricRecordingService_Factory implements Factory {
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider executorServiceProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;

    public StartupMetricRecordingService_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.metricRecorderFactoryProvider = provider;
        this.executorServiceProvider = provider2;
        this.deferredExecutorProvider = provider3;
        this.configsProvider = provider4;
        this.samplingParametersProvider = provider5;
    }

    public static StartupMetricRecordingService_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new StartupMetricRecordingService_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static StartupMetricRecordingService newInstance(MetricRecorderFactory metricRecorderFactory, ListeningScheduledExecutorService listeningScheduledExecutorService, Executor executor, Lazy lazy, Provider provider) {
        return new StartupMetricRecordingService(metricRecorderFactory, listeningScheduledExecutorService, executor, lazy, provider);
    }

    @Override // javax.inject.Provider
    public StartupMetricRecordingService get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), (Executor) this.deferredExecutorProvider.get(), DoubleCheck.lazy(this.configsProvider), this.samplingParametersProvider);
    }
}
