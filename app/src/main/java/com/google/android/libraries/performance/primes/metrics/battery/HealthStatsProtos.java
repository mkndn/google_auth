package com.google.android.libraries.performance.primes.metrics.battery;

import android.os.health.HealthStats;
import android.os.health.TimerStat;
import com.google.android.libraries.performance.primes.metrics.battery.HashingNameSanitizer;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import logs.proto.wireless.performance.mobile.BatteryMetric$Counter;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;
import logs.proto.wireless.performance.mobile.BatteryMetric$PackageHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$ProcessHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$ServiceHealthProto;
import logs.proto.wireless.performance.mobile.BatteryMetric$Timer;
import logs.proto.wireless.performance.mobile.BatteryMetric$UidHealthProto;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class HealthStatsProtos {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class CounterOps extends ProtoStatsOps {
        private static final CounterOps INSTANCE = new CounterOps();

        private CounterOps() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$Counter convert(String str, Long l) {
            return HealthStatsProtos.counter(str, l.intValue());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public String nameOf(BatteryMetric$Counter batteryMetric$Counter) {
            return batteryMetric$Counter.getName().getUnhashedName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$Counter subtract(BatteryMetric$Counter batteryMetric$Counter, BatteryMetric$Counter batteryMetric$Counter2) {
            return HealthStatsProtos.subtract(batteryMetric$Counter, batteryMetric$Counter2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PackageHealthProtoOps extends ProtoStatsOps {
        private static final PackageHealthProtoOps INSTANCE = new PackageHealthProtoOps();

        private PackageHealthProtoOps() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$PackageHealthProto convert(String str, HealthStats healthStats) {
            return HealthStatsProtos.packageHealthProto(str, healthStats);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public String nameOf(BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto) {
            return batteryMetric$PackageHealthProto.getName().getUnhashedName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$PackageHealthProto subtract(BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto, BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto2) {
            return HealthStatsProtos.subtract(batteryMetric$PackageHealthProto, batteryMetric$PackageHealthProto2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ProcessHealthProtoOps extends ProtoStatsOps {
        private static final ProcessHealthProtoOps INSTANCE = new ProcessHealthProtoOps();

        private ProcessHealthProtoOps() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$ProcessHealthProto convert(String str, HealthStats healthStats) {
            return HealthStatsProtos.processHealthProto(str, healthStats);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public String nameOf(BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto) {
            return batteryMetric$ProcessHealthProto.getName().getUnhashedName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$ProcessHealthProto subtract(BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto, BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto2) {
            return HealthStatsProtos.subtract(batteryMetric$ProcessHealthProto, batteryMetric$ProcessHealthProto2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ProtoStatsOps {
        private ProtoStatsOps() {
        }

        abstract MessageLite convert(String str, Object obj);

        List convert(Map map) {
            MessageLite convert;
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : map.entrySet()) {
                if (entry.getValue() != null && (convert = convert((String) entry.getKey(), entry.getValue())) != null) {
                    arrayList.add(convert);
                }
            }
            return arrayList;
        }

        MessageLite find(List list, String str) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MessageLite messageLite = (MessageLite) it.next();
                if (str.equals(nameOf(messageLite))) {
                    return messageLite;
                }
            }
            return null;
        }

        abstract String nameOf(MessageLite messageLite);

        abstract MessageLite subtract(MessageLite messageLite, MessageLite messageLite2);

        List subtract(List list, List list2) {
            if (list.isEmpty()) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                MessageLite messageLite = (MessageLite) it.next();
                MessageLite subtract = subtract(messageLite, find(list2, nameOf(messageLite)));
                if (subtract != null) {
                    arrayList.add(subtract);
                }
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceHealthProtoOps extends ProtoStatsOps {
        private static final ServiceHealthProtoOps INSTANCE = new ServiceHealthProtoOps();

        private ServiceHealthProtoOps() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$ServiceHealthProto convert(String str, HealthStats healthStats) {
            return HealthStatsProtos.serviceHealthProto(str, healthStats);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public String nameOf(BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto) {
            return batteryMetric$ServiceHealthProto.getName().getUnhashedName();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$ServiceHealthProto subtract(BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto, BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto2) {
            return HealthStatsProtos.subtract(batteryMetric$ServiceHealthProto, batteryMetric$ServiceHealthProto2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TimerOps extends ProtoStatsOps {
        private static final TimerOps INSTANCE = new TimerOps();

        private TimerOps() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$Timer convert(String str, TimerStat timerStat) {
            return HealthStatsProtos.timer(str, timerStat);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public String nameOf(BatteryMetric$Timer batteryMetric$Timer) {
            if (batteryMetric$Timer.getName().hasUnhashedName()) {
                return batteryMetric$Timer.getName().getUnhashedName();
            }
            return Long.toHexString(batteryMetric$Timer.getName().getHash());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.android.libraries.performance.primes.metrics.battery.HealthStatsProtos.ProtoStatsOps
        public BatteryMetric$Timer subtract(BatteryMetric$Timer batteryMetric$Timer, BatteryMetric$Timer batteryMetric$Timer2) {
            return HealthStatsProtos.subtract(batteryMetric$Timer, batteryMetric$Timer2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BatteryMetric$UidHealthProto convert(HealthStats healthStats) {
        BatteryMetric$UidHealthProto.Builder newBuilder = BatteryMetric$UidHealthProto.newBuilder();
        long measurement = getMeasurement(healthStats, 10001);
        if (measurement != 0) {
            newBuilder.setRealtimeBatteryMs(measurement);
        }
        long measurement2 = getMeasurement(healthStats, 10002);
        if (measurement2 != 0) {
            newBuilder.setUptimeBatteryMs(measurement2);
        }
        long measurement3 = getMeasurement(healthStats, 10003);
        if (measurement3 != 0) {
            newBuilder.setRealtimeScreenOffBatteryMs(measurement3);
        }
        long measurement4 = getMeasurement(healthStats, 10004);
        if (measurement4 != 0) {
            newBuilder.setUptimeScreenOffBatteryMs(measurement4);
        }
        newBuilder.addAllWakelocksFull(getTimers(healthStats, 10005));
        newBuilder.addAllWakelocksPartial(getTimers(healthStats, 10006));
        newBuilder.addAllWakelocksWindow(getTimers(healthStats, 10007));
        newBuilder.addAllWakelocksDraw(getTimers(healthStats, 10008));
        newBuilder.addAllSyncs(getTimers(healthStats, 10009));
        newBuilder.addAllJobs(getTimers(healthStats, 10010));
        BatteryMetric$Timer timer = getTimer(healthStats, 10011);
        if (timer != null) {
            newBuilder.setGpsSensor(timer);
        }
        newBuilder.addAllSensors(getTimers(healthStats, 10012));
        newBuilder.addAllStatsProcesses(ProcessHealthProtoOps.INSTANCE.convert(getStatsMap(healthStats, 10014)));
        newBuilder.addAllStatsPackages(PackageHealthProtoOps.INSTANCE.convert(getStatsMap(healthStats, 10015)));
        long measurement5 = getMeasurement(healthStats, 10016);
        if (measurement5 != 0) {
            newBuilder.setWifiIdleMs(measurement5);
        }
        long measurement6 = getMeasurement(healthStats, 10017);
        if (measurement6 != 0) {
            newBuilder.setWifiRxMs(measurement6);
        }
        long measurement7 = getMeasurement(healthStats, 10018);
        if (measurement7 != 0) {
            newBuilder.setWifiTxMs(measurement7);
        }
        long measurement8 = getMeasurement(healthStats, 10019);
        if (measurement8 != 0) {
            newBuilder.setWifiPowerMams(measurement8);
        }
        long measurement9 = getMeasurement(healthStats, 10020);
        if (measurement9 != 0) {
            newBuilder.setBluetoothIdleMs(measurement9);
        }
        long measurement10 = getMeasurement(healthStats, 10021);
        if (measurement10 != 0) {
            newBuilder.setBluetoothRxMs(measurement10);
        }
        long measurement11 = getMeasurement(healthStats, 10022);
        if (measurement11 != 0) {
            newBuilder.setBluetoothTxMs(measurement11);
        }
        long measurement12 = getMeasurement(healthStats, 10023);
        if (measurement12 != 0) {
            newBuilder.setBluetoothPowerMams(measurement12);
        }
        long measurement13 = getMeasurement(healthStats, 10024);
        if (measurement13 != 0) {
            newBuilder.setMobileIdleMs(measurement13);
        }
        long measurement14 = getMeasurement(healthStats, 10025);
        if (measurement14 != 0) {
            newBuilder.setMobileRxMs(measurement14);
        }
        long measurement15 = getMeasurement(healthStats, 10026);
        if (measurement15 != 0) {
            newBuilder.setMobileTxMs(measurement15);
        }
        long measurement16 = getMeasurement(healthStats, 10027);
        if (measurement16 != 0) {
            newBuilder.setMobilePowerMams(measurement16);
        }
        long measurement17 = getMeasurement(healthStats, 10028);
        if (measurement17 != 0) {
            newBuilder.setWifiRunningMs(measurement17);
        }
        long measurement18 = getMeasurement(healthStats, 10029);
        if (measurement18 != 0) {
            newBuilder.setWifiFullLockMs(measurement18);
        }
        BatteryMetric$Timer timer2 = getTimer(healthStats, 10030);
        if (timer2 != null) {
            newBuilder.setWifiScan(timer2);
        }
        long measurement19 = getMeasurement(healthStats, 10031);
        if (measurement19 != 0) {
            newBuilder.setWifiMulticastMs(measurement19);
        }
        BatteryMetric$Timer timer3 = getTimer(healthStats, 10032);
        if (timer3 != null) {
            newBuilder.setAudio(timer3);
        }
        BatteryMetric$Timer timer4 = getTimer(healthStats, 10033);
        if (timer4 != null) {
            newBuilder.setVideo(timer4);
        }
        BatteryMetric$Timer timer5 = getTimer(healthStats, 10034);
        if (timer5 != null) {
            newBuilder.setFlashlight(timer5);
        }
        BatteryMetric$Timer timer6 = getTimer(healthStats, 10035);
        if (timer6 != null) {
            newBuilder.setCamera(timer6);
        }
        BatteryMetric$Timer timer7 = getTimer(healthStats, 10036);
        if (timer7 != null) {
            newBuilder.setForegroundActivity(timer7);
        }
        BatteryMetric$Timer timer8 = getTimer(healthStats, 10037);
        if (timer8 != null) {
            newBuilder.setBluetoothScan(timer8);
        }
        BatteryMetric$Timer timer9 = getTimer(healthStats, 10038);
        if (timer9 != null) {
            newBuilder.setProcessStateTopMs(timer9);
        }
        BatteryMetric$Timer timer10 = getTimer(healthStats, 10039);
        if (timer10 != null) {
            newBuilder.setProcessStateForegroundServiceMs(timer10);
        }
        BatteryMetric$Timer timer11 = getTimer(healthStats, 10040);
        if (timer11 != null) {
            newBuilder.setProcessStateTopSleepingMs(timer11);
        }
        BatteryMetric$Timer timer12 = getTimer(healthStats, 10041);
        if (timer12 != null) {
            newBuilder.setProcessStateForegroundMs(timer12);
        }
        BatteryMetric$Timer timer13 = getTimer(healthStats, 10042);
        if (timer13 != null) {
            newBuilder.setProcessStateBackgroundMs(timer13);
        }
        BatteryMetric$Timer timer14 = getTimer(healthStats, 10043);
        if (timer14 != null) {
            newBuilder.setProcessStateCachedMs(timer14);
        }
        BatteryMetric$Timer timer15 = getTimer(healthStats, 10044);
        if (timer15 != null) {
            newBuilder.setVibrator(timer15);
        }
        long measurement20 = getMeasurement(healthStats, 10045);
        if (measurement20 != 0) {
            newBuilder.setOtherUserActivityCount(measurement20);
        }
        long measurement21 = getMeasurement(healthStats, 10046);
        if (measurement21 != 0) {
            newBuilder.setButtonUserActivityCount(measurement21);
        }
        long measurement22 = getMeasurement(healthStats, 10047);
        if (measurement22 != 0) {
            newBuilder.setTouchUserActivityCount(measurement22);
        }
        long measurement23 = getMeasurement(healthStats, 10048);
        if (measurement23 != 0) {
            newBuilder.setMobileRxBytes(measurement23);
        }
        long measurement24 = getMeasurement(healthStats, 10049);
        if (measurement24 != 0) {
            newBuilder.setMobileTxBytes(measurement24);
        }
        long measurement25 = getMeasurement(healthStats, 10050);
        if (measurement25 != 0) {
            newBuilder.setWifiRxBytes(measurement25);
        }
        long measurement26 = getMeasurement(healthStats, 10051);
        if (measurement26 != 0) {
            newBuilder.setWifiTxBytes(measurement26);
        }
        long measurement27 = getMeasurement(healthStats, 10052);
        if (measurement27 != 0) {
            newBuilder.setBluetoothRxBytes(measurement27);
        }
        long measurement28 = getMeasurement(healthStats, 10053);
        if (measurement28 != 0) {
            newBuilder.setBluetoothTxBytes(measurement28);
        }
        long measurement29 = getMeasurement(healthStats, 10054);
        if (measurement29 != 0) {
            newBuilder.setMobileRxPackets(measurement29);
        }
        long measurement30 = getMeasurement(healthStats, 10055);
        if (measurement30 != 0) {
            newBuilder.setMobileTxPackets(measurement30);
        }
        long measurement31 = getMeasurement(healthStats, 10056);
        if (measurement31 != 0) {
            newBuilder.setWifiRxPackets(measurement31);
        }
        long measurement32 = getMeasurement(healthStats, 10057);
        if (measurement32 != 0) {
            newBuilder.setWifiTxPackets(measurement32);
        }
        long measurement33 = getMeasurement(healthStats, 10058);
        if (measurement33 != 0) {
            newBuilder.setBluetoothRxPackets(measurement33);
        }
        long measurement34 = getMeasurement(healthStats, 10059);
        if (measurement34 != 0) {
            newBuilder.setBluetoothTxPackets(measurement34);
        }
        BatteryMetric$Timer timer16 = getTimer(healthStats, 10061);
        if (timer16 != null) {
            newBuilder.setMobileRadioActive(timer16);
        }
        long measurement35 = getMeasurement(healthStats, 10062);
        if (measurement35 != 0) {
            newBuilder.setUserCpuTimeMs(measurement35);
        }
        long measurement36 = getMeasurement(healthStats, 10063);
        if (measurement36 != 0) {
            newBuilder.setSystemCpuTimeMs(measurement36);
        }
        long measurement37 = getMeasurement(healthStats, 10064);
        if (measurement37 != 0) {
            newBuilder.setCpuPowerMams(measurement37);
        }
        return (BatteryMetric$UidHealthProto) newBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BatteryMetric$Counter counter(String str, int i) {
        if (i == 0) {
            return null;
        }
        BatteryMetric$Counter.Builder count = BatteryMetric$Counter.newBuilder().setCount(i);
        if (str != null) {
            count.setName(hashedString(str));
        }
        return (BatteryMetric$Counter) count.build();
    }

    private static long getMeasurement(HealthStats healthStats, int i) {
        if (healthStats == null || !healthStats.hasMeasurement(i)) {
            return 0L;
        }
        return healthStats.getMeasurement(i);
    }

    private static Map getMeasurementsMap(HealthStats healthStats, int i) {
        return (healthStats == null || !healthStats.hasMeasurements(i)) ? Collections.emptyMap() : healthStats.getMeasurements(i);
    }

    private static Map getStatsMap(HealthStats healthStats, int i) {
        return (healthStats == null || !healthStats.hasStats(i)) ? Collections.emptyMap() : healthStats.getStats(i);
    }

    private static BatteryMetric$Timer getTimer(HealthStats healthStats, int i) {
        if (healthStats == null || !healthStats.hasTimer(i)) {
            return null;
        }
        return timer(null, healthStats.getTimer(i));
    }

    private static List getTimers(HealthStats healthStats, int i) {
        if (healthStats != null && healthStats.hasTimers(i)) {
            return TimerOps.INSTANCE.convert(healthStats.getTimers(i));
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void hashNames(BatteryMetric$UidHealthProto.Builder builder, HashingNameSanitizer hashingNameSanitizer) {
        builder.getWakelocksFullList();
        for (int i = 0; i < builder.getWakelocksFullCount(); i++) {
            builder.setWakelocksFull(i, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksFull(i)));
        }
        builder.getWakelocksPartialList();
        for (int i2 = 0; i2 < builder.getWakelocksPartialCount(); i2++) {
            builder.setWakelocksPartial(i2, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksPartial(i2)));
        }
        builder.getWakelocksWindowList();
        for (int i3 = 0; i3 < builder.getWakelocksWindowCount(); i3++) {
            builder.setWakelocksWindow(i3, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksWindow(i3)));
        }
        builder.getWakelocksDrawList();
        for (int i4 = 0; i4 < builder.getWakelocksDrawCount(); i4++) {
            builder.setWakelocksDraw(i4, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksDraw(i4)));
        }
        builder.getSyncsList();
        for (int i5 = 0; i5 < builder.getSyncsCount(); i5++) {
            builder.setSyncs(i5, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.SYNC, builder.getSyncs(i5)));
        }
        builder.getJobsList();
        for (int i6 = 0; i6 < builder.getJobsCount(); i6++) {
            builder.setJobs(i6, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.JOB, builder.getJobs(i6)));
        }
        builder.getSensorsList();
        for (int i7 = 0; i7 < builder.getSensorsCount(); i7++) {
            builder.setSensors(i7, hashingNameSanitizer.hashRawTimerName(HashingNameSanitizer.NameType.SENSOR, builder.getSensors(i7)));
        }
    }

    private static BatteryMetric$HashedString hashedString(String str) {
        return (BatteryMetric$HashedString) BatteryMetric$HashedString.newBuilder().setUnhashedName(str).build();
    }

    static boolean isZero(BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto) {
        if (batteryMetric$PackageHealthProto != null && (batteryMetric$PackageHealthProto.getStatsServicesCount() != 0 || batteryMetric$PackageHealthProto.getWakeupAlarmsCountCount() != 0)) {
            return false;
        }
        return true;
    }

    static BatteryMetric$PackageHealthProto packageHealthProto(String str, HealthStats healthStats) {
        BatteryMetric$PackageHealthProto.Builder newBuilder = BatteryMetric$PackageHealthProto.newBuilder();
        newBuilder.addAllStatsServices(ServiceHealthProtoOps.INSTANCE.convert(getStatsMap(healthStats, 40001)));
        newBuilder.addAllWakeupAlarmsCount(CounterOps.INSTANCE.convert(getMeasurementsMap(healthStats, 40002)));
        if (str != null) {
            newBuilder.setName(hashedString(str));
        }
        BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto = (BatteryMetric$PackageHealthProto) newBuilder.build();
        if (isZero(batteryMetric$PackageHealthProto)) {
            return null;
        }
        return batteryMetric$PackageHealthProto;
    }

    static BatteryMetric$ProcessHealthProto processHealthProto(String str, HealthStats healthStats) {
        BatteryMetric$ProcessHealthProto.Builder newBuilder = BatteryMetric$ProcessHealthProto.newBuilder();
        long measurement = getMeasurement(healthStats, 30001);
        if (measurement != 0) {
            newBuilder.setUserTimeMs(measurement);
        }
        long measurement2 = getMeasurement(healthStats, 30002);
        if (measurement2 != 0) {
            newBuilder.setSystemTimeMs(measurement2);
        }
        long measurement3 = getMeasurement(healthStats, 30003);
        if (measurement3 != 0) {
            newBuilder.setStartsCount(measurement3);
        }
        long measurement4 = getMeasurement(healthStats, 30004);
        if (measurement4 != 0) {
            newBuilder.setCrashesCount(measurement4);
        }
        long measurement5 = getMeasurement(healthStats, 30005);
        if (measurement5 != 0) {
            newBuilder.setAnrCount(measurement5);
        }
        long measurement6 = getMeasurement(healthStats, 30006);
        if (measurement6 != 0) {
            newBuilder.setForegroundMs(measurement6);
        }
        if (str != null) {
            newBuilder.setName(hashedString(str));
        }
        BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto = (BatteryMetric$ProcessHealthProto) newBuilder.build();
        if (isZero(batteryMetric$ProcessHealthProto)) {
            return null;
        }
        return batteryMetric$ProcessHealthProto;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sanitizeHashedNames(BatteryMetric$UidHealthProto.Builder builder, HashingNameSanitizer hashingNameSanitizer) {
        builder.getWakelocksFullList();
        for (int i = 0; i < builder.getWakelocksFullCount(); i++) {
            builder.setWakelocksFull(i, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksFull(i)));
        }
        builder.getWakelocksPartialList();
        for (int i2 = 0; i2 < builder.getWakelocksPartialCount(); i2++) {
            builder.setWakelocksPartial(i2, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksPartial(i2)));
        }
        builder.getWakelocksWindowList();
        for (int i3 = 0; i3 < builder.getWakelocksWindowCount(); i3++) {
            builder.setWakelocksWindow(i3, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksWindow(i3)));
        }
        builder.getWakelocksDrawList();
        for (int i4 = 0; i4 < builder.getWakelocksDrawCount(); i4++) {
            builder.setWakelocksDraw(i4, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.WAKELOCK, builder.getWakelocksDraw(i4)));
        }
        builder.getSyncsList();
        for (int i5 = 0; i5 < builder.getSyncsCount(); i5++) {
            builder.setSyncs(i5, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.SYNC, builder.getSyncs(i5)));
        }
        builder.getJobsList();
        for (int i6 = 0; i6 < builder.getJobsCount(); i6++) {
            builder.setJobs(i6, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.JOB, builder.getJobs(i6)));
        }
        builder.getSensorsList();
        for (int i7 = 0; i7 < builder.getSensorsCount(); i7++) {
            builder.setSensors(i7, hashingNameSanitizer.sanitizeHashedTimerName(HashingNameSanitizer.NameType.SENSOR, builder.getSensors(i7)));
        }
    }

    static BatteryMetric$ServiceHealthProto serviceHealthProto(String str, HealthStats healthStats) {
        BatteryMetric$ServiceHealthProto.Builder newBuilder = BatteryMetric$ServiceHealthProto.newBuilder();
        int measurement = (int) getMeasurement(healthStats, 50001);
        if (measurement != 0) {
            newBuilder.setStartServiceCount(measurement);
        }
        int measurement2 = (int) getMeasurement(healthStats, 50002);
        if (measurement2 != 0) {
            newBuilder.setLaunchCount(measurement2);
        }
        if (str != null) {
            newBuilder.setName(hashedString(str));
        }
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto = (BatteryMetric$ServiceHealthProto) newBuilder.build();
        if (isZero(batteryMetric$ServiceHealthProto)) {
            return null;
        }
        return batteryMetric$ServiceHealthProto;
    }

    static BatteryMetric$Counter subtract(BatteryMetric$Counter batteryMetric$Counter, BatteryMetric$Counter batteryMetric$Counter2) {
        int count;
        if (batteryMetric$Counter != null && batteryMetric$Counter2 != null) {
            if (batteryMetric$Counter.hasCount() && (count = batteryMetric$Counter.getCount() - batteryMetric$Counter2.getCount()) != 0) {
                BatteryMetric$Counter.Builder newBuilder = BatteryMetric$Counter.newBuilder();
                if (batteryMetric$Counter.hasName()) {
                    newBuilder.setName(batteryMetric$Counter.getName());
                }
                return (BatteryMetric$Counter) newBuilder.setCount(count).build();
            }
            return null;
        }
        return batteryMetric$Counter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BatteryMetric$Timer timer(String str, TimerStat timerStat) {
        BatteryMetric$Timer.Builder durationMs = BatteryMetric$Timer.newBuilder().setCount(timerStat.getCount()).setDurationMs(timerStat.getTime());
        if (durationMs.getCount() < 0) {
            durationMs.setCount(0);
        }
        if (str != null) {
            durationMs.setName(hashedString(str));
        }
        if (durationMs.getCount() == 0 && durationMs.getDurationMs() == 0) {
            return null;
        }
        return (BatteryMetric$Timer) durationMs.build();
    }

    static boolean isZero(BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto) {
        if (batteryMetric$ProcessHealthProto != null && (batteryMetric$ProcessHealthProto.getUserTimeMs() > 0 || batteryMetric$ProcessHealthProto.getSystemTimeMs() > 0 || batteryMetric$ProcessHealthProto.getStartsCount() > 0 || batteryMetric$ProcessHealthProto.getCrashesCount() > 0 || batteryMetric$ProcessHealthProto.getAnrCount() > 0 || batteryMetric$ProcessHealthProto.getForegroundMs() > 0)) {
            return false;
        }
        return true;
    }

    static BatteryMetric$PackageHealthProto subtract(BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto, BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto2) {
        if (batteryMetric$PackageHealthProto != null && batteryMetric$PackageHealthProto2 != null) {
            BatteryMetric$PackageHealthProto.Builder newBuilder = BatteryMetric$PackageHealthProto.newBuilder();
            newBuilder.addAllStatsServices(ServiceHealthProtoOps.INSTANCE.subtract(batteryMetric$PackageHealthProto.getStatsServicesList(), batteryMetric$PackageHealthProto2.getStatsServicesList()));
            newBuilder.addAllWakeupAlarmsCount(CounterOps.INSTANCE.subtract(batteryMetric$PackageHealthProto.getWakeupAlarmsCountList(), batteryMetric$PackageHealthProto2.getWakeupAlarmsCountList()));
            newBuilder.setName(batteryMetric$PackageHealthProto.getName());
            BatteryMetric$PackageHealthProto batteryMetric$PackageHealthProto3 = (BatteryMetric$PackageHealthProto) newBuilder.build();
            if (isZero(batteryMetric$PackageHealthProto3)) {
                return null;
            }
            return batteryMetric$PackageHealthProto3;
        }
        return batteryMetric$PackageHealthProto;
    }

    static boolean isZero(BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto) {
        if (batteryMetric$ServiceHealthProto != null && (batteryMetric$ServiceHealthProto.getStartServiceCount() > 0 || batteryMetric$ServiceHealthProto.getLaunchCount() > 0)) {
            return false;
        }
        return true;
    }

    static boolean isZero(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
        if (batteryMetric$UidHealthProto != null && (batteryMetric$UidHealthProto.getRealtimeBatteryMs() > 0 || batteryMetric$UidHealthProto.getUptimeBatteryMs() > 0 || batteryMetric$UidHealthProto.getRealtimeScreenOffBatteryMs() > 0 || batteryMetric$UidHealthProto.getUptimeScreenOffBatteryMs() > 0 || batteryMetric$UidHealthProto.getWakelocksFullCount() != 0 || batteryMetric$UidHealthProto.getWakelocksPartialCount() != 0 || batteryMetric$UidHealthProto.getWakelocksWindowCount() != 0 || batteryMetric$UidHealthProto.getWakelocksDrawCount() != 0 || batteryMetric$UidHealthProto.getSyncsCount() != 0 || batteryMetric$UidHealthProto.getJobsCount() != 0 || batteryMetric$UidHealthProto.getSensorsCount() != 0 || batteryMetric$UidHealthProto.getStatsPidsCount() != 0 || batteryMetric$UidHealthProto.getStatsProcessesCount() != 0 || batteryMetric$UidHealthProto.getStatsPackagesCount() != 0 || batteryMetric$UidHealthProto.getWifiIdleMs() > 0 || batteryMetric$UidHealthProto.getWifiRxMs() > 0 || batteryMetric$UidHealthProto.getWifiTxMs() > 0 || batteryMetric$UidHealthProto.getWifiPowerMams() > 0 || batteryMetric$UidHealthProto.getBluetoothIdleMs() > 0 || batteryMetric$UidHealthProto.getBluetoothRxMs() > 0 || batteryMetric$UidHealthProto.getBluetoothTxMs() > 0 || batteryMetric$UidHealthProto.getBluetoothPowerMams() > 0 || batteryMetric$UidHealthProto.getMobileIdleMs() > 0 || batteryMetric$UidHealthProto.getMobileRxMs() > 0 || batteryMetric$UidHealthProto.getMobileTxMs() > 0 || batteryMetric$UidHealthProto.getMobilePowerMams() > 0 || batteryMetric$UidHealthProto.getWifiRunningMs() > 0 || batteryMetric$UidHealthProto.getWifiFullLockMs() > 0 || batteryMetric$UidHealthProto.getWifiMulticastMs() > 0 || batteryMetric$UidHealthProto.getOtherUserActivityCount() > 0 || batteryMetric$UidHealthProto.getButtonUserActivityCount() > 0 || batteryMetric$UidHealthProto.getTouchUserActivityCount() > 0 || batteryMetric$UidHealthProto.getMobileRxBytes() > 0 || batteryMetric$UidHealthProto.getMobileTxBytes() > 0 || batteryMetric$UidHealthProto.getWifiRxBytes() > 0 || batteryMetric$UidHealthProto.getWifiTxBytes() > 0 || batteryMetric$UidHealthProto.getBluetoothRxBytes() > 0 || batteryMetric$UidHealthProto.getBluetoothTxBytes() > 0 || batteryMetric$UidHealthProto.getMobileRxPackets() > 0 || batteryMetric$UidHealthProto.getMobileTxPackets() > 0 || batteryMetric$UidHealthProto.getWifiRxPackets() > 0 || batteryMetric$UidHealthProto.getWifiTxPackets() > 0 || batteryMetric$UidHealthProto.getBluetoothRxPackets() > 0 || batteryMetric$UidHealthProto.getBluetoothTxPackets() > 0 || batteryMetric$UidHealthProto.getUserCpuTimeMs() > 0 || batteryMetric$UidHealthProto.getSystemCpuTimeMs() > 0 || batteryMetric$UidHealthProto.getCpuPowerMams() > 0)) {
            return false;
        }
        return true;
    }

    static BatteryMetric$ProcessHealthProto subtract(BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto, BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto2) {
        if (batteryMetric$ProcessHealthProto != null && batteryMetric$ProcessHealthProto2 != null) {
            BatteryMetric$ProcessHealthProto.Builder newBuilder = BatteryMetric$ProcessHealthProto.newBuilder();
            if (batteryMetric$ProcessHealthProto.hasUserTimeMs()) {
                long userTimeMs = batteryMetric$ProcessHealthProto.getUserTimeMs() - batteryMetric$ProcessHealthProto2.getUserTimeMs();
                if (userTimeMs != 0) {
                    newBuilder.setUserTimeMs(userTimeMs);
                }
            }
            if (batteryMetric$ProcessHealthProto.hasSystemTimeMs()) {
                long systemTimeMs = batteryMetric$ProcessHealthProto.getSystemTimeMs() - batteryMetric$ProcessHealthProto2.getSystemTimeMs();
                if (systemTimeMs != 0) {
                    newBuilder.setSystemTimeMs(systemTimeMs);
                }
            }
            if (batteryMetric$ProcessHealthProto.hasStartsCount()) {
                long startsCount = batteryMetric$ProcessHealthProto.getStartsCount() - batteryMetric$ProcessHealthProto2.getStartsCount();
                if (startsCount != 0) {
                    newBuilder.setStartsCount(startsCount);
                }
            }
            if (batteryMetric$ProcessHealthProto.hasCrashesCount()) {
                long crashesCount = batteryMetric$ProcessHealthProto.getCrashesCount() - batteryMetric$ProcessHealthProto2.getCrashesCount();
                if (crashesCount != 0) {
                    newBuilder.setCrashesCount(crashesCount);
                }
            }
            if (batteryMetric$ProcessHealthProto.hasAnrCount()) {
                long anrCount = batteryMetric$ProcessHealthProto.getAnrCount() - batteryMetric$ProcessHealthProto2.getAnrCount();
                if (anrCount != 0) {
                    newBuilder.setAnrCount(anrCount);
                }
            }
            if (batteryMetric$ProcessHealthProto.hasForegroundMs()) {
                long foregroundMs = batteryMetric$ProcessHealthProto.getForegroundMs() - batteryMetric$ProcessHealthProto2.getForegroundMs();
                if (foregroundMs != 0) {
                    newBuilder.setForegroundMs(foregroundMs);
                }
            }
            newBuilder.setName(batteryMetric$ProcessHealthProto.getName());
            BatteryMetric$ProcessHealthProto batteryMetric$ProcessHealthProto3 = (BatteryMetric$ProcessHealthProto) newBuilder.build();
            if (isZero(batteryMetric$ProcessHealthProto3)) {
                return null;
            }
            return batteryMetric$ProcessHealthProto3;
        }
        return batteryMetric$ProcessHealthProto;
    }

    static BatteryMetric$ServiceHealthProto subtract(BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto, BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto2) {
        int launchCount;
        int startServiceCount;
        if (batteryMetric$ServiceHealthProto != null && batteryMetric$ServiceHealthProto2 != null) {
            BatteryMetric$ServiceHealthProto.Builder newBuilder = BatteryMetric$ServiceHealthProto.newBuilder();
            if (batteryMetric$ServiceHealthProto.hasStartServiceCount() && (startServiceCount = batteryMetric$ServiceHealthProto.getStartServiceCount() - batteryMetric$ServiceHealthProto2.getStartServiceCount()) != 0) {
                newBuilder.setStartServiceCount(startServiceCount);
            }
            if (batteryMetric$ServiceHealthProto.hasLaunchCount() && (launchCount = batteryMetric$ServiceHealthProto.getLaunchCount() - batteryMetric$ServiceHealthProto2.getLaunchCount()) != 0) {
                newBuilder.setLaunchCount(launchCount);
            }
            newBuilder.setName(batteryMetric$ServiceHealthProto.getName());
            BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto3 = (BatteryMetric$ServiceHealthProto) newBuilder.build();
            if (isZero(batteryMetric$ServiceHealthProto3)) {
                return null;
            }
            return batteryMetric$ServiceHealthProto3;
        }
        return batteryMetric$ServiceHealthProto;
    }

    static BatteryMetric$Timer subtract(BatteryMetric$Timer batteryMetric$Timer, BatteryMetric$Timer batteryMetric$Timer2) {
        if (batteryMetric$Timer != null && batteryMetric$Timer2 != null) {
            int count = batteryMetric$Timer.getCount() - batteryMetric$Timer2.getCount();
            long durationMs = batteryMetric$Timer.getDurationMs() - batteryMetric$Timer2.getDurationMs();
            if (count == 0 && durationMs == 0) {
                return null;
            }
            BatteryMetric$Timer.Builder newBuilder = BatteryMetric$Timer.newBuilder();
            if (batteryMetric$Timer.hasName()) {
                newBuilder.setName(batteryMetric$Timer.getName());
            }
            return (BatteryMetric$Timer) newBuilder.setCount(count).setDurationMs(durationMs).build();
        }
        return batteryMetric$Timer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BatteryMetric$UidHealthProto subtract(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto, BatteryMetric$UidHealthProto batteryMetric$UidHealthProto2) {
        if (batteryMetric$UidHealthProto != null && batteryMetric$UidHealthProto2 != null) {
            BatteryMetric$UidHealthProto.Builder newBuilder = BatteryMetric$UidHealthProto.newBuilder();
            if (batteryMetric$UidHealthProto.hasRealtimeBatteryMs()) {
                long realtimeBatteryMs = batteryMetric$UidHealthProto.getRealtimeBatteryMs() - batteryMetric$UidHealthProto2.getRealtimeBatteryMs();
                if (realtimeBatteryMs != 0) {
                    newBuilder.setRealtimeBatteryMs(realtimeBatteryMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasUptimeBatteryMs()) {
                long uptimeBatteryMs = batteryMetric$UidHealthProto.getUptimeBatteryMs() - batteryMetric$UidHealthProto2.getUptimeBatteryMs();
                if (uptimeBatteryMs != 0) {
                    newBuilder.setUptimeBatteryMs(uptimeBatteryMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasRealtimeScreenOffBatteryMs()) {
                long realtimeScreenOffBatteryMs = batteryMetric$UidHealthProto.getRealtimeScreenOffBatteryMs() - batteryMetric$UidHealthProto2.getRealtimeScreenOffBatteryMs();
                if (realtimeScreenOffBatteryMs != 0) {
                    newBuilder.setRealtimeScreenOffBatteryMs(realtimeScreenOffBatteryMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasUptimeScreenOffBatteryMs()) {
                long uptimeScreenOffBatteryMs = batteryMetric$UidHealthProto.getUptimeScreenOffBatteryMs() - batteryMetric$UidHealthProto2.getUptimeScreenOffBatteryMs();
                if (uptimeScreenOffBatteryMs != 0) {
                    newBuilder.setUptimeScreenOffBatteryMs(uptimeScreenOffBatteryMs);
                }
            }
            newBuilder.addAllWakelocksFull(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getWakelocksFullList(), batteryMetric$UidHealthProto2.getWakelocksFullList()));
            newBuilder.addAllWakelocksPartial(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getWakelocksPartialList(), batteryMetric$UidHealthProto2.getWakelocksPartialList()));
            newBuilder.addAllWakelocksWindow(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getWakelocksWindowList(), batteryMetric$UidHealthProto2.getWakelocksWindowList()));
            newBuilder.addAllWakelocksDraw(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getWakelocksDrawList(), batteryMetric$UidHealthProto2.getWakelocksDrawList()));
            newBuilder.addAllSyncs(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getSyncsList(), batteryMetric$UidHealthProto2.getSyncsList()));
            newBuilder.addAllJobs(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getJobsList(), batteryMetric$UidHealthProto2.getJobsList()));
            BatteryMetric$Timer subtract = subtract(batteryMetric$UidHealthProto.hasGpsSensor() ? batteryMetric$UidHealthProto.getGpsSensor() : null, batteryMetric$UidHealthProto2.hasGpsSensor() ? batteryMetric$UidHealthProto2.getGpsSensor() : null);
            if (subtract != null) {
                newBuilder.setGpsSensor(subtract);
            }
            newBuilder.addAllSensors(TimerOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getSensorsList(), batteryMetric$UidHealthProto2.getSensorsList()));
            newBuilder.addAllStatsProcesses(ProcessHealthProtoOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getStatsProcessesList(), batteryMetric$UidHealthProto2.getStatsProcessesList()));
            newBuilder.addAllStatsPackages(PackageHealthProtoOps.INSTANCE.subtract(batteryMetric$UidHealthProto.getStatsPackagesList(), batteryMetric$UidHealthProto2.getStatsPackagesList()));
            if (batteryMetric$UidHealthProto.hasWifiIdleMs()) {
                long wifiIdleMs = batteryMetric$UidHealthProto.getWifiIdleMs() - batteryMetric$UidHealthProto2.getWifiIdleMs();
                if (wifiIdleMs != 0) {
                    newBuilder.setWifiIdleMs(wifiIdleMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiRxMs()) {
                long wifiRxMs = batteryMetric$UidHealthProto.getWifiRxMs() - batteryMetric$UidHealthProto2.getWifiRxMs();
                if (wifiRxMs != 0) {
                    newBuilder.setWifiRxMs(wifiRxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiTxMs()) {
                long wifiTxMs = batteryMetric$UidHealthProto.getWifiTxMs() - batteryMetric$UidHealthProto2.getWifiTxMs();
                if (wifiTxMs != 0) {
                    newBuilder.setWifiTxMs(wifiTxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiPowerMams()) {
                long wifiPowerMams = batteryMetric$UidHealthProto.getWifiPowerMams() - batteryMetric$UidHealthProto2.getWifiPowerMams();
                if (wifiPowerMams != 0) {
                    newBuilder.setWifiPowerMams(wifiPowerMams);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothIdleMs()) {
                long bluetoothIdleMs = batteryMetric$UidHealthProto.getBluetoothIdleMs() - batteryMetric$UidHealthProto2.getBluetoothIdleMs();
                if (bluetoothIdleMs != 0) {
                    newBuilder.setBluetoothIdleMs(bluetoothIdleMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothRxMs()) {
                long bluetoothRxMs = batteryMetric$UidHealthProto.getBluetoothRxMs() - batteryMetric$UidHealthProto2.getBluetoothRxMs();
                if (bluetoothRxMs != 0) {
                    newBuilder.setBluetoothRxMs(bluetoothRxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothTxMs()) {
                long bluetoothTxMs = batteryMetric$UidHealthProto.getBluetoothTxMs() - batteryMetric$UidHealthProto2.getBluetoothTxMs();
                if (bluetoothTxMs != 0) {
                    newBuilder.setBluetoothTxMs(bluetoothTxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothPowerMams()) {
                long bluetoothPowerMams = batteryMetric$UidHealthProto.getBluetoothPowerMams() - batteryMetric$UidHealthProto2.getBluetoothPowerMams();
                if (bluetoothPowerMams != 0) {
                    newBuilder.setBluetoothPowerMams(bluetoothPowerMams);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileIdleMs()) {
                long mobileIdleMs = batteryMetric$UidHealthProto.getMobileIdleMs() - batteryMetric$UidHealthProto2.getMobileIdleMs();
                if (mobileIdleMs != 0) {
                    newBuilder.setMobileIdleMs(mobileIdleMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileRxMs()) {
                long mobileRxMs = batteryMetric$UidHealthProto.getMobileRxMs() - batteryMetric$UidHealthProto2.getMobileRxMs();
                if (mobileRxMs != 0) {
                    newBuilder.setMobileRxMs(mobileRxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileTxMs()) {
                long mobileTxMs = batteryMetric$UidHealthProto.getMobileTxMs() - batteryMetric$UidHealthProto2.getMobileTxMs();
                if (mobileTxMs != 0) {
                    newBuilder.setMobileTxMs(mobileTxMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobilePowerMams()) {
                long mobilePowerMams = batteryMetric$UidHealthProto.getMobilePowerMams() - batteryMetric$UidHealthProto2.getMobilePowerMams();
                if (mobilePowerMams != 0) {
                    newBuilder.setMobilePowerMams(mobilePowerMams);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiRunningMs()) {
                long wifiRunningMs = batteryMetric$UidHealthProto.getWifiRunningMs() - batteryMetric$UidHealthProto2.getWifiRunningMs();
                if (wifiRunningMs != 0) {
                    newBuilder.setWifiRunningMs(wifiRunningMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiFullLockMs()) {
                long wifiFullLockMs = batteryMetric$UidHealthProto.getWifiFullLockMs() - batteryMetric$UidHealthProto2.getWifiFullLockMs();
                if (wifiFullLockMs != 0) {
                    newBuilder.setWifiFullLockMs(wifiFullLockMs);
                }
            }
            BatteryMetric$Timer subtract2 = subtract(batteryMetric$UidHealthProto.hasWifiScan() ? batteryMetric$UidHealthProto.getWifiScan() : null, batteryMetric$UidHealthProto2.hasWifiScan() ? batteryMetric$UidHealthProto2.getWifiScan() : null);
            if (subtract2 != null) {
                newBuilder.setWifiScan(subtract2);
            }
            if (batteryMetric$UidHealthProto.hasWifiMulticastMs()) {
                long wifiMulticastMs = batteryMetric$UidHealthProto.getWifiMulticastMs() - batteryMetric$UidHealthProto2.getWifiMulticastMs();
                if (wifiMulticastMs != 0) {
                    newBuilder.setWifiMulticastMs(wifiMulticastMs);
                }
            }
            BatteryMetric$Timer subtract3 = subtract(batteryMetric$UidHealthProto.hasAudio() ? batteryMetric$UidHealthProto.getAudio() : null, batteryMetric$UidHealthProto2.hasAudio() ? batteryMetric$UidHealthProto2.getAudio() : null);
            if (subtract3 != null) {
                newBuilder.setAudio(subtract3);
            }
            BatteryMetric$Timer subtract4 = subtract(batteryMetric$UidHealthProto.hasVideo() ? batteryMetric$UidHealthProto.getVideo() : null, batteryMetric$UidHealthProto2.hasVideo() ? batteryMetric$UidHealthProto2.getVideo() : null);
            if (subtract4 != null) {
                newBuilder.setVideo(subtract4);
            }
            BatteryMetric$Timer subtract5 = subtract(batteryMetric$UidHealthProto.hasFlashlight() ? batteryMetric$UidHealthProto.getFlashlight() : null, batteryMetric$UidHealthProto2.hasFlashlight() ? batteryMetric$UidHealthProto2.getFlashlight() : null);
            if (subtract5 != null) {
                newBuilder.setFlashlight(subtract5);
            }
            BatteryMetric$Timer subtract6 = subtract(batteryMetric$UidHealthProto.hasCamera() ? batteryMetric$UidHealthProto.getCamera() : null, batteryMetric$UidHealthProto2.hasCamera() ? batteryMetric$UidHealthProto2.getCamera() : null);
            if (subtract6 != null) {
                newBuilder.setCamera(subtract6);
            }
            BatteryMetric$Timer subtract7 = subtract(batteryMetric$UidHealthProto.hasForegroundActivity() ? batteryMetric$UidHealthProto.getForegroundActivity() : null, batteryMetric$UidHealthProto2.hasForegroundActivity() ? batteryMetric$UidHealthProto2.getForegroundActivity() : null);
            if (subtract7 != null) {
                newBuilder.setForegroundActivity(subtract7);
            }
            BatteryMetric$Timer subtract8 = subtract(batteryMetric$UidHealthProto.hasBluetoothScan() ? batteryMetric$UidHealthProto.getBluetoothScan() : null, batteryMetric$UidHealthProto2.hasBluetoothScan() ? batteryMetric$UidHealthProto2.getBluetoothScan() : null);
            if (subtract8 != null) {
                newBuilder.setBluetoothScan(subtract8);
            }
            BatteryMetric$Timer subtract9 = subtract(batteryMetric$UidHealthProto.hasProcessStateTopMs() ? batteryMetric$UidHealthProto.getProcessStateTopMs() : null, batteryMetric$UidHealthProto2.hasProcessStateTopMs() ? batteryMetric$UidHealthProto2.getProcessStateTopMs() : null);
            if (subtract9 != null) {
                newBuilder.setProcessStateTopMs(subtract9);
            }
            BatteryMetric$Timer subtract10 = subtract(batteryMetric$UidHealthProto.hasProcessStateForegroundServiceMs() ? batteryMetric$UidHealthProto.getProcessStateForegroundServiceMs() : null, batteryMetric$UidHealthProto2.hasProcessStateForegroundServiceMs() ? batteryMetric$UidHealthProto2.getProcessStateForegroundServiceMs() : null);
            if (subtract10 != null) {
                newBuilder.setProcessStateForegroundServiceMs(subtract10);
            }
            BatteryMetric$Timer subtract11 = subtract(batteryMetric$UidHealthProto.hasProcessStateTopSleepingMs() ? batteryMetric$UidHealthProto.getProcessStateTopSleepingMs() : null, batteryMetric$UidHealthProto2.hasProcessStateTopSleepingMs() ? batteryMetric$UidHealthProto2.getProcessStateTopSleepingMs() : null);
            if (subtract11 != null) {
                newBuilder.setProcessStateTopSleepingMs(subtract11);
            }
            BatteryMetric$Timer subtract12 = subtract(batteryMetric$UidHealthProto.hasProcessStateForegroundMs() ? batteryMetric$UidHealthProto.getProcessStateForegroundMs() : null, batteryMetric$UidHealthProto2.hasProcessStateForegroundMs() ? batteryMetric$UidHealthProto2.getProcessStateForegroundMs() : null);
            if (subtract12 != null) {
                newBuilder.setProcessStateForegroundMs(subtract12);
            }
            BatteryMetric$Timer subtract13 = subtract(batteryMetric$UidHealthProto.hasProcessStateBackgroundMs() ? batteryMetric$UidHealthProto.getProcessStateBackgroundMs() : null, batteryMetric$UidHealthProto2.hasProcessStateBackgroundMs() ? batteryMetric$UidHealthProto2.getProcessStateBackgroundMs() : null);
            if (subtract13 != null) {
                newBuilder.setProcessStateBackgroundMs(subtract13);
            }
            BatteryMetric$Timer subtract14 = subtract(batteryMetric$UidHealthProto.hasProcessStateCachedMs() ? batteryMetric$UidHealthProto.getProcessStateCachedMs() : null, batteryMetric$UidHealthProto2.hasProcessStateCachedMs() ? batteryMetric$UidHealthProto2.getProcessStateCachedMs() : null);
            if (subtract14 != null) {
                newBuilder.setProcessStateCachedMs(subtract14);
            }
            BatteryMetric$Timer subtract15 = subtract(batteryMetric$UidHealthProto.hasVibrator() ? batteryMetric$UidHealthProto.getVibrator() : null, batteryMetric$UidHealthProto2.hasVibrator() ? batteryMetric$UidHealthProto2.getVibrator() : null);
            if (subtract15 != null) {
                newBuilder.setVibrator(subtract15);
            }
            if (batteryMetric$UidHealthProto.hasOtherUserActivityCount()) {
                long otherUserActivityCount = batteryMetric$UidHealthProto.getOtherUserActivityCount() - batteryMetric$UidHealthProto2.getOtherUserActivityCount();
                if (otherUserActivityCount != 0) {
                    newBuilder.setOtherUserActivityCount(otherUserActivityCount);
                }
            }
            if (batteryMetric$UidHealthProto.hasButtonUserActivityCount()) {
                long buttonUserActivityCount = batteryMetric$UidHealthProto.getButtonUserActivityCount() - batteryMetric$UidHealthProto2.getButtonUserActivityCount();
                if (buttonUserActivityCount != 0) {
                    newBuilder.setButtonUserActivityCount(buttonUserActivityCount);
                }
            }
            if (batteryMetric$UidHealthProto.hasTouchUserActivityCount()) {
                long touchUserActivityCount = batteryMetric$UidHealthProto.getTouchUserActivityCount() - batteryMetric$UidHealthProto2.getTouchUserActivityCount();
                if (touchUserActivityCount != 0) {
                    newBuilder.setTouchUserActivityCount(touchUserActivityCount);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileRxBytes()) {
                long mobileRxBytes = batteryMetric$UidHealthProto.getMobileRxBytes() - batteryMetric$UidHealthProto2.getMobileRxBytes();
                if (mobileRxBytes != 0) {
                    newBuilder.setMobileRxBytes(mobileRxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileTxBytes()) {
                long mobileTxBytes = batteryMetric$UidHealthProto.getMobileTxBytes() - batteryMetric$UidHealthProto2.getMobileTxBytes();
                if (mobileTxBytes != 0) {
                    newBuilder.setMobileTxBytes(mobileTxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiRxBytes()) {
                long wifiRxBytes = batteryMetric$UidHealthProto.getWifiRxBytes() - batteryMetric$UidHealthProto2.getWifiRxBytes();
                if (wifiRxBytes != 0) {
                    newBuilder.setWifiRxBytes(wifiRxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiTxBytes()) {
                long wifiTxBytes = batteryMetric$UidHealthProto.getWifiTxBytes() - batteryMetric$UidHealthProto2.getWifiTxBytes();
                if (wifiTxBytes != 0) {
                    newBuilder.setWifiTxBytes(wifiTxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothRxBytes()) {
                long bluetoothRxBytes = batteryMetric$UidHealthProto.getBluetoothRxBytes() - batteryMetric$UidHealthProto2.getBluetoothRxBytes();
                if (bluetoothRxBytes != 0) {
                    newBuilder.setBluetoothRxBytes(bluetoothRxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothTxBytes()) {
                long bluetoothTxBytes = batteryMetric$UidHealthProto.getBluetoothTxBytes() - batteryMetric$UidHealthProto2.getBluetoothTxBytes();
                if (bluetoothTxBytes != 0) {
                    newBuilder.setBluetoothTxBytes(bluetoothTxBytes);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileRxPackets()) {
                long mobileRxPackets = batteryMetric$UidHealthProto.getMobileRxPackets() - batteryMetric$UidHealthProto2.getMobileRxPackets();
                if (mobileRxPackets != 0) {
                    newBuilder.setMobileRxPackets(mobileRxPackets);
                }
            }
            if (batteryMetric$UidHealthProto.hasMobileTxPackets()) {
                long mobileTxPackets = batteryMetric$UidHealthProto.getMobileTxPackets() - batteryMetric$UidHealthProto2.getMobileTxPackets();
                if (mobileTxPackets != 0) {
                    newBuilder.setMobileTxPackets(mobileTxPackets);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiRxPackets()) {
                long wifiRxPackets = batteryMetric$UidHealthProto.getWifiRxPackets() - batteryMetric$UidHealthProto2.getWifiRxPackets();
                if (wifiRxPackets != 0) {
                    newBuilder.setWifiRxPackets(wifiRxPackets);
                }
            }
            if (batteryMetric$UidHealthProto.hasWifiTxPackets()) {
                long wifiTxPackets = batteryMetric$UidHealthProto.getWifiTxPackets() - batteryMetric$UidHealthProto2.getWifiTxPackets();
                if (wifiTxPackets != 0) {
                    newBuilder.setWifiTxPackets(wifiTxPackets);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothRxPackets()) {
                long bluetoothRxPackets = batteryMetric$UidHealthProto.getBluetoothRxPackets() - batteryMetric$UidHealthProto2.getBluetoothRxPackets();
                if (bluetoothRxPackets != 0) {
                    newBuilder.setBluetoothRxPackets(bluetoothRxPackets);
                }
            }
            if (batteryMetric$UidHealthProto.hasBluetoothTxPackets()) {
                long bluetoothTxPackets = batteryMetric$UidHealthProto.getBluetoothTxPackets() - batteryMetric$UidHealthProto2.getBluetoothTxPackets();
                if (bluetoothTxPackets != 0) {
                    newBuilder.setBluetoothTxPackets(bluetoothTxPackets);
                }
            }
            BatteryMetric$Timer subtract16 = subtract(batteryMetric$UidHealthProto.hasMobileRadioActive() ? batteryMetric$UidHealthProto.getMobileRadioActive() : null, batteryMetric$UidHealthProto2.hasMobileRadioActive() ? batteryMetric$UidHealthProto2.getMobileRadioActive() : null);
            if (subtract16 != null) {
                newBuilder.setMobileRadioActive(subtract16);
            }
            if (batteryMetric$UidHealthProto.hasUserCpuTimeMs()) {
                long userCpuTimeMs = batteryMetric$UidHealthProto.getUserCpuTimeMs() - batteryMetric$UidHealthProto2.getUserCpuTimeMs();
                if (userCpuTimeMs != 0) {
                    newBuilder.setUserCpuTimeMs(userCpuTimeMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasSystemCpuTimeMs()) {
                long systemCpuTimeMs = batteryMetric$UidHealthProto.getSystemCpuTimeMs() - batteryMetric$UidHealthProto2.getSystemCpuTimeMs();
                if (systemCpuTimeMs != 0) {
                    newBuilder.setSystemCpuTimeMs(systemCpuTimeMs);
                }
            }
            if (batteryMetric$UidHealthProto.hasCpuPowerMams()) {
                long cpuPowerMams = batteryMetric$UidHealthProto.getCpuPowerMams() - batteryMetric$UidHealthProto2.getCpuPowerMams();
                if (cpuPowerMams != 0) {
                    newBuilder.setCpuPowerMams(cpuPowerMams);
                }
            }
            BatteryMetric$UidHealthProto batteryMetric$UidHealthProto3 = (BatteryMetric$UidHealthProto) newBuilder.build();
            if (isZero(batteryMetric$UidHealthProto3)) {
                return null;
            }
            return batteryMetric$UidHealthProto3;
        }
        return batteryMetric$UidHealthProto;
    }
}
