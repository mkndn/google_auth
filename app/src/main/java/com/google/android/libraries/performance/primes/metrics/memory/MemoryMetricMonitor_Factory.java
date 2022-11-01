package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetricMonitor_Factory implements Factory {
    private final Provider appLifecycleMonitorProvider;
    private final Provider executorServiceProvider;

    public MemoryMetricMonitor_Factory(Provider provider, Provider provider2) {
        this.appLifecycleMonitorProvider = provider;
        this.executorServiceProvider = provider2;
    }

    public static MemoryMetricMonitor_Factory create(Provider provider, Provider provider2) {
        return new MemoryMetricMonitor_Factory(provider, provider2);
    }

    public static MemoryMetricMonitor newInstance(AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService) {
        return new MemoryMetricMonitor(appLifecycleMonitor, listeningScheduledExecutorService);
    }

    @Override // javax.inject.Provider
    public MemoryMetricMonitor get() {
        return newInstance((AppLifecycleMonitor) this.appLifecycleMonitorProvider.get(), (ListeningScheduledExecutorService) this.executorServiceProvider.get());
    }
}
