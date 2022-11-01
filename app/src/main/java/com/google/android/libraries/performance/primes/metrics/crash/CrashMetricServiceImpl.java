package com.google.android.libraries.performance.primes.metrics.crash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.common.logging.stacktrace.LiteprotoEncoder;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.lang.Thread;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class CrashMetricServiceImpl extends CrashMetricService implements MetricService, AppLifecycleListener.OnActivityCreated, AppLifecycleListener.OnAppToBackground, AppLifecycleListener.OnActivityStarted {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl");
    volatile NoPiiString activeComponentName;
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Context application;
    private final Lazy configs;
    private final Executor deferredExecutor;
    private final boolean enableUnifiedInit;
    private final Lazy exceptionMessageMappingFunctions;
    boolean loggingTimedOut;
    private final MetricRecorder metricRecorder;
    private final Optional nativeCrashHandler;
    private final ProbabilitySamplerFactory probabilitySamplerFactory;
    private final Provider recordingTimeouts;
    private final AtomicBoolean isPrimesExceptionHandlerDefaultHandler = new AtomicBoolean();
    private final AtomicInteger queuedCrashMonitorInitialized = new AtomicInteger();
    private final AtomicInteger queuedFirstActivityCreated = new AtomicInteger();
    private final AtomicInteger queuedCustomLaunched = new AtomicInteger();
    private final AtomicBoolean activityHasBeenCreated = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PrimesUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler handlerToWrap;

        PrimesUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.handlerToWrap = uncaughtExceptionHandler;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
            try {
                try {
                    CrashMetricServiceImpl.this.reportJavaCrash(thread.getName(), th);
                    uncaughtExceptionHandler = this.handlerToWrap;
                    if (uncaughtExceptionHandler == null) {
                        return;
                    }
                } catch (Exception e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) CrashMetricServiceImpl.logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl$PrimesUncaughtExceptionHandler", "uncaughtException", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_MEASURING_USER_ENGAGEMENT_VALUE, "CrashMetricServiceImpl.java")).log("Failed to record crash.");
                    uncaughtExceptionHandler = this.handlerToWrap;
                    if (uncaughtExceptionHandler == null) {
                        return;
                    }
                }
                uncaughtExceptionHandler.uncaughtException(thread, th);
            } catch (Throwable th2) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.handlerToWrap;
                if (uncaughtExceptionHandler2 != null) {
                    uncaughtExceptionHandler2.uncaughtException(thread, th);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public CrashMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, Executor executor, Lazy lazy, Optional optional, AppLifecycleMonitor appLifecycleMonitor, ProbabilitySamplerFactory probabilitySamplerFactory, Lazy lazy2, Optional optional2, Provider provider) {
        this.configs = lazy;
        this.nativeCrashHandler = optional;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.probabilitySamplerFactory = probabilitySamplerFactory;
        this.exceptionMessageMappingFunctions = lazy2;
        this.metricRecorder = metricRecorderFactory.create(MoreExecutors.directExecutor(), lazy, null);
        this.application = context;
        this.deferredExecutor = executor;
        this.enableUnifiedInit = ((Boolean) optional2.or(Boolean.FALSE)).booleanValue();
        this.recordingTimeouts = provider;
    }

    static SystemHealthProto$CrashMetric.CrashType crashType(Class cls) {
        if (cls == OutOfMemoryError.class) {
            return SystemHealthProto$CrashMetric.CrashType.OUT_OF_MEMORY_ERROR;
        }
        if (NullPointerException.class.isAssignableFrom(cls)) {
            return SystemHealthProto$CrashMetric.CrashType.NULL_POINTER_EXCEPTION;
        }
        if (RuntimeException.class.isAssignableFrom(cls)) {
            return SystemHealthProto$CrashMetric.CrashType.OTHER_RUNTIME_EXCEPTION;
        }
        if (Error.class.isAssignableFrom(cls)) {
            return SystemHealthProto$CrashMetric.CrashType.OTHER_ERROR;
        }
        return SystemHealthProto$CrashMetric.CrashType.UNKNOWN;
    }

    private SystemHealthProto$CrashMetric.Builder createCrashMetric() {
        SystemHealthProto$CrashMetric.Builder hasCrashed = SystemHealthProto$CrashMetric.newBuilder().setHasCrashed(true);
        String safeToString = NoPiiString.safeToString(this.activeComponentName);
        if (safeToString != null) {
            hasCrashed.setActiveComponentName(safeToString);
        }
        try {
            hasCrashed.setProcessStats(ProcessProto$ProcessStats.newBuilder().setAndroidProcessStats(ProcessStatsCapture.getAndroidProcessStats(this.application)));
        } catch (RuntimeException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl", "createCrashMetric", 232, "CrashMetricServiceImpl.java")).log("Failed to get process stats.");
        }
        return hasCrashed;
    }

    private Logrecord$ThrowableProto.Builder createExceptionInfo(Throwable th, boolean z) {
        return LiteprotoEncoder.encodeThrowableWithGraph(th, z);
    }

    private ListenableFuture enqueueStartupEvent(final SystemHealthProto$PrimesStats.PrimesEvent primesEvent, final AtomicInteger atomicInteger) {
        atomicInteger.getAndIncrement();
        return Futures.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return CrashMetricServiceImpl.this.m329xf903b720(atomicInteger, primesEvent);
            }
        }, this.deferredExecutor);
    }

    private void flushDeferredPrimesStats(CrashConfigurations crashConfigurations) {
        while (this.queuedCrashMonitorInitialized.getAndDecrement() > 0) {
            PrimesExecutors.logFailures(recordStartupEventWithSampling(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_MONITORING_INITIALIZED, crashConfigurations));
        }
        while (this.queuedFirstActivityCreated.getAndDecrement() > 0) {
            PrimesExecutors.logFailures(recordStartupEventWithSampling(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_FIRST_ACTIVITY_LAUNCHED, crashConfigurations));
        }
        while (this.queuedCustomLaunched.getAndDecrement() > 0) {
            PrimesExecutors.logFailures(recordStartupEventWithSampling(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CUSTOM_LAUNCHED, crashConfigurations));
        }
    }

    private void reportCrash(SystemHealthProto$CrashMetric systemHealthProto$CrashMetric) {
        long bgThreadTimeoutMs;
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
        CrashConfigurations crashConfigurations = (CrashConfigurations) this.configs.get();
        if (!crashConfigurations.isEnabled()) {
            return;
        }
        RecentLogs.TimestampCollection debugLogsTime = this.metricRecorder.getDebugLogsTime();
        Provider metricExtensionProvider = crashConfigurations.getMetricExtensionProvider();
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension2 = null;
        if (metricExtensionProvider != null) {
            try {
                extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) metricExtensionProvider.get();
            } catch (RuntimeException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl", "reportCrash", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTEXTUALIZATION_1P_INSTALLED_APPS_VALUE, "CrashMetricServiceImpl.java")).log("Exception while getting crash metric extension!");
                extensionMetric$MetricExtension = null;
            }
            if (!ExtensionMetric$MetricExtension.getDefaultInstance().equals(extensionMetric$MetricExtension)) {
                extensionMetric$MetricExtension2 = extensionMetric$MetricExtension;
            }
        }
        try {
            if (ThreadUtil.isMainThread()) {
                bgThreadTimeoutMs = ((CrashRecordingTimeouts) this.recordingTimeouts.get()).getMainThreadTimeoutMs();
            } else {
                bgThreadTimeoutMs = ((CrashRecordingTimeouts) this.recordingTimeouts.get()).getBgThreadTimeoutMs();
            }
            this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setCrashMetric(systemHealthProto$CrashMetric).build()).setMetricExtension(extensionMetric$MetricExtension2).setDebugLogsTime(debugLogsTime).build()).get(bgThreadTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
        } catch (TimeoutException e3) {
            this.loggingTimedOut = true;
        } catch (Throwable th) {
        }
        flushDeferredPrimesStats(crashConfigurations);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportJavaCrash(String str, Throwable th) {
        String name = th.getClass().getName();
        for (Throwable cause = th.getCause(); cause != null && cause != cause.getCause(); cause = cause.getCause()) {
            name = cause.getClass().getName();
        }
        Logrecord$ThrowableProto.Builder createExceptionInfo = createExceptionInfo(th, true);
        for (ExceptionMessageMappingFunction exceptionMessageMappingFunction : (Set) this.exceptionMessageMappingFunctions.get()) {
            createExceptionInfo = ThrowableMapper.apply(createExceptionInfo, exceptionMessageMappingFunction);
        }
        reportCrash((SystemHealthProto$CrashMetric) createCrashMetric().setThreadName(str).setCrashType(crashType(th.getClass())).setCrashClassName(name).setException((Logrecord$ThrowableProto) createExceptionInfo.build()).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$enqueueStartupEvent$0$com-google-android-libraries-performance-primes-metrics-crash-CrashMetricServiceImpl  reason: not valid java name */
    public /* synthetic */ ListenableFuture m329xf903b720(AtomicInteger atomicInteger, SystemHealthProto$PrimesStats.PrimesEvent primesEvent) {
        if (atomicInteger.getAndDecrement() <= 0) {
            return Futures.immediateVoidFuture();
        }
        return recordStartupEventWithSampling(primesEvent, (CrashConfigurations) this.configs.get());
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityCreated
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl", "onActivityCreated", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_TACHYON_PHONE_NUMBER_IDENTITY_VALUE, "CrashMetricServiceImpl.java")).log("onActivityCreated");
        if (!this.activityHasBeenCreated.getAndSet(true)) {
            PrimesExecutors.logFailures(enqueueStartupEvent(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_FIRST_ACTIVITY_LAUNCHED, this.queuedFirstActivityCreated));
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityStarted
    public void onActivityStarted(Activity activity) {
        this.activeComponentName = NoPiiString.fromClass(activity.getClass());
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        this.activeComponentName = null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        if (this.nativeCrashHandler.isPresent()) {
            ((NativeCrashHandler) ((Provider) this.nativeCrashHandler.get()).get()).initialize(this);
        }
        this.appLifecycleMonitor.register(this);
        PrimesExecutors.logFailures(enqueueStartupEvent(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_MONITORING_INITIALIZED, this.queuedCrashMonitorInitialized));
        if (this.enableUnifiedInit) {
            setPrimesExceptionHandlerAsDefaultHandler();
        }
    }

    ListenableFuture recordStartupEventWithSampling(SystemHealthProto$PrimesStats.PrimesEvent primesEvent, CrashConfigurations crashConfigurations) {
        if (!crashConfigurations.isEnabled()) {
            return Futures.immediateVoidFuture();
        }
        float startupSamplePercentage = crashConfigurations.getStartupSamplePercentage();
        if (!this.probabilitySamplerFactory.create(startupSamplePercentage / 100.0f).isSampleAllowed()) {
            return Futures.immediateVoidFuture();
        }
        return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setPrimesStats(SystemHealthProto$PrimesStats.newBuilder().setEstimatedCount((int) (100.0f / startupSamplePercentage)).setPrimesEvent(primesEvent)).build()).build());
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashMetricService
    public void setPrimesExceptionHandlerAsDefaultHandler() {
        if (this.isPrimesExceptionHandlerDefaultHandler.compareAndSet(false, true)) {
            Thread.setDefaultUncaughtExceptionHandler(wrapUncaughtExceptionHandlerWithPrimesHandler(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    public Thread.UncaughtExceptionHandler wrapUncaughtExceptionHandlerWithPrimesHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        return new PrimesUncaughtExceptionHandler(uncaughtExceptionHandler);
    }
}
