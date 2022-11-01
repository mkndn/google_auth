package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.PrimesConfigurations;
import com.google.common.base.Optional;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_PrimesConfigurations extends C$AutoValue_PrimesConfigurations {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_PrimesConfigurations(final Provider provider, final Optional optional, final Optional optional2, final Optional optional3, final Optional optional4, final Optional optional5, final Optional optional6, final Optional optional7, final Optional optional8, final Optional optional9, final Optional optional10, final Optional optional11, final Optional optional12, final Optional optional13) {
        new PrimesConfigurations(provider, optional, optional2, optional3, optional4, optional5, optional6, optional7, optional8, optional9, optional10, optional11, optional12, optional13) { // from class: com.google.android.libraries.performance.primes.$AutoValue_PrimesConfigurations
            private final Optional applicationExitConfigurationsProvider;
            private final Optional batteryConfigurationsProvider;
            private final Optional cpuProfilingConfigurationsProvider;
            private final Optional crashConfigurationsProvider;
            private final Optional globalConfigurationsProvider;
            private final Optional jankConfigurationsProvider;
            private final Optional memoryConfigurationsProvider;
            private final Provider metricTransmittersProvider;
            private final Optional monitorAllActivitiesProvider;
            private final Optional networkConfigurationsProvider;
            private final Optional storageConfigurationsProvider;
            private final Optional tikTokTraceConfigurationsProvider;
            private final Optional timerConfigurationsProvider;
            private final Optional traceConfigurationsProvider;

            /* compiled from: PG */
            /* renamed from: com.google.android.libraries.performance.primes.$AutoValue_PrimesConfigurations$Builder */
            /* loaded from: classes.dex */
            class Builder extends PrimesConfigurations.Builder {
                private Provider metricTransmittersProvider;
                private Optional globalConfigurationsProvider = Optional.absent();
                private Optional memoryConfigurationsProvider = Optional.absent();
                private Optional timerConfigurationsProvider = Optional.absent();
                private Optional crashConfigurationsProvider = Optional.absent();
                private Optional applicationExitConfigurationsProvider = Optional.absent();
                private Optional networkConfigurationsProvider = Optional.absent();
                private Optional storageConfigurationsProvider = Optional.absent();
                private Optional jankConfigurationsProvider = Optional.absent();
                private Optional monitorAllActivitiesProvider = Optional.absent();
                private Optional tikTokTraceConfigurationsProvider = Optional.absent();
                private Optional traceConfigurationsProvider = Optional.absent();
                private Optional batteryConfigurationsProvider = Optional.absent();
                private Optional cpuProfilingConfigurationsProvider = Optional.absent();

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations build() {
                    if (this.metricTransmittersProvider == null) {
                        throw new IllegalStateException("Missing required properties: metricTransmittersProvider");
                    }
                    return new AutoValue_PrimesConfigurations(this.metricTransmittersProvider, this.globalConfigurationsProvider, this.memoryConfigurationsProvider, this.timerConfigurationsProvider, this.crashConfigurationsProvider, this.applicationExitConfigurationsProvider, this.networkConfigurationsProvider, this.storageConfigurationsProvider, this.jankConfigurationsProvider, this.monitorAllActivitiesProvider, this.tikTokTraceConfigurationsProvider, this.traceConfigurationsProvider, this.batteryConfigurationsProvider, this.cpuProfilingConfigurationsProvider);
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setBatteryConfigurationsProvider(Provider provider) {
                    this.batteryConfigurationsProvider = Optional.of(provider);
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setCrashConfigurationsProvider(Provider provider) {
                    this.crashConfigurationsProvider = Optional.of(provider);
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setMemoryConfigurationsProvider(Provider provider) {
                    this.memoryConfigurationsProvider = Optional.of(provider);
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setMetricTransmittersProvider(Provider provider) {
                    if (provider == null) {
                        throw new NullPointerException("Null metricTransmittersProvider");
                    }
                    this.metricTransmittersProvider = provider;
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setNetworkConfigurationsProvider(Provider provider) {
                    this.networkConfigurationsProvider = Optional.of(provider);
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setStorageConfigurationsProvider(Provider provider) {
                    this.storageConfigurationsProvider = Optional.of(provider);
                    return this;
                }

                @Override // com.google.android.libraries.performance.primes.PrimesConfigurations.Builder
                public PrimesConfigurations.Builder setTimerConfigurationsProvider(Provider provider) {
                    this.timerConfigurationsProvider = Optional.of(provider);
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (provider == null) {
                    throw new NullPointerException("Null metricTransmittersProvider");
                }
                this.metricTransmittersProvider = provider;
                if (optional == null) {
                    throw new NullPointerException("Null globalConfigurationsProvider");
                }
                this.globalConfigurationsProvider = optional;
                if (optional2 == null) {
                    throw new NullPointerException("Null memoryConfigurationsProvider");
                }
                this.memoryConfigurationsProvider = optional2;
                if (optional3 == null) {
                    throw new NullPointerException("Null timerConfigurationsProvider");
                }
                this.timerConfigurationsProvider = optional3;
                if (optional4 == null) {
                    throw new NullPointerException("Null crashConfigurationsProvider");
                }
                this.crashConfigurationsProvider = optional4;
                if (optional5 == null) {
                    throw new NullPointerException("Null applicationExitConfigurationsProvider");
                }
                this.applicationExitConfigurationsProvider = optional5;
                if (optional6 == null) {
                    throw new NullPointerException("Null networkConfigurationsProvider");
                }
                this.networkConfigurationsProvider = optional6;
                if (optional7 == null) {
                    throw new NullPointerException("Null storageConfigurationsProvider");
                }
                this.storageConfigurationsProvider = optional7;
                if (optional8 == null) {
                    throw new NullPointerException("Null jankConfigurationsProvider");
                }
                this.jankConfigurationsProvider = optional8;
                if (optional9 == null) {
                    throw new NullPointerException("Null monitorAllActivitiesProvider");
                }
                this.monitorAllActivitiesProvider = optional9;
                if (optional10 == null) {
                    throw new NullPointerException("Null tikTokTraceConfigurationsProvider");
                }
                this.tikTokTraceConfigurationsProvider = optional10;
                if (optional11 == null) {
                    throw new NullPointerException("Null traceConfigurationsProvider");
                }
                this.traceConfigurationsProvider = optional11;
                if (optional12 == null) {
                    throw new NullPointerException("Null batteryConfigurationsProvider");
                }
                this.batteryConfigurationsProvider = optional12;
                if (optional13 == null) {
                    throw new NullPointerException("Null cpuProfilingConfigurationsProvider");
                }
                this.cpuProfilingConfigurationsProvider = optional13;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional applicationExitConfigurationsProvider() {
                return this.applicationExitConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional batteryConfigurationsProvider() {
                return this.batteryConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional cpuProfilingConfigurationsProvider() {
                return this.cpuProfilingConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional crashConfigurationsProvider() {
                return this.crashConfigurationsProvider;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof PrimesConfigurations) {
                    PrimesConfigurations primesConfigurations = (PrimesConfigurations) obj;
                    return this.metricTransmittersProvider.equals(primesConfigurations.metricTransmittersProvider()) && this.globalConfigurationsProvider.equals(primesConfigurations.globalConfigurationsProvider()) && this.memoryConfigurationsProvider.equals(primesConfigurations.memoryConfigurationsProvider()) && this.timerConfigurationsProvider.equals(primesConfigurations.timerConfigurationsProvider()) && this.crashConfigurationsProvider.equals(primesConfigurations.crashConfigurationsProvider()) && this.applicationExitConfigurationsProvider.equals(primesConfigurations.applicationExitConfigurationsProvider()) && this.networkConfigurationsProvider.equals(primesConfigurations.networkConfigurationsProvider()) && this.storageConfigurationsProvider.equals(primesConfigurations.storageConfigurationsProvider()) && this.jankConfigurationsProvider.equals(primesConfigurations.jankConfigurationsProvider()) && this.monitorAllActivitiesProvider.equals(primesConfigurations.monitorAllActivitiesProvider()) && this.tikTokTraceConfigurationsProvider.equals(primesConfigurations.tikTokTraceConfigurationsProvider()) && this.traceConfigurationsProvider.equals(primesConfigurations.traceConfigurationsProvider()) && this.batteryConfigurationsProvider.equals(primesConfigurations.batteryConfigurationsProvider()) && this.cpuProfilingConfigurationsProvider.equals(primesConfigurations.cpuProfilingConfigurationsProvider());
                }
                return false;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional globalConfigurationsProvider() {
                return this.globalConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional jankConfigurationsProvider() {
                return this.jankConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional memoryConfigurationsProvider() {
                return this.memoryConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Provider metricTransmittersProvider() {
                return this.metricTransmittersProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            public Optional monitorAllActivitiesProvider() {
                return this.monitorAllActivitiesProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional networkConfigurationsProvider() {
                return this.networkConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional storageConfigurationsProvider() {
                return this.storageConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional tikTokTraceConfigurationsProvider() {
                return this.tikTokTraceConfigurationsProvider;
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional timerConfigurationsProvider() {
                return this.timerConfigurationsProvider;
            }

            public String toString() {
                String valueOf = String.valueOf(this.metricTransmittersProvider);
                String valueOf2 = String.valueOf(this.globalConfigurationsProvider);
                String valueOf3 = String.valueOf(this.memoryConfigurationsProvider);
                String valueOf4 = String.valueOf(this.timerConfigurationsProvider);
                String valueOf5 = String.valueOf(this.crashConfigurationsProvider);
                String valueOf6 = String.valueOf(this.applicationExitConfigurationsProvider);
                String valueOf7 = String.valueOf(this.networkConfigurationsProvider);
                String valueOf8 = String.valueOf(this.storageConfigurationsProvider);
                String valueOf9 = String.valueOf(this.jankConfigurationsProvider);
                String valueOf10 = String.valueOf(this.monitorAllActivitiesProvider);
                String valueOf11 = String.valueOf(this.tikTokTraceConfigurationsProvider);
                String valueOf12 = String.valueOf(this.traceConfigurationsProvider);
                String valueOf13 = String.valueOf(this.batteryConfigurationsProvider);
                return "PrimesConfigurations{metricTransmittersProvider=" + valueOf + ", globalConfigurationsProvider=" + valueOf2 + ", memoryConfigurationsProvider=" + valueOf3 + ", timerConfigurationsProvider=" + valueOf4 + ", crashConfigurationsProvider=" + valueOf5 + ", applicationExitConfigurationsProvider=" + valueOf6 + ", networkConfigurationsProvider=" + valueOf7 + ", storageConfigurationsProvider=" + valueOf8 + ", jankConfigurationsProvider=" + valueOf9 + ", monitorAllActivitiesProvider=" + valueOf10 + ", tikTokTraceConfigurationsProvider=" + valueOf11 + ", traceConfigurationsProvider=" + valueOf12 + ", batteryConfigurationsProvider=" + valueOf13 + ", cpuProfilingConfigurationsProvider=" + String.valueOf(this.cpuProfilingConfigurationsProvider) + "}";
            }

            @Override // com.google.android.libraries.performance.primes.PrimesConfigurations
            protected Optional traceConfigurationsProvider() {
                return this.traceConfigurationsProvider;
            }

            public int hashCode() {
                return ((((((((((((((((((((((((((this.metricTransmittersProvider.hashCode() ^ 1000003) * 1000003) ^ this.globalConfigurationsProvider.hashCode()) * 1000003) ^ this.memoryConfigurationsProvider.hashCode()) * 1000003) ^ this.timerConfigurationsProvider.hashCode()) * 1000003) ^ this.crashConfigurationsProvider.hashCode()) * 1000003) ^ this.applicationExitConfigurationsProvider.hashCode()) * 1000003) ^ this.networkConfigurationsProvider.hashCode()) * 1000003) ^ this.storageConfigurationsProvider.hashCode()) * 1000003) ^ this.jankConfigurationsProvider.hashCode()) * 1000003) ^ this.monitorAllActivitiesProvider.hashCode()) * 1000003) ^ this.tikTokTraceConfigurationsProvider.hashCode()) * 1000003) ^ this.traceConfigurationsProvider.hashCode()) * 1000003) ^ this.batteryConfigurationsProvider.hashCode()) * 1000003) ^ this.cpuProfilingConfigurationsProvider.hashCode();
            }
        };
    }
}
