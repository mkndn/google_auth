package com.google.android.gms.fido.u2f.internal.privileged;

import android.app.PendingIntent;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IU2fPrivilegedCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IU2fPrivilegedCallbacks {
        public Stub() {
            super("com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                enforceNoDataAvail(parcel);
                onPendingIntentFetched((Status) Codecs.createParcelable(parcel, Status.CREATOR), (PendingIntent) Codecs.createParcelable(parcel, PendingIntent.CREATOR));
                return true;
            }
            return false;
        }
    }

    void onPendingIntentFetched(Status status, PendingIntent pendingIntent);
}
