package com.google.android.libraries.material.motion;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CurvedMotionTiming {
    public final float downwardsXDelay;
    public final float downwardsXDuration;
    public final float downwardsYDelay;
    public final float downwardsYDuration;
    public final long totalDuration;
    public final float upwardsXDelay;
    public final float upwardsXDuration;
    public final float upwardsYDelay;
    public final float upwardsYDuration;
    public static final CurvedMotionTiming NORMAL = new CurvedMotionTiming(375, 0, 285, 15, 360, 45, 330, 0, 330);
    public static final CurvedMotionTiming FAB_EXPAND_FLOATING_SHEET = new CurvedMotionTiming(345, 0, 150, 0, 345, 0, 345, 0, 150);
    public static final CurvedMotionTiming FAB_COLLAPSE_FLOATING_SHEET = new CurvedMotionTiming(300, 0, 255, 45, 255, 45, 255, 0, 255);
    public static final CurvedMotionTiming FAB_EXPAND_TOOLBAR = new CurvedMotionTiming(300, 0, 120, 0, 300, 0, 300, 0, 120);
    public static final CurvedMotionTiming FAB_COLLAPSE_TOOLBAR = new CurvedMotionTiming(300, 0, 255, 105, 195, 105, 195, 0, 255);
    public static final CurvedMotionTiming FAB_EXPAND_BOTTOM_SHEET = new CurvedMotionTiming(345, 0, 0, 45, 300, 0, 0, 0, 0);
    public static final CurvedMotionTiming FAB_EXPAND_ACTIVITY = new CurvedMotionTiming(375, 0, 0, 45, 330, 0, 0, 0, 0);

    private CurvedMotionTiming(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
        this.totalDuration = j;
        float f = (float) j;
        this.upwardsXDelay = ((float) j2) / f;
        this.upwardsXDuration = ((float) j3) / f;
        this.upwardsYDelay = ((float) j4) / f;
        this.upwardsYDuration = ((float) j5) / f;
        this.downwardsXDelay = ((float) j6) / f;
        this.downwardsXDuration = ((float) j7) / f;
        this.downwardsYDelay = ((float) j8) / f;
        this.downwardsYDuration = ((float) j9) / f;
    }
}
