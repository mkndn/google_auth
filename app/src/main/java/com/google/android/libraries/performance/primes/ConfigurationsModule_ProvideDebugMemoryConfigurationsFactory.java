package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory implements Factory {
    private final Provider optionalDebugMemoryConfigurationsProvider;

    public ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory(Provider provider) {
        this.optionalDebugMemoryConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideDebugMemoryConfigurationsFactory(provider);
    }

    public static DebugMemoryConfigurations provideDebugMemoryConfigurations(Optional optional) {
        return (DebugMemoryConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideDebugMemoryConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public DebugMemoryConfigurations get() {
        return provideDebugMemoryConfigurations((Optional) this.optionalDebugMemoryConfigurationsProvider.get());
    }
}
