package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.C$AutoValue_PrimesConfigurations;
import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public static /* synthetic */ Set lambda$setMetricTransmitterProvider$0(Provider provider) {
            return ImmutableSet.of((Object) ((MetricTransmitter) provider.get()));
        }

        public abstract PrimesConfigurations build();

        public abstract Builder setBatteryConfigurationsProvider(Provider provider);

        public abstract Builder setCrashConfigurationsProvider(Provider provider);

        public abstract Builder setMemoryConfigurationsProvider(Provider provider);

        public final Builder setMetricTransmitterProvider(final Provider provider) {
            return setMetricTransmittersProvider(new Provider() { // from class: com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0
                @Override // javax.inject.Provider
                public final Object get() {
                    return PrimesConfigurations.Builder.lambda$setMetricTransmitterProvider$0(Provider.this);
                }
            });
        }

        public abstract Builder setMetricTransmittersProvider(Provider provider);

        public abstract Builder setNetworkConfigurationsProvider(Provider provider);

        public abstract Builder setStorageConfigurationsProvider(Provider provider);

        public abstract Builder setTimerConfigurationsProvider(Provider provider);
    }

    public static Builder newBuilder() {
        return new C$AutoValue_PrimesConfigurations.Builder();
    }

    public abstract Optional applicationExitConfigurationsProvider();

    public abstract Optional batteryConfigurationsProvider();

    public abstract Optional cpuProfilingConfigurationsProvider();

    public abstract Optional crashConfigurationsProvider();

    public abstract Optional globalConfigurationsProvider();

    public abstract Optional jankConfigurationsProvider();

    public abstract Optional memoryConfigurationsProvider();

    public abstract Provider metricTransmittersProvider();

    public abstract Optional monitorAllActivitiesProvider();

    public abstract Optional networkConfigurationsProvider();

    public abstract Optional storageConfigurationsProvider();

    public abstract Optional tikTokTraceConfigurationsProvider();

    public abstract Optional timerConfigurationsProvider();

    public abstract Optional traceConfigurationsProvider();
}
