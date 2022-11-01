package com.google.android.libraries.storage.protostore;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface InitializerApi {
    ListenableFuture updateDataAsync(AsyncFunction asyncFunction, Executor executor);
}
