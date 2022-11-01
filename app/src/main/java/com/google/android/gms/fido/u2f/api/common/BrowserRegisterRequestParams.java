package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class BrowserRegisterRequestParams extends BrowserRequestParams {
    public static final Parcelable.Creator CREATOR = new BrowserRegisterRequestParamsCreator();
    private final Uri origin;
    private final RegisterRequestParams registerRequestParams;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        Uri origin;
        RegisterRequestParams registerRequestParams;

        public BrowserRegisterRequestParams build() {
            return new BrowserRegisterRequestParams(this.registerRequestParams, this.origin);
        }

        public Builder setOrigin(Uri uri) {
            this.origin = uri;
            return this;
        }

        public Builder setRequestParams(RegisterRequestParams registerRequestParams) {
            this.registerRequestParams = registerRequestParams;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowserRegisterRequestParams(RegisterRequestParams registerRequestParams, Uri uri) {
        this.registerRequestParams = (RegisterRequestParams) Preconditions.checkNotNull(registerRequestParams);
        Preconditions.checkNotNull(uri);
        Preconditions.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        Preconditions.checkArgument(uri.getAuthority() != null, "origin authority must be non-empty");
        this.origin = uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BrowserRegisterRequestParams) {
            BrowserRegisterRequestParams browserRegisterRequestParams = (BrowserRegisterRequestParams) obj;
            return Objects.equal(this.registerRequestParams, browserRegisterRequestParams.registerRequestParams) && Objects.equal(this.origin, browserRegisterRequestParams.origin);
        }
        return false;
    }

    public Uri getOrigin() {
        return this.origin;
    }

    public RegisterRequestParams getRegisterRequestParams() {
        return this.registerRequestParams;
    }

    public int hashCode() {
        return Objects.hashCode(this.registerRequestParams, this.origin);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        BrowserRegisterRequestParamsCreator.writeToParcel(this, parcel, i);
    }
}
