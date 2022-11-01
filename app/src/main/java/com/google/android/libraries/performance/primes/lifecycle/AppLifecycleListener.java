package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Activity;
import android.os.Bundle;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AppLifecycleListener {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityCreated extends AppLifecycleListener {
        void onActivityCreated(Activity activity, Bundle bundle);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityDestroyed extends AppLifecycleListener {
        void onActivityDestroyed(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityPaused extends AppLifecycleListener {
        void onActivityPaused(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityResumed extends AppLifecycleListener {
        void onActivityResumed(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivitySaveInstanceState extends AppLifecycleListener {
        void onActivitySaveInstanceState(Activity activity, Bundle bundle);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityStarted extends AppLifecycleListener {
        void onActivityStarted(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnActivityStopped extends AppLifecycleListener {
        void onActivityStopped(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnAppToBackground extends AppLifecycleListener {
        void onAppToBackground(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnAppToForeground extends AppLifecycleListener {
        void onAppToForeground(Activity activity);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnTrimMemory extends AppLifecycleListener {
        void onTrimMemory(int i);
    }
}
