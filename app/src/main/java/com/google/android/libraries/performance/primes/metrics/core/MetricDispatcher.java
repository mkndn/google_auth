package com.google.android.libraries.performance.primes.metrics.core;

import android.os.Build;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import dagger.Lazy;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricDispatcher {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/core/MetricDispatcher");
    private final Supplier metricTransmittersSupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public MetricDispatcher(final Lazy lazy) {
        this.metricTransmittersSupplier = Suppliers.memoize(new Supplier() { // from class: com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                ImmutableList sortedCopyOf;
                sortedCopyOf = ImmutableList.sortedCopyOf(MetricDispatcher$$ExternalSyntheticLambda3.INSTANCE, (Iterable) Lazy.this.get());
                return sortedCopyOf;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$dispatch$2() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$new$0(MetricTransmitter metricTransmitter, MetricTransmitter metricTransmitter2) {
        int i;
        int i2;
        if (metricTransmitter.getTransmitterPriority() != null) {
            i = metricTransmitter.getTransmitterPriority().getPriority();
        } else {
            i = 0;
        }
        if (metricTransmitter2.getTransmitterPriority() != null) {
            i2 = metricTransmitter2.getTransmitterPriority().getPriority();
        } else {
            i2 = 0;
        }
        if (i == i2) {
            return 0;
        }
        return i > i2 ? -1 : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ListenableFuture dispatch(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        ImmutableList immutableList = (ImmutableList) this.metricTransmittersSupplier.get();
        ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(immutableList.size());
        UnmodifiableIterator it = immutableList.iterator();
        RuntimeException runtimeException = null;
        while (it.hasNext()) {
            try {
                builderWithExpectedSize.add((Object) ((MetricTransmitter) it.next()).send(systemHealthProto$SystemHealthMetric));
            } catch (RuntimeException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/core/MetricDispatcher", "dispatch", 66, "MetricDispatcher.java")).log("One transmitter failed to send message");
                if (runtimeException != null) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(runtimeException, e);
                    }
                } else {
                    runtimeException = e;
                }
            }
        }
        if (runtimeException == null) {
            return Futures.whenAllSucceed(builderWithExpectedSize.build()).call(MetricDispatcher$$ExternalSyntheticLambda2.INSTANCE, MoreExecutors.directExecutor());
        }
        throw runtimeException;
    }
}
