package com.google.android.libraries.performance.primes.metrics.battery;

import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BatteryConfigurations$$ExternalSyntheticLambda0 implements BatteryMetricExtensionProvider {
    public static final /* synthetic */ BatteryConfigurations$$ExternalSyntheticLambda0 INSTANCE = new BatteryConfigurations$$ExternalSyntheticLambda0();

    private /* synthetic */ BatteryConfigurations$$ExternalSyntheticLambda0() {
    }

    @Override // com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricExtensionProvider
    public final ExtensionMetric$MetricExtension getMetricExtension(String str, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo) {
        return BatteryConfigurations.lambda$newBuilder$0(str, sampleInfo);
    }
}
