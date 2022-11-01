package com.google.android.libraries.material.speeddial;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.material.animation.AnimatorSetCompat;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AnimationUtils {
    private static final Interpolator EXPAND_INTERPOLATOR = MaterialInterpolators.linearOutSlowIn();
    private static final Interpolator COLLAPSE_INTERPOLATOR = MaterialInterpolators.fastOutLinearIn();

    private static Animator createFadeFabAnimator(View view, int i, boolean z) {
        float f = z ? 0.0f : 1.0f;
        float f2 = z ? 1.0f : 0.0f;
        view.setAlpha(f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, f, f2);
        ofFloat.setInterpolator(z ? EXPAND_INTERPOLATOR : COLLAPSE_INTERPOLATOR);
        ofFloat.setDuration(z ? 200L : 150L);
        ofFloat.setStartDelay(i);
        return ofFloat;
    }

    private static Animator createFadeTitleAnimator(View view, int i, boolean z) {
        float f = z ? 0.0f : 1.0f;
        float f2 = z ? 1.0f : 0.0f;
        view.setAlpha(f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, f, f2);
        ofFloat.setInterpolator(z ? EXPAND_INTERPOLATOR : COLLAPSE_INTERPOLATOR);
        ofFloat.setDuration(150L);
        ofFloat.setStartDelay(i + (z ? 50 : 0));
        return ofFloat;
    }

    private static Animator createScaleFabAnimator(View view, int i, boolean z) {
        float f = z ? 0.1f : 1.0f;
        float f2 = z ? 1.0f : 0.1f;
        view.setScaleX(f);
        view.setScaleY(f);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.SCALE_X, f, f2), PropertyValuesHolder.ofFloat(View.SCALE_Y, f, f2));
        ofPropertyValuesHolder.setInterpolator(z ? EXPAND_INTERPOLATOR : COLLAPSE_INTERPOLATOR);
        ofPropertyValuesHolder.setDuration(z ? 200L : 150L);
        ofPropertyValuesHolder.setStartDelay(i);
        return ofPropertyValuesHolder;
    }

    public static Animator createSpeedDialAnimator(FloatingSpeedDialView floatingSpeedDialView, boolean z) {
        ArrayList arrayList = new ArrayList();
        RecyclerView recyclerView = (RecyclerView) floatingSpeedDialView.getChildAt(0);
        int i = 0;
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View childAt = recyclerView.getChildAt(i2);
            arrayList.add(createFadeTitleAnimator(childAt.findViewById(R$id.mtrl_internal_speed_dial_item_label), i, z));
            View findViewById = childAt.findViewById(R$id.mtrl_internal_speed_dial_item_fab);
            arrayList.add(createFadeFabAnimator(findViewById, i, z));
            if (z) {
                arrayList.add(createTranslateItemAnimator(childAt, i, true));
                arrayList.add(createScaleFabAnimator(findViewById, i, true));
            }
            i += z ? 20 : 0;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    private static Animator createTranslateItemAnimator(View view, int i, boolean z) {
        float dimension = view.getResources().getDimension(R$dimen.mtrl_internal_floating_speed_dial_item_vertical_translation_distance);
        float f = z ? dimension : 0.0f;
        if (z) {
            dimension = 0.0f;
        }
        view.setTranslationY(f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, f, dimension);
        ofFloat.setInterpolator(MaterialInterpolators.fastOutSlowIn());
        ofFloat.setDuration(z ? 200L : 150L);
        ofFloat.setStartDelay(i);
        return ofFloat;
    }
}
