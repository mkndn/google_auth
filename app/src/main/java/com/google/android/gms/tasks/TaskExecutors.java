package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new MainThreadExecutor();
    static final Executor DIRECT = new Executor() { // from class: com.google.android.gms.tasks.TaskExecutors.1
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            runnable.run();
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MainThreadExecutor implements Executor {
        private final Handler mHandler = new TracingHandler(Looper.getMainLooper());

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }
}
