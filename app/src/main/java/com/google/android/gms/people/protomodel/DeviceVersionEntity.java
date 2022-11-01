package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DeviceVersionEntity extends AbstractSafeParcelable implements DeviceVersion {
    public static final Parcelable.Creator CREATOR = new DeviceVersionCreator();
    private final Integer mSdkVersion;

    public DeviceVersionEntity(Integer num) {
        this.mSdkVersion = num;
    }

    public static boolean equals(DeviceVersion deviceVersion, DeviceVersion deviceVersion2) {
        return Objects.equal(deviceVersion.getSdkVersion(), deviceVersion2.getSdkVersion());
    }

    @Override // com.google.android.gms.people.protomodel.DeviceVersion
    public Integer getSdkVersion() {
        return this.mSdkVersion;
    }

    public int hashCode() {
        return hashCode(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        DeviceVersionCreator.writeToParcel(this, parcel, i);
    }

    public static int hashCode(DeviceVersion deviceVersion) {
        return Objects.hashCode(deviceVersion.getSdkVersion());
    }

    public boolean equals(Object obj) {
        if (obj instanceof DeviceVersion) {
            if (this == obj) {
                return true;
            }
            return equals(this, (DeviceVersion) obj);
        }
        return false;
    }
}
