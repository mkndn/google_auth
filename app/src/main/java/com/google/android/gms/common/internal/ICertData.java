package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ICertData extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Stub extends BaseStub implements ICertData {
        public Stub() {
            super("com.google.android.gms.common.internal.ICertData");
        }

        @Override // com.google.android.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    IObjectWrapper bytesWrapped = getBytesWrapped();
                    parcel2.writeNoException();
                    Codecs.writeStrongBinder(parcel2, bytesWrapped);
                    return true;
                case 2:
                    int hashCode = getHashCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(hashCode);
                    return true;
                default:
                    return false;
            }
        }
    }

    IObjectWrapper getBytesWrapped();

    int getHashCode();
}
