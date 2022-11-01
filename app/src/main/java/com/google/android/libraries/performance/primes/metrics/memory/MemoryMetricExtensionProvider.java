package com.google.android.libraries.performance.primes.metrics.memory;

import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MemoryMetricExtensionProvider {
    ExtensionMetric$MetricExtension getMetricExtension(String str, MemoryMetric$MemoryUsageMetric.MemoryEventCode memoryEventCode);
}
