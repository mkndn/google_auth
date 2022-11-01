package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.math.MathUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpandableCrossFadeDrawable extends ExpandableDrawable {
    private final Drawable collapsedDrawable;
    private float currentAnimationProgress;
    private float drawableAlpha = 1.0f;
    private final Drawable expandedDrawable;

    public ExpandableCrossFadeDrawable(Drawable drawable, Drawable drawable2) {
        this.expandedDrawable = DrawableCompat.wrap(drawable).mutate();
        this.collapsedDrawable = DrawableCompat.wrap(drawable2).mutate();
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    protected Animator createStateChangeAnimator(boolean z) {
        float[] fArr = new float[2];
        fArr[0] = z ? 0.0f : 1.0f;
        fArr[1] = z ? 1.0f : 0.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableCrossFadeDrawable$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableCrossFadeDrawable.this.m269xda89baf8(valueAnimator);
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
        int round = Math.round(MathUtils.lerp(0.0f, 255.0f, this.currentAnimationProgress) * this.drawableAlpha);
        int round2 = Math.round(MathUtils.lerp(255.0f, 0.0f, this.currentAnimationProgress) * this.drawableAlpha);
        this.expandedDrawable.setAlpha(round);
        this.collapsedDrawable.setAlpha(round2);
        float exactCenterX = getBounds().exactCenterX();
        float exactCenterY = getBounds().exactCenterY();
        canvas.save();
        canvas.rotate(MathUtils.lerp(180.0f, 360.0f, this.currentAnimationProgress), exactCenterX, exactCenterY);
        this.expandedDrawable.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.rotate(MathUtils.lerp(0.0f, 180.0f, this.currentAnimationProgress), exactCenterX, exactCenterY);
        this.collapsedDrawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createStateChangeAnimator$0$com-google-android-libraries-material-speeddial-expandable-ExpandableCrossFadeDrawable  reason: not valid java name */
    public /* synthetic */ void m269xda89baf8(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (this.currentAnimationProgress != floatValue) {
            this.currentAnimationProgress = floatValue;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.expandedDrawable.setBounds(rect);
        this.collapsedDrawable.setBounds(rect);
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    public void onTintListChanged(ColorStateList colorStateList) {
        DrawableCompat.setTintList(this.expandedDrawable, ExpandableUtils.createExpandedTintList(colorStateList));
        DrawableCompat.setTintList(this.collapsedDrawable, ExpandableUtils.createCollapsedTintList(colorStateList));
        invalidateSelf();
    }

    @Override // com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable
    public void onTintModeChanged(PorterDuff.Mode mode) {
        DrawableCompat.setTintMode(this.expandedDrawable, mode);
        DrawableCompat.setTintMode(this.collapsedDrawable, mode);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        float f = i / 255.0f;
        if (this.drawableAlpha != f) {
            this.drawableAlpha = f;
            invalidateSelf();
        }
    }
}
