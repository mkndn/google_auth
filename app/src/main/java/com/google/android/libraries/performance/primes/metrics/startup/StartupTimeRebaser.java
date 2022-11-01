package com.google.android.libraries.performance.primes.metrics.startup;

import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupActivity;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupMeasurements;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupTimeRebaser {
    private static PrimesTraceOuterClass$StartupActivity makeRelativeToBaseline(PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity, long j) {
        PrimesTraceOuterClass$StartupActivity.Builder builder = (PrimesTraceOuterClass$StartupActivity.Builder) primesTraceOuterClass$StartupActivity.toBuilder();
        if (builder.hasCreatedMs()) {
            builder.setCreatedMs(builder.getCreatedMs() - j);
        }
        if (builder.hasStartedMs()) {
            builder.setStartedMs(builder.getStartedMs() - j);
        }
        if (builder.hasResumedMs()) {
            builder.setResumedMs(builder.getResumedMs() - j);
        }
        return (PrimesTraceOuterClass$StartupActivity) builder.build();
    }

    public static PrimesTraceOuterClass$StartupMeasurements.Builder makeRelativeToBaseline(PrimesTraceOuterClass$StartupMeasurements.Builder builder, long j, boolean z) {
        if (j == 0) {
            return builder;
        }
        if (!z) {
            builder.setBaselineTimeMs(j);
        }
        if (builder.hasAppClassLoadedMs()) {
            builder.setAppClassLoadedMs(builder.getAppClassLoadedMs() - j);
        }
        if (builder.hasAppOnCreateMs()) {
            builder.setAppOnCreateMs(builder.getAppOnCreateMs() - j);
        }
        if (builder.hasAppOnCreateFinishedMs()) {
            builder.setAppOnCreateFinishedMs(builder.getAppOnCreateFinishedMs() - j);
        }
        if (builder.hasAppAttachBaseContextMs()) {
            builder.setAppAttachBaseContextMs(builder.getAppAttachBaseContextMs() - j);
        }
        if (builder.hasAppAttachBaseContextFinishedMs()) {
            builder.setAppAttachBaseContextFinishedMs(builder.getAppAttachBaseContextFinishedMs() - j);
        }
        if (builder.hasFirstOnActivityInitMs()) {
            builder.setFirstOnActivityInitMs(builder.getFirstOnActivityInitMs() - j);
        }
        if (builder.hasFirstDrawnMs()) {
            builder.setFirstDrawnMs(builder.getFirstDrawnMs() - j);
        }
        if (builder.hasPredrawBasedFirstDrawnMs()) {
            builder.setPredrawBasedFirstDrawnMs(builder.getPredrawBasedFirstDrawnMs() - j);
        }
        if (builder.hasPredrawFrontOfQueueBasedFirstDrawnMs()) {
            builder.setPredrawFrontOfQueueBasedFirstDrawnMs(builder.getPredrawFrontOfQueueBasedFirstDrawnMs() - j);
        }
        if (builder.hasOndrawBasedFirstDrawnMs()) {
            builder.setOndrawBasedFirstDrawnMs(builder.getOndrawBasedFirstDrawnMs() - j);
        }
        if (builder.hasOndrawFrontOfQueueBasedFirstDrawnMs()) {
            builder.setOndrawFrontOfQueueBasedFirstDrawnMs(builder.getOndrawFrontOfQueueBasedFirstDrawnMs() - j);
        }
        if (builder.hasFirstAppInteractiveMs()) {
            builder.setFirstAppInteractiveMs(builder.getFirstAppInteractiveMs() - j);
        }
        if (builder.hasFirstStartupActivity()) {
            builder.setFirstStartupActivity(makeRelativeToBaseline(builder.getFirstStartupActivity(), j));
        }
        if (builder.hasLastStartupActivity()) {
            builder.setLastStartupActivity(makeRelativeToBaseline(builder.getLastStartupActivity(), j));
        }
        if (builder.hasProcessStartElapsedRealtimeMs()) {
            builder.setProcessStartElapsedRealtimeMs(builder.getProcessStartElapsedRealtimeMs() - j);
        }
        if (builder.hasProcfsProcessCreationMs()) {
            builder.setProcfsProcessCreationMs(builder.getProcfsProcessCreationMs() - j);
        }
        return builder;
    }
}
