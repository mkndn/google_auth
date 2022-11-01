package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideTimerConfigurationsFactory implements Factory {
    private final Provider optionalTimerConfigurationsProvider;

    public ConfigurationsModule_ProvideTimerConfigurationsFactory(Provider provider) {
        this.optionalTimerConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideTimerConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideTimerConfigurationsFactory(provider);
    }

    public static TimerConfigurations provideTimerConfigurations(Optional optional) {
        return (TimerConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideTimerConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public TimerConfigurations get() {
        return provideTimerConfigurations((Optional) this.optionalTimerConfigurationsProvider.get());
    }
}
