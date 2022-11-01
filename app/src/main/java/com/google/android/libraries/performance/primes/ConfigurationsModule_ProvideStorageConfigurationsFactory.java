package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideStorageConfigurationsFactory implements Factory {
    private final Provider optionalStorageConfigurationsProvider;

    public ConfigurationsModule_ProvideStorageConfigurationsFactory(Provider provider) {
        this.optionalStorageConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideStorageConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideStorageConfigurationsFactory(provider);
    }

    public static StorageConfigurations provideStorageConfigurations(Optional optional) {
        return (StorageConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideStorageConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public StorageConfigurations get() {
        return provideStorageConfigurations((Optional) this.optionalStorageConfigurationsProvider.get());
    }
}
