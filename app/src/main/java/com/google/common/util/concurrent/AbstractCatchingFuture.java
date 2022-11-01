package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractCatchingFuture extends FluentFuture.TrustedFuture implements Runnable {
    Class exceptionType;
    Object fallback;
    ListenableFuture inputFuture;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AsyncCatchingFuture extends AbstractCatchingFuture {
        AsyncCatchingFuture(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction) {
            super(listenableFuture, cls, asyncFunction);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public ListenableFuture doFallback(AsyncFunction asyncFunction, Throwable th) {
            ListenableFuture apply = asyncFunction.apply(th);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", asyncFunction);
            return apply;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public void setResult(ListenableFuture listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CatchingFuture extends AbstractCatchingFuture {
        CatchingFuture(ListenableFuture listenableFuture, Class cls, Function function) {
            super(listenableFuture, cls, function);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        public Object doFallback(Function function, Throwable th) {
            return function.apply(th);
        }

        @Override // com.google.common.util.concurrent.AbstractCatchingFuture
        void setResult(Object obj) {
            set(obj);
        }
    }

    AbstractCatchingFuture(ListenableFuture listenableFuture, Class cls, Object obj) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.exceptionType = (Class) Preconditions.checkNotNull(cls);
        this.fallback = Preconditions.checkNotNull(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListenableFuture create(ListenableFuture listenableFuture, Class cls, Function function, Executor executor) {
        CatchingFuture catchingFuture = new CatchingFuture(listenableFuture, cls, function);
        listenableFuture.addListener(catchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, catchingFuture));
        return catchingFuture;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    abstract Object doFallback(Object obj, Throwable th);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        String str;
        ListenableFuture listenableFuture = this.inputFuture;
        Class cls = this.exceptionType;
        Object obj = this.fallback;
        String pendingToString = super.pendingToString();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture + "], ";
        } else {
            str = "";
        }
        if (cls != null && obj != null) {
            return str + "exceptionType=[" + cls + "], fallback=[" + obj + "]";
        }
        if (pendingToString != null) {
            return str + pendingToString;
        }
        return null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NullPointerException e;
        Object obj;
        ListenableFuture listenableFuture = this.inputFuture;
        Class cls = this.exceptionType;
        Object obj2 = this.fallback;
        if (!((obj2 == null) | (listenableFuture == null) | (cls == null)) && !isCancelled()) {
            this.inputFuture = null;
            try {
                if (listenableFuture instanceof InternalFutureFailureAccess) {
                    e = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) listenableFuture);
                } else {
                    e = null;
                }
                obj = e == null ? Futures.getDone(listenableFuture) : null;
            } catch (Error e2) {
                e = e2;
                obj = null;
            } catch (RuntimeException e3) {
                e = e3;
                obj = null;
            } catch (ExecutionException e4) {
                Throwable cause = e4.getCause();
                if (cause != null) {
                    e = cause;
                } else {
                    e = new NullPointerException("Future type " + listenableFuture.getClass() + " threw " + e4.getClass() + " without a cause");
                }
                obj = null;
            }
            if (e == null) {
                set(NullnessCasts.uncheckedCastNullableTToT(obj));
            } else if (Platform.isInstanceOfThrowableClass(e, cls)) {
                try {
                    Object doFallback = doFallback(obj2, e);
                    this.exceptionType = null;
                    this.fallback = null;
                    setResult(doFallback);
                } catch (Throwable th) {
                    try {
                        Platform.restoreInterruptIfIsInterruptedException(th);
                        setException(th);
                    } finally {
                        this.exceptionType = null;
                        this.fallback = null;
                    }
                }
            } else {
                setFuture(listenableFuture);
            }
        }
    }

    abstract void setResult(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListenableFuture create(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction, Executor executor) {
        AsyncCatchingFuture asyncCatchingFuture = new AsyncCatchingFuture(listenableFuture, cls, asyncFunction);
        listenableFuture.addListener(asyncCatchingFuture, MoreExecutors.rejectionPropagatingExecutor(executor, asyncCatchingFuture));
        return asyncCatchingFuture;
    }
}
