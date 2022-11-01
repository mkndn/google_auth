package com.google.android.apps.authenticator.testability.android.widget;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseAdapter extends android.widget.BaseAdapter {
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view != null ? new com.google.android.apps.authenticator.testability.android.view.View(view) : null, viewGroup != null ? new com.google.android.apps.authenticator.testability.android.view.ViewGroup(viewGroup) : null).getAndroidView();
    }

    public com.google.android.apps.authenticator.testability.android.view.View getView(int i, com.google.android.apps.authenticator.testability.android.view.View view, com.google.android.apps.authenticator.testability.android.view.ViewGroup viewGroup) {
        throw new UnsupportedOperationException("Override this method in subclasses");
    }
}
