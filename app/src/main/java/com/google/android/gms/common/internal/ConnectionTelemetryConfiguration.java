package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ConnectionTelemetryConfigurationCreator();
    private final int maxMethodInvocationsLogged;
    private final int[] methodInvocationMethodKeyAllowlist;
    private final int[] methodInvocationMethodKeyDisallowlist;
    private final boolean methodInvocationTelemetryEnabled;
    private final boolean methodTimingTelemetryEnabled;
    private final RootTelemetryConfiguration rootTelemetryConfiguration;

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration rootTelemetryConfiguration, boolean z, boolean z2, int[] iArr, int i, int[] iArr2) {
        this.rootTelemetryConfiguration = rootTelemetryConfiguration;
        this.methodInvocationTelemetryEnabled = z;
        this.methodTimingTelemetryEnabled = z2;
        this.methodInvocationMethodKeyAllowlist = iArr;
        this.maxMethodInvocationsLogged = i;
        this.methodInvocationMethodKeyDisallowlist = iArr2;
    }

    public int getMaxMethodInvocationsLogged() {
        return this.maxMethodInvocationsLogged;
    }

    public int[] getMethodInvocationMethodKeyAllowlist() {
        return this.methodInvocationMethodKeyAllowlist;
    }

    public int[] getMethodInvocationMethodKeyDisallowlist() {
        return this.methodInvocationMethodKeyDisallowlist;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.methodInvocationTelemetryEnabled;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.methodTimingTelemetryEnabled;
    }

    public RootTelemetryConfiguration getRootTelemetryConfiguration() {
        return this.rootTelemetryConfiguration;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ConnectionTelemetryConfigurationCreator.writeToParcel(this, parcel, i);
    }
}
