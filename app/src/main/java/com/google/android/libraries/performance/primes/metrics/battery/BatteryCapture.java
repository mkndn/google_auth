package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.health.HealthStats;
import com.google.android.libraries.clock.Clock;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.version.PrimesVersion;
import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import javax.inject.Inject;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryCapture {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/android/libraries/performance/primes/metrics/battery/BatteryCapture");
    private final Provider batteryConfigurations;
    private final Clock clock;
    private final SystemHealthCapture systemHealthCapture;
    private final String versionName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Snapshot {
        final Long currentTime;
        final String customEventName;
        final Long elapsedTime;
        final HealthStats healthStats;
        final ExtensionMetric$MetricExtension metricExtension;
        final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo;

        private Snapshot(Long l, Long l2, HealthStats healthStats, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, String str, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            this.elapsedTime = l;
            this.currentTime = l2;
            this.healthStats = healthStats;
            this.sampleInfo = sampleInfo;
            this.customEventName = str;
            this.metricExtension = extensionMetric$MetricExtension;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StatsStorage.StatsRecord toStatsRecord() {
            return new StatsStorage.StatsRecord(BatteryCapture.this.systemHealthCapture.convertStats(this.healthStats), this.elapsedTime, this.currentTime, Long.valueOf(PrimesVersion.getBaselineChangelist()), Long.valueOf(BatteryCapture.this.versionName == null ? 0L : BatteryCapture.this.versionName.hashCode()), this.sampleInfo, this.customEventName, this.metricExtension);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public BatteryCapture(String str, SystemHealthCapture systemHealthCapture, Clock clock, Provider provider) {
        this.versionName = str;
        this.systemHealthCapture = systemHealthCapture;
        this.clock = clock;
        this.batteryConfigurations = provider;
    }

    private static boolean consistentRecords(StatsStorage.StatsRecord statsRecord, StatsStorage.StatsRecord statsRecord2) {
        return matchesVersion(statsRecord, statsRecord2) && similarDuration(statsRecord, statsRecord2);
    }

    private static boolean matchesVersion(StatsStorage.StatsRecord statsRecord, StatsStorage.StatsRecord statsRecord2) {
        return BatteryCapture$$ExternalSyntheticBackport0.m(statsRecord.getPrimesVersion(), statsRecord2.getPrimesVersion()) && BatteryCapture$$ExternalSyntheticBackport0.m(statsRecord.getVersionNameHash(), statsRecord2.getVersionNameHash());
    }

    private static boolean similarDuration(StatsStorage.StatsRecord statsRecord, StatsStorage.StatsRecord statsRecord2) {
        if (statsRecord.getElapsedTime() == null || statsRecord.getCurrentTime() == null || statsRecord2.getElapsedTime() == null || statsRecord2.getCurrentTime() == null) {
            return false;
        }
        long longValue = statsRecord2.getElapsedTime().longValue() - statsRecord.getElapsedTime().longValue();
        long longValue2 = statsRecord2.getCurrentTime().longValue() - statsRecord.getCurrentTime().longValue();
        if (longValue2 <= 0) {
            return false;
        }
        long abs = Math.abs(longValue - longValue2);
        if (abs >= 25) {
            double d = abs;
            double d2 = longValue2;
            Double.isNaN(d);
            Double.isNaN(d2);
            if (d / d2 > 3.472222222222222E-5d) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SystemHealthProto$SystemHealthMetric createBatteryMetric(StatsStorage.StatsRecord statsRecord, StatsStorage.StatsRecord statsRecord2) {
        if (statsRecord != null && statsRecord2 != null && consistentRecords(statsRecord, statsRecord2)) {
            BatteryMetric$UidHealthProto diffStats = this.systemHealthCapture.diffStats(statsRecord2.getProto(), statsRecord.getProto());
            if (diffStats == null) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryCapture", "createBatteryMetric", 115, "BatteryCapture.java")).log("null diff");
                return null;
            } else if (diffStats.hasRealtimeBatteryMs() && diffStats.getRealtimeBatteryMs() > 0) {
                BatteryMetric$BatteryStatsDiff.Builder durationMs = BatteryMetric$BatteryStatsDiff.newBuilder().setDurationMs(((Long) Preconditions.checkNotNull(statsRecord2.getElapsedTime())).longValue() - ((Long) Preconditions.checkNotNull(statsRecord.getElapsedTime())).longValue());
                if (statsRecord.getSampleInfo() != null) {
                    durationMs.setStartInfo(statsRecord.getSampleInfo());
                }
                if (statsRecord.getCustomEventName() != null) {
                    durationMs.setStartConstantEventName(statsRecord.getCustomEventName());
                }
                if (statsRecord.getMetricExtension() != null) {
                    durationMs.setStartMetricExtension(statsRecord.getMetricExtension());
                }
                if (statsRecord2.getSampleInfo() != null) {
                    durationMs.setEndInfo(statsRecord2.getSampleInfo());
                }
                if (statsRecord2.getElapsedTime() != null) {
                    durationMs.setElapedRealtimeMs(statsRecord2.getElapsedTime().longValue());
                }
                durationMs.setUidHealthProtoDiff(diffStats);
                return (SystemHealthProto$SystemHealthMetric) SystemHealthProto$SystemHealthMetric.newBuilder().setBatteryUsageMetric(BatteryMetric$BatteryUsageMetric.newBuilder().setBatteryStatsDiff(durationMs)).build();
            } else {
                ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryCapture", "createBatteryMetric", 119, "BatteryCapture.java")).log("invalid realtime");
                return null;
            }
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryCapture", "createBatteryMetric", 110, "BatteryCapture.java")).log("inconsistent stats");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Snapshot takeSnapshot(BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, String str) {
        return new Snapshot(Long.valueOf(this.clock.elapsedRealtime()), Long.valueOf(this.clock.currentTimeMillis()), this.systemHealthCapture.uidHealthStats(), sampleInfo, str, ((BatteryConfigurations) this.batteryConfigurations.get()).getMetricExtensionProvider().getMetricExtension(str, sampleInfo));
    }
}
