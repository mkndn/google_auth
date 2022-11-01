package com.google.android.libraries.performance.primes.flags.impl;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.crash.CrashRecordingTimeouts;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import googledata.experiments.mobile.primes_android.features.AppExitFeature;
import googledata.experiments.mobile.primes_android.features.BatteryFeature;
import googledata.experiments.mobile.primes_android.features.CpuprofilingFeature;
import googledata.experiments.mobile.primes_android.features.CrashFeature;
import googledata.experiments.mobile.primes_android.features.JankFeature;
import googledata.experiments.mobile.primes_android.features.MemoryFeature;
import googledata.experiments.mobile.primes_android.features.NetworkFeature;
import googledata.experiments.mobile.primes_android.features.StartupFeature;
import googledata.experiments.mobile.primes_android.features.StorageFeature;
import googledata.experiments.mobile.primes_android.features.TimerFeature;
import googledata.experiments.mobile.primes_android.features.TraceFeature;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PhenotypeFlagsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static boolean appExitCollectionEnabled(Context context) {
        return AppExitFeature.appExitCollectionEnabled(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static ApplicationExitReasons appExitReasonsToReport(Context context) {
        return AppExitFeature.appExitReasonsToReport(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters batterySamplingParameters(Context context) {
        return BatteryFeature.batterySamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters cpuProfilingSamplingParameters(Context context) {
        return CpuprofilingFeature.cpuprofilingSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static boolean enableAlwaysOnJankRecording(Context context) {
        return JankFeature.enableAlwaysOnJankRecording(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static boolean enableStartupBaselineDiscarding(Context context) {
        return StartupFeature.enableStartupBaselineDiscarding(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static long firstDrawType(Context context) {
        return StartupFeature.firstDrawType(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static PerfettoTraceConfigurations$JankPerfettoConfigurations jankPerfettoConfigurations(Context context) {
        return JankFeature.jankPerfettoConfigurations(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters jankSamplingParameters(Context context) {
        return JankFeature.jankSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters memorySamplingParameters(Context context) {
        return MemoryFeature.memorySamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters networkSamplingParameters(Context context) {
        return NetworkFeature.networkSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CrashRecordingTimeouts recordingTimeouts(Context context) {
        return CrashFeature.recordingTimeouts(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static boolean registerFrameMetricsListenerInOnCreate(Context context) {
        return JankFeature.registerFrameMetricsListenerInOnCreate(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters startupSamplingParameters(Context context) {
        return StartupFeature.startupSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters storageSamplingParameters(Context context) {
        return StorageFeature.storageSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters timerSamplingParameters(Context context) {
        return TimerFeature.timerSamplingParameters(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static SystemHealthProto$SamplingParameters traceSamplingParameters(Context context) {
        return TraceFeature.traceSamplingParameters(context);
    }
}
