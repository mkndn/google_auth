package com.google.android.libraries.performance.primes.metrics.battery;

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
public final class BatteryMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider applicationContextProvider;
    private final Provider batteryCaptureProvider;
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider eagerExecutorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider samplingParametersProvider;
    private final Provider storageProvider;

    public BatteryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationContextProvider = provider2;
        this.appLifecycleMonitorProvider = provider3;
        this.eagerExecutorProvider = provider4;
        this.configsProvider = provider5;
        this.storageProvider = provider6;
        this.batteryCaptureProvider = provider7;
        this.samplingParametersProvider = provider8;
        this.deferredExecutorProvider = provider9;
    }

    public static BatteryMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9) {
        return new BatteryMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static BatteryMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Object obj, Object obj2, Provider provider, Executor executor) {
        return new BatteryMetricServiceImpl(metricRecorderFactory, context, appLifecycleMonitor, listeningScheduledExecutorService, lazy, (StatsStorage) obj, (BatteryCapture) obj2, provider, executor);
    }

    @Override // javax.inject.Provider
    public BatteryMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.applicationContextProvider.get(), (AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (ListeningScheduledExecutorService) this.eagerExecutorProvider.get(), DoubleCheck.lazy(this.configsProvider), this.storageProvider.get(), this.batteryCaptureProvider.get(), this.samplingParametersProvider, (Executor) this.deferredExecutorProvider.get());
    }
}
