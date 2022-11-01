package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideBatteryConfigurationsFactory implements Factory {
    private final Provider optionalBatteryConfigurationsProvider;

    public ConfigurationsModule_ProvideBatteryConfigurationsFactory(Provider provider) {
        this.optionalBatteryConfigurationsProvider = provider;
    }

    public static ConfigurationsModule_ProvideBatteryConfigurationsFactory create(Provider provider) {
        return new ConfigurationsModule_ProvideBatteryConfigurationsFactory(provider);
    }

    public static BatteryConfigurations provideBatteryConfigurations(Optional optional) {
        return (BatteryConfigurations) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideBatteryConfigurations(optional));
    }

    @Override // javax.inject.Provider
    public BatteryConfigurations get() {
        return provideBatteryConfigurations((Optional) this.optionalBatteryConfigurationsProvider.get());
    }
}
