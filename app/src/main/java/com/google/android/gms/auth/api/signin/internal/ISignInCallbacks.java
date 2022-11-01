package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISignInCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements ISignInCallbacks {
        public Stub() {
            super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 101:
                    enforceNoDataAvail(parcel);
                    onSilentSignedInToGoogle((GoogleSignInAccount) Codecs.createParcelable(parcel, GoogleSignInAccount.CREATOR), (Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    break;
                case 102:
                    enforceNoDataAvail(parcel);
                    onSignedOutFromGoogle((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    break;
                case 103:
                    enforceNoDataAvail(parcel);
                    onAccessRevokedFromGoogle((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAccessRevokedFromGoogle(Status status);

    void onSignedOutFromGoogle(Status status);

    void onSilentSignedInToGoogle(GoogleSignInAccount googleSignInAccount, Status status);
}
