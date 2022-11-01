package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.android.libraries.performance.primes.Primes;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
interface InternalComponent extends Primes.PrimesProvider {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Builder {
        InternalComponent build();

        Builder setApplicationContext(Context context);

        Builder setApplicationExitConfigurationsProvider(Optional optional);

        Builder setBatteryConfigurationsProvider(Optional optional);

        Builder setCpuProfilingConfigurationsProvider(Optional optional);

        Builder setCrashConfigurationsProvider(Optional optional);

        Builder setDebugMemoryConfigurationsProvider(Optional optional);

        Builder setGlobalConfigurationsProvider(Optional optional);

        Builder setJankConfigurationsProvider(Optional optional);

        Builder setMemoryConfigurationsProvider(Optional optional);

        Builder setMetricTransmittersSupplier(Supplier supplier);

        Builder setMonitorAllActivitiesProvider(Optional optional);

        Builder setNetworkConfigurationsProvider(Optional optional);

        Builder setSharedPreferencesSupplier(Optional optional);

        Builder setStorageConfigurationsProvider(Optional optional);

        Builder setThreadsConfigurations(Optional optional);

        Builder setTikTokTraceConfigurationsProvider(Optional optional);

        Builder setTimerConfigurationsProvider(Optional optional);

        Builder setTraceConfigurationsProvider(Optional optional);
    }
}
