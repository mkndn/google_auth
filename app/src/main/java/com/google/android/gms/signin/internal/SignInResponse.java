package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new SignInResponseCreator();
    private final ConnectionResult connectionResult;
    private final ResolveAccountResponse resolveAccountResponse;
    final int versionCode;

    public SignInResponse(int i) {
        this(new ConnectionResult(i, null), null);
    }

    public ConnectionResult getConnectionResult() {
        return this.connectionResult;
    }

    public ResolveAccountResponse getResolveAccountResponse() {
        return this.resolveAccountResponse;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignInResponseCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignInResponse(int i, ConnectionResult connectionResult, ResolveAccountResponse resolveAccountResponse) {
        this.versionCode = i;
        this.connectionResult = connectionResult;
        this.resolveAccountResponse = resolveAccountResponse;
    }

    public SignInResponse(ConnectionResult connectionResult, ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, resolveAccountResponse);
    }
}
