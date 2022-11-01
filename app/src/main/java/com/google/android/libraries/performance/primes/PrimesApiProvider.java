package com.google.android.libraries.performance.primes;

import android.app.Application;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.ProdInternalComponent;
import com.google.common.base.Preconditions;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApiProvider {
    public static PrimesApiProviderBuilder newBuilder(Application application) {
        return new PrimesApiProviderBuilder(application, ProdInternalComponent.CC.builder());
    }

    public static Primes.PrimesProvider newInstance(Application application, Provider provider) {
        return newInstance(application, provider, PrimesThreadsConfigurations.newBuilder().build());
    }

    public static Primes.PrimesProvider newInstance(Application application, Provider provider, PrimesThreadsConfigurations primesThreadsConfigurations) {
        return newBuilder((Application) Preconditions.checkNotNull(application)).setConfigurationsProvider((Provider) Preconditions.checkNotNull(provider)).setThreadsConfigurations((PrimesThreadsConfigurations) Preconditions.checkNotNull(primesThreadsConfigurations)).build();
    }
}
