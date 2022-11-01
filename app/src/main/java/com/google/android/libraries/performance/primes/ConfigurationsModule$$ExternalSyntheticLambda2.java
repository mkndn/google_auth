package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda2 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda2 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda2();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda2() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        JankConfigurations build;
        build = JankConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
