package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class BrowserSignRequestParams extends BrowserRequestParams {
    public static final Parcelable.Creator CREATOR = new BrowserSignRequestParamsCreator();
    private final Uri origin;
    private final SignRequestParams signRequestParams;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        Uri origin;
        SignRequestParams signRequestParams;

        public BrowserSignRequestParams build() {
            return new BrowserSignRequestParams(this.signRequestParams, this.origin);
        }

        public Builder setOrigin(Uri uri) {
            this.origin = uri;
            return this;
        }

        public Builder setRequestParams(SignRequestParams signRequestParams) {
            this.signRequestParams = signRequestParams;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowserSignRequestParams(SignRequestParams signRequestParams, Uri uri) {
        this.signRequestParams = (SignRequestParams) Preconditions.checkNotNull(signRequestParams);
        Preconditions.checkNotNull(uri);
        Preconditions.checkArgument(uri.getScheme() != null, "origin scheme must be non-empty");
        Preconditions.checkArgument(uri.getAuthority() != null, "origin authority must be non-empty");
        this.origin = uri;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BrowserSignRequestParams) {
            BrowserSignRequestParams browserSignRequestParams = (BrowserSignRequestParams) obj;
            return Objects.equal(this.signRequestParams, browserSignRequestParams.signRequestParams) && Objects.equal(this.origin, browserSignRequestParams.origin);
        }
        return false;
    }

    public Uri getOrigin() {
        return this.origin;
    }

    public SignRequestParams getSignRequestParams() {
        return this.signRequestParams;
    }

    public int hashCode() {
        return Objects.hashCode(this.signRequestParams, this.origin);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        BrowserSignRequestParamsCreator.writeToParcel(this, parcel, i);
    }
}
