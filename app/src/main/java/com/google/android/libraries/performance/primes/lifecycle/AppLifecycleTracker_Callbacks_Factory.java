package com.google.android.libraries.performance.primes.lifecycle;

import com.google.android.libraries.performance.primes.CrashOnBadPrimesConfiguration;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleTracker_Callbacks_Factory implements Factory {
    private final Provider crashOnBadPrimesConfigurationProvider;

    public AppLifecycleTracker_Callbacks_Factory(Provider provider) {
        this.crashOnBadPrimesConfigurationProvider = provider;
    }

    public static AppLifecycleTracker_Callbacks_Factory create(Provider provider) {
        return new AppLifecycleTracker_Callbacks_Factory(provider);
    }

    public static AppLifecycleTracker.Callbacks newInstance(CrashOnBadPrimesConfiguration crashOnBadPrimesConfiguration) {
        return new AppLifecycleTracker.Callbacks(crashOnBadPrimesConfiguration);
    }

    @Override // javax.inject.Provider
    public AppLifecycleTracker.Callbacks get() {
        return newInstance((CrashOnBadPrimesConfiguration) this.crashOnBadPrimesConfigurationProvider.get());
    }
}
