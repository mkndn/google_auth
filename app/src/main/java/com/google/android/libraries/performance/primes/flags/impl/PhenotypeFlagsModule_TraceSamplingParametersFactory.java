package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_TraceSamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_TraceSamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_TraceSamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_TraceSamplingParametersFactory(provider);
    }

    public static SystemHealthProto$SamplingParameters traceSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.traceSamplingParameters(context));
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return traceSamplingParameters((Context) this.contextProvider.get());
    }
}
