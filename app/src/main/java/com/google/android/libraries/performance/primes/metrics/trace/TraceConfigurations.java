package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.trace.AutoValue_TraceConfigurations;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TraceConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract TraceConfigurations autoBuild();

        public final TraceConfigurations build() {
            TraceConfigurations autoBuild = autoBuild();
            Preconditions.checkState(autoBuild.getSamplingProbability() >= 0.0f && autoBuild.getSamplingProbability() <= 1.0f, "Probability shall be between 0 and 1.");
            return autoBuild;
        }

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    public static final Builder newBuilder() {
        return new AutoValue_TraceConfigurations.Builder().setSamplingProbability(0.5f).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    public abstract float getSamplingProbability();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
