package com.google.android.apps.authenticator;

import android.content.Context;
import com.google.android.apps.authenticator.api.AuthenticateModule;
import com.google.android.apps.authenticator.common.AndroidDependenciesModule;
import com.google.android.apps.authenticator.crypto.CryptoModule;
import com.google.android.apps.authenticator.otp.OtpModule;
import com.google.android.apps.authenticator.timesync.TimeSyncModule;
import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthenticatorModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = {"members/com.google.android.apps.authenticator.AuthenticatorActivity", "members/com.google.android.apps.authenticator.barcode.BarcodeCaptureActivity"};
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = {AndroidDependenciesModule.class, AuthenticateModule.class, CryptoModule.class, OtpModule.class, TimeSyncModule.class};

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideApplicationContextProvidesAdapter extends ProvidesBinding implements Provider {
        private final AuthenticatorModule module;

        public ProvideApplicationContextProvidesAdapter(AuthenticatorModule authenticatorModule) {
            super("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", false, "com.google.android.apps.authenticator.AuthenticatorModule", "provideApplicationContext");
            this.module = authenticatorModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public Context get() {
            return this.module.provideApplicationContext();
        }
    }

    public AuthenticatorModule$$ModuleAdapter() {
        super(AuthenticatorModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, true, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, AuthenticatorModule authenticatorModule) {
        bindingsGroup.contributeProvidesBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", new ProvideApplicationContextProvidesAdapter(authenticatorModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public AuthenticatorModule newModule() {
        return new AuthenticatorModule();
    }
}
