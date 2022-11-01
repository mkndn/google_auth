package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Sampler {
    public static final long SAMPLING_OFF_SAMPLING_RATE_PERMILLE = -1;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/sampling/Sampler");
    private static final RateLimiting NO_RATE_LIMIT = RateLimiting.none();
    private volatile SamplingStrategy samplingStrategy = SamplingStrategy.Factory.DEFAULT_UNKNOWN_SAMPLING_STRATEGY;
    private volatile boolean enabled = true;
    private volatile RateLimiting rateLimiter = NO_RATE_LIMIT;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Sampler(Context context, Executor executor, SamplingStrategy.Factory factory, Lazy lazy, boolean z, Provider provider) {
        fetchConfigAndSamplingParameters(context, executor, factory, lazy, z ? provider : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fetchConfig */
    public void m358xb2df3173(Lazy lazy) {
        try {
            MetricConfigurations metricConfigurations = (MetricConfigurations) lazy.get();
            this.enabled = metricConfigurations.isEnabled();
            this.rateLimiter = RateLimiting.fixed(metricConfigurations.getRateLimitPerSecond());
        } catch (Throwable th) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(th)).withInjectedLogSite("com/google/android/libraries/performance/primes/sampling/Sampler", "fetchConfig", 103, "Sampler.java")).log("Couldn't get config");
            this.enabled = false;
        }
    }

    private void fetchConfigAndSamplingParameters(final Context context, final Executor executor, final SamplingStrategy.Factory factory, final Lazy lazy, final Provider provider) {
        submitFireAndForget(new Runnable() { // from class: com.google.android.libraries.performance.primes.sampling.Sampler$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Sampler.this.m360x873e41b1(context, lazy, executor, factory, provider);
            }
        }, executor);
    }

    private void fetchSamplingParameters(SamplingStrategy.Factory factory, Provider provider) {
        if (provider == null) {
            this.samplingStrategy = factory.create((SystemHealthProto$SamplingParameters) SystemHealthProto$SamplingParameters.newBuilder().setSamplingStrategy(SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_ALWAYS_ON).build());
            return;
        }
        try {
            this.samplingStrategy = factory.create((SystemHealthProto$SamplingParameters) provider.get());
        } catch (Throwable th) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(th)).withInjectedLogSite("com/google/android/libraries/performance/primes/sampling/Sampler", "fetchSamplingParameters", 124, "Sampler.java")).log("Couldn't get sampling strategy");
            this.samplingStrategy = factory.create((SystemHealthProto$SamplingParameters) SystemHealthProto$SamplingParameters.newBuilder().setSampleRatePermille(0L).setSamplingStrategy(SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_FLOOR).build());
        }
    }

    private static void submitFireAndForget(Runnable runnable, Executor executor) {
        Futures.submit(runnable, executor);
    }

    public SystemHealthProto$SamplingParameters getSamplingParameters(Long l) {
        return this.samplingStrategy.getSamplingParameters(l);
    }

    public void incrementEventCount() {
        this.rateLimiter.incrementEventCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchConfigAndSamplingParameters$1$com-google-android-libraries-performance-primes-sampling-Sampler  reason: not valid java name */
    public /* synthetic */ void m359x1d0eb992(final Lazy lazy, Executor executor) {
        submitFireAndForget(new Runnable() { // from class: com.google.android.libraries.performance.primes.sampling.Sampler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Sampler.this.m358xb2df3173(lazy);
            }
        }, executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$fetchConfigAndSamplingParameters$2$com-google-android-libraries-performance-primes-sampling-Sampler  reason: not valid java name */
    public /* synthetic */ void m360x873e41b1(Context context, final Lazy lazy, final Executor executor, SamplingStrategy.Factory factory, Provider provider) {
        if (DirectBootUtils.isUserUnlocked(context)) {
            m358xb2df3173(lazy);
        } else {
            DirectBootUtils.runWhenUnlocked(context, new Runnable() { // from class: com.google.android.libraries.performance.primes.sampling.Sampler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    Sampler.this.m359x1d0eb992(lazy, executor);
                }
            });
        }
        if (this.enabled) {
            fetchSamplingParameters(factory, provider);
        }
    }

    public SystemHealthProto$SamplingParameters samplingParametersIfShouldRecord(Long l) {
        return Sampling.samplingParametersIfShouldRecord(this.enabled, this.samplingStrategy, l);
    }

    public long samplingRatePermilleIfShouldCollect(String str) {
        if (this.rateLimiter.isRateLimitExceeded()) {
            return -1L;
        }
        return Sampling.samplingRatePermilleIfShouldCollect(this.enabled, this.samplingStrategy, str);
    }

    public boolean shouldRecordMetric() {
        return Sampling.shouldRecord(this.enabled, this.samplingStrategy);
    }
}
