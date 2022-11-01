package com.google.android.libraries.material.fabtransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.core.view.ViewCompat;
import com.google.android.libraries.material.animation.ArgbEvaluatorCompat;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.fabtransition.FabTransitionController;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
final class FloatingSpeedDialTransitionHelper {
    private static final int[] EXPANDED_STATE_SET = {16842920};
    private static final int[] COLLAPSED_STATE_SET = {-16842920};

    private static void animateOnLayout(final View view, final Animator animator) {
        if (!ViewCompat.isLaidOut(view)) {
            if (!animator.isStarted() && (animator instanceof AnimatorSet)) {
                animator.start();
            }
            animator.end();
        } else if (view.isLayoutRequested()) {
            final ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.libraries.material.fabtransition.FloatingSpeedDialTransitionHelper.3
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    animator.start();
                    return false;
                }
            };
            view.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.fabtransition.FloatingSpeedDialTransitionHelper.4
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    view.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
                }
            });
        } else {
            animator.start();
        }
    }

    private static FabTransitionController.FabTransition beginAnimation(final FloatingActionButton floatingActionButton, ExpandableWidget expandableWidget, View view, int i, int i2, boolean z) {
        floatingActionButton.setImageState(z ? EXPANDED_STATE_SET : COLLAPSED_STATE_SET, true);
        ArrayList arrayList = new ArrayList();
        if (i != 0 && i2 != 0) {
            final int i3 = z ? i : i2;
            arrayList.add(createTintAnimator(i, i2, z, new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.libraries.material.fabtransition.FloatingSpeedDialTransitionHelper$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FloatingActionButton.this.setBackgroundTintList(ColorStateList.valueOf(((Integer) valueAnimator.getAnimatedValue()).intValue()));
                }
            }, new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.fabtransition.FloatingSpeedDialTransitionHelper.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    FloatingActionButton.this.setBackgroundTintList(ColorStateList.valueOf(i3));
                }
            }));
        }
        if (z) {
            ((View) expandableWidget).setVisibility(0);
        }
        arrayList.add(expandableWidget.createStateChangeAnimator(z));
        if (view != null) {
            if (z) {
                view.setAlpha(0.0f);
                view.setVisibility(0);
            }
            arrayList.add(createScrimAnimator(view, z));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animateOnLayout((View) expandableWidget, animatorSet);
        return new AnimatorFabTransition(animatorSet);
    }

    public static FabTransitionController.FabTransition collapse(FloatingActionButton floatingActionButton, ExpandableWidget expandableWidget, View view, int i, int i2) {
        return beginAnimation(floatingActionButton, expandableWidget, view, i, i2, false);
    }

    private static Animator createScrimAnimator(final View view, final boolean z) {
        Property property = View.ALPHA;
        float[] fArr = new float[2];
        fArr[0] = z ? 0.0f : 1.0f;
        fArr[1] = z ? 1.0f : 0.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
        ofFloat.setDuration(200L);
        ofFloat.setInterpolator(MaterialInterpolators.fastOutSlowIn());
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.fabtransition.FloatingSpeedDialTransitionHelper.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    view.setVisibility(8);
                }
            }
        });
        return ofFloat;
    }

    private static Animator createTintAnimator(int i, int i2, boolean z, ValueAnimator.AnimatorUpdateListener animatorUpdateListener, Animator.AnimatorListener animatorListener) {
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

    public static FabTransitionController.FabTransition expandToFloatingSpeedDial(FloatingActionButton floatingActionButton, ExpandableWidget expandableWidget, View view, int i, int i2) {
        return beginAnimation(floatingActionButton, expandableWidget, view, i, i2, true);
    }
}
