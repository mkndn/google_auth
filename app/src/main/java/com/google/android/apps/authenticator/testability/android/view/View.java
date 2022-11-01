package com.google.android.apps.authenticator.testability.android.view;

import android.view.View;
import android.view.animation.Animation;
import com.google.android.apps.authenticator.testability.android.widget.ImageButton;
import com.google.android.apps.authenticator.testability.android.widget.ImageView;
import com.google.android.apps.authenticator.testability.android.widget.TextView;

/* compiled from: PG */
/* loaded from: classes.dex */
public class View {
    public static final int GONE = 8;
    public static final int VISIBLE = 0;
    private final android.view.View view;

    public View(android.view.View view) {
        this.view = view;
    }

    public ImageButton findImageButtonById(int i) {
        android.view.View findViewById = this.view.findViewById(i);
        if (findViewById != null) {
            return new ImageButton((android.widget.ImageButton) findViewById);
        }
        return null;
    }

    public ImageView findImageViewById(int i) {
        android.view.View findViewById = this.view.findViewById(i);
        if (findViewById != null) {
            return new ImageView((android.widget.ImageView) findViewById);
        }
        return null;
    }

    public TextView findTextViewById(int i) {
        android.view.View findViewById = this.view.findViewById(i);
        if (findViewById != null) {
            return new TextView((android.widget.TextView) findViewById);
        }
        return null;
    }

    public View findViewById(int i) {
        if (this.view.findViewById(i) != null) {
            return new View(this.view.findViewById(i));
        }
        return null;
    }

    public android.view.View getAndroidView() {
        return this.view;
    }

    public int getVisibility() {
        return this.view.getVisibility();
    }

    public void invalidate() {
        this.view.invalidate();
    }

    public void setEnabled(boolean z) {
        this.view.setEnabled(z);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.view.setOnClickListener(onClickListener);
    }

    public void setVisibility(int i) {
        this.view.setVisibility(i);
    }

    public void startAnimation(Animation animation) {
        this.view.startAnimation(animation);
    }
}
