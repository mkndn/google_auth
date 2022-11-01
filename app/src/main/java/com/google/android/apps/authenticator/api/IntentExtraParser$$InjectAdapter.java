package com.google.android.apps.authenticator.api;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IntentExtraParser$$InjectAdapter extends Binding implements Provider {
    public IntentExtraParser$$InjectAdapter() {
        super("com.google.android.apps.authenticator.api.IntentExtraParser", "members/com.google.android.apps.authenticator.api.IntentExtraParser", false, IntentExtraParser.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public IntentExtraParser get() {
        return new IntentExtraParser();
    }
}
