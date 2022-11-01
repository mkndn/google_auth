package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.ISignInCallbacks;
import com.google.android.gms.common.api.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AbstractSignInCallbacks extends ISignInCallbacks.Stub {
    @Override // com.google.android.gms.auth.api.signin.internal.ISignInCallbacks
    public void onAccessRevokedFromGoogle(Status status) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.ISignInCallbacks
    public void onSignedOutFromGoogle(Status status) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.auth.api.signin.internal.ISignInCallbacks
    public void onSilentSignedInToGoogle(GoogleSignInAccount googleSignInAccount, Status status) {
        throw new UnsupportedOperationException();
    }
}
