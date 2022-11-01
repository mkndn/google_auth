package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_MemorySamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_MemorySamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_MemorySamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_MemorySamplingParametersFactory(provider);
    }

    public static SystemHealthProto$SamplingParameters memorySamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.memorySamplingParameters(context));
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return memorySamplingParameters((Context) this.contextProvider.get());
    }
}
