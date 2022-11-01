package com.google.android.libraries.material.fabtransition;

import android.util.Property;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
class ContentFadeProperty extends Property {
    public static final Property CONTENT_FADE = new ContentFadeProperty("contentFade");

    private ContentFadeProperty(String str) {
        super(Float.class, str);
    }

    @Override // android.util.Property
    public Float get(ViewGroup viewGroup) {
        Float f = (Float) viewGroup.getTag(R$id.mtrl_content_fade);
        if (f != null) {
            return f;
        }
        return Float.valueOf(1.0f);
    }

    @Override // android.util.Property
    public void set(ViewGroup viewGroup, Float f) {
        float floatValue = f.floatValue();
        viewGroup.setTag(R$id.mtrl_content_fade, Float.valueOf(floatValue));
        if (viewGroup instanceof ContentContainer) {
            viewGroup = ((ContentContainer) viewGroup).getContainer();
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup.getChildAt(i).setAlpha(floatValue);
        }
    }
}
