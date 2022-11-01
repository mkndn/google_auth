package com.google.android.libraries.performance.primes.metrics.storage;

import android.os.Build;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesStorageDaggerModule {
    private static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 24;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StorageMetricService metricService(Optional optional, Provider provider) {
        if (isSupported() && optional.isPresent()) {
            return (StorageMetricService) provider.get();
        }
        return new StorageMetricService() { // from class: com.google.android.libraries.performance.primes.metrics.storage.PrimesStorageDaggerModule.1
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set storageMetricService(Optional optional, Provider provider) {
        if (optional.isPresent() && isSupported()) {
            return ImmutableSet.of((Object) ((MetricService) provider.get()));
        }
        return ImmutableSet.of();
    }
}
