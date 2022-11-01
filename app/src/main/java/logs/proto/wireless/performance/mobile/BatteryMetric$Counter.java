package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$Counter extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 1;
    private static final BatteryMetric$Counter DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int count_;
    private BatteryMetric$HashedString name_;

    static {
        BatteryMetric$Counter batteryMetric$Counter = new BatteryMetric$Counter();
        DEFAULT_INSTANCE = batteryMetric$Counter;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$Counter.class, batteryMetric$Counter);
    }

    private BatteryMetric$Counter() {
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
    public void setName(BatteryMetric$HashedString batteryMetric$HashedString) {
        batteryMetric$HashedString.getClass();
        this.name_ = batteryMetric$HashedString;
        this.bitField0_ |= 2;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$Counter();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "count_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$Counter.class) {
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

    public BatteryMetric$HashedString getName() {
        BatteryMetric$HashedString batteryMetric$HashedString = this.name_;
        return batteryMetric$HashedString == null ? BatteryMetric$HashedString.getDefaultInstance() : batteryMetric$HashedString;
    }

    public boolean hasCount() {
        return (this.bitField0_ & 1) != 0;
    }

    public boolean hasName() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$Counter.DEFAULT_INSTANCE);
        }

        public Builder setCount(int i) {
            copyOnWrite();
            ((BatteryMetric$Counter) this.instance).setCount(i);
            return this;
        }

        public Builder setName(BatteryMetric$HashedString batteryMetric$HashedString) {
            copyOnWrite();
            ((BatteryMetric$Counter) this.instance).setName(batteryMetric$HashedString);
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
