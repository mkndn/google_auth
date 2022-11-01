package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatsStorage_Factory implements Factory {
    private final Provider storageProvider;

    public StatsStorage_Factory(Provider provider) {
        this.storageProvider = provider;
    }

    public static StatsStorage_Factory create(Provider provider) {
        return new StatsStorage_Factory(provider);
    }

    public static StatsStorage newInstance(PersistentStorage persistentStorage) {
        return new StatsStorage(persistentStorage);
    }

    @Override // javax.inject.Provider
    public StatsStorage get() {
        return newInstance((PersistentStorage) this.storageProvider.get());
    }
}
