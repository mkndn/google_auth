package com.google.android.libraries.material.speeddial.expandable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialView;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExpandableFloatingSpeedDialBehavior extends FloatingSpeedDialView.Behavior {
    private boolean isExpanded;

    public ExpandableFloatingSpeedDialBehavior() {
    }

    private void updateChildExpandedState(FloatingSpeedDialView floatingSpeedDialView, ExpandableFloatingActionButton expandableFloatingActionButton) {
        if (this.isExpanded != expandableFloatingActionButton.isExpanded()) {
            boolean isExpanded = expandableFloatingActionButton.isExpanded();
            this.isExpanded = isExpanded;
            floatingSpeedDialView.setExpanded(isExpanded);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, FloatingSpeedDialView floatingSpeedDialView, View view) {
        return view instanceof ExpandableFloatingActionButton;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, FloatingSpeedDialView floatingSpeedDialView, View view) {
        if (view instanceof ExpandableFloatingActionButton) {
            updateChildExpandedState(floatingSpeedDialView, (ExpandableFloatingActionButton) view);
            return false;
        }
        return false;
    }

    @Override // com.google.android.libraries.material.speeddial.FloatingSpeedDialView.Behavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, FloatingSpeedDialView floatingSpeedDialView, int i) {
        ExpandableFloatingActionButton findExpandableFabDependency = CoordinatorLayoutUtils.findExpandableFabDependency(coordinatorLayout, floatingSpeedDialView);
        if (findExpandableFabDependency != null) {
            updateChildExpandedState(floatingSpeedDialView, findExpandableFabDependency);
        }
        return super.onLayoutChild(coordinatorLayout, floatingSpeedDialView, i);
    }

    public ExpandableFloatingSpeedDialBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
