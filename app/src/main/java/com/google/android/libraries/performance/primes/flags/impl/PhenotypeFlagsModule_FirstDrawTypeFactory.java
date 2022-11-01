package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_FirstDrawTypeFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_FirstDrawTypeFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_FirstDrawTypeFactory create(Provider provider) {
        return new PhenotypeFlagsModule_FirstDrawTypeFactory(provider);
    }

    public static long firstDrawType(Context context) {
        return PhenotypeFlagsModule.firstDrawType(context);
    }

    @Override // javax.inject.Provider
    public Long get() {
        return Long.valueOf(firstDrawType((Context) this.contextProvider.get()));
    }
}
