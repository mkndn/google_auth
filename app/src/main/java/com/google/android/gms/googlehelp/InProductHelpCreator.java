package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class InProductHelpCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(InProductHelp inProductHelp, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, inProductHelp.getGoogleHelp(), i, false);
        SafeParcelWriter.writeString(parcel, 2, inProductHelp.getSearchQuery(), false);
        SafeParcelWriter.writeString(parcel, 3, inProductHelp.getContentUrl(), false);
        SafeParcelWriter.writeInt(parcel, 4, inProductHelp.getOpeningMode());
        SafeParcelWriter.writeString(parcel, 5, inProductHelp.getSymptom(), false);
        SafeParcelWriter.writeInt(parcel, 6, inProductHelp.getChannel());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public InProductHelp createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        GoogleHelp googleHelp = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    googleHelp = (GoogleHelp) SafeParcelReader.createParcelable(parcel, readHeader, GoogleHelp.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new InProductHelp(googleHelp, str, str2, i, str3, i2);
    }

    @Override // android.os.Parcelable.Creator
    public InProductHelp[] newArray(int i) {
        return new InProductHelp[i];
    }
}
