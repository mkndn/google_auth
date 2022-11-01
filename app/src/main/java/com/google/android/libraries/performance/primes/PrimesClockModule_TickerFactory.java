package com.google.android.libraries.performance.primes;

import com.google.android.libraries.clock.Clock;
import com.google.common.base.Ticker;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesClockModule_TickerFactory implements Factory {
    private final Provider clockProvider;

    public PrimesClockModule_TickerFactory(Provider provider) {
        this.clockProvider = provider;
    }

    public static PrimesClockModule_TickerFactory create(Provider provider) {
        return new PrimesClockModule_TickerFactory(provider);
    }

    public static Ticker ticker(Clock clock) {
        return (Ticker) Preconditions.checkNotNullFromProvides(PrimesClockModule.ticker(clock));
    }

    @Override // javax.inject.Provider
    public Ticker get() {
        return ticker((Clock) this.clockProvider.get());
    }
}
