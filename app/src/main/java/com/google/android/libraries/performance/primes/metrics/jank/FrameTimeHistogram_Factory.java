package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.libraries.clock.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameTimeHistogram_Factory implements Factory {
    private final Provider clockProvider;

    public FrameTimeHistogram_Factory(Provider provider) {
        this.clockProvider = provider;
    }

    public static FrameTimeHistogram_Factory create(Provider provider) {
        return new FrameTimeHistogram_Factory(provider);
    }

    public static FrameTimeHistogram newInstance(Clock clock) {
        return new FrameTimeHistogram(clock);
    }

    @Override // javax.inject.Provider
    public FrameTimeHistogram get() {
        return newInstance((Clock) this.clockProvider.get());
    }
}
