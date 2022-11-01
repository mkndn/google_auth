package com.google.android.libraries.material.fabtransition;

import android.animation.Animator;
import com.google.android.libraries.material.fabtransition.FabTransitionController;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AnimatorFabTransition implements FabTransitionController.FabTransition {
    private Animator anim;

    public AnimatorFabTransition(Animator animator) {
        this.anim = animator;
    }
}
