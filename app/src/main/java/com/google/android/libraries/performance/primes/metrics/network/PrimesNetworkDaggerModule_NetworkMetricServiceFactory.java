package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesNetworkDaggerModule_NetworkMetricServiceFactory implements Factory {
    private final Provider networkMetricServiceProvider;
    private final Provider userConfigProvider;

    public PrimesNetworkDaggerModule_NetworkMetricServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.networkMetricServiceProvider = provider2;
    }

    public static PrimesNetworkDaggerModule_NetworkMetricServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesNetworkDaggerModule_NetworkMetricServiceFactory(provider, provider2);
    }

    public static Set networkMetricService(Optional optional, Object obj) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesNetworkDaggerModule.networkMetricService(optional, (NetworkMetricServiceImpl) obj));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return networkMetricService((Optional) this.userConfigProvider.get(), this.networkMetricServiceProvider.get());
    }
}
