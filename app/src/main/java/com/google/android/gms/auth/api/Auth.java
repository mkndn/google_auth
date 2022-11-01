package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.internal.CredentialsApiImpl;
import com.google.android.gms.auth.api.credentials.internal.CredentialsClientImpl;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInApiImpl;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Auth {
    private static final Api.AbstractClientBuilder BUILDER_CREDENTIALS_API;
    private static final Api.AbstractClientBuilder BUILDER_GOOGLE_SIGN_IN_API;
    public static final Api.ClientKey CLIENT_KEY_CREDENTIALS_API;
    public static final Api.ClientKey CLIENT_KEY_GOOGLE_SIGN_IN_API;
    public static final Api CREDENTIALS_API;
    public static final CredentialsApi CredentialsApi;
    public static final Api GOOGLE_SIGN_IN_API;
    public static final GoogleSignInApi GoogleSignInApi;
    @Deprecated
    public static final Api PROXY_API;
    @Deprecated
    public static final ProxyApi ProxyApi;

    /* compiled from: PG */
    @Deprecated
    /* loaded from: classes.dex */
    public class AuthCredentialsOptions implements Api.ApiOptions {
        public static final AuthCredentialsOptions DEFAULT = new Builder().build();
        private final String consumerPackage;
        private final boolean forceSaveDialog;
        private final String logSessionId;

        public AuthCredentialsOptions(Builder builder) {
            this.consumerPackage = builder.consumerPackage;
            this.forceSaveDialog = builder.forceEnableSaveDialog.booleanValue();
            this.logSessionId = builder.logSessionId;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AuthCredentialsOptions) {
                AuthCredentialsOptions authCredentialsOptions = (AuthCredentialsOptions) obj;
                return Objects.equal(this.consumerPackage, authCredentialsOptions.consumerPackage) && this.forceSaveDialog == authCredentialsOptions.forceSaveDialog && Objects.equal(this.logSessionId, authCredentialsOptions.logSessionId);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.consumerPackage, Boolean.valueOf(this.forceSaveDialog), this.logSessionId);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", this.consumerPackage);
            bundle.putBoolean("force_save_dialog", this.forceSaveDialog);
            bundle.putString("log_session_id", this.logSessionId);
            return bundle;
        }

        /* compiled from: PG */
        @Deprecated
        /* loaded from: classes.dex */
        public class Builder {
            protected String consumerPackage;
            protected Boolean forceEnableSaveDialog;
            protected String logSessionId;

            public Builder() {
                this.forceEnableSaveDialog = false;
            }

            public AuthCredentialsOptions build() {
                return new AuthCredentialsOptions(this);
            }

            public Builder setLogSessionId(String str) {
                this.logSessionId = str;
                return this;
            }

            public Builder(AuthCredentialsOptions authCredentialsOptions) {
                this.forceEnableSaveDialog = false;
                this.consumerPackage = authCredentialsOptions.consumerPackage;
                this.forceEnableSaveDialog = Boolean.valueOf(authCredentialsOptions.forceSaveDialog);
                this.logSessionId = authCredentialsOptions.logSessionId;
            }
        }
    }

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY_CREDENTIALS_API = clientKey;
        Api.ClientKey clientKey2 = new Api.ClientKey();
        CLIENT_KEY_GOOGLE_SIGN_IN_API = clientKey2;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.auth.api.Auth.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public CredentialsClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, AuthCredentialsOptions authCredentialsOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new CredentialsClientImpl(context, looper, clientSettings, authCredentialsOptions, connectionCallbacks, onConnectionFailedListener);
            }
        };
        BUILDER_CREDENTIALS_API = abstractClientBuilder;
        Api.AbstractClientBuilder abstractClientBuilder2 = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.auth.api.Auth.2
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public GoogleSignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, GoogleSignInOptions googleSignInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
                return new GoogleSignInClientImpl(context, looper, clientSettings, googleSignInOptions, connectionCallbacks, onConnectionFailedListener);
            }
        };
        BUILDER_GOOGLE_SIGN_IN_API = abstractClientBuilder2;
        PROXY_API = AuthProxy.API;
        CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", abstractClientBuilder, clientKey);
        GOOGLE_SIGN_IN_API = new Api("Auth.GOOGLE_SIGN_IN_API", abstractClientBuilder2, clientKey2);
        ProxyApi = AuthProxy.ProxyApi;
        CredentialsApi = new CredentialsApiImpl();
        GoogleSignInApi = new GoogleSignInApiImpl();
    }
}
