package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.fido.common.api.JsonConvertible;
import com.google.android.gms.fido.u2f.api.common.ResponseData;
import com.google.android.gms.fido.u2f.api.messagebased.RequestType;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class ResponseMessage implements JsonConvertible {
    private final Integer requestId;
    private final JSONObject responseData;
    private final String responseTypeString;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder {
        private Integer requestId;
        private JSONObject responseData;
        private String responseTypeString;

        Builder() {
        }

        private static String getResponseTypeForRequestType(RequestType requestType) {
            if (requestType != null) {
                try {
                    return ResponseType.getResponseTypeForRequestType(requestType).toString();
                } catch (RequestType.UnsupportedRequestTypeException e) {
                    return requestType.toString();
                }
            }
            return null;
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public static Builder newInstanceForRequestType(RequestType requestType) {
            return new Builder().setResponseTypeString(getResponseTypeForRequestType(requestType));
        }

        public ResponseMessage build() {
            return new ResponseMessage(this.responseTypeString, this.requestId, this.responseData);
        }

        public Builder setRequestId(Integer num) {
            this.requestId = num;
            return this;
        }

        public Builder setResponseData(ResponseData responseData) {
            if (responseData == null) {
                this.responseData = null;
            } else {
                this.responseData = responseData.toJSONObject();
            }
            return this;
        }

        public Builder setResponseTypeString(String str) {
            this.responseTypeString = str;
            return this;
        }
    }

    private ResponseMessage(String str, Integer num, JSONObject jSONObject) {
        this.responseTypeString = str;
        this.requestId = num;
        this.responseData = jSONObject;
    }

    private long getRequestIdAsUnsignedInteger(int i) {
        long intValue = this.requestId.intValue();
        if (i < 0) {
            return i + 4294967296L;
        }
        return intValue;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResponseMessage) {
            ResponseMessage responseMessage = (ResponseMessage) obj;
            if (Objects.equal(this.responseTypeString, responseMessage.responseTypeString) && Objects.equal(this.requestId, responseMessage.requestId)) {
                JSONObject jSONObject = this.responseData;
                if (jSONObject == null) {
                    return responseMessage.responseData == null;
                } else if (responseMessage.responseData == null) {
                    return false;
                } else {
                    return jSONObject.toString().equals(responseMessage.responseData.toString());
                }
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.responseTypeString, this.requestId, this.responseData);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            String str = this.responseTypeString;
            if (str != null) {
                jSONObject.put("type", str);
            }
            Integer num = this.requestId;
            if (num != null) {
                jSONObject.put("requestId", getRequestIdAsUnsignedInteger(num.intValue()));
            }
            JSONObject jSONObject2 = this.responseData;
            if (jSONObject2 != null) {
                jSONObject.put("responseData", jSONObject2);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
