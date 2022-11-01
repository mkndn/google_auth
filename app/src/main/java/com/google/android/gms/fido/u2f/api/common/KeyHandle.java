package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.common.api.JsonConvertible;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class KeyHandle extends AbstractSafeParcelable implements JsonConvertible {
    public static final Parcelable.Creator CREATOR = new KeyHandleCreator();
    private final byte[] keyHandleBytes;
    private final ProtocolVersion protocolVersion;
    private final List transports;
    private final int versionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyHandle(int i, byte[] bArr, String str, List list) {
        this.versionCode = i;
        this.keyHandleBytes = bArr;
        try {
            this.protocolVersion = ProtocolVersion.fromString(str);
            this.transports = list;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean equals(Object obj) {
        List list;
        if (this == obj) {
            return true;
        }
        if (obj instanceof KeyHandle) {
            KeyHandle keyHandle = (KeyHandle) obj;
            if (Arrays.equals(this.keyHandleBytes, keyHandle.keyHandleBytes) && this.protocolVersion.equals(keyHandle.protocolVersion)) {
                List list2 = this.transports;
                if (list2 == null && keyHandle.transports == null) {
                    return true;
                }
                return list2 != null && (list = keyHandle.transports) != null && list2.containsAll(list) && keyHandle.transports.containsAll(this.transports);
            }
            return false;
        }
        return false;
    }

    public byte[] getBytes() {
        return this.keyHandleBytes;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getProtocolVersionAsString() {
        return this.protocolVersion.toString();
    }

    public List getTransports() {
        return this.transports;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.keyHandleBytes)), this.protocolVersion, this.transports);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            byte[] bArr = this.keyHandleBytes;
            if (bArr != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(bArr, 11));
            }
            ProtocolVersion protocolVersion = this.protocolVersion;
            if (protocolVersion != null) {
                jSONObject.put("version", protocolVersion.toString());
            }
            if (this.transports != null) {
                JSONArray jSONArray = new JSONArray();
                for (Transport transport : this.transports) {
                    jSONArray.put(transport.toString());
                }
                jSONObject.put("transports", jSONArray);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        List list = this.transports;
        return String.format("{keyHandle: %s, version: %s, transports: %s}", Base64Utils.encode(this.keyHandleBytes), this.protocolVersion, list == null ? "null" : list.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        KeyHandleCreator.writeToParcel(this, parcel, i);
    }

    private static List getTransports(JSONObject jSONObject) {
        if (jSONObject.has("transports")) {
            return Transport.parseTransports(jSONObject.getJSONArray("transports"));
        }
        return null;
    }

    public static KeyHandle parseFromJson(JSONObject jSONObject) {
        String str;
        if (jSONObject.has("version")) {
            str = jSONObject.getString("version");
        } else {
            str = null;
        }
        try {
            ProtocolVersion fromString = ProtocolVersion.fromString(str);
            try {
                return new KeyHandle(Base64.decode(jSONObject.getString(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE), 8), fromString, getTransports(jSONObject));
            } catch (IllegalArgumentException e) {
                throw new JSONException(e.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e2) {
            throw new JSONException(e2.toString());
        }
    }

    public KeyHandle(byte[] bArr, ProtocolVersion protocolVersion, List list) {
        this.versionCode = 1;
        this.keyHandleBytes = bArr;
        this.protocolVersion = protocolVersion;
        this.transports = list;
    }
}
