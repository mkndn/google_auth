package com.google.android.libraries.performance.primes.metrics.jank;

import android.content.Context;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameMetricServiceImpl_Factory implements Factory {
    private final Provider activityLevelJankMonitorProvider;
    private final Provider appLifecycleMonitorProvider;
    private final Provider configsProvider;
    private final Provider contextProvider;
    private final Provider deferredExecutorProvider;
    private final Provider frameTimeHistogramProvider;
    private final Provider handlerProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider perfettoConfigurationsProvider;
    private final Provider perfettoTriggerProvider;
    private final Provider registerFrameMetricsListenerInOnCreateProvider;
    private final Provider samplingParametersProvider;

    public FrameMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12) {
        this.metricRecorderFactoryProvider = provider;
        this.contextProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.configsProvider = provider4;
        this.activityLevelJankMonitorProvider = provider5;
        this.frameTimeHistogramProvider = provider6;
        this.samplingParametersProvider = provider7;
        this.deferredExecutorProvider = provider8;
        this.handlerProvider = provider9;
        this.perfettoTriggerProvider = provider10;
        this.perfettoConfigurationsProvider = provider11;
        this.registerFrameMetricsListenerInOnCreateProvider = provider12;
    }

    public static FrameMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12) {
        return new FrameMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static FrameMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, Lazy lazy, Object obj, Provider provider, Provider provider2, Executor executor, Lazy lazy2, PerfettoTrigger perfettoTrigger, Provider provider3, boolean z) {
        return new FrameMetricServiceImpl(metricRecorderFactory, context, appLifecycleMonitor, lazy, (ActivityLevelJankMonitor) obj, provider, provider2, executor, lazy2, perfettoTrigger, provider3, z);
    }

    @Override // javax.inject.Provider
    public FrameMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.contextProvider.get(), (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), DoubleCheck.lazy(this.configsProvider), this.activityLevelJankMonitorProvider.get(), this.frameTimeHistogramProvider, this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get(), DoubleCheck.lazy(this.handlerProvider), (PerfettoTrigger) this.perfettoTriggerProvider.get(), this.perfettoConfigurationsProvider, ((Boolean) this.registerFrameMetricsListenerInOnCreateProvider.get()).booleanValue());
    }
}
