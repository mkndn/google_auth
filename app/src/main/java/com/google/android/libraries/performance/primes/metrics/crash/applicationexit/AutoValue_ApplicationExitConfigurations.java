package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_ApplicationExitConfigurations extends ApplicationExitConfigurations {
    private final MetricEnablement enablement;
    private final String reportingProcessShortName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ApplicationExitConfigurations.Builder {
        private MetricEnablement enablement;
        private String reportingProcessShortName;

        @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations.Builder
        public ApplicationExitConfigurations build() {
            if (this.enablement != null && this.reportingProcessShortName != null) {
                return new AutoValue_ApplicationExitConfigurations(this.enablement, this.reportingProcessShortName);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if (this.reportingProcessShortName == null) {
                sb.append(" reportingProcessShortName");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ApplicationExitConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations.Builder
        public ApplicationExitConfigurations.Builder setReportingProcessShortName(String str) {
            if (str == null) {
                throw new NullPointerException("Null reportingProcessShortName");
            }
            this.reportingProcessShortName = str;
            return this;
        }
    }

    private AutoValue_ApplicationExitConfigurations(MetricEnablement metricEnablement, String str) {
        this.enablement = metricEnablement;
        this.reportingProcessShortName = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ApplicationExitConfigurations) {
            ApplicationExitConfigurations applicationExitConfigurations = (ApplicationExitConfigurations) obj;
            return this.enablement.equals(applicationExitConfigurations.getEnablement()) && this.reportingProcessShortName.equals(applicationExitConfigurations.getReportingProcessShortName());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations
    public String getReportingProcessShortName() {
        return this.reportingProcessShortName;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        return "ApplicationExitConfigurations{enablement=" + valueOf + ", reportingProcessShortName=" + this.reportingProcessShortName + "}";
    }

    public int hashCode() {
        return ((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.reportingProcessShortName.hashCode();
    }
}
