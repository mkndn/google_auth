package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ResolveAccountResponseCreator();
    IBinder accountAccessorBinder;
    private ConnectionResult connectionResult;
    private boolean isFromCrossClientAuth;
    private boolean saveDefaultAccount;
    final int versionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.versionCode = i;
        this.accountAccessorBinder = iBinder;
        this.connectionResult = connectionResult;
        this.saveDefaultAccount = z;
        this.isFromCrossClientAuth = z2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        if (!this.connectionResult.equals(resolveAccountResponse.connectionResult) || !Objects.equal(getAccountAccessor(), resolveAccountResponse.getAccountAccessor())) {
            return false;
        }
        return true;
    }

    public IAccountAccessor getAccountAccessor() {
        IBinder iBinder = this.accountAccessorBinder;
        if (iBinder == null) {
            return null;
        }
        return IAccountAccessor.Stub.asInterface(iBinder);
    }

    public ConnectionResult getConnectionResult() {
        return this.connectionResult;
    }

    public boolean getSaveDefaultAccount() {
        return this.saveDefaultAccount;
    }

    public boolean isFromCrossClientAuth() {
        return this.isFromCrossClientAuth;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ResolveAccountResponseCreator.writeToParcel(this, parcel, i);
    }
}
