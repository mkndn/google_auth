package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory implements Factory {
    private final Provider allowEarlyPhenotypeProvider;
    private final Provider registerFrameMetricsListenerInOnCreateProvider;

    public PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory(Provider provider, Provider provider2) {
        this.allowEarlyPhenotypeProvider = provider;
        this.registerFrameMetricsListenerInOnCreateProvider = provider2;
    }

    public static PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory create(Provider provider, Provider provider2) {
        return new PrimesJankDaggerModule_RegisterFrameMetricsListenerInOnCreateFactory(provider, provider2);
    }

    public static boolean registerFrameMetricsListenerInOnCreate(Optional optional, Provider provider) {
        return PrimesJankDaggerModule.registerFrameMetricsListenerInOnCreate(optional, provider);
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(registerFrameMetricsListenerInOnCreate((Optional) this.allowEarlyPhenotypeProvider.get(), this.registerFrameMetricsListenerInOnCreateProvider));
    }
}
