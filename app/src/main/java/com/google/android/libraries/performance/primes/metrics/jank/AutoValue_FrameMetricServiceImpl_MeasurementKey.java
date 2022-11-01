package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoValue_FrameMetricServiceImpl_MeasurementKey extends FrameMetricServiceImpl.MeasurementKey {
    private final boolean isActivity;
    private final NoPiiString noPiiEventName;
    private final String rawStringEventName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FrameMetricServiceImpl_MeasurementKey(String str, NoPiiString noPiiString, boolean z) {
        this.rawStringEventName = str;
        this.noPiiEventName = noPiiString;
        this.isActivity = z;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.MeasurementKey
    boolean isActivity() {
        return this.isActivity;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.MeasurementKey
    NoPiiString noPiiEventName() {
        return this.noPiiEventName;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl.MeasurementKey
    String rawStringEventName() {
        return this.rawStringEventName;
    }

    public String toString() {
        String str = this.rawStringEventName;
        String valueOf = String.valueOf(this.noPiiEventName);
        return "MeasurementKey{rawStringEventName=" + str + ", noPiiEventName=" + valueOf + ", isActivity=" + this.isActivity + "}";
    }
}
