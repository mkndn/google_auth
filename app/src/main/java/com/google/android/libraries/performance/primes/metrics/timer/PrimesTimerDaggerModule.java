package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesTimerDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static TimerMetricService metricService(Optional optional, Optional optional2, Provider provider, Provider provider2) {
        if (optional.isPresent()) {
            return (TimerMetricService) (optional2.isPresent() ? provider.get() : provider2.get());
        }
        return new TimerMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule.1
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CustomDurationMetricService provideCustomDurationMetricService(Optional optional, Optional optional2, Provider provider, Provider provider2) {
        if (optional.isPresent()) {
            return (CustomDurationMetricService) (optional2.isPresent() ? provider.get() : provider2.get());
        }
        return new CustomDurationMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule.2
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set timerService(Optional optional, Optional optional2, Optional optional3, Provider provider, Provider provider2) {
        MetricService metricService;
        if (optional.isPresent()) {
            if (optional2.isPresent() && optional3.isPresent()) {
                metricService = (MetricService) provider2.get();
            } else {
                metricService = (MetricService) provider.get();
            }
            return ImmutableSet.of((Object) metricService);
        }
        return ImmutableSet.of();
    }
}
