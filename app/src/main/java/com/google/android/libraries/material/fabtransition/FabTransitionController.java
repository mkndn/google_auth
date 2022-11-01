package com.google.android.libraries.material.fabtransition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.view.View;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.fabtransition.AnimationBuilder;
import com.google.android.libraries.material.motion.CurvedMotionTiming;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FabTransitionController {
    private static final FabTransition UNSUCCESSFUL = new UnsuccessfulFabTransition();
    private final FloatingActionButton fab;
    private final Resources resources;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FabTransition {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class State {
        public static final int ACTIVITY = 5;
        public static final int BOTTOM_SHEET = 4;
        public static final int COLLAPSE = 1;
        public static final int EXPAND = 2;
        public static final int FLOATING_SHEET = 1;
        public static final int FLOATING_SPEED_DIAL = 2;
        public static final int NONE = 0;
        public static final int TOOLBAR = 3;
        public static final int UNINITIALIZED = 0;
        public Animator animation;
        public int collapsedFabBackgroundTint;
        public int expandedFabBackgroundTint;
        public View scrim;
        public View target;
        public int mode = 0;
        public int type = 0;

        private State() {
        }

        public static State get(FloatingActionButton floatingActionButton) {
            State state = (State) floatingActionButton.getTag(R$id.mtrl_fab_transition_state);
            if (state == null) {
                State state2 = new State();
                floatingActionButton.setTag(R$id.mtrl_fab_transition_state, state2);
                return state2;
            }
            return state;
        }

        public boolean canCollapse() {
            return this.mode == 2;
        }

        public boolean canExpandToFloatingSpeedDial() {
            int i;
            int i2 = this.mode;
            return (i2 == 0 || i2 == 1) && ((i = this.type) == 0 || i == 2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class UnsuccessfulFabTransition implements FabTransition {
        private UnsuccessfulFabTransition() {
        }
    }

    public FabTransitionController(FloatingActionButton floatingActionButton) {
        this.fab = floatingActionButton;
        this.resources = floatingActionButton.getResources();
    }

    private FabTransition collapseFromBottomSheet(State state) {
        return UNSUCCESSFUL;
    }

    private FabTransition collapseFromFloatingSheet(State state) {
        CircularRevealWidget circularRevealWidget = (CircularRevealWidget) state.target;
        return startNewAnimation(createCollapseFromFloatingSheetBuilder(new FabData(this.fab), circularRevealWidget, state.scrim), 1, 1, circularRevealWidget, state.scrim);
    }

    private FabTransition collapseFromToolbar(State state) {
        CircularRevealWidget circularRevealWidget = (CircularRevealWidget) state.target;
        return startNewAnimation(createCollapseFromToolbarBuilder(new FabData(this.fab), circularRevealWidget, state.scrim), 1, 3, circularRevealWidget, state.scrim);
    }

    private FabTransition collapseToFloatingSpeedDial(State state) {
        FabTransition collapse = FloatingSpeedDialTransitionHelper.collapse(this.fab, (ExpandableWidget) state.target, state.scrim, state.expandedFabBackgroundTint, state.collapsedFabBackgroundTint);
        state.type = 2;
        state.mode = 1;
        state.target = null;
        state.scrim = null;
        state.expandedFabBackgroundTint = 0;
        state.collapsedFabBackgroundTint = 0;
        return collapse;
    }

    private static AnimationBuilder createCollapseFromFloatingSheetBuilder(FabData fabData, CircularRevealWidget circularRevealWidget, View view) {
        CollapseAnimationBuilder collapseAnimationBuilder = new CollapseAnimationBuilder(fabData, circularRevealWidget, view, AnimationBuilder.Positioning.ofFloatingSheet(), 300L);
        collapseAnimationBuilder.animateElevation(150L, 150L);
        collapseAnimationBuilder.animateTranslation(CurvedMotionTiming.FAB_COLLAPSE_FLOATING_SHEET);
        collapseAnimationBuilder.animateIconFade(150L, 150L);
        collapseAnimationBuilder.animateRadialExpansion(0L, 180L, MaterialInterpolators.fastOutSlowIn());
        collapseAnimationBuilder.animateColor(60L, 150L);
        collapseAnimationBuilder.animateScrimFade(0L, 150L);
        collapseAnimationBuilder.animateContentFade(0L, 75L);
        return collapseAnimationBuilder;
    }

    private static AnimationBuilder createCollapseFromToolbarBuilder(FabData fabData, CircularRevealWidget circularRevealWidget, View view) {
        CollapseAnimationBuilder collapseAnimationBuilder = new CollapseAnimationBuilder(fabData, circularRevealWidget, view, AnimationBuilder.Positioning.ofToolbar(), 300L);
        collapseAnimationBuilder.animateElevation(150L, 150L);
        collapseAnimationBuilder.animateTranslation(CurvedMotionTiming.FAB_COLLAPSE_TOOLBAR);
        collapseAnimationBuilder.animateIconFade(150L, 150L);
        collapseAnimationBuilder.animateRadialExpansion(0L, 180L, MaterialInterpolators.fastOutSlowIn());
        collapseAnimationBuilder.animateColor(60L, 150L);
        collapseAnimationBuilder.animateContentTranslation(0L, 255L);
        collapseAnimationBuilder.animateContentFade(0L, 75L);
        return collapseAnimationBuilder;
    }

    private FabTransition startNewAnimation(AnimationBuilder animationBuilder, final int i, final int i2, final CircularRevealWidget circularRevealWidget, final View view) {
        final State state = State.get(this.fab);
        if (state.animation != null) {
            state.animation.cancel();
        }
        animationBuilder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.libraries.material.fabtransition.FabTransitionController.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                state.mode = i;
                state.type = i2;
                state.animation = null;
                if (state.mode == 1) {
                    state.target = null;
                    state.scrim = null;
                    FabTransitionController.this.fab.setAlpha(1.0f);
                    FabTransitionController.this.fab.setVisibility(0);
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                state.mode = i;
                state.type = i2;
                state.animation = animator;
                state.target = (View) circularRevealWidget;
                state.scrim = view;
                if (i == 2) {
                    FabTransitionController.this.fab.setAlpha(0.0f);
                    FabTransitionController.this.fab.setVisibility(4);
                }
            }
        });
        Animator build = animationBuilder.build();
        build.start();
        return new AnimatorFabTransition(build);
    }

    public FabTransition collapse() {
        State state = State.get(this.fab);
        if (state.canCollapse()) {
            switch (state.type) {
                case 1:
                    return collapseFromFloatingSheet(state);
                case 2:
                    return collapseToFloatingSpeedDial(state);
                case 3:
                    return collapseFromToolbar(state);
                case 4:
                    return collapseFromBottomSheet(state);
                case 5:
                    throw new IllegalStateException("Can't collapse from Activity in this context. Finish the Activity instead.");
                default:
                    throw new IllegalStateException("Can't collapse from type " + state.type);
            }
        }
        return UNSUCCESSFUL;
    }

    public FabTransition expandToFloatingSpeedDial(ExpandableWidget expandableWidget, View view) {
        return expandToFloatingSpeedDial(expandableWidget, view, 0, 0);
    }

    public FabTransition expandToFloatingSpeedDial(ExpandableWidget expandableWidget, View view, int i, int i2) {
        State state = State.get(this.fab);
        if (state.canExpandToFloatingSpeedDial()) {
            FabTransition expandToFloatingSpeedDial = FloatingSpeedDialTransitionHelper.expandToFloatingSpeedDial(this.fab, expandableWidget, view, i, i2);
            state.type = 2;
            state.mode = 2;
            state.target = (View) expandableWidget;
            state.scrim = view;
            state.expandedFabBackgroundTint = i;
            state.collapsedFabBackgroundTint = i2;
            return expandToFloatingSpeedDial;
        }
        return UNSUCCESSFUL;
    }
}
