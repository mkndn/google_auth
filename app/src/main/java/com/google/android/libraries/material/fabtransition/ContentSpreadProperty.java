package com.google.android.libraries.material.fabtransition;

import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
class ContentSpreadProperty extends Property {
    public static final Property CONTENT_SPREAD = new ContentSpreadProperty("contentSpread");

    private ContentSpreadProperty(String str) {
        super(Float.class, str);
    }

    @Override // android.util.Property
    public Float get(ViewGroup viewGroup) {
        Float f = (Float) viewGroup.getTag(R$id.mtrl_content_spread);
        if (f != null) {
            return f;
        }
        return Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public void set(ViewGroup viewGroup, Float f) {
        float floatValue = f.floatValue();
        viewGroup.setTag(R$id.mtrl_content_spread, Float.valueOf(floatValue));
        if (viewGroup instanceof ContentContainer) {
            viewGroup = ((ContentContainer) viewGroup).getContainer();
        }
        float width = viewGroup.getWidth() / 2.0f;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setTranslationX(((-1.0f) + floatValue) * ((childAt.getLeft() + (childAt.getWidth() / 2.0f)) - width));
        }
    }
}
