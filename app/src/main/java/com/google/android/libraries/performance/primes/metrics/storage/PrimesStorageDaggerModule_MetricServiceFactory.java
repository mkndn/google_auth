package com.google.android.libraries.performance.primes.metrics.storage;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesStorageDaggerModule_MetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesStorageDaggerModule_MetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static PrimesStorageDaggerModule_MetricServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesStorageDaggerModule_MetricServiceFactory(provider, provider2);
    }

    public static StorageMetricService metricService(Optional optional, Provider provider) {
        return (StorageMetricService) Preconditions.checkNotNullFromProvides(PrimesStorageDaggerModule.metricService(optional, provider));
    }

    @Override // javax.inject.Provider
    public StorageMetricService get() {
        return metricService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
