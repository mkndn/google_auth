package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.AutoValue_CrashConfigurations;
import com.google.common.base.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CrashConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract CrashConfigurations autoBuild();

        public final CrashConfigurations build() {
            CrashConfigurations autoBuild = autoBuild();
            float startupSamplePercentage = autoBuild.getStartupSamplePercentage();
            Preconditions.checkArgument(startupSamplePercentage > 0.0f && startupSamplePercentage <= 100.0f, "StartupSamplePercentage should be a floating number > 0 and <= 100.");
            return autoBuild;
        }

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    public static final Builder newBuilder() {
        return new AutoValue_CrashConfigurations.Builder().setStartupSamplePercentage(100.0f).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Provider getMetricExtensionProvider();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getStartupSamplePercentage();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        MetricEnablement enablement = getEnablement();
        return enablement == MetricEnablement.EXPLICITLY_ENABLED || enablement == MetricEnablement.DEFAULT;
    }
}
