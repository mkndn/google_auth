package com.google.android.libraries.performance.primes;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LegacyPrimesApiModule_ProvidePrimesApiFactory implements Factory {
    public static PrimesApi providePrimesApi(Provider provider) {
        return (PrimesApi) Preconditions.checkNotNullFromProvides(LegacyPrimesApiModule.providePrimesApi(provider));
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        throw null;
    }
}
