package logs.proto.wireless.performance.mobile;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum NetworkMetric$RequestStatus implements Internal.EnumLite {
    REQUEST_STATUS_UNSPECIFIED(0),
    SUCCEEDED(1),
    FAILED(2),
    CANCELED(3);
    
    public static final int CANCELED_VALUE = 3;
    public static final int FAILED_VALUE = 2;
    public static final int REQUEST_STATUS_UNSPECIFIED_VALUE = 0;
    public static final int SUCCEEDED_VALUE = 1;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.NetworkMetric$RequestStatus.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkMetric$RequestStatus findValueByNumber(int i) {
            return NetworkMetric$RequestStatus.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RequestStatusVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new RequestStatusVerifier();

        private RequestStatusVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return NetworkMetric$RequestStatus.forNumber(i) != null;
        }
    }

    NetworkMetric$RequestStatus(int i) {
        this.value = i;
    }

    public static NetworkMetric$RequestStatus forNumber(int i) {
        switch (i) {
            case 0:
                return REQUEST_STATUS_UNSPECIFIED;
            case 1:
                return SUCCEEDED;
            case 2:
                return FAILED;
            case 3:
                return CANCELED;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return RequestStatusVerifier.INSTANCE;
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
