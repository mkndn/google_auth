package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SamplerFactory {
    private final Provider contextProvider;
    private final Provider enableSamplingProvider;
    private final Provider executorProvider;
    private final Provider samplingStrategyFactoryProvider;

    @Inject
    public SamplerFactory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.contextProvider = (Provider) checkNotNull(provider, 1);
        this.executorProvider = (Provider) checkNotNull(provider2, 2);
        this.samplingStrategyFactoryProvider = (Provider) checkNotNull(provider3, 3);
        this.enableSamplingProvider = (Provider) checkNotNull(provider4, 4);
    }

    private static Object checkNotNull(Object obj, int i) {
        if (obj == null) {
            throw new NullPointerException("@AutoFactory method argument is null but is not marked @Nullable. Argument index: " + i);
        }
        return obj;
    }

    public Sampler create(Lazy lazy, Provider provider) {
        return new Sampler((Context) checkNotNull((Context) this.contextProvider.get(), 1), (Executor) checkNotNull((Executor) this.executorProvider.get(), 2), (SamplingStrategy.Factory) checkNotNull((SamplingStrategy.Factory) this.samplingStrategyFactoryProvider.get(), 3), (Lazy) checkNotNull(lazy, 4), ((Boolean) checkNotNull((Boolean) this.enableSamplingProvider.get(), 5)).booleanValue(), provider);
    }
}
