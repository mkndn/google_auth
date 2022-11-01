package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_AppExitCollectionEnabledFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_AppExitCollectionEnabledFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static boolean appExitCollectionEnabled(Context context) {
        return PhenotypeFlagsModule.appExitCollectionEnabled(context);
    }

    public static PhenotypeFlagsModule_AppExitCollectionEnabledFactory create(Provider provider) {
        return new PhenotypeFlagsModule_AppExitCollectionEnabledFactory(provider);
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(appExitCollectionEnabled((Context) this.contextProvider.get()));
    }
}
