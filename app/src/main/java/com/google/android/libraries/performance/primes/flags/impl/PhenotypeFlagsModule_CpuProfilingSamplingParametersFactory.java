package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static SystemHealthProto$SamplingParameters cpuProfilingSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.cpuProfilingSamplingParameters(context));
    }

    public static PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_CpuProfilingSamplingParametersFactory(provider);
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return cpuProfilingSamplingParameters((Context) this.contextProvider.get());
    }
}
