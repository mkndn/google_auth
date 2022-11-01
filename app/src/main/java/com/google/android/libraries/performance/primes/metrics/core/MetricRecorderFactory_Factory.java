package com.google.android.libraries.performance.primes.metrics.core;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricRecorderFactory_Factory implements Factory {
    private final Provider globalConfigurationsProvider;
    private final Provider interactionContextProvider;
    private final Provider metricDispatcherProvider;
    private final Provider metricStamperProvider;
    private final Provider recentLogsProvider;
    private final Provider samplerFactoryProvider;
    private final Provider shutdownProvider;

    public MetricRecorderFactory_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.metricDispatcherProvider = provider;
        this.metricStamperProvider = provider2;
        this.shutdownProvider = provider3;
        this.samplerFactoryProvider = provider4;
        this.globalConfigurationsProvider = provider5;
        this.recentLogsProvider = provider6;
        this.interactionContextProvider = provider7;
    }

    public static MetricRecorderFactory_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new MetricRecorderFactory_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MetricRecorderFactory newInstance(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new MetricRecorderFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    @Override // javax.inject.Provider
    public MetricRecorderFactory get() {
        return newInstance(this.metricDispatcherProvider, this.metricStamperProvider, this.shutdownProvider, this.samplerFactoryProvider, this.globalConfigurationsProvider, this.recentLogsProvider, this.interactionContextProvider);
    }
}
