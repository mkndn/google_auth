package com.google.apps.tiktok.concurrent;

import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Once {
    private final CallableHolder callableHolder;
    private final SettableFuture completedValue;
    private final AtomicLong currentState = new AtomicLong(packState(Integer.MIN_VALUE, Integer.MIN_VALUE));
    private final AtomicReference currentlyExecuting = new AtomicReference(null);
    private final AtomicReference prevInvocation = new AtomicReference(null);
    private final Executor sequentialDirect = MoreExecutors.newSequentialExecutor(MoreExecutors.directExecutor());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CallableHolder implements Runnable {
        private AsyncCallable callable;
        private Executor executor;

        CallableHolder(AsyncCallable asyncCallable, Executor executor) {
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
            this.executor = (Executor) Preconditions.checkNotNull(executor);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.callable = null;
            this.executor = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnceFuture extends AbstractFuture {
        private Once once;
        private final int tag;

        private OnceFuture(Once once, int i) {
            this.once = once;
            this.tag = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            TaggedFuture taggedFuture;
            Once once = this.once;
            this.once = null;
            if (once != null && once.decrement()) {
                do {
                    taggedFuture = (TaggedFuture) once.currentlyExecuting.get();
                    if (taggedFuture != null && taggedFuture.tag <= this.tag) {
                        taggedFuture.cancel(true);
                    } else {
                        return;
                    }
                } while (!Once$OnceFuture$$ExternalSyntheticBackportWithForwarding0.m(once.currentlyExecuting, taggedFuture, null));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            AsyncCallable asyncCallable;
            TaggedFuture taggedFuture;
            Once once = this.once;
            if (once == null || (asyncCallable = once.callableHolder.callable) == null) {
                return null;
            }
            String str = "callable=[" + String.valueOf(asyncCallable) + "]";
            return ((TaggedFuture) this.once.currentlyExecuting.get()) != null ? str + ", trial=[" + String.valueOf(taggedFuture) + "]" : str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setFuture(ListenableFuture listenableFuture) {
            return super.setFuture(listenableFuture);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TaggedFuture extends AbstractFuture {
        private final int tag;

        TaggedFuture(int i) {
            this.tag = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setException(Throwable th) {
            return super.setException(th);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setFuture(ListenableFuture listenableFuture) {
            return super.setFuture(listenableFuture);
        }
    }

    public Once(AsyncCallable asyncCallable, Executor executor) {
        SettableFuture create = SettableFuture.create();
        this.completedValue = create;
        CallableHolder callableHolder = new CallableHolder(asyncCallable, executor);
        this.callableHolder = callableHolder;
        create.addListener(callableHolder, MoreExecutors.directExecutor());
    }

    private static int getRefCount(long j) {
        return (int) j;
    }

    private static int getTag(long j) {
        return (int) (j >>> 32);
    }

    private int incrementRefCountAndGetTag() {
        long j;
        int tag;
        do {
            j = this.currentState.get();
            tag = getTag(j);
        } while (!this.currentState.compareAndSet(j, packState(tag, getRefCount(j) + 1)));
        return tag;
    }

    private static long packState(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: query */
    public ListenableFuture m565lambda$reQueryOnFailure$1$comgoogleappstiktokconcurrentOnce(int i) {
        TaggedFuture taggedFuture;
        if (getTag(this.currentState.get()) > i) {
            return Futures.immediateCancelledFuture();
        }
        TaggedFuture taggedFuture2 = new TaggedFuture(i);
        do {
            taggedFuture = (TaggedFuture) this.currentlyExecuting.get();
            if (taggedFuture != null && taggedFuture.tag > i) {
                return Futures.immediateCancelledFuture();
            }
        } while (!Once$$ExternalSyntheticBackportWithForwarding0.m(this.currentlyExecuting, taggedFuture, taggedFuture2));
        if (getTag(this.currentState.get()) > i) {
            taggedFuture2.cancel(true);
            Once$$ExternalSyntheticBackportWithForwarding0.m(this.currentlyExecuting, taggedFuture2, null);
            return taggedFuture2;
        }
        AsyncCallable asyncCallable = this.callableHolder.callable;
        Executor executor = this.callableHolder.executor;
        if (asyncCallable != null && executor != null) {
            taggedFuture2.setFuture(Futures.submitAsync(TracePropagation.propagateAsyncCallable(asyncCallable), executor));
        } else {
            taggedFuture2.setFuture(this.completedValue);
        }
        return taggedFuture2;
    }

    private ListenableFuture reQueryOnFailure(ListenableFuture listenableFuture, final int i) {
        if (listenableFuture == null) {
            return Futures.submitAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda2
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    return Once.this.m565lambda$reQueryOnFailure$1$comgoogleappstiktokconcurrentOnce(i);
                }
            }), MoreExecutors.directExecutor());
        }
        return Futures.catchingAsync(listenableFuture, Throwable.class, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda3
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return Once.this.m566lambda$reQueryOnFailure$2$comgoogleappstiktokconcurrentOnce(i, (Throwable) obj);
            }
        }), this.sequentialDirect);
    }

    public ListenableFuture get() {
        if (this.completedValue.isDone()) {
            return this.completedValue;
        }
        int incrementRefCountAndGetTag = incrementRefCountAndGetTag();
        final SettableFuture create = SettableFuture.create();
        create.setFuture(reQueryOnFailure((ListenableFuture) this.prevInvocation.getAndSet(create), incrementRefCountAndGetTag));
        final OnceFuture onceFuture = new OnceFuture(incrementRefCountAndGetTag);
        create.addListener(new Runnable() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Once.this.m564lambda$get$0$comgoogleappstiktokconcurrentOnce(create, onceFuture);
            }
        }, MoreExecutors.directExecutor());
        return onceFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$get$0$com-google-apps-tiktok-concurrent-Once  reason: not valid java name */
    public /* synthetic */ void m564lambda$get$0$comgoogleappstiktokconcurrentOnce(SettableFuture settableFuture, OnceFuture onceFuture) {
        try {
            this.completedValue.set(Futures.getDone(settableFuture));
            onceFuture.setFuture(this.completedValue);
        } catch (Throwable th) {
            onceFuture.setFuture(settableFuture);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reQueryOnFailure$2$com-google-apps-tiktok-concurrent-Once  reason: not valid java name */
    public /* synthetic */ ListenableFuture m566lambda$reQueryOnFailure$2$comgoogleappstiktokconcurrentOnce(int i, Throwable th) {
        return m565lambda$reQueryOnFailure$1$comgoogleappstiktokconcurrentOnce(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean decrement() {
        long j;
        int refCount;
        int tag;
        boolean z;
        do {
            j = this.currentState.get();
            refCount = getRefCount(j);
            tag = getTag(j);
            if (refCount == Integer.MIN_VALUE) {
                throw new AssertionError("Refcount is: " + j);
            }
            z = refCount == -2147483647;
            if (z) {
                tag++;
            }
        } while (!this.currentState.compareAndSet(j, packState(tag, refCount - 1)));
        return z;
    }
}
