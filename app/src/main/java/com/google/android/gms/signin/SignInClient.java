package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.signin.internal.ISignInCallbacks;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SignInClient extends Api.Client {
    void connect();

    void signIn(ISignInCallbacks iSignInCallbacks);
}
