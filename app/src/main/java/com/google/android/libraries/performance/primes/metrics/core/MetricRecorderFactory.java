package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.sampling.SamplerFactory;
import com.google.common.base.Optional;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricRecorderFactory {
    private final Provider globalConfigurationsProvider;
    private final Provider interactionContextProviderProvider;
    private final Provider metricDispatcherProvider;
    private final Provider metricStamperProviderProvider;
    private final Provider recentLogsProvider;
    private final Provider samplerFactoryProvider;
    private final Provider shutdownProvider;

    @Inject
    public MetricRecorderFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.metricDispatcherProvider = (Provider) checkNotNull(provider, 1);
        this.metricStamperProviderProvider = (Provider) checkNotNull(provider2, 2);
        this.shutdownProvider = (Provider) checkNotNull(provider3, 3);
        this.samplerFactoryProvider = (Provider) checkNotNull(provider4, 4);
        this.globalConfigurationsProvider = (Provider) checkNotNull(provider5, 5);
        this.recentLogsProvider = (Provider) checkNotNull(provider6, 6);
        this.interactionContextProviderProvider = (Provider) checkNotNull(provider7, 7);
    }

    private static Object checkNotNull(Object obj, int i) {
        if (obj == null) {
            throw new NullPointerException("@AutoFactory method argument is null but is not marked @Nullable. Argument index: " + i);
        }
        return obj;
    }

    public MetricRecorder create(Executor executor, Lazy lazy, Provider provider) {
        return new MetricRecorder((MetricDispatcher) checkNotNull((MetricDispatcher) this.metricDispatcherProvider.get(), 1), this.metricStamperProviderProvider, (Shutdown) checkNotNull((Shutdown) this.shutdownProvider.get(), 3), (SamplerFactory) checkNotNull((SamplerFactory) this.samplerFactoryProvider.get(), 4), this.globalConfigurationsProvider, (RecentLogs) checkNotNull((RecentLogs) this.recentLogsProvider.get(), 6), (Optional) checkNotNull((Optional) this.interactionContextProviderProvider.get(), 7), (Executor) checkNotNull(executor, 8), (Lazy) checkNotNull(lazy, 9), provider);
    }
}
