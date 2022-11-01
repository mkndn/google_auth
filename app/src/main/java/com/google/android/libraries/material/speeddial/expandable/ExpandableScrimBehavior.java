package com.google.android.libraries.material.speeddial.expandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialView;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExpandableScrimBehavior extends CoordinatorLayout.Behavior {
    private boolean isExpanded;

    public ExpandableScrimBehavior() {
    }

    private static boolean isPointInChildBounds(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY());
    }

    private void updateChildExpandedState(final View view, ExpandableFloatingActionButton expandableFloatingActionButton) {
        if (this.isExpanded != expandableFloatingActionButton.isExpanded()) {
            boolean isExpanded = expandableFloatingActionButton.isExpanded();
            this.isExpanded = isExpanded;
            if (isExpanded) {
                view.setAlpha(0.0f);
                view.setVisibility(0);
            }
            view.animate().alpha(this.isExpanded ? 1.0f : 0.0f).setDuration(200L).setInterpolator(MaterialInterpolators.fastOutSlowIn()).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.speeddial.expandable.ExpandableScrimBehavior.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (!ExpandableScrimBehavior.this.isExpanded) {
                        view.setVisibility(8);
                    }
                }
            });
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean blocksInteractionBelow(CoordinatorLayout coordinatorLayout, View view) {
        return this.isExpanded;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        return (view2 instanceof ExpandableFloatingActionButton) || (view2 instanceof FloatingSpeedDialView);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view2 instanceof ExpandableFloatingActionButton) {
            updateChildExpandedState(view, (ExpandableFloatingActionButton) view2);
            return false;
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && this.isExpanded) {
            ExpandableFloatingActionButton findExpandableFabDependency = CoordinatorLayoutUtils.findExpandableFabDependency(coordinatorLayout, view);
            FloatingSpeedDialView findSpeedDialViewDependency = CoordinatorLayoutUtils.findSpeedDialViewDependency(coordinatorLayout, view);
            return (findExpandableFabDependency == null || isPointInChildBounds(coordinatorLayout, findExpandableFabDependency, motionEvent) || findSpeedDialViewDependency == null || isPointInChildBounds(coordinatorLayout, findSpeedDialViewDependency, motionEvent)) ? false : true;
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        ExpandableFloatingActionButton findExpandableFabDependency = CoordinatorLayoutUtils.findExpandableFabDependency(coordinatorLayout, view);
        if (findExpandableFabDependency != null) {
            updateChildExpandedState(view, findExpandableFabDependency);
        }
        coordinatorLayout.onLayoutChild(view, i);
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        ExpandableFloatingActionButton findExpandableFabDependency;
        if (motionEvent.getActionMasked() == 1 && this.isExpanded && (findExpandableFabDependency = CoordinatorLayoutUtils.findExpandableFabDependency(coordinatorLayout, view)) != null) {
            findExpandableFabDependency.setExpanded(false);
        }
        return true;
    }

    public ExpandableScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
