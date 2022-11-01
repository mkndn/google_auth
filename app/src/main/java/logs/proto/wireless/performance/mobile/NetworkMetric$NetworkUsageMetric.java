package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkEventUsage;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$NetworkUsageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final NetworkMetric$NetworkUsageMetric DEFAULT_INSTANCE;
    public static final int NETWORK_EVENT_USAGE_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 2;
    private byte memoizedIsInitialized = 2;
    private Internal.ProtobufList networkEventUsage_ = emptyProtobufList();

    static {
        NetworkMetric$NetworkUsageMetric networkMetric$NetworkUsageMetric = new NetworkMetric$NetworkUsageMetric();
        DEFAULT_INSTANCE = networkMetric$NetworkUsageMetric;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$NetworkUsageMetric.class, networkMetric$NetworkUsageMetric);
    }

    private NetworkMetric$NetworkUsageMetric() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addNetworkEventUsage(NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage) {
        networkMetric$NetworkEventUsage.getClass();
        ensureNetworkEventUsageIsMutable();
        this.networkEventUsage_.add(networkMetric$NetworkEventUsage);
    }

    private void ensureNetworkEventUsageIsMutable() {
        Internal.ProtobufList protobufList = this.networkEventUsage_;
        if (!protobufList.isModifiable()) {
            this.networkEventUsage_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static NetworkMetric$NetworkUsageMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNetworkEventUsage(int i, NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage) {
        networkMetric$NetworkEventUsage.getClass();
        ensureNetworkEventUsageIsMutable();
        this.networkEventUsage_.set(i, networkMetric$NetworkEventUsage);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (NetworkMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new NetworkMetric$NetworkUsageMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"networkEventUsage_", NetworkMetric$NetworkEventUsage.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$NetworkUsageMetric.class) {
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

    public NetworkMetric$NetworkEventUsage getNetworkEventUsage(int i) {
        return (NetworkMetric$NetworkEventUsage) this.networkEventUsage_.get(i);
    }

    public int getNetworkEventUsageCount() {
        return this.networkEventUsage_.size();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(NetworkMetric$NetworkUsageMetric.DEFAULT_INSTANCE);
        }

        public Builder addNetworkEventUsage(NetworkMetric$NetworkEventUsage.Builder builder) {
            copyOnWrite();
            ((NetworkMetric$NetworkUsageMetric) this.instance).addNetworkEventUsage((NetworkMetric$NetworkEventUsage) builder.build());
            return this;
        }

        public NetworkMetric$NetworkEventUsage getNetworkEventUsage(int i) {
            return ((NetworkMetric$NetworkUsageMetric) this.instance).getNetworkEventUsage(i);
        }

        public int getNetworkEventUsageCount() {
            return ((NetworkMetric$NetworkUsageMetric) this.instance).getNetworkEventUsageCount();
        }

        public Builder setNetworkEventUsage(int i, NetworkMetric$NetworkEventUsage.Builder builder) {
            copyOnWrite();
            ((NetworkMetric$NetworkUsageMetric) this.instance).setNetworkEventUsage(i, (NetworkMetric$NetworkEventUsage) builder.build());
            return this;
        }

        /* synthetic */ Builder(NetworkMetric$1 networkMetric$1) {
            this();
        }
    }
}
