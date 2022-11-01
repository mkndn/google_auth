package com.google.android.gms.auth.account.data;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.auth.Features;
import com.google.android.gms.auth.account.data.IGoogleAuthService;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;

/* compiled from: PG */
/* loaded from: classes.dex */
class GoogleAuthServiceClientImpl extends GmsClient {
    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleAuthServiceClientImpl(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, (int) AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_FI_FUNCTIONAL_DEBUGGING_VALUE, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public void disconnect(String str) {
        Log.w("GoogleAuthSvcClientImpl", "GoogleAuthServiceClientImpl disconnected with reason: " + str);
        super.disconnect(str);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Feature[] getApiFeatures() {
        return new Feature[]{Features.GOOGLE_AUTH_SERVICE_TOKEN, Features.GOOGLE_AUTH_SERVICE_ACCOUNTS, Features.ACCOUNT_CAPABILITY_API};
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public int getMinApkVersion() {
        return 17895000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.data.IGoogleAuthService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected String getStartServiceAction() {
        return "com.google.android.gms.auth.account.authapi.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected boolean getUseDynamicLookup() {
        return true;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public boolean usesClientTelemetry() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public IGoogleAuthService createServiceInterface(IBinder iBinder) {
        return IGoogleAuthService.Stub.asInterface(iBinder);
    }
}
