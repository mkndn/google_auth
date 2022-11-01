package com.google.android.apps.authenticator.timesync;

import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkTimeProvider$$InjectAdapter extends Binding implements Provider {
    private Binding urlConnectionFactory;

    public NetworkTimeProvider$$InjectAdapter() {
        super("com.google.android.apps.authenticator.timesync.NetworkTimeProvider", "members/com.google.android.apps.authenticator.timesync.NetworkTimeProvider", false, NetworkTimeProvider.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.urlConnectionFactory = linker.requestBinding("com.google.android.apps.authenticator.timesync.HttpURLConnectionFactory", NetworkTimeProvider.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public NetworkTimeProvider get() {
        return new NetworkTimeProvider((HttpURLConnectionFactory) this.urlConnectionFactory.get());
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set.add(this.urlConnectionFactory);
    }
}
