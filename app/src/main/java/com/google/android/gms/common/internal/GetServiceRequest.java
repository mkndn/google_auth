package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GetServiceRequest extends AbstractSafeParcelable {
    IBinder accountAccessorBinder;
    private String attributionTag;
    int binderPropagationProtocol;
    String callingPackage;
    Feature[] clientApiFeatures;
    int clientLibraryVersion;
    Account clientRequestedAccount;
    Feature[] clientRequiredFeatures;
    Bundle extraArgs;
    boolean requestingConnectionInfo;
    boolean requestingTelemetryConfiguration;
    Scope[] scopes;
    final int serviceId;
    final int version;
    public static final Parcelable.Creator CREATOR = new GetServiceRequestCreator();
    static final Scope[] EMPTY_SCOPES = new Scope[0];
    static final Feature[] EMPTY_FEATURES = new Feature[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4, boolean z2, String str2) {
        scopeArr = scopeArr == null ? EMPTY_SCOPES : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        featureArr = featureArr == null ? EMPTY_FEATURES : featureArr;
        featureArr2 = featureArr2 == null ? EMPTY_FEATURES : featureArr2;
        this.version = i;
        this.serviceId = i2;
        this.clientLibraryVersion = i3;
        if ("com.google.android.gms".equals(str)) {
            this.callingPackage = "com.google.android.gms";
        } else {
            this.callingPackage = str;
        }
        if (i < 2) {
            this.clientRequestedAccount = getAccountFromAccessor(iBinder);
        } else {
            this.accountAccessorBinder = iBinder;
            this.clientRequestedAccount = account;
        }
        this.scopes = scopeArr;
        this.extraArgs = bundle;
        this.clientRequiredFeatures = featureArr;
        this.clientApiFeatures = featureArr2;
        this.requestingConnectionInfo = z;
        this.binderPropagationProtocol = i4;
        this.requestingTelemetryConfiguration = z2;
        this.attributionTag = str2;
    }

    public String getAttributionTag() {
        return this.attributionTag;
    }

    public boolean isRequestingTelemetryConfiguration() {
        return this.requestingTelemetryConfiguration;
    }

    public GetServiceRequest setAuthenticatedAccount(IAccountAccessor iAccountAccessor) {
        if (iAccountAccessor != null) {
            this.accountAccessorBinder = iAccountAccessor.asBinder();
        }
        return this;
    }

    public GetServiceRequest setCallingPackage(String str) {
        this.callingPackage = str;
        return this;
    }

    public GetServiceRequest setClientApiFeatures(Feature[] featureArr) {
        this.clientApiFeatures = featureArr;
        return this;
    }

    public GetServiceRequest setClientRequestedAccount(Account account) {
        this.clientRequestedAccount = account;
        return this;
    }

    public GetServiceRequest setClientRequiredFeatures(Feature[] featureArr) {
        this.clientRequiredFeatures = featureArr;
        return this;
    }

    public GetServiceRequest setExtraArgs(Bundle bundle) {
        this.extraArgs = bundle;
        return this;
    }

    public GetServiceRequest setRequestingTelemetryConfiguration(boolean z) {
        this.requestingTelemetryConfiguration = z;
        return this;
    }

    public GetServiceRequest setScopes(Collection collection) {
        this.scopes = (Scope[]) collection.toArray(new Scope[0]);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        GetServiceRequestCreator.writeToParcel(this, parcel, i);
    }

    private static Account getAccountFromAccessor(IBinder iBinder) {
        if (iBinder != null) {
            return AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder));
        }
        return null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GetServiceRequest(int i, String str) {
        this(6, i, r3, null, null, r6, r7, null, r10, r10, true, 0, false, str);
        int i2 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Scope[] scopeArr = EMPTY_SCOPES;
        Bundle bundle = new Bundle();
        Feature[] featureArr = EMPTY_FEATURES;
    }
}
