package com.google.android.libraries.performance.primes.metrics.jank;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Trace;
import android.util.ArrayMap;
import android.view.FrameMetrics;
import android.view.Window;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Verify;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$JankMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class FrameMetricServiceImpl extends JankMetricService implements AppLifecycleListener.OnAppToBackground, MetricService {
    public static final int FRAME_METRICS_DEADLINE_VALUE = 13;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl");
    private final ActivityLevelJankMonitor activityLevelJankMonitor;
    private final ActivityTracker activityTracker;
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Application application;
    private final Provider frameTimeHistogramProvider;
    private final ArrayMap measurements;
    private final MetricRecorder metricRecorder;
    private final Provider perfettoConfigurations;
    private final PerfettoTrigger perfettoTrigger;
    private final Supplier perfettoTriggerName;

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$1 */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType;

        static {
            int[] iArr = new int[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.values().length];
            $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType = iArr;
            try {
                iArr[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_JANKY_FRAME_COUNT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_TOTAL_FRAME_COUNT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_DROPPED_REPORT_COUNT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_MAX_FRAME_DURATION_MS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_TOTAL_JANKY_FRAME_DURATION_MS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_TOTAL_FRAME_DURATION_MS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_EMPTY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_UNKNOWN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ActivityTracker extends AppLifecycleListener {
        void startCollecting();

        void stopCollecting();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FrameMetricsListener implements Window.OnFrameMetricsAvailableListener {
        private final Supplier maxAcceptedFrameTimeMs;
        private final ArrayMap measurements;

        FrameMetricsListener(final Context context, ArrayMap arrayMap) {
            this.maxAcceptedFrameTimeMs = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$FrameMetricsListener$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return FrameMetricServiceImpl.FrameMetricsListener.lambda$new$0(context);
                }
            });
            this.measurements = arrayMap;
        }

        public static /* synthetic */ Integer lambda$new$0(Context context) {
            return Integer.valueOf(DisplayStats.maxAcceptedFrameRenderTimeMs(context));
        }

        @Override // android.view.Window.OnFrameMetricsAvailableListener
        public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
            if (frameMetrics.getMetric(9) == 1) {
                return;
            }
            int metric = (int) (frameMetrics.getMetric(8) / 1000000);
            int intValue = ((Integer) this.maxAcceptedFrameTimeMs.get()).intValue();
            int metric2 = (int) (frameMetrics.getMetric(13) / 1000000);
            ArrayMap arrayMap = this.measurements;
            synchronized (arrayMap) {
                int size = arrayMap.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((FrameTimeHistogram) arrayMap.valueAt(i2)).addFrame(metric, intValue, i, metric2);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class MeasurementKey {
        public static MeasurementKey create(Activity activity) {
            return new AutoValue_FrameMetricServiceImpl_MeasurementKey(null, NoPiiString.fromFullyQualifiedClassName(activity.getClass()), true);
        }

        public String stringValue() {
            NoPiiString noPiiEventName = noPiiEventName();
            if (noPiiEventName != null) {
                return noPiiEventName.toString();
            }
            return (String) Verify.verifyNotNull(rawStringEventName());
        }

        public final boolean equals(Object obj) {
            if (obj instanceof MeasurementKey) {
                MeasurementKey measurementKey = (MeasurementKey) obj;
                return stringValue().equals(measurementKey.stringValue()) && isActivity() == measurementKey.isActivity();
            }
            return false;
        }

        public final int hashCode() {
            return (stringValue().hashCode() * 31) ^ (isActivity() ? 1231 : 1237);
        }

        abstract boolean isActivity();

        abstract NoPiiString noPiiEventName();

        abstract String rawStringEventName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnCreateBasedActivityTracker implements ActivityTracker, AppLifecycleListener.OnActivityCreated, AppLifecycleListener.OnActivityDestroyed {
        private final Window.OnFrameMetricsAvailableListener frameMetricsListener;
        private final Lazy handler;

        OnCreateBasedActivityTracker(Window.OnFrameMetricsAvailableListener onFrameMetricsAvailableListener, Lazy lazy) {
            this.frameMetricsListener = onFrameMetricsAvailableListener;
            this.handler = lazy;
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityCreated
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activity.getWindow().addOnFrameMetricsAvailableListener(this.frameMetricsListener, (Handler) this.handler.get());
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityDestroyed
        public void onActivityDestroyed(Activity activity) {
            activity.getWindow().removeOnFrameMetricsAvailableListener(this.frameMetricsListener);
        }

        @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.ActivityTracker
        public void startCollecting() {
        }

        @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.ActivityTracker
        public void stopCollecting() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnResumedBasedActivityTracker implements AppLifecycleListener.OnActivityResumed, AppLifecycleListener.OnActivityPaused, ActivityTracker {
        private Activity currentActivity;
        private final Window.OnFrameMetricsAvailableListener frameMetricsListener;
        private final Lazy handler;
        private boolean measuring;

        OnResumedBasedActivityTracker(Window.OnFrameMetricsAvailableListener onFrameMetricsAvailableListener, Lazy lazy) {
            this.frameMetricsListener = onFrameMetricsAvailableListener;
            this.handler = lazy;
        }

        private void attachToCurrentActivity() {
            Activity activity = this.currentActivity;
            if (activity != null) {
                activity.getWindow().addOnFrameMetricsAvailableListener(this.frameMetricsListener, (Handler) this.handler.get());
            }
        }

        private void detachFromCurrentActivity() {
            Activity activity = this.currentActivity;
            if (activity != null) {
                try {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(this.frameMetricsListener);
                } catch (RuntimeException e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) FrameMetricServiceImpl.logger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl$OnResumedBasedActivityTracker", "detachFromCurrentActivity", 124, "FrameMetricServiceImpl.java")).log("remove frame metrics listener failed");
                }
            }
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityPaused
        public void onActivityPaused(Activity activity) {
            synchronized (this) {
                if (this.measuring) {
                    detachFromCurrentActivity();
                }
                this.currentActivity = null;
            }
        }

        @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnActivityResumed
        public void onActivityResumed(Activity activity) {
            synchronized (this) {
                this.currentActivity = activity;
                if (this.measuring) {
                    attachToCurrentActivity();
                }
            }
        }

        @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.ActivityTracker
        public void startCollecting() {
            synchronized (this) {
                this.measuring = true;
                if (this.currentActivity != null) {
                    attachToCurrentActivity();
                } else {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) FrameMetricServiceImpl.logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl$OnResumedBasedActivityTracker", "startCollecting", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_UNBRANDED_1P_APP_PRODUCT_DEVELOPMENT_VALUE, "FrameMetricServiceImpl.java")).log("No activity");
                }
            }
        }

        @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.ActivityTracker
        public void stopCollecting() {
            synchronized (this) {
                this.measuring = false;
                detachFromCurrentActivity();
            }
        }
    }

    @Inject
    public FrameMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, Lazy lazy, ActivityLevelJankMonitor activityLevelJankMonitor, Provider provider, Provider provider2, Executor executor, Lazy lazy2, PerfettoTrigger perfettoTrigger, final Provider provider3, boolean z) {
        ArrayMap arrayMap = new ArrayMap();
        this.measurements = arrayMap;
        Preconditions.checkState(Build.VERSION.SDK_INT >= 24);
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider2);
        Application application = (Application) context;
        this.application = application;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.frameTimeHistogramProvider = provider;
        this.activityLevelJankMonitor = activityLevelJankMonitor;
        this.perfettoTrigger = perfettoTrigger;
        this.perfettoTriggerName = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return FrameMetricServiceImpl.this.m336x46c4c2d0(provider3);
            }
        });
        this.perfettoConfigurations = provider3;
        FrameMetricsListener frameMetricsListener = new FrameMetricsListener(application, arrayMap);
        this.activityTracker = z ? new OnCreateBasedActivityTracker(frameMetricsListener, lazy2) : new OnResumedBasedActivityTracker(frameMetricsListener, lazy2);
    }

    private void endTraceSectionAndEmitCounters(String str, FrameTimeHistogram frameTimeHistogram) {
        if (Build.VERSION.SDK_INT < 29 || !Trace.isEnabled()) {
            return;
        }
        int i = -1;
        for (PerfettoTraceConfigurations$JankPerfettoConfigurations.Counter counter : ((PerfettoTraceConfigurations$JankPerfettoConfigurations) this.perfettoConfigurations.get()).getCounterList()) {
            switch (AnonymousClass1.$SwitchMap$com$google$android$libraries$performance$primes$metrics$jank$PerfettoTraceConfigurations$JankPerfettoConfigurations$CounterType[counter.getType().ordinal()]) {
                case 1:
                    i = frameTimeHistogram.getJankyFrameCount();
                    break;
                case 2:
                    i = frameTimeHistogram.getRenderedFrameCount();
                    break;
                case 3:
                    i = frameTimeHistogram.getDroppedReportCount();
                    break;
                case 4:
                    i = frameTimeHistogram.getMaxFrameDurationMs();
                    break;
                case 5:
                    i = frameTimeHistogram.getTotalJankyFrameDurationMs();
                    break;
                case 6:
                    i = frameTimeHistogram.getTotalFrameDurationMs();
                    break;
                case 7:
                    i = 0;
                    break;
                case 8:
                    ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "endTraceSectionAndEmitCounters", 505, "FrameMetricServiceImpl.java")).log("UNKNOWN COUNTER with %s as the name", counter.getName());
                    continue;
            }
            Trace.setCounter(counter.getName().replace("%EVENT_NAME%", str), i);
        }
        Trace.endAsyncSection(String.format("J<%s>", str), 352691800);
    }

    private void maybeFlushPerfetto(FrameTimeHistogram frameTimeHistogram) {
        if (!((PerfettoTraceConfigurations$JankPerfettoConfigurations) this.perfettoConfigurations.get()).getFlushingToPerfettoEnabled() || frameTimeHistogram.totalFrameDurationMs() > TimeUnit.SECONDS.toMillis(9L) || frameTimeHistogram.getJankyFrameCount() == 0) {
            return;
        }
        this.perfettoTrigger.trigger((String) this.perfettoTriggerName.get());
    }

    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-jank-FrameMetricServiceImpl */
    public /* synthetic */ String m336x46c4c2d0(Provider provider) {
        return ((PerfettoTraceConfigurations$JankPerfettoConfigurations) provider.get()).getTriggerNameFormatString().replace("%PACKAGE_NAME%", this.application.getPackageName());
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener.OnAppToBackground
    public void onAppToBackground(Activity activity) {
        synchronized (this.measurements) {
            this.measurements.clear();
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.appLifecycleMonitor.register(this.activityTracker);
        this.appLifecycleMonitor.register(this.activityLevelJankMonitor);
    }

    public void start(Activity activity) {
        start(MeasurementKey.create(activity));
    }

    public ListenableFuture stopAsFuture(Activity activity) {
        return stopAsFuture(MeasurementKey.create(activity), null);
    }

    private ListenableFuture stopAsFuture(MeasurementKey measurementKey, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        FrameTimeHistogram frameTimeHistogram;
        if (!this.metricRecorder.shouldRecordMetric()) {
            return Futures.immediateVoidFuture();
        }
        synchronized (this.measurements) {
            frameTimeHistogram = (FrameTimeHistogram) this.measurements.remove(measurementKey);
            if (this.measurements.isEmpty()) {
                this.activityTracker.stopCollecting();
            }
        }
        if (frameTimeHistogram == null) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "stopAsFuture", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_GOOGLE_STREET_VIEW_MANAGEMENT_VALUE, "FrameMetricServiceImpl.java")).log("Measurement not found: %s", measurementKey);
            return Futures.immediateVoidFuture();
        }
        endTraceSectionAndEmitCounters(measurementKey.stringValue(), frameTimeHistogram);
        if (frameTimeHistogram.getRenderedFrameCount() == 0) {
            return Futures.immediateVoidFuture();
        }
        maybeFlushPerfetto(frameTimeHistogram);
        SystemHealthProto$JankMetric metric = frameTimeHistogram.getMetric();
        Optional refreshRate = DisplayStats.getRefreshRate(this.application);
        if (refreshRate.isPresent()) {
            metric = (SystemHealthProto$JankMetric) ((SystemHealthProto$JankMetric.Builder) metric.toBuilder()).setDeviceRefreshRate(((Integer) refreshRate.get()).intValue()).build();
        }
        return this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setJankMetric(metric).build()).setMetricExtension(extensionMetric$MetricExtension).setAccountableComponentName(measurementKey.isActivity() ? "Activity" : null).setCustomEventName(measurementKey.stringValue()).setIsEventNameConstant(measurementKey.noPiiEventName() != null).build());
    }

    private void start(MeasurementKey measurementKey) {
        if (!this.metricRecorder.shouldCollectMetric(measurementKey.stringValue())) {
            return;
        }
        synchronized (this.measurements) {
            if (this.measurements.size() >= 25) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "start", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_OPENSKY_FUNCTIONAL_DEBUGGING_VALUE, "FrameMetricServiceImpl.java")).log("Too many concurrent measurements, ignoring %s", measurementKey);
                return;
            }
            FrameTimeHistogram frameTimeHistogram = (FrameTimeHistogram) this.measurements.put(measurementKey, (FrameTimeHistogram) this.frameTimeHistogramProvider.get());
            if (frameTimeHistogram != null) {
                this.measurements.put(measurementKey, frameTimeHistogram);
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "start", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BINARY_TRANSPARENCY_VALUE, "FrameMetricServiceImpl.java")).log("measurement already started: %s", measurementKey);
                return;
            }
            if (this.measurements.size() == 1) {
                this.activityTracker.startCollecting();
            }
            if (Build.VERSION.SDK_INT >= 29 && Trace.isEnabled()) {
                Trace.beginAsyncSection(String.format("J<%s>", measurementKey.stringValue()), 352691800);
            }
        }
    }
}
