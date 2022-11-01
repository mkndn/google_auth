package com.google.android.libraries.performance.primes.metrics.storage;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.storage.AutoValue_StorageConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class StorageConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract StorageConfigurations build();

        abstract Builder setDirStatsConfigurations(Optional optional);

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    public static final Builder newBuilder() {
        return new AutoValue_StorageConfigurations.Builder().setManualCapture(false).setDirStatsConfigurations(Optional.absent()).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract Optional getDirStatsConfigurations();

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }

    public abstract boolean isManualCapture();
}
