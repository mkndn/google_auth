package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.jank.AutoValue_JankConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class JankConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract JankConfigurations build();

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    public static final Builder newBuilder() {
        return new AutoValue_JankConfigurations.Builder().setRateLimitPerSecond(10).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public abstract int getRateLimitPerSecond();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
