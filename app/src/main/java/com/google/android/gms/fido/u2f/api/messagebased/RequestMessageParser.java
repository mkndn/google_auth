package com.google.android.gms.fido.u2f.api.messagebased;

import android.util.Log;
import com.google.android.gms.fido.u2f.api.messagebased.RequestType;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RequestMessageParser {

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.fido.u2f.api.messagebased.RequestMessageParser$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType;

        static {
            int[] iArr = new int[RequestType.values().length];
            $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType = iArr;
            try {
                iArr[RequestType.SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[RequestType.REGISTER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static RequestMessage parseRequestJsonString(String str) {
        Integer num = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("type")) {
                throw new ParseException(null, null, "Server provided request without type");
            }
            try {
                String string = jSONObject.getString("type");
                if (jSONObject.has("requestId")) {
                    try {
                        num = Integer.valueOf(jSONObject.getInt("requestId"));
                    } catch (JSONException e) {
                        Log.w("RequestMessageParser", "Request has invalid requestId");
                    }
                }
                try {
                    try {
                        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[RequestType.fromString(string).ordinal()]) {
                            case 1:
                                return SignRequestMessage.parseFromJson(jSONObject);
                            case 2:
                                return RegisterRequestMessage.parseFromJson(jSONObject);
                            default:
                                throw new ParseException(num, string, "Unsupported request type " + string);
                        }
                    } catch (JSONException e2) {
                        throw new ParseException(num, string);
                    }
                } catch (RequestType.UnsupportedRequestTypeException e3) {
                    throw new ParseException(num, string, "Invalid request type " + string);
                }
            } catch (JSONException e4) {
                throw new ParseException(null, null, "Request type didn't parse as a string");
            }
        } catch (JSONException e5) {
            throw new ParseException(null, null, "Request didn't parse as a JSONObject");
        }
    }
}
