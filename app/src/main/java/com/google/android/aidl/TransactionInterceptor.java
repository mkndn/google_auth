package com.google.android.aidl;

import android.os.Parcel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TransactionInterceptor {
    void enforceNoDataAvail(Parcel parcel);

    boolean interceptTransaction(BaseStub baseStub, int i, Parcel parcel, Parcel parcel2, int i2);
}
