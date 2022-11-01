package logs.proto.wireless.performance.mobile;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;
import logs.proto.wireless.performance.mobile.NetworkMetric$NetworkConnectionInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkMetric$NetworkEventUsage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CACHE_STATS_FIELD_NUMBER = 26;
    public static final int CONSTANT_RPC_PATH_FIELD_NUMBER = 25;
    public static final int CONTENT_TYPE_FIELD_NUMBER = 1;
    private static final NetworkMetric$NetworkEventUsage DEFAULT_INSTANCE;
    public static final int DEPRECATED_PRIMES_SCENARIO_FIELD_NUMBER = 23;
    public static final int DOMAIN_PATH_FIELD_NUMBER = 20;
    public static final int HASHED_RPC_PATH_FIELD_NUMBER = 21;
    public static final int HTTP_METHOD_FIELD_NUMBER = 18;
    public static final int HTTP_STATUS_CODE_FIELD_NUMBER = 5;
    public static final int METRIC_EXTENSION_FIELD_NUMBER = 12;
    public static final int NETWORKING_STACK_FIELD_NUMBER = 24;
    public static final int NETWORK_CONNECTION_INFO_FIELD_NUMBER = 11;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 10;
    public static final int QUIC_DETAILED_ERROR_CODE_FIELD_NUMBER = 19;
    public static final int REQUEST_FAILED_REASON_FIELD_NUMBER = 15;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_FIELD_NUMBER = 8;
    public static final int REQUEST_PATH_FIELD_NUMBER = 2;
    public static final int REQUEST_SIZE_BYTES_FIELD_NUMBER = 7;
    public static final int REQUEST_STATUS_FIELD_NUMBER = 14;
    public static final int RESPONSE_SIZE_BYTES_FIELD_NUMBER = 6;
    public static final int RETRY_COUNT_FIELD_NUMBER = 16;
    public static final int RPC_PATH_FIELD_NUMBER = 17;
    public static final int RPC_STATS_FIELD_NUMBER = 22;
    public static final int SERVER_DISTANCE_FIELD_NUMBER = 27;
    public static final int START_TIME_MS_FIELD_NUMBER = 13;
    public static final int SUB_REQUEST_FIELD_NUMBER = 9;
    public static final int TIME_TO_RESPONSE_DATA_FINISH_MS_FIELD_NUMBER = 3;
    public static final int TIME_TO_RESPONSE_HEADER_MS_FIELD_NUMBER = 4;
    private int bitField0_;
    private NetworkMetric$CacheStats cacheStats_;
    private int httpStatusCode_;
    private ExtensionMetric$MetricExtension metricExtension_;
    private NetworkMetric$NetworkConnectionInfo networkConnectionInfo_;
    private int networkingStack_;
    private ProcessProto$AndroidProcessStats processStats_;
    private int quicDetailedErrorCode_;
    private int requestFailedReason_;
    private int requestNegotiatedProtocol_;
    private int requestSizeBytes_;
    private int requestStatus_;
    private int responseSizeBytes_;
    private int retryCount_;
    private NetworkMetric$RpcStats rpcStats_;
    private int serverDistance_;
    private long startTimeMs_;
    private int timeToResponseDataFinishMs_;
    private int timeToResponseHeaderMs_;
    private int hashedRpcPathMemoizedSerializedSize = -1;
    private byte memoizedIsInitialized = 2;
    private String contentType_ = "";
    private String requestPath_ = "";
    private String constantRpcPath_ = "";
    private Internal.ProtobufList subRequest_ = emptyProtobufList();
    private String rpcPath_ = "";
    private Internal.LongList hashedRpcPath_ = emptyLongList();
    private String domainPath_ = "";
    private Internal.ProtobufList dEPRECATEDPrimesScenario_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum NetworkingStack implements Internal.EnumLite {
        UNKNOWN(0),
        CRONET(1);
        
        public static final int CRONET_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.NetworkMetric.NetworkEventUsage.NetworkingStack.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NetworkingStack findValueByNumber(int i) {
                return NetworkingStack.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class NetworkingStackVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new NetworkingStackVerifier();

            private NetworkingStackVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return NetworkingStack.forNumber(i) != null;
            }
        }

        NetworkingStack(int i) {
            this.value = i;
        }

        public static NetworkingStack forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return CRONET;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return NetworkingStackVerifier.INSTANCE;
        }

        @Override // com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        @Override // java.lang.Enum
        public String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" number=").append(getNumber());
            return sb.append(" name=").append(name()).append('>').toString();
        }
    }

    static {
        NetworkMetric$NetworkEventUsage networkMetric$NetworkEventUsage = new NetworkMetric$NetworkEventUsage();
        DEFAULT_INSTANCE = networkMetric$NetworkEventUsage;
        GeneratedMessageLite.registerDefaultInstance(NetworkMetric$NetworkEventUsage.class, networkMetric$NetworkEventUsage);
    }

    private NetworkMetric$NetworkEventUsage() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllHashedRpcPath(Iterable iterable) {
        ensureHashedRpcPathIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.hashedRpcPath_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashedRpcPath() {
        this.hashedRpcPath_ = emptyLongList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRpcPath() {
        this.bitField0_ &= -524289;
        this.rpcPath_ = getDefaultInstance().getRpcPath();
    }

    private void ensureHashedRpcPathIsMutable() {
        Internal.LongList longList = this.hashedRpcPath_;
        if (!longList.isModifiable()) {
            this.hashedRpcPath_ = GeneratedMessageLite.mutableCopy(longList);
        }
    }

    public static NetworkMetric$NetworkEventUsage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCacheStats(NetworkMetric$CacheStats networkMetric$CacheStats) {
        networkMetric$CacheStats.getClass();
        this.cacheStats_ = networkMetric$CacheStats;
        this.bitField0_ |= 8388608;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConstantRpcPath(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.constantRpcPath_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setContentType(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.contentType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDomainPath(String str) {
        str.getClass();
        this.bitField0_ |= 2097152;
        this.domainPath_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHttpStatusCode(int i) {
        this.bitField0_ |= 32;
        this.httpStatusCode_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        extensionMetric$MetricExtension.getClass();
        this.metricExtension_ = extensionMetric$MetricExtension;
        this.bitField0_ |= 8192;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNetworkConnectionInfo(NetworkMetric$NetworkConnectionInfo networkMetric$NetworkConnectionInfo) {
        networkMetric$NetworkConnectionInfo.getClass();
        this.networkConnectionInfo_ = networkMetric$NetworkConnectionInfo;
        this.bitField0_ |= 2048;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNetworkingStack(NetworkingStack networkingStack) {
        this.networkingStack_ = networkingStack.getNumber();
        this.bitField0_ |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStats(ProcessProto$AndroidProcessStats processProto$AndroidProcessStats) {
        processProto$AndroidProcessStats.getClass();
        this.processStats_ = processProto$AndroidProcessStats;
        this.bitField0_ |= 512;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQuicDetailedErrorCode(int i) {
        this.bitField0_ |= 131072;
        this.quicDetailedErrorCode_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestFailedReason(NetworkMetric$RequestFailedReason networkMetric$RequestFailedReason) {
        this.requestFailedReason_ = networkMetric$RequestFailedReason.getNumber();
        this.bitField0_ |= 65536;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestNegotiatedProtocol(NetworkMetric$RequestNegotiatedProtocol networkMetric$RequestNegotiatedProtocol) {
        this.requestNegotiatedProtocol_ = networkMetric$RequestNegotiatedProtocol.getNumber();
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestPath(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.requestPath_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestSizeBytes(int i) {
        this.bitField0_ |= 128;
        this.requestSizeBytes_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestStatus(NetworkMetric$RequestStatus networkMetric$RequestStatus) {
        this.requestStatus_ = networkMetric$RequestStatus.getNumber();
        this.bitField0_ |= 32768;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResponseSizeBytes(int i) {
        this.bitField0_ |= 64;
        this.responseSizeBytes_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRetryCount(int i) {
        this.bitField0_ |= 262144;
        this.retryCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRpcPath(String str) {
        str.getClass();
        this.bitField0_ |= 524288;
        this.rpcPath_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRpcStats(NetworkMetric$RpcStats networkMetric$RpcStats) {
        networkMetric$RpcStats.getClass();
        this.rpcStats_ = networkMetric$RpcStats;
        this.bitField0_ |= AccessibilityEventCompat.TYPE_WINDOWS_CHANGED;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServerDistance(NetworkMetric$PeerDistance networkMetric$PeerDistance) {
        this.serverDistance_ = networkMetric$PeerDistance.getNumber();
        this.bitField0_ |= 4096;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartTimeMs(long j) {
        this.bitField0_ |= 16384;
        this.startTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeToResponseDataFinishMs(int i) {
        this.bitField0_ |= 8;
        this.timeToResponseDataFinishMs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeToResponseHeaderMs(int i) {
        this.bitField0_ |= 16;
        this.timeToResponseHeaderMs_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (NetworkMetric$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new NetworkMetric$NetworkEventUsage();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0018\u0000\u0001\u0001\u001b\u0018\u0000\u0001\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003င\u0003\u0004င\u0004\u0005င\u0005\u0006ဋ\u0006\u0007ဋ\u0007\bဌ\b\nဉ\t\u000bဉ\u000b\fᐉ\r\rဂ\u000e\u000eဌ\u000f\u000fဌ\u0010\u0010င\u0012\u0011ဈ\u0013\u0013င\u0011\u0014ဈ\u0015\u0015(\u0016ဉ\u0016\u0018ဌ\n\u0019ဈ\u0002\u001aဉ\u0017\u001bဌ\f", new Object[]{"bitField0_", "contentType_", "requestPath_", "timeToResponseDataFinishMs_", "timeToResponseHeaderMs_", "httpStatusCode_", "responseSizeBytes_", "requestSizeBytes_", "requestNegotiatedProtocol_", NetworkMetric$RequestNegotiatedProtocol.internalGetVerifier(), "processStats_", "networkConnectionInfo_", "metricExtension_", "startTimeMs_", "requestStatus_", NetworkMetric$RequestStatus.internalGetVerifier(), "requestFailedReason_", NetworkMetric$RequestFailedReason.internalGetVerifier(), "retryCount_", "rpcPath_", "quicDetailedErrorCode_", "domainPath_", "hashedRpcPath_", "rpcStats_", "networkingStack_", NetworkingStack.internalGetVerifier(), "constantRpcPath_", "cacheStats_", "serverDistance_", NetworkMetric$PeerDistance.internalGetVerifier()});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (NetworkMetric$NetworkEventUsage.class) {
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

    public String getRpcPath() {
        return this.rpcPath_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(NetworkMetric$NetworkEventUsage.DEFAULT_INSTANCE);
        }

        public Builder addAllHashedRpcPath(Iterable iterable) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).addAllHashedRpcPath(iterable);
            return this;
        }

        public Builder clearHashedRpcPath() {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).clearHashedRpcPath();
            return this;
        }

        public Builder clearRpcPath() {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).clearRpcPath();
            return this;
        }

        public String getRpcPath() {
            return ((NetworkMetric$NetworkEventUsage) this.instance).getRpcPath();
        }

        public Builder setCacheStats(NetworkMetric$CacheStats networkMetric$CacheStats) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setCacheStats(networkMetric$CacheStats);
            return this;
        }

        public Builder setConstantRpcPath(String str) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setConstantRpcPath(str);
            return this;
        }

        public Builder setContentType(String str) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setContentType(str);
            return this;
        }

        public Builder setDomainPath(String str) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setDomainPath(str);
            return this;
        }

        public Builder setHttpStatusCode(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setHttpStatusCode(i);
            return this;
        }

        public Builder setMetricExtension(ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setMetricExtension(extensionMetric$MetricExtension);
            return this;
        }

        public Builder setNetworkConnectionInfo(NetworkMetric$NetworkConnectionInfo.Builder builder) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setNetworkConnectionInfo((NetworkMetric$NetworkConnectionInfo) builder.build());
            return this;
        }

        public Builder setNetworkingStack(NetworkingStack networkingStack) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setNetworkingStack(networkingStack);
            return this;
        }

        public Builder setProcessStats(ProcessProto$AndroidProcessStats processProto$AndroidProcessStats) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setProcessStats(processProto$AndroidProcessStats);
            return this;
        }

        public Builder setQuicDetailedErrorCode(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setQuicDetailedErrorCode(i);
            return this;
        }

        public Builder setRequestFailedReason(NetworkMetric$RequestFailedReason networkMetric$RequestFailedReason) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRequestFailedReason(networkMetric$RequestFailedReason);
            return this;
        }

        public Builder setRequestNegotiatedProtocol(NetworkMetric$RequestNegotiatedProtocol networkMetric$RequestNegotiatedProtocol) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRequestNegotiatedProtocol(networkMetric$RequestNegotiatedProtocol);
            return this;
        }

        public Builder setRequestPath(String str) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRequestPath(str);
            return this;
        }

        public Builder setRequestSizeBytes(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRequestSizeBytes(i);
            return this;
        }

        public Builder setRequestStatus(NetworkMetric$RequestStatus networkMetric$RequestStatus) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRequestStatus(networkMetric$RequestStatus);
            return this;
        }

        public Builder setResponseSizeBytes(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setResponseSizeBytes(i);
            return this;
        }

        public Builder setRetryCount(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRetryCount(i);
            return this;
        }

        public Builder setRpcPath(String str) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRpcPath(str);
            return this;
        }

        public Builder setRpcStats(NetworkMetric$RpcStats networkMetric$RpcStats) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setRpcStats(networkMetric$RpcStats);
            return this;
        }

        public Builder setServerDistance(NetworkMetric$PeerDistance networkMetric$PeerDistance) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setServerDistance(networkMetric$PeerDistance);
            return this;
        }

        public Builder setStartTimeMs(long j) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setStartTimeMs(j);
            return this;
        }

        public Builder setTimeToResponseDataFinishMs(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setTimeToResponseDataFinishMs(i);
            return this;
        }

        public Builder setTimeToResponseHeaderMs(int i) {
            copyOnWrite();
            ((NetworkMetric$NetworkEventUsage) this.instance).setTimeToResponseHeaderMs(i);
            return this;
        }

        /* synthetic */ Builder(NetworkMetric$1 networkMetric$1) {
            this();
        }
    }
}
