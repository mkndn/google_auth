package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.clock.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryCapture_Factory implements Factory {
    private final Provider batteryConfigurationsProvider;
    private final Provider clockProvider;
    private final Provider systemHealthCaptureProvider;
    private final Provider versionNameProvider;

    public BatteryCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.versionNameProvider = provider;
        this.systemHealthCaptureProvider = provider2;
        this.clockProvider = provider3;
        this.batteryConfigurationsProvider = provider4;
    }

    public static BatteryCapture_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new BatteryCapture_Factory(provider, provider2, provider3, provider4);
    }

    public static BatteryCapture newInstance(String str, Object obj, Clock clock, Provider provider) {
        return new BatteryCapture(str, (SystemHealthCapture) obj, clock, provider);
    }

    @Override // javax.inject.Provider
    public BatteryCapture get() {
        return newInstance((String) this.versionNameProvider.get(), this.systemHealthCaptureProvider.get(), (Clock) this.clockProvider.get(), this.batteryConfigurationsProvider);
    }
}
