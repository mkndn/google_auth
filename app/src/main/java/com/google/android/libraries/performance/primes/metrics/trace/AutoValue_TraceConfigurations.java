package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_TraceConfigurations extends TraceConfigurations {
    private final MetricEnablement enablement;
    private final float samplingProbability;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends TraceConfigurations.Builder {
        private MetricEnablement enablement;
        private float samplingProbability;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations.Builder
        TraceConfigurations autoBuild() {
            if (this.set$0 == 1 && this.enablement != null) {
                return new AutoValue_TraceConfigurations(this.enablement, this.samplingProbability);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" samplingProbability");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations.Builder
        TraceConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public TraceConfigurations.Builder setSamplingProbability(float f) {
            this.samplingProbability = f;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_TraceConfigurations(MetricEnablement metricEnablement, float f) {
        this.enablement = metricEnablement;
        this.samplingProbability = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TraceConfigurations) {
            TraceConfigurations traceConfigurations = (TraceConfigurations) obj;
            return this.enablement.equals(traceConfigurations.getEnablement()) && Float.floatToIntBits(this.samplingProbability) == Float.floatToIntBits(traceConfigurations.getSamplingProbability());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations
    public float getSamplingProbability() {
        return this.samplingProbability;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        return "TraceConfigurations{enablement=" + valueOf + ", samplingProbability=" + this.samplingProbability + "}";
    }

    public int hashCode() {
        return ((this.enablement.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.samplingProbability);
    }
}
