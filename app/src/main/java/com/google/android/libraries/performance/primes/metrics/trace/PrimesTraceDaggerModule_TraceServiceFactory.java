package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceDaggerModule_TraceServiceFactory implements Factory {
    private final Provider metricServiceImplProvider;
    private final Provider userTikTokTraceConfigProvider;
    private final Provider userTraceConfigProvider;

    public PrimesTraceDaggerModule_TraceServiceFactory(Provider provider, Provider provider2, Provider provider3) {
        this.userTraceConfigProvider = provider;
        this.userTikTokTraceConfigProvider = provider2;
        this.metricServiceImplProvider = provider3;
    }

    public static PrimesTraceDaggerModule_TraceServiceFactory create(Provider provider, Provider provider2, Provider provider3) {
        return new PrimesTraceDaggerModule_TraceServiceFactory(provider, provider2, provider3);
    }

    public static Set traceService(Optional optional, Optional optional2, Provider provider) {
        return (Set) Preconditions.checkNotNullFromProvides(PrimesTraceDaggerModule.traceService(optional, optional2, provider));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return traceService((Optional) this.userTraceConfigProvider.get(), (Optional) this.userTikTokTraceConfigProvider.get(), this.metricServiceImplProvider);
    }
}
