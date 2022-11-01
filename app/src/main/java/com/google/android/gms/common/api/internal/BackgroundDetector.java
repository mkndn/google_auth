package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final BackgroundDetector sInstance = new BackgroundDetector();
    private final AtomicBoolean mBackground = new AtomicBoolean();
    private final AtomicBoolean mStateKnown = new AtomicBoolean();
    private final ArrayList mListeners = new ArrayList();
    private boolean mInitialized = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean z);
    }

    private BackgroundDetector() {
    }

    public static BackgroundDetector getInstance() {
        return sInstance;
    }

    public static void initialize(Application application) {
        BackgroundDetector backgroundDetector = sInstance;
        synchronized (backgroundDetector) {
            if (!backgroundDetector.mInitialized) {
                application.registerActivityLifecycleCallbacks(backgroundDetector);
                application.registerComponentCallbacks(backgroundDetector);
                backgroundDetector.mInitialized = true;
            }
        }
    }

    private void onBackgroundStateChanged(boolean z) {
        synchronized (sInstance) {
            Iterator it = this.mListeners.iterator();
            while (it.hasNext()) {
                ((BackgroundStateChangeListener) it.next()).onBackgroundStateChanged(z);
            }
        }
    }

    public void addListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        synchronized (sInstance) {
            this.mListeners.add(backgroundStateChangeListener);
        }
    }

    public boolean isInBackground() {
        return this.mBackground.get();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.mBackground.compareAndSet(true, false);
        this.mStateKnown.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.mBackground.compareAndSet(true, false);
        this.mStateKnown.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 20 && this.mBackground.compareAndSet(false, true)) {
            this.mStateKnown.set(true);
            onBackgroundStateChanged(true);
        }
    }

    public boolean readCurrentStateIfPossible(boolean z) {
        if (!this.mStateKnown.get()) {
            if (PlatformVersion.isAtLeastJellyBean()) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                ActivityManager.getMyMemoryState(runningAppProcessInfo);
                if (!this.mStateKnown.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                    this.mBackground.set(true);
                }
            } else {
                return z;
            }
        }
        return isInBackground();
    }
}
