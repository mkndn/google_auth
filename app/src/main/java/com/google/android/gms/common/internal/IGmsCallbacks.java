package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGmsCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements IGmsCallbacks {
        public Stub() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    enforceNoDataAvail(parcel);
                    onPostInitComplete(parcel.readInt(), parcel.readStrongBinder(), (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR));
                    break;
                case 2:
                    enforceNoDataAvail(parcel);
                    onAccountValidationComplete(parcel.readInt(), (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR));
                    break;
                case 3:
                    enforceNoDataAvail(parcel);
                    onPostInitCompleteWithConnectionInfo(parcel.readInt(), parcel.readStrongBinder(), (ConnectionInfo) Codecs.createParcelable(parcel, ConnectionInfo.CREATOR));
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onAccountValidationComplete(int i, Bundle bundle);

    void onPostInitComplete(int i, IBinder iBinder, Bundle bundle);

    void onPostInitCompleteWithConnectionInfo(int i, IBinder iBinder, ConnectionInfo connectionInfo);
}
