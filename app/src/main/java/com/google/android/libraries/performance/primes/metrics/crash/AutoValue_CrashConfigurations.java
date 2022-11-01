package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_CrashConfigurations extends CrashConfigurations {
    private final MetricEnablement enablement;
    private final Provider metricExtensionProvider;
    private final float startupSamplePercentage;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends CrashConfigurations.Builder {
        private MetricEnablement enablement;
        private Provider metricExtensionProvider;
        private byte set$0;
        private float startupSamplePercentage;

        @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations.Builder
        CrashConfigurations autoBuild() {
            if (this.set$0 == 1 && this.enablement != null) {
                return new AutoValue_CrashConfigurations(this.enablement, this.startupSamplePercentage, this.metricExtensionProvider);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" startupSamplePercentage");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations.Builder
        CrashConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public CrashConfigurations.Builder setStartupSamplePercentage(float f) {
            this.startupSamplePercentage = f;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_CrashConfigurations(MetricEnablement metricEnablement, float f, Provider provider) {
        this.enablement = metricEnablement;
        this.startupSamplePercentage = f;
        this.metricExtensionProvider = provider;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashConfigurations) {
            CrashConfigurations crashConfigurations = (CrashConfigurations) obj;
            if (this.enablement.equals(crashConfigurations.getEnablement()) && Float.floatToIntBits(this.startupSamplePercentage) == Float.floatToIntBits(crashConfigurations.getStartupSamplePercentage())) {
                Provider provider = this.metricExtensionProvider;
                if (provider == null) {
                    if (crashConfigurations.getMetricExtensionProvider() == null) {
                        return true;
                    }
                } else if (provider.equals(crashConfigurations.getMetricExtensionProvider())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations
    Provider getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations
    float getStartupSamplePercentage() {
        return this.startupSamplePercentage;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        float f = this.startupSamplePercentage;
        return "CrashConfigurations{enablement=" + valueOf + ", startupSamplePercentage=" + f + ", metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + "}";
    }

    public int hashCode() {
        int hashCode = (((this.enablement.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.startupSamplePercentage)) * 1000003;
        Provider provider = this.metricExtensionProvider;
        return hashCode ^ (provider == null ? 0 : provider.hashCode());
    }
}
