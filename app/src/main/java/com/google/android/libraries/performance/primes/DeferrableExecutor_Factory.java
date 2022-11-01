package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeferrableExecutor_Factory implements Factory {
    private final Provider executorProvider;
    private final Provider lifecycleMonitorProvider;

    public DeferrableExecutor_Factory(Provider provider, Provider provider2) {
        this.executorProvider = provider;
        this.lifecycleMonitorProvider = provider2;
    }

    public static DeferrableExecutor_Factory create(Provider provider, Provider provider2) {
        return new DeferrableExecutor_Factory(provider, provider2);
    }

    public static DeferrableExecutor newInstance(ListeningScheduledExecutorService listeningScheduledExecutorService, AppLifecycleMonitor appLifecycleMonitor) {
        return new DeferrableExecutor(listeningScheduledExecutorService, appLifecycleMonitor);
    }

    @Override // javax.inject.Provider
    public DeferrableExecutor get() {
        return newInstance((ListeningScheduledExecutorService) this.executorProvider.get(), (AppLifecycleMonitor) this.lifecycleMonitorProvider.get());
    }
}
