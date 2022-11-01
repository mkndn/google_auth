package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RootTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new RootTelemetryConfigurationCreator();
    private final int batchPeriodMillis;
    private final int maxMethodInvocationsInBatch;
    private final boolean methodInvocationTelemetryEnabled;
    private final boolean methodTimingTelemetryEnabled;
    private final int version;

    public RootTelemetryConfiguration(int i, boolean z, boolean z2, int i2, int i3) {
        this.version = i;
        this.methodInvocationTelemetryEnabled = z;
        this.methodTimingTelemetryEnabled = z2;
        this.batchPeriodMillis = i2;
        this.maxMethodInvocationsInBatch = i3;
    }

    public int getBatchPeriodMillis() {
        return this.batchPeriodMillis;
    }

    public int getMaxMethodInvocationsInBatch() {
        return this.maxMethodInvocationsInBatch;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.methodInvocationTelemetryEnabled;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.methodTimingTelemetryEnabled;
    }

    public int getVersion() {
        return this.version;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        RootTelemetryConfigurationCreator.writeToParcel(this, parcel, i);
    }
}
