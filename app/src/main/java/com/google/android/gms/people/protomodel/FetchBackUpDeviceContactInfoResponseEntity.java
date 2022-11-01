package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FetchBackUpDeviceContactInfoResponseEntity extends AbstractSafeParcelable implements FetchBackUpDeviceContactInfoResponse {
    public static final Parcelable.Creator CREATOR = new FetchBackUpDeviceContactInfoResponseCreator();
    private final List mContactsPerDevice;
    private List mContactsPerDeviceInternal;

    public FetchBackUpDeviceContactInfoResponseEntity(List list) {
        this.mContactsPerDevice = list;
    }

    public static boolean equals(FetchBackUpDeviceContactInfoResponse fetchBackUpDeviceContactInfoResponse, FetchBackUpDeviceContactInfoResponse fetchBackUpDeviceContactInfoResponse2) {
        return Objects.equal(fetchBackUpDeviceContactInfoResponse.getContactsPerDevice(), fetchBackUpDeviceContactInfoResponse2.getContactsPerDevice());
    }

    @Override // com.google.android.gms.people.protomodel.FetchBackUpDeviceContactInfoResponse
    public List getContactsPerDevice() {
        if (this.mContactsPerDeviceInternal == null && this.mContactsPerDevice != null) {
            this.mContactsPerDeviceInternal = new ArrayList(this.mContactsPerDevice.size());
            for (BackedUpContactsPerDevice backedUpContactsPerDevice : this.mContactsPerDevice) {
                this.mContactsPerDeviceInternal.add(backedUpContactsPerDevice);
            }
        }
        return this.mContactsPerDeviceInternal;
    }

    public int hashCode() {
        return hashCode(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        FetchBackUpDeviceContactInfoResponseCreator.writeToParcel(this, parcel, i);
    }

    public static int hashCode(FetchBackUpDeviceContactInfoResponse fetchBackUpDeviceContactInfoResponse) {
        return Objects.hashCode(fetchBackUpDeviceContactInfoResponse.getContactsPerDevice());
    }

    public boolean equals(Object obj) {
        if (obj instanceof FetchBackUpDeviceContactInfoResponse) {
            if (this == obj) {
                return true;
            }
            return equals(this, (FetchBackUpDeviceContactInfoResponse) obj);
        }
        return false;
    }
}
