package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import com.google.common.base.Ascii;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_CpuProfilingConfigurations extends CpuProfilingConfigurations {
    private final MetricEnablement enablement;
    private final int maxBufferSizeBytes;
    private final int sampleDurationMs;
    private final int sampleDurationSkewMs;
    private final int sampleFrequencyMicro;
    private final double samplesPerEpoch;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends CpuProfilingConfigurations.Builder {
        private MetricEnablement enablement;
        private int maxBufferSizeBytes;
        private int sampleDurationMs;
        private int sampleDurationSkewMs;
        private int sampleFrequencyMicro;
        private double samplesPerEpoch;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        public CpuProfilingConfigurations build() {
            if (this.set$0 == 31 && this.enablement != null) {
                return new AutoValue_CpuProfilingConfigurations(this.enablement, this.maxBufferSizeBytes, this.sampleDurationMs, this.sampleDurationSkewMs, this.sampleFrequencyMicro, this.samplesPerEpoch);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" maxBufferSizeBytes");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" sampleDurationMs");
            }
            if ((this.set$0 & 4) == 0) {
                sb.append(" sampleDurationSkewMs");
            }
            if ((this.set$0 & 8) == 0) {
                sb.append(" sampleFrequencyMicro");
            }
            if ((this.set$0 & Ascii.DLE) == 0) {
                sb.append(" samplesPerEpoch");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        CpuProfilingConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public CpuProfilingConfigurations.Builder setMaxBufferSizeBytes(int i) {
            this.maxBufferSizeBytes = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        public CpuProfilingConfigurations.Builder setSampleDurationMs(int i) {
            this.sampleDurationMs = i;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        public CpuProfilingConfigurations.Builder setSampleDurationSkewMs(int i) {
            this.sampleDurationSkewMs = i;
            this.set$0 = (byte) (this.set$0 | 4);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        public CpuProfilingConfigurations.Builder setSampleFrequencyMicro(int i) {
            this.sampleFrequencyMicro = i;
            this.set$0 = (byte) (this.set$0 | 8);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations.Builder
        public CpuProfilingConfigurations.Builder setSamplesPerEpoch(double d) {
            this.samplesPerEpoch = d;
            this.set$0 = (byte) (this.set$0 | Ascii.DLE);
            return this;
        }
    }

    private AutoValue_CpuProfilingConfigurations(MetricEnablement metricEnablement, int i, int i2, int i3, int i4, double d) {
        this.enablement = metricEnablement;
        this.maxBufferSizeBytes = i;
        this.sampleDurationMs = i2;
        this.sampleDurationSkewMs = i3;
        this.sampleFrequencyMicro = i4;
        this.samplesPerEpoch = d;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CpuProfilingConfigurations) {
            CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) obj;
            return this.enablement.equals(cpuProfilingConfigurations.getEnablement()) && this.maxBufferSizeBytes == cpuProfilingConfigurations.getMaxBufferSizeBytes() && this.sampleDurationMs == cpuProfilingConfigurations.getSampleDurationMs() && this.sampleDurationSkewMs == cpuProfilingConfigurations.getSampleDurationSkewMs() && this.sampleFrequencyMicro == cpuProfilingConfigurations.getSampleFrequencyMicro() && Double.doubleToLongBits(this.samplesPerEpoch) == Double.doubleToLongBits(cpuProfilingConfigurations.getSamplesPerEpoch());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public int getMaxBufferSizeBytes() {
        return this.maxBufferSizeBytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public int getSampleDurationMs() {
        return this.sampleDurationMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public int getSampleDurationSkewMs() {
        return this.sampleDurationSkewMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public int getSampleFrequencyMicro() {
        return this.sampleFrequencyMicro;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations
    public double getSamplesPerEpoch() {
        return this.samplesPerEpoch;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        int i = this.maxBufferSizeBytes;
        int i2 = this.sampleDurationMs;
        int i3 = this.sampleDurationSkewMs;
        int i4 = this.sampleFrequencyMicro;
        return "CpuProfilingConfigurations{enablement=" + valueOf + ", maxBufferSizeBytes=" + i + ", sampleDurationMs=" + i2 + ", sampleDurationSkewMs=" + i3 + ", sampleFrequencyMicro=" + i4 + ", samplesPerEpoch=" + this.samplesPerEpoch + "}";
    }

    public int hashCode() {
        return ((((((((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.maxBufferSizeBytes) * 1000003) ^ this.sampleDurationMs) * 1000003) ^ this.sampleDurationSkewMs) * 1000003) ^ this.sampleFrequencyMicro) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.samplesPerEpoch) >>> 32) ^ Double.doubleToLongBits(this.samplesPerEpoch)));
    }
}
