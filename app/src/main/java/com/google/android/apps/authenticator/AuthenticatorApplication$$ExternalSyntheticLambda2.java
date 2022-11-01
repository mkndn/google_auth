package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda2 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda2 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda2();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda2() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        MemoryConfigurations build;
        build = MemoryConfigurations.newBuilder().setEnabled(PrimesFeature.enableMemoryMetric()).build();
        return build;
    }
}
