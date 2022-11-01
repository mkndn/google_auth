package com.google.android.libraries.material.speeddial;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScrimView extends View {
    public ScrimView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public ScrimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScrimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setVisibility(8);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrimView, i, R$style.InternalScrimViewStyle);
        setBackgroundColor(obtainStyledAttributes.getColor(R$styleable.ScrimView_scrimColor, ContextCompat.getColor(context, R$color.mtrl_default_background_scrim)));
        if (Build.VERSION.SDK_INT >= 21) {
            setElevation(obtainStyledAttributes.getDimension(R$styleable.ScrimView_android_elevation, 0.0f));
        }
        obtainStyledAttributes.recycle();
    }
}
