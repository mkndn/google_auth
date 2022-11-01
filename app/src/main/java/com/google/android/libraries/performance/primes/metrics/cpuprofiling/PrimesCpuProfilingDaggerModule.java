package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.os.Build;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesCpuProfilingDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set cpuProfilingService(Optional optional, Provider provider) {
        if (!optional.isPresent()) {
            return ImmutableSet.of();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return ImmutableSet.of((Object) ((MetricService) provider.get()));
        }
        return ImmutableSet.of();
    }
}
