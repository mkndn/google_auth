package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.internal.AuthClientImpl;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.internal.ProxyApiImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthProxy {
    public static final Api API;
    private static final Api.AbstractClientBuilder BUILDER_PROXY_API;
    public static final Api.ClientKey CLIENT_KEY_PROXY_API;
    public static final ProxyApi ProxyApi;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY_PROXY_API = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.auth.api.AuthProxy.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public AuthClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, AuthProxyOptions authProxyOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new AuthClientImpl(context, looper, clientSettings, authProxyOptions, connectionCallbacks, onConnectionFailedListener);
            }
        };
        BUILDER_PROXY_API = abstractClientBuilder;
        API = new Api("Auth.PROXY_API", abstractClientBuilder, clientKey);
        ProxyApi = new ProxyApiImpl();
    }
}
