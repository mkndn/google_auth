package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ConfigurationsModule$$ExternalSyntheticLambda1 implements Provider {
    public static final /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda1 INSTANCE = new ConfigurationsModule$$ExternalSyntheticLambda1();

    private /* synthetic */ ConfigurationsModule$$ExternalSyntheticLambda1() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        StorageConfigurations build;
        build = StorageConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
