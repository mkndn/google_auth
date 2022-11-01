package com.google.android.libraries.performance.primes.metrics.core;

import android.content.Context;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricStamper_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider globalConfigurationsProvider;
    private final Provider versionNameProvider;

    public MetricStamper_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.applicationProvider = provider;
        this.globalConfigurationsProvider = provider2;
        this.versionNameProvider = provider3;
    }

    public static MetricStamper_Factory create(Provider provider, Provider provider2, Provider provider3) {
        return new MetricStamper_Factory(provider, provider2, provider3);
    }

    public static MetricStamper newInstance(Context context, Optional optional, String str) {
        return new MetricStamper(context, optional, str);
    }

    @Override // javax.inject.Provider
    public MetricStamper get() {
        return newInstance((Context) this.applicationProvider.get(), (Optional) this.globalConfigurationsProvider.get(), (String) this.versionNameProvider.get());
    }
}
