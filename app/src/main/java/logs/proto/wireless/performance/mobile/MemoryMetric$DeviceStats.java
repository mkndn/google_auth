package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$DeviceStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final MemoryMetric$DeviceStats DEFAULT_INSTANCE;
    public static final int IS_SCREEN_ON_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int bitField0_;
    private boolean isScreenOn_;

    static {
        MemoryMetric$DeviceStats memoryMetric$DeviceStats = new MemoryMetric$DeviceStats();
        DEFAULT_INSTANCE = memoryMetric$DeviceStats;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$DeviceStats.class, memoryMetric$DeviceStats);
    }

    private MemoryMetric$DeviceStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsScreenOn(boolean z) {
        this.bitField0_ |= 1;
        this.isScreenOn_ = z;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (MemoryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MemoryMetric$DeviceStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဇ\u0000", new Object[]{"bitField0_", "isScreenOn_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$DeviceStats.class) {
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
            super(MemoryMetric$DeviceStats.DEFAULT_INSTANCE);
        }

        public Builder setIsScreenOn(boolean z) {
            copyOnWrite();
            ((MemoryMetric$DeviceStats) this.instance).setIsScreenOn(z);
            return this;
        }

        /* synthetic */ Builder(MemoryMetric$1 memoryMetric$1) {
            this();
        }
    }
}
