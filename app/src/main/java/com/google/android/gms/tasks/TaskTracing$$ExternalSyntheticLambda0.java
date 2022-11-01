package com.google.android.gms.tasks;

import com.google.android.gms.tasks.TaskTracing;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TaskTracing$$ExternalSyntheticLambda0 implements TaskTracing.TraceEngine {
    public static final /* synthetic */ TaskTracing$$ExternalSyntheticLambda0 INSTANCE = new TaskTracing$$ExternalSyntheticLambda0();

    private /* synthetic */ TaskTracing$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.gms.tasks.TaskTracing.TraceEngine
    public final Executor traceExecutor(Executor executor) {
        return TaskTracing.lambda$static$0(executor);
    }
}
