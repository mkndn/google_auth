package com.google.android.libraries.material.speeddial.expandable;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.libraries.material.speeddial.FloatingSpeedDialView;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CoordinatorLayoutUtils {
    private static Object findDependency(CoordinatorLayout coordinatorLayout, View view, Class cls) {
        List dependencies = coordinatorLayout.getDependencies(view);
        int size = dependencies.size();
        for (int i = 0; i < size; i++) {
            View view2 = (View) dependencies.get(i);
            if (cls.isInstance(view2)) {
                return cls.cast(view2);
            }
        }
        return null;
    }

    public static ExpandableFloatingActionButton findExpandableFabDependency(CoordinatorLayout coordinatorLayout, View view) {
        return (ExpandableFloatingActionButton) findDependency(coordinatorLayout, view, ExpandableFloatingActionButton.class);
    }

    public static FloatingSpeedDialView findSpeedDialViewDependency(CoordinatorLayout coordinatorLayout, View view) {
        return (FloatingSpeedDialView) findDependency(coordinatorLayout, view, FloatingSpeedDialView.class);
    }
}
