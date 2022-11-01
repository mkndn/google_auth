package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.fido.common.api.JsonConvertible;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RegisterRequest extends AbstractSafeParcelable implements JsonConvertible {
    public static final Parcelable.Creator CREATOR = new RegisterRequestCreator();
    public static final int U2F_V1_CHALLENGE_BYTE_LENGTH = 65;
    private final String appId;
    private final byte[] challengeValue;
    private final ProtocolVersion protocolVersion;
    private final int versionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RegisterRequest(int i, String str, byte[] bArr, String str2) {
        this.versionCode = i;
        try {
            this.protocolVersion = ProtocolVersion.fromString(str);
            this.challengeValue = bArr;
            this.appId = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterRequest) {
            RegisterRequest registerRequest = (RegisterRequest) obj;
            if (Arrays.equals(this.challengeValue, registerRequest.challengeValue) && this.protocolVersion == registerRequest.protocolVersion) {
                String str = this.appId;
                if (str == null) {
                    if (registerRequest.appId != null) {
                        return false;
                    }
                } else if (!str.equals(registerRequest.appId)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public byte[] getChallengeValue() {
        return this.challengeValue;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getProtocolVersionAsString() {
        return this.protocolVersion.toString();
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.protocolVersion.toString());
            jSONObject.put("challenge", Base64.encodeToString(this.challengeValue, 11));
            String str = this.appId;
            if (str != null) {
                jSONObject.put("appId", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        RegisterRequestCreator.writeToParcel(this, parcel, i);
    }

    public static RegisterRequest parseFromJson(JSONObject jSONObject) {
        String str;
        if (jSONObject.has("version")) {
            str = jSONObject.getString("version");
        } else {
            str = null;
        }
        try {
            try {
                try {
                    return new RegisterRequest(ProtocolVersion.fromString(str), Base64.decode(jSONObject.getString("challenge"), 8), jSONObject.has("appId") ? jSONObject.getString("appId") : null);
                } catch (IllegalArgumentException e) {
                    throw new JSONException(e.getMessage());
                }
            } catch (IllegalArgumentException e2) {
                throw new JSONException(e2.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e3) {
            throw new JSONException(e3.toString());
        }
    }

    public int hashCode() {
        int hashCode = (((Arrays.hashCode(this.challengeValue) + 31) * 31) + this.protocolVersion.hashCode()) * 31;
        String str = this.appId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public RegisterRequest(ProtocolVersion protocolVersion, byte[] bArr, String str) {
        this.versionCode = 1;
        this.protocolVersion = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        this.challengeValue = (byte[]) Preconditions.checkNotNull(bArr);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(bArr.length == 65, "invalid challengeValue length for V1");
        }
        this.appId = str;
    }
}
