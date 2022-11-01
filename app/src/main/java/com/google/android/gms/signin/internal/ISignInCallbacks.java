package com.google.android.gms.signin.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ISignInCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements ISignInCallbacks {
        public Stub() {
            super("com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 3:
                    enforceNoDataAvail(parcel);
                    onAuthAccountComplete((ConnectionResult) Codecs.createParcelable(parcel, ConnectionResult.CREATOR), (AuthAccountResult) Codecs.createParcelable(parcel, AuthAccountResult.CREATOR));
                    break;
                case 4:
                    enforceNoDataAvail(parcel);
                    onSaveAccountToSessionStoreComplete((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    break;
                case 5:
                default:
                    return false;
                case 6:
                    enforceNoDataAvail(parcel);
                    onRecordConsentComplete((Status) Codecs.createParcelable(parcel, Status.CREATOR));
                    break;
                case 7:
                    enforceNoDataAvail(parcel);
                    onGetCurrentAccountComplete((Status) Codecs.createParcelable(parcel, Status.CREATOR), (GoogleSignInAccount) Codecs.createParcelable(parcel, GoogleSignInAccount.CREATOR));
                    break;
                case 8:
                    enforceNoDataAvail(parcel);
                    onSignInComplete((SignInResponse) Codecs.createParcelable(parcel, SignInResponse.CREATOR));
                    break;
                case 9:
                    enforceNoDataAvail(parcel);
                    onRecordConsentByConsentResultComplete((RecordConsentByConsentResultResponse) Codecs.createParcelable(parcel, RecordConsentByConsentResultResponse.CREATOR));
                    break;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAuthAccountComplete(ConnectionResult connectionResult, AuthAccountResult authAccountResult);

    void onGetCurrentAccountComplete(Status status, GoogleSignInAccount googleSignInAccount);

    void onRecordConsentByConsentResultComplete(RecordConsentByConsentResultResponse recordConsentByConsentResultResponse);

    void onRecordConsentComplete(Status status);

    void onSaveAccountToSessionStoreComplete(Status status);

    void onSignInComplete(SignInResponse signInResponse);
}
