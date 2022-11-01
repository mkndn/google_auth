package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideTraceConfigurationsFactory implements Factory {
    private final Provider optionalTraceConfigurationsProvider;

    public ConfigurationsModule_ProvideTraceConfigurationsFactory(Provider provider) {
        this.optionalTraceConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideTraceConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideTraceConfigurationsFactory(provider);
    }

    public static TraceConfigurations provideTraceConfigurations(Optional optional) {
        return (TraceConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideTraceConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public TraceConfigurations get() {
        return provideTraceConfigurations((Optional) this.optionalTraceConfigurationsProvider.get());
    }
}
