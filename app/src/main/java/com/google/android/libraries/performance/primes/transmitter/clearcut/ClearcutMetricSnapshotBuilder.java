package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.android.libraries.performance.primes.transmitter.AccountProvider;
import com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshot;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutMetricSnapshotBuilder {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder");
    private final AccountProvider accountProvider;
    private final boolean anonymous;
    private final ExperimentsProvider experimentsProvider;
    private final String logSource;
    private final String mendelPackageName;
    private final ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClearcutMetricSnapshotBuilder(String str, String str2, AccountProvider accountProvider, ExperimentsProvider experimentsProvider, ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider, boolean z) {
        this.logSource = (String) Preconditions.checkNotNull(str2);
        this.accountProvider = (AccountProvider) Preconditions.checkNotNull(accountProvider);
        this.experimentsProvider = (ExperimentsProvider) Preconditions.checkNotNull(experimentsProvider);
        this.zwiebackCookieOverrideProvider = (ZwiebackCookieOverrideProvider) Preconditions.checkNotNull(zwiebackCookieOverrideProvider);
        this.anonymous = z;
        this.mendelPackageName = "com.google.android.libraries.performance.primes#" + str;
    }

    public ListenableFuture buildExtension() {
        final ListenableFuture accountName = this.accountProvider.getAccountName();
        final ListenableFuture experimentIds = this.experimentsProvider.getExperimentIds();
        final ListenableFuture zwiebackCookieOverride = this.zwiebackCookieOverrideProvider.getZwiebackCookieOverride();
        return Futures.whenAllComplete(accountName, experimentIds, zwiebackCookieOverride).call(new Callable() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshotBuilder$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ClearcutMetricSnapshotBuilder.this.m361x4f714222(accountName, experimentIds, zwiebackCookieOverride);
            }
        }, MoreExecutors.directExecutor());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$buildExtension$0$com-google-android-libraries-performance-primes-transmitter-clearcut-ClearcutMetricSnapshotBuilder  reason: not valid java name */
    public /* synthetic */ MetricSnapshot m361x4f714222(ListenableFuture listenableFuture, ListenableFuture listenableFuture2, ListenableFuture listenableFuture3) {
        ClearcutMetricSnapshot.Builder anonymous = ClearcutMetricSnapshot.newBuilder().setLogSource(this.logSource).setMendelPackageName(this.mendelPackageName).setAnonymous(this.anonymous);
        try {
            Optional optional = (Optional) Futures.getDone(listenableFuture);
            if (optional.isPresent()) {
                anonymous.setUploadAccountName((String) optional.get());
            }
        } catch (Exception e) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", 88, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set Account Name, falling back to Zwieback logging.");
        }
        try {
            anonymous.addAllExperimentIds((List) Futures.getDone(listenableFuture2));
        } catch (Exception e2) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", 96, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set external Experiment Ids.");
        }
        try {
            Optional optional2 = (Optional) Futures.getDone(listenableFuture3);
            if (optional2.isPresent()) {
                anonymous.setZwiebackCookieOverride((String) optional2.get());
            }
        } catch (Exception e3) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withCause(e3)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", 106, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set Zwieback.");
        }
        return (MetricSnapshot) ((MetricSnapshot.Builder) MetricSnapshot.newBuilder().setExtension(ClearcutMetricSnapshot.clearcutMetricSnapshot, (ClearcutMetricSnapshot) anonymous.build())).build();
    }
}
