package com.google.android.gms.auth.account.data;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.auth.AuthLoggers;
import com.google.android.gms.auth.Features;
import com.google.android.gms.auth.account.data.IGetTokenWithDetailsCallback;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class InternalGoogleAuthServiceClient extends GoogleApi implements GoogleAuthServiceClient {
    private static final Api API;
    private static final Api.ClientKey CLIENT_KEY;
    private static final Api.AbstractClientBuilder clientBuilder;
    private static final Logger logger;
    private final Context context;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY = clientKey;
        Api.AbstractClientBuilder abstractClientBuilder = new Api.AbstractClientBuilder() { // from class: com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient.1
            @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
            public GoogleAuthServiceClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Api.ApiOptions.NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new GoogleAuthServiceClientImpl(context, looper, clientSettings, connectionCallbacks, onConnectionFailedListener);
            }
        };
        clientBuilder = abstractClientBuilder;
        API = new Api("GoogleAuthService.API", abstractClientBuilder, clientKey);
        logger = AuthLoggers.newLogger("GoogleAuthServiceClient");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalGoogleAuthServiceClient(Context context) {
        super(context, API, Api.ApiOptions.NoOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void trySetResultOrApiException(Status status, Object obj, TaskCompletionSource taskCompletionSource) {
        if (!TaskUtil.trySetResultOrApiException(status, obj, taskCompletionSource)) {
            logger.w("The task is already complete.", new Object[0]);
        }
    }

    @Override // com.google.android.gms.auth.account.data.GoogleAuthServiceClient
    public Task getTokenWithDetails(final Account account, final String str, final Bundle bundle) {
        Preconditions.checkNotNull(account, "Account name cannot be null!");
        Preconditions.checkNotEmpty(str, "Scope cannot be null!");
        return doWrite(TaskApiCall.builder().setFeatures(Features.GOOGLE_AUTH_SERVICE_TOKEN).run(new RemoteCall() { // from class: com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                InternalGoogleAuthServiceClient.this.m87x408ace3a(account, str, bundle, (GoogleAuthServiceClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).setMethodKey(1512).build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getTokenWithDetails$0$com-google-android-gms-auth-account-data-InternalGoogleAuthServiceClient  reason: not valid java name */
    public /* synthetic */ void m87x408ace3a(Account account, String str, Bundle bundle, GoogleAuthServiceClientImpl googleAuthServiceClientImpl, final TaskCompletionSource taskCompletionSource) {
        IGoogleAuthService iGoogleAuthService = (IGoogleAuthService) googleAuthServiceClientImpl.getService();
        IGetTokenWithDetailsCallback.Stub stub = new IGetTokenWithDetailsCallback.Stub(this) { // from class: com.google.android.gms.auth.account.data.InternalGoogleAuthServiceClient.2
            @Override // com.google.android.gms.auth.account.data.IGetTokenWithDetailsCallback
            public void onResponse(Status status, Bundle bundle2) {
                InternalGoogleAuthServiceClient.trySetResultOrApiException(status, bundle2, taskCompletionSource);
            }
        };
        if (bundle == null) {
            bundle = new Bundle();
        }
        iGoogleAuthService.getTokenWithDetails(stub, account, str, bundle);
    }
}
