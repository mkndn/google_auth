package com.google.android.apps.authenticator.crypto;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.security.MessageDigest;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CryptoModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = new String[0];
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = new Class[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideMessageDigestMd5ProvidesAdapter extends ProvidesBinding implements Provider {
        private final CryptoModule module;

        public ProvideMessageDigestMd5ProvidesAdapter(CryptoModule cryptoModule) {
            super("@javax.inject.Named(value=MD5)/java.security.MessageDigest", false, "com.google.android.apps.authenticator.crypto.CryptoModule", "provideMessageDigestMd5");
            this.module = cryptoModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public MessageDigest get() {
            return this.module.provideMessageDigestMd5();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvideMessageDigestSha256ProvidesAdapter extends ProvidesBinding implements Provider {
        private final CryptoModule module;

        public ProvideMessageDigestSha256ProvidesAdapter(CryptoModule cryptoModule) {
            super("@javax.inject.Named(value=SHA256)/java.security.MessageDigest", false, "com.google.android.apps.authenticator.crypto.CryptoModule", "provideMessageDigestSha256");
            this.module = cryptoModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public MessageDigest get() {
            return this.module.provideMessageDigestSha256();
        }
    }

    public CryptoModule$$ModuleAdapter() {
        super(CryptoModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, true, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, CryptoModule cryptoModule) {
        bindingsGroup.contributeProvidesBinding("@javax.inject.Named(value=MD5)/java.security.MessageDigest", new ProvideMessageDigestMd5ProvidesAdapter(cryptoModule));
        bindingsGroup.contributeProvidesBinding("@javax.inject.Named(value=SHA256)/java.security.MessageDigest", new ProvideMessageDigestSha256ProvidesAdapter(cryptoModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public CryptoModule newModule() {
        return new CryptoModule();
    }
}
