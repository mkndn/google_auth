package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda1 implements Supplier {
    public static final /* synthetic */ ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda1 INSTANCE = new ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda1();

    private /* synthetic */ ClearcutMetricSnapshotTransmitter$$ExternalSyntheticLambda1() {
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        boolean isUserAMonkeyOrRunningInTestHarness;
        isUserAMonkeyOrRunningInTestHarness = ClearcutMetricSnapshotTransmitter.isUserAMonkeyOrRunningInTestHarness();
        return Boolean.valueOf(isUserAMonkeyOrRunningInTestHarness);
    }
}
