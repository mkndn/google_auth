package com.google.android.libraries.performance.primes.metrics.battery;

import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BatteryMetricExtensionProvider {
    ExtensionMetric$MetricExtension getMetricExtension(String str, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo);
}
