package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_BatteryConfigurations extends BatteryConfigurations {
    private final MetricEnablement enablement;
    private final BatteryMetricExtensionProvider metricExtensionProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends BatteryConfigurations.Builder {
        private MetricEnablement enablement;
        private BatteryMetricExtensionProvider metricExtensionProvider;

        @Override // com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations.Builder
        public BatteryConfigurations build() {
            if (this.enablement != null && this.metricExtensionProvider != null) {
                return new AutoValue_BatteryConfigurations(this.enablement, this.metricExtensionProvider);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if (this.metricExtensionProvider == null) {
                sb.append(" metricExtensionProvider");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations.Builder
        BatteryConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public BatteryConfigurations.Builder setMetricExtensionProvider(BatteryMetricExtensionProvider batteryMetricExtensionProvider) {
            if (batteryMetricExtensionProvider == null) {
                throw new NullPointerException("Null metricExtensionProvider");
            }
            this.metricExtensionProvider = batteryMetricExtensionProvider;
            return this;
        }
    }

    private AutoValue_BatteryConfigurations(MetricEnablement metricEnablement, BatteryMetricExtensionProvider batteryMetricExtensionProvider) {
        this.enablement = metricEnablement;
        this.metricExtensionProvider = batteryMetricExtensionProvider;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BatteryConfigurations) {
            BatteryConfigurations batteryConfigurations = (BatteryConfigurations) obj;
            return this.enablement.equals(batteryConfigurations.getEnablement()) && this.metricExtensionProvider.equals(batteryConfigurations.getMetricExtensionProvider());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations
    BatteryMetricExtensionProvider getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        return "BatteryConfigurations{enablement=" + valueOf + ", metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + "}";
    }

    public int hashCode() {
        return ((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.metricExtensionProvider.hashCode();
    }
}
