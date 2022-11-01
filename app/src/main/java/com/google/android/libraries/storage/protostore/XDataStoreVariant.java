package com.google.android.libraries.storage.protostore;

import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public interface XDataStoreVariant {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface UpdateParam {
    }

    AsyncCallable getInitializer();

    String getTracingName();

    ListenableFuture update(AsyncFunction asyncFunction, Executor executor, UpdateParam updateParam);
}
