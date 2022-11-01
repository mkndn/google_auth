package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.base.MoreObjects;
import com.google.common.io.BaseEncoding;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class SignResponseData extends ResponseData {
    public static final Parcelable.Creator CREATOR = new SignResponseDataCreator();
    public static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    public static final String JSON_RESPONSE_DATA_KEY_HANDLE = "keyHandle";
    public static final String JSON_RESPONSE_DATA_SIGNATURE_DATA = "signatureData";
    private final byte[] application;
    private final String clientDataString;
    private final byte[] keyHandle;
    private final byte[] signatureData;

    @Deprecated
    public SignResponseData(byte[] bArr, String str, byte[] bArr2) {
        this(bArr, str, bArr2, new byte[0]);
    }

    private String webSafeBase64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 11);
    }

    public boolean equals(Object obj) {
        if (obj instanceof SignResponseData) {
            SignResponseData signResponseData = (SignResponseData) obj;
            return Arrays.equals(this.keyHandle, signResponseData.keyHandle) && Objects.equal(this.clientDataString, signResponseData.clientDataString) && Arrays.equals(this.signatureData, signResponseData.signatureData) && Arrays.equals(this.application, signResponseData.application);
        }
        return false;
    }

    public byte[] getApplication() {
        return this.application;
    }

    public String getClientDataString() {
        return this.clientDataString;
    }

    public byte[] getKeyHandle() {
        return this.keyHandle;
    }

    public byte[] getSignatureData() {
        return this.signatureData;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.keyHandle)), this.clientDataString, Integer.valueOf(Arrays.hashCode(this.signatureData)), Integer.valueOf(Arrays.hashCode(this.application)));
    }

    @Override // com.google.android.gms.fido.common.api.JsonConvertible
    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_RESPONSE_DATA_KEY_HANDLE, webSafeBase64Encode(this.keyHandle));
            jSONObject.put(JSON_RESPONSE_DATA_CLIENT_DATA, webSafeBase64Encode(this.clientDataString.getBytes()));
            jSONObject.put(JSON_RESPONSE_DATA_SIGNATURE_DATA, webSafeBase64Encode(this.signatureData));
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    public JSONObject toJsonObject() {
        return toJSONObject();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(JSON_RESPONSE_DATA_KEY_HANDLE, BaseEncoding.base16().encode(this.keyHandle)).add("clientDataString", this.clientDataString).add(JSON_RESPONSE_DATA_SIGNATURE_DATA, BaseEncoding.base16().encode(this.signatureData)).add("application", BaseEncoding.base16().encode(this.application)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        SignResponseDataCreator.writeToParcel(this, parcel, i);
    }

    public SignResponseData(byte[] bArr, String str, byte[] bArr2, byte[] bArr3) {
        this.keyHandle = (byte[]) Preconditions.checkNotNull(bArr);
        this.clientDataString = (String) Preconditions.checkNotNull(str);
        this.signatureData = (byte[]) Preconditions.checkNotNull(bArr2);
        this.application = (byte[]) Preconditions.checkNotNull(bArr3);
    }
}
