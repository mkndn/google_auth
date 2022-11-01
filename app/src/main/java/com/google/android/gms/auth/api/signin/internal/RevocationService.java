package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.IRevocationService;
import com.google.android.gms.common.util.UidVerifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RevocationService extends IRevocationService.Stub {
    private final Context mContext;

    public RevocationService(Context context) {
        this.mContext = context;
    }

    private void checkBinderCallerIsGmsCore() {
        if (!UidVerifier.isGooglePlayServicesUid(this.mContext, Binder.getCallingUid())) {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }

    private void tryRevokeAndClearDefaultAccount() {
        Storage storage = Storage.getInstance(this.mContext);
        GoogleSignInAccount savedDefaultGoogleSignInAccount = storage.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            googleSignInOptions = storage.getSavedDefaultGoogleSignInOptions();
        }
        GoogleSignInClient client = GoogleSignIn.getClient(this.mContext, googleSignInOptions);
        if (savedDefaultGoogleSignInAccount != null) {
            client.revokeAccess();
        } else {
            client.signOut();
        }
    }

    @Override // com.google.android.gms.auth.api.signin.internal.IRevocationService
    public void cleanupClientState() {
        checkBinderCallerIsGmsCore();
        GoogleSignInSession.getInstance(this.mContext).clear();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.IRevocationService
    public void revokeAccess() {
        checkBinderCallerIsGmsCore();
        tryRevokeAndClearDefaultAccount();
    }
}
