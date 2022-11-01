package com.google.android.gms.fido.u2f;

import android.app.Activity;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.fido.u2f.api.common.BrowserRegisterRequestParams;
import com.google.android.gms.fido.u2f.api.common.BrowserSignRequestParams;
import com.google.android.gms.fido.u2f.internal.U2fPendingIntentImpl;
import com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedCallbacks;
import com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedService;
import com.google.android.gms.fido.u2f.internal.privileged.U2fPrivilegedClientImpl;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public class U2fPrivilegedApiClient extends GoogleApi {
    private static final Api.ClientKey CLIENT_KEY_U2F_PRIVILEGED_API;
    private static final Api U2F_PRIVILEGED_API;

    static {
        Api.ClientKey clientKey = new Api.ClientKey();
        CLIENT_KEY_U2F_PRIVILEGED_API = clientKey;
        U2F_PRIVILEGED_API = new Api("Fido.U2F_PRIVILEGED_API", new U2fPrivilegedClientImpl.ClientBuilder(), clientKey);
    }

    public U2fPrivilegedApiClient(Activity activity) {
        super(activity, U2F_PRIVILEGED_API, (Api.ApiOptions) Api.ApiOptions.NO_OPTIONS, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getRegisterIntent$0$com-google-android-gms-fido-u2f-U2fPrivilegedApiClient  reason: not valid java name */
    public /* synthetic */ void m216xb1527ee6(BrowserRegisterRequestParams browserRegisterRequestParams, U2fPrivilegedClientImpl u2fPrivilegedClientImpl, final TaskCompletionSource taskCompletionSource) {
        ((IU2fPrivilegedService) u2fPrivilegedClientImpl.getService()).getRegisterIntent(new IU2fPrivilegedCallbacks.Stub(this) { // from class: com.google.android.gms.fido.u2f.U2fPrivilegedApiClient.1
            @Override // com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedCallbacks
            public void onPendingIntentFetched(Status status, PendingIntent pendingIntent) {
                TaskUtil.setResultOrApiException(status, new U2fPendingIntentImpl(pendingIntent), taskCompletionSource);
            }
        }, browserRegisterRequestParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getSignIntent$1$com-google-android-gms-fido-u2f-U2fPrivilegedApiClient  reason: not valid java name */
    public /* synthetic */ void m217x234522eb(BrowserSignRequestParams browserSignRequestParams, U2fPrivilegedClientImpl u2fPrivilegedClientImpl, final TaskCompletionSource taskCompletionSource) {
        ((IU2fPrivilegedService) u2fPrivilegedClientImpl.getService()).getSignIntent(new IU2fPrivilegedCallbacks.Stub(this) { // from class: com.google.android.gms.fido.u2f.U2fPrivilegedApiClient.2
            @Override // com.google.android.gms.fido.u2f.internal.privileged.IU2fPrivilegedCallbacks
            public void onPendingIntentFetched(Status status, PendingIntent pendingIntent) {
                TaskUtil.setResultOrApiException(status, new U2fPendingIntentImpl(pendingIntent), taskCompletionSource);
            }
        }, browserSignRequestParams);
    }

    public Task getRegisterIntent(final BrowserRegisterRequestParams browserRegisterRequestParams) {
        return doRead(TaskApiCall.builder().setMethodKey(5426).run(new RemoteCall() { // from class: com.google.android.gms.fido.u2f.U2fPrivilegedApiClient$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                U2fPrivilegedApiClient.this.m216xb1527ee6(browserRegisterRequestParams, (U2fPrivilegedClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }

    public Task getSignIntent(final BrowserSignRequestParams browserSignRequestParams) {
        return doRead(TaskApiCall.builder().setMethodKey(5427).run(new RemoteCall() { // from class: com.google.android.gms.fido.u2f.U2fPrivilegedApiClient$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                U2fPrivilegedApiClient.this.m217x234522eb(browserSignRequestParams, (U2fPrivilegedClientImpl) obj, (TaskCompletionSource) obj2);
            }
        }).build());
    }
}
