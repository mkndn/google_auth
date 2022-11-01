package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.AutoValue_CpuProfilingConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CpuProfilingConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract CpuProfilingConfigurations build();

        abstract Builder setEnablement(MetricEnablement metricEnablement);

        public abstract Builder setSampleDurationMs(int i);

        public abstract Builder setSampleDurationSkewMs(int i);

        public abstract Builder setSampleFrequencyMicro(int i);

        public abstract Builder setSamplesPerEpoch(double d);
    }

    public static Builder newBuilder() {
        return new AutoValue_CpuProfilingConfigurations.Builder().setMaxBufferSizeBytes(2097152).setSampleDurationMs(30000).setSampleDurationSkewMs(5000).setSampleFrequencyMicro(1000).setSamplesPerEpoch(5.0d).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getMaxBufferSizeBytes();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getSampleDurationMs();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getSampleDurationSkewMs();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getSampleFrequencyMicro();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract double getSamplesPerEpoch();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
