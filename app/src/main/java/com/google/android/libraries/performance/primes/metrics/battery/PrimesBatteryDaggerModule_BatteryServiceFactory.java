package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesBatteryDaggerModule_BatteryServiceFactory implements Factory {
    private final Provider metricImplProvider;
    private final Provider userConfigProvider;

    public PrimesBatteryDaggerModule_BatteryServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.metricImplProvider = provider2;
    }

    public static Set batteryService(Optional optional, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesBatteryDaggerModule.batteryService(optional, provider));
    }

    public static PrimesBatteryDaggerModule_BatteryServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesBatteryDaggerModule_BatteryServiceFactory(provider, provider2);
    }

    @Override // javax.inject.Provider
    public Set get() {
        return batteryService((Optional) this.userConfigProvider.get(), this.metricImplProvider);
    }
}
