package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_TimerConfigurations extends TimerConfigurations {
    private final MetricEnablement enablement;
    private final Optional perEventConfigurationFlags;
    private final int rateLimitPerSecond;
    private final float samplingProbability;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends TimerConfigurations.Builder {
        private MetricEnablement enablement;
        private Optional perEventConfigurationFlags = Optional.absent();
        private int rateLimitPerSecond;
        private float samplingProbability;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations.Builder
        TimerConfigurations autoBuild() {
            if (this.set$0 == 3 && this.enablement != null) {
                return new AutoValue_TimerConfigurations(this.enablement, this.rateLimitPerSecond, this.samplingProbability, this.perEventConfigurationFlags);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" samplingProbability");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations.Builder
        TimerConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations.Builder
        TimerConfigurations.Builder setPerEventConfigurationFlags(Optional optional) {
            if (optional == null) {
                throw new NullPointerException("Null perEventConfigurationFlags");
            }
            this.perEventConfigurationFlags = optional;
            return this;
        }

        public TimerConfigurations.Builder setRateLimitPerSecond(int i) {
            this.rateLimitPerSecond = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations.Builder
        public TimerConfigurations.Builder setSamplingProbability(float f) {
            this.samplingProbability = f;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }
    }

    private AutoValue_TimerConfigurations(MetricEnablement metricEnablement, int i, float f, Optional optional) {
        this.enablement = metricEnablement;
        this.rateLimitPerSecond = i;
        this.samplingProbability = f;
        this.perEventConfigurationFlags = optional;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TimerConfigurations) {
            TimerConfigurations timerConfigurations = (TimerConfigurations) obj;
            return this.enablement.equals(timerConfigurations.getEnablement()) && this.rateLimitPerSecond == timerConfigurations.getRateLimitPerSecond() && Float.floatToIntBits(this.samplingProbability) == Float.floatToIntBits(timerConfigurations.getSamplingProbability()) && this.perEventConfigurationFlags.equals(timerConfigurations.getPerEventConfigurationFlags());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations
    Optional getPerEventConfigurationFlags() {
        return this.perEventConfigurationFlags;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations, com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations
    float getSamplingProbability() {
        return this.samplingProbability;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        int i = this.rateLimitPerSecond;
        float f = this.samplingProbability;
        return "TimerConfigurations{enablement=" + valueOf + ", rateLimitPerSecond=" + i + ", samplingProbability=" + f + ", perEventConfigurationFlags=" + String.valueOf(this.perEventConfigurationFlags) + "}";
    }

    public int hashCode() {
        return ((((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ Float.floatToIntBits(this.samplingProbability)) * 1000003) ^ this.perEventConfigurationFlags.hashCode();
    }
}
