package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TelemetryData extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new TelemetryDataCreator();
    private List methodInvocations;
    private final int telemetryConfigVersion;

    public TelemetryData(int i, List list) {
        this.telemetryConfigVersion = i;
        this.methodInvocations = list;
    }

    public void addMethodInvocation(MethodInvocation methodInvocation) {
        if (this.methodInvocations == null) {
            this.methodInvocations = new ArrayList();
        }
        this.methodInvocations.add(methodInvocation);
    }

    public List getMethodInvocations() {
        return this.methodInvocations;
    }

    public int getTelemetryConfigVersion() {
        return this.telemetryConfigVersion;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        TelemetryDataCreator.writeToParcel(this, parcel, i);
    }
}
