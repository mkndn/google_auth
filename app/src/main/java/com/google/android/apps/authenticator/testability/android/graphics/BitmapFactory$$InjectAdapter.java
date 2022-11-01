package com.google.android.apps.authenticator.testability.android.graphics;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BitmapFactory$$InjectAdapter extends Binding implements Provider {
    public BitmapFactory$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.graphics.BitmapFactory", "members/com.google.android.apps.authenticator.testability.android.graphics.BitmapFactory", false, BitmapFactory.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public BitmapFactory get() {
        return new BitmapFactory();
    }
}
