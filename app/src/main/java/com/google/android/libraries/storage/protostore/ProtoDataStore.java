package com.google.android.libraries.storage.protostore;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataStore extends XDataStore {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ProtoDataStore(XDataStoreVariant xDataStoreVariant, Logger logger, ListenableFuture listenableFuture, boolean z) {
        super(xDataStoreVariant, logger, listenableFuture, z);
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStore
    public /* bridge */ /* synthetic */ void addInitializer(AsyncFunction asyncFunction) {
        super.addInitializer(asyncFunction);
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStore
    public /* bridge */ /* synthetic */ ListenableFuture updateData(Function function, Executor executor) {
        return super.updateData(function, executor);
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStore
    public /* bridge */ /* synthetic */ ListenableFuture updateDataAsync(AsyncFunction asyncFunction, Executor executor) {
        return super.updateDataAsync(asyncFunction, executor);
    }
}
