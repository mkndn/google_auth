package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import com.google.android.gms.fido.u2f.api.common.RegisteredKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class RegisterRequestMessage implements RequestMessage {
    private final String appId;
    private final Set appIds;
    private final List registerRequests;
    private final List registeredKeys;
    private final Integer requestId;
    private final Double timeoutSeconds;

    public RegisterRequestMessage(Integer num, Double d, String str, List list, List list2) {
        this.requestId = num;
        this.timeoutSeconds = d;
        this.appId = str;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "empty list of register requests is provided");
        this.registerRequests = list;
        this.registeredKeys = list2;
        this.appIds = validateAppIdsAndReturnAllAppIds();
    }

    private static List parseRegisterRequests(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(RegisterRequest.parseFromJson(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private static List parseRegisteredKeys(JSONArray jSONArray) {
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
        Iterator it = this.registerRequests.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            RegisterRequest registerRequest = (RegisterRequest) it.next();
            Preconditions.checkArgument((this.appId == null && registerRequest.getAppId() == null) ? true : true, "register request has null appId and no request appId is provided");
            if (registerRequest.getAppId() != null) {
                hashSet.add(registerRequest.getAppId());
            }
        }
        for (RegisteredKey registeredKey : this.registeredKeys) {
            Preconditions.checkArgument((this.appId == null && registeredKey.getAppId() == null) ? false : true, "registered key has null appId and no request appId is provided");
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
        if (obj instanceof RegisterRequestMessage) {
            RegisterRequestMessage registerRequestMessage = (RegisterRequestMessage) obj;
            return Objects.equal(this.requestId, registerRequestMessage.requestId) && Objects.equal(this.timeoutSeconds, registerRequestMessage.timeoutSeconds) && Objects.equal(this.appId, registerRequestMessage.appId) && Objects.equal(this.registerRequests, registerRequestMessage.registerRequests) && Objects.equal(this.registeredKeys, registerRequestMessage.registeredKeys);
        }
        return false;
    }

    public String getAppId() {
        return this.appId;
    }

    public List getRegisterRequests() {
        return this.registerRequests;
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
        return RequestType.REGISTER;
    }

    public Double getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    public int hashCode() {
        return Objects.hashCode(this.requestId, this.timeoutSeconds, this.appId, this.registerRequests, this.registeredKeys);
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", RequestType.REGISTER.toString());
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
            JSONArray jSONArray = new JSONArray();
            for (RegisterRequest registerRequest : this.registerRequests) {
                jSONArray.put(registerRequest.toJSONObject());
            }
            jSONObject.put("registerRequests", jSONArray);
            if (this.registeredKeys != null) {
                JSONArray jSONArray2 = new JSONArray();
                for (RegisteredKey registeredKey : this.registeredKeys) {
                    jSONArray2.put(registeredKey.toJSONObject());
                }
                jSONObject.put("registeredKeys", jSONArray2);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static RegisterRequestMessage parseFromJson(JSONObject jSONObject) {
        Integer num;
        Double d;
        String str;
        List parseRegisteredKeys;
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
        if (!jSONObject.has("registerRequests")) {
            throw new JSONException("Server provided no list of register requests");
        }
        List parseRegisterRequests = parseRegisterRequests(jSONObject.getJSONArray("registerRequests"));
        if (jSONObject.has("registeredKeys")) {
            parseRegisteredKeys = parseRegisteredKeys(jSONObject.getJSONArray("registeredKeys"));
        } else {
            parseRegisteredKeys = parseRegisteredKeys(jSONObject.getJSONArray("signRequests"));
        }
        try {
            return new RegisterRequestMessage(num, d, str, parseRegisterRequests, parseRegisteredKeys);
        } catch (IllegalArgumentException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
