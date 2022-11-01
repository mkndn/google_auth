package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideCrashConfigurationsFactory implements Factory {
    private final Provider optionalCrashConfigurationsProvider;

    public ConfigurationsModule_ProvideCrashConfigurationsFactory(Provider provider) {
        this.optionalCrashConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideCrashConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideCrashConfigurationsFactory(provider);
    }

    public static CrashConfigurations provideCrashConfigurations(Optional optional) {
        return (CrashConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideCrashConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public CrashConfigurations get() {
        return provideCrashConfigurations((Optional) this.optionalCrashConfigurationsProvider.get());
    }
}
