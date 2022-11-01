package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class TrustedListenableFutureTask extends FluentFuture.TrustedFuture implements RunnableFuture {
    private volatile InterruptibleTask task;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TrustedFutureInterruptibleAsyncTask extends InterruptibleTask {
        private final AsyncCallable callable;

        TrustedFutureInterruptibleAsyncTask(AsyncCallable asyncCallable) {
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        void afterRanInterruptiblyFailure(Throwable th) {
            TrustedListenableFutureTask.this.setException(th);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public void afterRanInterruptiblySuccess(ListenableFuture listenableFuture) {
            TrustedListenableFutureTask.this.setFuture(listenableFuture);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.InterruptibleTask
        public ListenableFuture runInterruptibly() {
            return (ListenableFuture) Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String toPendingString() {
            return this.callable.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable callable;

        TrustedFutureInterruptibleTask(Callable callable) {
            this.callable = (Callable) Preconditions.checkNotNull(callable);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        void afterRanInterruptiblyFailure(Throwable th) {
            TrustedListenableFutureTask.this.setException(th);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        void afterRanInterruptiblySuccess(Object obj) {
            TrustedListenableFutureTask.this.set(obj);
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        Object runInterruptibly() {
            return this.callable.call();
        }

        @Override // com.google.common.util.concurrent.InterruptibleTask
        String toPendingString() {
            return this.callable.toString();
        }
    }

    TrustedListenableFutureTask(AsyncCallable asyncCallable) {
        this.task = new TrustedFutureInterruptibleAsyncTask(asyncCallable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustedListenableFutureTask create(AsyncCallable asyncCallable) {
        return new TrustedListenableFutureTask(asyncCallable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        InterruptibleTask interruptibleTask;
        super.afterDone();
        if (wasInterrupted() && (interruptibleTask = this.task) != null) {
            interruptibleTask.interruptTask();
        }
        this.task = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            return "task=[" + interruptibleTask + "]";
        }
        return super.pendingToString();
    }

    @Override // java.util.concurrent.RunnableFuture, java.lang.Runnable
    public void run() {
        InterruptibleTask interruptibleTask = this.task;
        if (interruptibleTask != null) {
            interruptibleTask.run();
        }
        this.task = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustedListenableFutureTask create(Runnable runnable, Object obj) {
        return new TrustedListenableFutureTask(Executors.callable(runnable, obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrustedListenableFutureTask create(Callable callable) {
        return new TrustedListenableFutureTask(callable);
    }

    TrustedListenableFutureTask(Callable callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }
}
