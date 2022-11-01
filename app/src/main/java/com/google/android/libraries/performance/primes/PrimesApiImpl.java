package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricService;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricService;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PrimesApiImpl implements PrimesApi {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/PrimesApiImpl");
    private final Provider batteryMetricServiceProvider;
    private final Provider crashMetricServiceProvider;
    private final Provider customDurationMetricServiceProvider;
    private final Provider debugMemoryMetricServiceProvider;
    private final Provider executorServiceProvider;
    private final Provider jankMetricServiceProvider;
    private final Provider memoryDiffMetricServiceProvider;
    private final Provider memoryMetricServiceProvider;
    private final Provider memoryMetricServiceUnsafeStringProvider;
    private final Provider metricServices;
    private final Provider metricTransmittersProvider;
    private final Provider networkConfigurations;
    private final Provider networkMetricServiceProvider;
    private final Shutdown shutdown;
    private final Provider startupMetricServiceProvider;
    private final Provider storageMetricServiceProvider;
    private final Provider timerMetricServiceProvider;
    private final Provider traceMetricServiceProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public PrimesApiImpl(Context context, Provider provider, Shutdown shutdown, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8, Provider provider9, Provider provider10, Provider provider11, Provider provider12, Provider provider13, Provider provider14, Provider provider15, Provider provider16, Provider provider17, Optional optional, CrashOnBadPrimesConfiguration crashOnBadPrimesConfiguration) {
        this.executorServiceProvider = provider;
        this.shutdown = shutdown;
        this.metricServices = provider2;
        this.metricTransmittersProvider = provider3;
        this.networkConfigurations = provider4;
        this.batteryMetricServiceProvider = provider5;
        this.crashMetricServiceProvider = provider6;
        this.jankMetricServiceProvider = provider7;
        this.memoryMetricServiceProvider = provider8;
        this.memoryMetricServiceUnsafeStringProvider = provider9;
        this.debugMemoryMetricServiceProvider = provider10;
        this.memoryDiffMetricServiceProvider = provider11;
        this.networkMetricServiceProvider = provider12;
        this.storageMetricServiceProvider = provider13;
        this.timerMetricServiceProvider = provider14;
        this.traceMetricServiceProvider = provider15;
        this.customDurationMetricServiceProvider = provider16;
        this.startupMetricServiceProvider = provider17;
        if (!ThreadUtil.isMainThread()) {
            crashOnBadPrimesConfiguration.observedBackgroundInitialization();
        }
        if (!((Boolean) optional.or(Boolean.FALSE)).booleanValue()) {
            initialize();
        }
    }

    private void initialize() {
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesApiImpl", "initialize", 115, "PrimesApiImpl.java")).log("Primes instant initialization");
        try {
            Tracer.checkTrace();
            for (MetricService metricService : (Set) this.metricServices.get()) {
                metricService.onApplicationStartup();
            }
        } catch (RuntimeException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesApiImpl", "initialize", 123, "PrimesApiImpl.java")).log("Primes failed to initialize");
            shutdown();
        }
    }

    public void shutdown() {
        this.shutdown.shutdown();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public void startCrashMonitor() {
        ((CrashMetricService) this.crashMetricServiceProvider.get()).setPrimesExceptionHandlerAsDefaultHandler();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public void startMemoryMonitor() {
        ((MemoryMetricService) this.memoryMetricServiceProvider.get()).startMonitoring();
    }
}
