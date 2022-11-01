package com.google.android.apps.authenticator.testability.android.widget;

import dagger.internal.Binding;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToastFactory$$InjectAdapter extends Binding implements Provider {
    public ToastFactory$$InjectAdapter() {
        super("com.google.android.apps.authenticator.testability.android.widget.ToastFactory", "members/com.google.android.apps.authenticator.testability.android.widget.ToastFactory", false, ToastFactory.class);
    }

    @Override // dagger.internal.Binding, javax.inject.Provider
    public ToastFactory get() {
        return new ToastFactory();
    }
}
