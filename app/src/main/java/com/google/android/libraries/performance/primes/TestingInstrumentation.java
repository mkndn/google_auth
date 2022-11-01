package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.battery.BatteryConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.CrashConfigurations;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.android.libraries.performance.primes.metrics.jank.JankConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.DebugMemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryConfigurations;
import com.google.android.libraries.performance.primes.metrics.network.NetworkConfigurations;
import com.google.android.libraries.performance.primes.metrics.storage.StorageConfigurations;
import com.google.android.libraries.performance.primes.metrics.timer.TimerConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceConfigurations;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TestingInstrumentation {
    boolean allowSpawningLifeboat();

    void initializePrimes();

    PrimesThreadsConfigurations instrument(PrimesThreadsConfigurations primesThreadsConfigurations);

    BatteryConfigurations instrument(BatteryConfigurations batteryConfigurations);

    CrashConfigurations instrument(CrashConfigurations crashConfigurations);

    ApplicationExitConfigurations instrument(ApplicationExitConfigurations applicationExitConfigurations);

    JankConfigurations instrument(JankConfigurations jankConfigurations);

    DebugMemoryConfigurations instrument(DebugMemoryConfigurations debugMemoryConfigurations);

    MemoryConfigurations instrument(MemoryConfigurations memoryConfigurations);

    NetworkConfigurations instrument(NetworkConfigurations networkConfigurations);

    StorageConfigurations instrument(StorageConfigurations storageConfigurations);

    TimerConfigurations instrument(TimerConfigurations timerConfigurations);

    TikTokTraceConfigurations instrument(TikTokTraceConfigurations tikTokTraceConfigurations);

    TraceConfigurations instrument(TraceConfigurations traceConfigurations);

    void log(SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric);
}
