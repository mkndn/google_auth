package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Application;
import android.content.Context;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public class AppLifecycleMonitor {
    private final AppLifecycleTracker tracker;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public AppLifecycleMonitor(Context context, AppLifecycleTracker appLifecycleTracker) {
        this.tracker = appLifecycleTracker;
        appLifecycleTracker.attachToApp((Application) context);
    }

    public int getActivityResumedCount() {
        return this.tracker.getActivityResumedCount();
    }

    public void register(AppLifecycleListener appLifecycleListener) {
        this.tracker.register(appLifecycleListener);
    }

    public void unregister(AppLifecycleListener appLifecycleListener) {
        this.tracker.unregister(appLifecycleListener);
    }
}
