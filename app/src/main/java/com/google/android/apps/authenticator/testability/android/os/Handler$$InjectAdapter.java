package com.google.android.apps.authenticator.testability.android.os;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Handler$$InjectAdapter extends Binding implements Provider {
    public Handler$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.os.Handler", "members/com.google.android.apps.authenticator.testability.android.os.Handler", false, Handler.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public Handler get() {
        return new Handler();
    }
}
