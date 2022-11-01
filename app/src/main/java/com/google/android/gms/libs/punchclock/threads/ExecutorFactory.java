package com.google.android.gms.libs.punchclock.threads;

import java.util.concurrent.ExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ExecutorFactory {
    ExecutorService newThreadPool(int i, ThreadPriority threadPriority);
}
