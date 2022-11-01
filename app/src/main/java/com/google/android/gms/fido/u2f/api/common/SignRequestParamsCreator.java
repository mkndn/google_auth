package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignRequestParamsCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(SignRequestParams signRequestParams, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerObject(parcel, 2, signRequestParams.getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(parcel, 3, signRequestParams.getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, signRequestParams.getAppId(), i, false);
        SafeParcelWriter.writeByteArray(parcel, 5, signRequestParams.getDefaultSignChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, signRequestParams.getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(parcel, 7, signRequestParams.getChannelIdValue(), i, false);
        SafeParcelWriter.writeString(parcel, 8, signRequestParams.getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public SignRequestParams createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Integer num = null;
        Double d = null;
        Uri uri = null;
        byte[] bArr = null;
        ArrayList arrayList = null;
        ChannelIdValue channelIdValue = null;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    num = SafeParcelReader.readIntegerObject(parcel, readHeader);
                    break;
                case 3:
                    d = SafeParcelReader.readDoubleObject(parcel, readHeader);
                    break;
                case 4:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 5:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 6:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, RegisteredKey.CREATOR);
                    break;
                case 7:
                    channelIdValue = (ChannelIdValue) SafeParcelReader.createParcelable(parcel, readHeader, ChannelIdValue.CREATOR);
                    break;
                case 8:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SignRequestParams(num, d, uri, bArr, arrayList, channelIdValue, str);
    }

    @Override // android.os.Parcelable.Creator
    public SignRequestParams[] newArray(int i) {
        return new SignRequestParams[i];
    }
}
