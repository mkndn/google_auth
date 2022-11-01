package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda5 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda5 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda5();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda5() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        NetworkConfigurations build;
        build = NetworkConfigurations.newBuilder().setEnabled(PrimesFeature.enableNetworkMetric()).build();
        return build;
    }
}
