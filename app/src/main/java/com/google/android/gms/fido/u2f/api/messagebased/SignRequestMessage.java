package com.google.android.gms.fido.u2f.api.messagebased;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fido.u2f.api.common.RegisteredKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class SignRequestMessage implements RequestMessage {
    private final String appId;
    private final Set appIds;
    private final byte[] defaultSignChallenge;
    private final List registeredKeys;
    private final Integer requestId;
    private final Double timeoutSeconds;

    public SignRequestMessage(Integer num, Double d, String str, byte[] bArr, List list) {
        this.requestId = num;
        this.timeoutSeconds = d;
        this.appId = str;
        this.defaultSignChallenge = bArr;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "Server provided empty list of registered keys");
        this.registeredKeys = list;
        this.appIds = validateAppIdsAndReturnAllAppIds();
    }

    private static List parseSignRequests(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(RegisteredKey.parseFromJson(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private Set validateAppIdsAndReturnAllAppIds() {
        HashSet hashSet = new HashSet();
        String str = this.appId;
        if (str != null) {
            hashSet.add(str);
        }
        for (RegisteredKey registeredKey : this.registeredKeys) {
            boolean z = false;
            Preconditions.checkArgument((registeredKey.getAppId() == null && this.appId == null) ? false : true, "Server provided request with null appId and no request appId");
            Preconditions.checkArgument((registeredKey.getChallengeValue() == null && this.defaultSignChallenge == null) ? true : true, "Server provided request with null challenge and no default challenge");
            if (registeredKey.getAppId() != null) {
                hashSet.add(registeredKey.getAppId());
            }
        }
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SignRequestMessage) {
            SignRequestMessage signRequestMessage = (SignRequestMessage) obj;
            return Objects.equal(this.requestId, signRequestMessage.requestId) && Objects.equal(this.timeoutSeconds, signRequestMessage.timeoutSeconds) && Objects.equal(this.appId, signRequestMessage.appId) && Arrays.equals(this.defaultSignChallenge, signRequestMessage.defaultSignChallenge) && Objects.equal(this.registeredKeys, signRequestMessage.registeredKeys);
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public byte[] getDefaultSignChallenge() {
        return this.defaultSignChallenge;
    }

    public List getRegisteredKeys() {
        return this.registeredKeys;
    }

    @Override // com.google.android.gms.fido.u2f.api.messagebased.RequestMessage
    public Integer getRequestId() {
        return this.requestId;
    }

    @Override // com.google.android.gms.fido.u2f.api.messagebased.RequestMessage
    public RequestType getRequestType() {
        return RequestType.SIGN;
    }

    public Double getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.defaultSignChallenge) * 31) + Objects.hashCode(this.requestId, this.timeoutSeconds, this.appId, this.registeredKeys);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", RequestType.SIGN.toString());
            Integer num = this.requestId;
            if (num != null) {
                jSONObject.put("requestId", num);
            }
            Double d = this.timeoutSeconds;
            if (d != null) {
                jSONObject.put("timeoutSeconds", d);
            }
            String str = this.appId;
            if (str != null) {
                jSONObject.put("appId", str);
            }
            byte[] bArr = this.defaultSignChallenge;
            if (bArr != null) {
                jSONObject.put("challenge", Base64.encodeToString(bArr, 11));
            }
            JSONArray jSONArray = new JSONArray();
            for (RegisteredKey registeredKey : this.registeredKeys) {
                jSONArray.put(registeredKey.toJSONObject());
            }
            jSONObject.put("registeredKeys", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public static SignRequestMessage parseFromJson(JSONObject jSONObject) {
        Integer num;
        Double d;
        String str;
        byte[] bArr;
        List parseSignRequests;
        if (jSONObject.has("requestId")) {
            num = Integer.valueOf(jSONObject.getInt("requestId"));
        } else {
            num = null;
        }
        if (jSONObject.has("timeoutSeconds")) {
            d = Double.valueOf(jSONObject.getDouble("timeoutSeconds"));
        } else {
            d = null;
        }
        if (jSONObject.has("appId")) {
            str = jSONObject.getString("appId");
        } else {
            str = null;
        }
        if (jSONObject.has("challenge")) {
            bArr = Base64.decode(jSONObject.getString("challenge"), 11);
        } else {
            bArr = null;
        }
        if (jSONObject.has("registeredKeys")) {
            parseSignRequests = parseSignRequests(jSONObject.getJSONArray("registeredKeys"));
        } else if (jSONObject.has("signRequests")) {
            parseSignRequests = parseSignRequests(jSONObject.getJSONArray("signRequests"));
        } else {
            throw new JSONException("Server provided no list of registered keys");
        }
        try {
            return new SignRequestMessage(num, d, str, bArr, parseSignRequests);
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
