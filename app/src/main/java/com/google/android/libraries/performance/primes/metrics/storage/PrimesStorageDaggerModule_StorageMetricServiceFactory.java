package com.google.android.libraries.performance.primes.metrics.storage;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesStorageDaggerModule_StorageMetricServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesStorageDaggerModule_StorageMetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static PrimesStorageDaggerModule_StorageMetricServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesStorageDaggerModule_StorageMetricServiceFactory(provider, provider2);
    }

    public static Set storageMetricService(Optional optional, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesStorageDaggerModule.storageMetricService(optional, provider));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return storageMetricService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
