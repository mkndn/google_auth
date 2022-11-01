package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda0 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda0 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda0();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda0() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        CrashConfigurations build;
        build = CrashConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
