package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_StorageSamplingParametersFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_StorageSamplingParametersFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_StorageSamplingParametersFactory create(Provider provider) {
        return new PhenotypeFlagsModule_StorageSamplingParametersFactory(provider);
    }

    public static SystemHealthProto$SamplingParameters storageSamplingParameters(Context context) {
        return (SystemHealthProto$SamplingParameters) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.storageSamplingParameters(context));
    }

    @Override // javax.inject.Provider
    public SystemHealthProto$SamplingParameters get() {
        return storageSamplingParameters((Context) this.contextProvider.get());
    }
}
