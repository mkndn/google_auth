package com.google.android.gms.fido;

import android.app.Activity;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.fido.u2f.U2fPrivilegedApiClient;
import com.google.android.gms.fido.u2f.U2fZeroPartyApi;
import com.google.android.gms.fido.u2f.internal.zeroparty.U2fZeroPartyApiImpl;
import com.google.android.gms.fido.u2f.internal.zeroparty.U2fZeroPartyClientImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Fido {
    public static final Api.ClientKey CLIENT_KEY_U2F_ZERO_PARTY_API;
    public static final Api U2F_ZERO_PARTY_API;
    public static final U2fZeroPartyApi U2fZeroPartyApi;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY_U2F_ZERO_PARTY_API = clientKey;
        U2F_ZERO_PARTY_API = new Api("Fido.U2F_ZERO_PARTY_API", new U2fZeroPartyClientImpl.ClientBuilder(), clientKey);
        U2fZeroPartyApi = new U2fZeroPartyApiImpl();
    }

    public static U2fPrivilegedApiClient getU2fPrivilegedApiClient(Activity activity) {
        return new U2fPrivilegedApiClient(activity);
    }
}
