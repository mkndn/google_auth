package com.google.android.apps.authenticator.timesync;

import dagger.internal.Binding;
import dagger.internal.BindingsGroup;
import dagger.internal.Linker;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeSyncModule$$ModuleAdapter extends ModuleAdapter {
    private static final String[] INJECTS = {"members/com.google.android.apps.authenticator.timesync.SyncNowActivity"};
    private static final Class[] STATIC_INJECTIONS = new Class[0];
    private static final Class[] INCLUDES = new Class[0];

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesHttpURLConnectionFactoryProvidesAdapter extends ProvidesBinding implements Provider {
        private final TimeSyncModule module;

        public ProvidesHttpURLConnectionFactoryProvidesAdapter(TimeSyncModule timeSyncModule) {
            super("com.google.android.apps.authenticator.timesync.HttpURLConnectionFactory", false, "com.google.android.apps.authenticator.timesync.TimeSyncModule", "providesHttpURLConnectionFactory");
            this.module = timeSyncModule;
            setLibrary(true);
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public HttpURLConnectionFactory get() {
            return this.module.providesHttpURLConnectionFactory();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProvidesNetworkTimeProviderProvidesAdapter extends ProvidesBinding implements Provider {
        private Binding factory;
        private final TimeSyncModule module;

        public ProvidesNetworkTimeProviderProvidesAdapter(TimeSyncModule timeSyncModule) {
            super("com.google.android.apps.authenticator.timesync.NetworkTimeProvider", true, "com.google.android.apps.authenticator.timesync.TimeSyncModule", "providesNetworkTimeProvider");
            this.module = timeSyncModule;
            setLibrary(true);
        }

        @Override // dagger.internal.Binding
        public void attach(Linker linker) {
            this.factory = linker.requestBinding("com.google.android.apps.authenticator.timesync.HttpURLConnectionFactory", TimeSyncModule.class, getClass().getClassLoader());
        }

        @Override // dagger.internal.ProvidesBinding, dagger.internal.Binding, javax.inject.Provider
        public NetworkTimeProvider get() {
            return this.module.providesNetworkTimeProvider((HttpURLConnectionFactory) this.factory.get());
        }

        @Override // dagger.internal.Binding
        public void getDependencies(Set set, Set set2) {
            set.add(this.factory);
        }
    }

    public TimeSyncModule$$ModuleAdapter() {
        super(TimeSyncModule.class, INJECTS, STATIC_INJECTIONS, false, INCLUDES, false, true);
    }

    @Override // dagger.internal.ModuleAdapter
    public void getBindings(BindingsGroup bindingsGroup, TimeSyncModule timeSyncModule) {
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.timesync.NetworkTimeProvider", new ProvidesNetworkTimeProviderProvidesAdapter(timeSyncModule));
        bindingsGroup.contributeProvidesBinding("com.google.android.apps.authenticator.timesync.HttpURLConnectionFactory", new ProvidesHttpURLConnectionFactoryProvidesAdapter(timeSyncModule));
    }

    @Override // dagger.internal.ModuleAdapter
    public TimeSyncModule newModule() {
        return new TimeSyncModule();
    }
}
