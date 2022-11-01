package com.google.android.libraries.performance.primes.metrics.storage;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider applicationProvider;
    private final Provider configurationsProvider;
    private final Provider executorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider persistentRateLimitingProvider;
    private final Provider samplingParametersProvider;

    public StorageMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.executorProvider = provider4;
        this.configurationsProvider = provider5;
        this.persistentRateLimitingProvider = provider6;
        this.samplingParametersProvider = provider7;
    }

    public static StorageMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new StorageMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static StorageMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, Executor executor, Lazy lazy, PersistentRateLimiting persistentRateLimiting, Provider provider) {
        return new StorageMetricServiceImpl(metricRecorderFactory, context, appLifecycleMonitor, executor, lazy, persistentRateLimiting, provider);
    }

    @Override // javax.inject.Provider
    public StorageMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.applicationProvider.get(), (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (Executor) this.executorProvider.get(), DoubleCheck.lazy(this.configurationsProvider), (PersistentRateLimiting) this.persistentRateLimitingProvider.get(), this.samplingParametersProvider);
    }
}
