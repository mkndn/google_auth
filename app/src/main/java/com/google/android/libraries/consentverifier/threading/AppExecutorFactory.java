package com.google.android.libraries.consentverifier.threading;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AppExecutorFactory {
    private static final RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();

    public static Executor newBestEffortExecutor() {
        return new ThreadPoolExecutor(0, 10, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue(10), rejectedExecutionHandler);
    }
}
