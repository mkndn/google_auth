package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.Build;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesBatteryDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set batteryService(Optional optional, Provider provider) {
        if (optional.isPresent() && isSupported()) {
            return ImmutableSet.of((Object) ((MetricService) provider.get()));
        }
        return ImmutableSet.of();
    }

    private static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BatteryMetricService metricService(Optional optional, Provider provider) {
        if (isSupported() && optional.isPresent()) {
            return (BatteryMetricService) provider.get();
        }
        return new BatteryMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.battery.PrimesBatteryDaggerModule.1
        };
    }
}
