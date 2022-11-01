package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.common.base.Optional;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityLevelJankMonitor_Factory implements Factory {
    private final Provider deferredExecutorProvider;
    private final Provider experimentEnableAlwaysOnJankRecordingProvider;
    private final Provider frameMetricServiceProvider;
    private final Provider monitorAllActivitiesProvider;

    public ActivityLevelJankMonitor_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.frameMetricServiceProvider = provider;
        this.experimentEnableAlwaysOnJankRecordingProvider = provider2;
        this.monitorAllActivitiesProvider = provider3;
        this.deferredExecutorProvider = provider4;
    }

    public static ActivityLevelJankMonitor_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new ActivityLevelJankMonitor_Factory(provider, provider2, provider3, provider4);
    }

    public static ActivityLevelJankMonitor newInstance(Lazy lazy, Provider provider, Optional optional, Executor executor) {
        return new ActivityLevelJankMonitor(lazy, provider, optional, executor);
    }

    @Override // javax.inject.Provider
    public ActivityLevelJankMonitor get() {
        return newInstance(DoubleCheck.lazy(this.frameMetricServiceProvider), this.experimentEnableAlwaysOnJankRecordingProvider, (Optional) this.monitorAllActivitiesProvider.get(), (Executor) this.deferredExecutorProvider.get());
    }
}
