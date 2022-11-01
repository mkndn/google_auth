package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new SignInAccountCreator();
    @Deprecated
    String mEmail;
    private GoogleSignInAccount mGoogleSignInAccount;
    @Deprecated
    String mUserId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignInAccount(String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.mGoogleSignInAccount = googleSignInAccount;
        this.mEmail = Preconditions.checkNotEmpty(str, "8.3 and 8.4 SDKs require non-null email");
        this.mUserId = Preconditions.checkNotEmpty(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return this.mGoogleSignInAccount;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignInAccountCreator.writeToParcel(this, parcel, i);
    }
}
