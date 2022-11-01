package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$BatteryStatsDiff extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final BatteryMetric$BatteryStatsDiff DEFAULT_INSTANCE;
    public static final int DURATION_MS_FIELD_NUMBER = 3;
    public static final int ELAPED_REALTIME_MS_FIELD_NUMBER = 7;
    public static final int END_INFO_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int START_CONSTANT_EVENT_NAME_FIELD_NUMBER = 10;
    public static final int START_CUSTOM_EVENT_NAME_FIELD_NUMBER = 9;
    public static final int START_HASHED_CUSTOM_EVENT_NAME_FIELD_NUMBER = 8;
    public static final int START_INFO_FIELD_NUMBER = 1;
    public static final int START_METRIC_EXTENSION_FIELD_NUMBER = 11;
    public static final int SYNC_STATS_FIELD_NUMBER = 5;
    public static final int UID_HEALTH_PROTO_DIFF_FIELD_NUMBER = 6;
    public static final int WAKELOCK_STATS_FIELD_NUMBER = 4;
    private int bitField0_;
    private long durationMs_;
    private long elapedRealtimeMs_;
    private int endInfo_;
    private long startHashedCustomEventName_;
    private int startInfo_;
    private ExtensionMetric$MetricExtension startMetricExtension_;
    private BatteryMetric$UidHealthProto uidHealthProtoDiff_;
    private byte memoizedIsInitialized = 2;
    private String startCustomEventName_ = "";
    private String startConstantEventName_ = "";
    private Internal.ProtobufList wakelockStats_ = emptyProtobufList();
    private Internal.ProtobufList syncStats_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SampleInfo implements Internal.EnumLite {
        UNKNOWN(0),
        FOREGROUND_TO_BACKGROUND(1),
        BACKGROUND_TO_FOREGROUND(2),
        FOREGROUND_SERVICE_START(3),
        FOREGROUND_SERVICE_STOP(4),
        CUSTOM_MEASURE_START(5),
        CUSTOM_MEASURE_STOP(6);
        
        public static final int BACKGROUND_TO_FOREGROUND_VALUE = 2;
        public static final int CUSTOM_MEASURE_START_VALUE = 5;
        public static final int CUSTOM_MEASURE_STOP_VALUE = 6;
        public static final int FOREGROUND_SERVICE_START_VALUE = 3;
        public static final int FOREGROUND_SERVICE_STOP_VALUE = 4;
        public static final int FOREGROUND_TO_BACKGROUND_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.BatteryMetric.BatteryStatsDiff.SampleInfo.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SampleInfo findValueByNumber(int i) {
                return SampleInfo.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SampleInfoVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SampleInfoVerifier();

            private SampleInfoVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return SampleInfo.forNumber(i) != null;
            }
        }

        SampleInfo(int i) {
            this.value = i;
        }

        public static SampleInfo forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return FOREGROUND_TO_BACKGROUND;
                case 2:
                    return BACKGROUND_TO_FOREGROUND;
                case 3:
                    return FOREGROUND_SERVICE_START;
                case 4:
                    return FOREGROUND_SERVICE_STOP;
                case 5:
                    return CUSTOM_MEASURE_START;
                case 6:
                    return CUSTOM_MEASURE_STOP;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SampleInfoVerifier.INSTANCE;
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
        BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = new BatteryMetric$BatteryStatsDiff();
        DEFAULT_INSTANCE = batteryMetric$BatteryStatsDiff;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$BatteryStatsDiff.class, batteryMetric$BatteryStatsDiff);
    }

    private BatteryMetric$BatteryStatsDiff() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStartCustomEventName() {
        this.bitField0_ &= -5;
        this.startCustomEventName_ = getDefaultInstance().getStartCustomEventName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStartHashedCustomEventName() {
        this.bitField0_ &= -3;
        this.startHashedCustomEventName_ = 0L;
    }

    public static BatteryMetric$BatteryStatsDiff getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDurationMs(long j) {
        this.bitField0_ |= 64;
        this.durationMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setElapedRealtimeMs(long j) {
        this.bitField0_ |= 256;
        this.elapedRealtimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEndInfo(SampleInfo sampleInfo) {
        this.endInfo_ = sampleInfo.getNumber();
        this.bitField0_ |= 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartConstantEventName(String str) {
        str.getClass();
        this.bitField0_ |= 8;
        this.startConstantEventName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartCustomEventName(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.startCustomEventName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartHashedCustomEventName(long j) {
        this.bitField0_ |= 2;
        this.startHashedCustomEventName_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartInfo(SampleInfo sampleInfo) {
        this.startInfo_ = sampleInfo.getNumber();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        extensionMetric$MetricExtension.getClass();
        this.startMetricExtension_ = extensionMetric$MetricExtension;
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUidHealthProtoDiff(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
        batteryMetric$UidHealthProto.getClass();
        this.uidHealthProtoDiff_ = batteryMetric$UidHealthProto;
        this.bitField0_ |= 128;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$BatteryStatsDiff();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\u000b\t\u0000\u0000\u0001\u0001ဌ\u0000\u0002ဌ\u0005\u0003ဂ\u0006\u0006ဉ\u0007\u0007ဂ\b\bစ\u0001\tဈ\u0002\nဈ\u0003\u000bᐉ\u0004", new Object[]{"bitField0_", "startInfo_", SampleInfo.internalGetVerifier(), "endInfo_", SampleInfo.internalGetVerifier(), "durationMs_", "uidHealthProtoDiff_", "elapedRealtimeMs_", "startHashedCustomEventName_", "startCustomEventName_", "startConstantEventName_", "startMetricExtension_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$BatteryStatsDiff.class) {
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

    public String getStartConstantEventName() {
        return this.startConstantEventName_;
    }

    public String getStartCustomEventName() {
        return this.startCustomEventName_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$BatteryStatsDiff.DEFAULT_INSTANCE);
        }

        public Builder clearStartCustomEventName() {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).clearStartCustomEventName();
            return this;
        }

        public Builder clearStartHashedCustomEventName() {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).clearStartHashedCustomEventName();
            return this;
        }

        public String getStartConstantEventName() {
            return ((BatteryMetric$BatteryStatsDiff) this.instance).getStartConstantEventName();
        }

        public String getStartCustomEventName() {
            return ((BatteryMetric$BatteryStatsDiff) this.instance).getStartCustomEventName();
        }

        public Builder setDurationMs(long j) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setDurationMs(j);
            return this;
        }

        public Builder setElapedRealtimeMs(long j) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setElapedRealtimeMs(j);
            return this;
        }

        public Builder setEndInfo(SampleInfo sampleInfo) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setEndInfo(sampleInfo);
            return this;
        }

        public Builder setStartConstantEventName(String str) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setStartConstantEventName(str);
            return this;
        }

        public Builder setStartCustomEventName(String str) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setStartCustomEventName(str);
            return this;
        }

        public Builder setStartHashedCustomEventName(long j) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setStartHashedCustomEventName(j);
            return this;
        }

        public Builder setStartInfo(SampleInfo sampleInfo) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setStartInfo(sampleInfo);
            return this;
        }

        public Builder setStartMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setStartMetricExtension(extensionMetric$MetricExtension);
            return this;
        }

        public Builder setUidHealthProtoDiff(BatteryMetric$UidHealthProto batteryMetric$UidHealthProto) {
            copyOnWrite();
            ((BatteryMetric$BatteryStatsDiff) this.instance).setUidHealthProtoDiff(batteryMetric$UidHealthProto);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
