package com.google.android.libraries.performance.primes;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesExecutorsModule_ProvideDeferrableExecutorFactory implements Factory {
    private final Provider deferrableExecutorProvider;
    private final Provider scheduledExecutorProvider;
    private final Provider threadsConfigurationsProvider;

    public PrimesExecutorsModule_ProvideDeferrableExecutorFactory(Provider provider, Provider provider2, Provider provider3) {
        this.deferrableExecutorProvider = provider;
        this.scheduledExecutorProvider = provider2;
        this.threadsConfigurationsProvider = provider3;
    }

    public static PrimesExecutorsModule_ProvideDeferrableExecutorFactory create(Provider provider, Provider provider2, Provider provider3) {
        return new PrimesExecutorsModule_ProvideDeferrableExecutorFactory(provider, provider2, provider3);
    }

    public static Executor provideDeferrableExecutor(Provider provider, Provider provider2, PrimesThreadsConfigurations primesThreadsConfigurations) {
        return (Executor) Preconditions.checkNotNullFromProvides(PrimesExecutorsModule.provideDeferrableExecutor(provider, provider2, primesThreadsConfigurations));
    }

    @Override // javax.inject.Provider
    public Executor get() {
        return provideDeferrableExecutor(this.deferrableExecutorProvider, this.scheduledExecutorProvider, (PrimesThreadsConfigurations) this.threadsConfigurationsProvider.get());
    }
}
