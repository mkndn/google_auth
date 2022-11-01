package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesNetworkDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set networkMetricService(Optional optional, NetworkMetricServiceImpl networkMetricServiceImpl) {
        if (optional.isPresent()) {
            return ImmutableSet.of((Object) networkMetricServiceImpl);
        }
        return ImmutableSet.of();
    }
}
