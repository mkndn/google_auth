package com.google.apps.tiktok.tracing;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceCheckingFlag {
    private static Mode traceCheckingMode = Mode.DISABLED;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Mode {
        DISABLED,
        THROW_ON_FAILURE,
        LOG_ON_FAILURE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isEnabled() {
        return traceCheckingMode != Mode.DISABLED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean logOnFailure() {
        return traceCheckingMode == Mode.LOG_ON_FAILURE;
    }
}
