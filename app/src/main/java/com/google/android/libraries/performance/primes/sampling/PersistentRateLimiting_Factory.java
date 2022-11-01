package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import com.google.android.libraries.clock.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PersistentRateLimiting_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider clockProvider;
    private final Provider sharedPrefsProvider;

    public PersistentRateLimiting_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.applicationProvider = provider;
        this.clockProvider = provider2;
        this.sharedPrefsProvider = provider3;
    }

    public static PersistentRateLimiting_Factory create(Provider provider, Provider provider2, Provider provider3) {
        return new PersistentRateLimiting_Factory(provider, provider2, provider3);
    }

    public static PersistentRateLimiting newInstance(Context context, Clock clock, Provider provider) {
        return new PersistentRateLimiting(context, clock, provider);
    }

    @Override // javax.inject.Provider
    public PersistentRateLimiting get() {
        return newInstance((Context) this.applicationProvider.get(), (Clock) this.clockProvider.get(), this.sharedPrefsProvider);
    }
}
