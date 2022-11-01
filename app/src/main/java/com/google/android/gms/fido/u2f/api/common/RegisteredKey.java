package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.fido.common.api.JsonConvertible;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RegisteredKey extends AbstractSafeParcelable implements JsonConvertible {
    public static final Parcelable.Creator CREATOR = new RegisteredKeyCreator();
    private final String appId;
    String challengeValue;
    private final KeyHandle keyHandle;

    public RegisteredKey(KeyHandle keyHandle, String str, String str2) {
        this.keyHandle = (KeyHandle) Preconditions.checkNotNull(keyHandle);
        this.challengeValue = str;
        this.appId = str2;
    }

    public static RegisteredKey parseFromJson(JSONObject jSONObject) {
        String str;
        if (jSONObject.has("challenge")) {
            str = jSONObject.getString("challenge");
        } else {
            str = null;
        }
        return new RegisteredKey(KeyHandle.parseFromJson(jSONObject), str, jSONObject.has("appId") ? jSONObject.getString("appId") : null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisteredKey) {
            RegisteredKey registeredKey = (RegisteredKey) obj;
            String str = this.challengeValue;
            if (str == null) {
                if (registeredKey.challengeValue != null) {
                    return false;
                }
            } else if (!str.equals(registeredKey.challengeValue)) {
                return false;
            }
            if (this.keyHandle.equals(registeredKey.keyHandle)) {
                String str2 = this.appId;
                if (str2 == null) {
                    if (registeredKey.appId != null) {
                        return false;
                    }
                } else if (!str2.equals(registeredKey.appId)) {
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

    public String getChallengeValue() {
        return this.challengeValue;
    }

    public KeyHandle getKeyHandle() {
        return this.keyHandle;
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.challengeValue;
            if (str != null) {
                jSONObject.put("challenge", str);
            }
            JSONObject jSONObject2 = this.keyHandle.toJSONObject();
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
            String str2 = this.appId;
            if (str2 != null) {
                jSONObject.put("appId", str2);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.keyHandle.getBytes(), 11));
            if (this.keyHandle.getProtocolVersion() != ProtocolVersion.UNKNOWN) {
                jSONObject.put("version", this.keyHandle.getProtocolVersion().toString());
            }
            if (this.keyHandle.getTransports() != null) {
                jSONObject.put("transports", this.keyHandle.getTransports().toString());
            }
            String str = this.challengeValue;
            if (str != null) {
                jSONObject.put("challenge", str);
            }
            String str2 = this.appId;
            if (str2 != null) {
                jSONObject.put("appId", str2);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        RegisteredKeyCreator.writeToParcel(this, parcel, i);
    }

    public int hashCode() {
        String str = this.challengeValue;
        int hashCode = ((((str == null ? 0 : str.hashCode()) + 31) * 31) + this.keyHandle.hashCode()) * 31;
        String str2 = this.appId;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
