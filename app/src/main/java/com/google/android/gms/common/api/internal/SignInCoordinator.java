package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.internal.BaseSignInCallbacks;
import com.google.android.gms.signin.internal.SignInResponse;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInCoordinator extends BaseSignInCallbacks implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.AbstractClientBuilder sClientBuilder = SignIn.CLIENT_BUILDER;
    private SignInCallback mCallback;
    private final Api.AbstractClientBuilder mClientBuilder;
    private ClientSettings mClientSettings;
    private final Context mContext;
    private final Handler mHandler;
    private Set mScopes;
    private SignInClient mSignInClient;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface SignInCallback {
        void onSignInFailed(ConnectionResult connectionResult);

        void onSignInSuccess(IAccountAccessor iAccountAccessor, Set set);
    }

    public SignInCoordinator(Context context, Handler handler, ClientSettings clientSettings) {
        this(context, handler, clientSettings, sClientBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signInComplete(SignInResponse signInResponse) {
        ConnectionResult connectionResult = signInResponse.getConnectionResult();
        if (connectionResult.isSuccess()) {
            ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) Preconditions.checkNotNull(signInResponse.getResolveAccountResponse());
            ConnectionResult connectionResult2 = resolveAccountResponse.getConnectionResult();
            if (!connectionResult2.isSuccess()) {
                Log.wtf("SignInCoordinator", "Sign-in succeeded with resolve account failure: " + String.valueOf(connectionResult2), new Exception());
                this.mCallback.onSignInFailed(connectionResult2);
                this.mSignInClient.disconnect();
                return;
            }
            this.mCallback.onSignInSuccess(resolveAccountResponse.getAccountAccessor(), this.mScopes);
        } else {
            this.mCallback.onSignInFailed(connectionResult);
        }
        this.mSignInClient.disconnect();
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        this.mSignInClient.signIn(this);
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.mCallback.onSignInFailed(connectionResult);
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        this.mSignInClient.disconnect();
    }

    @Override // com.google.android.gms.signin.internal.BaseSignInCallbacks, com.google.android.gms.signin.internal.ISignInCallbacks
    public void onSignInComplete(final SignInResponse signInResponse) {
        this.mHandler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.SignInCoordinator.2
            @Override // java.lang.Runnable
            public void run() {
                SignInCoordinator.this.signInComplete(signInResponse);
            }
        });
    }

    public void startSignIn(SignInCallback signInCallback) {
        SignInClient signInClient = this.mSignInClient;
        if (signInClient != null) {
            signInClient.disconnect();
        }
        this.mClientSettings.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
        Api.AbstractClientBuilder abstractClientBuilder = this.mClientBuilder;
        Context context = this.mContext;
        Looper looper = this.mHandler.getLooper();
        ClientSettings clientSettings = this.mClientSettings;
        this.mSignInClient = (SignInClient) abstractClientBuilder.buildClient(context, looper, clientSettings, (Object) clientSettings.getSignInOptions(), (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
        this.mCallback = signInCallback;
        Set set = this.mScopes;
        if (set != null && !set.isEmpty()) {
            this.mSignInClient.connect();
        } else {
            this.mHandler.post(new Runnable() { // from class: com.google.android.gms.common.api.internal.SignInCoordinator.1
                @Override // java.lang.Runnable
                public void run() {
                    SignInCoordinator.this.mCallback.onSignInFailed(new ConnectionResult(4));
                }
            });
        }
    }

    public void stopSignIn() {
        SignInClient signInClient = this.mSignInClient;
        if (signInClient != null) {
            signInClient.disconnect();
        }
    }

    public SignInCoordinator(Context context, Handler handler, ClientSettings clientSettings, Api.AbstractClientBuilder abstractClientBuilder) {
        this.mContext = context;
        this.mHandler = handler;
        this.mClientSettings = (ClientSettings) Preconditions.checkNotNull(clientSettings, "ClientSettings must not be null");
        this.mScopes = clientSettings.getRequiredScopes();
        this.mClientBuilder = abstractClientBuilder;
    }
}
