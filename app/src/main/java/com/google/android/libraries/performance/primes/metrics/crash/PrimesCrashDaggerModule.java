package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesCrashDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set crashService(Optional optional, Provider provider) {
        return optional.isPresent() ? ImmutableSet.of((Object) ((MetricService) provider.get())) : ImmutableSet.of();
    }
}
