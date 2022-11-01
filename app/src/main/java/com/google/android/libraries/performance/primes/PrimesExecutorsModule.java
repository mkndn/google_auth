package com.google.android.libraries.performance.primes;

import android.os.Process;
import com.google.android.libraries.concurrent.ExecutorDecorator;
import com.google.android.libraries.performance.primes.PrimesExecutorsModule;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesExecutorsModule {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/PrimesExecutorsModule");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {
        private DefaultRejectedExecutionHandler() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesExecutorsModule.logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesExecutorsModule$DefaultRejectedExecutionHandler", "rejectedExecution", 64, "PrimesExecutorsModule.java")).log("Service rejected execution of %s", runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static Executor provideDeferrableExecutor(Provider provider, Provider provider2, PrimesThreadsConfigurations primesThreadsConfigurations) {
        if (primesThreadsConfigurations.getEnableDeferredTasks()) {
            return (Executor) provider.get();
        }
        return (Executor) provider2.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PrimesThreadFactory implements ThreadFactory {
        private final AtomicInteger count;
        private final String prefix;
        private final int priority;

        PrimesThreadFactory(int i) {
            this("Primes", i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$newThread$0$com-google-android-libraries-performance-primes-PrimesExecutorsModule$PrimesThreadFactory  reason: not valid java name */
        public /* synthetic */ void m283xf634a4d3(Runnable runnable) {
            int i = this.priority;
            if (i != 0) {
                Process.setThreadPriority(i);
            }
            runnable.run();
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(final Runnable runnable) {
            Runnable runnable2 = new Runnable() { // from class: com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PrimesExecutorsModule.PrimesThreadFactory.this.m283xf634a4d3(runnable);
                }
            };
            String str = this.prefix;
            Thread thread = new Thread(runnable2, str + "-" + this.count.getAndIncrement());
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }

        PrimesThreadFactory(String str, int i) {
            this.count = new AtomicInteger(1);
            this.priority = i;
            this.prefix = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static ListeningScheduledExecutorService provideListeningScheduledExecutorService(PrimesThreadsConfigurations primesThreadsConfigurations, ExecutorDecorator executorDecorator) {
        ListeningScheduledExecutorService primesExecutorService = primesThreadsConfigurations.getPrimesExecutorService();
        if (primesExecutorService == null) {
            int primesMetricExecutorPoolSize = primesThreadsConfigurations.getPrimesMetricExecutorPoolSize();
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(primesMetricExecutorPoolSize, new PrimesThreadFactory(primesThreadsConfigurations.getPrimesMetricExecutorPriority()), new DefaultRejectedExecutionHandler());
            scheduledThreadPoolExecutor.setMaximumPoolSize(primesMetricExecutorPoolSize);
            return executorDecorator.decorate(MoreExecutors.listeningDecorator(scheduledThreadPoolExecutor));
        }
        return primesExecutorService;
    }
}
