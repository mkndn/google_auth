package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.timer.AutoValue_TimerConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TimerConfigurations implements MetricConfigurations {
    public static final float DEFAULT_SAMPLING_PROBABILITY = 1.0f;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract TimerConfigurations autoBuild();

        public final TimerConfigurations build() {
            TimerConfigurations autoBuild = autoBuild();
            boolean z = true;
            Preconditions.checkState(autoBuild.getRateLimitPerSecond() >= 0, "Rate limit per second must be >= 0");
            Preconditions.checkState((autoBuild.getSamplingProbability() <= 0.0f || autoBuild.getSamplingProbability() > 1.0f) ? false : false, "Sampling Probability shall be > 0 and <= 1");
            return autoBuild;
        }

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);

        abstract Builder setPerEventConfigurationFlags(Optional optional);

        public abstract Builder setSamplingProbability(float f);
    }

    public static final Builder newBuilder() {
        return new AutoValue_TimerConfigurations.Builder().setRateLimitPerSecond(10).setSamplingProbability(1.0f).setPerEventConfigurationFlags(Optional.absent()).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Optional getPerEventConfigurationFlags();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public abstract int getRateLimitPerSecond();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getSamplingProbability();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
