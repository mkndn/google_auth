package com.google.android.libraries.storage.protostore;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ProtoDataMigration {
    ListenableFuture cleanup();

    ListenableFuture migrate(MessageLite messageLite);

    ListenableFuture shouldMigrate();
}
