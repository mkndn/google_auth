package com.google.android.apps.authenticator.api;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthenticateActivity$$InjectAdapter extends Binding implements Provider, MembersInjector {
    private Binding mFacetIdCalculator;
    private Binding mIntentParser;
    private Binding mPackageSignatureFingerprintProvider;

    public AuthenticateActivity$$InjectAdapter() {
        super("com.google.android.apps.authenticator.api.AuthenticateActivity", "members/com.google.android.apps.authenticator.api.AuthenticateActivity", false, AuthenticateActivity.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.mIntentParser = linker.requestBinding("com.google.android.apps.authenticator.api.IntentExtraParser", AuthenticateActivity.class, getClass().getClassLoader());
        this.mPackageSignatureFingerprintProvider = linker.requestBinding("com.google.android.apps.authenticator.api.PackageSignatureFingerprintProvider", AuthenticateActivity.class, getClass().getClassLoader());
        this.mFacetIdCalculator = linker.requestBinding("com.google.android.apps.authenticator.api.FacetIdCalculator", AuthenticateActivity.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public AuthenticateActivity get() {
        AuthenticateActivity authenticateActivity = new AuthenticateActivity();
        injectMembers(authenticateActivity);
        return authenticateActivity;
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set2.add(this.mIntentParser);
        set2.add(this.mPackageSignatureFingerprintProvider);
        set2.add(this.mFacetIdCalculator);
    }

    @Override // dagger.internal.Binding
    public void injectMembers(AuthenticateActivity authenticateActivity) {
        authenticateActivity.mIntentParser = (IntentExtraParser) this.mIntentParser.get();
        authenticateActivity.mPackageSignatureFingerprintProvider = (PackageSignatureFingerprintProvider) this.mPackageSignatureFingerprintProvider.get();
        authenticateActivity.mFacetIdCalculator = (FacetIdCalculator) this.mFacetIdCalculator.get();
    }
}
