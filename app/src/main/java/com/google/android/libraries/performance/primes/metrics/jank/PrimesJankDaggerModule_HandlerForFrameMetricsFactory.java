package com.google.android.libraries.performance.primes.metrics.jank;

import android.os.Handler;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesJankDaggerModule_HandlerForFrameMetricsFactory implements Factory {
    private final Provider lightweightExecutorLooperProvider;

    public PrimesJankDaggerModule_HandlerForFrameMetricsFactory(Provider provider) {
        this.lightweightExecutorLooperProvider = provider;
    }

    public static PrimesJankDaggerModule_HandlerForFrameMetricsFactory create(Provider provider) {
        return new PrimesJankDaggerModule_HandlerForFrameMetricsFactory(provider);
    }

    public static Handler handlerForFrameMetrics(Optional optional) {
        return (Handler) Preconditions.checkNotNullFromProvides(PrimesJankDaggerModule.handlerForFrameMetrics(optional));
    }

    @Override // javax.inject.Provider
    public Handler get() {
        return handlerForFrameMetrics((Optional) this.lightweightExecutorLooperProvider.get());
    }
}
