package com.google.android.material.button;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    private static final String LOG_TAG = MaterialButtonToggleGroup.class.getSimpleName();
    private static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_MaterialButtonToggleGroup;

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        throw null;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onButtonCheckedStateChanged(MaterialButton materialButton, boolean z) {
        throw null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        throw null;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        throw null;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        throw null;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        throw null;
    }
}
