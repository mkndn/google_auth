package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.base.MoreObjects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class ErrorResponseData extends ResponseData {
    public static final Parcelable.Creator CREATOR = new ErrorResponseDataCreator();
    private final ErrorCode errorCode;
    private final String errorMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ErrorResponseData(int i, String str) {
        this.errorCode = ErrorCode.toErrorCode(i);
        this.errorMessage = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ErrorResponseData) {
            ErrorResponseData errorResponseData = (ErrorResponseData) obj;
            return Objects.equal(this.errorCode, errorResponseData.errorCode) && Objects.equal(this.errorMessage, errorResponseData.errorMessage);
        }
        return false;
    }

    public int getErrorCodeAsInt() {
        return this.errorCode.getCode();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public int hashCode() {
        return Objects.hashCode(this.errorCode, this.errorMessage);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.errorCode.getCode());
            String str = this.errorMessage;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    public JSONObject toJsonObject() {
        return toJSONObject();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ErrorResponseDataCreator.writeToParcel(this, parcel, i);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("errorCode", this.errorCode.getCode());
        String str = this.errorMessage;
        if (str != null) {
            add.add("errorMessage", str);
        }
        return add.toString();
    }

    public ErrorResponseData(ErrorCode errorCode) {
        this.errorCode = (ErrorCode) Preconditions.checkNotNull(errorCode);
        this.errorMessage = null;
    }
}
