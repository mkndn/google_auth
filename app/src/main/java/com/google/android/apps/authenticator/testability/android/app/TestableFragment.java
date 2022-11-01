package com.google.android.apps.authenticator.testability.android.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import com.google.android.apps.authenticator.testability.android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TestableFragment extends Fragment {
    public Activity getAttachedActivity() {
        return super.getActivity();
    }

    public View getRootView() {
        android.view.View view = super.getView();
        if (view != null) {
            return new View(view);
        }
        return null;
    }

    @Override // android.app.Fragment
    public android.view.View onCreateView(android.view.LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return onCreateView(layoutInflater != null ? new LayoutInflater(layoutInflater) : null, viewGroup != null ? new com.google.android.apps.authenticator.testability.android.view.ViewGroup(viewGroup) : null, bundle).getAndroidView();
    }

    public View onCreateView(LayoutInflater layoutInflater, com.google.android.apps.authenticator.testability.android.view.ViewGroup viewGroup, Bundle bundle) {
        throw new UnsupportedOperationException("Override this class in the subclasses.");
    }
}
