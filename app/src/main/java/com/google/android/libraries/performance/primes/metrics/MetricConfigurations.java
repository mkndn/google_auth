package com.google.android.libraries.performance.primes.metrics;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MetricConfigurations {

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.MetricConfigurations$-CC  reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC {
        public static int $default$getRateLimitPerSecond(MetricConfigurations metricConfigurations) {
            return Integer.MAX_VALUE;
        }
    }

    int getRateLimitPerSecond();

    boolean isEnabled();
}
