package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory implements Factory {
    private final Provider metricServiceProvider;
    private final Provider userConfigProvider;

    public PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.metricServiceProvider = provider2;
    }

    public static Set cpuProfilingService(Optional optional, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesCpuProfilingDaggerModule.cpuProfilingService(optional, provider));
    }

    public static PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesCpuProfilingDaggerModule_CpuProfilingServiceFactory(provider, provider2);
    }

    @Override // javax.inject.Provider
    public Set get() {
        return cpuProfilingService((Optional) this.userConfigProvider.get(), this.metricServiceProvider);
    }
}
