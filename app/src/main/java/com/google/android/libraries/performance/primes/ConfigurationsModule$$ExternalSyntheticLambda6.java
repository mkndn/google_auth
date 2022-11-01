package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda6 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda6 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda6();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda6() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        TimerConfigurations build;
        build = TimerConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
