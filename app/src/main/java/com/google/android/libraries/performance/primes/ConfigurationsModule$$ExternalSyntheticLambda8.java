package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda8 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda8 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda8();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda8() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        DebugMemoryConfigurations build;
        build = DebugMemoryConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
