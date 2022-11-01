package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthAccountResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator CREATOR = new AuthAccountResultCreator();
    private int connectionResultCode;
    private Intent rawAuthResultionIntent;
    final int versionCode;

    public AuthAccountResult() {
        this(0, null);
    }

    public int getConnectionResultCode() {
        return this.connectionResultCode;
    }

    public Intent getRawAuthResolutionIntent() {
        return this.rawAuthResultionIntent;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        if (this.connectionResultCode == 0) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AuthAccountResultCreator.writeToParcel(this, parcel, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthAccountResult(int i, int i2, Intent intent) {
        this.versionCode = i;
        this.connectionResultCode = i2;
        this.rawAuthResultionIntent = intent;
    }

    public AuthAccountResult(int i, Intent intent) {
        this(2, i, intent);
    }
}
