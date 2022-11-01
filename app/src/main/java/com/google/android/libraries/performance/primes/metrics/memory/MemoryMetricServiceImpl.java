package com.google.android.libraries.performance.primes.metrics.memory;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metrics.MetricEnablement;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Callables;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class MemoryMetricServiceImpl extends MemoryMetricService implements MetricService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/memory/MemoryMetricServiceImpl");
    private final Application application;
    private final MemoryUsageCapture capture;
    private final Clock clock;
    private final Lazy configsProvider;
    private final boolean enableUnifiedInit;
    private final ListeningScheduledExecutorService executorService;
    private final MemoryMetricMonitor metricMonitor;
    private final MetricRecorder metricRecorder;
    private final Shutdown shutdown;
    final AtomicReference lastSnapshot = new AtomicReference(MemoryEvent.EMPTY_SNAPSHOT);
    final ConcurrentHashMap startSnapshots = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MemoryMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Clock clock, Context context, MemoryMetricMonitor memoryMetricMonitor, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, MemoryUsageCapture memoryUsageCapture, Shutdown shutdown, Provider provider, Executor executor, Optional optional) {
        this.metricMonitor = memoryMetricMonitor;
        this.shutdown = shutdown;
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider);
        this.application = (Application) context;
        this.executorService = listeningScheduledExecutorService;
        this.clock = clock;
        this.configsProvider = lazy;
        this.capture = memoryUsageCapture;
        this.enableUnifiedInit = ((Boolean) optional.or(Boolean.FALSE)).booleanValue();
    }

    private boolean isUnsampled(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode) {
        return (memoryEventCode.equals(MemoryMetric$MemoryUsageMetric.MemoryEventCode.UNKNOWN) || memoryEventCode.equals(MemoryMetric$MemoryUsageMetric.MemoryEventCode.DELTA_OF_MEMORY)) ? false : true;
    }

    private ListenableFuture record(final String str, final boolean z, final MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, final String str2, final ExtensionMetric$MetricExtension extensionMetric$MetricExtension, final boolean z2) {
        if (this.shutdown.isShutdown()) {
            return Futures.immediateCancelledFuture();
        }
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return MemoryMetricServiceImpl.this.m345xe9c43549(memoryEventCode, str, extensionMetric$MetricExtension, z, str2, z2);
            }
        }, this.executorService);
    }

    private ListenableFuture recordMemory(String str, boolean z, long j, MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, boolean z2) {
        Metric.Builder metricExtension = Metric.newBuilder().setCustomEventName(str).setIsEventNameConstant(z).setSampleRatePermille(Long.valueOf(j)).setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setMemoryUsageMetric(this.capture.getMemoryUsageMetric(memoryEventCode, str2, z2)).build()).setMetricExtension(extensionMetric$MetricExtension);
        if (isUnsampled(memoryEventCode)) {
            metricExtension.setIsUnsampled(true);
        }
        return this.metricRecorder.recordMetric(metricExtension.build());
    }

    private ListenableFuture recordMemoryPerProcess(String str, boolean z, long j, MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, boolean z2) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ProcessStats.getActivityManager(this.application).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return Futures.immediateVoidFuture();
        }
        ArrayList arrayList = new ArrayList(runningAppProcesses.size());
        String packageName = this.application.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (Build.VERSION.SDK_INT > 22 || runningAppProcessInfo.processName.contains(packageName)) {
                Metric.Builder metricExtension = Metric.newBuilder().setCustomEventName(str).setIsEventNameConstant(z).setSampleRatePermille(Long.valueOf(j)).setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setMemoryUsageMetric(this.capture.getMemoryUsageMetric(memoryEventCode, runningAppProcessInfo.pid, runningAppProcessInfo.processName, str2, z2)).build()).setMetricExtension(extensionMetric$MetricExtension);
                if (isUnsampled(memoryEventCode)) {
                    metricExtension.setIsUnsampled(true);
                }
                arrayList.add(this.metricRecorder.recordMetric(metricExtension.build()));
            }
        }
        return Futures.whenAllSucceed(arrayList).call(Callables.returning(null), MoreExecutors.directExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0095  */
    /* renamed from: lambda$record$5$com-google-android-libraries-performance-primes-metrics-memory-MemoryMetricServiceImpl  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ ListenableFuture m345xe9c43549(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, boolean z, String str2, boolean z2) {
        long samplingRatePermilleIfShouldCollect;
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension2;
        MemoryMetricExtensionProvider memoryMetricExtensionProvider;
        MemoryConfigurations memoryConfigurations = (MemoryConfigurations) this.configsProvider.get();
        if (isUnsampled(memoryEventCode)) {
            samplingRatePermilleIfShouldCollect = memoryConfigurations.getEnablement() == MetricEnablement.EXPLICITLY_ENABLED ? 1000L : -1L;
        } else {
            samplingRatePermilleIfShouldCollect = this.metricRecorder.samplingRatePermilleIfShouldCollect(str);
        }
        if (samplingRatePermilleIfShouldCollect == -1) {
            return Futures.immediateVoidFuture();
        }
        if (memoryConfigurations.getForceGcBeforeRecordMemory()) {
            System.gc();
            System.runFinalization();
            System.gc();
        }
        if (extensionMetric$MetricExtension == null && (memoryMetricExtensionProvider = (MemoryMetricExtensionProvider) memoryConfigurations.getMetricExtensionProvider().orNull()) != null) {
            try {
                extensionMetric$MetricExtension2 = memoryMetricExtensionProvider.getMetricExtension(str, memoryEventCode);
            } catch (RuntimeException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/memory/MemoryMetricServiceImpl", "lambda$record$5", 407, "MemoryMetricServiceImpl.java")).log("Metric extension provider failed.");
            }
            if (!memoryConfigurations.getRecordMetricPerProcess()) {
                return recordMemoryPerProcess(str, z, samplingRatePermilleIfShouldCollect, memoryEventCode, str2, extensionMetric$MetricExtension2, z2);
            }
            return recordMemory(str, z, samplingRatePermilleIfShouldCollect, memoryEventCode, str2, extensionMetric$MetricExtension2, z2);
        }
        extensionMetric$MetricExtension2 = extensionMetric$MetricExtension;
        if (!memoryConfigurations.getRecordMetricPerProcess()) {
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        if (this.enableUnifiedInit) {
            startMonitoring();
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricService
    public void startMonitoring() {
        this.metricMonitor.start(new MemoryMetricMonitor.Callback() { // from class: com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor.Callback
            public final void onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str) {
                MemoryMetricServiceImpl.this.m346xf98a602f(memoryEventCode, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startMonitoring$0$com-google-android-libraries-performance-primes-metrics-memory-MemoryMetricServiceImpl  reason: not valid java name */
    public /* synthetic */ void m346xf98a602f(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str) {
        PrimesExecutors.logFailures(record(null, true, memoryEventCode, str, null, false));
    }
}
