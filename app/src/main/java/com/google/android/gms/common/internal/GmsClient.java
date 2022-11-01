package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GmsClient extends BaseGmsClient implements Api.Client {
    private static volatile Executor bindServiceExecutor;
    private final Account mAccount;
    private final ClientSettings mClientSettings;
    private final Set mScopes;

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, i, clientSettings, (ConnectionCallbacks) connectionCallbacks, (OnConnectionFailedListener) onConnectionFailedListener);
    }

    private Set validateScopesDisallowingExpansion(Set set) {
        Set<Scope> validateScopes = validateScopes(set);
        for (Scope scope : validateScopes) {
            if (!set.contains(scope)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return validateScopes;
    }

    private static BaseGmsClient.BaseConnectionCallbacks wrapCallbacks(final ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return new BaseGmsClient.BaseConnectionCallbacks() { // from class: com.google.android.gms.common.internal.GmsClient.1
            @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
            public void onConnected(Bundle bundle) {
                ConnectionCallbacks.this.onConnected(bundle);
            }

            @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
            public void onConnectionSuspended(int i) {
                ConnectionCallbacks.this.onConnectionSuspended(i);
            }
        };
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account getAccount() {
        return this.mAccount;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Executor getBindServiceExecutor() {
        return bindServiceExecutor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ClientSettings getClientSettings() {
        return this.mClientSettings;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        throw null;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Set getScopes() {
        return this.mScopes;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public Set getScopesForConnectionlessNonSignIn() {
        return requiresSignIn() ? this.mScopes : Collections.emptySet();
    }

    protected Set validateScopes(Set set) {
        return set;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GmsClient(Context context, Looper looper, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), i, clientSettings, (ConnectionCallbacks) Preconditions.checkNotNull(connectionCallbacks), (OnConnectionFailedListener) Preconditions.checkNotNull(onConnectionFailedListener));
    }

    private static BaseGmsClient.BaseOnConnectionFailedListener wrapCallbacks(final OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return new BaseGmsClient.BaseOnConnectionFailedListener() { // from class: com.google.android.gms.common.internal.GmsClient.2
            @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
            public void onConnectionFailed(ConnectionResult connectionResult) {
                OnConnectionFailedListener.this.onConnectionFailed(connectionResult);
            }
        };
    }

    protected GmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, googleApiAvailability, i, wrapCallbacks(connectionCallbacks), wrapCallbacks(onConnectionFailedListener), clientSettings.getRealClientClassName());
        this.mClientSettings = clientSettings;
        this.mAccount = clientSettings.getAccount();
        this.mScopes = validateScopesDisallowingExpansion(clientSettings.getAllRequestedScopes());
    }
}
