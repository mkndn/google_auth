package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.fido.u2f.api.common.ErrorCode;
import com.google.android.gms.fido.u2f.api.common.ErrorResponseData;
import com.google.android.gms.fido.u2f.api.messagebased.ResponseMessage;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class ParseException extends Exception {
    private static final ErrorResponseData BAD_REQUEST = new ErrorResponseData(ErrorCode.BAD_REQUEST);
    private final Integer requestId;
    private final String type;
    private JSONObject u2fResponse;

    public ParseException(Integer num, String str) {
        this.requestId = num;
        this.type = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ParseException) {
            ParseException parseException = (ParseException) obj;
            return Objects.equal(this.requestId, parseException.requestId) && Objects.equal(this.type, parseException.type);
        }
        return false;
    }

    public JSONObject formatAsErrorResponse() {
        if (this.u2fResponse == null) {
            this.u2fResponse = ResponseMessage.Builder.newInstance().setResponseTypeString(this.type).setRequestId(this.requestId).setResponseData(BAD_REQUEST).build().toJSONObject();
        }
        return this.u2fResponse;
    }

    public int hashCode() {
        return Objects.hashCode(this.requestId, this.type);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return formatAsErrorResponse().toString();
    }

    public ParseException(Integer num, String str, String str2) {
        super(str2);
        this.requestId = num;
        this.type = str;
    }
}
