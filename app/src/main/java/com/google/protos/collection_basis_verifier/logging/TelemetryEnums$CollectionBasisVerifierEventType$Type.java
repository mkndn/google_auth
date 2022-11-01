package com.google.protos.collection_basis_verifier.logging;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TelemetryEnums$CollectionBasisVerifierEventType$Type implements Internal.EnumLite {
    UNKNOWN_EVENT_TYPE(0),
    VERIFICATION_FAILURE_LOG(1),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_EVENT_TYPE_VALUE = 0;
    public static final int VERIFICATION_FAILURE_LOG_VALUE = 1;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.protos.collection_basis_verifier.logging.TelemetryEnums$CollectionBasisVerifierEventType$Type.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public TelemetryEnums$CollectionBasisVerifierEventType$Type findValueByNumber(int i) {
            return TelemetryEnums$CollectionBasisVerifierEventType$Type.forNumber(i);
        }
    };
    private final int value;

    TelemetryEnums$CollectionBasisVerifierEventType$Type(int i) {
        this.value = i;
    }

    public static TelemetryEnums$CollectionBasisVerifierEventType$Type forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_EVENT_TYPE;
            case 1:
                return VERIFICATION_FAILURE_LOG;
            default:
                return null;
        }
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
        if (this != UNRECOGNIZED) {
            sb.append(" number=").append(getNumber());
        }
        return sb.append(" name=").append(name()).append('>').toString();
    }
}
