package com.google.android.libraries.performance.primes.transmitter.clearcut;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.libraries.consentverifier.ProtoCollectionBasis;
import com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier;
import com.google.android.libraries.gmstasks.TaskFutures;
import com.google.android.libraries.performance.primes.TestingInstrumentationHolder;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshotTransmitter;
import com.google.android.libraries.performance.primes.transmitter.impl.EventSanitizer;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.flogger.GoogleLogger;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import googledata.experiments.mobile.primes_android.features.MetricTransmitterFeature;
import java.util.List;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class ClearcutMetricSnapshotTransmitter implements MetricSnapshotTransmitter {
    private volatile ClearcutLogger clearcutLogger;
    private volatile ClearcutLogger deidentifiedClearcutLogger;
    private final Supplier isUserAMonkeyOrRunningInTestHarness;
    private final ClearcutLogger testClearcutLogger;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotTransmitter");
    private static final Supplier PROTO_COLLECTION_BASIS = Suppliers.memoize(ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda0.INSTANCE);

    @Inject
    ClearcutMetricSnapshotTransmitter() {
        this(null);
    }

    private ClearcutLogger getClearcutLogger(Context context, String str) {
        ClearcutLogger clearcutLogger = this.clearcutLogger;
        if (clearcutLogger == null) {
            synchronized (this) {
                clearcutLogger = this.clearcutLogger;
                if (clearcutLogger == null) {
                    ClearcutLogger build = ClearcutLogger.newBuilder(context, str).build();
                    this.clearcutLogger = build;
                    clearcutLogger = build;
                }
            }
        }
        return clearcutLogger;
    }

    private ClearcutLogger getDeidentifiedClearcutLogger(Context context, String str) {
        ClearcutLogger clearcutLogger = this.deidentifiedClearcutLogger;
        if (clearcutLogger == null) {
            synchronized (this) {
                clearcutLogger = this.deidentifiedClearcutLogger;
                if (clearcutLogger == null) {
                    ClearcutLogger deidentifiedLogger = ClearcutLogger.deidentifiedLogger(context, str);
                    this.deidentifiedClearcutLogger = deidentifiedLogger;
                    clearcutLogger = deidentifiedLogger;
                }
            }
        }
        return clearcutLogger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isUserAMonkeyOrRunningInTestHarness() {
        if (ActivityManager.isUserAMonkey()) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 29) {
            return ActivityManager.isRunningInTestHarness();
        }
        return ActivityManager.isRunningInUserTestHarness();
    }

    private static void logSystemHealthMetric(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        String str;
        GoogleLogger googleLogger = logger;
        if (!((GoogleLogger.Api) googleLogger.atFinest()).isEnabled()) {
            return;
        }
        if (systemHealthProto$SystemHealthMetric.hasPrimesStats()) {
            str = "primes stats";
        } else {
            str = null;
        }
        if (systemHealthProto$SystemHealthMetric.hasNetworkUsageMetric()) {
            str = "network metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasTimerMetric()) {
            str = "timer metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasMemoryUsageMetric()) {
            str = "memory metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasBatteryUsageMetric()) {
            str = "battery metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasCrashMetric()) {
            str = "crash metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasJankMetric()) {
            str = "jank metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasPackageMetric()) {
            str = "package metric";
        }
        if (systemHealthProto$SystemHealthMetric.hasPrimesTrace()) {
            str = "trace";
        }
        if (str == null) {
            str = "unknown";
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) googleLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotTransmitter", "logSystemHealthMetric", AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FMD_UNPAIR_VALUE, "ClearcutMetricSnapshotTransmitter.java")).log("Sending Primes %s: %s", str, systemHealthProto$SystemHealthMetric);
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricSnapshotTransmitter
    public ListenableFuture transmit(Context context, MetricSnapshot metricSnapshot) {
        Preconditions.checkArgument(metricSnapshot.hasExtension(ClearcutMetricSnapshot.clearcutMetricSnapshot), "ClearcutMetricSnapshotTransmitter received a snapshot without the expected extension.");
        SystemHealthProto$SystemHealthMetric sanitize = EventSanitizer.sanitize(metricSnapshot.getSystemHealthMetric());
        logSystemHealthMetric(sanitize);
        if (TestingInstrumentationHolder.instance != null) {
            TestingInstrumentationHolder.instance.log(sanitize);
            return Futures.immediateVoidFuture();
        } else if (MetricTransmitterFeature.disableClearcutLoggingInTests(context) && ((Boolean) this.isUserAMonkeyOrRunningInTestHarness.get()).booleanValue()) {
            return Futures.immediateVoidFuture();
        } else {
            ClearcutMetricSnapshot clearcutMetricSnapshot = (ClearcutMetricSnapshot) metricSnapshot.getExtension(ClearcutMetricSnapshot.clearcutMetricSnapshot);
            GoogleLogger googleLogger = logger;
            if (((GoogleLogger.Api) googleLogger.atFinest()).isEnabled()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) googleLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotTransmitter", "transmit", 99, "ClearcutMetricSnapshotTransmitter.java")).log("%s", Base64.encodeToString(sanitize.toByteArray(), 2));
            }
            ClearcutLogger.LogEventBuilder newEvent = getClearcutLogger(context, clearcutMetricSnapshot.getLogSource(), clearcutMetricSnapshot.getAnonymous()).newEvent(sanitize);
            if (MetricTransmitterFeature.enableDelphiCollectionBasisLogVerifier(context)) {
                newEvent.setLogVerifier(CollectionBasisLogVerifier.newInstance(context, (ProtoCollectionBasis) PROTO_COLLECTION_BASIS.get()));
            }
            String zwiebackCookieOverride = clearcutMetricSnapshot.getZwiebackCookieOverride();
            if (!Strings.isNullOrEmpty(zwiebackCookieOverride)) {
                newEvent.setZwiebackCookieOverride(zwiebackCookieOverride);
            }
            if (!clearcutMetricSnapshot.getAnonymous()) {
                if (clearcutMetricSnapshot.hasMendelPackageName()) {
                    newEvent.addMendelPackage(clearcutMetricSnapshot.getMendelPackageName());
                }
                if (clearcutMetricSnapshot.hasUploadAccountName()) {
                    newEvent.setUploadAccountName(clearcutMetricSnapshot.getUploadAccountName());
                }
                List experimentIdsList = clearcutMetricSnapshot.getExperimentIdsList();
                if (!experimentIdsList.isEmpty()) {
                    newEvent.addExperimentIds(Ints.toArray(experimentIdsList));
                }
            }
            return TaskFutures.toListenableFuture(newEvent.logAsyncTask());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClearcutMetricSnapshotTransmitter(ClearcutLogger clearcutLogger) {
        this.testClearcutLogger = clearcutLogger;
        this.isUserAMonkeyOrRunningInTestHarness = Suppliers.memoize(ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda1.INSTANCE);
    }

    private ClearcutLogger getClearcutLogger(Context context, String str, boolean z) {
        ClearcutLogger clearcutLogger = this.testClearcutLogger;
        if (clearcutLogger != null) {
            return clearcutLogger;
        }
        if (z) {
            return getDeidentifiedClearcutLogger(context, str);
        }
        return getClearcutLogger(context, str);
    }
}
