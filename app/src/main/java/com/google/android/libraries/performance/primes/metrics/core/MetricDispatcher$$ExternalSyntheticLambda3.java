package com.google.android.libraries.performance.primes.metrics.core;

import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import java.util.Comparator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MetricDispatcher$$ExternalSyntheticLambda3 implements Comparator {
    public static final /* synthetic */ MetricDispatcher$$ExternalSyntheticLambda3 INSTANCE = new MetricDispatcher$$ExternalSyntheticLambda3();

    private /* synthetic */ MetricDispatcher$$ExternalSyntheticLambda3() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return MetricDispatcher.lambda$new$0((MetricTransmitter) obj, (MetricTransmitter) obj2);
    }
}
