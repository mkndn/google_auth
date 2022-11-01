package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.InternalComponent;
import com.google.android.libraries.performance.primes.Primes;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApiProviderBuilder {
    private final InternalComponent.Builder componentBuilder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PrimesApiProviderBuilder(Application application, InternalComponent.Builder builder) {
        this.componentBuilder = builder.setApplicationContext(application).setSharedPreferencesSupplier(Optional.absent()).setThreadsConfigurations(Optional.absent());
    }

    public Primes.PrimesProvider build() {
        return this.componentBuilder.build();
    }

    public PrimesApiProviderBuilder setApplicationExitConfigurationsProvider(Optional optional) {
        this.componentBuilder.setApplicationExitConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setBatteryConfigurationsProvider(Optional optional) {
        this.componentBuilder.setBatteryConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setConfigurationsProvider(Provider provider) {
        PrimesConfigurations primesConfigurations = (PrimesConfigurations) provider.get();
        return setMetricTransmittersProvider(primesConfigurations.metricTransmittersProvider()).setMemoryConfigurationsProvider(primesConfigurations.memoryConfigurationsProvider()).setDebugMemoryConfigurationsProvider(Optional.absent()).setGlobalConfigurationsProvider(primesConfigurations.globalConfigurationsProvider()).setTimerConfigurationsProvider(primesConfigurations.timerConfigurationsProvider()).setCrashConfigurationsProvider(primesConfigurations.crashConfigurationsProvider()).setApplicationExitConfigurationsProvider(primesConfigurations.applicationExitConfigurationsProvider()).setNetworkConfigurationsProvider(primesConfigurations.networkConfigurationsProvider()).setStorageConfigurationsProvider(primesConfigurations.storageConfigurationsProvider()).setJankConfigurationsProvider(primesConfigurations.jankConfigurationsProvider()).setTikTokTraceConfigurationsProvider(primesConfigurations.tikTokTraceConfigurationsProvider()).setTraceConfigurationsProvider(primesConfigurations.traceConfigurationsProvider()).setBatteryConfigurationsProvider(primesConfigurations.batteryConfigurationsProvider()).setCpuProfilingConfigurationsProvider(primesConfigurations.cpuProfilingConfigurationsProvider()).setMonitorAllActivitiesProvider(primesConfigurations.monitorAllActivitiesProvider());
    }

    public PrimesApiProviderBuilder setCpuProfilingConfigurationsProvider(Optional optional) {
        this.componentBuilder.setCpuProfilingConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setCrashConfigurationsProvider(Optional optional) {
        this.componentBuilder.setCrashConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setDebugMemoryConfigurationsProvider(Optional optional) {
        this.componentBuilder.setDebugMemoryConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setGlobalConfigurationsProvider(Optional optional) {
        this.componentBuilder.setGlobalConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setJankConfigurationsProvider(Optional optional) {
        this.componentBuilder.setJankConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setMemoryConfigurationsProvider(Optional optional) {
        this.componentBuilder.setMemoryConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setMetricTransmittersProvider(final Provider provider) {
        InternalComponent.Builder builder = this.componentBuilder;
        provider.getClass();
        builder.setMetricTransmittersSupplier(new Supplier() { // from class: com.google.android.libraries.performance.primes.PrimesApiProviderBuilder$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return (Set) Provider.this.get();
            }
        });
        return this;
    }

    public PrimesApiProviderBuilder setMonitorAllActivitiesProvider(Optional optional) {
        this.componentBuilder.setMonitorAllActivitiesProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setNetworkConfigurationsProvider(Optional optional) {
        this.componentBuilder.setNetworkConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setStorageConfigurationsProvider(Optional optional) {
        this.componentBuilder.setStorageConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setThreadsConfigurations(PrimesThreadsConfigurations primesThreadsConfigurations) {
        this.componentBuilder.setThreadsConfigurations(Optional.of(primesThreadsConfigurations));
        return this;
    }

    public PrimesApiProviderBuilder setTikTokTraceConfigurationsProvider(Optional optional) {
        this.componentBuilder.setTikTokTraceConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setTimerConfigurationsProvider(Optional optional) {
        this.componentBuilder.setTimerConfigurationsProvider(optional);
        return this;
    }

    public PrimesApiProviderBuilder setTraceConfigurationsProvider(Optional optional) {
        this.componentBuilder.setTraceConfigurationsProvider(optional);
        return this;
    }
}
