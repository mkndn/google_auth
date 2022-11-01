package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.MemoryMetric$DeviceStats;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryStats;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$MemoryUsageMetric extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int ACTIVITY_NAME_FIELD_NUMBER = 5;
    private static final MemoryMetric$MemoryUsageMetric DEFAULT_INSTANCE;
    public static final int DEVICE_STATS_FIELD_NUMBER = 4;
    public static final int MEMORY_EVENT_CODE_FIELD_NUMBER = 3;
    public static final int MEMORY_STATS_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 2;
    private int bitField0_;
    private MemoryMetric$DeviceStats deviceStats_;
    private int memoryEventCode_;
    private MemoryMetric$MemoryStats memoryStats_;
    private ProcessProto$ProcessStats processStats_;
    private byte memoizedIsInitialized = 2;
    private String activityName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum MemoryEventCode implements Internal.EnumLite {
        UNKNOWN(0),
        APP_CREATED(1),
        APP_TO_BACKGROUND(2),
        APP_TO_FOREGROUND(3),
        APP_IN_BACKGROUND_FOR_SECONDS(4),
        APP_IN_FOREGROUND_FOR_SECONDS(5),
        DELTA_OF_MEMORY(6);
        
        @Deprecated
        public static final int APP_CREATED_VALUE = 1;
        public static final int APP_IN_BACKGROUND_FOR_SECONDS_VALUE = 4;
        public static final int APP_IN_FOREGROUND_FOR_SECONDS_VALUE = 5;
        public static final int APP_TO_BACKGROUND_VALUE = 2;
        public static final int APP_TO_FOREGROUND_VALUE = 3;
        public static final int DELTA_OF_MEMORY_VALUE = 6;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.MemoryMetric.MemoryUsageMetric.MemoryEventCode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MemoryEventCode findValueByNumber(int i) {
                return MemoryEventCode.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class MemoryEventCodeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new MemoryEventCodeVerifier();

            private MemoryEventCodeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return MemoryEventCode.forNumber(i) != null;
            }
        }

        MemoryEventCode(int i) {
            this.value = i;
        }

        public static MemoryEventCode forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return APP_CREATED;
                case 2:
                    return APP_TO_BACKGROUND;
                case 3:
                    return APP_TO_FOREGROUND;
                case 4:
                    return APP_IN_BACKGROUND_FOR_SECONDS;
                case 5:
                    return APP_IN_FOREGROUND_FOR_SECONDS;
                case 6:
                    return DELTA_OF_MEMORY;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return MemoryEventCodeVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" number=").append(getNumber());
            return sb.append(" name=").append(name()).append('>').toString();
        }
    }

    static {
        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric = new MemoryMetric$MemoryUsageMetric();
        DEFAULT_INSTANCE = memoryMetric$MemoryUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$MemoryUsageMetric.class, memoryMetric$MemoryUsageMetric);
    }

    private MemoryMetric$MemoryUsageMetric() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActivityName(String str) {
        str.getClass();
        this.bitField0_ |= 16;
        this.activityName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceStats(MemoryMetric$DeviceStats memoryMetric$DeviceStats) {
        memoryMetric$DeviceStats.getClass();
        this.deviceStats_ = memoryMetric$DeviceStats;
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMemoryEventCode(MemoryEventCode memoryEventCode) {
        this.memoryEventCode_ = memoryEventCode.getNumber();
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMemoryStats(MemoryMetric$MemoryStats memoryMetric$MemoryStats) {
        memoryMetric$MemoryStats.getClass();
        this.memoryStats_ = memoryMetric$MemoryStats;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStats(ProcessProto$ProcessStats processProto$ProcessStats) {
        processProto$ProcessStats.getClass();
        this.processStats_ = processProto$ProcessStats;
        this.bitField0_ |= 2;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (MemoryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MemoryMetric$MemoryUsageMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဌ\u0002\u0004ဉ\u0003\u0005ဈ\u0004", new Object[]{"bitField0_", "memoryStats_", "processStats_", "memoryEventCode_", MemoryEventCode.internalGetVerifier(), "deviceStats_", "activityName_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$MemoryUsageMetric.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(MemoryMetric$MemoryUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder setActivityName(String str) {
            copyOnWrite();
            ((MemoryMetric$MemoryUsageMetric) this.instance).setActivityName(str);
            return this;
        }

        public Builder setDeviceStats(MemoryMetric$DeviceStats.Builder builder) {
            copyOnWrite();
            ((MemoryMetric$MemoryUsageMetric) this.instance).setDeviceStats((MemoryMetric$DeviceStats) builder.build());
            return this;
        }

        public Builder setMemoryEventCode(MemoryEventCode memoryEventCode) {
            copyOnWrite();
            ((MemoryMetric$MemoryUsageMetric) this.instance).setMemoryEventCode(memoryEventCode);
            return this;
        }

        public Builder setMemoryStats(MemoryMetric$MemoryStats.Builder builder) {
            copyOnWrite();
            ((MemoryMetric$MemoryUsageMetric) this.instance).setMemoryStats((MemoryMetric$MemoryStats) builder.build());
            return this;
        }

        public Builder setProcessStats(ProcessProto$ProcessStats.Builder builder) {
            copyOnWrite();
            ((MemoryMetric$MemoryUsageMetric) this.instance).setProcessStats((ProcessProto$ProcessStats) builder.build());
            return this;
        }

        /* synthetic */ Builder(MemoryMetric$1 memoryMetric$1) {
            this();
        }
    }
}
