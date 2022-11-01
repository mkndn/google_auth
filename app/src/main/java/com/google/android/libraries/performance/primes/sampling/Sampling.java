package com.google.android.libraries.performance.primes.sampling;

import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Sampling {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static SystemHealthProto$SamplingParameters samplingParametersIfShouldRecord(boolean z, SamplingStrategy samplingStrategy, Long l) {
        if (z) {
            return samplingStrategy.getSamplingParametersIfShouldRecord(l);
        }
        return samplingStrategy.getSamplingOffParameters();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long samplingRatePermilleIfShouldCollect(boolean z, SamplingStrategy samplingStrategy, String str) {
        if (!z) {
            return -1L;
        }
        return samplingStrategy.getSamplingRatePermilleIfShouldSample(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldRecord(boolean z, SamplingStrategy samplingStrategy) {
        return z && samplingStrategy.getMetricServiceEnabled();
    }
}
