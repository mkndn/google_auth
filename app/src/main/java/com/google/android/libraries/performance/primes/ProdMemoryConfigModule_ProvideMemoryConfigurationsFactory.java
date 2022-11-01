package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory implements Factory {
    private final Provider optionalMemoryConfigurationsProvider;

    public ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory(Provider provider) {
        this.optionalMemoryConfigurationsProvider = provider;
    }

    public static ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory create(Provider provider) {
        return new ProdMemoryConfigModule_ProvideMemoryConfigurationsFactory(provider);
    }

    public static MemoryConfigurations provideMemoryConfigurations(Optional optional) {
        return (MemoryConfigurations) Preconditions.checkNotNullFromProvides(ProdMemoryConfigModule.provideMemoryConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public MemoryConfigurations get() {
        return provideMemoryConfigurations((Optional) this.optionalMemoryConfigurationsProvider.get());
    }
}
