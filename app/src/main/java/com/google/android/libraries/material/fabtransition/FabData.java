package com.google.android.libraries.material.fabtransition;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.core.view.ViewCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
final class FabData {
    public final Rect bounds;
    public final int color;
    public final float elevation;
    public final Bitmap icon = null;
    public final float radius;

    public FabData(FloatingActionButton floatingActionButton) {
        this.color = getFabColor(floatingActionButton);
        this.bounds = getFabBounds(floatingActionButton);
        this.radius = getFabContentSize(floatingActionButton) / 2.0f;
        this.elevation = ViewCompat.getElevation(floatingActionButton);
    }

    private static Rect getFabBounds(FloatingActionButton floatingActionButton) {
        Rect rect = new Rect(0, 0, floatingActionButton.getWidth(), floatingActionButton.getHeight());
        int[] iArr = new int[2];
        floatingActionButton.getLocationInWindow(iArr);
        rect.offsetTo(iArr[0], iArr[1]);
        rect.offset((int) (-floatingActionButton.getTranslationX()), (int) (-floatingActionButton.getTranslationY()));
        return rect;
    }

    static int getFabColor(FloatingActionButton floatingActionButton) {
        ColorStateList backgroundTintList = floatingActionButton.getBackgroundTintList();
        if (backgroundTintList != null) {
            return backgroundTintList.getColorForState(floatingActionButton.getDrawableState(), backgroundTintList.getDefaultColor());
        }
        return 0;
    }

    static int getFabContentSize(FloatingActionButton floatingActionButton) {
        Rect rect = new Rect();
        floatingActionButton.getContentRect(rect);
        return rect.width();
    }
}
