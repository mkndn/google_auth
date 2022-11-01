package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory create(Provider provider) {
        return new PhenotypeFlagsModule_EnableStartupBaselineDiscardingFactory(provider);
    }

    public static boolean enableStartupBaselineDiscarding(Context context) {
        return PhenotypeFlagsModule.enableStartupBaselineDiscarding(context);
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(enableStartupBaselineDiscarding((Context) this.contextProvider.get()));
    }
}
