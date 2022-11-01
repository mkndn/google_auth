package com.google.android.apps.authenticator.testability.android.view;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ViewGroup extends View {
    private final android.view.ViewGroup viewGroup;

    public ViewGroup(android.view.ViewGroup viewGroup) {
        super(viewGroup);
        this.viewGroup = viewGroup;
    }

    public android.view.ViewGroup getAndroidViewGroup() {
        return this.viewGroup;
    }
}
