package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskTracing {
    private static final TraceEngine NOOP_TRACE_ENGINE;
    private static TraceEngine traceEngine;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TraceEngine {
        Executor traceExecutor(Executor executor);
    }

    static {
        TaskTracing$$ExternalSyntheticLambda0 taskTracing$$ExternalSyntheticLambda0 = TaskTracing$$ExternalSyntheticLambda0.INSTANCE;
        NOOP_TRACE_ENGINE = taskTracing$$ExternalSyntheticLambda0;
        traceEngine = taskTracing$$ExternalSyntheticLambda0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Executor lambda$static$0(Executor executor) {
        return executor;
    }

    public static Executor traceExecutor(Executor executor) {
        return traceEngine.traceExecutor(executor);
    }
}
