package com.google.android.libraries.performance.primes.metrics.memory;

import com.google.common.flogger.GoogleLogger;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MemoryEvent {
    private final MemoryMetric$MemoryUsageMetric capture;
    private final long createTimestamp;
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/memory/MemoryEvent");
    static final MemoryEvent EMPTY_SNAPSHOT = new MemoryEvent(0, null);

    private MemoryEvent(long j, MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric) {
        this.createTimestamp = j;
        this.capture = memoryMetric$MemoryUsageMetric;
    }
}
