package logs.proto.wireless.performance.mobile;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$UidHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int AUDIO_FIELD_NUMBER = 32;
    public static final int BLUETOOTH_IDLE_MS_FIELD_NUMBER = 20;
    public static final int BLUETOOTH_POWER_MAMS_FIELD_NUMBER = 23;
    public static final int BLUETOOTH_RX_BYTES_FIELD_NUMBER = 52;
    public static final int BLUETOOTH_RX_MS_FIELD_NUMBER = 21;
    public static final int BLUETOOTH_RX_PACKETS_FIELD_NUMBER = 58;
    public static final int BLUETOOTH_SCAN_FIELD_NUMBER = 37;
    public static final int BLUETOOTH_TX_BYTES_FIELD_NUMBER = 53;
    public static final int BLUETOOTH_TX_MS_FIELD_NUMBER = 22;
    public static final int BLUETOOTH_TX_PACKETS_FIELD_NUMBER = 59;
    public static final int BUTTON_USER_ACTIVITY_COUNT_FIELD_NUMBER = 46;
    public static final int CAMERA_FIELD_NUMBER = 35;
    public static final int CPU_POWER_MAMS_FIELD_NUMBER = 64;
    private static final BatteryMetric$UidHealthProto DEFAULT_INSTANCE;
    public static final int FLASHLIGHT_FIELD_NUMBER = 34;
    public static final int FOREGROUND_ACTIVITY_FIELD_NUMBER = 36;
    public static final int GPS_SENSOR_FIELD_NUMBER = 11;
    public static final int JOBS_FIELD_NUMBER = 10;
    public static final int MOBILE_IDLE_MS_FIELD_NUMBER = 24;
    public static final int MOBILE_POWER_MAMS_FIELD_NUMBER = 27;
    public static final int MOBILE_RADIO_ACTIVE_FIELD_NUMBER = 61;
    public static final int MOBILE_RX_BYTES_FIELD_NUMBER = 48;
    public static final int MOBILE_RX_MS_FIELD_NUMBER = 25;
    public static final int MOBILE_RX_PACKETS_FIELD_NUMBER = 54;
    public static final int MOBILE_TX_BYTES_FIELD_NUMBER = 49;
    public static final int MOBILE_TX_MS_FIELD_NUMBER = 26;
    public static final int MOBILE_TX_PACKETS_FIELD_NUMBER = 55;
    public static final int OTHER_USER_ACTIVITY_COUNT_FIELD_NUMBER = 45;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATE_BACKGROUND_MS_FIELD_NUMBER = 42;
    public static final int PROCESS_STATE_CACHED_MS_FIELD_NUMBER = 43;
    public static final int PROCESS_STATE_FOREGROUND_MS_FIELD_NUMBER = 41;
    public static final int PROCESS_STATE_FOREGROUND_SERVICE_MS_FIELD_NUMBER = 39;
    public static final int PROCESS_STATE_TOP_MS_FIELD_NUMBER = 38;
    public static final int PROCESS_STATE_TOP_SLEEPING_MS_FIELD_NUMBER = 40;
    public static final int REALTIME_BATTERY_MS_FIELD_NUMBER = 1;
    public static final int REALTIME_SCREEN_OFF_BATTERY_MS_FIELD_NUMBER = 3;
    public static final int SENSORS_FIELD_NUMBER = 12;
    public static final int STATS_PACKAGES_FIELD_NUMBER = 15;
    public static final int STATS_PIDS_FIELD_NUMBER = 13;
    public static final int STATS_PROCESSES_FIELD_NUMBER = 14;
    public static final int SYNCS_FIELD_NUMBER = 9;
    public static final int SYSTEM_CPU_TIME_MS_FIELD_NUMBER = 63;
    public static final int TOUCH_USER_ACTIVITY_COUNT_FIELD_NUMBER = 47;
    public static final int UPTIME_BATTERY_MS_FIELD_NUMBER = 2;
    public static final int UPTIME_SCREEN_OFF_BATTERY_MS_FIELD_NUMBER = 4;
    public static final int USER_CPU_TIME_MS_FIELD_NUMBER = 62;
    public static final int VIBRATOR_FIELD_NUMBER = 44;
    public static final int VIDEO_FIELD_NUMBER = 33;
    public static final int WAKELOCKS_DRAW_FIELD_NUMBER = 8;
    public static final int WAKELOCKS_FULL_FIELD_NUMBER = 5;
    public static final int WAKELOCKS_PARTIAL_FIELD_NUMBER = 6;
    public static final int WAKELOCKS_WINDOW_FIELD_NUMBER = 7;
    public static final int WIFI_FULL_LOCK_MS_FIELD_NUMBER = 29;
    public static final int WIFI_IDLE_MS_FIELD_NUMBER = 16;
    public static final int WIFI_MULTICAST_MS_FIELD_NUMBER = 31;
    public static final int WIFI_POWER_MAMS_FIELD_NUMBER = 19;
    public static final int WIFI_RUNNING_MS_FIELD_NUMBER = 28;
    public static final int WIFI_RX_BYTES_FIELD_NUMBER = 50;
    public static final int WIFI_RX_MS_FIELD_NUMBER = 17;
    public static final int WIFI_RX_PACKETS_FIELD_NUMBER = 56;
    public static final int WIFI_SCAN_FIELD_NUMBER = 30;
    public static final int WIFI_TX_BYTES_FIELD_NUMBER = 51;
    public static final int WIFI_TX_MS_FIELD_NUMBER = 18;
    public static final int WIFI_TX_PACKETS_FIELD_NUMBER = 57;
    private BatteryMetric$Timer audio_;
    private int bitField0_;
    private int bitField1_;
    private long bluetoothIdleMs_;
    private long bluetoothPowerMams_;
    private long bluetoothRxBytes_;
    private long bluetoothRxMs_;
    private long bluetoothRxPackets_;
    private BatteryMetric$Timer bluetoothScan_;
    private long bluetoothTxBytes_;
    private long bluetoothTxMs_;
    private long bluetoothTxPackets_;
    private long buttonUserActivityCount_;
    private BatteryMetric$Timer camera_;
    private long cpuPowerMams_;
    private BatteryMetric$Timer flashlight_;
    private BatteryMetric$Timer foregroundActivity_;
    private BatteryMetric$Timer gpsSensor_;
    private long mobileIdleMs_;
    private long mobilePowerMams_;
    private BatteryMetric$Timer mobileRadioActive_;
    private long mobileRxBytes_;
    private long mobileRxMs_;
    private long mobileRxPackets_;
    private long mobileTxBytes_;
    private long mobileTxMs_;
    private long mobileTxPackets_;
    private long otherUserActivityCount_;
    private BatteryMetric$Timer processStateBackgroundMs_;
    private BatteryMetric$Timer processStateCachedMs_;
    private BatteryMetric$Timer processStateForegroundMs_;
    private BatteryMetric$Timer processStateForegroundServiceMs_;
    private BatteryMetric$Timer processStateTopMs_;
    private BatteryMetric$Timer processStateTopSleepingMs_;
    private long realtimeBatteryMs_;
    private long realtimeScreenOffBatteryMs_;
    private long systemCpuTimeMs_;
    private long touchUserActivityCount_;
    private long uptimeBatteryMs_;
    private long uptimeScreenOffBatteryMs_;
    private long userCpuTimeMs_;
    private BatteryMetric$Timer vibrator_;
    private BatteryMetric$Timer video_;
    private long wifiFullLockMs_;
    private long wifiIdleMs_;
    private long wifiMulticastMs_;
    private long wifiPowerMams_;
    private long wifiRunningMs_;
    private long wifiRxBytes_;
    private long wifiRxMs_;
    private long wifiRxPackets_;
    private BatteryMetric$Timer wifiScan_;
    private long wifiTxBytes_;
    private long wifiTxMs_;
    private long wifiTxPackets_;
    private Internal.ProtobufList wakelocksFull_ = emptyProtobufList();
    private Internal.ProtobufList wakelocksPartial_ = emptyProtobufList();
    private Internal.ProtobufList wakelocksWindow_ = emptyProtobufList();
    private Internal.ProtobufList wakelocksDraw_ = emptyProtobufList();
    private Internal.ProtobufList syncs_ = emptyProtobufList();
    private Internal.ProtobufList jobs_ = emptyProtobufList();
    private Internal.ProtobufList sensors_ = emptyProtobufList();
    private Internal.ProtobufList statsPids_ = emptyProtobufList();
    private Internal.ProtobufList statsProcesses_ = emptyProtobufList();
    private Internal.ProtobufList statsPackages_ = emptyProtobufList();

    static {
        BatteryMetric$UidHealthProto batteryMetric$UidHealthProto = new BatteryMetric$UidHealthProto();
        DEFAULT_INSTANCE = batteryMetric$UidHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$UidHealthProto.class, batteryMetric$UidHealthProto);
    }

    private BatteryMetric$UidHealthProto() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllJobs(Iterable iterable) {
        ensureJobsIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.jobs_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllSensors(Iterable iterable) {
        ensureSensorsIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.sensors_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllStatsPackages(Iterable iterable) {
        ensureStatsPackagesIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.statsPackages_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllStatsProcesses(Iterable iterable) {
        ensureStatsProcessesIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.statsProcesses_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllSyncs(Iterable iterable) {
        ensureSyncsIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.syncs_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWakelocksDraw(Iterable iterable) {
        ensureWakelocksDrawIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.wakelocksDraw_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWakelocksFull(Iterable iterable) {
        ensureWakelocksFullIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.wakelocksFull_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWakelocksPartial(Iterable iterable) {
        ensureWakelocksPartialIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.wakelocksPartial_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllWakelocksWindow(Iterable iterable) {
        ensureWakelocksWindowIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.wakelocksWindow_);
    }

    private void ensureJobsIsMutable() {
        Internal.ProtobufList protobufList = this.jobs_;
        if (!protobufList.isModifiable()) {
            this.jobs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureSensorsIsMutable() {
        Internal.ProtobufList protobufList = this.sensors_;
        if (!protobufList.isModifiable()) {
            this.sensors_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureStatsPackagesIsMutable() {
        Internal.ProtobufList protobufList = this.statsPackages_;
        if (!protobufList.isModifiable()) {
            this.statsPackages_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureStatsProcessesIsMutable() {
        Internal.ProtobufList protobufList = this.statsProcesses_;
        if (!protobufList.isModifiable()) {
            this.statsProcesses_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureSyncsIsMutable() {
        Internal.ProtobufList protobufList = this.syncs_;
        if (!protobufList.isModifiable()) {
            this.syncs_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureWakelocksDrawIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksDraw_;
        if (!protobufList.isModifiable()) {
            this.wakelocksDraw_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureWakelocksFullIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksFull_;
        if (!protobufList.isModifiable()) {
            this.wakelocksFull_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureWakelocksPartialIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksPartial_;
        if (!protobufList.isModifiable()) {
            this.wakelocksPartial_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureWakelocksWindowIsMutable() {
        Internal.ProtobufList protobufList = this.wakelocksWindow_;
        if (!protobufList.isModifiable()) {
            this.wakelocksWindow_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static BatteryMetric$UidHealthProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudio(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.audio_ = batteryMetric$Timer;
        this.bitField0_ |= 2097152;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothIdleMs(long j) {
        this.bitField0_ |= 512;
        this.bluetoothIdleMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothPowerMams(long j) {
        this.bitField0_ |= 4096;
        this.bluetoothPowerMams_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothRxBytes(long j) {
        this.bitField1_ |= 512;
        this.bluetoothRxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothRxMs(long j) {
        this.bitField0_ |= 1024;
        this.bluetoothRxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothRxPackets(long j) {
        this.bitField1_ |= 32768;
        this.bluetoothRxPackets_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothScan(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.bluetoothScan_ = batteryMetric$Timer;
        this.bitField0_ |= 67108864;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothTxBytes(long j) {
        this.bitField1_ |= 1024;
        this.bluetoothTxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothTxMs(long j) {
        this.bitField0_ |= 2048;
        this.bluetoothTxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothTxPackets(long j) {
        this.bitField1_ |= 65536;
        this.bluetoothTxPackets_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setButtonUserActivityCount(long j) {
        this.bitField1_ |= 8;
        this.buttonUserActivityCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCamera(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.camera_ = batteryMetric$Timer;
        this.bitField0_ |= 16777216;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCpuPowerMams(long j) {
        this.bitField1_ |= 1048576;
        this.cpuPowerMams_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFlashlight(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.flashlight_ = batteryMetric$Timer;
        this.bitField0_ |= 8388608;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setForegroundActivity(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.foregroundActivity_ = batteryMetric$Timer;
        this.bitField0_ |= 33554432;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setGpsSensor(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.gpsSensor_ = batteryMetric$Timer;
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJobs(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureJobsIsMutable();
        this.jobs_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileIdleMs(long j) {
        this.bitField0_ |= 8192;
        this.mobileIdleMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobilePowerMams(long j) {
        this.bitField0_ |= 65536;
        this.mobilePowerMams_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileRadioActive(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.mobileRadioActive_ = batteryMetric$Timer;
        this.bitField1_ |= 131072;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileRxBytes(long j) {
        this.bitField1_ |= 32;
        this.mobileRxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileRxMs(long j) {
        this.bitField0_ |= 16384;
        this.mobileRxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileRxPackets(long j) {
        this.bitField1_ |= 2048;
        this.mobileRxPackets_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileTxBytes(long j) {
        this.bitField1_ |= 64;
        this.mobileTxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileTxMs(long j) {
        this.bitField0_ |= 32768;
        this.mobileTxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMobileTxPackets(long j) {
        this.bitField1_ |= 4096;
        this.mobileTxPackets_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOtherUserActivityCount(long j) {
        this.bitField1_ |= 4;
        this.otherUserActivityCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateBackgroundMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateBackgroundMs_ = batteryMetric$Timer;
        this.bitField0_ |= Integer.MIN_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateCachedMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateCachedMs_ = batteryMetric$Timer;
        this.bitField1_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateForegroundMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateForegroundMs_ = batteryMetric$Timer;
        this.bitField0_ |= 1073741824;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateForegroundServiceMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateForegroundServiceMs_ = batteryMetric$Timer;
        this.bitField0_ |= PowerManager.ACQUIRE_CAUSES_WAKEUP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateTopMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateTopMs_ = batteryMetric$Timer;
        this.bitField0_ |= 134217728;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStateTopSleepingMs(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.processStateTopSleepingMs_ = batteryMetric$Timer;
        this.bitField0_ |= PowerManager.ON_AFTER_RELEASE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRealtimeBatteryMs(long j) {
        this.bitField0_ |= 1;
        this.realtimeBatteryMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRealtimeScreenOffBatteryMs(long j) {
        this.bitField0_ |= 4;
        this.realtimeScreenOffBatteryMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSensors(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureSensorsIsMutable();
        this.sensors_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyncs(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureSyncsIsMutable();
        this.syncs_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemCpuTimeMs(long j) {
        this.bitField1_ |= 524288;
        this.systemCpuTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTouchUserActivityCount(long j) {
        this.bitField1_ |= 16;
        this.touchUserActivityCount_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUptimeBatteryMs(long j) {
        this.bitField0_ |= 2;
        this.uptimeBatteryMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUptimeScreenOffBatteryMs(long j) {
        this.bitField0_ |= 8;
        this.uptimeScreenOffBatteryMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserCpuTimeMs(long j) {
        this.bitField1_ |= 262144;
        this.userCpuTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVibrator(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.vibrator_ = batteryMetric$Timer;
        this.bitField1_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideo(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.video_ = batteryMetric$Timer;
        this.bitField0_ |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWakelocksDraw(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureWakelocksDrawIsMutable();
        this.wakelocksDraw_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWakelocksFull(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureWakelocksFullIsMutable();
        this.wakelocksFull_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWakelocksPartial(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureWakelocksPartialIsMutable();
        this.wakelocksPartial_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWakelocksWindow(int i, BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        ensureWakelocksWindowIsMutable();
        this.wakelocksWindow_.set(i, batteryMetric$Timer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiFullLockMs(long j) {
        this.bitField0_ |= 262144;
        this.wifiFullLockMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiIdleMs(long j) {
        this.bitField0_ |= 32;
        this.wifiIdleMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiMulticastMs(long j) {
        this.bitField0_ |= 1048576;
        this.wifiMulticastMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiPowerMams(long j) {
        this.bitField0_ |= 256;
        this.wifiPowerMams_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiRunningMs(long j) {
        this.bitField0_ |= 131072;
        this.wifiRunningMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiRxBytes(long j) {
        this.bitField1_ |= 128;
        this.wifiRxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiRxMs(long j) {
        this.bitField0_ |= 64;
        this.wifiRxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiRxPackets(long j) {
        this.bitField1_ |= 8192;
        this.wifiRxPackets_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiScan(BatteryMetric$Timer batteryMetric$Timer) {
        batteryMetric$Timer.getClass();
        this.wifiScan_ = batteryMetric$Timer;
        this.bitField0_ |= 524288;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiTxBytes(long j) {
        this.bitField1_ |= 256;
        this.wifiTxBytes_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiTxMs(long j) {
        this.bitField0_ |= 128;
        this.wifiTxMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWifiTxPackets(long j) {
        this.bitField1_ |= 16384;
        this.wifiTxPackets_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$UidHealthProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001?\u0000\u0002\u0001@?\u0000\n\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005\u001b\u0006\u001b\u0007\u001b\b\u001b\t\u001b\n\u001b\u000bဉ\u0004\f\u001b\r\u001b\u000e\u001b\u000f\u001b\u0010ဂ\u0005\u0011ဂ\u0006\u0012ဂ\u0007\u0013ဂ\b\u0014ဂ\t\u0015ဂ\n\u0016ဂ\u000b\u0017ဂ\f\u0018ဂ\r\u0019ဂ\u000e\u001aဂ\u000f\u001bဂ\u0010\u001cဂ\u0011\u001dဂ\u0012\u001eဉ\u0013\u001fဂ\u0014 ဉ\u0015!ဉ\u0016\"ဉ\u0017#ဉ\u0018$ဉ\u0019%ဉ\u001a&ဉ\u001b'ဉ\u001c(ဉ\u001d)ဉ\u001e*ဉ\u001f+ဉ ,ဉ!-ဂ\".ဂ#/ဂ$0ဂ%1ဂ&2ဂ'3ဂ(4ဂ)5ဂ*6ဂ+7ဂ,8ဂ-9ဂ.:ဂ/;ဂ0=ဉ1>ဂ2?ဂ3@ဂ4", new Object[]{"bitField0_", "bitField1_", "realtimeBatteryMs_", "uptimeBatteryMs_", "realtimeScreenOffBatteryMs_", "uptimeScreenOffBatteryMs_", "wakelocksFull_", BatteryMetric$Timer.class, "wakelocksPartial_", BatteryMetric$Timer.class, "wakelocksWindow_", BatteryMetric$Timer.class, "wakelocksDraw_", BatteryMetric$Timer.class, "syncs_", BatteryMetric$Timer.class, "jobs_", BatteryMetric$Timer.class, "gpsSensor_", "sensors_", BatteryMetric$Timer.class, "statsPids_", BatteryMetric$PidHealthProto.class, "statsProcesses_", BatteryMetric$ProcessHealthProto.class, "statsPackages_", BatteryMetric$PackageHealthProto.class, "wifiIdleMs_", "wifiRxMs_", "wifiTxMs_", "wifiPowerMams_", "bluetoothIdleMs_", "bluetoothRxMs_", "bluetoothTxMs_", "bluetoothPowerMams_", "mobileIdleMs_", "mobileRxMs_", "mobileTxMs_", "mobilePowerMams_", "wifiRunningMs_", "wifiFullLockMs_", "wifiScan_", "wifiMulticastMs_", "audio_", "video_", "flashlight_", "camera_", "foregroundActivity_", "bluetoothScan_", "processStateTopMs_", "processStateForegroundServiceMs_", "processStateTopSleepingMs_", "processStateForegroundMs_", "processStateBackgroundMs_", "processStateCachedMs_", "vibrator_", "otherUserActivityCount_", "buttonUserActivityCount_", "touchUserActivityCount_", "mobileRxBytes_", "mobileTxBytes_", "wifiRxBytes_", "wifiTxBytes_", "bluetoothRxBytes_", "bluetoothTxBytes_", "mobileRxPackets_", "mobileTxPackets_", "wifiRxPackets_", "wifiTxPackets_", "bluetoothRxPackets_", "bluetoothTxPackets_", "mobileRadioActive_", "userCpuTimeMs_", "systemCpuTimeMs_", "cpuPowerMams_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$UidHealthProto.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public BatteryMetric$Timer getAudio() {
        BatteryMetric$Timer batteryMetric$Timer = this.audio_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getBluetoothIdleMs() {
        return this.bluetoothIdleMs_;
    }

    public long getBluetoothPowerMams() {
        return this.bluetoothPowerMams_;
    }

    public long getBluetoothRxBytes() {
        return this.bluetoothRxBytes_;
    }

    public long getBluetoothRxMs() {
        return this.bluetoothRxMs_;
    }

    public long getBluetoothRxPackets() {
        return this.bluetoothRxPackets_;
    }

    public BatteryMetric$Timer getBluetoothScan() {
        BatteryMetric$Timer batteryMetric$Timer = this.bluetoothScan_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getBluetoothTxBytes() {
        return this.bluetoothTxBytes_;
    }

    public long getBluetoothTxMs() {
        return this.bluetoothTxMs_;
    }

    public long getBluetoothTxPackets() {
        return this.bluetoothTxPackets_;
    }

    public long getButtonUserActivityCount() {
        return this.buttonUserActivityCount_;
    }

    public BatteryMetric$Timer getCamera() {
        BatteryMetric$Timer batteryMetric$Timer = this.camera_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getCpuPowerMams() {
        return this.cpuPowerMams_;
    }

    public BatteryMetric$Timer getFlashlight() {
        BatteryMetric$Timer batteryMetric$Timer = this.flashlight_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getForegroundActivity() {
        BatteryMetric$Timer batteryMetric$Timer = this.foregroundActivity_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getGpsSensor() {
        BatteryMetric$Timer batteryMetric$Timer = this.gpsSensor_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getJobs(int i) {
        return (BatteryMetric$Timer) this.jobs_.get(i);
    }

    public int getJobsCount() {
        return this.jobs_.size();
    }

    public List getJobsList() {
        return this.jobs_;
    }

    public long getMobileIdleMs() {
        return this.mobileIdleMs_;
    }

    public long getMobilePowerMams() {
        return this.mobilePowerMams_;
    }

    public BatteryMetric$Timer getMobileRadioActive() {
        BatteryMetric$Timer batteryMetric$Timer = this.mobileRadioActive_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getMobileRxBytes() {
        return this.mobileRxBytes_;
    }

    public long getMobileRxMs() {
        return this.mobileRxMs_;
    }

    public long getMobileRxPackets() {
        return this.mobileRxPackets_;
    }

    public long getMobileTxBytes() {
        return this.mobileTxBytes_;
    }

    public long getMobileTxMs() {
        return this.mobileTxMs_;
    }

    public long getMobileTxPackets() {
        return this.mobileTxPackets_;
    }

    public long getOtherUserActivityCount() {
        return this.otherUserActivityCount_;
    }

    public BatteryMetric$Timer getProcessStateBackgroundMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateBackgroundMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getProcessStateCachedMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateCachedMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getProcessStateForegroundMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateForegroundMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getProcessStateForegroundServiceMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateForegroundServiceMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getProcessStateTopMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateTopMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getProcessStateTopSleepingMs() {
        BatteryMetric$Timer batteryMetric$Timer = this.processStateTopSleepingMs_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getRealtimeBatteryMs() {
        return this.realtimeBatteryMs_;
    }

    public long getRealtimeScreenOffBatteryMs() {
        return this.realtimeScreenOffBatteryMs_;
    }

    public BatteryMetric$Timer getSensors(int i) {
        return (BatteryMetric$Timer) this.sensors_.get(i);
    }

    public int getSensorsCount() {
        return this.sensors_.size();
    }

    public List getSensorsList() {
        return this.sensors_;
    }

    public int getStatsPackagesCount() {
        return this.statsPackages_.size();
    }

    public List getStatsPackagesList() {
        return this.statsPackages_;
    }

    public int getStatsPidsCount() {
        return this.statsPids_.size();
    }

    public int getStatsProcessesCount() {
        return this.statsProcesses_.size();
    }

    public List getStatsProcessesList() {
        return this.statsProcesses_;
    }

    public BatteryMetric$Timer getSyncs(int i) {
        return (BatteryMetric$Timer) this.syncs_.get(i);
    }

    public int getSyncsCount() {
        return this.syncs_.size();
    }

    public List getSyncsList() {
        return this.syncs_;
    }

    public long getSystemCpuTimeMs() {
        return this.systemCpuTimeMs_;
    }

    public long getTouchUserActivityCount() {
        return this.touchUserActivityCount_;
    }

    public long getUptimeBatteryMs() {
        return this.uptimeBatteryMs_;
    }

    public long getUptimeScreenOffBatteryMs() {
        return this.uptimeScreenOffBatteryMs_;
    }

    public long getUserCpuTimeMs() {
        return this.userCpuTimeMs_;
    }

    public BatteryMetric$Timer getVibrator() {
        BatteryMetric$Timer batteryMetric$Timer = this.vibrator_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getVideo() {
        BatteryMetric$Timer batteryMetric$Timer = this.video_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public BatteryMetric$Timer getWakelocksDraw(int i) {
        return (BatteryMetric$Timer) this.wakelocksDraw_.get(i);
    }

    public int getWakelocksDrawCount() {
        return this.wakelocksDraw_.size();
    }

    public List getWakelocksDrawList() {
        return this.wakelocksDraw_;
    }

    public BatteryMetric$Timer getWakelocksFull(int i) {
        return (BatteryMetric$Timer) this.wakelocksFull_.get(i);
    }

    public int getWakelocksFullCount() {
        return this.wakelocksFull_.size();
    }

    public List getWakelocksFullList() {
        return this.wakelocksFull_;
    }

    public BatteryMetric$Timer getWakelocksPartial(int i) {
        return (BatteryMetric$Timer) this.wakelocksPartial_.get(i);
    }

    public int getWakelocksPartialCount() {
        return this.wakelocksPartial_.size();
    }

    public List getWakelocksPartialList() {
        return this.wakelocksPartial_;
    }

    public BatteryMetric$Timer getWakelocksWindow(int i) {
        return (BatteryMetric$Timer) this.wakelocksWindow_.get(i);
    }

    public int getWakelocksWindowCount() {
        return this.wakelocksWindow_.size();
    }

    public List getWakelocksWindowList() {
        return this.wakelocksWindow_;
    }

    public long getWifiFullLockMs() {
        return this.wifiFullLockMs_;
    }

    public long getWifiIdleMs() {
        return this.wifiIdleMs_;
    }

    public long getWifiMulticastMs() {
        return this.wifiMulticastMs_;
    }

    public long getWifiPowerMams() {
        return this.wifiPowerMams_;
    }

    public long getWifiRunningMs() {
        return this.wifiRunningMs_;
    }

    public long getWifiRxBytes() {
        return this.wifiRxBytes_;
    }

    public long getWifiRxMs() {
        return this.wifiRxMs_;
    }

    public long getWifiRxPackets() {
        return this.wifiRxPackets_;
    }

    public BatteryMetric$Timer getWifiScan() {
        BatteryMetric$Timer batteryMetric$Timer = this.wifiScan_;
        return batteryMetric$Timer == null ? BatteryMetric$Timer.getDefaultInstance() : batteryMetric$Timer;
    }

    public long getWifiTxBytes() {
        return this.wifiTxBytes_;
    }

    public long getWifiTxMs() {
        return this.wifiTxMs_;
    }

    public long getWifiTxPackets() {
        return this.wifiTxPackets_;
    }

    public boolean hasAudio() {
        return (this.bitField0_ & 2097152) != 0;
    }

    public boolean hasBluetoothIdleMs() {
        return (this.bitField0_ & 512) != 0;
    }

    public boolean hasBluetoothPowerMams() {
        return (this.bitField0_ & 4096) != 0;
    }

    public boolean hasBluetoothRxBytes() {
        return (this.bitField1_ & 512) != 0;
    }

    public boolean hasBluetoothRxMs() {
        return (this.bitField0_ & 1024) != 0;
    }

    public boolean hasBluetoothRxPackets() {
        return (this.bitField1_ & 32768) != 0;
    }

    public boolean hasBluetoothScan() {
        return (this.bitField0_ & 67108864) != 0;
    }

    public boolean hasBluetoothTxBytes() {
        return (this.bitField1_ & 1024) != 0;
    }

    public boolean hasBluetoothTxMs() {
        return (this.bitField0_ & 2048) != 0;
    }

    public boolean hasBluetoothTxPackets() {
        return (this.bitField1_ & 65536) != 0;
    }

    public boolean hasButtonUserActivityCount() {
        return (this.bitField1_ & 8) != 0;
    }

    public boolean hasCamera() {
        return (this.bitField0_ & 16777216) != 0;
    }

    public boolean hasCpuPowerMams() {
        return (this.bitField1_ & 1048576) != 0;
    }

    public boolean hasFlashlight() {
        return (this.bitField0_ & 8388608) != 0;
    }

    public boolean hasForegroundActivity() {
        return (this.bitField0_ & 33554432) != 0;
    }

    public boolean hasGpsSensor() {
        return (this.bitField0_ & 16) != 0;
    }

    public boolean hasMobileIdleMs() {
        return (this.bitField0_ & 8192) != 0;
    }

    public boolean hasMobilePowerMams() {
        return (this.bitField0_ & 65536) != 0;
    }

    public boolean hasMobileRadioActive() {
        return (this.bitField1_ & 131072) != 0;
    }

    public boolean hasMobileRxBytes() {
        return (this.bitField1_ & 32) != 0;
    }

    public boolean hasMobileRxMs() {
        return (this.bitField0_ & 16384) != 0;
    }

    public boolean hasMobileRxPackets() {
        return (this.bitField1_ & 2048) != 0;
    }

    public boolean hasMobileTxBytes() {
        return (this.bitField1_ & 64) != 0;
    }

    public boolean hasMobileTxMs() {
        return (this.bitField0_ & 32768) != 0;
    }

    public boolean hasMobileTxPackets() {
        return (this.bitField1_ & 4096) != 0;
    }

    public boolean hasOtherUserActivityCount() {
        return (this.bitField1_ & 4) != 0;
    }

    public boolean hasProcessStateBackgroundMs() {
        return (this.bitField0_ & Integer.MIN_VALUE) != 0;
    }

    public boolean hasProcessStateCachedMs() {
        return (this.bitField1_ & 1) != 0;
    }

    public boolean hasProcessStateForegroundMs() {
        return (this.bitField0_ & 1073741824) != 0;
    }

    public boolean hasProcessStateForegroundServiceMs() {
        return (this.bitField0_ & PowerManager.ACQUIRE_CAUSES_WAKEUP) != 0;
    }

    public boolean hasProcessStateTopMs() {
        return (this.bitField0_ & 134217728) != 0;
    }

    public boolean hasProcessStateTopSleepingMs() {
        return (this.bitField0_ & PowerManager.ON_AFTER_RELEASE) != 0;
    }

    public boolean hasRealtimeBatteryMs() {
        return (this.bitField0_ & 1) != 0;
    }

    public boolean hasRealtimeScreenOffBatteryMs() {
        return (this.bitField0_ & 4) != 0;
    }

    public boolean hasSystemCpuTimeMs() {
        return (this.bitField1_ & 524288) != 0;
    }

    public boolean hasTouchUserActivityCount() {
        return (this.bitField1_ & 16) != 0;
    }

    public boolean hasUptimeBatteryMs() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasUptimeScreenOffBatteryMs() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasUserCpuTimeMs() {
        return (this.bitField1_ & 262144) != 0;
    }

    public boolean hasVibrator() {
        return (this.bitField1_ & 2) != 0;
    }

    public boolean hasVideo() {
        return (this.bitField0_ & AccessibilityEventCompat.TYPE_WINDOWS_CHANGED) != 0;
    }

    public boolean hasWifiFullLockMs() {
        return (this.bitField0_ & 262144) != 0;
    }

    public boolean hasWifiIdleMs() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasWifiMulticastMs() {
        return (this.bitField0_ & 1048576) != 0;
    }

    public boolean hasWifiPowerMams() {
        return (this.bitField0_ & 256) != 0;
    }

    public boolean hasWifiRunningMs() {
        return (this.bitField0_ & 131072) != 0;
    }

    public boolean hasWifiRxBytes() {
        return (this.bitField1_ & 128) != 0;
    }

    public boolean hasWifiRxMs() {
        return (this.bitField0_ & 64) != 0;
    }

    public boolean hasWifiRxPackets() {
        return (this.bitField1_ & 8192) != 0;
    }

    public boolean hasWifiScan() {
        return (this.bitField0_ & 524288) != 0;
    }

    public boolean hasWifiTxBytes() {
        return (this.bitField1_ & 256) != 0;
    }

    public boolean hasWifiTxMs() {
        return (this.bitField0_ & 128) != 0;
    }

    public boolean hasWifiTxPackets() {
        return (this.bitField1_ & 16384) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$UidHealthProto.DEFAULT_INSTANCE);
        }

        public Builder addAllJobs(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllJobs(iterable);
            return this;
        }

        public Builder addAllSensors(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllSensors(iterable);
            return this;
        }

        public Builder addAllStatsPackages(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllStatsPackages(iterable);
            return this;
        }

        public Builder addAllStatsProcesses(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllStatsProcesses(iterable);
            return this;
        }

        public Builder addAllSyncs(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllSyncs(iterable);
            return this;
        }

        public Builder addAllWakelocksDraw(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllWakelocksDraw(iterable);
            return this;
        }

        public Builder addAllWakelocksFull(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllWakelocksFull(iterable);
            return this;
        }

        public Builder addAllWakelocksPartial(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllWakelocksPartial(iterable);
            return this;
        }

        public Builder addAllWakelocksWindow(Iterable iterable) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).addAllWakelocksWindow(iterable);
            return this;
        }

        public BatteryMetric$Timer getJobs(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getJobs(i);
        }

        public int getJobsCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getJobsCount();
        }

        public List getJobsList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getJobsList());
        }

        public BatteryMetric$Timer getSensors(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getSensors(i);
        }

        public int getSensorsCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getSensorsCount();
        }

        public List getSensorsList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getSensorsList());
        }

        public BatteryMetric$Timer getSyncs(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getSyncs(i);
        }

        public int getSyncsCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getSyncsCount();
        }

        public List getSyncsList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getSyncsList());
        }

        public BatteryMetric$Timer getWakelocksDraw(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksDraw(i);
        }

        public int getWakelocksDrawCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksDrawCount();
        }

        public List getWakelocksDrawList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getWakelocksDrawList());
        }

        public BatteryMetric$Timer getWakelocksFull(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksFull(i);
        }

        public int getWakelocksFullCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksFullCount();
        }

        public List getWakelocksFullList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getWakelocksFullList());
        }

        public BatteryMetric$Timer getWakelocksPartial(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksPartial(i);
        }

        public int getWakelocksPartialCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksPartialCount();
        }

        public List getWakelocksPartialList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getWakelocksPartialList());
        }

        public BatteryMetric$Timer getWakelocksWindow(int i) {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksWindow(i);
        }

        public int getWakelocksWindowCount() {
            return ((BatteryMetric$UidHealthProto) this.instance).getWakelocksWindowCount();
        }

        public List getWakelocksWindowList() {
            return Collections.unmodifiableList(((BatteryMetric$UidHealthProto) this.instance).getWakelocksWindowList());
        }

        public Builder setAudio(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setAudio(batteryMetric$Timer);
            return this;
        }

        public Builder setBluetoothIdleMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothIdleMs(j);
            return this;
        }

        public Builder setBluetoothPowerMams(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothPowerMams(j);
            return this;
        }

        public Builder setBluetoothRxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothRxBytes(j);
            return this;
        }

        public Builder setBluetoothRxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothRxMs(j);
            return this;
        }

        public Builder setBluetoothRxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothRxPackets(j);
            return this;
        }

        public Builder setBluetoothScan(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothScan(batteryMetric$Timer);
            return this;
        }

        public Builder setBluetoothTxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothTxBytes(j);
            return this;
        }

        public Builder setBluetoothTxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothTxMs(j);
            return this;
        }

        public Builder setBluetoothTxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setBluetoothTxPackets(j);
            return this;
        }

        public Builder setButtonUserActivityCount(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setButtonUserActivityCount(j);
            return this;
        }

        public Builder setCamera(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setCamera(batteryMetric$Timer);
            return this;
        }

        public Builder setCpuPowerMams(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setCpuPowerMams(j);
            return this;
        }

        public Builder setFlashlight(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setFlashlight(batteryMetric$Timer);
            return this;
        }

        public Builder setForegroundActivity(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setForegroundActivity(batteryMetric$Timer);
            return this;
        }

        public Builder setGpsSensor(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setGpsSensor(batteryMetric$Timer);
            return this;
        }

        public Builder setJobs(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setJobs(i, batteryMetric$Timer);
            return this;
        }

        public Builder setMobileIdleMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileIdleMs(j);
            return this;
        }

        public Builder setMobilePowerMams(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobilePowerMams(j);
            return this;
        }

        public Builder setMobileRadioActive(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileRadioActive(batteryMetric$Timer);
            return this;
        }

        public Builder setMobileRxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileRxBytes(j);
            return this;
        }

        public Builder setMobileRxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileRxMs(j);
            return this;
        }

        public Builder setMobileRxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileRxPackets(j);
            return this;
        }

        public Builder setMobileTxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileTxBytes(j);
            return this;
        }

        public Builder setMobileTxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileTxMs(j);
            return this;
        }

        public Builder setMobileTxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setMobileTxPackets(j);
            return this;
        }

        public Builder setOtherUserActivityCount(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setOtherUserActivityCount(j);
            return this;
        }

        public Builder setProcessStateBackgroundMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateBackgroundMs(batteryMetric$Timer);
            return this;
        }

        public Builder setProcessStateCachedMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateCachedMs(batteryMetric$Timer);
            return this;
        }

        public Builder setProcessStateForegroundMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateForegroundMs(batteryMetric$Timer);
            return this;
        }

        public Builder setProcessStateForegroundServiceMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateForegroundServiceMs(batteryMetric$Timer);
            return this;
        }

        public Builder setProcessStateTopMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateTopMs(batteryMetric$Timer);
            return this;
        }

        public Builder setProcessStateTopSleepingMs(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setProcessStateTopSleepingMs(batteryMetric$Timer);
            return this;
        }

        public Builder setRealtimeBatteryMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setRealtimeBatteryMs(j);
            return this;
        }

        public Builder setRealtimeScreenOffBatteryMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setRealtimeScreenOffBatteryMs(j);
            return this;
        }

        public Builder setSensors(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setSensors(i, batteryMetric$Timer);
            return this;
        }

        public Builder setSyncs(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setSyncs(i, batteryMetric$Timer);
            return this;
        }

        public Builder setSystemCpuTimeMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setSystemCpuTimeMs(j);
            return this;
        }

        public Builder setTouchUserActivityCount(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setTouchUserActivityCount(j);
            return this;
        }

        public Builder setUptimeBatteryMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setUptimeBatteryMs(j);
            return this;
        }

        public Builder setUptimeScreenOffBatteryMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setUptimeScreenOffBatteryMs(j);
            return this;
        }

        public Builder setUserCpuTimeMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setUserCpuTimeMs(j);
            return this;
        }

        public Builder setVibrator(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setVibrator(batteryMetric$Timer);
            return this;
        }

        public Builder setVideo(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setVideo(batteryMetric$Timer);
            return this;
        }

        public Builder setWakelocksDraw(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWakelocksDraw(i, batteryMetric$Timer);
            return this;
        }

        public Builder setWakelocksFull(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWakelocksFull(i, batteryMetric$Timer);
            return this;
        }

        public Builder setWakelocksPartial(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWakelocksPartial(i, batteryMetric$Timer);
            return this;
        }

        public Builder setWakelocksWindow(int i, BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWakelocksWindow(i, batteryMetric$Timer);
            return this;
        }

        public Builder setWifiFullLockMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiFullLockMs(j);
            return this;
        }

        public Builder setWifiIdleMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiIdleMs(j);
            return this;
        }

        public Builder setWifiMulticastMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiMulticastMs(j);
            return this;
        }

        public Builder setWifiPowerMams(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiPowerMams(j);
            return this;
        }

        public Builder setWifiRunningMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiRunningMs(j);
            return this;
        }

        public Builder setWifiRxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiRxBytes(j);
            return this;
        }

        public Builder setWifiRxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiRxMs(j);
            return this;
        }

        public Builder setWifiRxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiRxPackets(j);
            return this;
        }

        public Builder setWifiScan(BatteryMetric$Timer batteryMetric$Timer) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiScan(batteryMetric$Timer);
            return this;
        }

        public Builder setWifiTxBytes(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiTxBytes(j);
            return this;
        }

        public Builder setWifiTxMs(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiTxMs(j);
            return this;
        }

        public Builder setWifiTxPackets(long j) {
            copyOnWrite();
            ((BatteryMetric$UidHealthProto) this.instance).setWifiTxPackets(j);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
