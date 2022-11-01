package com.google.android.apps.authenticator.testability.android.widget;

import com.google.android.apps.authenticator.testability.android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TextView extends View {
    private final android.widget.TextView textView;

    public TextView(android.widget.TextView textView) {
        super(textView);
        this.textView = textView;
    }

    public void setText(int i) {
        this.textView.setText(i);
    }

    public void setText(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }
}
