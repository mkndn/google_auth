package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new SignInRequestCreator();
    final ResolveAccountRequest mResolveAccountRequest;
    final int mVersionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignInRequest(int i, ResolveAccountRequest resolveAccountRequest) {
        this.mVersionCode = i;
        this.mResolveAccountRequest = resolveAccountRequest;
    }

    public ResolveAccountRequest getResolveAccountRequest() {
        return this.mResolveAccountRequest;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignInRequestCreator.writeToParcel(this, parcel, i);
    }

    public SignInRequest(ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
}
