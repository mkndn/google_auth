package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$ServiceHealthProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final BatteryMetric$ServiceHealthProto DEFAULT_INSTANCE;
    public static final int LAUNCH_COUNT_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int START_SERVICE_COUNT_FIELD_NUMBER = 1;
    private int bitField0_;
    private int launchCount_;
    private BatteryMetric$HashedString name_;
    private int startServiceCount_;

    static {
        BatteryMetric$ServiceHealthProto batteryMetric$ServiceHealthProto = new BatteryMetric$ServiceHealthProto();
        DEFAULT_INSTANCE = batteryMetric$ServiceHealthProto;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$ServiceHealthProto.class, batteryMetric$ServiceHealthProto);
    }

    private BatteryMetric$ServiceHealthProto() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLaunchCount(int i) {
        this.bitField0_ |= 2;
        this.launchCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(BatteryMetric$HashedString batteryMetric$HashedString) {
        batteryMetric$HashedString.getClass();
        this.name_ = batteryMetric$HashedString;
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartServiceCount(int i) {
        this.bitField0_ |= 1;
        this.startServiceCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$ServiceHealthProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "startServiceCount_", "launchCount_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$ServiceHealthProto.class) {
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

    public int getLaunchCount() {
        return this.launchCount_;
    }

    public BatteryMetric$HashedString getName() {
        BatteryMetric$HashedString batteryMetric$HashedString = this.name_;
        return batteryMetric$HashedString == null ? BatteryMetric$HashedString.getDefaultInstance() : batteryMetric$HashedString;
    }

    public int getStartServiceCount() {
        return this.startServiceCount_;
    }

    public boolean hasLaunchCount() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasStartServiceCount() {
        return (this.bitField0_ & 1) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$ServiceHealthProto.DEFAULT_INSTANCE);
        }

        public Builder setLaunchCount(int i) {
            copyOnWrite();
            ((BatteryMetric$ServiceHealthProto) this.instance).setLaunchCount(i);
            return this;
        }

        public Builder setName(BatteryMetric$HashedString batteryMetric$HashedString) {
            copyOnWrite();
            ((BatteryMetric$ServiceHealthProto) this.instance).setName(batteryMetric$HashedString);
            return this;
        }

        public Builder setStartServiceCount(int i) {
            copyOnWrite();
            ((BatteryMetric$ServiceHealthProto) this.instance).setStartServiceCount(i);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
