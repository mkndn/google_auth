package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.libraries.performance.primes.CrashOnBadPrimesConfiguration;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class AppLifecycleTracker {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/lifecycle/AppLifecycleTracker");
    private final Callbacks callbacks;

    /* compiled from: PG */
    @Singleton
    /* loaded from: classes.dex */
    final class Callbacks implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
        private final CrashOnBadPrimesConfiguration crashOnBadPrimesConfiguration;
        private Boolean lastForegroundState;
        private volatile String nameOfForegroundActivity;
        private volatile Activity stoppedActivity;
        private final List lifecycleListeners = new CopyOnWriteArrayList();
        private final AtomicInteger createdCount = new AtomicInteger();
        private final AtomicInteger startedCount = new AtomicInteger();
        private final AtomicInteger resumedCount = new AtomicInteger();
        private final AtomicInteger pausedCount = new AtomicInteger();
        private final AtomicInteger stoppedCount = new AtomicInteger();
        private final AtomicInteger destroyedCount = new AtomicInteger();
        private boolean didCheck = false;

        /* JADX INFO: Access modifiers changed from: package-private */
        @Inject
        public Callbacks(CrashOnBadPrimesConfiguration crashOnBadPrimesConfiguration) {
            this.crashOnBadPrimesConfiguration = crashOnBadPrimesConfiguration;
        }

        private void checkVisibilityAndNotifyListeners(Activity activity) {
            setForegroundState(ProcessStats.isAppInForeground(activity.getApplicationContext()), activity);
        }

        private void crashIfNotAllowlistedAndOnCreateDidNotComeFirst() {
            if (this.didCheck || this.createdCount.get() != 0) {
                return;
            }
            this.crashOnBadPrimesConfiguration.observedOutOfOrderLifecycleEvent();
            this.didCheck = true;
        }

        private void ensureToBackground(Activity activity) {
            setForegroundState(false, activity);
        }

        private void setForegroundState(boolean z, Activity activity) {
            Boolean bool = this.lastForegroundState;
            if (bool != null && bool.booleanValue() == z) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) AppLifecycleTracker.logger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/lifecycle/AppLifecycleTracker$Callbacks", "setForegroundState", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_HEADLESS_DPC_CLOUD_API_RUN_DEVICE_COMMANDS_VALUE, "AppLifecycleTracker.java")).log("App foreground state unchanged: inForeground ? %b", Boolean.valueOf(z));
                return;
            }
            this.lastForegroundState = Boolean.valueOf(z);
            if (z) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) AppLifecycleTracker.logger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/lifecycle/AppLifecycleTracker$Callbacks", "setForegroundState", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_PRODUCT_IMPROVEMENT_VALUE, "AppLifecycleTracker.java")).log("App transition to foreground");
                for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                    if (appLifecycleListener instanceof AppLifecycleListener.OnAppToForeground) {
                        ((AppLifecycleListener.OnAppToForeground) appLifecycleListener).onAppToForeground(activity);
                    }
                }
                return;
            }
            ((GoogleLogger.Api) ((GoogleLogger.Api) AppLifecycleTracker.logger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/lifecycle/AppLifecycleTracker$Callbacks", "setForegroundState", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_PLAY_CONSOLE_MEASURING_USER_ENGAGEMENT_VALUE, "AppLifecycleTracker.java")).log("App transition to background");
            for (AppLifecycleListener appLifecycleListener2 : this.lifecycleListeners) {
                if (appLifecycleListener2 instanceof AppLifecycleListener.OnAppToBackground) {
                    ((AppLifecycleListener.OnAppToBackground) appLifecycleListener2).onAppToBackground(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            this.createdCount.incrementAndGet();
            this.stoppedActivity = null;
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityCreated) {
                    ((AppLifecycleListener.OnActivityCreated) appLifecycleListener).onActivityCreated(activity, bundle);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.destroyedCount.getAndIncrement() == 0) {
                crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
            }
            this.stoppedActivity = null;
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityDestroyed) {
                    ((AppLifecycleListener.OnActivityDestroyed) appLifecycleListener).onActivityDestroyed(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (this.pausedCount.getAndIncrement() == 0) {
                crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
            }
            this.nameOfForegroundActivity = null;
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityPaused) {
                    ((AppLifecycleListener.OnActivityPaused) appLifecycleListener).onActivityPaused(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (this.resumedCount.getAndIncrement() == 0) {
                crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
            }
            this.stoppedActivity = null;
            this.nameOfForegroundActivity = activity.getClass().getSimpleName();
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityResumed) {
                    ((AppLifecycleListener.OnActivityResumed) appLifecycleListener).onActivityResumed(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivitySaveInstanceState) {
                    ((AppLifecycleListener.OnActivitySaveInstanceState) appLifecycleListener).onActivitySaveInstanceState(activity, bundle);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.startedCount.getAndIncrement() == 0) {
                crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
            }
            this.stoppedActivity = null;
            checkVisibilityAndNotifyListeners(activity);
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityStarted) {
                    ((AppLifecycleListener.OnActivityStarted) appLifecycleListener).onActivityStarted(activity);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (this.stoppedCount.getAndIncrement() == 0) {
                crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
            }
            this.stoppedActivity = activity;
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnActivityStopped) {
                    ((AppLifecycleListener.OnActivityStopped) appLifecycleListener).onActivityStopped(activity);
                }
            }
            checkVisibilityAndNotifyListeners(activity);
        }

        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            for (AppLifecycleListener appLifecycleListener : this.lifecycleListeners) {
                if (appLifecycleListener instanceof AppLifecycleListener.OnTrimMemory) {
                    ((AppLifecycleListener.OnTrimMemory) appLifecycleListener).onTrimMemory(i);
                }
            }
            if (i >= 20 && this.stoppedActivity != null) {
                ensureToBackground(this.stoppedActivity);
            }
            this.stoppedActivity = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AppLifecycleTracker(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    public void attachToApp(Application application) {
        application.registerActivityLifecycleCallbacks(this.callbacks);
        application.registerComponentCallbacks(this.callbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getActivityResumedCount() {
        return this.callbacks.resumedCount.get();
    }

    public void register(AppLifecycleListener appLifecycleListener) {
        Preconditions.checkNotNull(appLifecycleListener);
        this.callbacks.lifecycleListeners.add(appLifecycleListener);
    }

    public void unregister(AppLifecycleListener appLifecycleListener) {
        Preconditions.checkNotNull(appLifecycleListener);
        this.callbacks.lifecycleListeners.remove(appLifecycleListener);
    }
}
