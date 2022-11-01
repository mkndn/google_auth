package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BackedUpContactsPerDeviceCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(BackedUpContactsPerDeviceEntity backedUpContactsPerDeviceEntity, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, backedUpContactsPerDeviceEntity.getDeviceId(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, backedUpContactsPerDeviceEntity.getSourceStats(), false);
        SafeParcelWriter.writeString(parcel, 4, backedUpContactsPerDeviceEntity.getDeviceDisplayName(), false);
        SafeParcelWriter.writeLongObject(parcel, 5, backedUpContactsPerDeviceEntity.getLastUpdatedTimestampMs(), false);
        SafeParcelWriter.writeLongObject(parcel, 6, backedUpContactsPerDeviceEntity.getLastRestoreTimestampMs(), false);
        SafeParcelWriter.writeLongObject(parcel, 7, backedUpContactsPerDeviceEntity.getAndroidDeviceId(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, backedUpContactsPerDeviceEntity.getDeviceVersion(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public BackedUpContactsPerDeviceEntity createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        Long l = null;
        ArrayList arrayList = null;
        String str2 = null;
        Long l2 = null;
        Long l3 = null;
        DeviceVersionEntity deviceVersionEntity = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, SourceStatsEntity.CREATOR);
                    break;
                case 4:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    l2 = SafeParcelReader.readLongObject(parcel, readHeader);
                    break;
                case 6:
                    l3 = SafeParcelReader.readLongObject(parcel, readHeader);
                    break;
                case 7:
                    l = SafeParcelReader.readLongObject(parcel, readHeader);
                    break;
                case 8:
                    deviceVersionEntity = (DeviceVersionEntity) SafeParcelReader.createParcelable(parcel, readHeader, DeviceVersionEntity.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BackedUpContactsPerDeviceEntity(str, l, arrayList, str2, l2, l3, deviceVersionEntity);
    }

    @Override // android.os.Parcelable.Creator
    public BackedUpContactsPerDeviceEntity[] newArray(int i) {
        return new BackedUpContactsPerDeviceEntity[i];
    }
}
