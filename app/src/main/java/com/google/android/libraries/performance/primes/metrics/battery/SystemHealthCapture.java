package com.google.android.libraries.performance.primes.metrics.battery;

import android.content.Context;
import android.os.health.HealthStats;
import android.os.health.SystemHealthManager;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class SystemHealthCapture {
    private final Context applicationContext;
    private final HashingNameSanitizer hashingNameSanitizer = new HashingNameSanitizer();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public SystemHealthCapture(Context context) {
        this.applicationContext = context;
    }

    public BatteryMetric$UidHealthProto convertStats(HealthStats healthStats) {
        BatteryMetric$UidHealthProto.Builder builder = (BatteryMetric$UidHealthProto.Builder) HealthStatsProtos.convert(healthStats).toBuilder();
        HealthStatsProtos.hashNames(builder, this.hashingNameSanitizer);
        return (BatteryMetric$UidHealthProto) builder.build();
    }

    public BatteryMetric$UidHealthProto diffStats(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2) {
        BatteryMetric$UidHealthProto subtract = HealthStatsProtos.subtract(batteryMetric$UidHealthProto, batteryMetric$UidHealthProto2);
        if (subtract == null) {
            return null;
        }
        BatteryMetric$UidHealthProto.Builder builder = (BatteryMetric$UidHealthProto.Builder) subtract.toBuilder();
        HealthStatsProtos.sanitizeHashedNames(builder, this.hashingNameSanitizer);
        return (BatteryMetric$UidHealthProto) builder.build();
    }

    public HealthStats uidHealthStats() {
        SystemHealthManager systemHealthManager = (SystemHealthManager) this.applicationContext.getSystemService("systemhealth");
        if (systemHealthManager != null) {
            return systemHealthManager.takeMyUidSnapshot();
        }
        return null;
    }
}
