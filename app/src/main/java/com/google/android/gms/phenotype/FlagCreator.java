package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FlagCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    public static boolean isDefault(double d) {
        return d == 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(Flag flag, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        if (!isDefault(flag.name)) {
            SafeParcelWriter.writeString(parcel, 2, flag.name, false);
        }
        if (!isDefault(flag.longValue)) {
            SafeParcelWriter.writeLong(parcel, 3, flag.longValue);
        }
        if (!isDefault(flag.booleanValue)) {
            SafeParcelWriter.writeBoolean(parcel, 4, flag.booleanValue);
        }
        if (!isDefault(flag.doubleValue)) {
            SafeParcelWriter.writeDouble(parcel, 5, flag.doubleValue);
        }
        if (!isDefault(flag.stringValue)) {
            SafeParcelWriter.writeString(parcel, 6, flag.stringValue, false);
        }
        if (!isDefault(flag.bytesValue)) {
            SafeParcelWriter.writeByteArray(parcel, 7, flag.bytesValue, false);
        }
        if (!isDefault(flag.flagValueType)) {
            SafeParcelWriter.writeInt(parcel, 8, flag.flagValueType);
        }
        if (!isDefault(flag.flagStorageType)) {
            SafeParcelWriter.writeInt(parcel, 9, flag.flagStorageType);
        }
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public Flag createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        long j = 0;
        double d = 0.0d;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 5:
                    d = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 8:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 9:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Flag(str, j, z, d, str2, bArr, i, i2);
    }

    @Override // android.os.Parcelable.Creator
    public Flag[] newArray(int i) {
        return new Flag[i];
    }

    public static boolean isDefault(int i) {
        return i == 0;
    }

    public static boolean isDefault(long j) {
        return j == 0;
    }

    public static boolean isDefault(Object obj) {
        return obj == null;
    }

    public static boolean isDefault(boolean z) {
        return !z;
    }
}
