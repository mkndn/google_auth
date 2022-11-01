package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_JankSamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_JankSamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_JankSamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_JankSamplingParametersFactory(provider);
    }

    public static SystemHealthProto$SamplingParameters jankSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.jankSamplingParameters(context));
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return jankSamplingParameters((Context) this.contextProvider.get());
    }
}
