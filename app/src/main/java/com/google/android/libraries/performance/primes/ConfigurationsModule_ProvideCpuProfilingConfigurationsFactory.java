package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideCpuProfilingConfigurationsFactory implements Factory {
    private final Provider optionalCpuProfilingConfigurationsProvider;

    public ConfigurationsModule_ProvideCpuProfilingConfigurationsFactory(Provider provider) {
        this.optionalCpuProfilingConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideCpuProfilingConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideCpuProfilingConfigurationsFactory(provider);
    }

    public static CpuProfilingConfigurations provideCpuProfilingConfigurations(Optional optional) {
        return (CpuProfilingConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideCpuProfilingConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public CpuProfilingConfigurations get() {
        return provideCpuProfilingConfigurations((Optional) this.optionalCpuProfilingConfigurationsProvider.get());
    }
}
