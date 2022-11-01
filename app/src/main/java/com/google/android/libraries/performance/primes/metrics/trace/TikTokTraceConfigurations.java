package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.trace.AutoValue_TikTokTraceConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TikTokTraceConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract TikTokTraceConfigurations build();

        public abstract Builder setDynamicSampler(DynamicSampler dynamicSampler);

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);

        public abstract Builder setRecordTimerDuration(boolean z);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DynamicSampler {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UniformSampler implements DynamicSampler {
        private final float probability;

        public UniformSampler(float f) {
            this.probability = f;
        }
    }

    public static final Builder newBuilder() {
        return new AutoValue_TikTokTraceConfigurations.Builder().setRateLimitPerSecond(10).setRecordTimerDuration(true).setDynamicSampler(new UniformSampler(1.0f)).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract DynamicSampler getDynamicSampler();

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public abstract int getRateLimitPerSecond();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isRecordTimerDuration();
}
