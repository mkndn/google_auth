package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_TikTokTraceConfigurations extends TikTokTraceConfigurations {
    private final TikTokTraceConfigurations.DynamicSampler dynamicSampler;
    private final MetricEnablement enablement;
    private final int rateLimitPerSecond;
    private final boolean recordTimerDuration;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends TikTokTraceConfigurations.Builder {
        private TikTokTraceConfigurations.DynamicSampler dynamicSampler;
        private MetricEnablement enablement;
        private int rateLimitPerSecond;
        private boolean recordTimerDuration;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations.Builder
        public TikTokTraceConfigurations build() {
            if (this.set$0 == 3 && this.enablement != null && this.dynamicSampler != null) {
                return new AutoValue_TikTokTraceConfigurations(this.enablement, this.rateLimitPerSecond, this.dynamicSampler, this.recordTimerDuration);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            if (this.dynamicSampler == null) {
                sb.append(" dynamicSampler");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" recordTimerDuration");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations.Builder
        public TikTokTraceConfigurations.Builder setDynamicSampler(TikTokTraceConfigurations.DynamicSampler dynamicSampler) {
            if (dynamicSampler == null) {
                throw new NullPointerException("Null dynamicSampler");
            }
            this.dynamicSampler = dynamicSampler;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations.Builder
        TikTokTraceConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public TikTokTraceConfigurations.Builder setRateLimitPerSecond(int i) {
            this.rateLimitPerSecond = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations.Builder
        public TikTokTraceConfigurations.Builder setRecordTimerDuration(boolean z) {
            this.recordTimerDuration = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }
    }

    private AutoValue_TikTokTraceConfigurations(MetricEnablement metricEnablement, int i, TikTokTraceConfigurations.DynamicSampler dynamicSampler, boolean z) {
        this.enablement = metricEnablement;
        this.rateLimitPerSecond = i;
        this.dynamicSampler = dynamicSampler;
        this.recordTimerDuration = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TikTokTraceConfigurations) {
            TikTokTraceConfigurations tikTokTraceConfigurations = (TikTokTraceConfigurations) obj;
            return this.enablement.equals(tikTokTraceConfigurations.getEnablement()) && this.rateLimitPerSecond == tikTokTraceConfigurations.getRateLimitPerSecond() && this.dynamicSampler.equals(tikTokTraceConfigurations.getDynamicSampler()) && this.recordTimerDuration == tikTokTraceConfigurations.isRecordTimerDuration();
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations
    public TikTokTraceConfigurations.DynamicSampler getDynamicSampler() {
        return this.dynamicSampler;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations, com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations
    boolean isRecordTimerDuration() {
        return this.recordTimerDuration;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        int i = this.rateLimitPerSecond;
        String valueOf2 = String.valueOf(this.dynamicSampler);
        return "TikTokTraceConfigurations{enablement=" + valueOf + ", rateLimitPerSecond=" + i + ", dynamicSampler=" + valueOf2 + ", recordTimerDuration=" + this.recordTimerDuration + "}";
    }

    public int hashCode() {
        return ((((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ this.dynamicSampler.hashCode()) * 1000003) ^ (this.recordTimerDuration ? 1231 : 1237);
    }
}
