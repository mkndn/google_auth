package com.google.android.libraries.material.animation;

import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialInterpolators {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    class Interpolators {
        private static final Interpolator linearOutSlowIn = PathInterpolatorCompat.create(0.0f, 0.0f, 0.2f, 1.0f);
        private static final Interpolator fastOutLinearIn = PathInterpolatorCompat.create(0.4f, 0.0f, 1.0f, 1.0f);
        private static final Interpolator fastOutSlowIn = PathInterpolatorCompat.create(0.4f, 0.0f, 0.2f, 1.0f);
    }

    public static Interpolator fastOutLinearIn() {
        return Interpolators.fastOutLinearIn;
    }

    public static Interpolator fastOutSlowIn() {
        return Interpolators.fastOutSlowIn;
    }

    public static Interpolator linearOutSlowIn() {
        return Interpolators.linearOutSlowIn;
    }
}
