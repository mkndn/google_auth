package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.memory.AutoValue_DebugMemoryConfigurations;
import com.google.common.collect.ImmutableSet;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DebugMemoryConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract DebugMemoryConfigurations build();

        public abstract Builder setDebugMemoryServiceThrottleMs(long j);

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    public static Builder newBuilder() {
        return new AutoValue_DebugMemoryConfigurations.Builder().setEnablement(MetricEnablement.DEFAULT).setDebugMemoryServiceThrottleMs(TimeUnit.MINUTES.toMillis(5L) + TimeUnit.SECONDS.toMillis(20L));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ImmutableSet getDebugMemoryEventsToSample();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract long getDebugMemoryServiceThrottleMs();

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
