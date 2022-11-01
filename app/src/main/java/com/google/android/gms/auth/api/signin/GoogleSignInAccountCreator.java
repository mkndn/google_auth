package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignInAccountCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(GoogleSignInAccount googleSignInAccount, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, googleSignInAccount.versionCode);
        SafeParcelWriter.writeString(parcel, 2, googleSignInAccount.getId(), false);
        SafeParcelWriter.writeString(parcel, 3, googleSignInAccount.getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 4, googleSignInAccount.getEmail(), false);
        SafeParcelWriter.writeString(parcel, 5, googleSignInAccount.getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 6, googleSignInAccount.getPhotoUrl(), i, false);
        SafeParcelWriter.writeString(parcel, 7, googleSignInAccount.getServerAuthCode(), false);
        SafeParcelWriter.writeLong(parcel, 8, googleSignInAccount.getExpirationTimeSecs());
        SafeParcelWriter.writeString(parcel, 9, googleSignInAccount.getObfuscatedIdentifier(), false);
        SafeParcelWriter.writeTypedList(parcel, 10, googleSignInAccount.mGrantedScopes, false);
        SafeParcelWriter.writeString(parcel, 11, googleSignInAccount.getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 12, googleSignInAccount.getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public GoogleSignInAccount createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        ArrayList arrayList = null;
        String str7 = null;
        String str8 = null;
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.createParcelable(parcel, readHeader, Uri.CREATOR);
                    break;
                case 7:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 8:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, Scope.CREATOR);
                    break;
                case 11:
                    str7 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 12:
                    str8 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
    }

    @Override // android.os.Parcelable.Creator
    public GoogleSignInAccount[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
