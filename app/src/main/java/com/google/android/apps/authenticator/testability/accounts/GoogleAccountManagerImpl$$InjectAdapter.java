package com.google.android.apps.authenticator.testability.accounts;

import android.content.Context;
import com.google.android.apps.authenticator.testability.android.gms.GoogleAuthWrapper;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleAccountManagerImpl$$InjectAdapter extends Binding implements Provider {
    private Binding context;
    private Binding googleAuthWrapper;

    public GoogleAccountManagerImpl$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.accounts.GoogleAccountManagerImpl", "members/com.google.android.apps.authenticator.testability.accounts.GoogleAccountManagerImpl", false, GoogleAccountManagerImpl.class);
    }

    @Override // dagger.internal.Binding
    public void attach(Linker linker) {
        this.context = linker.requestBinding("@com.google.android.apps.authenticator.common.ApplicationContext()/android.content.Context", GoogleAccountManagerImpl.class, getClass().getClassLoader());
        this.googleAuthWrapper = linker.requestBinding("com.google.android.apps.authenticator.testability.android.gms.GoogleAuthWrapper", GoogleAccountManagerImpl.class, getClass().getClassLoader());
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public GoogleAccountManagerImpl get() {
        return new GoogleAccountManagerImpl((Context) this.context.get(), (GoogleAuthWrapper) this.googleAuthWrapper.get());
    }

    @Override // dagger.internal.Binding
    public void getDependencies(Set set, Set set2) {
        set.add(this.context);
        set.add(this.googleAuthWrapper);
    }
}
