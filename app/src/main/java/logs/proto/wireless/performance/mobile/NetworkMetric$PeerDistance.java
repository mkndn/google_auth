package logs.proto.wireless.performance.mobile;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum NetworkMetric$PeerDistance implements Internal.EnumLite {
    PEER_DISTANCE_UNKNOWN(0),
    PEER_DISTANCE_IN_PROCESS(1),
    PEER_DISTANCE_INTER_PROCESS(2);
    
    public static final int PEER_DISTANCE_INTER_PROCESS_VALUE = 2;
    public static final int PEER_DISTANCE_IN_PROCESS_VALUE = 1;
    public static final int PEER_DISTANCE_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.NetworkMetric$PeerDistance.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public NetworkMetric$PeerDistance findValueByNumber(int i) {
            return NetworkMetric$PeerDistance.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PeerDistanceVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new PeerDistanceVerifier();

        private PeerDistanceVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return NetworkMetric$PeerDistance.forNumber(i) != null;
        }
    }

    NetworkMetric$PeerDistance(int i) {
        this.value = i;
    }

    public static NetworkMetric$PeerDistance forNumber(int i) {
        switch (i) {
            case 0:
                return PEER_DISTANCE_UNKNOWN;
            case 1:
                return PEER_DISTANCE_IN_PROCESS;
            case 2:
                return PEER_DISTANCE_INTER_PROCESS;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return PeerDistanceVerifier.INSTANCE;
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
