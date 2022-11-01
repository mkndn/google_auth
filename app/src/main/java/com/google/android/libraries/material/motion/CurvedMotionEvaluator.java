package com.google.android.libraries.material.motion;

import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.graphics.PointF;
import com.google.android.libraries.material.animation.MaterialInterpolators;
import com.google.android.libraries.material.math.MathUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CurvedMotionEvaluator implements TypeEvaluator {
    private final TimeInterpolator interpolator = MaterialInterpolators.fastOutSlowIn();
    private final PointF pointF = new PointF();
    private final CurvedMotionTiming timing;

    public CurvedMotionEvaluator(CurvedMotionTiming curvedMotionTiming) {
        this.timing = curvedMotionTiming;
    }

    static float clamp(float f, float f2, float f3) {
        if (f < f2) {
            return 0.0f;
        }
        if (f > f2 + f3) {
            return 1.0f;
        }
        return (f - f2) / f3;
    }

    private float interpolate(float f) {
        return this.interpolator.getInterpolation(f);
    }

    @Override // android.animation.TypeEvaluator
    public PointF evaluate(float f, PointF pointF, PointF pointF2) {
        float f2;
        float f3;
        float f4 = 1.0f;
        float f5 = 0.0f;
        if (pointF2.x == pointF.x || pointF2.y == pointF.y) {
            f2 = 0.0f;
            f3 = 1.0f;
        } else if (pointF2.y < pointF.y) {
            f5 = this.timing.upwardsXDelay;
            f4 = this.timing.upwardsXDuration;
            f2 = this.timing.upwardsYDelay;
            f3 = this.timing.upwardsYDuration;
        } else {
            f5 = this.timing.downwardsXDelay;
            f4 = this.timing.downwardsXDuration;
            f2 = this.timing.downwardsYDelay;
            f3 = this.timing.downwardsYDuration;
        }
        this.pointF.set(MathUtils.lerp(pointF.x, pointF2.x, interpolate(clamp(f, f5, f4))), MathUtils.lerp(pointF.y, pointF2.y, interpolate(clamp(f, f2, f3))));
        return this.pointF;
    }
}
