package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.startup.AutoValue_StartupConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class StartupConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract StartupConfigurations build();
    }

    public static Builder newBuilder() {
        return new AutoValue_StartupConfigurations.Builder().setEnablement(MetricEnablement.DEFAULT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Optional getCustomTimestampProvider();

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Optional getMetricExtensionProvider();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        MetricEnablement enablement = getEnablement();
        return enablement == MetricEnablement.EXPLICITLY_ENABLED || enablement == MetricEnablement.DEFAULT;
    }
}
