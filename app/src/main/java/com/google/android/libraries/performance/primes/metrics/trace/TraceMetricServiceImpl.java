package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import com.google.android.libraries.performance.primes.sampling.RateLimiting;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class TraceMetricServiceImpl extends TraceMetricService implements MetricService, TimerMetricServiceSupport {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/trace/TraceMetricServiceImpl");
    private final ListeningScheduledExecutorService executorService;
    private final MetricRecorder metricRecorder;
    private final AtomicReference nonTikTokSampler;
    private final ProbabilitySamplerFactory probabilitySamplerFactory;
    private final RateLimiting rateLimiter;
    private final Lazy tikTokTraceConfigurationsProvider;
    private final Lazy traceConfigurationsProvider;

    @Inject
    public TraceMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, ListeningScheduledExecutorService listeningScheduledExecutorService, final Lazy lazy, Lazy lazy2, Provider provider, ProbabilitySamplerFactory probabilitySamplerFactory) {
        AtomicReference atomicReference = new AtomicReference();
        this.nonTikTokSampler = atomicReference;
        this.executorService = listeningScheduledExecutorService;
        this.tikTokTraceConfigurationsProvider = lazy;
        this.traceConfigurationsProvider = lazy2;
        this.probabilitySamplerFactory = probabilitySamplerFactory;
        this.metricRecorder = metricRecorderFactory.create(listeningScheduledExecutorService, TraceMetricServiceImpl$$ExternalSyntheticLambda3.INSTANCE, provider);
        this.rateLimiter = RateLimiting.dynamic(new Provider() { // from class: com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl$$ExternalSyntheticLambda4
            @Override // javax.inject.Provider
            public final Object get() {
                return TraceMetricServiceImpl.lambda$new$1(Lazy.this);
            }
        });
        atomicReference.set(probabilitySamplerFactory.create(1.0f));
    }

    public static /* synthetic */ Integer lambda$new$1(Lazy lazy) {
        int i;
        if (!((TikTokTraceConfigurations) lazy.get()).isEnabled()) {
            i = 10;
        } else {
            i = ((TikTokTraceConfigurations) lazy.get()).getRateLimitPerSecond();
        }
        return Integer.valueOf(i);
    }

    /* renamed from: lambda$onApplicationStartup$2$com-google-android-libraries-performance-primes-metrics-trace-TraceMetricServiceImpl */
    public /* synthetic */ void m356x19c86de1() {
        float f;
        try {
            AtomicReference atomicReference = this.nonTikTokSampler;
            ProbabilitySamplerFactory probabilitySamplerFactory = this.probabilitySamplerFactory;
            if (((TraceConfigurations) this.traceConfigurationsProvider.get()).isEnabled()) {
                f = ((TraceConfigurations) this.traceConfigurationsProvider.get()).getSamplingProbability();
            } else {
                f = 0.0f;
            }
            atomicReference.set(probabilitySamplerFactory.create(f));
        } catch (Throwable th) {
            this.nonTikTokSampler.set(this.probabilitySamplerFactory.create(0.0f));
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        Futures.submit(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TraceMetricServiceImpl.this.m356x19c86de1();
            }
        }, this.executorService);
    }
}
