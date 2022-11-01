package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.content.Context;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingService_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider clockProvider;
    private final Provider configsProvider;
    private final Provider executorServiceProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider schedulerProvider;

    public CpuProfilingService_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationProvider = provider2;
        this.executorServiceProvider = provider3;
        this.configsProvider = provider4;
        this.samplingParametersProvider = provider5;
        this.clockProvider = provider6;
        this.schedulerProvider = provider7;
    }

    public static CpuProfilingService_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new CpuProfilingService_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static CpuProfilingService newInstance(MetricRecorderFactory metricRecorderFactory, Context context, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Provider provider, Clock clock, Provider provider2) {
        return new CpuProfilingService(metricRecorderFactory, context, listeningScheduledExecutorService, lazy, provider, clock, provider2);
    }

    @Override // javax.inject.Provider
    public CpuProfilingService get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.applicationProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get(), DoubleCheck.lazy(this.configsProvider), this.samplingParametersProvider, (Clock) this.clockProvider.get(), this.schedulerProvider);
    }
}
