package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.StateSet;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.libraries.material.animation.ArgbEvaluatorCompat;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.math.MathUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpandableRotationDrawable extends ExpandableDrawable {
    private static final ArgbEvaluatorCompat ARGB_EVALUATOR = ArgbEvaluatorCompat.getInstance();
    private ColorStateList clientSpecifiedTintList;
    private float currentAnimatedRotation;
    private ColorStateList currentAnimatedTintList;
    private final Drawable tintableDrawable;
    private final float totalRotationDegrees;

    public ExpandableRotationDrawable(Drawable drawable, float f) {
        this.tintableDrawable = DrawableCompat.wrap(drawable).mutate();
        this.totalRotationDegrees = f;
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    protected Animator createStateChangeAnimator(final boolean z) {
        float[] fArr = new float[2];
        fArr[0] = z ? 0.0f : 1.0f;
        fArr[1] = z ? 1.0f : 0.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        final ColorStateList colorStateList = this.clientSpecifiedTintList;
        final Integer valueOf = colorStateList == null ? null : Integer.valueOf(ExpandableUtils.getCollapsedTint(colorStateList));
        final Integer valueOf2 = colorStateList != null ? Integer.valueOf(ExpandableUtils.getExpandedTint(colorStateList)) : null;
        final ColorStateList createCollapsedTintList = ExpandableUtils.createCollapsedTintList(colorStateList);
        final ColorStateList createExpandedTintList = ExpandableUtils.createExpandedTintList(colorStateList);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableRotationDrawable$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableRotationDrawable.this.m277x9a1b831e(colorStateList, valueOf, valueOf2, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableRotationDrawable.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ExpandableRotationDrawable.this.currentAnimatedTintList = z ? createExpandedTintList : createCollapsedTintList;
                ExpandableRotationDrawable.this.invalidateSelf();
            }
        });
        ofFloat.setInterpolator(MaterialInterpolators.linearOutSlowIn());
        ofFloat.setDuration(200L);
        return ofFloat;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!isVisible()) {
            return;
        }
        DrawableCompat.setTintList(this.tintableDrawable, this.currentAnimatedTintList);
        float exactCenterX = getBounds().exactCenterX();
        float exactCenterY = getBounds().exactCenterY();
        canvas.save();
        canvas.rotate(this.currentAnimatedRotation, exactCenterX, exactCenterY);
        this.tintableDrawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createStateChangeAnimator$0$com-google-android-libraries-material-speeddial-expandable-ExpandableRotationDrawable  reason: not valid java name */
    public /* synthetic */ void m277x9a1b831e(ColorStateList colorStateList, Integer num, Integer num2, ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.currentAnimatedRotation = MathUtils.lerp(0.0f, this.totalRotationDegrees, floatValue);
        if (colorStateList == null) {
            this.currentAnimatedTintList = null;
        } else {
            this.currentAnimatedTintList = ColorStateList.valueOf(ARGB_EVALUATOR.evaluate(floatValue, num, num2).intValue());
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.tintableDrawable.setBounds(rect);
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    public void onTintListChanged(ColorStateList colorStateList) {
        this.clientSpecifiedTintList = colorStateList;
        this.currentAnimatedTintList = ExpandableUtils.createTintList(colorStateList, StateSet.stateSetMatches(ExpandableUtils.EXPANDED_STATE_SET, getState()));
        invalidateSelf();
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    public void onTintModeChanged(PorterDuff.Mode mode) {
        DrawableCompat.setTintMode(this.tintableDrawable, mode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.tintableDrawable.setAlpha(i);
        invalidateSelf();
    }
}
