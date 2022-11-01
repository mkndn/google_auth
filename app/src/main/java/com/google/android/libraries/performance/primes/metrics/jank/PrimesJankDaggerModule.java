package com.google.android.libraries.performance.primes.metrics.jank;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesJankDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Singleton
    public static Handler handlerForFrameMetrics(Optional optional) {
        Looper looper;
        if (optional.isPresent()) {
            looper = (Looper) optional.get();
        } else {
            HandlerThread handlerThread = new HandlerThread("Primes-Jank", 10);
            handlerThread.start();
            looper = handlerThread.getLooper();
        }
        return new Handler(looper);
    }

    private static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set jankService(Optional optional, Provider provider) {
        if (isSupported() && optional.isPresent()) {
            return ImmutableSet.of((Object) ((MetricService) provider.get()));
        }
        return ImmutableSet.of();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JankMetricService metricService(Optional optional, Provider provider) {
        if (isSupported() && optional.isPresent()) {
            return (JankMetricService) provider.get();
        }
        return new JankMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.jank.PrimesJankDaggerModule.1
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean registerFrameMetricsListenerInOnCreate(Optional optional, Provider provider) {
        return optional.isPresent() && ((Boolean) optional.get()).booleanValue() && ((Boolean) provider.get()).booleanValue();
    }
}
