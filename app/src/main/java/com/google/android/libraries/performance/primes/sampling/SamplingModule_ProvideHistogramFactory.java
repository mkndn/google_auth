package com.google.android.libraries.performance.primes.sampling;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Random;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SamplingModule_ProvideHistogramFactory implements Factory {
    private final Provider randomProvider;

    public SamplingModule_ProvideHistogramFactory(Provider provider) {
        this.randomProvider = provider;
    }

    public static SamplingModule_ProvideHistogramFactory create(Provider provider) {
        return new SamplingModule_ProvideHistogramFactory(provider);
    }

    public static ApproximateHistogram provideHistogram(Random random) {
        return (ApproximateHistogram) Preconditions.checkNotNullFromProvides(SamplingModule.provideHistogram(random));
    }

    @Override // javax.inject.Provider
    public ApproximateHistogram get() {
        return provideHistogram((Random) this.randomProvider.get());
    }
}
