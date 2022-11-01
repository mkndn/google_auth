package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new ResolveAccountRequestCreator();
    private final Account mAccount;
    private final int mSessionId;
    private final GoogleSignInAccount mSignInAccountHint;
    final int mVersionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResolveAccountRequest(int i, Account account, int i2, GoogleSignInAccount googleSignInAccount) {
        this.mVersionCode = i;
        this.mAccount = account;
        this.mSessionId = i2;
        this.mSignInAccountHint = googleSignInAccount;
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public int getSessionId() {
        return this.mSessionId;
    }

    public GoogleSignInAccount getSignInAccountHint() {
        return this.mSignInAccountHint;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ResolveAccountRequestCreator.writeToParcel(this, parcel, i);
    }

    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }
}
