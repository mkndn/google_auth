package com.google.android.gms.signin;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.apps.authenticator.otp.AccountDb;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SignIn {
    public static final Api API;
    public static final Api.AbstractClientBuilder CLIENT_BUILDER;
    public static final Api.ClientKey CLIENT_KEY;
    public static final Api INTERNAL_API;
    static final Api.AbstractClientBuilder INTERNAL_CLIENT_BUILDER;
    public static final Api.ClientKey INTERNAL_CLIENT_KEY;
    public static final Scope SCOPE_EMAIL;
    public static final Scope SCOPE_PROFILE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SignInOptionsInternal implements Api.ApiOptions {
        public boolean equals(Object obj) {
            throw null;
        }

        public Bundle getSignInOptionsBundle() {
            throw null;
        }

        public int hashCode() {
            throw null;
        }
    }

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        INTERNAL_CLIENT_KEY = clientKey2;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.signin.SignIn.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public SignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, SignInOptions signInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                SignInOptions signInOptions2;
                if (signInOptions == null) {
                    signInOptions2 = SignInOptions.DEFAULT;
                } else {
                    signInOptions2 = signInOptions;
                }
                return new SignInClientImpl(context, looper, true, clientSettings, signInOptions2, connectionCallbacks, onConnectionFailedListener);
            }
        };
        CLIENT_BUILDER = abstractClientBuilder;
        Api.AbstractClientBuilder abstractClientBuilder2 = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.signin.SignIn.2
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public SignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, SignInOptionsInternal signInOptionsInternal, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new SignInClientImpl(context, looper, false, clientSettings, signInOptionsInternal.getSignInOptionsBundle(), connectionCallbacks, onConnectionFailedListener);
            }
        };
        INTERNAL_CLIENT_BUILDER = abstractClientBuilder2;
        SCOPE_PROFILE = new Scope("profile");
        SCOPE_EMAIL = new Scope(AccountDb.NAME_COLUMN);
        API = new Api("SignIn.API", abstractClientBuilder, clientKey);
        INTERNAL_API = new Api("SignIn.INTERNAL_API", abstractClientBuilder2, clientKey2);
    }
}
