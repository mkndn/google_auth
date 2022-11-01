package com.google.android.libraries.performance.primes.metrics.crash;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import com.google.common.base.Optional;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider applicationProvider;
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider enableUnifiedInitProvider;
    private final Provider exceptionMessageMappingFunctionsProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider nativeCrashHandlerProvider;
    private final Provider probabilitySamplerFactoryProvider;
    private final Provider recordingTimeoutsProvider;

    public CrashMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationProvider = provider2;
        this.deferredExecutorProvider = provider3;
        this.configsProvider = provider4;
        this.nativeCrashHandlerProvider = provider5;
        this.appLifecycleMonitorProvider = provider6;
        this.probabilitySamplerFactoryProvider = provider7;
        this.exceptionMessageMappingFunctionsProvider = provider8;
        this.enableUnifiedInitProvider = provider9;
        this.recordingTimeoutsProvider = provider10;
    }

    public static CrashMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10) {
        return new CrashMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static CrashMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, Executor executor, Lazy lazy, Optional optional, AppLifecycleMonitor appLifecycleMonitor, ProbabilitySamplerFactory probabilitySamplerFactory, Lazy lazy2, Optional optional2, Provider provider) {
        return new CrashMetricServiceImpl(metricRecorderFactory, context, executor, lazy, optional, appLifecycleMonitor, probabilitySamplerFactory, lazy2, optional2, provider);
    }

    @Override // javax.inject.Provider
    public CrashMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.applicationProvider.get(), (Executor) this.deferredExecutorProvider.get(), DoubleCheck.lazy(this.configsProvider), (Optional) this.nativeCrashHandlerProvider.get(), (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (ProbabilitySamplerFactory) this.probabilitySamplerFactoryProvider.get(), DoubleCheck.lazy(this.exceptionMessageMappingFunctionsProvider), (Optional) this.enableUnifiedInitProvider.get(), this.recordingTimeoutsProvider);
    }
}
