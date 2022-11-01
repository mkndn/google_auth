package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeFlagsModule_JankPerfettoConfigurationsFactory implements Factory {
    private final Provider contextProvider;

    public PhenotypeFlagsModule_JankPerfettoConfigurationsFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public static PhenotypeFlagsModule_JankPerfettoConfigurationsFactory create(Provider provider) {
        return new PhenotypeFlagsModule_JankPerfettoConfigurationsFactory(provider);
    }

    public static PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context) {
        return (PerfettoTraceConfigurations$JankPerfettoConfigurations) Preconditions.checkNotNullFromProvides(PhenotypeFlagsModule.jankPerfettoConfigurations(context));
    }

    @Override // javax.inject.Provider
    public PerfettoTraceConfigurations$JankPerfettoConfigurations get() {
        return jankPerfettoConfigurations((Context) this.contextProvider.get());
    }
}
