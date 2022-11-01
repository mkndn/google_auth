package com.google.android.libraries.performance.primes;

import com.google.android.libraries.clock.Clock;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesClockModule_ProvideClockFactory implements Factory {
    private final Provider clockProvider;

    public PrimesClockModule_ProvideClockFactory(Provider provider) {
        this.clockProvider = provider;
    }

    public static PrimesClockModule_ProvideClockFactory create(Provider provider) {
        return new PrimesClockModule_ProvideClockFactory(provider);
    }

    public static Clock provideClock(Optional optional) {
        return (Clock) Preconditions.checkNotNullFromProvides(PrimesClockModule.provideClock(optional));
    }

    @Override // javax.inject.Provider
    public Clock get() {
        return provideClock((Optional) this.clockProvider.get());
    }
}
