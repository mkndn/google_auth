package com.google.android.apps.authenticator.testability.android.app;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PendingIntentFactory$$InjectAdapter extends Binding implements Provider {
    public PendingIntentFactory$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.app.PendingIntentFactory", "members/com.google.android.apps.authenticator.testability.android.app.PendingIntentFactory", false, PendingIntentFactory.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public PendingIntentFactory get() {
        return new PendingIntentFactory();
    }
}
