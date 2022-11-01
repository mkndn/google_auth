package com.google.android.libraries.material.speeddial;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.libraries.material.fabtransition.ExpandableWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FloatingSpeedDialView extends FrameLayout implements ExpandableWidget, CoordinatorLayout.AttachedBehavior {
    private Animator currentAnimator;
    private boolean isExpanded;
    private final int miniFabWidthWithShadowPadding;
    private final RecyclerView recyclerView;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends CoordinatorLayout.Behavior {
        public Behavior() {
        }

        private static FloatingActionButton findAnchoredFab(CoordinatorLayout coordinatorLayout, View view) {
            int anchorId = ((CoordinatorLayout.LayoutParams) view.getLayoutParams()).getAnchorId();
            if (anchorId == -1) {
                return null;
            }
            List dependencies = coordinatorLayout.getDependencies(view);
            int size = dependencies.size();
            for (int i = 0; i < size; i++) {
                View view2 = (View) dependencies.get(i);
                if (view2.getId() == anchorId && (view2 instanceof FloatingActionButton)) {
                    return (FloatingActionButton) view2;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingSpeedDialView floatingSpeedDialView, int i) {
            coordinatorLayout.onLayoutChild(floatingSpeedDialView, i);
            FloatingActionButton findAnchoredFab = findAnchoredFab(coordinatorLayout, floatingSpeedDialView);
            if (findAnchoredFab != null) {
                int absoluteGravity = GravityCompat.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) floatingSpeedDialView.getLayoutParams()).anchorGravity, i) & 7;
                int width = (findAnchoredFab.getWidth() - floatingSpeedDialView.miniFabWidthWithShadowPadding) / 2;
                if (absoluteGravity == 5) {
                    floatingSpeedDialView.setTranslationX(-width);
                    return true;
                } else if (absoluteGravity != 3) {
                    return true;
                } else {
                    floatingSpeedDialView.setTranslationX(width);
                    return true;
                }
            }
            return true;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public FloatingSpeedDialView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateOnLayout(final Animator animator) {
        if (!ViewCompat.isLaidOut(this)) {
            if (!animator.isStarted() && (animator instanceof AnimatorSet)) {
                animator.start();
            }
            animator.end();
            return;
        }
        Animator animator2 = this.currentAnimator;
        if (animator2 != null && animator2.isStarted()) {
            this.currentAnimator.cancel();
        }
        this.currentAnimator = animator;
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator3) {
                FloatingSpeedDialView.this.currentAnimator = null;
            }
        });
        if (isLayoutRequested()) {
            final ViewTreeObserver.OnPreDrawListener onPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialView.4
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    FloatingSpeedDialView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    animator.start();
                    return false;
                }
            };
            getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialView.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator3) {
                    FloatingSpeedDialView.this.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
                }
            });
            return;
        }
        animator.start();
    }

    @Override // com.google.android.libraries.material.fabtransition.ExpandableWidget
    public Animator createStateChangeAnimator(final boolean z) {
        Animator createSpeedDialAnimator = AnimationUtils.createSpeedDialAnimator(this, z);
        createSpeedDialAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    FloatingSpeedDialView.this.setVisibility(8);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (z) {
                    FloatingSpeedDialView.this.setVisibility(0);
                }
            }
        });
        return createSpeedDialAnimator;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior getBehavior() {
        return new Behavior();
    }

    public void setAdapter(FloatingSpeedDialAdapter floatingSpeedDialAdapter) {
        this.recyclerView.setAdapter(floatingSpeedDialAdapter);
    }

    public void setExpanded(final boolean z) {
        if (this.isExpanded != z) {
            this.isExpanded = z;
            if (z) {
                setVisibility(0);
            } else {
                invalidate();
            }
            final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.libraries.material.speeddial.FloatingSpeedDialView.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    viewTreeObserver.removeOnPreDrawListener(this);
                    FloatingSpeedDialView floatingSpeedDialView = FloatingSpeedDialView.this;
                    floatingSpeedDialView.animateOnLayout(floatingSpeedDialView.createStateChangeAnimator(z));
                    return false;
                }
            });
        }
    }

    public FloatingSpeedDialView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.floatingSpeedDialStyle);
    }

    public FloatingSpeedDialView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setVisibility(8);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.FloatingSpeedDialView, i, R$style.InternalSpeedDialViewStyle);
        if (Build.VERSION.SDK_INT >= 21) {
            setElevation(obtainStyledAttributes.getDimension(R$styleable.FloatingSpeedDialView_android_elevation, 0.0f));
        }
        obtainStyledAttributes.recycle();
        Resources resources = getResources();
        float dimension = resources.getDimension(R$dimen.mtrl_internal_floating_speed_dial_mini_fab_elevation) + resources.getDimension(R$dimen.mtrl_internal_floating_speed_dial_mini_fab_pressed_translation_z);
        this.miniFabWidthWithShadowPadding = (int) (dimension + dimension + getResources().getDimension(R$dimen.design_fab_size_mini));
        RecyclerView recyclerView = new RecyclerView(context);
        this.recyclerView = recyclerView;
        recyclerView.setClipChildren(false);
        recyclerView.setOverScrollMode(2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        addView(recyclerView, new FrameLayout.LayoutParams(-1, -1));
    }
}
