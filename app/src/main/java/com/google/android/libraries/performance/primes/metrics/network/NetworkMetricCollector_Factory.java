package com.google.android.libraries.performance.primes.metrics.network;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetricCollector_Factory implements Factory {
    private final Provider configsProvider;

    public NetworkMetricCollector_Factory(Provider provider) {
        this.configsProvider = provider;
    }

    public static NetworkMetricCollector_Factory create(Provider provider) {
        return new NetworkMetricCollector_Factory(provider);
    }

    public static NetworkMetricCollector newInstance(Provider provider) {
        return new NetworkMetricCollector(provider);
    }

    @Override // javax.inject.Provider
    public NetworkMetricCollector get() {
        return newInstance(this.configsProvider);
    }
}
