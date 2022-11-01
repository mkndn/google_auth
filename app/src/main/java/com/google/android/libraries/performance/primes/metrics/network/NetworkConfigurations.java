package com.google.android.libraries.performance.primes.metrics.network;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.network.AutoValue_NetworkConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NetworkConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Builder {
        abstract NetworkConfigurations autoBuild();

        public final NetworkConfigurations build() {
            NetworkConfigurations autoBuild = autoBuild();
            Preconditions.checkArgument(autoBuild.getUrlSanitizer() == null || !autoBuild.getEnableUrlAutoSanitization(), "only one of auto url auto sanitization and custom url sanitizer can be enabled.");
            return autoBuild;
        }

        public abstract Builder setBatchSize(int i);

        public final Builder setEnabled(boolean z) {
            return setEnablement(MetricEnablement.forBoolean(z));
        }

        abstract Builder setEnablement(MetricEnablement metricEnablement);

        abstract Builder setMetricExtensionProvider(Optional optional);
    }

    public static final Builder newBuilder() {
        return new AutoValue_NetworkConfigurations.Builder().setEnableUrlAutoSanitization(false).setBatchSize(50).setMetricExtensionProvider(Optional.absent()).setEnablement(MetricEnablement.DEFAULT);
    }

    public abstract int getBatchSize();

    public abstract boolean getEnableUrlAutoSanitization();

    public abstract MetricEnablement getEnablement();

    public abstract Optional getMetricExtensionProvider();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        return MetricConfigurations.CC.$default$getRateLimitPerSecond(this);
    }

    public abstract UrlSanitizer getUrlSanitizer();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
