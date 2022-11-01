package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.performance.proto.primes.persistent.PersistentFormat$BatterySnapshot;
import javax.inject.Inject;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class StatsStorage {
    private final PersistentStorage storage;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StatsRecord {
        private final Long currentTime;
        private final String customEventName;
        private final Long elapsedTime;
        private final ExtensionMetric$MetricExtension metricExtension;
        private final Long primesVersion;
        private final BatteryMetric$UidHealthProto proto;
        private final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo;
        private final Long versionNameHash;

        /* JADX INFO: Access modifiers changed from: package-private */
        public StatsRecord(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, Long l, Long l2, Long l3, Long l4, BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo, String str, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            this.proto = batteryMetric$UidHealthProto;
            this.elapsedTime = l;
            this.currentTime = l2;
            this.primesVersion = l3;
            this.versionNameHash = l4;
            this.sampleInfo = sampleInfo;
            this.customEventName = str;
            this.metricExtension = extensionMetric$MetricExtension;
        }

        public Long getCurrentTime() {
            return this.currentTime;
        }

        public String getCustomEventName() {
            return this.customEventName;
        }

        public Long getElapsedTime() {
            return this.elapsedTime;
        }

        public ExtensionMetric$MetricExtension getMetricExtension() {
            return this.metricExtension;
        }

        public Long getPrimesVersion() {
            return this.primesVersion;
        }

        public BatteryMetric$UidHealthProto getProto() {
            return this.proto;
        }

        public BatteryMetric$BatteryStatsDiff.SampleInfo getSampleInfo() {
            return this.sampleInfo;
        }

        public Long getVersionNameHash() {
            return this.versionNameHash;
        }

        public String toString() {
            return String.format("StatsRecord:\n  elapsed: %d\n  current: %d\n  Primes version: %d\n  version name #: %d\n  customName: %s\n", this.elapsedTime, this.currentTime, this.primesVersion, this.versionNameHash, this.customEventName);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public StatsStorage(PersistentStorage persistentStorage) {
        this.storage = persistentStorage;
    }

    public void clear() {
        this.storage.remove("primes.battery.snapshot");
    }

    public StatsRecord readStatsRecord() {
        BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo;
        PersistentFormat$BatterySnapshot persistentFormat$BatterySnapshot = (PersistentFormat$BatterySnapshot) this.storage.readProto("primes.battery.snapshot", PersistentFormat$BatterySnapshot.parser());
        if (persistentFormat$BatterySnapshot == null) {
            return null;
        }
        if (persistentFormat$BatterySnapshot.hasSampleInfo()) {
            BatteryMetric$BatteryStatsDiff.SampleInfo forNumber = BatteryMetric$BatteryStatsDiff.SampleInfo.forNumber(persistentFormat$BatterySnapshot.getSampleInfo());
            if (forNumber != null) {
                sampleInfo = forNumber;
            } else {
                sampleInfo = BatteryMetric$BatteryStatsDiff.SampleInfo.UNKNOWN;
            }
        } else {
            sampleInfo = null;
        }
        return new StatsRecord(persistentFormat$BatterySnapshot.getUidHealthProto(), persistentFormat$BatterySnapshot.hasElapsedTime() ? Long.valueOf(persistentFormat$BatterySnapshot.getElapsedTime()) : null, persistentFormat$BatterySnapshot.hasCurrentTime() ? Long.valueOf(persistentFormat$BatterySnapshot.getCurrentTime()) : null, persistentFormat$BatterySnapshot.hasPrimesVersion() ? Long.valueOf(persistentFormat$BatterySnapshot.getPrimesVersion()) : null, persistentFormat$BatterySnapshot.hasVersionNameHash() ? Long.valueOf(persistentFormat$BatterySnapshot.getVersionNameHash()) : null, sampleInfo, persistentFormat$BatterySnapshot.hasCustomEventName() ? persistentFormat$BatterySnapshot.getCustomEventName() : null, persistentFormat$BatterySnapshot.hasMetricExtension() ? persistentFormat$BatterySnapshot.getMetricExtension() : null);
    }

    public boolean writeStatsRecord(StatsRecord statsRecord) {
        PersistentFormat$BatterySnapshot.Builder newBuilder = PersistentFormat$BatterySnapshot.newBuilder();
        if (statsRecord.proto != null) {
            newBuilder.setUidHealthProto(statsRecord.proto);
        }
        if (statsRecord.elapsedTime != null) {
            newBuilder.setElapsedTime(statsRecord.elapsedTime.longValue());
        }
        if (statsRecord.currentTime != null) {
            newBuilder.setCurrentTime(statsRecord.currentTime.longValue());
        }
        if (statsRecord.primesVersion != null) {
            newBuilder.setPrimesVersion(statsRecord.primesVersion.longValue());
        }
        if (statsRecord.versionNameHash != null) {
            newBuilder.setVersionNameHash(statsRecord.versionNameHash.longValue());
        }
        if (statsRecord.sampleInfo != null) {
            newBuilder.setSampleInfo(statsRecord.sampleInfo.getNumber());
        }
        if (statsRecord.customEventName != null) {
            newBuilder.setCustomEventName(statsRecord.customEventName);
        }
        if (statsRecord.metricExtension != null) {
            newBuilder.setMetricExtension(statsRecord.metricExtension);
        }
        return this.storage.writeProto("primes.battery.snapshot", (PersistentFormat$BatterySnapshot) newBuilder.build());
    }
}
