package com.google.android.libraries.performance.primes.sampling;

import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import dagger.internal.Factory;
import java.util.Random;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SamplingStrategy_Factory_Factory implements Factory {
    private final Provider approximateHistogramProvider;
    private final Provider clockProvider;
    private final Provider randomProvider;

    public SamplingStrategy_Factory_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.randomProvider = provider;
        this.approximateHistogramProvider = provider2;
        this.clockProvider = provider3;
    }

    public static SamplingStrategy_Factory_Factory create(Provider provider, Provider provider2, Provider provider3) {
        return new SamplingStrategy_Factory_Factory(provider, provider2, provider3);
    }

    public static SamplingStrategy.Factory newInstance(Random random, ApproximateHistogram approximateHistogram, Clock clock) {
        return new SamplingStrategy.Factory(random, approximateHistogram, clock);
    }

    @Override // javax.inject.Provider
    public SamplingStrategy.Factory get() {
        return newInstance((Random) this.randomProvider.get(), (ApproximateHistogram) this.approximateHistogramProvider.get(), (Clock) this.clockProvider.get());
    }
}
