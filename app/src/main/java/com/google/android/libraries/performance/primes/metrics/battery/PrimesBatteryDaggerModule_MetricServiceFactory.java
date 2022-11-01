package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesBatteryDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesBatteryDaggerModule_MetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static PrimesBatteryDaggerModule_MetricServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesBatteryDaggerModule_MetricServiceFactory(provider, provider2);
    }

    public static BatteryMetricService metricService(Optional optional, Provider provider) {
        return (BatteryMetricService) Preconditions.checkNotNullFromProvides(PrimesBatteryDaggerModule.metricService(optional, provider));
    }

    @Override // javax.inject.Provider
    public BatteryMetricService get() {
        return metricService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
