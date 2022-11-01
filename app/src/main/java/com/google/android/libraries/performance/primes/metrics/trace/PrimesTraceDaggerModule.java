package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesTraceDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static TraceMetricService metricService(Optional optional, Optional optional2, Provider provider) {
        if (!optional.isPresent() && !optional2.isPresent()) {
            return new TraceMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule.1
            };
        }
        return (TraceMetricService) provider.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Optional timerMetricServiceSupport(Optional optional, Optional optional2, final Provider provider) {
        if (!optional.isPresent() && !optional2.isPresent()) {
            return Optional.absent();
        }
        provider.getClass();
        return Optional.of(new Provider() { // from class: com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule$$ExternalSyntheticLambda0
            @Override // javax.inject.Provider
            public final Object get() {
                return (TimerMetricServiceSupport) Provider.this.get();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set traceService(Optional optional, Optional optional2, Provider provider) {
        if (!optional.isPresent() && !optional2.isPresent()) {
            return ImmutableSet.of();
        }
        return ImmutableSet.of((Object) ((MetricService) provider.get()));
    }
}
