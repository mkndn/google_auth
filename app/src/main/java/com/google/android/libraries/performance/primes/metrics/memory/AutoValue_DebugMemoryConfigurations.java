package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.common.collect.ImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_DebugMemoryConfigurations extends DebugMemoryConfigurations {
    private final ImmutableSet debugMemoryEventsToSample;
    private final long debugMemoryServiceThrottleMs;
    private final MetricEnablement enablement;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends DebugMemoryConfigurations.Builder {
        private ImmutableSet debugMemoryEventsToSample;
        private ImmutableSet.Builder debugMemoryEventsToSampleBuilder$;
        private long debugMemoryServiceThrottleMs;
        private MetricEnablement enablement;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations.Builder
        public DebugMemoryConfigurations build() {
            ImmutableSet.Builder builder = this.debugMemoryEventsToSampleBuilder$;
            if (builder != null) {
                this.debugMemoryEventsToSample = builder.build();
            } else if (this.debugMemoryEventsToSample == null) {
                this.debugMemoryEventsToSample = ImmutableSet.of();
            }
            if (this.set$0 == 1 && this.enablement != null) {
                return new AutoValue_DebugMemoryConfigurations(this.enablement, this.debugMemoryServiceThrottleMs, this.debugMemoryEventsToSample);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" debugMemoryServiceThrottleMs");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations.Builder
        public DebugMemoryConfigurations.Builder setDebugMemoryServiceThrottleMs(long j) {
            this.debugMemoryServiceThrottleMs = j;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations.Builder
        public DebugMemoryConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }
    }

    private AutoValue_DebugMemoryConfigurations(MetricEnablement metricEnablement, long j, ImmutableSet immutableSet) {
        this.enablement = metricEnablement;
        this.debugMemoryServiceThrottleMs = j;
        this.debugMemoryEventsToSample = immutableSet;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DebugMemoryConfigurations) {
            DebugMemoryConfigurations debugMemoryConfigurations = (DebugMemoryConfigurations) obj;
            return this.enablement.equals(debugMemoryConfigurations.getEnablement()) && this.debugMemoryServiceThrottleMs == debugMemoryConfigurations.getDebugMemoryServiceThrottleMs() && this.debugMemoryEventsToSample.equals(debugMemoryConfigurations.getDebugMemoryEventsToSample());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations
    public ImmutableSet getDebugMemoryEventsToSample() {
        return this.debugMemoryEventsToSample;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations
    public long getDebugMemoryServiceThrottleMs() {
        return this.debugMemoryServiceThrottleMs;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        long j = this.debugMemoryServiceThrottleMs;
        return "DebugMemoryConfigurations{enablement=" + valueOf + ", debugMemoryServiceThrottleMs=" + j + ", debugMemoryEventsToSample=" + String.valueOf(this.debugMemoryEventsToSample) + "}";
    }

    public int hashCode() {
        long j = this.debugMemoryServiceThrottleMs;
        return ((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.debugMemoryEventsToSample.hashCode();
    }
}
