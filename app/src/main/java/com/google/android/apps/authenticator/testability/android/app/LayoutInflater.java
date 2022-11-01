package com.google.android.apps.authenticator.testability.android.app;

import com.google.android.apps.authenticator.testability.android.view.View;
import com.google.android.apps.authenticator.testability.android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LayoutInflater {
    private final android.view.LayoutInflater layoutInflator;

    public LayoutInflater(android.view.LayoutInflater layoutInflater) {
        this.layoutInflator = layoutInflater;
    }

    public View inflate(int i, ViewGroup viewGroup, boolean z) {
        return new View(this.layoutInflator.inflate(i, viewGroup.getAndroidViewGroup(), z));
    }
}
