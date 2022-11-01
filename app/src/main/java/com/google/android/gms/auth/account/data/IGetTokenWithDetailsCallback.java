package com.google.android.gms.auth.account.data;

import android.os.Bundle;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGetTokenWithDetailsCallback extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IGetTokenWithDetailsCallback {
        public Stub() {
            super("com.google.android.gms.auth.account.data.IGetTokenWithDetailsCallback");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 2) {
                enforceNoDataAvail(parcel);
                onResponse((Status) Codecs.createParcelable(parcel, Status.CREATOR), (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR));
                return true;
            }
            return false;
        }
    }

    void onResponse(Status status, Bundle bundle);
}
