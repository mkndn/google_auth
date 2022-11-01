package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.metrics.core.AutoValue_Metric;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Metric {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        public abstract Metric build();

        public abstract Builder setAccountableComponentName(String str);

        public abstract Builder setCustomEventName(String str);

        public abstract Builder setDebugLogsTime(RecentLogs.TimestampCollection timestampCollection);

        public abstract Builder setIsEventNameConstant(boolean z);

        public abstract Builder setIsUnsampled(boolean z);

        public abstract Builder setMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric);

        public abstract Builder setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension);

        public abstract Builder setSampleRatePermille(Long l);
    }

    public static Builder newBuilder() {
        return new AutoValue_Metric.Builder().setIsEventNameConstant(false).setIsUnsampled(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getAccountableComponentName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCustomEventName();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract RecentLogs.TimestampCollection getDebugLogsTime();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getIsEventNameConstant();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getIsUnsampled();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SystemHealthProto$SystemHealthMetric getMetric();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ExtensionMetric$MetricExtension getMetricExtension();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Long getSampleRatePermille();
}
