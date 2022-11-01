package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BitmapTeleporterCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(BitmapTeleporter bitmapTeleporter, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, bitmapTeleporter.mVersionCode);
        SafeParcelWriter.writeParcelable(parcel, 2, bitmapTeleporter.mFileDescriptor, i, false);
        SafeParcelWriter.writeInt(parcel, 3, bitmapTeleporter.mType);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public BitmapTeleporter createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    parcelFileDescriptor = (ParcelFileDescriptor) SafeParcelReader.createParcelable(parcel, readHeader, ParcelFileDescriptor.CREATOR);
                    break;
                case 3:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BitmapTeleporter(i, parcelFileDescriptor, i2);
    }

    @Override // android.os.Parcelable.Creator
    public BitmapTeleporter[] newArray(int i) {
        return new BitmapTeleporter[i];
    }
}
