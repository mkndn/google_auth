package com.google.android.libraries.performance.primes.metrics.battery;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryCapture;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class BatteryMetricServiceImpl extends BatteryMetricService implements AppLifecycleListener.OnActivityCreated, AppLifecycleListener.OnAppToForeground, AppLifecycleListener.OnAppToBackground, MetricService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/battery/BatteryMetricServiceImpl");
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Context applicationContext;
    private final BatteryCapture batteryCapture;
    private final Executor collectionExecutor;
    private final MetricRecorder metricRecorder;
    private final StatsStorage storage;
    final AtomicBoolean inForeground = new AtomicBoolean();
    final ConcurrentHashMap startSnapshots = new ConcurrentHashMap();
    private final AtomicBoolean activityHasBeenCreated = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    @Inject
    public BatteryMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, StatsStorage statsStorage, BatteryCapture batteryCapture, Provider provider, Executor executor) {
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.applicationContext = context;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.collectionExecutor = shouldDeferCollection() ? executor : listeningScheduledExecutorService;
        this.storage = statsStorage;
        this.batteryCapture = batteryCapture;
    }

    private ListenableFuture captureAndLog(final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, final NoPiiString noPiiString) {
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return BatteryMetricServiceImpl.this.m292x1a2d0423(sampleInfo, noPiiString);
            }
        }, this.collectionExecutor);
    }

    private BatteryCapture.Snapshot captureBattery(BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, NoPiiString noPiiString) {
        return this.batteryCapture.takeSnapshot(sampleInfo, NoPiiString.safeToString(noPiiString));
    }

    private static boolean shouldDeferCollection() {
        return Build.VERSION.SDK_INT < 31;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$captureAndLog$3$com-google-android-libraries-performance-primes-metrics-battery-BatteryMetricServiceImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m292x1a2d0423(BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, NoPiiString noPiiString) {
        StatsStorage.StatsRecord readStatsRecord;
        boolean writeStatsRecord;
        if (!this.metricRecorder.shouldCollectMetric(null)) {
            return Futures.immediateVoidFuture();
        }
        ThreadUtil.ensureBackgroundThread();
        synchronized (this.storage) {
            readStatsRecord = this.storage.readStatsRecord();
        }
        StatsStorage.StatsRecord statsRecord = captureBattery(sampleInfo, noPiiString).toStatsRecord();
        synchronized (this.storage) {
            writeStatsRecord = this.storage.writeStatsRecord(statsRecord);
        }
        if (!writeStatsRecord) {
            this.appLifecycleMonitor.unregister(this);
            synchronized (this.storage) {
                this.storage.clear();
            }
            throw new IOException("Failure storing persistent snapshot and helper data");
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryMetricServiceImpl", "lambda$captureAndLog$3", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CHROME_IMAGE_DESCRIPTIONS_VALUE, "BatteryMetricServiceImpl.java")).log("log start: %s\nend: %s", readStatsRecord, statsRecord);
        SystemHealthProto$SystemHealthMetric createBatteryMetric = this.batteryCapture.createBatteryMetric(readStatsRecord, statsRecord);
        if (createBatteryMetric == null) {
            return Futures.immediateVoidFuture();
        }
        return this.metricRecorder.recordMetric(Metric.newBuilder().setCustomEventName(statsRecord.getCustomEventName()).setIsEventNameConstant(true).setMetric(createBatteryMetric).setMetricExtension(statsRecord.getMetricExtension()).build());
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityCreated
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.activityHasBeenCreated.getAndSet(true)) {
            onAppToForeground(null);
        }
    }

    ListenableFuture onAppToBackground() {
        if (!DirectBootUtils.isUserUnlocked(this.applicationContext)) {
            return Futures.immediateVoidFuture();
        }
        try {
            Preconditions.checkState(this.inForeground.getAndSet(false));
            return captureAndLog(BatteryMetric$BatteryStatsDiff.SampleInfo.FOREGROUND_TO_BACKGROUND, null);
        } catch (Exception e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    ListenableFuture onAppToForeground() {
        if (!DirectBootUtils.isUserUnlocked(this.applicationContext)) {
            return Futures.immediateVoidFuture();
        }
        if (this.inForeground.getAndSet(true)) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryMetricServiceImpl", "onAppToForeground", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CRITICAL_FLEET_MANAGEMENT_VALUE, "BatteryMetricServiceImpl.java")).log("App is already in the foreground.");
            return Futures.immediateCancelledFuture();
        }
        return captureAndLog(BatteryMetric$BatteryStatsDiff.SampleInfo.BACKGROUND_TO_FOREGROUND, null);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.appLifecycleMonitor.register(this);
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToForeground
    public void onAppToForeground(Activity activity) {
        if (!this.inForeground.get()) {
            PrimesExecutors.logFailures(onAppToForeground());
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        PrimesExecutors.logFailures(onAppToBackground());
    }
}
