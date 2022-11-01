package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrowserRegisterRequestParamsCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(BrowserRegisterRequestParams browserRegisterRequestParams, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, browserRegisterRequestParams.getRegisterRequestParams(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, browserRegisterRequestParams.getOrigin(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public BrowserRegisterRequestParams createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        RegisterRequestParams registerRequestParams = null;
        Uri uri = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    registerRequestParams = (RegisterRequestParams) SafeParcelReader.createParcelable(parcel, readHeader, RegisterRequestParams.CREATOR);
                    break;
                case 3:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new BrowserRegisterRequestParams(registerRequestParams, uri);
    }

    @Override // android.os.Parcelable.Creator
    public BrowserRegisterRequestParams[] newArray(int i) {
        return new BrowserRegisterRequestParams[i];
    }
}
