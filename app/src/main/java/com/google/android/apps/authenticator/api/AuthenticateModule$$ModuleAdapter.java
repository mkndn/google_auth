package com.google.android.apps.authenticator.api;

import android.content.Context;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.security.MessageDigest;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthenticateModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = {"members/com.google.android.apps.authenticator.api.AuthenticateActivity"};
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = new Class[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideFacetIdCalculatorProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AuthenticateModule module;

        public ProvideFacetIdCalculatorProvidesAdapter(AuthenticateModule authenticateModule) {
            super("com.google.android.apps.authenticator.api.FacetIdCalculator", true, "com.google.android.apps.authenticator.api.AuthenticateModule", "provideFacetIdCalculator");
            this.module = authenticateModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AuthenticateModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public FacetIdCalculator get() {
            return this.module.provideFacetIdCalculator((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidePackageSignatureFingerprintProviderProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final AuthenticateModule module;
        private Binding sha256MessageDigest;

        public ProvidePackageSignatureFingerprintProviderProvidesAdapter(AuthenticateModule authenticateModule) {
            super("com.google.android.apps.authenticator.api.PackageSignatureFingerprintProvider", true, "com.google.android.apps.authenticator.api.AuthenticateModule", "providePackageSignatureFingerprintProvider");
            this.module = authenticateModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.sha256MessageDigest = linker.requestBinding("@javax.inject.Named(value=SHA256)/java.security.MessageDigest", AuthenticateModule.class, getClass().getClassLoader());
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", AuthenticateModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public PackageSignatureFingerprintProvider get() {
            return this.module.providePackageSignatureFingerprintProvider((MessageDigest) this.sha256MessageDigest.get(), (Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.sha256MessageDigest);
            set.add(this.applicationContext);
        }
    }

    public AuthenticateModule$$ModuleAdapter() {
        super(AuthenticateModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, false, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, AuthenticateModule authenticateModule) {
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.api.PackageSignatureFingerprintProvider", new ProvidePackageSignatureFingerprintProviderProvidesAdapter(authenticateModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.api.FacetIdCalculator", new ProvideFacetIdCalculatorProvidesAdapter(authenticateModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public AuthenticateModule newModule() {
        return new AuthenticateModule();
    }
}
