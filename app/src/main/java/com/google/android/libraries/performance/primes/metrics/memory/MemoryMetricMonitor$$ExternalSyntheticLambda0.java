package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class MemoryMetricMonitor$$ExternalSyntheticLambda0 implements MemoryMetricMonitor.Callback {
    public static final /* synthetic */ MemoryMetricMonitor$$ExternalSyntheticLambda0 INSTANCE = new MemoryMetricMonitor$$ExternalSyntheticLambda0();

    private /* synthetic */ MemoryMetricMonitor$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricMonitor.Callback
    public final void onEvent(MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode, String str) {
        MemoryMetricMonitor.lambda$static$0(memoryEventCode, str);
    }
}
