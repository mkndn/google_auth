package com.google.android.apps.authenticator.testability.android.widget;

import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Button extends TextView {
    private final android.widget.Button button;

    public Button(android.widget.Button button) {
        super(button);
        this.button = button;
    }

    @Override // com.google.android.apps.authenticator.testability.android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.button.setOnClickListener(onClickListener);
    }
}
