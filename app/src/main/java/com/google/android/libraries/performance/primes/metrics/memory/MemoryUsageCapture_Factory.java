package com.google.android.libraries.performance.primes.metrics.memory;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryUsageCapture_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider configsProvider;

    public MemoryUsageCapture_Factory(Provider provider, Provider provider2) {
        this.configsProvider = provider;
        this.applicationProvider = provider2;
    }

    public static MemoryUsageCapture_Factory create(Provider provider, Provider provider2) {
        return new MemoryUsageCapture_Factory(provider, provider2);
    }

    public static MemoryUsageCapture newInstance(Provider provider, Context context) {
        return new MemoryUsageCapture(provider, context);
    }

    @Override // javax.inject.Provider
    public MemoryUsageCapture get() {
        return newInstance(this.configsProvider, (Context) this.applicationProvider.get());
    }
}
