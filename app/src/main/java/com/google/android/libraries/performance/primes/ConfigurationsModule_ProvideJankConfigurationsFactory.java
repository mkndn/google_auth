package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideJankConfigurationsFactory implements Factory {
    private final Provider optionalJankConfigurationsProvider;

    public ConfigurationsModule_ProvideJankConfigurationsFactory(Provider provider) {
        this.optionalJankConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideJankConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideJankConfigurationsFactory(provider);
    }

    public static JankConfigurations provideJankConfigurations(Optional optional) {
        return (JankConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideJankConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public JankConfigurations get() {
        return provideJankConfigurations((Optional) this.optionalJankConfigurationsProvider.get());
    }
}
