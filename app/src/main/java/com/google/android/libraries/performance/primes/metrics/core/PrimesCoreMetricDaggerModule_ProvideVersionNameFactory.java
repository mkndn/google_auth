package com.google.android.libraries.performance.primes.metrics.core;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesCoreMetricDaggerModule_ProvideVersionNameFactory implements Factory {
    private final Provider applicationProvider;

    public PrimesCoreMetricDaggerModule_ProvideVersionNameFactory(Provider provider) {
        this.applicationProvider = provider;
    }

    public static PrimesCoreMetricDaggerModule_ProvideVersionNameFactory create(Provider provider) {
        return new PrimesCoreMetricDaggerModule_ProvideVersionNameFactory(provider);
    }

    public static String provideVersionName(Context context) {
        return PrimesCoreMetricDaggerModule.provideVersionName(context);
    }

    @Override // javax.inject.Provider
    public String get() {
        return provideVersionName((Context) this.applicationProvider.get());
    }
}
