package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Function;
import com.google.common.collect.Sets;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ApplicationExitInfo;
import logs.proto.wireless.performance.mobile.ApplicationExitMetric;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class ApplicationExitMetricServiceImpl extends ApplicationExitMetricService implements MetricService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/crash/applicationexit/ApplicationExitMetricServiceImpl");
    private final Provider appExitCollectionEnabledProvider;
    private final Provider appExitReasonsToReportProvider;
    private final Context application;
    private final Lazy applicationExitConfigurations;
    private final ApplicationExitInfoCapture applicationExitInfoCapture;
    private final Executor deferrableExecutor;
    private final MetricRecorder metricRecorder;
    private final Provider sharedPrefsProvider;

    /* renamed from: $r8$lambda$UDx51xMZMpfdUlHQHB-BgAnwyWM */
    public static /* synthetic */ ListenableFuture m330$r8$lambda$UDx51xMZMpfdUlHQHBBgAnwyWM(ApplicationExitMetricServiceImpl applicationExitMetricServiceImpl) {
        return applicationExitMetricServiceImpl.sendInBackgroundAsFuture();
    }

    @Inject
    public ApplicationExitMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, Executor executor, ApplicationExitInfoCapture applicationExitInfoCapture, Provider provider, Lazy lazy, Provider provider2, Provider provider3) {
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, null);
        this.application = context;
        this.deferrableExecutor = executor;
        this.applicationExitInfoCapture = applicationExitInfoCapture;
        this.sharedPrefsProvider = provider;
        this.applicationExitConfigurations = lazy;
        this.appExitCollectionEnabledProvider = provider2;
        this.appExitReasonsToReportProvider = provider3;
    }

    private String getLastAppExitProcessName() {
        return ((SharedPreferences) this.sharedPrefsProvider.get()).getString("lastExitProcessName", null);
    }

    private long getLastAppExitTimestamp() {
        return ((SharedPreferences) this.sharedPrefsProvider.get()).getLong("lastExitTimestamp", -1L);
    }

    private ListenableFuture recordMetric(ApplicationExitMetric applicationExitMetric) {
        return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setApplicationExitMetric(applicationExitMetric).build()).build());
    }

    public ListenableFuture sendInBackgroundAsFuture() {
        if (!((ApplicationExitConfigurations) this.applicationExitConfigurations.get()).isEnabled()) {
            return Futures.immediateVoidFuture();
        }
        String packageName = this.application.getPackageName();
        if (!Application.getProcessName().equals(packageName + ((ApplicationExitConfigurations) this.applicationExitConfigurations.get()).getReportingProcessShortName())) {
            return Futures.immediateVoidFuture();
        }
        if (!((Boolean) this.appExitCollectionEnabledProvider.get()).booleanValue()) {
            return Futures.immediateVoidFuture();
        }
        final List<ApplicationExitInfo> applicationExits = this.applicationExitInfoCapture.getApplicationExits(0, 0, getLastAppExitProcessName(), getLastAppExitTimestamp());
        if (applicationExits.isEmpty()) {
            return Futures.immediateVoidFuture();
        }
        ApplicationExitReasons applicationExitReasons = (ApplicationExitReasons) this.appExitReasonsToReportProvider.get();
        ApplicationExitMetric.Builder reportableReasons = ApplicationExitMetric.newBuilder().setTotalExits(applicationExits.size()).setReportableReasons(applicationExitReasons);
        HashSet newHashSet = Sets.newHashSet();
        for (int i = 0; i < applicationExitReasons.getExitReasonCount(); i++) {
            newHashSet.add(Integer.valueOf(applicationExitReasons.getExitReason(i).getNumber()));
        }
        for (ApplicationExitInfo applicationExitInfo : applicationExits) {
            if (newHashSet.contains(Integer.valueOf(applicationExitInfo.getReason().getNumber()))) {
                reportableReasons.addApplicationExitInfo(applicationExitInfo);
            }
        }
        return Futures.transform(recordMetric((ApplicationExitMetric) reportableReasons.build()), new Function() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ApplicationExitMetricServiceImpl.this.m333x72f88691(applicationExits, (Void) obj);
            }
        }, this.deferrableExecutor);
    }

    private boolean writeAppExitRecord(String str, long j) {
        return ((SharedPreferences) this.sharedPrefsProvider.get()).edit().putString("lastExitProcessName", str).putLong("lastExitTimestamp", j).commit();
    }

    /* renamed from: lambda$sendInBackground$0$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl */
    public /* synthetic */ void m331x81a4fae8() {
        PrimesExecutors.logFailures(Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return ApplicationExitMetricServiceImpl.m330$r8$lambda$UDx51xMZMpfdUlHQHBBgAnwyWM(ApplicationExitMetricServiceImpl.this);
            }
        }, this.deferrableExecutor));
    }

    /* renamed from: lambda$sendInBackground$1$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl */
    public /* synthetic */ ListenableFuture m332x8fb124c7() {
        return DirectBootUtils.runWhenUnlocked(this.application, new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ApplicationExitMetricServiceImpl.this.m331x81a4fae8();
            }
        });
    }

    /* renamed from: lambda$sendInBackgroundAsFuture$2$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl */
    public /* synthetic */ Void m333x72f88691(List list, Void r2) {
        updateLastRecordedAppExit((ApplicationExitInfo) list.get(0));
        return null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        sendInBackground();
    }

    public void sendInBackground() {
        PrimesExecutors.logFailures(Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return ApplicationExitMetricServiceImpl.this.m332x8fb124c7();
            }
        }, this.deferrableExecutor));
    }

    private void updateLastRecordedAppExit(ApplicationExitInfo applicationExitInfo) {
        boolean writeAppExitRecord;
        int i = 0;
        do {
            writeAppExitRecord = writeAppExitRecord(applicationExitInfo.getProcessName(), applicationExitInfo.getTimestampMillis());
            i++;
            if (writeAppExitRecord) {
                break;
            }
        } while (i < 3);
        if (!writeAppExitRecord) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/applicationexit/ApplicationExitMetricServiceImpl", "updateLastRecordedAppExit", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FIT_PRODUCT_DEVELOPMENT_VALUE, "ApplicationExitMetricServiceImpl.java")).log("Failed to persist most recent App Exit");
        }
    }
}
