package com.google.android.apps.authenticator.timesync;

import com.google.android.apps.authenticator.otp.TotpClock;
import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SyncNowActivity$$InjectAdapter extends Binding implements Provider, MembersInjector {
    private Binding mNetworkTimeProvider;
    private Binding mTotpClock;

    public SyncNowActivity$$InjectAdapter() {
        super("com.google.android.apps.authenticator.timesync.SyncNowActivity", "members/com.google.android.apps.authenticator.timesync.SyncNowActivity", false, SyncNowActivity.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.mTotpClock = linker.requestBinding("com.google.android.apps.authenticator.otp.TotpClock", SyncNowActivity.class, getClass().getClassLoader());
        this.mNetworkTimeProvider = linker.requestBinding("com.google.android.apps.authenticator.timesync.NetworkTimeProvider", SyncNowActivity.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public SyncNowActivity get() {
        SyncNowActivity syncNowActivity = new SyncNowActivity();
        injectMembers(syncNowActivity);
        return syncNowActivity;
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set2.add(this.mTotpClock);
        set2.add(this.mNetworkTimeProvider);
    }

    @Override // dagger.internal.Binding
    public void injectMembers(SyncNowActivity syncNowActivity) {
        syncNowActivity.mTotpClock = (TotpClock) this.mTotpClock.get();
        syncNowActivity.mNetworkTimeProvider = (NetworkTimeProvider) this.mNetworkTimeProvider.get();
    }
}
