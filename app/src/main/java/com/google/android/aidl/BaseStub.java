package com.google.android.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseStub extends Binder implements IInterface {
    private static TransactionInterceptor globalInterceptor = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseStub(String str) {
        attachInterface(this, str);
    }

    private boolean routeToSuperOrEnforceInterface(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i > 16777215) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface(getInterfaceDescriptor());
        return false;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void enforceNoDataAvail(Parcel parcel) {
        TransactionInterceptor transactionInterceptor = globalInterceptor;
        if (transactionInterceptor != null) {
            transactionInterceptor.enforceNoDataAvail(parcel);
        } else {
            Codecs.enforceNoDataAvail(parcel);
        }
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (routeToSuperOrEnforceInterface(i, parcel, parcel2, i2)) {
            return true;
        }
        TransactionInterceptor transactionInterceptor = globalInterceptor;
        if (transactionInterceptor == null) {
            return dispatchTransaction(i, parcel, parcel2, i2);
        }
        return transactionInterceptor.interceptTransaction(this, i, parcel, parcel2, i2);
    }
}
