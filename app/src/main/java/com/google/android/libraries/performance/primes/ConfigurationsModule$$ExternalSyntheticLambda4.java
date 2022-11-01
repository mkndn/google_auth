package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda4 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda4 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda4();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda4() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        NetworkConfigurations build;
        build = NetworkConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
