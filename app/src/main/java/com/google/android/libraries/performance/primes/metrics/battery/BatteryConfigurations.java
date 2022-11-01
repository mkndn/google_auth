package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.battery.AutoValue_BatteryConfigurations;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BatteryConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract BatteryConfigurations build();

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ExtensionMetric$MetricExtension lambda$newBuilder$0(String str, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo) {
        return null;
    }

    public static final Builder newBuilder() {
        return new AutoValue_BatteryConfigurations.Builder().setMetricExtensionProvider(BatteryConfigurations$$ExternalSyntheticLambda0.INSTANCE).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BatteryMetricExtensionProvider getMetricExtensionProvider();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
