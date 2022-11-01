package com.google.android.gms.auth.api.signin;

import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInCommon;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleSignInClient extends GoogleApi {
    private static final GoogleSignInAccountResultConverter googleSignInAccountResultConverter = new GoogleSignInAccountResultConverter();
    static Implementation mImplementation = Implementation.UNINITIALIZED;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class GoogleSignInAccountResultConverter implements PendingResultUtil.ResultConverter {
        private GoogleSignInAccountResultConverter() {
        }

        @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
        public GoogleSignInAccount convert(GoogleSignInResult googleSignInResult) {
            return googleSignInResult.getSignInAccount();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Implementation {
        UNINITIALIZED,
        NONE,
        FALLBACK,
        GMS
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new ApiExceptionMapper());
    }

    private synchronized Implementation getImplementation() {
        if (mImplementation == Implementation.UNINITIALIZED) {
            Context applicationContext = getApplicationContext();
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int isGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(applicationContext, 12451000);
            if (isGooglePlayServicesAvailable == 0) {
                mImplementation = Implementation.GMS;
            } else if (googleApiAvailability.getErrorResolutionIntent(applicationContext, isGooglePlayServicesAvailable, null) == null && DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") != 0) {
                mImplementation = Implementation.FALLBACK;
            } else {
                mImplementation = Implementation.NONE;
            }
        }
        return mImplementation;
    }

    public Task revokeAccess() {
        return PendingResultUtil.toVoidTask(GoogleSignInCommon.revokeAccess(asGoogleApiClient(), getApplicationContext(), getImplementation() == Implementation.FALLBACK));
    }

    public Task signOut() {
        return PendingResultUtil.toVoidTask(GoogleSignInCommon.signOut(asGoogleApiClient(), getApplicationContext(), getImplementation() == Implementation.FALLBACK));
    }
}
