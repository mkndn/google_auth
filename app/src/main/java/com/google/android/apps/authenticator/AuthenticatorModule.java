package com.google.android.apps.authenticator;

import android.content.Context;
import com.google.android.apps.authenticator.common.ApplicationContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AuthenticatorModule {
    private Context applicationContext;

    public AuthenticatorModule() {
    }

    @ApplicationContext
    public Context provideApplicationContext() {
        return this.applicationContext;
    }

    public AuthenticatorModule(Context context) {
        this.applicationContext = context;
    }
}
