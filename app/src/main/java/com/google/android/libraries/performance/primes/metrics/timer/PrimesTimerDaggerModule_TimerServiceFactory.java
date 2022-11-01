package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTimerDaggerModule_TimerServiceFactory implements Factory {
    private final Provider implProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider traceUserConfigProvider;
    private final Provider userConfigProvider;
    private final Provider withTracingImplProvider;

    public PrimesTimerDaggerModule_TimerServiceFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.userConfigProvider = provider;
        this.traceUserConfigProvider = provider2;
        this.traceMetricServiceProvider = provider3;
        this.implProvider = provider4;
        this.withTracingImplProvider = provider5;
    }

    public static PrimesTimerDaggerModule_TimerServiceFactory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new PrimesTimerDaggerModule_TimerServiceFactory(provider, provider2, provider3, provider4, provider5);
    }

    public static Set timerService(Optional optional, Optional optional2, Optional optional3, Provider provider, Provider provider2) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesTimerDaggerModule.timerService(optional, optional2, optional3, provider, provider2));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return timerService((Optional) this.userConfigProvider.get(), (Optional) this.traceUserConfigProvider.get(), (Optional) this.traceMetricServiceProvider.get(), this.implProvider, this.withTracingImplProvider);
    }
}
