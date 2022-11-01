package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_Metric extends Metric {
    private final String accountableComponentName;
    private final String customEventName;
    private final RecentLogs.TimestampCollection debugLogsTime;
    private final boolean isEventNameConstant;
    private final boolean isUnsampled;
    private final SystemHealthProto$SystemHealthMetric metric;
    private final ExtensionMetric$MetricExtension metricExtension;
    private final Long sampleRatePermille;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Builder extends Metric.Builder {
        private String accountableComponentName;
        private String customEventName;
        private RecentLogs.TimestampCollection debugLogsTime;
        private boolean isEventNameConstant;
        private boolean isUnsampled;
        private SystemHealthProto$SystemHealthMetric metric;
        private ExtensionMetric$MetricExtension metricExtension;
        private Long sampleRatePermille;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric build() {
            if (this.set$0 == 3 && this.metric != null) {
                return new AutoValue_Metric(this.customEventName, this.isEventNameConstant, this.metric, this.metricExtension, this.accountableComponentName, this.sampleRatePermille, this.isUnsampled, this.debugLogsTime);
            }
            StringBuilder sb = new StringBuilder();
            if ((this.set$0 & 1) == 0) {
                sb.append(" isEventNameConstant");
            }
            if (this.metric == null) {
                sb.append(" metric");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" isUnsampled");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setAccountableComponentName(String str) {
            this.accountableComponentName = str;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setCustomEventName(String str) {
            this.customEventName = str;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setDebugLogsTime(RecentLogs.TimestampCollection timestampCollection) {
            this.debugLogsTime = timestampCollection;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setIsEventNameConstant(boolean z) {
            this.isEventNameConstant = z;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setIsUnsampled(boolean z) {
            this.isUnsampled = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
            if (systemHealthProto$SystemHealthMetric == null) {
                throw new NullPointerException("Null metric");
            }
            this.metric = systemHealthProto$SystemHealthMetric;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            this.metricExtension = extensionMetric$MetricExtension;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.core.Metric.Builder
        public Metric.Builder setSampleRatePermille(Long l) {
            this.sampleRatePermille = l;
            return this;
        }
    }

    private AutoValue_Metric(String str, boolean z, SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, String str2, Long l, boolean z2, RecentLogs.TimestampCollection timestampCollection) {
        this.customEventName = str;
        this.isEventNameConstant = z;
        this.metric = systemHealthProto$SystemHealthMetric;
        this.metricExtension = extensionMetric$MetricExtension;
        this.accountableComponentName = str2;
        this.sampleRatePermille = l;
        this.isUnsampled = z2;
        this.debugLogsTime = timestampCollection;
    }

    public boolean equals(Object obj) {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        String str;
        Long l;
        if (obj == this) {
            return true;
        }
        if (obj instanceof Metric) {
            Metric metric = (Metric) obj;
            String str2 = this.customEventName;
            if (str2 != null ? str2.equals(metric.getCustomEventName()) : metric.getCustomEventName() == null) {
                if (this.isEventNameConstant == metric.getIsEventNameConstant() && this.metric.equals(metric.getMetric()) && ((extensionMetric$MetricExtension = this.metricExtension) != null ? extensionMetric$MetricExtension.equals(metric.getMetricExtension()) : metric.getMetricExtension() == null) && ((str = this.accountableComponentName) != null ? str.equals(metric.getAccountableComponentName()) : metric.getAccountableComponentName() == null) && ((l = this.sampleRatePermille) != null ? l.equals(metric.getSampleRatePermille()) : metric.getSampleRatePermille() == null) && this.isUnsampled == metric.getIsUnsampled()) {
                    RecentLogs.TimestampCollection timestampCollection = this.debugLogsTime;
                    if (timestampCollection == null) {
                        if (metric.getDebugLogsTime() == null) {
                            return true;
                        }
                    } else if (timestampCollection.equals(metric.getDebugLogsTime())) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    String getAccountableComponentName() {
        return this.accountableComponentName;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    String getCustomEventName() {
        return this.customEventName;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    RecentLogs.TimestampCollection getDebugLogsTime() {
        return this.debugLogsTime;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    boolean getIsEventNameConstant() {
        return this.isEventNameConstant;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    boolean getIsUnsampled() {
        return this.isUnsampled;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    SystemHealthProto$SystemHealthMetric getMetric() {
        return this.metric;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    ExtensionMetric$MetricExtension getMetricExtension() {
        return this.metricExtension;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.Metric
    Long getSampleRatePermille() {
        return this.sampleRatePermille;
    }

    public String toString() {
        String str = this.customEventName;
        boolean z = this.isEventNameConstant;
        String valueOf = String.valueOf(this.metric);
        String valueOf2 = String.valueOf(this.metricExtension);
        String str2 = this.accountableComponentName;
        Long l = this.sampleRatePermille;
        boolean z2 = this.isUnsampled;
        return "Metric{customEventName=" + str + ", isEventNameConstant=" + z + ", metric=" + valueOf + ", metricExtension=" + valueOf2 + ", accountableComponentName=" + str2 + ", sampleRatePermille=" + l + ", isUnsampled=" + z2 + ", debugLogsTime=" + String.valueOf(this.debugLogsTime) + "}";
    }

    public int hashCode() {
        String str = this.customEventName;
        int hashCode = ((((((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ (this.isEventNameConstant ? 1231 : 1237)) * 1000003) ^ this.metric.hashCode()) * 1000003;
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension = this.metricExtension;
        int hashCode2 = (hashCode ^ (extensionMetric$MetricExtension == null ? 0 : extensionMetric$MetricExtension.hashCode())) * 1000003;
        String str2 = this.accountableComponentName;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        Long l = this.sampleRatePermille;
        int hashCode4 = (((hashCode3 ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.isUnsampled ? 1231 : 1237)) * 1000003;
        RecentLogs.TimestampCollection timestampCollection = this.debugLogsTime;
        return hashCode4 ^ (timestampCollection != null ? timestampCollection.hashCode() : 0);
    }
}
