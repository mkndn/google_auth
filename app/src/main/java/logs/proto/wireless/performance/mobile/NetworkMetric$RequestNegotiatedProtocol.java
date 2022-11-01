package logs.proto.wireless.performance.mobile;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum NetworkMetric$RequestNegotiatedProtocol implements Internal.EnumLite {
    REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN(0),
    REQUEST_NEGOTIATED_PROTOCOL_HTTP11(1),
    REQUEST_NEGOTIATED_PROTOCOL_SPDY2(2),
    REQUEST_NEGOTIATED_PROTOCOL_SPDY3(3),
    REQUEST_NEGOTIATED_PROTOCOL_SPDY31(4),
    REQUEST_NEGOTIATED_PROTOCOL_SPDY4(5),
    REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3(6),
    REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43(7);
    
    public static final int REQUEST_NEGOTIATED_PROTOCOL_HTTP11_VALUE = 1;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43_VALUE = 7;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3_VALUE = 6;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY2_VALUE = 2;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY31_VALUE = 4;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY3_VALUE = 3;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_SPDY4_VALUE = 5;
    public static final int REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.NetworkMetric$RequestNegotiatedProtocol.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkMetric$RequestNegotiatedProtocol findValueByNumber(int i) {
            return NetworkMetric$RequestNegotiatedProtocol.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RequestNegotiatedProtocolVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new RequestNegotiatedProtocolVerifier();

        private RequestNegotiatedProtocolVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return NetworkMetric$RequestNegotiatedProtocol.forNumber(i) != null;
        }
    }

    NetworkMetric$RequestNegotiatedProtocol(int i) {
        this.value = i;
    }

    public static NetworkMetric$RequestNegotiatedProtocol forNumber(int i) {
        switch (i) {
            case 0:
                return REQUEST_NEGOTIATED_PROTOCOL_UNKNOWN;
            case 1:
                return REQUEST_NEGOTIATED_PROTOCOL_HTTP11;
            case 2:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY2;
            case 3:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY3;
            case 4:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY31;
            case 5:
                return REQUEST_NEGOTIATED_PROTOCOL_SPDY4;
            case 6:
                return REQUEST_NEGOTIATED_PROTOCOL_QUIC1_SPDY3;
            case 7:
                return REQUEST_NEGOTIATED_PROTOCOL_HTTP2_QUIC43;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return RequestNegotiatedProtocolVerifier.INSTANCE;
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
