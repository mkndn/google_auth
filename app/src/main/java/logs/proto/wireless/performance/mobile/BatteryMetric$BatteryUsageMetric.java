package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetric$BatteryUsageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BATTERY_STATS_DIFF_FIELD_NUMBER = 1;
    private static final BatteryMetric$BatteryUsageMetric DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private BatteryMetric$BatteryStatsDiff batteryStatsDiff_;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;

    static {
        BatteryMetric$BatteryUsageMetric batteryMetric$BatteryUsageMetric = new BatteryMetric$BatteryUsageMetric();
        DEFAULT_INSTANCE = batteryMetric$BatteryUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(BatteryMetric$BatteryUsageMetric.class, batteryMetric$BatteryUsageMetric);
    }

    private BatteryMetric$BatteryUsageMetric() {
    }

    public static BatteryMetric$BatteryUsageMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBatteryStatsDiff(BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff) {
        batteryMetric$BatteryStatsDiff.getClass();
        this.batteryStatsDiff_ = batteryMetric$BatteryStatsDiff;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (BatteryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BatteryMetric$BatteryUsageMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "batteryStatsDiff_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (BatteryMetric$BatteryUsageMetric.class) {
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

    public BatteryMetric$BatteryStatsDiff getBatteryStatsDiff() {
        BatteryMetric$BatteryStatsDiff batteryMetric$BatteryStatsDiff = this.batteryStatsDiff_;
        return batteryMetric$BatteryStatsDiff == null ? BatteryMetric$BatteryStatsDiff.getDefaultInstance() : batteryMetric$BatteryStatsDiff;
    }

    public boolean hasBatteryStatsDiff() {
        return (this.bitField0_ & 1) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(BatteryMetric$BatteryUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder setBatteryStatsDiff(BatteryMetric$BatteryStatsDiff.Builder builder) {
            copyOnWrite();
            ((BatteryMetric$BatteryUsageMetric) this.instance).setBatteryStatsDiff((BatteryMetric$BatteryStatsDiff) builder.build());
            return this;
        }

        /* synthetic */ Builder(BatteryMetric$1 batteryMetric$1) {
            this();
        }
    }
}
