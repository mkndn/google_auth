package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.Lazy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class TimerMetricServiceImpl extends TimerMetricService implements MetricService, CustomDurationMetricService {
    private final Executor deferredExecutor;
    final ConcurrentHashMap globalTimerEvents = new ConcurrentHashMap();
    private final MetricRecorder metricRecorder;
    private final Supplier sampler;
    private final Lazy timerConfigurations;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/timer/TimerMetricServiceImpl");
    static final ImmutableSet RESERVED_EVENT_NAMES = ImmutableSet.of((Object) "Cold startup", (Object) "Cold startup interactive", (Object) "Cold startup interactive before onDraw", (Object) "Warm startup", (Object) "Warm startup interactive", (Object) "Warm startup interactive before onDraw", (Object[]) new String[]{"Warm startup activity onStart", "Cold startup class loading", "Cold startup from process creation", "Cold startup interactive before onDraw from process creation", "Cold startup interactive from process creation"});

    @Inject
    public TimerMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Executor executor, final Lazy lazy, Provider provider, final ProbabilitySamplerFactory probabilitySamplerFactory) {
        this.metricRecorder = metricRecorderFactory.create(MoreExecutors.directExecutor(), lazy, provider);
        this.deferredExecutor = executor;
        this.timerConfigurations = lazy;
        this.sampler = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return TimerMetricServiceImpl.lambda$new$0(ProbabilitySamplerFactory.this, lazy);
            }
        });
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public /* synthetic */ void onApplicationStartup() {
        MetricService.CC.$default$onApplicationStartup(this);
    }

    public static /* synthetic */ ProbabilitySampler lambda$new$0(ProbabilitySamplerFactory probabilitySamplerFactory, Lazy lazy) {
        return probabilitySamplerFactory.create(((TimerConfigurations) lazy.get()).getSamplingProbability());
    }
}
