package com.google.android.libraries.performance.primes;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LegacyPrimesApiModule_ProvidePrimesFactory implements Factory {
    public static Primes providePrimes(Object obj) {
        return (Primes) Preconditions.checkNotNullFromProvides(LegacyPrimesApiModule.providePrimes((PrimesApi) obj));
    }

    @Override // javax.inject.Provider
    public /* bridge */ /* synthetic */ Object get() {
        throw null;
    }
}
