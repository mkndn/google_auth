package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda4 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda4 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda4();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda4() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        CrashConfigurations build;
        build = CrashConfigurations.newBuilder().setEnabled(PrimesFeature.enableCrashMetric()).build();
        return build;
    }
}
