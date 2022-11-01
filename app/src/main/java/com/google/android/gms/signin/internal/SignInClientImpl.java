package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.ISignInService;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SignInClientImpl extends GmsClient implements SignInClient {
    private final ClientSettings mClientSettings;
    private final boolean mIsExposedSignInApi;
    private final Integer mSessionId;
    private final Bundle mSignInOptionsBundle;

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.mIsExposedSignInApi = z;
        this.mClientSettings = clientSettings;
        this.mSignInOptionsBundle = bundle;
        this.mSessionId = clientSettings.getClientSessionId();
    }

    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings) {
        SignInOptions signInOptions = clientSettings.getSignInOptions();
        Integer clientSessionId = clientSettings.getClientSessionId();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings.getAccount());
        if (clientSessionId != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", clientSessionId.intValue());
        }
        if (signInOptions != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.isOfflineAccessRequested());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.isForceCodeForRefreshToken());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.getHostedDomain());
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", signInOptions.getLogSessionId());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.waitForAccessTokenRefresh());
            Long authApiSignInModuleVersion = signInOptions.getAuthApiSignInModuleVersion();
            if (authApiSignInModuleVersion != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", authApiSignInModuleVersion.longValue());
            }
            Long realClientLibraryVersion = signInOptions.getRealClientLibraryVersion();
            if (realClientLibraryVersion != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", realClientLibraryVersion.longValue());
            }
        }
        return bundle;
    }

    private ResolveAccountRequest createResolveAccountRequest() {
        GoogleSignInAccount googleSignInAccount;
        Account accountOrDefault = this.mClientSettings.getAccountOrDefault();
        if ("<<default account>>".equals(accountOrDefault.name)) {
            googleSignInAccount = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();
        } else {
            googleSignInAccount = null;
        }
        return new ResolveAccountRequest(accountOrDefault, ((Integer) Preconditions.checkNotNull(this.mSessionId)).intValue(), googleSignInAccount);
    }

    @Override // com.google.android.gms.signin.SignInClient
    public void connect() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter());
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.mClientSettings.getRealClientPackageName())) {
            this.mSignInOptionsBundle.putString("com.google.android.gms.signin.internal.realClientPackageName", this.mClientSettings.getRealClientPackageName());
        }
        return this.mSignInOptionsBundle;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public boolean requiresSignIn() {
        return this.mIsExposedSignInApi;
    }

    @Override // com.google.android.gms.signin.SignInClient
    public void signIn(ISignInCallbacks iSignInCallbacks) {
        Preconditions.checkNotNull(iSignInCallbacks, "Expecting a valid ISignInCallbacks");
        try {
            ((ISignInService) getService()).signIn(new SignInRequest(createResolveAccountRequest()), iSignInCallbacks);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                iSignInCallbacks.onSignInComplete(new SignInResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public ISignInService createServiceInterface(IBinder iBinder) {
        return ISignInService.Stub.asInterface(iBinder);
    }

    public SignInClientImpl(Context context, Looper looper, boolean z, ClientSettings clientSettings, SignInOptions signInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, z, clientSettings, createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }
}
