package com.google.android.libraries.material.motion;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TranslationProperty extends Property {
    public static final Property TRANSLATION = new TranslationProperty("translation");

    private TranslationProperty(String str) {
        super(PointF.class, str);
    }

    @Override // android.util.Property
    public PointF get(View view) {
        return new PointF(view.getTranslationX(), view.getTranslationY());
    }

    @Override // android.util.Property
    public void set(View view, PointF pointF) {
        view.setTranslationX(pointF.x);
        view.setTranslationY(pointF.y);
    }
}
