package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import com.google.android.libraries.material.animation.ArgbEvaluatorCompat;
import com.google.android.libraries.material.animation.MaterialInterpolators;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ExpandableUtils {
    public static final int EXPANDABLE_DURATION = 200;
    public static final int[] EXPANDED_STATE_SET = {16842920};

    public static ColorStateList createCollapsedTintList(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return null;
        }
        return ColorStateList.valueOf(getCollapsedTint(colorStateList));
    }

    public static ColorStateList createExpandedTintList(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return null;
        }
        return ColorStateList.valueOf(getExpandedTint(colorStateList));
    }

    public static ValueAnimator createTintAnimator(int i, int i2, boolean z, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener animatorListener) {
        int i3 = z ? i2 : i;
        if (!z) {
            i = i2;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(i3, i);
        ofInt.setEvaluator(ArgbEvaluatorCompat.getInstance());
        ofInt.addUpdateListener(animatorUpdateListener);
        ofInt.addListener(animatorListener);
        ofInt.setDuration(200L);
        ofInt.setInterpolator(MaterialInterpolators.fastOutSlowIn());
        return ofInt;
    }

    public static ColorStateList createTintList(ColorStateList colorStateList, boolean z) {
        if (colorStateList == null) {
            return null;
        }
        return z ? createExpandedTintList(colorStateList) : createCollapsedTintList(colorStateList);
    }

    public static int getCollapsedTint(ColorStateList colorStateList) {
        return colorStateList.getDefaultColor();
    }

    public static int getExpandedTint(ColorStateList colorStateList) {
        return colorStateList.getColorForState(EXPANDED_STATE_SET, colorStateList.getDefaultColor());
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        switch (i) {
            case 3:
                return PorterDuff.Mode.SRC_OVER;
            case 5:
                return PorterDuff.Mode.SRC_IN;
            case 9:
                return PorterDuff.Mode.SRC_ATOP;
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
