package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory implements Factory {
    private final Provider configsProvider;

    public PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory(Provider provider) {
        this.configsProvider = provider;
    }

    public static PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory create(Provider provider) {
        return new PrimesStartupDaggerModule_ProvideStartupConfigurationsFactory(provider);
    }

    public static StartupConfigurations provideStartupConfigurations(Optional optional) {
        return (StartupConfigurations) Preconditions.checkNotNullFromProvides(PrimesStartupDaggerModule.provideStartupConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public StartupConfigurations get() {
        return provideStartupConfigurations((Optional) this.configsProvider.get());
    }
}
