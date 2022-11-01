package com.google.android.libraries.performance.primes.metrics;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum MetricEnablement {
    DEFAULT,
    EXPLICITLY_DISABLED,
    EXPLICITLY_ENABLED;

    public static MetricEnablement forBoolean(boolean z) {
        return z ? EXPLICITLY_ENABLED : EXPLICITLY_DISABLED;
    }
}
