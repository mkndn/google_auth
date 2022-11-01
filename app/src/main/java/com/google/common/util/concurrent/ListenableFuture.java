package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ListenableFuture extends Future {
    void addListener(Runnable runnable, Executor executor);
}
