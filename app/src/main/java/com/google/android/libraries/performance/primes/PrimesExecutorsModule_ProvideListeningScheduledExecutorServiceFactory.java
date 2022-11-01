package com.google.android.libraries.performance.primes;

import com.google.android.libraries.concurrent.ExecutorDecorator;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesExecutorsModule_ProvideListeningScheduledExecutorServiceFactory implements Factory {
    private final Provider executorDecoratorProvider;
    private final Provider threadsConfigurationsProvider;

    public PrimesExecutorsModule_ProvideListeningScheduledExecutorServiceFactory(Provider provider, Provider provider2) {
        this.threadsConfigurationsProvider = provider;
        this.executorDecoratorProvider = provider2;
    }

    public static PrimesExecutorsModule_ProvideListeningScheduledExecutorServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesExecutorsModule_ProvideListeningScheduledExecutorServiceFactory(provider, provider2);
    }

    public static ListeningScheduledExecutorService provideListeningScheduledExecutorService(PrimesThreadsConfigurations primesThreadsConfigurations, ExecutorDecorator executorDecorator) {
        return (ListeningScheduledExecutorService) Preconditions.checkNotNullFromProvides(PrimesExecutorsModule.provideListeningScheduledExecutorService(primesThreadsConfigurations, executorDecorator));
    }

    @Override // javax.inject.Provider
    public ListeningScheduledExecutorService get() {
        return provideListeningScheduledExecutorService((PrimesThreadsConfigurations) this.threadsConfigurationsProvider.get(), (ExecutorDecorator) this.executorDecoratorProvider.get());
    }
}
