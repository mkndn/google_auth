package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideNetworkConfigurationsFactory implements Factory {
    private final Provider optionalNetworkConfigurationsProvider;

    public ConfigurationsModule_ProvideNetworkConfigurationsFactory(Provider provider) {
        this.optionalNetworkConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideNetworkConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideNetworkConfigurationsFactory(provider);
    }

    public static NetworkConfigurations provideNetworkConfigurations(Optional optional) {
        return (NetworkConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideNetworkConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public NetworkConfigurations get() {
        return provideNetworkConfigurations((Optional) this.optionalNetworkConfigurationsProvider.get());
    }
}
