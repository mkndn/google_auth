package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.common.base.MoreObjects;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RegisterResponseData extends ResponseData {
    public static final Parcelable.Creator CREATOR = new RegisterResponseDataCreator();
    static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    static final String JSON_RESPONSE_DATA_REGISTRATION_DATA = "registrationData";
    static final String JSON_RESPONSE_DATA_VERSION = "version";
    private final String clientDataString;
    private final ProtocolVersion protocolVersion;
    private final byte[] registerData;

    /* compiled from: PG */
    /* renamed from: com.google.android.gms.fido.u2f.api.common.RegisterResponseData$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$gms$fido$u2f$api$common$ProtocolVersion;

        static {
            int[] iArr = new int[ProtocolVersion.values().length];
            $SwitchMap$com$google$android$gms$fido$u2f$api$common$ProtocolVersion = iArr;
            try {
                iArr[ProtocolVersion.V1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$gms$fido$u2f$api$common$ProtocolVersion[ProtocolVersion.V2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public RegisterResponseData(byte[] bArr) {
        this.registerData = (byte[]) Preconditions.checkNotNull(bArr);
        this.protocolVersion = ProtocolVersion.V1;
        this.clientDataString = null;
    }

    private String webSafeBase64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public static RegisterResponseData withProtocolV1(byte[] bArr) {
        return new RegisterResponseData(bArr, ProtocolVersion.V1, (String) null);
    }

    public static RegisterResponseData withProtocolV2OrNewer(byte[] bArr, ProtocolVersion protocolVersion, String str) {
        return new RegisterResponseData(bArr, protocolVersion, str);
    }

    public boolean equals(Object obj) {
        if (obj instanceof RegisterResponseData) {
            RegisterResponseData registerResponseData = (RegisterResponseData) obj;
            return Objects.equal(this.protocolVersion, registerResponseData.protocolVersion) && Arrays.equals(this.registerData, registerResponseData.registerData) && Objects.equal(this.clientDataString, registerResponseData.clientDataString);
        }
        return false;
    }

    public String getClientDataString() {
        return this.clientDataString;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getProtocolVersionAsString() {
        return this.protocolVersion.toString();
    }

    public byte[] getRegisterData() {
        return this.registerData;
    }

    public int getVersionCode() {
        switch (AnonymousClass1.$SwitchMap$com$google$android$gms$fido$u2f$api$common$ProtocolVersion[this.protocolVersion.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return -1;
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.protocolVersion, Integer.valueOf(Arrays.hashCode(this.registerData)), this.clientDataString);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_RESPONSE_DATA_REGISTRATION_DATA, webSafeBase64Encode(this.registerData));
            jSONObject.put(JSON_RESPONSE_DATA_VERSION, this.protocolVersion.toString());
            String str = this.clientDataString;
            if (str != null) {
                jSONObject.put("clientData", webSafeBase64Encode(str.getBytes()));
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
        RegisterResponseDataCreator.writeToParcel(this, parcel, i);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper(this).add("protocolVersion", this.protocolVersion).add("registerData", BaseEncoding.base16().encode(this.registerData));
        String str = this.clientDataString;
        if (str != null) {
            add.add("clientDataString", str);
        }
        return add.toString();
    }

    public RegisterResponseData(byte[] bArr, ProtocolVersion protocolVersion, String str) {
        this.registerData = (byte[]) Preconditions.checkNotNull(bArr);
        this.protocolVersion = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        Preconditions.checkArgument(protocolVersion != ProtocolVersion.UNKNOWN);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(str == null);
            this.clientDataString = null;
            return;
        }
        this.clientDataString = (String) Preconditions.checkNotNull(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegisterResponseData(byte[] bArr, String str, String str2) {
        this.registerData = bArr;
        try {
            this.protocolVersion = ProtocolVersion.fromString(str);
            this.clientDataString = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
