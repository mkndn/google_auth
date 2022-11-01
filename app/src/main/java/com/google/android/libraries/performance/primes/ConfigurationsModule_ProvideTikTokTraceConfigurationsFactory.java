package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideTikTokTraceConfigurationsFactory implements Factory {
    private final Provider optionalTikTokTraceConfigurationsProvider;

    public ConfigurationsModule_ProvideTikTokTraceConfigurationsFactory(Provider provider) {
        this.optionalTikTokTraceConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideTikTokTraceConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideTikTokTraceConfigurationsFactory(provider);
    }

    public static TikTokTraceConfigurations provideTikTokTraceConfigurations(Optional optional) {
        return (TikTokTraceConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideTikTokTraceConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public TikTokTraceConfigurations get() {
        return provideTikTokTraceConfigurations((Optional) this.optionalTikTokTraceConfigurationsProvider.get());
    }
}
