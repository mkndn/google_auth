package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda5 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda5 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda5();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda5() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        TraceConfigurations build;
        build = TraceConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
