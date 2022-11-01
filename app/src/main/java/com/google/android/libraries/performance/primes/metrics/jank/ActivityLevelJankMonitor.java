package com.google.android.libraries.performance.primes.metrics.jank;

import android.app.Activity;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class ActivityLevelJankMonitor implements AppLifecycleListener.OnActivityResumed, AppLifecycleListener.OnActivityPaused {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/jank/ActivityLevelJankMonitor");
    private Activity currentActivity;
    private boolean enabled = false;
    private final Lazy frameMetricService;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public ActivityLevelJankMonitor(Lazy lazy, final Provider provider, final Optional optional, Executor executor) {
        this.frameMetricService = lazy;
        executor.execute(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.jank.ActivityLevelJankMonitor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityLevelJankMonitor.this.m334x22d674a9(provider, optional);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-jank-ActivityLevelJankMonitor  reason: not valid java name */
    public /* synthetic */ void m334x22d674a9(Provider provider, Optional optional) {
        if (((Boolean) provider.get()).booleanValue()) {
            if (optional.isPresent() && !((Boolean) ((Provider) optional.get()).get()).booleanValue()) {
                return;
            }
        } else if (!optional.isPresent() || !((Boolean) ((Provider) optional.get()).get()).booleanValue()) {
            return;
        }
        synchronized (this) {
            this.enabled = true;
            Activity activity = this.currentActivity;
            if (activity != null) {
                onActivityResumed(activity);
            }
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityPaused
    public synchronized void onActivityPaused(Activity activity) {
        if (!activity.equals(this.currentActivity)) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/ActivityLevelJankMonitor", "onActivityPaused", 90, "ActivityLevelJankMonitor.java")).log("Activity mismatch (currentActivity=%s, activity=%s)", this.currentActivity, activity);
        }
        if (this.enabled) {
            PrimesExecutors.logFailures(((FrameMetricServiceImpl) this.frameMetricService.get()).stopAsFuture(activity));
        }
        this.currentActivity = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityResumed
    public synchronized void onActivityResumed(Activity activity) {
        this.currentActivity = activity;
        if (this.enabled) {
            ((FrameMetricServiceImpl) this.frameMetricService.get()).start(activity);
        }
    }
}
