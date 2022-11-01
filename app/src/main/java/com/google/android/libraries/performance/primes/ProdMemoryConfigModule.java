package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.common.base.Optional;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ProdMemoryConfigModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static MemoryConfigurations provideMemoryConfigurations(Optional optional) {
        MemoryConfigurations memoryConfigurations = (MemoryConfigurations) ((Provider) optional.or(ProdMemoryConfigModule$$ExternalSyntheticLambda0.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? memoryConfigurations : TestingInstrumentationHolder.instance.instrument(memoryConfigurations);
    }
}
