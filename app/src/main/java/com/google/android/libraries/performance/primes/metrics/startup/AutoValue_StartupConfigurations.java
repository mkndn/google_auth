package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_StartupConfigurations extends StartupConfigurations {
    private final Optional customTimestampProvider;
    private final MetricEnablement enablement;
    private final Optional metricExtensionProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends StartupConfigurations.Builder {
        private MetricEnablement enablement;
        private Optional metricExtensionProvider = Optional.absent();
        private Optional customTimestampProvider = Optional.absent();

        @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations.Builder
        public StartupConfigurations build() {
            if (this.enablement == null) {
                throw new IllegalStateException("Missing required properties: enablement");
            }
            return new AutoValue_StartupConfigurations(this.enablement, this.metricExtensionProvider, this.customTimestampProvider);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StartupConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }
    }

    private AutoValue_StartupConfigurations(MetricEnablement metricEnablement, Optional optional, Optional optional2) {
        this.enablement = metricEnablement;
        this.metricExtensionProvider = optional;
        this.customTimestampProvider = optional2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StartupConfigurations) {
            StartupConfigurations startupConfigurations = (StartupConfigurations) obj;
            return this.enablement.equals(startupConfigurations.getEnablement()) && this.metricExtensionProvider.equals(startupConfigurations.getMetricExtensionProvider()) && this.customTimestampProvider.equals(startupConfigurations.getCustomTimestampProvider());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations
    Optional getCustomTimestampProvider() {
        return this.customTimestampProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations
    Optional getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        String valueOf2 = String.valueOf(this.metricExtensionProvider);
        return "StartupConfigurations{enablement=" + valueOf + ", metricExtensionProvider=" + valueOf2 + ", customTimestampProvider=" + String.valueOf(this.customTimestampProvider) + "}";
    }

    public int hashCode() {
        return ((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.metricExtensionProvider.hashCode()) * 1000003) ^ this.customTimestampProvider.hashCode();
    }
}
