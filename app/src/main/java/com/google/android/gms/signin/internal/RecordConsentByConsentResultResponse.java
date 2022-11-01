package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RecordConsentByConsentResultResponse extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator CREATOR = new RecordConsentByConsentResultResponseCreator();
    private final List grantedScopes;
    private final String token;

    public RecordConsentByConsentResultResponse(List list, String str) {
        this.grantedScopes = list;
        this.token = str;
    }

    public List getGrantedScopes() {
        return this.grantedScopes;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        if (this.token != null) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }

    public String getToken() {
        return this.token;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        RecordConsentByConsentResultResponseCreator.writeToParcel(this, parcel, i);
    }
}
