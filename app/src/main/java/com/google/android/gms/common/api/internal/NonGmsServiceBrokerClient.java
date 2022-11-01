package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NonGmsServiceBrokerClient implements Api.Client, ServiceConnection {
    private static final String TAG = NonGmsServiceBrokerClient.class.getSimpleName();

    @Override // com.google.android.gms.common.api.Api.Client
    public void connect(BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public void disconnect() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public void disconnect(String str) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public Feature[] getAvailableFeatures() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public String getEndpointPackageName() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public String getLastDisconnectMessage() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public void getRemoteService(IAccountAccessor iAccountAccessor, Set set) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public Set getScopesForConnectionlessNonSignIn() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public boolean isConnected() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public boolean isConnecting() {
        throw null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        throw null;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public void onUserSignOut(BaseGmsClient.SignOutCallbacks signOutCallbacks) {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public boolean requiresGooglePlayServices() {
        throw null;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public boolean requiresSignIn() {
        throw null;
    }

    public void setAttributionTag(String str) {
        throw null;
    }
}
