package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesJankDaggerModule_JankServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider userConfigProvider;

    public PrimesJankDaggerModule_JankServiceFactory(Provider provider, Provider provider2) {
        this.userConfigProvider = provider;
        this.implProvider = provider2;
    }

    public static PrimesJankDaggerModule_JankServiceFactory create(Provider provider, Provider provider2) {
        return new PrimesJankDaggerModule_JankServiceFactory(provider, provider2);
    }

    public static Set jankService(Optional optional, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesJankDaggerModule.jankService(optional, provider));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return jankService((Optional) this.userConfigProvider.get(), this.implProvider);
    }
}
