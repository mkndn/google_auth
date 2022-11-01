package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApiImpl_Factory implements Factory {
    private final Provider batteryMetricServiceProvider;
    private final Provider contextProvider;
    private final Provider crashMetricServiceProvider;
    private final Provider crashOnBadPrimesConfigurationProvider;
    private final Provider customDurationMetricServiceProvider;
    private final Provider debugMemoryMetricServiceProvider;
    private final Provider executorServiceProvider;
    private final Provider jankMetricServiceProvider;
    private final Provider memoryDiffMetricServiceProvider;
    private final Provider memoryMetricServiceProvider;
    private final Provider memoryMetricServiceUnsafeStringProvider;
    private final Provider metricServicesProvider;
    private final Provider metricTransmittersProvider;
    private final Provider networkConfigurationsProvider;
    private final Provider networkMetricServiceProvider;
    private final Provider shutdownProvider;
    private final Provider startupMetricServiceProvider;
    private final Provider storageMetricServiceProvider;
    private final Provider timerMetricServiceProvider;
    private final Provider traceMetricServiceProvider;
    private final Provider unifiedInitProvider;

    public PrimesApiImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20, Provider provider21) {
        this.contextProvider = provider;
        this.executorServiceProvider = provider2;
        this.shutdownProvider = provider3;
        this.metricServicesProvider = provider4;
        this.metricTransmittersProvider = provider5;
        this.networkConfigurationsProvider = provider6;
        this.batteryMetricServiceProvider = provider7;
        this.crashMetricServiceProvider = provider8;
        this.jankMetricServiceProvider = provider9;
        this.memoryMetricServiceProvider = provider10;
        this.memoryMetricServiceUnsafeStringProvider = provider11;
        this.debugMemoryMetricServiceProvider = provider12;
        this.memoryDiffMetricServiceProvider = provider13;
        this.networkMetricServiceProvider = provider14;
        this.storageMetricServiceProvider = provider15;
        this.timerMetricServiceProvider = provider16;
        this.traceMetricServiceProvider = provider17;
        this.customDurationMetricServiceProvider = provider18;
        this.startupMetricServiceProvider = provider19;
        this.unifiedInitProvider = provider20;
        this.crashOnBadPrimesConfigurationProvider = provider21;
    }

    public static PrimesApiImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Provider provider18, Provider provider19, Provider provider20, Provider provider21) {
        return new PrimesApiImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21);
    }

    public static PrimesApiImpl newInstance(Context context, Provider provider, Shutdown shutdown, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Optional optional, CrashOnBadPrimesConfiguration crashOnBadPrimesConfiguration) {
        return new PrimesApiImpl(context, provider, shutdown, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, optional, crashOnBadPrimesConfiguration);
    }

    @Override // javax.inject.Provider
    public PrimesApiImpl get() {
        return newInstance((Context) this.contextProvider.get(), this.executorServiceProvider, (Shutdown) this.shutdownProvider.get(), this.metricServicesProvider, this.metricTransmittersProvider, this.networkConfigurationsProvider, this.batteryMetricServiceProvider, this.crashMetricServiceProvider, this.jankMetricServiceProvider, this.memoryMetricServiceProvider, this.memoryMetricServiceUnsafeStringProvider, this.debugMemoryMetricServiceProvider, this.memoryDiffMetricServiceProvider, this.networkMetricServiceProvider, this.storageMetricServiceProvider, this.timerMetricServiceProvider, this.traceMetricServiceProvider, this.customDurationMetricServiceProvider, this.startupMetricServiceProvider, (Optional) this.unifiedInitProvider.get(), (CrashOnBadPrimesConfiguration) this.crashOnBadPrimesConfigurationProvider.get());
    }
}
