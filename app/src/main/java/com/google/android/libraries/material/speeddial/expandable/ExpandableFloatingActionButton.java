package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpandableFloatingActionButton extends FloatingActionButton {
    private static final String TAG = ExpandableFloatingActionButton.class.getSimpleName();
    private ColorStateList backgroundTintList;
    private ExpandableDrawable expandableDrawable;
    private ColorStateList imageTintListCompat;
    private PorterDuff.Mode imageTintModeCompat;
    private boolean isExpanded;
    private OnExpandedStateChangeListener stateChangeListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnExpandedStateChangeListener {
        void onExpandedStateChanged(ExpandableFloatingActionButton expandableFloatingActionButton, boolean z);
    }

    public ExpandableFloatingActionButton(Context context) {
        this(context, null);
    }

    private void animateOnLayout(final Animator animator) {
        if (!ViewCompat.isLaidOut(this)) {
            if (!animator.isStarted() && (animator instanceof AnimatorSet)) {
                animator.start();
            }
            animator.end();
        } else if (isLayoutRequested()) {
            final ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    ExpandableFloatingActionButton.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    animator.start();
                    return false;
                }
            };
            getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator2) {
                    ExpandableFloatingActionButton.this.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
                }
            });
        } else {
            animator.start();
        }
    }

    private void applyImageTintCompat() {
        ExpandableDrawable expandableDrawable;
        if (Build.VERSION.SDK_INT < 21 && (expandableDrawable = this.expandableDrawable) != null) {
            expandableDrawable.setTintList(this.imageTintListCompat);
            this.expandableDrawable.setTintMode(this.imageTintModeCompat);
        }
    }

    private Animator createBackgroundTintAnimator(ColorStateList colorStateList, final boolean z) {
        final int expandedTint = ExpandableUtils.getExpandedTint(colorStateList);
        final int collapsedTint = ExpandableUtils.getCollapsedTint(colorStateList);
        return ExpandableUtils.createTintAnimator(expandedTint, collapsedTint, z, new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableFloatingActionButton.this.m273x48b317ca(valueAnimator);
            }
        }, new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                ExpandableFloatingActionButton.this.setBackgroundTintListInternal(ColorStateList.valueOf(z ? expandedTint : collapsedTint));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackgroundTintListInternal(ColorStateList colorStateList) {
        super.setBackgroundTintList(ExpandableUtils.createTintList(colorStateList, this.isExpanded));
    }

    private void setExpandedInternal(boolean z) {
        ColorStateList colorStateList = this.backgroundTintList;
        if (colorStateList != null) {
            animateOnLayout(createBackgroundTintAnimator(colorStateList, z));
        }
        if (getParent() instanceof CoordinatorLayout) {
            ((CoordinatorLayout) getParent()).dispatchDependentViewsChanged(this);
        }
        refreshDrawableState();
    }

    public void collapse() {
        setExpanded(false);
    }

    public void expand() {
        setExpanded(true);
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public ColorStateList getBackgroundTintList() {
        return this.backgroundTintList;
    }

    @Override // android.widget.ImageView
    public ColorStateList getImageTintList() {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.getImageTintList();
        }
        return this.imageTintListCompat;
    }

    @Override // android.widget.ImageView
    public PorterDuff.Mode getImageTintMode() {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.getImageTintMode();
        }
        return this.imageTintModeCompat;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.isExpanded) {
            mergeDrawableStates(onCreateDrawableState, ExpandableUtils.EXPANDED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setExpanded(savedState.isExpanded, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.isExpanded = this.isExpanded;
        return savedState;
    }

    @Override // com.google.android.material.floatingactionbutton.FloatingActionButton, android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList) {
        this.backgroundTintList = colorStateList;
        setBackgroundTintListInternal(colorStateList);
    }

    public void setExpandableDrawable(ExpandableDrawable expandableDrawable) {
        if (this.expandableDrawable != expandableDrawable) {
            this.expandableDrawable = expandableDrawable;
            setImageDrawable(expandableDrawable);
            applyImageTintCompat();
        }
    }

    public boolean setExpanded(boolean z) {
        return setExpanded(z, true);
    }

    @Override // android.widget.ImageView
    public void setImageTintList(ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setImageTintList(colorStateList);
        } else if (this.imageTintListCompat != colorStateList) {
            this.imageTintListCompat = colorStateList;
            applyImageTintCompat();
        }
    }

    @Override // android.widget.ImageView
    public void setImageTintMode(PorterDuff.Mode mode) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setImageTintMode(mode);
        } else if (this.imageTintModeCompat != mode) {
            this.imageTintModeCompat = mode;
            applyImageTintCompat();
        }
    }

    private boolean setExpanded(boolean z, boolean z2) {
        if (this.isExpanded != z) {
            this.isExpanded = z;
            setExpandedInternal(z);
            OnExpandedStateChangeListener onExpandedStateChangeListener = this.stateChangeListener;
            if (onExpandedStateChangeListener != null && z2) {
                onExpandedStateChangeListener.onExpandedStateChanged(this, z);
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createBackgroundTintAnimator$0$com-google-android-libraries-material-speeddial-expandable-ExpandableFloatingActionButton  reason: not valid java name */
    public /* synthetic */ void m273x48b317ca(ValueAnimator valueAnimator) {
        setBackgroundTintListInternal(ColorStateList.valueOf(((Integer) valueAnimator.getAnimatedValue()).intValue()));
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Parcelable.ClassLoaderCreator() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableFloatingActionButton.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }
        };
        private boolean isExpanded;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.isExpanded = parcel.readByte() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.isExpanded ? (byte) 1 : (byte) 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ExpandableFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExpandableFloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z = true;
        setUseCompatPadding(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExpandableFloatingActionButton, i, 0);
        boolean hasValue = obtainStyledAttributes.hasValue(R$styleable.ExpandableFloatingActionButton_rotationDegrees);
        boolean hasValue2 = obtainStyledAttributes.hasValue(R$styleable.ExpandableFloatingActionButton_expandedDrawable);
        boolean hasValue3 = obtainStyledAttributes.hasValue(R$styleable.ExpandableFloatingActionButton_collapsedDrawable);
        z = (hasValue2 && hasValue3) ? false : false;
        if (hasValue && z) {
            Log.w(TAG, "app:rotationDegrees can't be specified w/ app:expandedDrawable & app:collapsedDrawable");
        }
        if (hasValue2 != hasValue3) {
            Log.w(TAG, "app:" + (hasValue2 ? "collapsedDrawable" : "expandedDrawable") + " must also be specified");
        }
        if (hasValue && getDrawable() == null) {
            Log.w(TAG, "A source image for this FAB must also be specified");
        }
        if (hasValue) {
            setExpandableDrawable(ExpandableDrawable.createWithRotation(getDrawable(), obtainStyledAttributes.getInteger(R$styleable.ExpandableFloatingActionButton_rotationDegrees, 0)));
        } else if (z) {
            setExpandableDrawable(ExpandableDrawable.createWithCrossFade(obtainStyledAttributes.getDrawable(R$styleable.ExpandableFloatingActionButton_expandedDrawable), obtainStyledAttributes.getDrawable(R$styleable.ExpandableFloatingActionButton_collapsedDrawable)));
        }
        setImageTintList(obtainStyledAttributes.getColorStateList(R$styleable.ExpandableFloatingActionButton_drawableTint));
        setImageTintMode(ExpandableUtils.parseTintMode(obtainStyledAttributes.getInt(R$styleable.ExpandableFloatingActionButton_drawableTintMode, -1), PorterDuff.Mode.SRC_IN));
        obtainStyledAttributes.recycle();
        ColorStateList backgroundTintList = super.getBackgroundTintList();
        this.backgroundTintList = backgroundTintList;
        if (backgroundTintList != null) {
            setBackgroundTintListInternal(ExpandableUtils.createCollapsedTintList(backgroundTintList));
        }
        refreshDrawableState();
    }
}
