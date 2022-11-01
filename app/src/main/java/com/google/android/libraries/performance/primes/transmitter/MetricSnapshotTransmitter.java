package com.google.android.libraries.performance.primes.transmitter;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MetricSnapshotTransmitter {
    ListenableFuture transmit(Context context, MetricSnapshot metricSnapshot);
}
