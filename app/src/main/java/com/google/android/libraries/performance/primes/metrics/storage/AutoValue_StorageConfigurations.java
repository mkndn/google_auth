package com.google.android.libraries.performance.primes.metrics.storage;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_StorageConfigurations extends StorageConfigurations {
    private final Optional dirStatsConfigurations;
    private final MetricEnablement enablement;
    private final boolean manualCapture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends StorageConfigurations.Builder {
        private Optional dirStatsConfigurations = Optional.absent();
        private MetricEnablement enablement;
        private boolean manualCapture;
        private byte set$0;

        @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations.Builder
        public StorageConfigurations build() {
            if (this.set$0 == 1 && this.enablement != null) {
                return new AutoValue_StorageConfigurations(this.enablement, this.manualCapture, this.dirStatsConfigurations);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((1 & this.set$0) == 0) {
                sb.append(" manualCapture");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations.Builder
        StorageConfigurations.Builder setDirStatsConfigurations(Optional optional) {
            if (optional == null) {
                throw new NullPointerException("Null dirStatsConfigurations");
            }
            this.dirStatsConfigurations = optional;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations.Builder
        StorageConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        public StorageConfigurations.Builder setManualCapture(boolean z) {
            this.manualCapture = z;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }
    }

    private AutoValue_StorageConfigurations(MetricEnablement metricEnablement, boolean z, Optional optional) {
        this.enablement = metricEnablement;
        this.manualCapture = z;
        this.dirStatsConfigurations = optional;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof StorageConfigurations) {
            StorageConfigurations storageConfigurations = (StorageConfigurations) obj;
            return this.enablement.equals(storageConfigurations.getEnablement()) && this.manualCapture == storageConfigurations.isManualCapture() && this.dirStatsConfigurations.equals(storageConfigurations.getDirStatsConfigurations());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations
    public Optional getDirStatsConfigurations() {
        return this.dirStatsConfigurations;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations
    public boolean isManualCapture() {
        return this.manualCapture;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        boolean z = this.manualCapture;
        return "StorageConfigurations{enablement=" + valueOf + ", manualCapture=" + z + ", dirStatsConfigurations=" + String.valueOf(this.dirStatsConfigurations) + "}";
    }

    public int hashCode() {
        return ((((this.enablement.hashCode() ^ 1000003) * 1000003) ^ (this.manualCapture ? 1231 : 1237)) * 1000003) ^ this.dirStatsConfigurations.hashCode();
    }
}
