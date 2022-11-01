package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.trace.TimerMetricServiceSupport;
import com.google.common.base.Optional;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
final class TimerMetricServiceWithTracingImpl extends TimerMetricService implements MetricService, CustomDurationMetricService {
    private static final Callable EMPTY_CALLABLE = TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0.INSTANCE;
    private final TimerMetricServiceImpl timerMetricService;
    private final TimerMetricServiceSupport traceMetricService;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public TimerMetricServiceWithTracingImpl(TimerMetricServiceImpl timerMetricServiceImpl, Optional optional) {
        this.timerMetricService = timerMetricServiceImpl;
        this.traceMetricService = (TimerMetricServiceSupport) ((Provider) optional.get()).get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$static$0() {
        return null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public /* synthetic */ void onApplicationStartup() {
        MetricService.CC.$default$onApplicationStartup(this);
    }
}
