package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.common.base.Supplier;
import logs.proto.wireless.performance.mobile.SystemHealthMetricCollectionBasisHelper$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda0 implements Supplier {
    public static final /* synthetic */ ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda0 INSTANCE = new ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda0();

    private /* synthetic */ ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return SystemHealthMetricCollectionBasisHelper$SystemHealthMetric.getInstance();
    }
}
