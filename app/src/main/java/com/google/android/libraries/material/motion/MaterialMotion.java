package com.google.android.libraries.material.motion;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.PointF;
import android.view.View;
import android.view.animation.LinearInterpolator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialMotion {
    private static final TimeInterpolator CURVED_MOTION_INTERPOLATOR = new LinearInterpolator();

    public static ObjectAnimator createCurvedMotionAnimator(View view, CurvedMotionTiming curvedMotionTiming, PointF... pointFArr) {
        ObjectAnimator ofObject = ObjectAnimator.ofObject(view, TranslationProperty.TRANSLATION, new CurvedMotionEvaluator(curvedMotionTiming), pointFArr);
        ofObject.setDuration(curvedMotionTiming.totalDuration);
        ofObject.setInterpolator(CURVED_MOTION_INTERPOLATOR);
        return ofObject;
    }
}
