package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PrimesStartupDaggerModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static StartupConfigurations provideStartupConfigurations(Optional optional) {
        return (StartupConfigurations) optional.or(StartupConfigurations.newBuilder().build());
    }
}
