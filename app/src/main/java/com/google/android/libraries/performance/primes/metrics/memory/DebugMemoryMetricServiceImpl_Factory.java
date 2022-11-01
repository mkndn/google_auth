package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.clock.Clock;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DebugMemoryMetricServiceImpl_Factory implements Factory {
    private final Provider clockProvider;
    private final Provider configProvider;
    private final Provider deferredExecutorProvider;
    private final Provider memoryMetricServiceProvider;

    public DebugMemoryMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.configProvider = provider;
        this.deferredExecutorProvider = provider2;
        this.memoryMetricServiceProvider = provider3;
        this.clockProvider = provider4;
    }

    public static DebugMemoryMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new DebugMemoryMetricServiceImpl_Factory(provider, provider2, provider3, provider4);
    }

    public static DebugMemoryMetricServiceImpl newInstance(Provider provider, Executor executor, MemoryMetricService memoryMetricService, Clock clock) {
        return new DebugMemoryMetricServiceImpl(provider, executor, memoryMetricService, clock);
    }

    @Override // javax.inject.Provider
    public DebugMemoryMetricServiceImpl get() {
        return newInstance(this.configProvider, (Executor) this.deferredExecutorProvider.get(), (MemoryMetricService) this.memoryMetricServiceProvider.get(), (Clock) this.clockProvider.get());
    }
}
