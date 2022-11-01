package com.google.android.gms.clearcut.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PlayLoggerContextCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(PlayLoggerContext playLoggerContext, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, playLoggerContext.packageName, false);
        SafeParcelWriter.writeInt(parcel, 3, playLoggerContext.packageVersionCode);
        SafeParcelWriter.writeInt(parcel, 4, playLoggerContext.logSource);
        SafeParcelWriter.writeString(parcel, 5, playLoggerContext.uploadAccountName, false);
        SafeParcelWriter.writeBoolean(parcel, 7, playLoggerContext.logAndroidId);
        SafeParcelWriter.writeString(parcel, 8, playLoggerContext.logSourceName, false);
        SafeParcelWriter.writeBoolean(parcel, 9, playLoggerContext.isAnonymous);
        SafeParcelWriter.writeInt(parcel, 10, playLoggerContext.qosTier);
        SafeParcelWriter.writeIntegerObject(parcel, 11, playLoggerContext.appMobilespecId, false);
        SafeParcelWriter.writeBoolean(parcel, 12, playLoggerContext.scrubMccMnc);
        SafeParcelWriter.writeInt(parcel, 13, playLoggerContext.piiLevelSet);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public PlayLoggerContext createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        Integer num = null;
        int i = 0;
        int i2 = 0;
        boolean z = true;
        boolean z2 = false;
        int i3 = 0;
        boolean z3 = false;
        int i4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 8:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 11:
                    num = SafeParcelReader.readIntegerObject(parcel, readHeader);
                    break;
                case 12:
                    z3 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 13:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new PlayLoggerContext(str, i, i2, str2, z, str3, z2, i3, num, z3, i4);
    }

    @Override // android.os.Parcelable.Creator
    public PlayLoggerContext[] newArray(int i) {
        return new PlayLoggerContext[i];
    }
}
