package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceDaggerModule_TimerMetricServiceSupportFactory implements Factory {
    private final Provider implProvider;
    private final Provider userTikTokTraceConfigProvider;
    private final Provider userTraceConfigProvider;

    public PrimesTraceDaggerModule_TimerMetricServiceSupportFactory(Provider provider, Provider provider2, Provider provider3) {
        this.userTraceConfigProvider = provider;
        this.userTikTokTraceConfigProvider = provider2;
        this.implProvider = provider3;
    }

    public static PrimesTraceDaggerModule_TimerMetricServiceSupportFactory create(Provider provider, Provider provider2, Provider provider3) {
        return new PrimesTraceDaggerModule_TimerMetricServiceSupportFactory(provider, provider2, provider3);
    }

    public static Optional timerMetricServiceSupport(Optional optional, Optional optional2, Provider provider) {
        return (Optional) Preconditions.checkNotNullFromProvides(PrimesTraceDaggerModule.timerMetricServiceSupport(optional, optional2, provider));
    }

    @Override // javax.inject.Provider
    public Optional get() {
        return timerMetricServiceSupport((Optional) this.userTraceConfigProvider.get(), (Optional) this.userTikTokTraceConfigProvider.get(), this.implProvider);
    }
}
