package com.google.android.libraries.performance.primes.sampling;

import java.util.Random;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProbabilitySamplerFactory {
    private final Provider randomProvider;

    @Inject
    public ProbabilitySamplerFactory(Provider provider) {
        this.randomProvider = (Provider) checkNotNull(provider, 1);
    }

    private static Object checkNotNull(Object obj, int i) {
        if (obj == null) {
            throw new NullPointerException("@AutoFactory method argument is null but is not marked @Nullable. Argument index: " + i);
        }
        return obj;
    }

    public ProbabilitySampler create(float f) {
        return new ProbabilitySampler((Random) checkNotNull((Random) this.randomProvider.get(), 1), f);
    }
}
