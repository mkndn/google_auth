package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.content.Context;
import com.google.android.libraries.clock.Clock;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfilingServiceScheduler_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider clockProvider;
    private final Provider configsProvider;

    public CpuProfilingServiceScheduler_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.clockProvider = provider;
        this.configsProvider = provider2;
        this.applicationProvider = provider3;
    }

    public static CpuProfilingServiceScheduler_Factory create(Provider provider, Provider provider2, Provider provider3) {
        return new CpuProfilingServiceScheduler_Factory(provider, provider2, provider3);
    }

    public static CpuProfilingServiceScheduler newInstance(Clock clock, Lazy lazy, Context context) {
        return new CpuProfilingServiceScheduler(clock, lazy, context);
    }

    @Override // javax.inject.Provider
    public CpuProfilingServiceScheduler get() {
        return newInstance((Clock) this.clockProvider.get(), DoubleCheck.lazy(this.configsProvider), (Context) this.applicationProvider.get());
    }
}
