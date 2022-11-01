package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.os.Build;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesApplicationExitDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set applicationExitMetricService(Provider provider) {
        return isSupported() ? ImmutableSet.of((Object) ((MetricService) provider.get())) : ImmutableSet.of();
    }

    private static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 30;
    }
}
