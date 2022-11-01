package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProtoDataStoreFactory$$ExternalSyntheticLambda1 implements AsyncFunction {
    public static final /* synthetic */ ProtoDataStoreFactory$$ExternalSyntheticLambda1 INSTANCE = new ProtoDataStoreFactory$$ExternalSyntheticLambda1();

    private /* synthetic */ ProtoDataStoreFactory$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        ListenableFuture immediateFuture;
        Uri uri = (Uri) obj;
        immediateFuture = Futures.immediateFuture("");
        return immediateFuture;
    }
}
