package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.BatteryMetric$HashedString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$Timer extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 1;
    private static final BatteryMetric$Timer DEFAULT_INSTANCE;
    public static final int DURATION_MS_FIELD_NUMBER = 2;
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int count_;
    private long durationMs_;
    private BatteryMetric$HashedString name_;

    static {
        BatteryMetric$Timer batteryMetric$Timer = new BatteryMetric$Timer();
        DEFAULT_INSTANCE = batteryMetric$Timer;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$Timer.class, batteryMetric$Timer);
    }

    private BatteryMetric$Timer() {
    }

    public static BatteryMetric$Timer getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCount(int i) {
        this.bitField0_ |= 1;
        this.count_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDurationMs(long j) {
        this.bitField0_ |= 2;
        this.durationMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(BatteryMetric$HashedString batteryMetric$HashedString) {
        batteryMetric$HashedString.getClass();
        this.name_ = batteryMetric$HashedString;
        this.bitField0_ |= 4;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$Timer();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001???\u0000\u0002???\u0001\u0003???\u0002", new Object[]{"bitField0_", "count_", "durationMs_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$Timer.class) {
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

    public int getCount() {
        return this.count_;
    }

    public long getDurationMs() {
        return this.durationMs_;
    }

    public BatteryMetric$HashedString getName() {
        BatteryMetric$HashedString batteryMetric$HashedString = this.name_;
        return batteryMetric$HashedString == null ? BatteryMetric$HashedString.getDefaultInstance() : batteryMetric$HashedString;
    }

    public boolean hasName() {
        return (this.bitField0_ & 4) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$Timer.DEFAULT_INSTANCE);
        }

        public int getCount() {
            return ((BatteryMetric$Timer) this.instance).getCount();
        }

        public long getDurationMs() {
            return ((BatteryMetric$Timer) this.instance).getDurationMs();
        }

        public Builder setCount(int i) {
            copyOnWrite();
            ((BatteryMetric$Timer) this.instance).setCount(i);
            return this;
        }

        public Builder setDurationMs(long j) {
            copyOnWrite();
            ((BatteryMetric$Timer) this.instance).setDurationMs(j);
            return this;
        }

        public Builder setName(BatteryMetric$HashedString.Builder builder) {
            copyOnWrite();
            ((BatteryMetric$Timer) this.instance).setName((BatteryMetric$HashedString) builder.build());
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }

        public Builder setName(BatteryMetric$HashedString batteryMetric$HashedString) {
            copyOnWrite();
            ((BatteryMetric$Timer) this.instance).setName(batteryMetric$HashedString);
            return this;
        }
    }
}
