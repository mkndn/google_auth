package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitMetricServiceImpl_Factory implements Factory {
    private final Provider appExitCollectionEnabledProvider;
    private final Provider appExitReasonsToReportProvider;
    private final Provider applicationExitConfigurationsProvider;
    private final Provider applicationExitInfoCaptureProvider;
    private final Provider applicationProvider;
    private final Provider deferrableExecutorProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider sharedPrefsProvider;

    public ApplicationExitMetricServiceImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        this.metricRecorderFactoryProvider = provider;
        this.applicationProvider = provider2;
        this.deferrableExecutorProvider = provider3;
        this.applicationExitInfoCaptureProvider = provider4;
        this.sharedPrefsProvider = provider5;
        this.applicationExitConfigurationsProvider = provider6;
        this.appExitCollectionEnabledProvider = provider7;
        this.appExitReasonsToReportProvider = provider8;
    }

    public static ApplicationExitMetricServiceImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        return new ApplicationExitMetricServiceImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static ApplicationExitMetricServiceImpl newInstance(MetricRecorderFactory metricRecorderFactory, Context context, Executor executor, Object obj, Provider provider, Lazy lazy, Provider provider2, Provider provider3) {
        return new ApplicationExitMetricServiceImpl(metricRecorderFactory, context, executor, (ApplicationExitInfoCapture) obj, provider, lazy, provider2, provider3);
    }

    @Override // javax.inject.Provider
    public ApplicationExitMetricServiceImpl get() {
        return newInstance((MetricRecorderFactory) this.metricRecorderFactoryProvider.get(), (Context) this.applicationProvider.get(), (Executor) this.deferrableExecutorProvider.get(), this.applicationExitInfoCaptureProvider.get(), this.sharedPrefsProvider, DoubleCheck.lazy(this.applicationExitConfigurationsProvider), this.appExitCollectionEnabledProvider, this.appExitReasonsToReportProvider);
    }
}
