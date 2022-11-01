package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$CacheStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final NetworkMetric$CacheStats DEFAULT_INSTANCE;
    public static final int HIT_COUNT_FIELD_NUMBER = 2;
    public static final int LOOKUP_COUNT_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int hitCount_;
    private int lookupCount_;

    static {
        NetworkMetric$CacheStats networkMetric$CacheStats = new NetworkMetric$CacheStats();
        DEFAULT_INSTANCE = networkMetric$CacheStats;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$CacheStats.class, networkMetric$CacheStats);
    }

    private NetworkMetric$CacheStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHitCount(int i) {
        this.bitField0_ |= 2;
        this.hitCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLookupCount(int i) {
        this.bitField0_ |= 1;
        this.lookupCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (NetworkMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new NetworkMetric$CacheStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"bitField0_", "lookupCount_", "hitCount_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$CacheStats.class) {
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
            super(NetworkMetric$CacheStats.DEFAULT_INSTANCE);
        }

        public Builder setHitCount(int i) {
            copyOnWrite();
            ((NetworkMetric$CacheStats) this.instance).setHitCount(i);
            return this;
        }

        public Builder setLookupCount(int i) {
            copyOnWrite();
            ((NetworkMetric$CacheStats) this.instance).setLookupCount(i);
            return this;
        }

        /* synthetic */ Builder(NetworkMetric$1 networkMetric$1) {
            this();
        }
    }
}
