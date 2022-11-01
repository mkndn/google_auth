package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda7 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda7 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda7();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda7() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        BatteryConfigurations build;
        build = BatteryConfigurations.newBuilder().setEnabled(PrimesFeature.enableBatteryMetric()).build();
        return build;
    }
}
