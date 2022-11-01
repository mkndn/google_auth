package com.google.android.libraries.performance.primes.metrics.jank;

import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisplayStats {
    private static volatile int maxFrameRenderTimeMs;
    private static volatile int refreshRate;

    private DisplayStats() {
    }

    public static Optional getRefreshRate(Context context) {
        if (Build.VERSION.SDK_INT >= 31) {
            return Optional.absent();
        }
        int i = refreshRate;
        if (i == 0) {
            synchronized (DisplayStats.class) {
                i = refreshRate;
                if (i == 0) {
                    int round = Math.round(((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getRefreshRate());
                    refreshRate = round;
                    i = round;
                }
            }
        }
        return Optional.of(Integer.valueOf(i));
    }

    public static int maxAcceptedFrameRenderTimeMs(Context context) {
        int i = maxFrameRenderTimeMs;
        if (i == 0) {
            synchronized (DisplayStats.class) {
                i = maxFrameRenderTimeMs;
                if (i == 0) {
                    int intValue = ((Integer) getRefreshRate(context).or(60)).intValue();
                    double d = intValue > 0 ? intValue : 60;
                    Double.isNaN(d);
                    int ceil = (int) Math.ceil(1000.0d / d);
                    maxFrameRenderTimeMs = ceil;
                    i = ceil;
                }
            }
        }
        return i;
    }
}
