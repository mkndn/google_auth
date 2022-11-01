package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_MemoryConfigurations extends MemoryConfigurations {
    private final boolean captureDebugMetrics;
    private final boolean captureMemoryInfo;
    private final MetricEnablement enablement;
    private final boolean forceGcBeforeRecordMemory;
    private final Optional metricExtensionProvider;
    private final int rateLimitPerSecond;
    private final boolean recordMetricPerProcess;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends MemoryConfigurations.Builder {
        private boolean captureDebugMetrics;
        private boolean captureMemoryInfo;
        private MetricEnablement enablement;
        private boolean forceGcBeforeRecordMemory;
        private Optional metricExtensionProvider = Optional.absent();
        private int rateLimitPerSecond;
        private boolean recordMetricPerProcess;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        public MemoryConfigurations build() {
            if (this.set$0 == 31 && this.enablement != null) {
                return new AutoValue_MemoryConfigurations(this.enablement, this.rateLimitPerSecond, this.recordMetricPerProcess, this.metricExtensionProvider, this.forceGcBeforeRecordMemory, this.captureDebugMetrics, this.captureMemoryInfo);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" rateLimitPerSecond");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" recordMetricPerProcess");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" forceGcBeforeRecordMemory");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" captureDebugMetrics");
            }
            if ((this.set$0 & Ascii.DLE) == 0) {
                sb.append(" captureMemoryInfo");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        public MemoryConfigurations.Builder setCaptureDebugMetrics(boolean z) {
            this.captureDebugMetrics = z;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        MemoryConfigurations.Builder setCaptureMemoryInfo(boolean z) {
            this.captureMemoryInfo = z;
            this.set$0 = (byte) (this.set$0 | Ascii.DLE);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        MemoryConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        public MemoryConfigurations.Builder setForceGcBeforeRecordMemory(boolean z) {
            this.forceGcBeforeRecordMemory = z;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        public MemoryConfigurations.Builder setMetricExtensionProvider(Optional optional) {
            if (optional == null) {
                throw new NullPointerException("Null metricExtensionProvider");
            }
            this.metricExtensionProvider = optional;
            return this;
        }

        public MemoryConfigurations.Builder setRateLimitPerSecond(int i) {
            this.rateLimitPerSecond = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations.Builder
        public MemoryConfigurations.Builder setRecordMetricPerProcess(boolean z) {
            this.recordMetricPerProcess = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }
    }

    private AutoValue_MemoryConfigurations(MetricEnablement metricEnablement, int i, boolean z, Optional optional, boolean z2, boolean z3, boolean z4) {
        this.enablement = metricEnablement;
        this.rateLimitPerSecond = i;
        this.recordMetricPerProcess = z;
        this.metricExtensionProvider = optional;
        this.forceGcBeforeRecordMemory = z2;
        this.captureDebugMetrics = z3;
        this.captureMemoryInfo = z4;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MemoryConfigurations) {
            MemoryConfigurations memoryConfigurations = (MemoryConfigurations) obj;
            return this.enablement.equals(memoryConfigurations.getEnablement()) && this.rateLimitPerSecond == memoryConfigurations.getRateLimitPerSecond() && this.recordMetricPerProcess == memoryConfigurations.getRecordMetricPerProcess() && this.metricExtensionProvider.equals(memoryConfigurations.getMetricExtensionProvider()) && this.forceGcBeforeRecordMemory == memoryConfigurations.getForceGcBeforeRecordMemory() && this.captureDebugMetrics == memoryConfigurations.getCaptureDebugMetrics() && this.captureMemoryInfo == memoryConfigurations.getCaptureMemoryInfo();
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    boolean getCaptureDebugMetrics() {
        return this.captureDebugMetrics;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    boolean getCaptureMemoryInfo() {
        return this.captureMemoryInfo;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    public boolean getForceGcBeforeRecordMemory() {
        return this.forceGcBeforeRecordMemory;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    Optional getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations, com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public int getRateLimitPerSecond() {
        return this.rateLimitPerSecond;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations
    boolean getRecordMetricPerProcess() {
        return this.recordMetricPerProcess;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        int i = this.rateLimitPerSecond;
        boolean z = this.recordMetricPerProcess;
        String valueOf2 = String.valueOf(this.metricExtensionProvider);
        boolean z2 = this.forceGcBeforeRecordMemory;
        boolean z3 = this.captureDebugMetrics;
        return "MemoryConfigurations{enablement=" + valueOf + ", rateLimitPerSecond=" + i + ", recordMetricPerProcess=" + z + ", metricExtensionProvider=" + valueOf2 + ", forceGcBeforeRecordMemory=" + z2 + ", captureDebugMetrics=" + z3 + ", captureMemoryInfo=" + this.captureMemoryInfo + "}";
    }

    public int hashCode() {
        return ((((((((((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.rateLimitPerSecond) * 1000003) ^ (this.recordMetricPerProcess ? 1231 : 1237)) * 1000003) ^ this.metricExtensionProvider.hashCode()) * 1000003) ^ (this.forceGcBeforeRecordMemory ? 1231 : 1237)) * 1000003) ^ (this.captureDebugMetrics ? 1231 : 1237)) * 1000003) ^ (this.captureMemoryInfo ? 1231 : 1237);
    }
}
