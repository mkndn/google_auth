package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.Lazy;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$PrimesTrace;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupMeasurements;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class StartupMetricRecordingService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/startup/StartupMetricRecordingService");
    private final Lazy configsProvider;
    private final ListeningScheduledExecutorService executorService;
    private final MetricRecorder metricRecorder;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public StartupMetricRecordingService(MetricRecorderFactory metricRecorderFactory, ListeningScheduledExecutorService listeningScheduledExecutorService, Executor executor, Lazy lazy, Provider provider) {
        this.configsProvider = lazy;
        this.executorService = listeningScheduledExecutorService;
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
    }

    private static void setCustomTimestamps(PrimesTraceOuterClass$StartupMeasurements.Builder builder, ListenableFuture listenableFuture) {
        try {
            Map map = (Map) ((Optional) Futures.getDone(listenableFuture)).orNull();
            if (map == null) {
                return;
            }
            long baselineTimeMs = builder.getBaselineTimeMs();
            for (Map.Entry entry : map.entrySet()) {
                builder.putCustomMetricsMs(((Integer) entry.getKey()).intValue(), ((Long) entry.getValue()).longValue() - baselineTimeMs);
            }
        } catch (Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/startup/StartupMetricRecordingService", "setCustomTimestamps", 118, "StartupMetricRecordingService.java")).log("Failed to get custom timestamps future");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$record$2$com-google-android-libraries-performance-primes-metrics-startup-StartupMetricRecordingService  reason: not valid java name */
    public /* synthetic */ ListenableFuture m348xd252784d(PrimesTraceOuterClass$StartupMeasurements.Builder builder, ListenableFuture listenableFuture, ListenableFuture listenableFuture2, String str) {
        setCustomTimestamps(builder, listenableFuture);
        return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setPrimesTrace(PrimesTraceOuterClass$PrimesTrace.newBuilder().setTraceId(UUID.randomUUID().getLeastSignificantBits()).setTraceType(PrimesTraceOuterClass$PrimesTrace.TraceType.MINI_TRACE).setStartupMeasurements(builder)).build()).setMetricExtension((ExtensionMetric$MetricExtension) ((Optional) Futures.getDone(listenableFuture2)).orNull()).setAccountableComponentName(str).setIsUnsampled(true).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$record$3$com-google-android-libraries-performance-primes-metrics-startup-StartupMetricRecordingService  reason: not valid java name */
    public /* synthetic */ ListenableFuture m349xfa98b88e(final PrimesTraceOuterClass$StartupMeasurements.Builder builder, final String str) {
        if (!this.metricRecorder.shouldCollectMetric(null)) {
            return Futures.immediateVoidFuture();
        }
        if ((builder.getStartupType() == PrimesTraceOuterClass$StartupMeasurements.StartupType.WARM || builder.getStartupType() == PrimesTraceOuterClass$StartupMeasurements.StartupType.COLD) && !builder.hasAppClassLoadedMs()) {
            return Futures.immediateVoidFuture();
        }
        StartupConfigurations startupConfigurations = (StartupConfigurations) this.configsProvider.get();
        final ListenableFuture customTimestamps = ((StartupMetricCustomTimestampProvider) startupConfigurations.getCustomTimestampProvider().or(StartupMetricRecordingService$$ExternalSyntheticLambda1.INSTANCE)).getCustomTimestamps();
        final ListenableFuture metricExtension = ((StartupMetricExtensionProvider) startupConfigurations.getMetricExtensionProvider().or(StartupMetricRecordingService$$ExternalSyntheticLambda2.INSTANCE)).getMetricExtension();
        return Futures.whenAllComplete(customTimestamps, metricExtension).callAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.startup.StartupMetricRecordingService$$ExternalSyntheticLambda3
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return StartupMetricRecordingService.this.m348xd252784d(builder, customTimestamps, metricExtension, str);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture record(final PrimesTraceOuterClass$StartupMeasurements.Builder builder, final String str) {
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.startup.StartupMetricRecordingService$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return StartupMetricRecordingService.this.m349xfa98b88e(builder, str);
            }
        }, this.executorService);
    }
}
