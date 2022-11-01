package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class DeferrableExecutor implements Executor {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/DeferrableExecutor");
    private final ListeningScheduledExecutorService executor;
    private final ConcurrentLinkedQueue tasks = new ConcurrentLinkedQueue();
    private volatile boolean canExecute = false;
    private final AtomicBoolean maxDelayScheduled = new AtomicBoolean();

    public static /* synthetic */ void $r8$lambda$8CjPfsBOgnaocxSVCp1hqndSe80(DeferrableExecutor deferrableExecutor) {
        deferrableExecutor.onFirstResume();
    }

    public static /* synthetic */ Void $r8$lambda$ArlahOZCoGroe95FjN8k3JQMfEw(DeferrableExecutor deferrableExecutor) {
        return deferrableExecutor.unblockAfterMaxDelay();
    }

    /* renamed from: $r8$lambda$JhF7akC579kCuIuIhbH4--U4Owg */
    public static /* synthetic */ Void m280$r8$lambda$JhF7akC579kCuIuIhbH4U4Owg(DeferrableExecutor deferrableExecutor) {
        return deferrableExecutor.unblockAfterResume();
    }

    @Inject
    public DeferrableExecutor(ListeningScheduledExecutorService listeningScheduledExecutorService, AppLifecycleMonitor appLifecycleMonitor) {
        this.executor = listeningScheduledExecutorService;
        PrimesExecutors.onActivityResumedTrigger(appLifecycleMonitor).execute(new Runnable() { // from class: com.google.android.libraries.performance.primes.DeferrableExecutor$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                DeferrableExecutor.$r8$lambda$8CjPfsBOgnaocxSVCp1hqndSe80(DeferrableExecutor.this);
            }
        });
    }

    private void drainPending() {
        while (true) {
            Runnable runnable = (Runnable) this.tasks.poll();
            if (runnable != null) {
                this.executor.execute(runnable);
            } else {
                return;
            }
        }
    }

    private void maybeDrainPending() {
        if (this.canExecute) {
            drainPending();
        } else if (!this.maxDelayScheduled.getAndSet(true)) {
            PrimesExecutors.logFailures(this.executor.schedule(new Callable() { // from class: com.google.android.libraries.performance.primes.DeferrableExecutor$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return DeferrableExecutor.$r8$lambda$ArlahOZCoGroe95FjN8k3JQMfEw(DeferrableExecutor.this);
                }
            }, 7000L, TimeUnit.MILLISECONDS));
        }
    }

    public void onFirstResume() {
        PrimesExecutors.logFailures(this.executor.schedule(new Callable() { // from class: com.google.android.libraries.performance.primes.DeferrableExecutor$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeferrableExecutor.m280$r8$lambda$JhF7akC579kCuIuIhbH4U4Owg(DeferrableExecutor.this);
            }
        }, 3000L, TimeUnit.MILLISECONDS));
    }

    private void unblock() {
        this.canExecute = true;
        drainPending();
    }

    public Void unblockAfterMaxDelay() {
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/DeferrableExecutor", "unblockAfterMaxDelay", 79, "DeferrableExecutor.java")).log("DeferrableExecutor unblocked after max task delay");
        unblock();
        return null;
    }

    public Void unblockAfterResume() {
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/DeferrableExecutor", "unblockAfterResume", 85, "DeferrableExecutor.java")).log("DeferrableExecutor unblocked after onResume");
        unblock();
        return null;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        if (this.canExecute) {
            this.executor.execute(runnable);
            return;
        }
        this.tasks.add(runnable);
        maybeDrainPending();
    }
}
