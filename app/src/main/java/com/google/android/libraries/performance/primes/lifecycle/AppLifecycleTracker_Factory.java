package com.google.android.libraries.performance.primes.lifecycle;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleTracker_Factory implements Factory {
    private final Provider callbacksProvider;

    public AppLifecycleTracker_Factory(Provider provider) {
        this.callbacksProvider = provider;
    }

    public static AppLifecycleTracker_Factory create(Provider provider) {
        return new AppLifecycleTracker_Factory(provider);
    }

    public static AppLifecycleTracker newInstance(Object obj) {
        return new AppLifecycleTracker((AppLifecycleTracker.Callbacks) obj);
    }

    @Override // javax.inject.Provider
    public AppLifecycleTracker get() {
        return newInstance(this.callbacksProvider.get());
    }
}
