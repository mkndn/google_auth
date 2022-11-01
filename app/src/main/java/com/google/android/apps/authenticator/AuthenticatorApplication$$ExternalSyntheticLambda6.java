package com.google.android.apps.authenticator;

import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import googledata.experiments.mobile.authenticator_android.features.PrimesFeature;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AuthenticatorApplication$$ExternalSyntheticLambda6 implements Provider {
    public static final /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda6 INSTANCE = new AuthenticatorApplication$$ExternalSyntheticLambda6();

    private /* synthetic */ AuthenticatorApplication$$ExternalSyntheticLambda6() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        StorageConfigurations build;
        build = StorageConfigurations.newBuilder().setEnabled(PrimesFeature.enablePackageMetric()).build();
        return build;
    }
}
