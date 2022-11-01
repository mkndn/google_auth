package com.google.android.libraries.performance.primes.metrics.core;

import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricDispatcher_Factory implements Factory {
    private final Provider metricTransmittersSetProvider;

    public MetricDispatcher_Factory(Provider provider) {
        this.metricTransmittersSetProvider = provider;
    }

    public static MetricDispatcher_Factory create(Provider provider) {
        return new MetricDispatcher_Factory(provider);
    }

    public static MetricDispatcher newInstance(Lazy lazy) {
        return new MetricDispatcher(lazy);
    }

    @Override // javax.inject.Provider
    public MetricDispatcher get() {
        return newInstance(DoubleCheck.lazy(this.metricTransmittersSetProvider));
    }
}
