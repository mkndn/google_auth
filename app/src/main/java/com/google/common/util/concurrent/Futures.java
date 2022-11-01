package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.CollectionFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.internal.InternalFutureFailureAccess;
import com.google.common.util.concurrent.internal.InternalFutures;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Futures extends GwtFuturesCatchingSpecialization {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CallbackListener implements Runnable {
        final FutureCallback callback;
        final Future future;

        CallbackListener(Future future, FutureCallback futureCallback) {
            this.future = future;
            this.callback = futureCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable tryInternalFastPathGetFailure;
            Future future = this.future;
            if ((future instanceof InternalFutureFailureAccess) && (tryInternalFastPathGetFailure = InternalFutures.tryInternalFastPathGetFailure((InternalFutureFailureAccess) future)) != null) {
                this.callback.onFailure(tryInternalFastPathGetFailure);
                return;
            }
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (Error e) {
                e = e;
                this.callback.onFailure(e);
            } catch (RuntimeException e2) {
                e = e2;
                this.callback.onFailure(e);
            } catch (ExecutionException e3) {
                this.callback.onFailure(e3.getCause());
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.callback).toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CancellationPropagater implements Runnable {
        ListenableFuture from;
        Future to;

        CancellationPropagater(ListenableFuture listenableFuture, Future future) {
            this.from = listenableFuture;
            this.to = future;
        }

        @Override // java.lang.Runnable
        public void run() {
            Futures.maybePropagateCancellation(this.from, this.to);
            this.from = null;
            this.to = null;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FutureCombiner {
        private final boolean allMustSucceed;
        private final ImmutableList futures;

        private FutureCombiner(boolean z, ImmutableList immutableList) {
            this.allMustSucceed = z;
            this.futures = immutableList;
        }

        public ListenableFuture call(Callable callable, Executor executor) {
            return new CombinedFuture(this.futures, this.allMustSucceed, executor, callable);
        }

        public ListenableFuture callAsync(AsyncCallable asyncCallable, Executor executor) {
            return new CombinedFuture(this.futures, this.allMustSucceed, executor, asyncCallable);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NonCancellationPropagatingFuture extends AbstractFuture.TrustedFuture implements Runnable {
        private ListenableFuture delegate;

        NonCancellationPropagatingFuture(ListenableFuture listenableFuture) {
            this.delegate = listenableFuture;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public void afterDone() {
            this.delegate = null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            ListenableFuture listenableFuture = this.delegate;
            if (listenableFuture != null) {
                return "delegate=[" + listenableFuture + "]";
            }
            return null;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture listenableFuture = this.delegate;
            if (listenableFuture != null) {
                setFuture(listenableFuture);
            }
        }
    }

    public static void addCallback(ListenableFuture listenableFuture, FutureCallback futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new CallbackListener(listenableFuture, futureCallback), executor);
    }

    public static ListenableFuture catching(ListenableFuture listenableFuture, Class cls, Function function, Executor executor) {
        return AbstractCatchingFuture.create(listenableFuture, cls, function, executor);
    }

    public static ListenableFuture catchingAsync(ListenableFuture listenableFuture, Class cls, AsyncFunction asyncFunction, Executor executor) {
        return AbstractCatchingFuture.create(listenableFuture, cls, asyncFunction, executor);
    }

    public static Object getDone(Future future) {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
        return Uninterruptibles.getUninterruptibly(future);
    }

    public static ListenableFuture immediateCancelledFuture() {
        ImmediateFuture.ImmediateCancelledFuture immediateCancelledFuture = ImmediateFuture.ImmediateCancelledFuture.INSTANCE;
        if (immediateCancelledFuture != null) {
            return immediateCancelledFuture;
        }
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    public static ListenableFuture immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new ImmediateFuture.ImmediateFailedFuture(th);
    }

    public static ListenableFuture immediateFuture(Object obj) {
        if (obj == null) {
            return ImmediateFuture.NULL;
        }
        return new ImmediateFuture(obj);
    }

    public static ListenableFuture immediateVoidFuture() {
        return ImmediateFuture.NULL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void maybePropagateCancellation(ListenableFuture listenableFuture, Future future) {
        if (listenableFuture instanceof AbstractFuture) {
            ((AbstractFuture) listenableFuture).maybePropagateCancellationTo(future);
        } else if (listenableFuture != null && listenableFuture.isCancelled() && future != null) {
            future.cancel(false);
        }
    }

    public static ListenableFuture nonCancellationPropagating(ListenableFuture listenableFuture) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        NonCancellationPropagatingFuture nonCancellationPropagatingFuture = new NonCancellationPropagatingFuture(listenableFuture);
        listenableFuture.addListener(nonCancellationPropagatingFuture, MoreExecutors.directExecutor());
        return nonCancellationPropagatingFuture;
    }

    public static void propagateCancellation(ListenableFuture listenableFuture, Future future) {
        Preconditions.checkNotNull(listenableFuture);
        if (!future.isDone()) {
            if (listenableFuture.isDone()) {
                maybePropagateCancellation(listenableFuture, future);
                return;
            }
            CancellationPropagater cancellationPropagater = new CancellationPropagater(listenableFuture, future);
            listenableFuture.addListener(cancellationPropagater, MoreExecutors.directExecutor());
            if (future instanceof ListenableFuture) {
                ((ListenableFuture) future).addListener(cancellationPropagater, MoreExecutors.directExecutor());
            }
        }
    }

    public static ListenableFuture scheduleAsync(AsyncCallable asyncCallable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(asyncCallable);
        final ScheduledFuture<?> schedule = scheduledExecutorService.schedule(create, j, timeUnit);
        create.addListener(new Runnable() { // from class: com.google.common.util.concurrent.Futures.1
            @Override // java.lang.Runnable
            public void run() {
                schedule.cancel(false);
            }
        }, MoreExecutors.directExecutor());
        return create;
    }

    public static ListenableFuture submitAsync(AsyncCallable asyncCallable, Executor executor) {
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(asyncCallable);
        executor.execute(create);
        return create;
    }

    public static ListenableFuture successfulAsList(Iterable iterable) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(iterable), false);
    }

    public static ListenableFuture transform(ListenableFuture listenableFuture, Function function, Executor executor) {
        return AbstractTransformFuture.create(listenableFuture, function, executor);
    }

    public static ListenableFuture transformAsync(ListenableFuture listenableFuture, AsyncFunction asyncFunction, Executor executor) {
        return AbstractTransformFuture.create(listenableFuture, asyncFunction, executor);
    }

    public static FutureCombiner whenAllComplete(Iterable iterable) {
        return new FutureCombiner(false, ImmutableList.copyOf(iterable));
    }

    public static FutureCombiner whenAllSucceed(Iterable iterable) {
        return new FutureCombiner(true, ImmutableList.copyOf(iterable));
    }

    public static ListenableFuture submit(Runnable runnable, Executor executor) {
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(runnable, null);
        executor.execute(create);
        return create;
    }

    @SafeVarargs
    public static FutureCombiner whenAllComplete(ListenableFuture... listenableFutureArr) {
        return new FutureCombiner(false, ImmutableList.copyOf(listenableFutureArr));
    }

    public static ListenableFuture submit(Callable callable, Executor executor) {
        TrustedListenableFutureTask create = TrustedListenableFutureTask.create(callable);
        executor.execute(create);
        return create;
    }
}
