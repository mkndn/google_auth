package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.ProdInternalComponent;
import com.google.common.base.Supplier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProdInternalComponent_NonDaggerInternalModule_ProvideMetricTransmittersFactory implements Factory {
    private final Provider metricTransmittersSupplierProvider;

    public ProdInternalComponent_NonDaggerInternalModule_ProvideMetricTransmittersFactory(Provider provider) {
        this.metricTransmittersSupplierProvider = provider;
    }

    public static ProdInternalComponent_NonDaggerInternalModule_ProvideMetricTransmittersFactory create(Provider provider) {
        return new ProdInternalComponent_NonDaggerInternalModule_ProvideMetricTransmittersFactory(provider);
    }

    public static Set provideMetricTransmitters(Supplier supplier) {
        return (Set) Preconditions.checkNotNullFromProvides(ProdInternalComponent.NonDaggerInternalModule.provideMetricTransmitters(supplier));
    }

    @Override // javax.inject.Provider
    public Set get() {
        return provideMetricTransmitters((Supplier) this.metricTransmittersSupplierProvider.get());
    }
}
