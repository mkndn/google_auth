package logs.proto.wireless.performance.mobile;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.apps.authenticator.testability.android.os.PowerManager;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryUsageMetric;
import logs.proto.wireless.performance.mobile.CpuProfiling$CpuProfilingMetric;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkUsageMetric;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$PrimesTrace;
import logs.proto.wireless.performance.mobile.SystemHealthProto$AccountableComponent;
import logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DeviceInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackageMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$SystemHealthMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ACCOUNTABLE_COMPONENT_FIELD_NUMBER = 21;
    public static final int AGGREGATED_METRICS_FIELD_NUMBER = 25;
    public static final int APPLICATION_EXIT_METRIC_FIELD_NUMBER = 32;
    public static final int APPLICATION_INFO_FIELD_NUMBER = 5;
    public static final int BATTERY_USAGE_METRIC_FIELD_NUMBER = 10;
    public static final int CLIENT_ERROR_LOGGING_METRICS_FIELD_NUMBER = 29;
    public static final int CONSTANT_EVENT_NAME_FIELD_NUMBER = 17;
    public static final int CPU_PROFILING_METRIC_FIELD_NUMBER = 27;
    public static final int CPU_USAGE_METRIC_FIELD_NUMBER = 18;
    public static final int CRASH_METRIC_FIELD_NUMBER = 7;
    public static final int CUSTOM_EVENT_NAME_FIELD_NUMBER = 3;
    public static final int DEBUG_LOGS_FIELD_NUMBER = 31;
    private static final SystemHealthProto$SystemHealthMetric DEFAULT_INSTANCE;
    public static final int DEPRECATED_PRIMES_SCENARIO_FIELD_NUMBER = 26;
    public static final int DEVICE_INFO_FIELD_NUMBER = 23;
    public static final int FRAME_RATE_METRIC_FIELD_NUMBER = 11;
    public static final int HASHED_CUSTOM_EVENT_NAME_FIELD_NUMBER = 2;
    public static final int INTERACTION_CONTEXT_FIELD_NUMBER = 34;
    public static final int JANK_METRIC_FIELD_NUMBER = 12;
    public static final int MEMORY_LEAK_METRIC_FIELD_NUMBER = 13;
    public static final int MEMORY_USAGE_METRIC_FIELD_NUMBER = 1;
    public static final int METRIC_EXTENSION_FIELD_NUMBER = 14;
    public static final int NETWORK_USAGE_METRIC_FIELD_NUMBER = 6;
    public static final int PACKAGE_METRIC_FIELD_NUMBER = 9;
    private static volatile Parser PARSER = null;
    public static final int PRIMES_HEAP_DUMP_FIELD_NUMBER = 22;
    public static final int PRIMES_SPANS_FIELD_NUMBER = 19;
    public static final int PRIMES_STATS_FIELD_NUMBER = 8;
    public static final int PRIMES_TRACE_FIELD_NUMBER = 16;
    public static final int SAMPLING_PARAMETERS_FIELD_NUMBER = 30;
    public static final int STALL_METRIC_FIELD_NUMBER = 33;
    public static final int STRICT_MODE_VIOLATION_FIELD_NUMBER = 28;
    public static final int TELEMETRY_METRICS_FIELD_NUMBER = 20;
    public static final int TIMER_METRIC_FIELD_NUMBER = 4;
    private SystemHealthProto$AccountableComponent accountableComponent_;
    private ApplicationExitMetric applicationExitMetric_;
    private SystemHealthProto$ApplicationInfo applicationInfo_;
    private BatteryMetric$BatteryUsageMetric batteryUsageMetric_;
    private int bitField0_;
    private SystemHealthProto$ClientErrorLoggingMetric clientErrorLoggingMetrics_;
    private CpuProfiling$CpuProfilingMetric cpuProfilingMetric_;
    private SystemHealthProto$CrashMetric crashMetric_;
    private SystemHealthProto$DebugLogs debugLogs_;
    private SystemHealthProto$DeviceInfo deviceInfo_;
    private long hashedCustomEventName_;
    private SystemHealthProto$JankMetric jankMetric_;
    private MemoryMetric$MemoryUsageMetric memoryUsageMetric_;
    private ExtensionMetric$MetricExtension metricExtension_;
    private NetworkMetric$NetworkUsageMetric networkUsageMetric_;
    private SystemHealthProto$PackageMetric packageMetric_;
    private SystemHealthProto$PrimesStats primesStats_;
    private PrimesTraceOuterClass$PrimesTrace primesTrace_;
    private SystemHealthProto$SamplingParameters samplingParameters_;
    private SystemHealthProto$TimerMetric timerMetric_;
    private byte memoizedIsInitialized = 2;
    private String customEventName_ = "";
    private String constantEventName_ = "";
    private Internal.ProtobufList telemetryMetrics_ = emptyProtobufList();
    private Internal.ProtobufList aggregatedMetrics_ = emptyProtobufList();
    private Internal.ProtobufList interactionContext_ = emptyProtobufList();

    static {
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = new SystemHealthProto$SystemHealthMetric();
        DEFAULT_INSTANCE = systemHealthProto$SystemHealthMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$SystemHealthMetric.class, systemHealthProto$SystemHealthMetric);
    }

    private SystemHealthProto$SystemHealthMetric() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllInteractionContext(Iterable iterable) {
        ensureInteractionContextIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.interactionContext_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConstantEventName() {
        this.bitField0_ &= -5;
        this.constantEventName_ = getDefaultInstance().getConstantEventName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCustomEventName() {
        this.bitField0_ &= -3;
        this.customEventName_ = getDefaultInstance().getCustomEventName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashedCustomEventName() {
        this.bitField0_ &= -2;
        this.hashedCustomEventName_ = 0L;
    }

    private void ensureInteractionContextIsMutable() {
        Internal.ProtobufList protobufList = this.interactionContext_;
        if (!protobufList.isModifiable()) {
            this.interactionContext_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static SystemHealthProto$SystemHealthMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAccountableComponent(SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent) {
        systemHealthProto$AccountableComponent.getClass();
        this.accountableComponent_ = systemHealthProto$AccountableComponent;
        this.bitField0_ |= PowerManager.ACQUIRE_CAUSES_WAKEUP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApplicationExitMetric(ApplicationExitMetric applicationExitMetric) {
        applicationExitMetric.getClass();
        this.applicationExitMetric_ = applicationExitMetric;
        this.bitField0_ |= 524288;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApplicationInfo(SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo) {
        systemHealthProto$ApplicationInfo.getClass();
        this.applicationInfo_ = systemHealthProto$ApplicationInfo;
        this.bitField0_ |= 16777216;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBatteryUsageMetric(BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric) {
        batteryMetric$BatteryUsageMetric.getClass();
        this.batteryUsageMetric_ = batteryMetric$BatteryUsageMetric;
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConstantEventName(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.constantEventName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCpuProfilingMetric(CpuProfiling$CpuProfilingMetric cpuProfiling$CpuProfilingMetric) {
        cpuProfiling$CpuProfilingMetric.getClass();
        this.cpuProfilingMetric_ = cpuProfiling$CpuProfilingMetric;
        this.bitField0_ |= 16384;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCrashMetric(SystemHealthProto$CrashMetric systemHealthProto$CrashMetric) {
        systemHealthProto$CrashMetric.getClass();
        this.crashMetric_ = systemHealthProto$CrashMetric;
        this.bitField0_ |= 64;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCustomEventName(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.customEventName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDebugLogs(SystemHealthProto$DebugLogs systemHealthProto$DebugLogs) {
        systemHealthProto$DebugLogs.getClass();
        this.debugLogs_ = systemHealthProto$DebugLogs;
        this.bitField0_ |= 8388608;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceInfo(SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo) {
        systemHealthProto$DeviceInfo.getClass();
        this.deviceInfo_ = systemHealthProto$DeviceInfo;
        this.bitField0_ |= 67108864;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashedCustomEventName(long j) {
        this.bitField0_ |= 1;
        this.hashedCustomEventName_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJankMetric(SystemHealthProto$JankMetric systemHealthProto$JankMetric) {
        systemHealthProto$JankMetric.getClass();
        this.jankMetric_ = systemHealthProto$JankMetric;
        this.bitField0_ |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMemoryUsageMetric(MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric) {
        memoryMetric$MemoryUsageMetric.getClass();
        this.memoryUsageMetric_ = memoryMetric$MemoryUsageMetric;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        extensionMetric$MetricExtension.getClass();
        this.metricExtension_ = extensionMetric$MetricExtension;
        this.bitField0_ |= 134217728;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNetworkUsageMetric(NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric) {
        networkMetric$NetworkUsageMetric.getClass();
        this.networkUsageMetric_ = networkMetric$NetworkUsageMetric;
        this.bitField0_ |= 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPackageMetric(SystemHealthProto$PackageMetric systemHealthProto$PackageMetric) {
        systemHealthProto$PackageMetric.getClass();
        this.packageMetric_ = systemHealthProto$PackageMetric;
        this.bitField0_ |= 128;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimesStats(SystemHealthProto$PrimesStats systemHealthProto$PrimesStats) {
        systemHealthProto$PrimesStats.getClass();
        this.primesStats_ = systemHealthProto$PrimesStats;
        this.bitField0_ |= 33554432;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimesTrace(PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace) {
        primesTraceOuterClass$PrimesTrace.getClass();
        this.primesTrace_ = primesTraceOuterClass$PrimesTrace;
        this.bitField0_ |= 4096;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSamplingParameters(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
        systemHealthProto$SamplingParameters.getClass();
        this.samplingParameters_ = systemHealthProto$SamplingParameters;
        this.bitField0_ |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$SystemHealthMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0016\u0000\u0001\u0001\"\u0016\u0000\u0001\b\u0001ᐉ\u0003\u0002စ\u0000\u0003ဈ\u0001\u0004ဉ\u0004\u0005ဉ\u0018\u0006ᐉ\u0005\u0007ᐉ\u0006\bᐉ\u0019\tဉ\u0007\nᐉ\b\fဉ\n\u000eᐉ\u001b\u0010ᐉ\f\u0011ဈ\u0002\u0015ဉ\u001c\u0017ဉ\u001a\u001bဉ\u000e\u001dᐉ\u0015\u001eဉ\u0016\u001fဉ\u0017 ဉ\u0013\"\u001b", new Object[]{"bitField0_", "memoryUsageMetric_", "hashedCustomEventName_", "customEventName_", "timerMetric_", "applicationInfo_", "networkUsageMetric_", "crashMetric_", "primesStats_", "packageMetric_", "batteryUsageMetric_", "jankMetric_", "metricExtension_", "primesTrace_", "constantEventName_", "accountableComponent_", "deviceInfo_", "cpuProfilingMetric_", "clientErrorLoggingMetrics_", "samplingParameters_", "debugLogs_", "applicationExitMetric_", "interactionContext_", InteractionContext.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$SystemHealthMetric.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return Byte.valueOf(this.memoizedIsInitialized);
            case 7:
                this.memoizedIsInitialized = obj == null ? (byte) 0 : (byte) 1;
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public SystemHealthProto$AccountableComponent getAccountableComponent() {
        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent = this.accountableComponent_;
        return systemHealthProto$AccountableComponent == null ? SystemHealthProto$AccountableComponent.getDefaultInstance() : systemHealthProto$AccountableComponent;
    }

    public BatteryMetric$BatteryUsageMetric getBatteryUsageMetric() {
        BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric = this.batteryUsageMetric_;
        return batteryMetric$BatteryUsageMetric == null ? BatteryMetric$BatteryUsageMetric.getDefaultInstance() : batteryMetric$BatteryUsageMetric;
    }

    public String getConstantEventName() {
        return this.constantEventName_;
    }

    public SystemHealthProto$CrashMetric getCrashMetric() {
        SystemHealthProto$CrashMetric systemHealthProto$CrashMetric = this.crashMetric_;
        return systemHealthProto$CrashMetric == null ? SystemHealthProto$CrashMetric.getDefaultInstance() : systemHealthProto$CrashMetric;
    }

    public String getCustomEventName() {
        return this.customEventName_;
    }

    public SystemHealthProto$JankMetric getJankMetric() {
        SystemHealthProto$JankMetric systemHealthProto$JankMetric = this.jankMetric_;
        return systemHealthProto$JankMetric == null ? SystemHealthProto$JankMetric.getDefaultInstance() : systemHealthProto$JankMetric;
    }

    public NetworkMetric$NetworkUsageMetric getNetworkUsageMetric() {
        NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = this.networkUsageMetric_;
        return networkMetric$NetworkUsageMetric == null ? NetworkMetric$NetworkUsageMetric.getDefaultInstance() : networkMetric$NetworkUsageMetric;
    }

    public SystemHealthProto$PackageMetric getPackageMetric() {
        SystemHealthProto$PackageMetric systemHealthProto$PackageMetric = this.packageMetric_;
        return systemHealthProto$PackageMetric == null ? SystemHealthProto$PackageMetric.getDefaultInstance() : systemHealthProto$PackageMetric;
    }

    public PrimesTraceOuterClass$PrimesTrace getPrimesTrace() {
        PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = this.primesTrace_;
        return primesTraceOuterClass$PrimesTrace == null ? PrimesTraceOuterClass$PrimesTrace.getDefaultInstance() : primesTraceOuterClass$PrimesTrace;
    }

    public boolean hasBatteryUsageMetric() {
        return (this.bitField0_ & 256) != 0;
    }

    public boolean hasCrashMetric() {
        return (this.bitField0_ & 64) != 0;
    }

    public boolean hasJankMetric() {
        return (this.bitField0_ & 1024) != 0;
    }

    public boolean hasMemoryUsageMetric() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasNetworkUsageMetric() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasPackageMetric() {
        return (this.bitField0_ & 128) != 0;
    }

    public boolean hasPrimesStats() {
        return (this.bitField0_ & 33554432) != 0;
    }

    public boolean hasPrimesTrace() {
        return (this.bitField0_ & 4096) != 0;
    }

    public boolean hasTimerMetric() {
        return (this.bitField0_ & 16) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE);
        }

        public Builder addAllInteractionContext(Iterable iterable) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).addAllInteractionContext(iterable);
            return this;
        }

        public Builder clearConstantEventName() {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).clearConstantEventName();
            return this;
        }

        public Builder clearCustomEventName() {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).clearCustomEventName();
            return this;
        }

        public Builder clearHashedCustomEventName() {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).clearHashedCustomEventName();
            return this;
        }

        public BatteryMetric$BatteryUsageMetric getBatteryUsageMetric() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getBatteryUsageMetric();
        }

        public String getConstantEventName() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getConstantEventName();
        }

        public SystemHealthProto$CrashMetric getCrashMetric() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getCrashMetric();
        }

        public String getCustomEventName() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getCustomEventName();
        }

        public NetworkMetric$NetworkUsageMetric getNetworkUsageMetric() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getNetworkUsageMetric();
        }

        public SystemHealthProto$PackageMetric getPackageMetric() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getPackageMetric();
        }

        public PrimesTraceOuterClass$PrimesTrace getPrimesTrace() {
            return ((SystemHealthProto$SystemHealthMetric) this.instance).getPrimesTrace();
        }

        public Builder setAccountableComponent(SystemHealthProto$AccountableComponent.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setAccountableComponent((SystemHealthProto$AccountableComponent) builder.build());
            return this;
        }

        public Builder setApplicationExitMetric(ApplicationExitMetric applicationExitMetric) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setApplicationExitMetric(applicationExitMetric);
            return this;
        }

        public Builder setApplicationInfo(SystemHealthProto$ApplicationInfo.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setApplicationInfo((SystemHealthProto$ApplicationInfo) builder.build());
            return this;
        }

        public Builder setBatteryUsageMetric(BatteryMetric$BatteryUsageMetric.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setBatteryUsageMetric((BatteryMetric$BatteryUsageMetric) builder.build());
            return this;
        }

        public Builder setConstantEventName(String str) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setConstantEventName(str);
            return this;
        }

        public Builder setCpuProfilingMetric(CpuProfiling$CpuProfilingMetric.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setCpuProfilingMetric((CpuProfiling$CpuProfilingMetric) builder.build());
            return this;
        }

        public Builder setCrashMetric(SystemHealthProto$CrashMetric systemHealthProto$CrashMetric) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setCrashMetric(systemHealthProto$CrashMetric);
            return this;
        }

        public Builder setCustomEventName(String str) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setCustomEventName(str);
            return this;
        }

        public Builder setDebugLogs(SystemHealthProto$DebugLogs systemHealthProto$DebugLogs) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setDebugLogs(systemHealthProto$DebugLogs);
            return this;
        }

        public Builder setDeviceInfo(SystemHealthProto$DeviceInfo.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setDeviceInfo((SystemHealthProto$DeviceInfo) builder.build());
            return this;
        }

        public Builder setHashedCustomEventName(long j) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setHashedCustomEventName(j);
            return this;
        }

        public Builder setJankMetric(SystemHealthProto$JankMetric systemHealthProto$JankMetric) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setJankMetric(systemHealthProto$JankMetric);
            return this;
        }

        public Builder setMemoryUsageMetric(MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setMemoryUsageMetric(memoryMetric$MemoryUsageMetric);
            return this;
        }

        public Builder setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setMetricExtension(extensionMetric$MetricExtension);
            return this;
        }

        public Builder setNetworkUsageMetric(NetworkMetric$NetworkUsageMetric.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setNetworkUsageMetric((NetworkMetric$NetworkUsageMetric) builder.build());
            return this;
        }

        public Builder setPackageMetric(SystemHealthProto$PackageMetric.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setPackageMetric((SystemHealthProto$PackageMetric) builder.build());
            return this;
        }

        public Builder setPrimesStats(SystemHealthProto$PrimesStats.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setPrimesStats((SystemHealthProto$PrimesStats) builder.build());
            return this;
        }

        public Builder setPrimesTrace(PrimesTraceOuterClass$PrimesTrace.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setPrimesTrace((PrimesTraceOuterClass$PrimesTrace) builder.build());
            return this;
        }

        public Builder setSamplingParameters(SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters) {
            copyOnWrite();
            ((SystemHealthProto$SystemHealthMetric) this.instance).setSamplingParameters(systemHealthProto$SamplingParameters);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
