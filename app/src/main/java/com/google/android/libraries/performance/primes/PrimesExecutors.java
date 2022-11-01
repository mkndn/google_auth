package com.google.android.libraries.performance.primes;

import android.app.Activity;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesExecutors {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/PrimesExecutors");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DefaultFailureCallback implements FutureCallback {
        static final DefaultFailureCallback INSTANCE = new DefaultFailureCallback();

        private DefaultFailureCallback() {
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof CancellationException) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesExecutors.logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesExecutors$DefaultFailureCallback", "onFailure", 99, "PrimesExecutors.java")).log("Background task cancelled");
            } else {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesExecutors.logger.atWarning()).withCause(th)).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesExecutors$DefaultFailureCallback", "onFailure", 102, "PrimesExecutors.java")).log("Background task failed");
            }
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public void onSuccess(Void r1) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OnResumeListener implements Executor, AppLifecycleListener.OnActivityResumed {
        private boolean done;
        private final AppLifecycleMonitor lifecycleMonitor;
        private boolean resumed;
        private Runnable task;

        OnResumeListener(AppLifecycleMonitor appLifecycleMonitor) {
            this.lifecycleMonitor = appLifecycleMonitor;
        }

        private void runTask(Runnable runnable) {
            if (this.done) {
                return;
            }
            this.done = true;
            runnable.run();
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            synchronized (this) {
                if (!this.resumed && this.lifecycleMonitor.getActivityResumedCount() <= 0) {
                    this.task = runnable;
                }
                runTask(runnable);
            }
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityResumed
        public void onActivityResumed(Activity activity) {
            this.lifecycleMonitor.unregister(this);
            synchronized (this) {
                Runnable runnable = this.task;
                if (runnable != null) {
                    runTask(runnable);
                    this.task = null;
                } else {
                    this.resumed = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$logFailuresOfNonVoidFuture$0(Object obj) {
        return null;
    }

    public static void logFailures(ListenableFuture listenableFuture) {
        Futures.addCallback(listenableFuture, DefaultFailureCallback.INSTANCE, MoreExecutors.directExecutor());
    }

    public static void logFailuresOfNonVoidFuture(ListenableFuture listenableFuture) {
        logFailures(Futures.transform(listenableFuture, PrimesExecutors$$ExternalSyntheticLambda0.INSTANCE, MoreExecutors.directExecutor()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Executor onActivityResumedTrigger(AppLifecycleMonitor appLifecycleMonitor) {
        OnResumeListener onResumeListener = new OnResumeListener(appLifecycleMonitor);
        appLifecycleMonitor.register(onResumeListener);
        return onResumeListener;
    }
}
