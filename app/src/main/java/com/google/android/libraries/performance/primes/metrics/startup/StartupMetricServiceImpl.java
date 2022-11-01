package com.google.android.libraries.performance.primes.metrics.startup;

import android.app.Activity;
import android.os.Build;
import android.os.Process;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.startup.StartupMeasure;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupActivity;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupMeasurements;

/* compiled from: PG */
/* loaded from: classes.dex */
final class StartupMetricServiceImpl extends StartupMetricService implements MetricService, AppLifecycleListener.OnAppToBackground {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/startup/StartupMetricServiceImpl");
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Provider enableStartupBaselineDiscarding;
    private final Provider firstDrawType;
    private final AtomicBoolean metricsRecorded = new AtomicBoolean();
    private final Provider startupMetricRecordingServiceProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public StartupMetricServiceImpl(AppLifecycleMonitor appLifecycleMonitor, Provider provider, Provider provider2, Provider provider3) {
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.startupMetricRecordingServiceProvider = provider;
        this.enableStartupBaselineDiscarding = provider2;
        this.firstDrawType = provider3;
    }

    private static PrimesTraceOuterClass$StartupActivity convertActivityInfoToProto(StartupMeasure.StartupActivityInfo startupActivityInfo) {
        PrimesTraceOuterClass$StartupActivity.Builder newBuilder = PrimesTraceOuterClass$StartupActivity.newBuilder();
        if (startupActivityInfo.name != null) {
            newBuilder.setName(startupActivityInfo.name);
        }
        if (startupActivityInfo.createdAt != null) {
            newBuilder.setCreatedMs(startupActivityInfo.createdAt.longValue());
        }
        if (startupActivityInfo.startedAt != null) {
            newBuilder.setStartedMs(startupActivityInfo.startedAt.longValue());
        }
        if (startupActivityInfo.resumedAt != null) {
            newBuilder.setResumedMs(startupActivityInfo.resumedAt.longValue());
        }
        return (PrimesTraceOuterClass$StartupActivity) newBuilder.build();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private PrimesTraceOuterClass$StartupMeasurements.Builder createBuilderWithTimestamps(StartupMeasure startupMeasure) {
        long preDrawBasedFirstDrawnAt;
        PrimesTraceOuterClass$StartupMeasurements.Builder newBuilder = PrimesTraceOuterClass$StartupMeasurements.newBuilder();
        newBuilder.setStartedByUser(startupMeasure.isColdStartup());
        if (startupMeasure.isColdStartup()) {
            newBuilder.setStartupType(PrimesTraceOuterClass$StartupMeasurements.StartupType.COLD);
        } else {
            newBuilder.setStartupType(PrimesTraceOuterClass$StartupMeasurements.StartupType.WARM);
        }
        StartupMeasure.TimestampsRecorded timestampsRecorded = startupMeasure.getTimestampsRecorded();
        Long l = null;
        if (timestampsRecorded.appClassLoaded) {
            long appClassLoadedAt = startupMeasure.getAppClassLoadedAt();
            newBuilder.setAppClassLoadedMs(appClassLoadedAt);
            l = Long.valueOf(minWithNull(null, appClassLoadedAt));
        }
        if (timestampsRecorded.appOnCreate) {
            long appOnCreateAt = startupMeasure.getAppOnCreateAt();
            newBuilder.setAppOnCreateMs(appOnCreateAt);
            l = Long.valueOf(minWithNull(l, appOnCreateAt));
        }
        if (timestampsRecorded.appOnCreateFinished) {
            long appOnCreateFinishedAt = startupMeasure.getAppOnCreateFinishedAt();
            newBuilder.setAppOnCreateFinishedMs(appOnCreateFinishedAt);
            l = Long.valueOf(minWithNull(l, appOnCreateFinishedAt));
        }
        if (timestampsRecorded.appAttachBaseContext) {
            long appAttachBaseContextAt = startupMeasure.getAppAttachBaseContextAt();
            newBuilder.setAppAttachBaseContextMs(appAttachBaseContextAt);
            l = Long.valueOf(minWithNull(l, appAttachBaseContextAt));
        }
        if (timestampsRecorded.appAttachBaseContextFinished) {
            long appAttachBaseContextFinishedAt = startupMeasure.getAppAttachBaseContextFinishedAt();
            newBuilder.setAppAttachBaseContextFinishedMs(appAttachBaseContextFinishedAt);
            l = Long.valueOf(minWithNull(l, appAttachBaseContextFinishedAt));
        }
        if (timestampsRecorded.firstOnActivityInit) {
            long firstOnActivityInitAt = startupMeasure.getFirstOnActivityInitAt();
            newBuilder.setFirstOnActivityInitMs(firstOnActivityInitAt);
            l = Long.valueOf(minWithNull(l, firstOnActivityInitAt));
        }
        switch (((Long) this.firstDrawType.get()).intValue()) {
            case 1:
                if (timestampsRecorded.preDrawBasedFirstDrawn) {
                    preDrawBasedFirstDrawnAt = startupMeasure.getPreDrawBasedFirstDrawnAt();
                    break;
                }
                preDrawBasedFirstDrawnAt = -1;
                break;
            case 2:
                if (timestampsRecorded.preDrawFrontOfQueueBasedFirstDrawn) {
                    preDrawBasedFirstDrawnAt = startupMeasure.getPreDrawFrontOfQueueBasedFirstDrawnAt();
                    break;
                }
                preDrawBasedFirstDrawnAt = -1;
                break;
            case 3:
                if (timestampsRecorded.onDrawBasedFirstDrawn) {
                    preDrawBasedFirstDrawnAt = startupMeasure.getOnDrawBasedFirstDrawnAt();
                    break;
                }
                preDrawBasedFirstDrawnAt = -1;
                break;
            case 4:
                if (timestampsRecorded.onDrawFrontOfQueueBasedFirstDrawn) {
                    preDrawBasedFirstDrawnAt = startupMeasure.getOnDrawFrontOfQueueBasedFirstDrawnAt();
                    break;
                }
                preDrawBasedFirstDrawnAt = -1;
                break;
            default:
                preDrawBasedFirstDrawnAt = -1;
                break;
        }
        if (preDrawBasedFirstDrawnAt != -1) {
            newBuilder.setFirstDrawnMs(preDrawBasedFirstDrawnAt);
            l = Long.valueOf(minWithNull(l, preDrawBasedFirstDrawnAt));
        }
        if (timestampsRecorded.onDrawBasedFirstDrawn) {
            long onDrawBasedFirstDrawnAt = startupMeasure.getOnDrawBasedFirstDrawnAt();
            newBuilder.setOndrawBasedFirstDrawnMs(onDrawBasedFirstDrawnAt);
            l = Long.valueOf(minWithNull(l, onDrawBasedFirstDrawnAt));
        }
        if (timestampsRecorded.onDrawFrontOfQueueBasedFirstDrawn) {
            long onDrawFrontOfQueueBasedFirstDrawnAt = startupMeasure.getOnDrawFrontOfQueueBasedFirstDrawnAt();
            newBuilder.setOndrawFrontOfQueueBasedFirstDrawnMs(onDrawFrontOfQueueBasedFirstDrawnAt);
            l = Long.valueOf(minWithNull(l, onDrawFrontOfQueueBasedFirstDrawnAt));
        }
        if (timestampsRecorded.preDrawBasedFirstDrawn) {
            long preDrawBasedFirstDrawnAt2 = startupMeasure.getPreDrawBasedFirstDrawnAt();
            newBuilder.setPredrawBasedFirstDrawnMs(preDrawBasedFirstDrawnAt2);
            l = Long.valueOf(minWithNull(l, preDrawBasedFirstDrawnAt2));
        }
        if (timestampsRecorded.preDrawFrontOfQueueBasedFirstDrawn) {
            long preDrawFrontOfQueueBasedFirstDrawnAt = startupMeasure.getPreDrawFrontOfQueueBasedFirstDrawnAt();
            newBuilder.setPredrawFrontOfQueueBasedFirstDrawnMs(preDrawFrontOfQueueBasedFirstDrawnAt);
            l = Long.valueOf(minWithNull(l, preDrawFrontOfQueueBasedFirstDrawnAt));
        }
        if (timestampsRecorded.firstAppInteractive) {
            long firstAppInteractiveAt = startupMeasure.getFirstAppInteractiveAt();
            newBuilder.setFirstAppInteractiveMs(firstAppInteractiveAt);
            l = Long.valueOf(minWithNull(l, firstAppInteractiveAt));
        }
        if (startupMeasure.getFirstActivity().createdAt != null) {
            PrimesTraceOuterClass$StartupActivity convertActivityInfoToProto = convertActivityInfoToProto(startupMeasure.getFirstActivity());
            newBuilder.setFirstStartupActivity(convertActivityInfoToProto);
            if (convertActivityInfoToProto.hasCreatedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto.getCreatedMs()));
            }
            if (convertActivityInfoToProto.hasStartedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto.getStartedMs()));
            }
            if (convertActivityInfoToProto.hasResumedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto.getResumedMs()));
            }
        }
        if (startupMeasure.getLastActivity().createdAt != null) {
            PrimesTraceOuterClass$StartupActivity convertActivityInfoToProto2 = convertActivityInfoToProto(startupMeasure.getLastActivity());
            newBuilder.setLastStartupActivity(convertActivityInfoToProto2);
            if (convertActivityInfoToProto2.hasCreatedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto2.getCreatedMs()));
            }
            if (convertActivityInfoToProto2.hasStartedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto2.getStartedMs()));
            }
            if (convertActivityInfoToProto2.hasResumedMs()) {
                l = Long.valueOf(minWithNull(l, convertActivityInfoToProto2.getResumedMs()));
            }
        }
        Optional processCreationMs = StartupTime.getProcessCreationMs();
        if (processCreationMs.isPresent()) {
            Long l2 = (Long) processCreationMs.get();
            newBuilder.setProcfsProcessCreationMs(l2.longValue());
            l = Long.valueOf(minWithNull(l, l2.longValue()));
        }
        if (Build.VERSION.SDK_INT >= 24) {
            long systemProcessCreateTime = getSystemProcessCreateTime();
            newBuilder.setProcessStartElapsedRealtimeMs(systemProcessCreateTime);
            l = Long.valueOf(minWithNull(l, systemProcessCreateTime));
        }
        newBuilder.setComplete(true);
        if (l != null) {
            StartupTimeRebaser.makeRelativeToBaseline(newBuilder, l.longValue(), ((Boolean) this.enableStartupBaselineDiscarding.get()).booleanValue());
        }
        return newBuilder;
    }

    private static long getSystemProcessCreateTime() {
        return Process.getStartElapsedRealtime();
    }

    private static long minWithNull(Long l, long j) {
        if (l == null) {
            return j;
        }
        return Math.min(l.longValue(), j);
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        long firstOnActivityInitAt;
        this.appLifecycleMonitor.unregister(this);
        StartupMeasure startupMeasure = StartupMeasure.get();
        if (startupMeasure.getOnDrawBasedFirstDrawnAt() <= 0 && startupMeasure.getPreDrawBasedFirstDrawnAt() <= 0) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/startup/StartupMetricServiceImpl", "onAppToBackground", 256, "StartupMetricServiceImpl.java")).log("missing firstDraw timestamp");
            return;
        }
        if (startupMeasure.isColdStartup()) {
            firstOnActivityInitAt = startupMeasure.getAppClassLoadedAt();
        } else {
            firstOnActivityInitAt = startupMeasure.getFirstOnActivityInitAt();
        }
        if (firstOnActivityInitAt <= 0) {
            return;
        }
        if (startupMeasure.getOnDrawBasedFirstDrawnAt() < firstOnActivityInitAt && startupMeasure.getPreDrawBasedFirstDrawnAt() < firstOnActivityInitAt) {
            return;
        }
        recordMetrics(startupMeasure);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.appLifecycleMonitor.register(this);
    }

    private void recordMetrics(StartupMeasure startupMeasure) {
        PrimesExecutors.logFailures(recordMetrics(createBuilderWithTimestamps(startupMeasure), startupMeasure.getStartupAccountableComponent()));
    }

    ListenableFuture recordMetrics(PrimesTraceOuterClass$StartupMeasurements.Builder builder, NoPiiString noPiiString) {
        if (!this.metricsRecorded.getAndSet(true)) {
            return ((StartupMetricRecordingService) this.startupMetricRecordingServiceProvider.get()).record(builder, NoPiiString.safeToString(noPiiString));
        }
        return Futures.immediateVoidFuture();
    }
}
