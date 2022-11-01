package com.google.android.apps.authenticator.testability.android.view.animation;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AnimationUtils$$InjectAdapter extends Binding implements Provider {
    public AnimationUtils$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.view.animation.AnimationUtils", "members/com.google.android.apps.authenticator.testability.android.view.animation.AnimationUtils", false, AnimationUtils.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public AnimationUtils get() {
        return new AnimationUtils();
    }
}
