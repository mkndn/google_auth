package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideSharedPreferencesFactory implements Factory {
    private final Provider contextProvider;
    private final Provider sharedPreferencesSupplierProvider;

    public ConfigurationsModule_ProvideSharedPreferencesFactory(Provider provider, Provider provider2) {
        this.contextProvider = provider;
        this.sharedPreferencesSupplierProvider = provider2;
    }

    public static ConfigurationsModule_ProvideSharedPreferencesFactory create(Provider provider, Provider provider2) {
        return new ConfigurationsModule_ProvideSharedPreferencesFactory(provider, provider2);
    }

    public static SharedPreferences provideSharedPreferences(Context context, Optional optional) {
        return (SharedPreferences) Preconditions.checkNotNullFromProvides(ConfigurationsModule.provideSharedPreferences(context, optional));
    }

    @Override // javax.inject.Provider
    public SharedPreferences get() {
        return provideSharedPreferences((Context) this.contextProvider.get(), (Optional) this.sharedPreferencesSupplierProvider.get());
    }
}
