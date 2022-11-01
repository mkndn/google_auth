package com.google.android.libraries.performance.primes;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashOnBadPrimesConfiguration_Factory implements Factory {
    private final Provider contextProvider;

    public CrashOnBadPrimesConfiguration_Factory(Provider provider) {
        this.contextProvider = provider;
    }

    public static CrashOnBadPrimesConfiguration_Factory create(Provider provider) {
        return new CrashOnBadPrimesConfiguration_Factory(provider);
    }

    public static CrashOnBadPrimesConfiguration newInstance(Context context) {
        return new CrashOnBadPrimesConfiguration(context);
    }

    @Override // javax.inject.Provider
    public CrashOnBadPrimesConfiguration get() {
        return newInstance((Context) this.contextProvider.get());
    }
}
