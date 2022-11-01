package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda3 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda3 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda3();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda3() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        ApplicationExitConfigurations build;
        build = ApplicationExitConfigurations.newBuilder().build();
        return build;
    }
}
