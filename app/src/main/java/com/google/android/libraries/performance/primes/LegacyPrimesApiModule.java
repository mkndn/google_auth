package com.google.android.libraries.performance.primes;

import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LegacyPrimesApiModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Primes providePrimes(PrimesApi primesApi) {
        return new Primes(primesApi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PrimesApi providePrimesApi(Provider provider) {
        return Primes.isPrimesSupported() ? (PrimesApi) provider.get() : new NoopPrimesApi();
    }
}
