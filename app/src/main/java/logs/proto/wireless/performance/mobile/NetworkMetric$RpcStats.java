package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$RpcStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final NetworkMetric$RpcStats DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int RPC_STATUS_CODE_FIELD_NUMBER = 1;
    private int bitField0_;
    private int rpcStatusCode_;

    static {
        NetworkMetric$RpcStats networkMetric$RpcStats = new NetworkMetric$RpcStats();
        DEFAULT_INSTANCE = networkMetric$RpcStats;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$RpcStats.class, networkMetric$RpcStats);
    }

    private NetworkMetric$RpcStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRpcStatusCode(int i) {
        this.bitField0_ |= 1;
        this.rpcStatusCode_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (NetworkMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new NetworkMetric$RpcStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"bitField0_", "rpcStatusCode_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$RpcStats.class) {
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
            super(NetworkMetric$RpcStats.DEFAULT_INSTANCE);
        }

        public Builder setRpcStatusCode(int i) {
            copyOnWrite();
            ((NetworkMetric$RpcStats) this.instance).setRpcStatusCode(i);
            return this;
        }

        /* synthetic */ Builder(NetworkMetric$1 networkMetric$1) {
            this();
        }
    }
}
