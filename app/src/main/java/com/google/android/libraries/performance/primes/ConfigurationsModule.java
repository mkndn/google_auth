package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.core.GlobalConfigurations;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ConfigurationsModule {
    public static /* synthetic */ SharedPreferences lambda$provideSharedPreferences$0(Context context) {
        return context.getSharedPreferences("primes", 0);
    }

    public static ApplicationExitConfigurations provideApplicationExitConfigurations(Optional optional) {
        ApplicationExitConfigurations applicationExitConfigurations = (ApplicationExitConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda3.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? applicationExitConfigurations : TestingInstrumentationHolder.instance.instrument(applicationExitConfigurations);
    }

    public static BatteryConfigurations provideBatteryConfigurations(Optional optional) {
        BatteryConfigurations batteryConfigurations = (BatteryConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda11.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? batteryConfigurations : TestingInstrumentationHolder.instance.instrument(batteryConfigurations);
    }

    public static CpuProfilingConfigurations provideCpuProfilingConfigurations(Optional optional) {
        return (CpuProfilingConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda7.INSTANCE)).get();
    }

    public static CrashConfigurations provideCrashConfigurations(Optional optional) {
        CrashConfigurations crashConfigurations = (CrashConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda0.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? crashConfigurations : TestingInstrumentationHolder.instance.instrument(crashConfigurations);
    }

    public static DebugMemoryConfigurations provideDebugMemoryConfigurations(Optional optional) {
        DebugMemoryConfigurations debugMemoryConfigurations = (DebugMemoryConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda8.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? debugMemoryConfigurations : TestingInstrumentationHolder.instance.instrument(debugMemoryConfigurations);
    }

    public static Optional provideGlobalConfigurations(Optional optional) {
        if (optional.isPresent()) {
            return Optional.fromNullable((GlobalConfigurations) ((Provider) optional.get()).get());
        }
        return Optional.absent();
    }

    public static JankConfigurations provideJankConfigurations(Optional optional) {
        JankConfigurations jankConfigurations = (JankConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda2.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? jankConfigurations : TestingInstrumentationHolder.instance.instrument(jankConfigurations);
    }

    public static NetworkConfigurations provideNetworkConfigurations(Optional optional) {
        NetworkConfigurations networkConfigurations = (NetworkConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda4.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? networkConfigurations : TestingInstrumentationHolder.instance.instrument(networkConfigurations);
    }

    @Singleton
    public static SharedPreferences provideSharedPreferences(final Context context, Optional optional) {
        return (SharedPreferences) ((Supplier) optional.or(new Supplier() { // from class: com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda10
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return ConfigurationsModule.lambda$provideSharedPreferences$0(context);
            }
        })).get();
    }

    public static StorageConfigurations provideStorageConfigurations(Optional optional) {
        StorageConfigurations storageConfigurations = (StorageConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda1.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? storageConfigurations : TestingInstrumentationHolder.instance.instrument(storageConfigurations);
    }

    public static TikTokTraceConfigurations provideTikTokTraceConfigurations(Optional optional) {
        TikTokTraceConfigurations tikTokTraceConfigurations = (TikTokTraceConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda9.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? tikTokTraceConfigurations : TestingInstrumentationHolder.instance.instrument(tikTokTraceConfigurations);
    }

    public static TimerConfigurations provideTimerConfigurations(Optional optional) {
        TimerConfigurations timerConfigurations = (TimerConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda6.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? timerConfigurations : TestingInstrumentationHolder.instance.instrument(timerConfigurations);
    }

    public static TraceConfigurations provideTraceConfigurations(Optional optional) {
        TraceConfigurations traceConfigurations = (TraceConfigurations) ((Provider) optional.or(ConfigurationsModule$$ExternalSyntheticLambda5.INSTANCE)).get();
        return (TestingInstrumentationHolder.instance == null || !optional.isPresent()) ? traceConfigurations : TestingInstrumentationHolder.instance.instrument(traceConfigurations);
    }

    @Singleton
    public static PrimesThreadsConfigurations provideThreadConfigurations(Optional optional) {
        PrimesThreadsConfigurations primesThreadsConfigurations = (PrimesThreadsConfigurations) optional.or(PrimesThreadsConfigurations.newBuilder().build());
        return TestingInstrumentationHolder.instance == null ? primesThreadsConfigurations : TestingInstrumentationHolder.instance.instrument(primesThreadsConfigurations);
    }
}
