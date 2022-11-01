package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConnectionInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ConnectionInfoCreator();
    Feature[] availableFeatures;
    int binderPropagationProtocol;
    ConnectionTelemetryConfiguration connectionTelemetryConfiguration;
    Bundle resolutionBundle;

    public ConnectionInfo() {
    }

    public Feature[] getAvailableFeatures() {
        return this.availableFeatures;
    }

    public ConnectionTelemetryConfiguration getConnectionTelemetryConfiguration() {
        return this.connectionTelemetryConfiguration;
    }

    public Bundle getResolutionBundle() {
        return this.resolutionBundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ConnectionInfoCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConnectionInfo(Bundle bundle, Feature[] featureArr, int i, ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.resolutionBundle = bundle;
        this.availableFeatures = featureArr;
        this.binderPropagationProtocol = i;
        this.connectionTelemetryConfiguration = connectionTelemetryConfiguration;
    }
}
