package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.StateSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ExpandableDrawable extends Drawable {
    private static final String TAG = ExpandableDrawable.class.getSimpleName();
    private Animator currentAnimator;
    private boolean isExpanded;
    private ColorStateList tintList;
    private PorterDuff.Mode tintMode;

    public static ExpandableDrawable createWithCrossFade(Drawable drawable, Drawable drawable2) {
        return new ExpandableCrossFadeDrawable(drawable, drawable2);
    }

    public static ExpandableDrawable createWithRotation(Drawable drawable, int i) {
        return new ExpandableRotationDrawable(drawable, i);
    }

    private void expandedStateChanged(boolean z) {
        this.isExpanded = z;
        Animator animator = this.currentAnimator;
        if (animator != null && animator.isRunning()) {
            this.currentAnimator.cancel();
        }
        Animator createStateChangeAnimator = createStateChangeAnimator(z);
        this.currentAnimator = createStateChangeAnimator;
        createStateChangeAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableDrawable.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                if (ExpandableDrawable.this.currentAnimator == animator2) {
                    ExpandableDrawable.this.currentAnimator = null;
                }
            }
        });
        this.currentAnimator.start();
    }

    protected abstract Animator createStateChangeAnimator(boolean z);

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        Animator animator = this.currentAnimator;
        if (animator != null) {
            animator.end();
            this.currentAnimator = null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected final boolean onStateChange(int[] iArr) {
        boolean stateSetMatches = StateSet.stateSetMatches(ExpandableUtils.EXPANDED_STATE_SET, iArr);
        boolean z = this.isExpanded != stateSetMatches;
        if (z) {
            expandedStateChanged(stateSetMatches);
        }
        return super.onStateChange(iArr) || z;
    }

    protected abstract void onTintListChanged(ColorStateList colorStateList);

    protected abstract void onTintModeChanged(PorterDuff.Mode mode);

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Log.w(TAG, "setColorFilter() is not supported, use setTintList() and setTintMode() instead");
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.tintList != colorStateList) {
            this.tintList = colorStateList;
            onTintListChanged(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.tintMode != mode) {
            this.tintMode = mode;
            onTintModeChanged(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (visible) {
            jumpToCurrentState();
        }
        return visible;
    }
}
