package com.google.android.libraries.performance.primes.lifecycle;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleMonitor_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider trackerProvider;

    public AppLifecycleMonitor_Factory(Provider provider, Provider provider2) {
        this.applicationProvider = provider;
        this.trackerProvider = provider2;
    }

    public static AppLifecycleMonitor_Factory create(Provider provider, Provider provider2) {
        return new AppLifecycleMonitor_Factory(provider, provider2);
    }

    public static AppLifecycleMonitor newInstance(Context context, AppLifecycleTracker appLifecycleTracker) {
        return new AppLifecycleMonitor(context, appLifecycleTracker);
    }

    @Override // javax.inject.Provider
    public AppLifecycleMonitor get() {
        return newInstance((Context) this.applicationProvider.get(), (AppLifecycleTracker) this.trackerProvider.get());
    }
}
