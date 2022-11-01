package com.google.android.libraries.storage.protostore;

import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Callables;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtoDataMigrationInitializer implements AsyncFunction {
    private final Executor executor;
    private final List migrations;

    private ProtoDataMigrationInitializer(List list, Executor executor) {
        this.migrations = list;
        this.executor = executor;
    }

    public static ProtoDataMigrationInitializer create(List list, Executor executor) {
        return new ProtoDataMigrationInitializer(list, executor);
    }

    public static /* synthetic */ ListenableFuture lambda$apply$0(ProtoDataMigration protoDataMigration, MessageLite messageLite) {
        return protoDataMigration.migrate(messageLite);
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public ListenableFuture apply(InitializerApi initializerApi) {
        final int size = this.migrations.size();
        final ArrayList arrayList = new ArrayList(size);
        for (ProtoDataMigration protoDataMigration : this.migrations) {
            arrayList.add(protoDataMigration.shouldMigrate());
        }
        return Futures.transformAsync(initializerApi.updateDataAsync(TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.ProtoDataMigrationInitializer$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return ProtoDataMigrationInitializer.this.m394xeecfdd79(arrayList, size, (MessageLite) obj);
            }
        }), MoreExecutors.directExecutor()), TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.ProtoDataMigrationInitializer$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return ProtoDataMigrationInitializer.this.m395xf5f8bfba(size, arrayList, obj);
            }
        }), MoreExecutors.directExecutor());
    }

    /* renamed from: lambda$apply$1$com-google-android-libraries-storage-protostore-ProtoDataMigrationInitializer */
    public /* synthetic */ ListenableFuture m393xe7a6fb38(MessageLite messageLite, int i, List list) {
        ListenableFuture immediateFuture = Futures.immediateFuture(messageLite);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) Futures.getDone((Future) list.get(i2))).booleanValue()) {
                final ProtoDataMigration protoDataMigration = (ProtoDataMigration) this.migrations.get(i2);
                immediateFuture = Futures.transformAsync(immediateFuture, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.ProtoDataMigrationInitializer$$ExternalSyntheticLambda3
                    @Override // com.google.common.util.concurrent.AsyncFunction
                    public final ListenableFuture apply(Object obj) {
                        return ProtoDataMigrationInitializer.lambda$apply$0(ProtoDataMigration.this, (MessageLite) obj);
                    }
                }), MoreExecutors.directExecutor());
            }
        }
        return immediateFuture;
    }

    /* renamed from: lambda$apply$2$com-google-android-libraries-storage-protostore-ProtoDataMigrationInitializer */
    public /* synthetic */ ListenableFuture m394xeecfdd79(final List list, final int i, final MessageLite messageLite) {
        return Futures.whenAllComplete(list).callAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.ProtoDataMigrationInitializer$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return ProtoDataMigrationInitializer.this.m393xe7a6fb38(messageLite, i, list);
            }
        }), this.executor);
    }

    /* renamed from: lambda$apply$3$com-google-android-libraries-storage-protostore-ProtoDataMigrationInitializer */
    public /* synthetic */ ListenableFuture m395xf5f8bfba(int i, List list, Object obj) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            if (((Boolean) Futures.getDone((Future) list.get(i2))).booleanValue()) {
                arrayList.add(((ProtoDataMigration) this.migrations.get(i2)).cleanup());
            }
        }
        return Futures.whenAllSucceed(arrayList).call(Callables.returning(null), MoreExecutors.directExecutor());
    }
}
