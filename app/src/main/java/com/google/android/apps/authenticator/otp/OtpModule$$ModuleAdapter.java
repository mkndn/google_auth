package com.google.android.apps.authenticator.otp;

import android.content.Context;
import com.google.android.apps.authenticator.time.Clock;
import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OtpModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = new String[0];
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = new Class[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesAccountDbProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private final OtpModule module;

        public ProvidesAccountDbProvidesAdapter(OtpModule otpModule) {
            super("com.google.android.apps.authenticator.otp.AccountDb", true, "com.google.android.apps.authenticator.otp.OtpModule", "providesAccountDb");
            this.module = otpModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", OtpModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public AccountDb get() {
            return this.module.providesAccountDb((Context) this.applicationContext.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesOtpSourceProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding accountDb;
        private final OtpModule module;
        private Binding totpClock;

        public ProvidesOtpSourceProvidesAdapter(OtpModule otpModule) {
            super("com.google.android.apps.authenticator.otp.OtpSource", true, "com.google.android.apps.authenticator.otp.OtpModule", "providesOtpSource");
            this.module = otpModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.accountDb = linker.requestBinding("com.google.android.apps.authenticator.otp.AccountDb", OtpModule.class, getClass().getClassLoader());
            this.totpClock = linker.requestBinding("com.google.android.apps.authenticator.otp.TotpClock", OtpModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public OtpSource get() {
            return this.module.providesOtpSource((AccountDb) this.accountDb.get(), (TotpClock) this.totpClock.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.accountDb);
            set.add(this.totpClock);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesTotpClockProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding applicationContext;
        private Binding clock;
        private final OtpModule module;

        public ProvidesTotpClockProvidesAdapter(OtpModule otpModule) {
            super("com.google.android.apps.authenticator.otp.TotpClock", true, "com.google.android.apps.authenticator.otp.OtpModule", "providesTotpClock");
            this.module = otpModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.applicationContext = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", OtpModule.class, getClass().getClassLoader());
            this.clock = linker.requestBinding("com.google.android.apps.authenticator.time.Clock", OtpModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public TotpClock get() {
            return this.module.providesTotpClock((Context) this.applicationContext.get(), (Clock) this.clock.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.applicationContext);
            set.add(this.clock);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesTotpCountdownTaskProvidesAdapter extends ProvidesBinding implements Provider {
        private final OtpModule module;
        private Binding totpClock;
        private Binding totpCounter;

        public ProvidesTotpCountdownTaskProvidesAdapter(OtpModule otpModule) {
            super("com.google.android.apps.authenticator.otp.TotpCountdownTask", false, "com.google.android.apps.authenticator.otp.OtpModule", "providesTotpCountdownTask");
            this.module = otpModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.totpCounter = linker.requestBinding("com.google.android.apps.authenticator.otp.TotpCounter", OtpModule.class, getClass().getClassLoader());
            this.totpClock = linker.requestBinding("com.google.android.apps.authenticator.otp.TotpClock", OtpModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public TotpCountdownTask get() {
            return this.module.providesTotpCountdownTask((TotpCounter) this.totpCounter.get(), (TotpClock) this.totpClock.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.totpCounter);
            set.add(this.totpClock);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesTotpCounterProvidesAdapter extends ProvidesBinding implements Provider {
        private final OtpModule module;
        private Binding otpSource;

        public ProvidesTotpCounterProvidesAdapter(OtpModule otpModule) {
            super("com.google.android.apps.authenticator.otp.TotpCounter", false, "com.google.android.apps.authenticator.otp.OtpModule", "providesTotpCounter");
            this.module = otpModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.otpSource = linker.requestBinding("com.google.android.apps.authenticator.otp.OtpSource", OtpModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public TotpCounter get() {
            return this.module.providesTotpCounter((OtpSource) this.otpSource.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.otpSource);
        }
    }

    public OtpModule$$ModuleAdapter() {
        super(OtpModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, false, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, OtpModule otpModule) {
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.otp.TotpClock", new ProvidesTotpClockProvidesAdapter(otpModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.otp.OtpSource", new ProvidesOtpSourceProvidesAdapter(otpModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.otp.TotpCounter", new ProvidesTotpCounterProvidesAdapter(otpModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.otp.TotpCountdownTask", new ProvidesTotpCountdownTaskProvidesAdapter(otpModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.otp.AccountDb", new ProvidesAccountDbProvidesAdapter(otpModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public OtpModule newModule() {
        return new OtpModule();
    }
}
