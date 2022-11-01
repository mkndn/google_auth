package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory create(Provider provider) {
        return new PhenotypeFlagsModule_RegisterFrameMetricsListenerInOnCreateFactory(provider);
    }

    public static boolean registerFrameMetricsListenerInOnCreate(Context context) {
        return PhenotypeFlagsModule.registerFrameMetricsListenerInOnCreate(context);
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(registerFrameMetricsListenerInOnCreate((Context) this.contextProvider.get()));
    }
}
