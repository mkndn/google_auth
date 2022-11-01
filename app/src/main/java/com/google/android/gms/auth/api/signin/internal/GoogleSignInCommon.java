package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleSignInCommon {
    private static Logger mLogger = new Logger("GoogleSignInCommon", new String[0]);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class GoogleSignInApiMethodImpl extends BaseImplementation$ApiMethodImpl {
        public GoogleSignInApiMethodImpl(GoogleApiClient googleApiClient) {
            super(Auth.GOOGLE_SIGN_IN_API, googleApiClient);
        }
    }

    private static void cleanupClientState(Context context) {
        GoogleSignInSession.getInstance(context).clear();
        for (GoogleApiClient googleApiClient : GoogleApiClient.getAllClients()) {
            googleApiClient.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }

    public static PendingResult revokeAccess(GoogleApiClient googleApiClient, Context context, boolean z) {
        mLogger.d("Revoking access", new Object[0]);
        String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        cleanupClientState(context);
        if (z) {
            return FallbackRevokeAccessOperation.execute(savedRefreshToken);
        }
        return googleApiClient.execute(new GoogleSignInApiMethodImpl(googleApiClient) { // from class: com.google.android.gms.auth.api.signin.internal.GoogleSignInCommon.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.internal.BasePendingResult
            public Status createFailedResult(Status status) {
                return status;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
            public void doExecute(GoogleSignInClientImpl googleSignInClientImpl) {
                ((ISignInService) googleSignInClientImpl.getService()).revokeAccessFromGoogle(new AbstractSignInCallbacks() { // from class: com.google.android.gms.auth.api.signin.internal.GoogleSignInCommon.3.1
                    @Override // com.google.android.gms.auth.api.signin.internal.AbstractSignInCallbacks, com.google.android.gms.auth.api.signin.internal.ISignInCallbacks
                    public void onAccessRevokedFromGoogle(Status status) {
                        setResult(status);
                    }
                }, googleSignInClientImpl.getSignInOptions());
            }
        });
    }

    public static PendingResult signOut(GoogleApiClient googleApiClient, Context context, boolean z) {
        mLogger.d("Signing out", new Object[0]);
        cleanupClientState(context);
        if (z) {
            return PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return googleApiClient.execute(new GoogleSignInApiMethodImpl(googleApiClient) { // from class: com.google.android.gms.auth.api.signin.internal.GoogleSignInCommon.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.internal.BasePendingResult
            public Status createFailedResult(Status status) {
                return status;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl
            public void doExecute(GoogleSignInClientImpl googleSignInClientImpl) {
                ((ISignInService) googleSignInClientImpl.getService()).signOutFromGoogle(new AbstractSignInCallbacks() { // from class: com.google.android.gms.auth.api.signin.internal.GoogleSignInCommon.2.1
                    @Override // com.google.android.gms.auth.api.signin.internal.AbstractSignInCallbacks, com.google.android.gms.auth.api.signin.internal.ISignInCallbacks
                    public void onSignedOutFromGoogle(Status status) {
                        setResult(status);
                    }
                }, googleSignInClientImpl.getSignInOptions());
            }
        });
    }
}
