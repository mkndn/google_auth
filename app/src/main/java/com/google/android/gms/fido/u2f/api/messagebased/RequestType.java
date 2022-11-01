package com.google.android.gms.fido.u2f.api.messagebased;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public enum RequestType {
    REGISTER("u2f_register_request"),
    SIGN("u2f_sign_request");
    
    private final String value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class UnsupportedRequestTypeException extends Exception {
        public UnsupportedRequestTypeException(String str) {
            super("Unsupported request type " + str);
        }
    }

    RequestType(String str) {
        this.value = str;
    }

    public static RequestType fromString(String str) {
        RequestType[] values;
        for (RequestType requestType : values()) {
            if (str.equals(requestType.value)) {
                return requestType;
            }
        }
        throw new UnsupportedRequestTypeException(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
