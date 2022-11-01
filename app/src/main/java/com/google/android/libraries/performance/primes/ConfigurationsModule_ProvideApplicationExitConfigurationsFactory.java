package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideApplicationExitConfigurationsFactory implements Factory {
    private final Provider optionalApplicationExitConfigurationsProvider;

    public ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(Provider provider) {
        this.optionalApplicationExitConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideApplicationExitConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider);
    }

    public static ApplicationExitConfigurations provideApplicationExitConfigurations(Optional optional) {
        return (ApplicationExitConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideApplicationExitConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public ApplicationExitConfigurations get() {
        return provideApplicationExitConfigurations((Optional) this.optionalApplicationExitConfigurationsProvider.get());
    }
}
