package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FRDProductSpecificDataEntryCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(FRDProductSpecificDataEntry fRDProductSpecificDataEntry, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, fRDProductSpecificDataEntry.frdIdentifier);
        SafeParcelWriter.writeInt(parcel, 3, fRDProductSpecificDataEntry.frdType);
        SafeParcelWriter.writeStringList(parcel, 4, fRDProductSpecificDataEntry.enumValueIdentifiers, false);
        SafeParcelWriter.writeLongList(parcel, 5, fRDProductSpecificDataEntry.intValues, false);
        SafeParcelWriter.writeStringList(parcel, 6, fRDProductSpecificDataEntry.stringValues, false);
        SafeParcelWriter.writeLongList(parcel, 7, fRDProductSpecificDataEntry.dateTimeValues, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 8, fRDProductSpecificDataEntry.bytesValues, false);
        SafeParcelWriter.writeBooleanObject(parcel, 9, fRDProductSpecificDataEntry.boolValue, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public FRDProductSpecificDataEntry createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Boolean bool = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        ArrayList arrayList4 = null;
        byte[][] bArr = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    arrayList = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 5:
                    arrayList2 = SafeParcelReader.createLongList(parcel, readHeader);
                    break;
                case 6:
                    arrayList3 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 7:
                    arrayList4 = SafeParcelReader.createLongList(parcel, readHeader);
                    break;
                case 8:
                    bArr = SafeParcelReader.createByteArrayArray(parcel, readHeader);
                    break;
                case 9:
                    bool = SafeParcelReader.readBooleanObject(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new FRDProductSpecificDataEntry(i, i2, arrayList, arrayList2, arrayList3, arrayList4, bArr, bool.booleanValue());
    }

    @Override // android.os.Parcelable.Creator
    public FRDProductSpecificDataEntry[] newArray(int i) {
        return new FRDProductSpecificDataEntry[i];
    }
}
