package logs.proto.wireless.performance.mobile;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum NetworkMetric$RequestFailedReason implements Internal.EnumLite {
    REQUEST_FAILED_REASON_UNSPECIFIED(0),
    LISTENER_EXCEPTION_THROWN(1),
    HOSTNAME_NOT_RESOLVED(2),
    INTERNET_DISCONNECTED(3),
    NETWORK_CHANGED(4),
    TIMED_OUT(5),
    CONNECTION_CLOSED(6),
    CONNECTION_TIMED_OUT(7),
    CONNECTION_REFUSED(8),
    CONNECTION_RESET(9),
    ADDRESS_UNREACHABLE(10),
    QUIC_PROTOCOL_FAILED(11),
    OTHER(12);
    
    public static final int ADDRESS_UNREACHABLE_VALUE = 10;
    public static final int CONNECTION_CLOSED_VALUE = 6;
    public static final int CONNECTION_REFUSED_VALUE = 8;
    public static final int CONNECTION_RESET_VALUE = 9;
    public static final int CONNECTION_TIMED_OUT_VALUE = 7;
    public static final int HOSTNAME_NOT_RESOLVED_VALUE = 2;
    public static final int INTERNET_DISCONNECTED_VALUE = 3;
    public static final int LISTENER_EXCEPTION_THROWN_VALUE = 1;
    public static final int NETWORK_CHANGED_VALUE = 4;
    public static final int OTHER_VALUE = 12;
    public static final int QUIC_PROTOCOL_FAILED_VALUE = 11;
    public static final int REQUEST_FAILED_REASON_UNSPECIFIED_VALUE = 0;
    public static final int TIMED_OUT_VALUE = 5;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.NetworkMetric$RequestFailedReason.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkMetric$RequestFailedReason findValueByNumber(int i) {
            return NetworkMetric$RequestFailedReason.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RequestFailedReasonVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new RequestFailedReasonVerifier();

        private RequestFailedReasonVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return NetworkMetric$RequestFailedReason.forNumber(i) != null;
        }
    }

    NetworkMetric$RequestFailedReason(int i) {
        this.value = i;
    }

    public static NetworkMetric$RequestFailedReason forNumber(int i) {
        switch (i) {
            case 0:
                return REQUEST_FAILED_REASON_UNSPECIFIED;
            case 1:
                return LISTENER_EXCEPTION_THROWN;
            case 2:
                return HOSTNAME_NOT_RESOLVED;
            case 3:
                return INTERNET_DISCONNECTED;
            case 4:
                return NETWORK_CHANGED;
            case 5:
                return TIMED_OUT;
            case 6:
                return CONNECTION_CLOSED;
            case 7:
                return CONNECTION_TIMED_OUT;
            case 8:
                return CONNECTION_REFUSED;
            case 9:
                return CONNECTION_RESET;
            case 10:
                return ADDRESS_UNREACHABLE;
            case 11:
                return QUIC_PROTOCOL_FAILED;
            case 12:
                return OTHER;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return RequestFailedReasonVerifier.INSTANCE;
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
