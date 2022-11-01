package com.google.android.libraries.consentverifier.threading;

import com.google.android.gms.libs.punchclock.threads.PoolableExecutors;
import com.google.android.gms.libs.punchclock.threads.ThreadPriority;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GmscoreExecutorFactory {
    public static Executor newBestEffortExecutor() {
        return PoolableExecutors.factory().newThreadPool(10, ThreadPriority.LOW_POWER);
    }
}
