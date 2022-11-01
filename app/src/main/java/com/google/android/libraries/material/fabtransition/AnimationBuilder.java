package com.google.android.libraries.material.fabtransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.circularreveal.CircularRevealWidget;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AnimationBuilder {
    protected final CircularRevealWidget circularRevealTarget;
    protected final float fabElevation;
    protected final Bitmap fabIcon;
    protected final float fabRadius;
    protected final int fabTint;
    protected final Rect fabWindowBounds;
    protected final Positioning positioning;
    protected final View scrim;
    protected final View target;
    protected final Rect targetWindowBounds;
    protected final long transitionDuration;
    private final List animators = new ArrayList();
    private final List listeners = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Positioning {
        public final int gravity;
        public final float xAdjustment;
        public final float yAdjustment;

        private Positioning(int i, float f, float f2) {
            this.gravity = i;
            this.xAdjustment = f;
            this.yAdjustment = f2;
        }

        public static Positioning ofFloatingSheet() {
            return new Positioning(17, 0.0f, 0.0f);
        }

        public static Positioning ofToolbar() {
            return new Positioning(17, 0.0f, 0.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnimationBuilder(FabData fabData, CircularRevealWidget circularRevealWidget, View view, Positioning positioning, long j) {
        this.circularRevealTarget = circularRevealWidget;
        View view2 = (View) circularRevealWidget;
        this.target = view2;
        this.scrim = view;
        this.positioning = positioning;
        this.transitionDuration = j;
        this.fabWindowBounds = fabData.bounds;
        this.fabRadius = fabData.radius;
        this.fabTint = fabData.color;
        this.fabElevation = fabData.elevation;
        this.fabIcon = fabData.icon;
        Rect rect = new Rect(0, 0, view2.getWidth(), view2.getHeight());
        this.targetWindowBounds = rect;
        int[] iArr = new int[2];
        view2.getLocationInWindow(iArr);
        rect.offsetTo(iArr[0], iArr[1]);
        rect.offset((int) (-view2.getTranslationX()), (int) (-view2.getTranslationY()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addAnimator(Animator animator) {
        animator.setStartDelay(normalize(animator.getStartDelay()));
        animator.setDuration(normalize(animator.getDuration()));
        this.animators.add(animator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addListener(Animator.AnimatorListener animatorListener) {
        this.listeners.add(animatorListener);
    }

    public final Animator build() {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, this.animators);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.fabtransition.AnimationBuilder.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimationBuilder.this.onAnimationsEnd();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimationBuilder.this.onAnimationsStart();
            }
        });
        for (Animator.AnimatorListener animatorListener : this.listeners) {
            animatorSet.addListener(animatorListener);
        }
        this.animators.clear();
        this.listeners.clear();
        return animatorSet;
    }

    protected final long normalize(long j) {
        return (float) j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAnimationsEnd() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAnimationsStart() {
        this.target.setVisibility(0);
        View view = this.scrim;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void postFillRadialExpansion(long j, long j2, long j3, int i, int i2, float f) {
        long j4 = j2 + j;
        if (j4 < j3) {
            addAnimator(ViewAnimationUtils.createCircularReveal(this.target, i, i2, f, f), j4, j3 - j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void preFillRadialExpansion(long j, int i, int i2, float f) {
        if (j > 0) {
            addAnimator(ViewAnimationUtils.createCircularReveal(this.target, i, i2, f, f), 0L, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float calculateTranslationX(Positioning positioning) {
        float exactCenterX;
        if ((positioning.gravity & 3) == 3) {
            exactCenterX = this.fabWindowBounds.left - this.targetWindowBounds.left;
        } else if ((positioning.gravity & 1) == 1) {
            exactCenterX = this.fabWindowBounds.exactCenterX() - this.targetWindowBounds.exactCenterX();
        } else {
            exactCenterX = (positioning.gravity & 5) == 5 ? this.fabWindowBounds.right - this.targetWindowBounds.right : 0.0f;
        }
        return exactCenterX + positioning.xAdjustment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float calculateTranslationY(Positioning positioning) {
        float exactCenterY;
        if ((positioning.gravity & 48) == 48) {
            exactCenterY = this.fabWindowBounds.top - this.targetWindowBounds.top;
        } else if ((positioning.gravity & 16) == 16) {
            exactCenterY = this.fabWindowBounds.exactCenterY() - this.targetWindowBounds.exactCenterY();
        } else {
            exactCenterY = (positioning.gravity & 80) == 80 ? this.fabWindowBounds.bottom - this.targetWindowBounds.bottom : 0.0f;
        }
        return exactCenterY + positioning.yAdjustment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addAnimator(Animator animator, long j, long j2) {
        animator.setStartDelay(normalize(j));
        animator.setDuration(normalize(j2));
        animator.setInterpolator(MaterialInterpolators.fastOutSlowIn());
        this.animators.add(animator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addAnimator(Animator animator, long j, long j2, TimeInterpolator timeInterpolator) {
        animator.setStartDelay(normalize(j));
        animator.setDuration(normalize(j2));
        animator.setInterpolator(timeInterpolator);
        this.animators.add(animator);
    }
}
