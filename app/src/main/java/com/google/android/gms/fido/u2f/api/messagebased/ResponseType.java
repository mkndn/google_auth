package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.fido.u2f.api.messagebased.RequestType;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public enum ResponseType {
    REGISTER("u2f_register_response"),
    SIGN("u2f_sign_response");
    
    private final String value;

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.fido.u2f.api.messagebased.ResponseType$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType;

        static {
            int[] iArr = new int[RequestType.values().length];
            $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType = iArr;
            try {
                iArr[RequestType.REGISTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[RequestType.SIGN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    ResponseType(String str) {
        this.value = str;
    }

    public static ResponseType getResponseTypeForRequestType(RequestType requestType) {
        if (requestType == null) {
            throw new RequestType.UnsupportedRequestTypeException(null);
        }
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[requestType.ordinal()]) {
            case 1:
                return REGISTER;
            case 2:
                return SIGN;
            default:
                throw new RequestType.UnsupportedRequestTypeException(requestType.toString());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }
}
