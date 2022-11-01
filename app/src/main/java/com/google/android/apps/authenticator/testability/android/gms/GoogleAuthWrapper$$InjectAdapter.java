package com.google.android.apps.authenticator.testability.android.gms;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleAuthWrapper$$InjectAdapter extends Binding implements Provider {
    public GoogleAuthWrapper$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.gms.GoogleAuthWrapper", "members/com.google.android.apps.authenticator.testability.android.gms.GoogleAuthWrapper", false, GoogleAuthWrapper.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public GoogleAuthWrapper get() {
        return new GoogleAuthWrapper();
    }
}
