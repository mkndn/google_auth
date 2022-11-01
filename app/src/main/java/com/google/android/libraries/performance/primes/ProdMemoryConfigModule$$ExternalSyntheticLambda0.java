package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProdMemoryConfigModule$$ExternalSyntheticLambda0 implements Provider {
    public static final /* synthetic */ ProdMemoryConfigModule$$ExternalSyntheticLambda0 INSTANCE = new ProdMemoryConfigModule$$ExternalSyntheticLambda0();

    private /* synthetic */ ProdMemoryConfigModule$$ExternalSyntheticLambda0() {
    }

    @Override // javax.inject.Provider
    public final Object get() {
        MemoryConfigurations build;
        build = MemoryConfigurations.newBuilder().setEnabled(false).build();
        return build;
    }
}
