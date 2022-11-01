package com.google.android.libraries.performance.primes.transmitter;

import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MetricTransmitter$$ExternalSyntheticLambda0 implements MetricTransmitter {
    public static final /* synthetic */ MetricTransmitter$$ExternalSyntheticLambda0 INSTANCE = new MetricTransmitter$$ExternalSyntheticLambda0();

    private /* synthetic */ MetricTransmitter$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public /* synthetic */ MetricTransmitter.Priority getTransmitterPriority() {
        MetricTransmitter.Priority priority;
        priority = MetricTransmitter.Priority.DEFAULT;
        return priority;
    }

    @Override // com.google.android.libraries.performance.primes.transmitter.MetricTransmitter
    public final ListenableFuture send(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric) {
        ListenableFuture immediateVoidFuture;
        immediateVoidFuture = Futures.immediateVoidFuture();
        return immediateVoidFuture;
    }
}
