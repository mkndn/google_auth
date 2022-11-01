package com.google.android.libraries.performance.primes.metrics.memory;

import android.app.Activity;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class MemoryMetricMonitor {
    private static final Callback NOOP_CALLBACK = MemoryMetricMonitor$$ExternalSyntheticLambda0.INSTANCE;
    private volatile Callback callback = NOOP_CALLBACK;
    private ScheduledFuture futureMemoryBackgroundTask;
    private ScheduledFuture futureMemoryForegroundTask;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements AppLifecycleListener.OnAppToBackground {
        final /* synthetic */ ListeningScheduledExecutorService val$executorService;

        AnonymousClass1(ListeningScheduledExecutorService listeningScheduledExecutorService) {
            this.val$executorService = listeningScheduledExecutorService;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onAppToBackground$0$com-google-android-libraries-performance-primes-metrics-memory-MemoryMetricMonitor$1  reason: not valid java name */
        public /* synthetic */ void m343xdf4b32a0(String str) {
            MemoryMetricMonitor.this.callback.onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_IN_BACKGROUND_FOR_SECONDS, str);
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
        public void onAppToBackground(Activity activity) {
            final String simpleName = activity.getClass().getSimpleName();
            MemoryMetricMonitor.this.callback.onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_TO_BACKGROUND, simpleName);
            MemoryMetricMonitor.this.cancelFutureTasksIfAny();
            MemoryMetricMonitor.this.futureMemoryBackgroundTask = this.val$executorService.schedule(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MemoryMetricMonitor.AnonymousClass1.this.m343xdf4b32a0(simpleName);
                }
            }, 10L, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 implements AppLifecycleListener.OnAppToForeground {
        final /* synthetic */ ListeningScheduledExecutorService val$executorService;

        AnonymousClass2(ListeningScheduledExecutorService listeningScheduledExecutorService) {
            this.val$executorService = listeningScheduledExecutorService;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$onAppToForeground$0$com-google-android-libraries-performance-primes-metrics-memory-MemoryMetricMonitor$2  reason: not valid java name */
        public /* synthetic */ void m344x62dd7eec(String str) {
            MemoryMetricMonitor.this.callback.onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_IN_FOREGROUND_FOR_SECONDS, str);
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToForeground
        public void onAppToForeground(Activity activity) {
            final String simpleName = activity.getClass().getSimpleName();
            MemoryMetricMonitor.this.callback.onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode.APP_TO_FOREGROUND, simpleName);
            MemoryMetricMonitor.this.cancelFutureTasksIfAny();
            MemoryMetricMonitor.this.futureMemoryForegroundTask = this.val$executorService.schedule(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MemoryMetricMonitor.AnonymousClass2.this.m344x62dd7eec(simpleName);
                }
            }, 10L, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MemoryMetricMonitor(AppLifecycleMonitor appLifecycleMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService) {
        appLifecycleMonitor.register(new AnonymousClass1(listeningScheduledExecutorService));
        appLifecycleMonitor.register(new AnonymousClass2(listeningScheduledExecutorService));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelFutureTasksIfAny() {
        ScheduledFuture scheduledFuture = this.futureMemoryForegroundTask;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.futureMemoryForegroundTask = null;
        }
        ScheduledFuture scheduledFuture2 = this.futureMemoryBackgroundTask;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(true);
            this.futureMemoryBackgroundTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$0(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(Callback callback) {
        this.callback = callback;
    }
}
