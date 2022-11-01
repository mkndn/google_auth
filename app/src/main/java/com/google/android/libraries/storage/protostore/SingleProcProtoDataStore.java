package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import android.os.Build;
import com.google.android.libraries.storage.file.SynchronousFileStorage;
import com.google.android.libraries.storage.file.behaviors.SyncingBehavior;
import com.google.android.libraries.storage.file.common.FileStorageUnavailableException;
import com.google.android.libraries.storage.file.openers.ReadStreamOpener;
import com.google.android.libraries.storage.file.openers.WriteStreamOpener;
import com.google.android.libraries.storage.protostore.XDataStoreVariant;
import com.google.android.libraries.storage.protostore.common.FileDiagnostics;
import com.google.android.libraries.storage.protostore.common.Uris;
import com.google.android.libraries.storage.protostore.serializers.ProtoSerializer;
import com.google.apps.tiktok.tracing.LibraryTracing;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.apps.tiktok.tracing.TracingRestricted;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ExtensionRegistryLite;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleProcProtoDataStore implements XDataStoreVariant {
    private final ListenableFuture fileFuture;
    private final IOExceptionHandler ioExceptionHandler;
    private final Executor ioExecutor;
    private final LibraryTracing libraryTracing;
    private final Serializer serializer;
    private final SynchronousFileStorage storage;
    private final String tracingName;
    private final Object lock = new Object();
    private final ExecutionSequencer futureSerializer = ExecutionSequencer.create();
    private ListenableFuture cachedData = null;
    private ListenableXDataStore$Listener listener = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory extends XDataStoreVariantFactory {
        private static final XDataStoreVariantFactory INSTANCE = new Factory();

        private Factory() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.storage.protostore.XDataStoreVariantFactory
        public SingleProcProtoDataStore create(ProtoDataStoreConfig protoDataStoreConfig, String str, Executor executor, SynchronousFileStorage synchronousFileStorage, LibraryRestricted libraryRestricted) {
            ExtensionRegistryLite emptyRegistry;
            LibraryTracing createForNonTikTok;
            Preconditions.checkNotNull(libraryRestricted);
            if (protoDataStoreConfig.useGeneratedExtensionRegistry()) {
                emptyRegistry = ExtensionRegistryLite.getGeneratedRegistry();
            } else {
                emptyRegistry = ExtensionRegistryLite.getEmptyRegistry();
            }
            ProtoSerializer create = ProtoSerializer.create(protoDataStoreConfig.schema(), emptyRegistry);
            ListenableFuture immediateFuture = Futures.immediateFuture(protoDataStoreConfig.uri());
            IOExceptionHandler handler = protoDataStoreConfig.handler();
            if (protoDataStoreConfig.enableTracing()) {
                createForNonTikTok = LibraryTracing.createForTikTok();
            } else {
                createForNonTikTok = LibraryTracing.createForNonTikTok();
            }
            return new SingleProcProtoDataStore(str, immediateFuture, create, executor, synchronousFileStorage, handler, createForNonTikTok);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.storage.protostore.XDataStoreVariantFactory
        public String id(LibraryRestricted libraryRestricted) {
            Preconditions.checkNotNull(libraryRestricted);
            return "singleproc";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SingleProcIOExceptionHandlerApi implements IOExceptionHandlerApi {
        private final SingleProcProtoDataStore store;

        private SingleProcIOExceptionHandlerApi(SingleProcProtoDataStore singleProcProtoDataStore) {
            this.store = singleProcProtoDataStore;
        }

        @Override // com.google.android.libraries.storage.protostore.IOExceptionHandlerApi
        public ListenableFuture replaceData(ListenableFuture listenableFuture) {
            return this.store.replaceData(listenableFuture);
        }
    }

    SingleProcProtoDataStore(String str, ListenableFuture listenableFuture, Serializer serializer, Executor executor, SynchronousFileStorage synchronousFileStorage, IOExceptionHandler iOExceptionHandler, LibraryTracing libraryTracing) {
        this.tracingName = str;
        this.fileFuture = Futures.nonCancellationPropagating(listenableFuture);
        this.serializer = serializer;
        this.ioExecutor = MoreExecutors.newSequentialExecutor(executor);
        this.storage = synchronousFileStorage;
        this.ioExceptionHandler = iOExceptionHandler;
        this.libraryTracing = libraryTracing;
    }

    private void addCallbackIfListenerPresent(ListenableFuture listenableFuture) {
        synchronized (this.lock) {
            final ListenableXDataStore$Listener listenableXDataStore$Listener = this.listener;
            if (listenableXDataStore$Listener != null) {
                Futures.addCallback(listenableFuture, new FutureCallback(this) { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore.1
                    @Override // com.google.common.util.concurrent.FutureCallback
                    public void onFailure(Throwable th) {
                    }

                    @Override // com.google.common.util.concurrent.FutureCallback
                    public void onSuccess(Object obj) {
                        listenableXDataStore$Listener.tickle(null);
                    }
                }, MoreExecutors.directExecutor());
            }
        }
    }

    public static XDataStoreVariantFactory factory() {
        return Factory.INSTANCE;
    }

    private ListenableFuture getCachedIfSuccessful(ListenableFuture listenableFuture) {
        return Futures.transformAsync(listenableFuture, new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda8
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SingleProcProtoDataStore.this.m397x8d677659(obj);
            }
        }, MoreExecutors.directExecutor());
    }

    private ListenableFuture handleReadException(IOException iOException, IOExceptionHandlerApi iOExceptionHandlerApi) {
        if (!(iOException instanceof FileStorageUnavailableException) && !(iOException.getCause() instanceof FileStorageUnavailableException)) {
            return Futures.transformAsync(this.ioExceptionHandler.handleReadException(iOException, iOExceptionHandlerApi), TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda6
                @Override // com.google.common.util.concurrent.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return SingleProcProtoDataStore.this.m399x1b29de93((Void) obj);
                }
            }), this.ioExecutor);
        }
        return Futures.immediateFailedFuture(iOException);
    }

    private ListenableFuture lazyWriteData(final ListenableFuture listenableFuture, final ListenableFuture listenableFuture2) {
        return Futures.transformAsync(listenableFuture2, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda4
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SingleProcProtoDataStore.this.m400xc7c08291(listenableFuture, listenableFuture2, obj);
            }
        }), MoreExecutors.directExecutor());
    }

    private ListenableFuture migrateBackup(ListenableFuture listenableFuture) {
        return Futures.transformAsync(listenableFuture, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda3
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SingleProcProtoDataStore.this.m401xa27f2bd5((Uri) obj);
            }
        }), this.ioExecutor);
    }

    private ListenableFuture populateAndGetCachedData() {
        ListenableFuture listenableFuture;
        synchronized (this.lock) {
            ListenableFuture listenableFuture2 = this.cachedData;
            if (listenableFuture2 != null && listenableFuture2.isDone()) {
                try {
                    Futures.getDone(this.cachedData);
                } catch (ExecutionException e) {
                    this.cachedData = null;
                }
            }
            if (this.cachedData == null) {
                this.cachedData = readData();
            }
            listenableFuture = this.cachedData;
        }
        return listenableFuture;
    }

    private ListenableFuture readData() {
        return Futures.nonCancellationPropagating(this.futureSerializer.submitAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda9
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return SingleProcProtoDataStore.this.m402x3f776ad();
            }
        }), this.ioExecutor));
    }

    private Object readDataSync(Uri uri) {
        try {
            try {
                SpanEndSignal beginSpan = this.libraryTracing.beginSpan("Read " + this.tracingName, TracingRestricted.I_HAVE_PERMISSION_TO_USE_RESTRICTED_APIS);
                try {
                    InputStream inputStream = (InputStream) this.storage.open(uri, ReadStreamOpener.create());
                    Object readFrom = this.serializer.readFrom(inputStream);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (beginSpan != null) {
                        beginSpan.close();
                    }
                    return readFrom;
                } catch (Throwable th) {
                    if (beginSpan != null) {
                        try {
                            beginSpan.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                if (this.storage.exists(uri)) {
                    throw e;
                }
                return this.serializer.defaultValue();
            }
        } catch (IOException e2) {
            throw FileDiagnostics.attachFileDebugInfoV1(this.storage, uri, e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ListenableFuture replaceData(ListenableFuture listenableFuture) {
        return Futures.transformAsync(listenableFuture, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SingleProcProtoDataStore.this.m403x8fff1c25(obj);
            }
        }), this.ioExecutor);
    }

    private ListenableFuture writeData(final ListenableFuture listenableFuture) {
        ListenableFuture transformAsync = Futures.transformAsync(listenableFuture, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda5
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return SingleProcProtoDataStore.this.m405xf03f3c9c(listenableFuture, obj);
            }
        }), this.ioExecutor);
        addCallbackIfListenerPresent(transformAsync);
        return transformAsync;
    }

    private void writeDataSync(Uri uri, Object obj) {
        Uri addSuffix = Uris.addSuffix(uri, ".tmp");
        try {
            SpanEndSignal beginSpan = this.libraryTracing.beginSpan("Write " + this.tracingName, TracingRestricted.I_HAVE_PERMISSION_TO_USE_RESTRICTED_APIS);
            SyncingBehavior syncingBehavior = new SyncingBehavior();
            try {
                OutputStream outputStream = (OutputStream) this.storage.open(addSuffix, WriteStreamOpener.create().withBehaviors(syncingBehavior));
                try {
                    this.serializer.writeTo(obj, outputStream);
                    syncingBehavior.sync();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (beginSpan != null) {
                        beginSpan.close();
                    }
                    this.storage.rename(addSuffix, uri);
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th2) {
                            Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException e) {
                throw FileDiagnostics.attachFileDebugInfoV1(this.storage, uri, e);
            }
        } catch (IOException e2) {
            if (this.storage.exists(addSuffix)) {
                try {
                    this.storage.deleteFile(addSuffix);
                } catch (IOException e3) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(e2, e3);
                    }
                }
            }
            throw e2;
        }
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStoreVariant
    public AsyncCallable getInitializer() {
        return new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return SingleProcProtoDataStore.this.m398x6e5eb244();
            }
        };
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStoreVariant
    public String getTracingName() {
        return this.tracingName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getCachedIfSuccessful$2$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m397x8d677659(Object obj) {
        ListenableFuture listenableFuture;
        synchronized (this.lock) {
            listenableFuture = this.cachedData;
        }
        return listenableFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getInitializer$0$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m398x6e5eb244() {
        return Futures.nonCancellationPropagating(migrateBackup(this.fileFuture));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleReadException$6$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m399x1b29de93(Void r1) {
        return Futures.immediateFuture(readDataSync((Uri) Futures.getDone(this.fileFuture)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$lazyWriteData$4$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m400xc7c08291(ListenableFuture listenableFuture, ListenableFuture listenableFuture2, Object obj) {
        if (Futures.getDone(listenableFuture).equals(Futures.getDone(listenableFuture2))) {
            return Futures.immediateVoidFuture();
        }
        return writeData(listenableFuture2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$migrateBackup$8$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m401xa27f2bd5(Uri uri) {
        Uri addSuffix = Uris.addSuffix(uri, ".bak");
        try {
            if (this.storage.exists(addSuffix)) {
                this.storage.rename(addSuffix, uri);
            }
            return Futures.immediateVoidFuture();
        } catch (IOException e) {
            return Futures.immediateFailedFuture(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$readData$3$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m402x3f776ad() {
        try {
            return Futures.immediateFuture(readDataSync((Uri) Futures.getDone(this.fileFuture)));
        } catch (IOException e) {
            return handleReadException(e, new SingleProcIOExceptionHandlerApi());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$replaceData$7$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m403x8fff1c25(Object obj) {
        writeDataSync((Uri) Futures.getDone(this.fileFuture), obj);
        return Futures.immediateVoidFuture();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$update$1$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m404x272bb726(ListenableFuture listenableFuture, AsyncFunction asyncFunction, Executor executor) {
        ListenableFuture cachedIfSuccessful = getCachedIfSuccessful(listenableFuture);
        return lazyWriteData(cachedIfSuccessful, Futures.transformAsync(cachedIfSuccessful, asyncFunction, executor));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeData$5$com-google-android-libraries-storage-protostore-SingleProcProtoDataStore  reason: not valid java name */
    public /* synthetic */ ListenableFuture m405xf03f3c9c(ListenableFuture listenableFuture, Object obj) {
        writeDataSync((Uri) Futures.getDone(this.fileFuture), obj);
        synchronized (this.lock) {
            this.cachedData = listenableFuture;
        }
        return Futures.immediateVoidFuture();
    }

    @Override // com.google.android.libraries.storage.protostore.XDataStoreVariant
    public ListenableFuture update(final AsyncFunction asyncFunction, final Executor executor, XDataStoreVariant.UpdateParam updateParam) {
        final ListenableFuture populateAndGetCachedData = populateAndGetCachedData();
        return this.futureSerializer.submitAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.SingleProcProtoDataStore$$ExternalSyntheticLambda7
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return SingleProcProtoDataStore.this.m404x272bb726(populateAndGetCachedData, asyncFunction, executor);
            }
        }), MoreExecutors.directExecutor());
    }
}
