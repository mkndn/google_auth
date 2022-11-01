package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda9 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda9 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda9();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda9() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        TikTokTraceConfigurations build;
        build = TikTokTraceConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
