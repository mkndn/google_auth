package com.google.android.libraries.storage.protostore;

import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.apps.tiktok.concurrent.Once;
import com.google.apps.tiktok.tracing.LibraryTracing;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.apps.tiktok.tracing.TracingRestricted;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class XDataStore {
    private final ListenableFuture hashedUri;
    private final Logger logger;
    private final LibraryTracing tracing;
    private final String tracingName;
    private final XDataStoreVariant variant;
    private final Once variantInit;
    private final Once init = new Once(new InitializationTasks(), MoreExecutors.directExecutor());
    private final Object lock = new Object();
    private List initTasks = new ArrayList();
    private final ExecutionSequencer updateSequencer = ExecutionSequencer.create();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InitializationTasks implements AsyncCallable {
        private List tasks;

        private InitializationTasks() {
            XDataStore.this = r1;
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public ListenableFuture call() {
            SpanEndSignal beginSpan = XDataStore.this.tracing.beginSpan("Initialize " + XDataStore.this.tracingName, TracingRestricted.I_HAVE_PERMISSION_TO_USE_RESTRICTED_APIS);
            try {
                synchronized (XDataStore.this.lock) {
                    if (this.tasks == null) {
                        this.tasks = XDataStore.this.initTasks;
                        XDataStore.this.initTasks = Collections.emptyList();
                    }
                }
                ArrayList arrayList = new ArrayList(this.tasks.size());
                XInitializerApi xInitializerApi = new XInitializerApi();
                for (AsyncFunction asyncFunction : this.tasks) {
                    try {
                        arrayList.add(asyncFunction.apply(xInitializerApi));
                    } catch (Exception e) {
                        arrayList.add(Futures.immediateFailedFuture(e));
                    }
                }
                ListenableFuture attachToFuture = beginSpan.attachToFuture(Futures.whenAllSucceed(arrayList).call(new Callable() { // from class: com.google.android.libraries.storage.protostore.XDataStore$InitializationTasks$$ExternalSyntheticLambda1
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return XDataStore.InitializationTasks.this.m418xde6bfcee();
                    }
                }, MoreExecutors.directExecutor()));
                if (beginSpan != null) {
                    beginSpan.close();
                }
                return attachToFuture;
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
        }

        /* renamed from: lambda$call$0$com-google-android-libraries-storage-protostore-XDataStore$InitializationTasks */
        public /* synthetic */ Object m418xde6bfcee() {
            synchronized (XDataStore.this.lock) {
                this.tasks = null;
            }
            return null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class XInitializerApi implements InitializerApi {
        private XInitializerApi() {
            XDataStore.this = r1;
        }

        /* renamed from: lambda$updateDataAsync$0$com-google-android-libraries-storage-protostore-XDataStore$XInitializerApi */
        public /* synthetic */ ListenableFuture m419x3e6a71ff(AsyncFunction asyncFunction, Executor executor, Void r4) {
            return XDataStore.this.variant.update(asyncFunction, executor, null);
        }

        @Override // com.google.android.libraries.storage.protostore.InitializerApi
        public ListenableFuture updateDataAsync(final AsyncFunction asyncFunction, final Executor executor) {
            XDataStore.this.tracing.checkTrace();
            return Futures.transformAsync(Futures.nonCancellationPropagating(XDataStore.this.variantInit.get()), TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.XDataStore$XInitializerApi$$ExternalSyntheticLambda0
                @Override // com.google.common.util.concurrent.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return XDataStore.XInitializerApi.this.m419x3e6a71ff(asyncFunction, executor, (Void) obj);
                }
            }), MoreExecutors.directExecutor());
        }
    }

    public XDataStore(XDataStoreVariant xDataStoreVariant, Logger logger, ListenableFuture listenableFuture, boolean z) {
        this.variant = xDataStoreVariant;
        this.logger = logger;
        this.hashedUri = listenableFuture;
        this.tracingName = xDataStoreVariant.getTracingName();
        this.variantInit = new Once(xDataStoreVariant.getInitializer(), MoreExecutors.directExecutor());
        if (z) {
            this.tracing = LibraryTracing.createForTikTok();
        } else {
            this.tracing = LibraryTracing.createForNonTikTok();
        }
        addInitializer(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.XDataStore$$ExternalSyntheticLambda4
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return XDataStore.this.m415xdcc7bdbc((InitializerApi) obj);
            }
        });
    }

    public static /* synthetic */ ListenableFuture lambda$updateData$5(Function function, Object obj) {
        return Futures.immediateFuture(function.apply(obj));
    }

    public static /* synthetic */ ListenableFuture lambda$updateDataAsync$2(ListenableFuture listenableFuture) {
        return listenableFuture;
    }

    public void addInitializer(AsyncFunction asyncFunction) {
        synchronized (this.lock) {
            this.initTasks.add(asyncFunction);
        }
    }

    /* renamed from: lambda$new$0$com-google-android-libraries-storage-protostore-XDataStore */
    public /* synthetic */ ListenableFuture m415xdcc7bdbc(InitializerApi initializerApi) {
        return this.variantInit.get();
    }

    /* renamed from: lambda$updateDataAsync$3$com-google-android-libraries-storage-protostore-XDataStore */
    public /* synthetic */ ListenableFuture m416x666ffc70(AsyncFunction asyncFunction, Executor executor, Object obj) {
        return this.variant.update(asyncFunction, executor, null);
    }

    /* renamed from: lambda$updateDataAsync$4$com-google-android-libraries-storage-protostore-XDataStore */
    public /* synthetic */ ListenableFuture m417x6c73c7cf(ListenableFuture listenableFuture, final AsyncFunction asyncFunction, final Executor executor) {
        return Futures.transformAsync(listenableFuture, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.XDataStore$$ExternalSyntheticLambda6
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return XDataStore.this.m416x666ffc70(asyncFunction, executor, obj);
            }
        }), MoreExecutors.directExecutor());
    }

    public ListenableFuture updateData(final Function function, Executor executor) {
        return updateDataAsync(TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.android.libraries.storage.protostore.XDataStore$$ExternalSyntheticLambda5
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return XDataStore.lambda$updateData$5(Function.this, obj);
            }
        }), executor);
    }

    public ListenableFuture updateDataAsync(final AsyncFunction asyncFunction, final Executor executor) {
        SpanEndSignal beginSpan = this.tracing.beginSpan("Update " + this.tracingName, TracingRestricted.I_HAVE_PERMISSION_TO_USE_RESTRICTED_APIS);
        try {
            final ListenableFuture listenableFuture = this.init.get();
            this.updateSequencer.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.XDataStore$$ExternalSyntheticLambda2
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    return XDataStore.lambda$updateDataAsync$2(ListenableFuture.this);
                }
            }, MoreExecutors.directExecutor());
            ListenableFuture submitAsync = this.updateSequencer.submitAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.XDataStore$$ExternalSyntheticLambda3
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    return XDataStore.this.m417x6c73c7cf(listenableFuture, asyncFunction, executor);
                }
            }), MoreExecutors.directExecutor());
            Futures.propagateCancellation(submitAsync, listenableFuture);
            this.logger.logUpdateData(Futures.nonCancellationPropagating(this.hashedUri));
            ListenableFuture attachToFuture = beginSpan.attachToFuture(submitAsync);
            if (beginSpan != null) {
                beginSpan.close();
            }
            return attachToFuture;
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
    }
}
