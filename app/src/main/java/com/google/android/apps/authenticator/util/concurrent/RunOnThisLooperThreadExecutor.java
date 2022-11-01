package com.google.android.apps.authenticator.util.concurrent;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RunOnThisLooperThreadExecutor implements Executor {
    private final Handler handler = new Handler();

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (Thread.currentThread() == this.handler.getLooper().getThread()) {
            runnable.run();
        } else {
            this.handler.post(runnable);
        }
    }
}
