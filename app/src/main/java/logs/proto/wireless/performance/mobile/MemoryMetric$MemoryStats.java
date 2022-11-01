package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryMetric$MemoryStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANDROID_MEMORY_STATS_FIELD_NUMBER = 1;
    private static final MemoryMetric$MemoryStats DEFAULT_INSTANCE;
    public static final int IOS_MEMORY_STATS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private MemoryMetric$AndroidMemoryStats androidMemoryStats_;
    private int bitField0_;

    static {
        MemoryMetric$MemoryStats memoryMetric$MemoryStats = new MemoryMetric$MemoryStats();
        DEFAULT_INSTANCE = memoryMetric$MemoryStats;
        GeneratedMessageLite.registerDefaultInstance(MemoryMetric$MemoryStats.class, memoryMetric$MemoryStats);
    }

    private MemoryMetric$MemoryStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAndroidMemoryStats(MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats) {
        memoryMetric$AndroidMemoryStats.getClass();
        this.androidMemoryStats_ = memoryMetric$AndroidMemoryStats;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (MemoryMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MemoryMetric$MemoryStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "androidMemoryStats_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (MemoryMetric$MemoryStats.class) {
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
            super(MemoryMetric$MemoryStats.DEFAULT_INSTANCE);
        }

        public Builder setAndroidMemoryStats(MemoryMetric$AndroidMemoryStats memoryMetric$AndroidMemoryStats) {
            copyOnWrite();
            ((MemoryMetric$MemoryStats) this.instance).setAndroidMemoryStats(memoryMetric$AndroidMemoryStats);
            return this;
        }

        /* synthetic */ Builder(MemoryMetric$1 memoryMetric$1) {
            this();
        }
    }
}
