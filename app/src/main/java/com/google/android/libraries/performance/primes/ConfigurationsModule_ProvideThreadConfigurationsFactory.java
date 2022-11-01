package com.google.android.libraries.performance.primes;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideThreadConfigurationsFactory implements Factory {
    private final Provider optionalThreadConfigurationsProvider;

    public ConfigurationsModule_ProvideThreadConfigurationsFactory(Provider provider) {
        this.optionalThreadConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideThreadConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideThreadConfigurationsFactory(provider);
    }

    public static PrimesThreadsConfigurations provideThreadConfigurations(Optional optional) {
        return (PrimesThreadsConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideThreadConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public PrimesThreadsConfigurations get() {
        return provideThreadConfigurations((Optional) this.optionalThreadConfigurationsProvider.get());
    }
}
