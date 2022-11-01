package com.google.android.libraries.performance.primes.transmitter.clearcut;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.libraries.performance.primes.TestingInstrumentationHolder;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricReencoder;
import com.google.android.libraries.performance.primes.transmitter.AccountProvider;
import com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider;
import com.google.android.libraries.performance.primes.transmitter.LifeboatReceiver;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import googledata.experiments.mobile.primes_android.features.MetricTransmitterFeature;
import javax.inject.Inject;
import javax.inject.Singleton;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
@Singleton
/* loaded from: classes.dex */
public final class ClearcutMetricTransmitter implements MetricTransmitter {
    private final Context context;
    private final boolean lifeboatEnabled;
    private final ClearcutMetricSnapshotBuilder snapshotBuilder;
    private final ClearcutMetricSnapshotTransmitter snapshotTransmitter;
    private final Supplier usePackedHistogramEncodingInClearcut;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        private AccountProvider accountProvider;
        private boolean anonymous;
        private ClearcutMetricSnapshotTransmitter clearcutMetricSnapshotTransmitter;
        private Context context;
        private ExperimentsProvider experimentsProvider;
        private boolean lifeboatEnabled;
        private String logSource;
        private ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider;

        private Builder() {
            this.accountProvider = AccountProvider.NOOP_PROVIDER;
            this.experimentsProvider = ExperimentsProvider.NOOP_PROVIDER;
            this.zwiebackCookieOverrideProvider = ZwiebackCookieOverrideProvider.NOOP_PROVIDER;
        }

        public ClearcutMetricTransmitter build() {
            Context context = this.context;
            Optional of = Optional.of(Boolean.valueOf(this.lifeboatEnabled));
            ClearcutMetricSnapshotBuilder clearcutMetricSnapshotBuilder = new ClearcutMetricSnapshotBuilder(this.context.getPackageName(), this.logSource, this.accountProvider, this.experimentsProvider, this.zwiebackCookieOverrideProvider, this.anonymous);
            ClearcutMetricSnapshotTransmitter clearcutMetricSnapshotTransmitter = this.clearcutMetricSnapshotTransmitter;
            if (clearcutMetricSnapshotTransmitter == null) {
                clearcutMetricSnapshotTransmitter = new ClearcutMetricSnapshotTransmitter(null);
            }
            return new ClearcutMetricTransmitter(context, of, clearcutMetricSnapshotBuilder, clearcutMetricSnapshotTransmitter);
        }

        public Builder setApplicationContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setLogSource(String str) {
            this.logSource = str;
            return this;
        }
    }

    @Inject
    ClearcutMetricTransmitter(final Context context, Optional optional, ClearcutMetricSnapshotBuilder clearcutMetricSnapshotBuilder, ClearcutMetricSnapshotTransmitter clearcutMetricSnapshotTransmitter) {
        this.context = context;
        this.usePackedHistogramEncodingInClearcut = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return ClearcutMetricTransmitter.lambda$new$0(context);
            }
        });
        this.lifeboatEnabled = shouldEnableLifeboat(optional);
        this.snapshotBuilder = clearcutMetricSnapshotBuilder;
        this.snapshotTransmitter = clearcutMetricSnapshotTransmitter;
    }

    public static /* synthetic */ Boolean lambda$new$0(Context context) {
        return Boolean.valueOf(MetricTransmitterFeature.usePackedHistogramEncodingInClearcut(context));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private ListenableFuture sendCrashViaLifeboat(final SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        return Futures.transform(this.snapshotBuilder.buildExtension(), new Function() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ClearcutMetricTransmitter.this.m363x42972220(systemHealthProto$SystemHealthMetric, (MetricSnapshot) obj);
            }
        }, MoreExecutors.directExecutor());
    }

    static boolean shouldEnableLifeboat(Optional optional) {
        if (TestingInstrumentationHolder.instance != null) {
            if (TestingInstrumentationHolder.instance.allowSpawningLifeboat()) {
                return optional.isPresent();
            }
            return false;
        }
        return ((Boolean) optional.or(false)).booleanValue();
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public MetricTransmitter.Priority getTransmitterPriority() {
        return new MetricTransmitter.Priority(9);
    }

    /* renamed from: lambda$send$1$com-google-android-libraries-performance-primes-transmitter-clearcut-ClearcutMetricTransmitter */
    public /* synthetic */ ListenableFuture m362xbf2a9244(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric, MetricSnapshot metricSnapshot) {
        return this.snapshotTransmitter.transmit(this.context, (MetricSnapshot) ((MetricSnapshot.Builder) metricSnapshot.toBuilder()).setSystemHealthMetric(systemHealthProto$SystemHealthMetric).build());
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public ListenableFuture send(final SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        if (this.lifeboatEnabled && systemHealthProto$SystemHealthMetric.getCrashMetric().hasHasCrashed()) {
            return sendCrashViaLifeboat(systemHealthProto$SystemHealthMetric);
        }
        if (systemHealthProto$SystemHealthMetric.hasJankMetric() && ((Boolean) this.usePackedHistogramEncodingInClearcut.get()).booleanValue()) {
            systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) ((SystemHealthProto$SystemHealthMetric.Builder) systemHealthProto$SystemHealthMetric.toBuilder()).setJankMetric(JankMetricReencoder.packJankHistogram(systemHealthProto$SystemHealthMetric.getJankMetric())).build();
        }
        ListenableFuture transformAsync = Futures.transformAsync(this.snapshotBuilder.buildExtension(), new AsyncFunction() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter$$ExternalSyntheticLambda1
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                return ClearcutMetricTransmitter.this.m362xbf2a9244(systemHealthProto$SystemHealthMetric, (MetricSnapshot) obj);
            }
        }, MoreExecutors.directExecutor());
        if (Log.isLoggable("ClearcutMetricXmitter", 4)) {
            Futures.addCallback(transformAsync, new FutureCallback(this) { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter.1
                @Override // com.google.common.util.concurrent.FutureCallback
                public void onFailure(Throwable th) {
                    if (Log.isLoggable("ClearcutMetricXmitter", 4)) {
                        Log.i("ClearcutMetricXmitter", "Transmission has failed: " + String.valueOf(th));
                    }
                }

                @Override // com.google.common.util.concurrent.FutureCallback
                public void onSuccess(Void r2) {
                    Log.v("ClearcutMetricXmitter", "Transmission is done.");
                }
            }, MoreExecutors.directExecutor());
        }
        return transformAsync;
    }

    /* renamed from: lambda$sendCrashViaLifeboat$2$com-google-android-libraries-performance-primes-transmitter-clearcut-ClearcutMetricTransmitter */
    public /* synthetic */ Void m363x42972220(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric, MetricSnapshot metricSnapshot) {
        String[] strArr = {this.snapshotTransmitter.getClass().getName()};
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.context, LifeboatReceiver.class));
        intent.setPackage(this.context.getPackageName());
        intent.putExtra("Transmitters", strArr);
        intent.putExtra("MetricSnapshot", ((MetricSnapshot) ((MetricSnapshot.Builder) metricSnapshot.toBuilder()).setSystemHealthMetric(systemHealthProto$SystemHealthMetric).build()).toByteArray());
        this.context.sendBroadcast(intent);
        return null;
    }
}
