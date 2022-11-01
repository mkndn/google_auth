package com.google.android.libraries.performance.primes;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideGlobalConfigurationsFactory implements Factory {
    private final Provider globalConfigurationsProvider;

    public ConfigurationsModule_ProvideGlobalConfigurationsFactory(Provider provider) {
        this.globalConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideGlobalConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideGlobalConfigurationsFactory(provider);
    }

    public static Optional provideGlobalConfigurations(Optional optional) {
        return (Optional) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideGlobalConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public Optional get() {
        return provideGlobalConfigurations((Optional) this.globalConfigurationsProvider.get());
    }
}
