package com.google.android.gms.fido.u2f.api.messagebased;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fido.u2f.api.common.BrowserRegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserSignRequestParams;
import com.google.android.gms.fido.u2f.api.common.ChannelIdValue;
import com.google.android.gms.fido.u2f.api.common.RegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.RequestParams;
import com.google.android.gms.fido.u2f.api.common.ResponseData;
import com.google.android.gms.fido.u2f.api.common.SignRequestParams;
import com.google.android.gms.fido.u2f.api.messagebased.ResponseMessage;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class WrappedRequestMessage {
    private final RequestMessage requestMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.gms.fido.u2f.api.messagebased.WrappedRequestMessage$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public /* synthetic */ class AnonymousClass1 {
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

    public WrappedRequestMessage(RequestMessage requestMessage) {
        Preconditions.checkNotNull(requestMessage);
        Preconditions.checkArgument(requestMessage.getRequestType() == RequestType.REGISTER || requestMessage.getRequestType() == RequestType.SIGN, "Unsupported request type " + String.valueOf(requestMessage.getRequestType()));
        this.requestMessage = requestMessage;
    }

    public static WrappedRequestMessage fromBundle(Bundle bundle) {
        Preconditions.checkNotNull(bundle);
        String string = bundle.getString("requestMessage");
        if (string == null) {
            throw new RuntimeException("Saved bundle doesn't include entry for requestMessage");
        }
        try {
            return new WrappedRequestMessage(RequestMessageParser.parseRequestJsonString(string));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private RequestParams getRequestParams(ChannelIdValue channelIdValue) {
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[this.requestMessage.getRequestType().ordinal()]) {
            case 1:
                RegisterRequestMessage registerRequestMessage = (RegisterRequestMessage) this.requestMessage;
                RegisterRequestParams.Builder requestId = new RegisterRequestParams.Builder().setAppId(registerRequestMessage.getAppId() != null ? Uri.parse(registerRequestMessage.getAppId()) : null).setRegisterRequests(registerRequestMessage.getRegisterRequests()).setRegisteredKeys(registerRequestMessage.getRegisteredKeys()).setTimeoutSeconds(registerRequestMessage.getTimeoutSeconds()).setRequestId(registerRequestMessage.getRequestId());
                if (channelIdValue != null) {
                    requestId.setChannelIdValue(channelIdValue);
                }
                return requestId.build();
            case 2:
                SignRequestMessage signRequestMessage = (SignRequestMessage) this.requestMessage;
                SignRequestParams.Builder requestId2 = new SignRequestParams.Builder().setAppId(signRequestMessage.getAppId() != null ? Uri.parse(signRequestMessage.getAppId()) : null).setDefaultSignChallenge(signRequestMessage.getDefaultSignChallenge()).setRegisteredKeys(signRequestMessage.getRegisteredKeys()).setTimeoutSeconds(signRequestMessage.getTimeoutSeconds()).setRequestId(signRequestMessage.getRequestId());
                if (channelIdValue != null) {
                    requestId2.setChannelIdValue(channelIdValue);
                }
                return requestId2.build();
            default:
                throw new RuntimeException("Unsupported request type " + String.valueOf(this.requestMessage.getRequestType()));
        }
    }

    public BrowserRequestParams getBrowserRequestParams(Uri uri, ChannelIdValue channelIdValue) {
        Preconditions.checkNotNull(uri);
        RequestParams requestParams = getRequestParams(channelIdValue);
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$messagebased$RequestType[this.requestMessage.getRequestType().ordinal()]) {
            case 1:
                return new BrowserRegisterRequestParams.Builder().setRequestParams((RegisterRequestParams) requestParams).setOrigin(uri).build();
            case 2:
                return new BrowserSignRequestParams.Builder().setRequestParams((SignRequestParams) requestParams).setOrigin(uri).build();
            default:
                throw new RuntimeException("Unsupported request type " + String.valueOf(this.requestMessage.getRequestType()));
        }
    }

    public Integer getRequestId() {
        return this.requestMessage.getRequestId();
    }

    public RequestType getRequestType() {
        return this.requestMessage.getRequestType();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("requestMessage", this.requestMessage.toJSONObject().toString());
        return bundle;
    }

    public ResponseMessage buildResponse(ResponseData responseData) {
        return ResponseMessage.Builder.newInstanceForRequestType(getRequestType()).setRequestId(getRequestId()).setResponseData(responseData).build();
    }
}
