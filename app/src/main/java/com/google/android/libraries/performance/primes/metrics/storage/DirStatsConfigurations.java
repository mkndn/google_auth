package com.google.android.libraries.performance.primes.metrics.storage;

import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.common.collect.ImmutableList;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DirStatsConfigurations implements MetricConfigurations {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface PathMatcher {
        boolean matches(String str);
    }

    DirStatsConfigurations() {
    }

    public abstract MetricEnablement getEnablement();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getIncludeDeviceEncryptedStorage();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ImmutableList getListPathMatchers();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getMaxFolderDepth();

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public /* synthetic */ int getRateLimitPerSecond() {
        throw null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.MetricConfigurations
    public final boolean isEnabled() {
        return getEnablement() == MetricEnablement.EXPLICITLY_ENABLED;
    }
}
