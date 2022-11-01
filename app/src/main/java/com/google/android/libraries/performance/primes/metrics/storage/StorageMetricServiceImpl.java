package com.google.android.libraries.performance.primes.metrics.storage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageStats;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class StorageMetricServiceImpl extends StorageMetricService implements AppLifecycleListener.OnAppToBackground, MetricService {
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Application application;
    private final Lazy configurationsProvider;
    private final Executor executor;
    private final MetricRecorder metricRecorder;
    private final PersistentRateLimiting persistentRateLimiting;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/storage/StorageMetricServiceImpl");
    private static final long CONSIDER_RECENT_DURATION_MS = TimeUnit.HOURS.toMillis(12);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public StorageMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, Executor executor, Lazy lazy, PersistentRateLimiting persistentRateLimiting, Provider provider) {
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.executor = executor;
        this.application = (Application) context;
        this.configurationsProvider = lazy;
        this.persistentRateLimiting = persistentRateLimiting;
        this.appLifecycleMonitor = appLifecycleMonitor;
    }

    private ListenableFuture sendInBackgroundInternal(final boolean z) {
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.storage.StorageMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return StorageMetricServiceImpl.this.m355x69f104cb(z);
            }
        }, this.executor);
    }

    static SystemHealthProto$PackageMetric statsToProto(PackageStats packageStats) {
        Preconditions.checkNotNull(packageStats);
        return (SystemHealthProto$PackageMetric) SystemHealthProto$PackageMetric.newBuilder().setCacheSize(packageStats.cacheSize).setCodeSize(packageStats.codeSize).setDataSize(packageStats.dataSize).setExternalCacheSize(packageStats.externalCacheSize).setExternalCodeSize(packageStats.externalCodeSize).setExternalDataSize(packageStats.externalDataSize).setExternalMediaSize(packageStats.externalMediaSize).setExternalObbSize(packageStats.externalObbSize).build();
    }

    boolean hasRecentTimestamp() {
        ThreadUtil.ensureBackgroundThread();
        return this.persistentRateLimiting.hasRecentTimeStamp("primes.packageMetric.lastSendTime", CONSIDER_RECENT_DURATION_MS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendInBackgroundInternal$0$com-google-android-libraries-performance-primes-metrics-storage-StorageMetricServiceImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m355x69f104cb(boolean z) {
        if (z != ((StorageConfigurations) this.configurationsProvider.get()).isManualCapture()) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/StorageMetricServiceImpl", "lambda$sendInBackgroundInternal$0", 104, "StorageMetricServiceImpl.java")).log("Ignoring storage metric request, triggering mechanism didn't match manual capture configuration");
            return Futures.immediateVoidFuture();
        } else if (!DirectBootUtils.isUserUnlocked(this.application)) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/StorageMetricServiceImpl", "lambda$sendInBackgroundInternal$0", 110, "StorageMetricServiceImpl.java")).log("Device locked.");
            return Futures.immediateVoidFuture();
        } else if (hasRecentTimestamp()) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/StorageMetricServiceImpl", "lambda$sendInBackgroundInternal$0", 114, "StorageMetricServiceImpl.java")).log("Ignoring storage metric request, storage metric collection occurred too recently.");
            return Futures.immediateVoidFuture();
        } else if (!this.metricRecorder.shouldCollectMetric(null)) {
            return Futures.immediateVoidFuture();
        } else {
            PackageStats packageStats = PackageStatsCapture.getPackageStats(this.application);
            if (packageStats == null) {
                return Futures.immediateFailedFuture(new IllegalStateException("PackageStats capture failed."));
            }
            SystemHealthProto$SystemHealthMetric.Builder newBuilder = SystemHealthProto$SystemHealthMetric.newBuilder();
            SystemHealthProto$PackageMetric.Builder builder = (SystemHealthProto$PackageMetric.Builder) statsToProto(packageStats).toBuilder();
            Optional dirStatsConfigurations = ((StorageConfigurations) this.configurationsProvider.get()).getDirStatsConfigurations();
            if (dirStatsConfigurations.isPresent() && ((DirStatsConfigurations) dirStatsConfigurations.get()).isEnabled()) {
                DirStatsConfigurations dirStatsConfigurations2 = (DirStatsConfigurations) dirStatsConfigurations.get();
                builder.clearDirStats().addAllDirStats(DirStatsCapture.getDirStats(this.application, dirStatsConfigurations2.getMaxFolderDepth(), dirStatsConfigurations2.getListPathMatchers(), dirStatsConfigurations2.getIncludeDeviceEncryptedStorage()));
            }
            newBuilder.setPackageMetric(builder);
            if (!this.persistentRateLimiting.writeTimeStamp("primes.packageMetric.lastSendTime")) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/storage/StorageMetricServiceImpl", "lambda$sendInBackgroundInternal$0", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DROIDGUARD_VERDICT_INPUT_VALUE, "StorageMetricServiceImpl.java")).log("Failure storing timestamp persistently");
            }
            return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) newBuilder.build()).build());
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        this.appLifecycleMonitor.unregister(this);
        PrimesExecutors.logFailures(sendInBackgroundInternal(false));
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.appLifecycleMonitor.register(this);
    }
}
