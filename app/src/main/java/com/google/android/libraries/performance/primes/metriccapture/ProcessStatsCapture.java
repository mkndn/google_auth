package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import android.os.Process;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStatsCapture {
    public static ProcessProto$AndroidProcessStats getAndroidProcessStats(Context context) {
        return getAndroidProcessStats(null, context);
    }

    public static ProcessProto$AndroidProcessStats getAndroidProcessStats(String str, Context context) {
        ProcessProto$AndroidProcessStats.Builder threadCount = ProcessProto$AndroidProcessStats.newBuilder().setProcessElapsedTimeMs(Process.getElapsedCpuTime()).setIsInForeground(ProcessStats.isAppInForeground(context)).setThreadCount(Thread.activeCount());
        if (str != null) {
            threadCount.setProcessName(str);
        }
        return (ProcessProto$AndroidProcessStats) threadCount.build();
    }
}
