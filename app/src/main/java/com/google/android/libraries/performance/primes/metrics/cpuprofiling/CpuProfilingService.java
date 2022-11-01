package com.google.android.libraries.performance.primes.metrics.cpuprofiling;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Debug;
import androidx.core.content.ContextCompat;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.PrimesExecutors;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.protobuf.ByteString;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import dagger.Lazy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.CpuProfiling$CpuProfilingMetric;
import logs.proto.wireless.performance.mobile.CpuProfiling$DeviceMetadata;
import logs.proto.wireless.performance.mobile.CpuProfiling$DeviceState;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class CpuProfilingService implements MetricService {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService");
    private final Application application;
    private final Clock clock;
    private final Lazy configsProvider;
    private final ListeningScheduledExecutorService executorService;
    private final MetricRecorder metricRecorder;
    private final Supplier schedulerSupplier;
    private final Supplier traceFileSupplier;
    private WifiManager wifi;
    private final AtomicBoolean scheduled = new AtomicBoolean(false);
    private final IntentFilter batteryIntentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CpuCollectionEndTask implements Runnable {
        private final long actualStartTiemMs;
        private final Float batteryPercent;
        private final CpuProfiling$DeviceMetadata deviceMetadata;
        private final long stopTimeMs;
        private final File traceFile;

        private CpuCollectionEndTask(File file, CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata, Float f, long j, long j2) {
            CpuProfilingService.this = r1;
            this.traceFile = file;
            this.deviceMetadata = cpuProfiling$DeviceMetadata;
            this.batteryPercent = f;
            this.stopTimeMs = j;
            this.actualStartTiemMs = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CpuProfilingService.this.scheduled.set(false);
            Debug.stopMethodTracing();
            long currentTimeMillis = CpuProfilingService.this.clock.currentTimeMillis();
            if (currentTimeMillis >= this.stopTimeMs + ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleDurationSkewMs()) {
                CpuProfilingService.this.scheduleNextMonitoringWindow(false);
                ((GoogleLogger.Api) ((GoogleLogger.Api) CpuProfilingService.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_WINGDELIVERY_PROVISION_OF_SERVICE_VALUE, "CpuProfilingService.java")).log("Missed sample window by %d ms", currentTimeMillis - this.stopTimeMs);
                return;
            }
            Intent registerReceiver = CpuProfilingService.this.application.registerReceiver(null, CpuProfilingService.this.batteryIntentFilter);
            CpuProfiling$CpuProfilingMetric.Builder deviceMetadata = CpuProfiling$CpuProfilingMetric.newBuilder().setDeviceMetadata((CpuProfiling$DeviceMetadata) ((CpuProfiling$DeviceMetadata.Builder) this.deviceMetadata.toBuilder()).setAfterState(CpuProfilingService.this.getDeviceState(registerReceiver)).setBatteryDropPercent(this.batteryPercent.floatValue() - CpuProfilingService.this.getBatteryPercent(registerReceiver)).build());
            File file = this.traceFile;
            if (file != null && file.exists()) {
                long length = this.traceFile.length();
                if (length > 0 && length < ((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getMaxBufferSizeBytes()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.traceFile);
                        ByteString.Output newOutput = ByteString.newOutput((int) length);
                        Compression.compressBytes(fileInputStream, newOutput);
                        deviceMetadata.setTraceBlob(newOutput.toByteString());
                        CpuProfilingService.clearFileAndSwallowResultingExceptions(this.traceFile);
                        if (newOutput != null) {
                            newOutput.close();
                        }
                        fileInputStream.close();
                    } catch (IOException e) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) CpuProfilingService.logger.atSevere()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_BRELLA_FEDERATED_COMPUTE_VALUE, "CpuProfilingService.java")).log("Unable to read file %s", this.traceFile);
                    }
                }
            } else {
                ((GoogleLogger.Api) ((GoogleLogger.Api) CpuProfilingService.logger.atSevere()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionEndTask", "run", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CROWDSOURCE_PUBLIC_PHOTO_VALUE, "CpuProfilingService.java")).log("Missing trace file");
            }
            deviceMetadata.setSamplesPerEpoch(((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSamplesPerEpoch()).setSampleFrequency(((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleFrequencyMicro());
            long j = this.actualStartTiemMs;
            if (currentTimeMillis - j < 2147483647L) {
                deviceMetadata.setSampleDurationActual((int) (currentTimeMillis - j));
            } else {
                deviceMetadata.setSampleDurationActual(-1);
            }
            deviceMetadata.setSampleDurationScheduled(((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getSampleDurationMs()).setSampleBufferSize(((CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get()).getMaxBufferSizeBytes());
            if (deviceMetadata.getTraceBlob().size() > 0) {
                PrimesExecutors.logFailures(CpuProfilingService.this.metricRecorder.recordMetric(Metric.newBuilder().setMetric((SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setCpuProfilingMetric(deviceMetadata).build()).build()));
            }
            CpuProfilingService.this.scheduleNextMonitoringWindow(false);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CpuCollectionStartTask implements Runnable {
        private final long nextWindowMillis;

        private CpuCollectionStartTask(long j) {
            CpuProfilingService.this = r1;
            this.nextWindowMillis = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) CpuProfilingService.this.configsProvider.get();
            long sampleDurationMs = this.nextWindowMillis + cpuProfilingConfigurations.getSampleDurationMs();
            long currentTimeMillis = CpuProfilingService.this.clock.currentTimeMillis();
            if (sampleDurationMs > currentTimeMillis) {
                Intent registerReceiver = CpuProfilingService.this.application.registerReceiver(null, CpuProfilingService.this.batteryIntentFilter);
                CpuProfiling$DeviceMetadata createAndInitDeviceMetadata = CpuProfilingService.this.createAndInitDeviceMetadata(registerReceiver);
                Optional optional = (Optional) CpuProfilingService.this.traceFileSupplier.get();
                if (!optional.isPresent()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) CpuProfilingService.logger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService$CpuCollectionStartTask", "run", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_CONTACTS_ACCOUNT_TYPE_LOGGING_VALUE, "CpuProfilingService.java")).log("Can't create file, aborting method sampling");
                    return;
                }
                File file = (File) optional.get();
                CpuProfilingService.clearFileAndSwallowResultingExceptions(file);
                Debug.startMethodTracingSampling(file.getAbsolutePath(), cpuProfilingConfigurations.getMaxBufferSizeBytes(), cpuProfilingConfigurations.getSampleFrequencyMicro());
                ListeningScheduledExecutorService listeningScheduledExecutorService = CpuProfilingService.this.executorService;
                CpuProfilingService cpuProfilingService = CpuProfilingService.this;
                PrimesExecutors.logFailuresOfNonVoidFuture(listeningScheduledExecutorService.schedule((Runnable) new CpuCollectionEndTask(file, createAndInitDeviceMetadata, Float.valueOf(cpuProfilingService.getBatteryPercent(registerReceiver)), sampleDurationMs, currentTimeMillis), sampleDurationMs - currentTimeMillis, TimeUnit.MILLISECONDS));
                return;
            }
            CpuProfilingService.this.scheduleNextMonitoringWindow(false);
        }
    }

    @Inject
    public CpuProfilingService(MetricRecorderFactory metricRecorderFactory, final Context context, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Provider provider, Clock clock, final Provider provider2) {
        this.metricRecorder = metricRecorderFactory.create(listeningScheduledExecutorService, lazy, provider);
        this.application = (Application) context;
        this.executorService = listeningScheduledExecutorService;
        this.configsProvider = lazy;
        this.clock = clock;
        this.traceFileSupplier = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return CpuProfilingService.this.m325x46f526a9(context);
            }
        });
        this.schedulerSupplier = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return CpuProfilingService.lambda$new$1(Provider.this);
            }
        });
    }

    public static void clearFileAndSwallowResultingExceptions(File file) {
        try {
            if (!file.exists()) {
                return;
            }
            file.delete();
        } catch (RuntimeException e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService", "clearFileAndSwallowResultingExceptions", 368, "CpuProfilingService.java")).log("Exception when clearing trace file.");
        }
    }

    public CpuProfiling$DeviceMetadata createAndInitDeviceMetadata(Intent intent) {
        return (CpuProfiling$DeviceMetadata) CpuProfiling$DeviceMetadata.newBuilder().setBeforeState(getDeviceState(intent)).build();
    }

    public float getBatteryPercent(Intent intent) {
        return intent.getIntExtra("level", -1) / intent.getIntExtra("scale", -1);
    }

    public CpuProfiling$DeviceState getDeviceState(Intent intent) {
        CpuProfiling$DeviceState.Builder wifiOn = CpuProfiling$DeviceState.newBuilder().setWifiOn(getWifiManager().isWifiEnabled());
        if (ContextCompat.checkSelfPermission(this.application, "android.permission.BLUETOOTH") == 0) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            wifiOn.setBluetoothOn(defaultAdapter != null && defaultAdapter.isEnabled());
        }
        return (CpuProfiling$DeviceState) wifiOn.setScreenOn(ProcessStats.isScreenOn(this.application)).setCharging(isCharging(intent)).build();
    }

    private WifiManager getWifiManager() {
        if (this.wifi == null) {
            this.wifi = (WifiManager) this.application.getSystemService("wifi");
        }
        return this.wifi;
    }

    private boolean isCharging(Intent intent) {
        int intExtra = intent.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }

    public static /* synthetic */ CpuProfilingServiceScheduler lambda$new$1(Provider provider) {
        return (CpuProfilingServiceScheduler) provider.get();
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0054, code lost:
        r6.scheduled.set(true);
        com.google.android.libraries.performance.primes.PrimesExecutors.logFailuresOfNonVoidFuture(r6.executorService.schedule((java.lang.Runnable) new com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService.CpuCollectionStartTask(r0.longValue()), r1, java.util.concurrent.TimeUnit.MILLISECONDS));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void scheduleNextMonitoringWindow(boolean z) {
        if (shouldCollectMetric()) {
            if (z) {
                Optional optional = (Optional) this.traceFileSupplier.get();
                if (optional.isPresent()) {
                    clearFileAndSwallowResultingExceptions((File) optional.get());
                }
            }
            if (this.scheduled.get()) {
                return;
            }
            int i = 0;
            while (true) {
                if (i < 5) {
                    Long nextWindow = ((CpuProfilingServiceScheduler) this.schedulerSupplier.get()).getNextWindow();
                    if (nextWindow != null) {
                        long longValue = nextWindow.longValue() - this.clock.currentTimeMillis();
                        if (longValue > 0) {
                            break;
                        }
                        i++;
                    } else {
                        return;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private boolean shouldCollectMetric() {
        CpuProfilingConfigurations cpuProfilingConfigurations = (CpuProfilingConfigurations) this.configsProvider.get();
        return cpuProfilingConfigurations.isEnabled() && this.metricRecorder.shouldCollectMetric(null) && cpuProfilingConfigurations.getMaxBufferSizeBytes() > 0 && cpuProfilingConfigurations.getMaxBufferSizeBytes() <= 3145728 && cpuProfilingConfigurations.getSampleDurationMs() > 0 && cpuProfilingConfigurations.getSampleFrequencyMicro() > 0 && cpuProfilingConfigurations.getSamplesPerEpoch() > 0.0d;
    }

    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-cpuprofiling-CpuProfilingService */
    public /* synthetic */ Optional m325x46f526a9(Context context) {
        synchronized (this) {
            String currentProcessName = ProcessStats.getCurrentProcessName();
            String str = currentProcessName + ".trace";
            File file = new File(context.getFilesDir(), "primes_profiling_" + currentProcessName);
            if (!file.exists() && !file.mkdir()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/cpuprofiling/CpuProfilingService", "lambda$new$0", 117, "CpuProfilingService.java")).log("Could not create directory");
                return Optional.absent();
            }
            File file2 = new File(file, str);
            file2.deleteOnExit();
            clearFileAndSwallowResultingExceptions(file2);
            return Optional.of(file2);
        }
    }

    /* renamed from: lambda$onApplicationStartup$2$com-google-android-libraries-performance-primes-metrics-cpuprofiling-CpuProfilingService */
    public /* synthetic */ void m326x7e6d061() {
        scheduleNextMonitoringWindow(true);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        PrimesExecutors.logFailures(Futures.submit(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CpuProfilingService.this.m326x7e6d061();
            }
        }, this.executorService));
    }
}
