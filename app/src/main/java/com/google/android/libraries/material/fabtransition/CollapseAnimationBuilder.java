package com.google.android.libraries.material.fabtransition;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.PointF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.material.animation.ArgbEvaluatorCompat;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.fabtransition.AnimationBuilder;
import com.google.android.libraries.material.motion.CurvedMotionTiming;
import com.google.android.libraries.material.motion.MaterialMotion;
import com.google.android.libraries.stitch.util.Preconditions;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CollapseAnimationBuilder extends AnimationBuilder {
    public CollapseAnimationBuilder(FabData fabData, CircularRevealWidget circularRevealWidget, View view, AnimationBuilder.Positioning positioning, long j) {
        super(fabData, circularRevealWidget, view, positioning, j);
    }

    public void animateColor(long j, long j2) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this.circularRevealTarget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, this.fabTint);
        ofInt.setEvaluator(ArgbEvaluatorCompat.getInstance());
        addAnimator(ofInt, j, j2);
    }

    public void animateContentFade(long j, long j2) {
        if (CircularRevealHelper.STRATEGY != 0) {
            addAnimator(ObjectAnimator.ofFloat((ViewGroup) this.target, ContentFadeProperty.CONTENT_FADE, 0.0f), j, j2);
        }
    }

    public void animateContentTranslation(long j, long j2) {
        if (CircularRevealHelper.STRATEGY != 0) {
            addAnimator(ObjectAnimator.ofFloat((ViewGroup) this.target, ContentSpreadProperty.CONTENT_SPREAD, 0.0f), j, j2);
        }
    }

    public void animateElevation(long j, long j2) {
        if (Build.VERSION.SDK_INT >= 21) {
            addAnimator(ObjectAnimator.ofFloat(this.target, View.TRANSLATION_Z, -(this.target.getElevation() - this.fabElevation)), j, j2);
        }
    }

    public void animateIconFade(long j, long j2) {
    }

    public void animateRadialExpansion(long j, long j2, TimeInterpolator timeInterpolator) {
        float calculateTranslationX = calculateTranslationX(this.positioning);
        float calculateTranslationY = calculateTranslationY(this.positioning);
        float centerX = this.fabWindowBounds.centerX() - (this.targetWindowBounds.left + calculateTranslationX);
        float centerY = this.fabWindowBounds.centerY() - (this.targetWindowBounds.top + calculateTranslationY);
        float f = ((CircularRevealWidget.RevealInfo) Preconditions.checkNotNull(this.circularRevealTarget.getRevealInfo())).radius;
        float f2 = this.fabRadius;
        addAnimator(CircularRevealCompat.createCircularReveal(this.circularRevealTarget, centerX, centerY, f2), j, j2, MaterialInterpolators.linearOutSlowIn());
        addListener(CircularRevealCompat.createCircularRevealListener(this.circularRevealTarget));
        if (Build.VERSION.SDK_INT >= 21) {
            int i = (int) centerX;
            int i2 = (int) centerY;
            preFillRadialExpansion(j, i, i2, f);
            postFillRadialExpansion(j, j2, this.transitionDuration, i, i2, f2);
        }
    }

    public void animateScrimFade(long j, long j2) {
        if (this.scrim != null) {
            addAnimator(ObjectAnimator.ofFloat(this.scrim, View.ALPHA, 0.0f), j, j2);
        }
    }

    public void animateTranslation(CurvedMotionTiming curvedMotionTiming) {
        addAnimator(MaterialMotion.createCurvedMotionAnimator(this.target, curvedMotionTiming, new PointF(calculateTranslationX(this.positioning), calculateTranslationY(this.positioning))));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.material.fabtransition.AnimationBuilder
    public void onAnimationsEnd() {
        super.onAnimationsEnd();
        this.target.setVisibility(4);
        if (this.scrim != null) {
            this.scrim.setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.libraries.material.fabtransition.AnimationBuilder
    public void onAnimationsStart() {
        super.onAnimationsStart();
        if (this.scrim != null) {
            this.scrim.setClickable(false);
        }
    }
}
