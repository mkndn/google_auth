package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerMetricServiceWithTracingImpl_Factory implements Factory {
    private final Provider timerMetricServiceProvider;
    private final Provider traceMetricServiceProvider;

    public TimerMetricServiceWithTracingImpl_Factory(Provider provider, Provider provider2) {
        this.timerMetricServiceProvider = provider;
        this.traceMetricServiceProvider = provider2;
    }

    public static TimerMetricServiceWithTracingImpl_Factory create(Provider provider, Provider provider2) {
        return new TimerMetricServiceWithTracingImpl_Factory(provider, provider2);
    }

    public static TimerMetricServiceWithTracingImpl newInstance(Object obj, Optional optional) {
        return new TimerMetricServiceWithTracingImpl((TimerMetricServiceImpl) obj, optional);
    }

    @Override // javax.inject.Provider
    public TimerMetricServiceWithTracingImpl get() {
        return newInstance(this.timerMetricServiceProvider.get(), (Optional) this.traceMetricServiceProvider.get());
    }
}
