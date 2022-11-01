package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_JankConfigurations extends JankConfigurations {
    private final MetricEnablement enablement;
    private final int rateLimitPerSecond;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends JankConfigurations.Builder {
        private MetricEnablement enablement;
        private int rateLimitPerSecond;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations.Builder
        public JankConfigurations build() {
            if (this.set$0 == 1 && this.enablement != null) {
                return new AutoValue_JankConfigurations(this.enablement, this.rateLimitPerSecond);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations.Builder
        JankConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public JankConfigurations.Builder setRateLimitPerSecond(int i) {
            this.rateLimitPerSecond = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_JankConfigurations(MetricEnablement metricEnablement, int i) {
        this.enablement = metricEnablement;
        this.rateLimitPerSecond = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof JankConfigurations) {
            JankConfigurations jankConfigurations = (JankConfigurations) obj;
            return this.enablement.equals(jankConfigurations.getEnablement()) && this.rateLimitPerSecond == jankConfigurations.getRateLimitPerSecond();
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations, com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        return "JankConfigurations{enablement=" + valueOf + ", rateLimitPerSecond=" + this.rateLimitPerSecond + "}";
    }

    public int hashCode() {
        return ((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.rateLimitPerSecond;
    }
}
