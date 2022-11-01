package com.google.android.libraries.performance.primes.metrics.network;

import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AutoValue_NetworkConfigurations extends NetworkConfigurations {
    private final int batchSize;
    private final boolean enableUrlAutoSanitization;
    private final MetricEnablement enablement;
    private final Optional metricExtensionProvider;
    private final UrlSanitizer urlSanitizer;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends NetworkConfigurations.Builder {
        private int batchSize;
        private boolean enableUrlAutoSanitization;
        private MetricEnablement enablement;
        private Optional metricExtensionProvider = Optional.absent();
        private byte set$0;
        private UrlSanitizer urlSanitizer;

        @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations.Builder
        NetworkConfigurations autoBuild() {
            if (this.set$0 == 3 && this.enablement != null) {
                return new AutoValue_NetworkConfigurations(this.enablement, this.batchSize, this.urlSanitizer, this.enableUrlAutoSanitization, this.metricExtensionProvider);
            }
            StringBuilder sb = new StringBuilder();
            if (this.enablement == null) {
                sb.append(" enablement");
            }
            if ((this.set$0 & 1) == 0) {
                sb.append(" batchSize");
            }
            if ((this.set$0 & 2) == 0) {
                sb.append(" enableUrlAutoSanitization");
            }
            throw new IllegalStateException("Missing required properties:" + String.valueOf(sb));
        }

        @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations.Builder
        public NetworkConfigurations.Builder setBatchSize(int i) {
            this.batchSize = i;
            this.set$0 = (byte) (this.set$0 | 1);
            return this;
        }

        public NetworkConfigurations.Builder setEnableUrlAutoSanitization(boolean z) {
            this.enableUrlAutoSanitization = z;
            this.set$0 = (byte) (this.set$0 | 2);
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations.Builder
        NetworkConfigurations.Builder setEnablement(MetricEnablement metricEnablement) {
            if (metricEnablement == null) {
                throw new NullPointerException("Null enablement");
            }
            this.enablement = metricEnablement;
            return this;
        }

        @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations.Builder
        NetworkConfigurations.Builder setMetricExtensionProvider(Optional optional) {
            if (optional == null) {
                throw new NullPointerException("Null metricExtensionProvider");
            }
            this.metricExtensionProvider = optional;
            return this;
        }
    }

    private AutoValue_NetworkConfigurations(MetricEnablement metricEnablement, int i, UrlSanitizer urlSanitizer, boolean z, Optional optional) {
        this.enablement = metricEnablement;
        this.batchSize = i;
        this.urlSanitizer = urlSanitizer;
        this.enableUrlAutoSanitization = z;
        this.metricExtensionProvider = optional;
    }

    public boolean equals(Object obj) {
        UrlSanitizer urlSanitizer;
        if (obj == this) {
            return true;
        }
        if (obj instanceof NetworkConfigurations) {
            NetworkConfigurations networkConfigurations = (NetworkConfigurations) obj;
            return this.enablement.equals(networkConfigurations.getEnablement()) && this.batchSize == networkConfigurations.getBatchSize() && ((urlSanitizer = this.urlSanitizer) != null ? urlSanitizer.equals(networkConfigurations.getUrlSanitizer()) : networkConfigurations.getUrlSanitizer() == null) && this.enableUrlAutoSanitization == networkConfigurations.getEnableUrlAutoSanitization() && this.metricExtensionProvider.equals(networkConfigurations.getMetricExtensionProvider());
        }
        return false;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations
    public int getBatchSize() {
        return this.batchSize;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations
    public boolean getEnableUrlAutoSanitization() {
        return this.enableUrlAutoSanitization;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations
    public MetricEnablement getEnablement() {
        return this.enablement;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations
    public Optional getMetricExtensionProvider() {
        return this.metricExtensionProvider;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations
    public UrlSanitizer getUrlSanitizer() {
        return this.urlSanitizer;
    }

    public String toString() {
        String valueOf = String.valueOf(this.enablement);
        int i = this.batchSize;
        String valueOf2 = String.valueOf(this.urlSanitizer);
        boolean z = this.enableUrlAutoSanitization;
        return "NetworkConfigurations{enablement=" + valueOf + ", batchSize=" + i + ", urlSanitizer=" + valueOf2 + ", enableUrlAutoSanitization=" + z + ", metricExtensionProvider=" + String.valueOf(this.metricExtensionProvider) + "}";
    }

    public int hashCode() {
        int hashCode = (((this.enablement.hashCode() ^ 1000003) * 1000003) ^ this.batchSize) * 1000003;
        UrlSanitizer urlSanitizer = this.urlSanitizer;
        return ((((hashCode ^ (urlSanitizer == null ? 0 : urlSanitizer.hashCode())) * 1000003) ^ (this.enableUrlAutoSanitization ? 1231 : 1237)) * 1000003) ^ this.metricExtensionProvider.hashCode();
    }
}
