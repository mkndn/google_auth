package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CpuProfiling$DeviceMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int AFTER_STATE_FIELD_NUMBER = 2;
    public static final int BATTERY_DROP_PERCENT_FIELD_NUMBER = 3;
    public static final int BEFORE_STATE_FIELD_NUMBER = 1;
    private static final CpuProfiling$DeviceMetadata DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private CpuProfiling$DeviceState afterState_;
    private float batteryDropPercent_;
    private CpuProfiling$DeviceState beforeState_;
    private int bitField0_;

    static {
        CpuProfiling$DeviceMetadata cpuProfiling$DeviceMetadata = new CpuProfiling$DeviceMetadata();
        DEFAULT_INSTANCE = cpuProfiling$DeviceMetadata;
        GeneratedMessageLite.registerDefaultInstance(CpuProfiling$DeviceMetadata.class, cpuProfiling$DeviceMetadata);
    }

    private CpuProfiling$DeviceMetadata() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAfterState(CpuProfiling$DeviceState cpuProfiling$DeviceState) {
        cpuProfiling$DeviceState.getClass();
        this.afterState_ = cpuProfiling$DeviceState;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBatteryDropPercent(float f) {
        this.bitField0_ |= 4;
        this.batteryDropPercent_ = f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBeforeState(CpuProfiling$DeviceState cpuProfiling$DeviceState) {
        cpuProfiling$DeviceState.getClass();
        this.beforeState_ = cpuProfiling$DeviceState;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (CpuProfiling$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new CpuProfiling$DeviceMetadata();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ခ\u0002", new Object[]{"bitField0_", "beforeState_", "afterState_", "batteryDropPercent_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CpuProfiling$DeviceMetadata.class) {
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(CpuProfiling$DeviceMetadata.DEFAULT_INSTANCE);
        }

        public Builder setAfterState(CpuProfiling$DeviceState cpuProfiling$DeviceState) {
            copyOnWrite();
            ((CpuProfiling$DeviceMetadata) this.instance).setAfterState(cpuProfiling$DeviceState);
            return this;
        }

        public Builder setBatteryDropPercent(float f) {
            copyOnWrite();
            ((CpuProfiling$DeviceMetadata) this.instance).setBatteryDropPercent(f);
            return this;
        }

        public Builder setBeforeState(CpuProfiling$DeviceState cpuProfiling$DeviceState) {
            copyOnWrite();
            ((CpuProfiling$DeviceMetadata) this.instance).setBeforeState(cpuProfiling$DeviceState);
            return this;
        }

        /* synthetic */ Builder(CpuProfiling$1 cpuProfiling$1) {
            this();
        }
    }
}
