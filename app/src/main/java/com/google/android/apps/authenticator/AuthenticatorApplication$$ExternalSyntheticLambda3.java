package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda3 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda3 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda3();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda3() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        TimerConfigurations build;
        build = TimerConfigurations.newBuilder().setEnabled(PrimesFeature.enableTimerMetric()).build();
        return build;
    }
}
