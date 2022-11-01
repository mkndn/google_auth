package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.memory.AutoValue_MemoryConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class MemoryConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract MemoryConfigurations build();

        public abstract Builder setCaptureDebugMetrics(boolean z);

        abstract Builder setCaptureMemoryInfo(boolean z);

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);

        public abstract Builder setForceGcBeforeRecordMemory(boolean z);

        public abstract Builder setMetricExtensionProvider(Optional optional);

        public abstract Builder setRecordMetricPerProcess(boolean z);
    }

    public static Builder newBuilder() {
        return new AutoValue_MemoryConfigurations.Builder().setRateLimitPerSecond(3).setRecordMetricPerProcess(false).setMetricExtensionProvider(Optional.absent()).setForceGcBeforeRecordMemory(false).setCaptureMemoryInfo(true).setCaptureDebugMetrics(false).setEnablement(MetricEnablement.DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getCaptureDebugMetrics();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getCaptureMemoryInfo();

    public abstract MetricEnablement getEnablement();

    public abstract boolean getForceGcBeforeRecordMemory();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Optional getMetricExtensionProvider();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public abstract int getRateLimitPerSecond();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getRecordMetricPerProcess();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
