package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IRevocationService extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IRevocationService {
        public Stub() {
            super("com.google.android.gms.auth.api.signin.internal.IRevocationService");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    revokeAccess();
                    return true;
                case 2:
                    cleanupClientState();
                    return true;
                default:
                    return false;
            }
        }
    }

    void cleanupClientState();

    void revokeAccess();
}
