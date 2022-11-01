package com.google.android.apps.authenticator;

import com.google.android.apps.authenticator.otp.OtpSource;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthenticatorActivity$$InjectAdapter extends Binding implements Provider, MembersInjector {
    private Binding otpProvider;

    public AuthenticatorActivity$$InjectAdapter() {
        super("com.google.android.apps.authenticator.AuthenticatorActivity", "members/com.google.android.apps.authenticator.AuthenticatorActivity", false, AuthenticatorActivity.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.otpProvider = linker.requestBinding("com.google.android.apps.authenticator.otp.OtpSource", AuthenticatorActivity.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public AuthenticatorActivity get() {
        AuthenticatorActivity authenticatorActivity = new AuthenticatorActivity();
        injectMembers(authenticatorActivity);
        return authenticatorActivity;
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set2.add(this.otpProvider);
    }

    @Override // dagger.internal.Binding
    public void injectMembers(AuthenticatorActivity authenticatorActivity) {
        authenticatorActivity.otpProvider = (OtpSource) this.otpProvider.get();
    }
}
