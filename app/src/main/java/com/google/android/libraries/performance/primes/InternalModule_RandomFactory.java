package com.google.android.libraries.performance.primes;

import com.google.android.libraries.clock.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Random;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalModule_RandomFactory implements Factory {
    private final Provider clockProvider;

    public InternalModule_RandomFactory(Provider provider) {
        this.clockProvider = provider;
    }

    public static InternalModule_RandomFactory create(Provider provider) {
        return new InternalModule_RandomFactory(provider);
    }

    public static Random random(Clock clock) {
        return (Random) Preconditions.checkNotNullFromProvides(InternalModule.random(clock));
    }

    @Override // javax.inject.Provider
    public Random get() {
        return random((Clock) this.clockProvider.get());
    }
}
