package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.AutoValue_ApplicationExitConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ApplicationExitConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract ApplicationExitConfigurations build();

        public abstract Builder setReportingProcessShortName(String str);
    }

    public static final Builder newBuilder() {
        return new AutoValue_ApplicationExitConfigurations.Builder().setEnablement(MetricEnablement.DEFAULT).setReportingProcessShortName("");
    }

    public abstract MetricEnablement getEnablement();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    public abstract String getReportingProcessShortName();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        if (getEnablement() != MetricEnablement.EXPLICITLY_ENABLED && getEnablement() != MetricEnablement.DEFAULT) {
            return false;
        }
        return true;
    }
}
