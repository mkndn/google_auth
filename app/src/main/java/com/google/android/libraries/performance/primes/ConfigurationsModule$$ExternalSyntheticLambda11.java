package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda11 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda11 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda11();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda11() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        BatteryConfigurations build;
        build = BatteryConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
