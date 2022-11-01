package com.google.android.gms.fido.u2f.internal.privileged;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService;

/* compiled from: PG */
/* loaded from: classes.dex */
public class U2fPrivilegedClientImpl extends GmsClient {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class ClientBuilder extends Api.AbstractClientBuilder {
        @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
        public U2fPrivilegedClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new U2fPrivilegedClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
        }
    }

    public U2fPrivilegedClientImpl(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 118, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        bundle.putString("ACTION_START_SERVICE", "com.google.android.gms.fido.u2f.privileged.START");
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 13000000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getServiceDescriptor() {
        return "com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.gms.fido.u2f.privileged.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public boolean usesClientTelemetry() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public IU2fPrivilegedService createServiceInterface(IBinder iBinder) {
        return IU2fPrivilegedService.Stub.asInterface(iBinder);
    }
}
