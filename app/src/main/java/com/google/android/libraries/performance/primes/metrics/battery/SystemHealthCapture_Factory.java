package com.google.android.libraries.performance.primes.metrics.battery;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthCapture_Factory implements Factory {
    private final Provider applicationContextProvider;

    public SystemHealthCapture_Factory(Provider provider) {
        this.applicationContextProvider = provider;
    }

    public static SystemHealthCapture_Factory create(Provider provider) {
        return new SystemHealthCapture_Factory(provider);
    }

    public static SystemHealthCapture newInstance(Context context) {
        return new SystemHealthCapture(context);
    }

    @Override // javax.inject.Provider
    public SystemHealthCapture get() {
        return newInstance((Context) this.applicationContextProvider.get());
    }
}
