package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda7 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda7 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda7();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda7() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        CpuProfilingConfigurations build;
        build = CpuProfilingConfigurations.newBuilder().build();
        return build;
    }
}
