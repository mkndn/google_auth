package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesCrashDaggerModule_CrashServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesCrashDaggerModule_CrashServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static Set crashService(Optional optional, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesCrashDaggerModule.crashService(optional, provider));
    }

    public static PrimesCrashDaggerModule_CrashServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesCrashDaggerModule_CrashServiceFactory(provider, provider2);
    }

    @Override // javax.inject.Provider
    public Set get() {
        return crashService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
