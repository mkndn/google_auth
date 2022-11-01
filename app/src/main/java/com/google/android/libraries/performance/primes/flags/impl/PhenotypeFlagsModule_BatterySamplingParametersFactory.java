package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_BatterySamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_BatterySamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static SystemHealthProto$SamplingParameters batterySamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.batterySamplingParameters(context));
    }

    public static PhenotypeFlagsModule_BatterySamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_BatterySamplingParametersFactory(provider);
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return batterySamplingParameters((Context) this.contextProvider.get());
    }
}
