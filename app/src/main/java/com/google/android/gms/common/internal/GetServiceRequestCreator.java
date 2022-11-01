package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetServiceRequestCreator implements Parcelable.Creator {
    public static final int CONTENT_DESCRIPTION = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void writeToParcel(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getServiceRequest.version);
        SafeParcelWriter.writeInt(parcel, 2, getServiceRequest.serviceId);
        SafeParcelWriter.writeInt(parcel, 3, getServiceRequest.clientLibraryVersion);
        SafeParcelWriter.writeString(parcel, 4, getServiceRequest.callingPackage, false);
        SafeParcelWriter.writeIBinder(parcel, 5, getServiceRequest.accountAccessorBinder, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, getServiceRequest.scopes, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, getServiceRequest.extraArgs, false);
        SafeParcelWriter.writeParcelable(parcel, 8, getServiceRequest.clientRequestedAccount, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, getServiceRequest.clientRequiredFeatures, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, getServiceRequest.clientApiFeatures, i, false);
        SafeParcelWriter.writeBoolean(parcel, 12, getServiceRequest.requestingConnectionInfo);
        SafeParcelWriter.writeInt(parcel, 13, getServiceRequest.binderPropagationProtocol);
        SafeParcelWriter.writeBoolean(parcel, 14, getServiceRequest.isRequestingTelemetryConfiguration());
        SafeParcelWriter.writeString(parcel, 15, getServiceRequest.getAttributionTag(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable.Creator
    public GetServiceRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Scope[] scopeArr = GetServiceRequest.EMPTY_SCOPES;
        Scope[] scopeArr2 = scopeArr;
        Bundle bundle = new Bundle();
        Feature[] featureArr = GetServiceRequest.EMPTY_FEATURES;
        Feature[] featureArr2 = GetServiceRequest.EMPTY_FEATURES;
        String str = null;
        IBinder iBinder = null;
        Account account = null;
        String str2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 3:
                    i3 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 5:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 6:
                    scopeArr2 = (Scope[]) SafeParcelReader.createTypedArray(parcel, readHeader, Scope.CREATOR);
                    break;
                case 7:
                    bundle = SafeParcelReader.createBundle(parcel, readHeader);
                    break;
                case 8:
                    account = (Account) SafeParcelReader.createParcelable(parcel, readHeader, Account.CREATOR);
                    break;
                case 9:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 10:
                    featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, readHeader, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) SafeParcelReader.createTypedArray(parcel, readHeader, Feature.CREATOR);
                    break;
                case 12:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 13:
                    i4 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 14:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 15:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new GetServiceRequest(i, i2, i3, str, iBinder, scopeArr2, bundle, account, featureArr, featureArr2, z, i4, z2, str2);
    }

    @Override // android.os.Parcelable.Creator
    public GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
