package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_TimerSamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_TimerSamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_TimerSamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_TimerSamplingParametersFactory(provider);
    }

    public static SystemHealthProto$SamplingParameters timerSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.timerSamplingParameters(context));
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return timerSamplingParameters((Context) this.contextProvider.get());
    }
}
