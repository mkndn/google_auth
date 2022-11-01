package com.google.android.libraries.performance.primes.persistent;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentStorage_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider sharedPreferencesProvider;

    public PersistentStorage_Factory(Provider provider, Provider provider2) {
        this.applicationProvider = provider;
        this.sharedPreferencesProvider = provider2;
    }

    public static PersistentStorage_Factory create(Provider provider, Provider provider2) {
        return new PersistentStorage_Factory(provider, provider2);
    }

    public static PersistentStorage newInstance(Context context, Provider provider) {
        return new PersistentStorage(context, provider);
    }

    @Override // javax.inject.Provider
    public PersistentStorage get() {
        return newInstance((Context) this.applicationProvider.get(), this.sharedPreferencesProvider);
    }
}
