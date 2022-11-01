package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMetricServiceImpl_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider enableStartupBaselineDiscardingProvider;
    private final Provider firstDrawTypeProvider;
    private final Provider startupMetricRecordingServiceProvider;

    public StartupMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.appLifecycleMonitorProvider = provider;
        this.startupMetricRecordingServiceProvider = provider2;
        this.enableStartupBaselineDiscardingProvider = provider3;
        this.firstDrawTypeProvider = provider4;
    }

    public static StartupMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new StartupMetricServiceImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static StartupMetricServiceImpl newInstance(AppLifecycleMonitor appLifecycleMonitor, Provider provider, Provider provider2, Provider provider3) {
        return new StartupMetricServiceImpl(appLifecycleMonitor, provider, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public StartupMetricServiceImpl get() {
        return newInstance((AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), this.startupMetricRecordingServiceProvider, this.enableStartupBaselineDiscardingProvider, this.firstDrawTypeProvider);
    }
}
