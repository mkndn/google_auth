package com.google.protos.collection_basis_verifier.logging;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum VerificationFailureEnum$VerificationFailure implements Internal.EnumLite {
    VF_UNKNOWN(0),
    VF_COLLECTION_BASIS_REJECTED(1),
    VF_COLLECTION_BASIS_QUERY_FAILURE(2),
    VF_ANNOTATION_LOAD_FAILURE(3),
    VF_ANNOTATION_PARSE_FAILURE(4),
    VF_ANNOTATION_MISSING_FAILURE(5),
    VF_FIELD_NOT_ANNOTATED(6),
    VF_ANY_LOOKUP_FAILURE(7),
    VF_FIELD_TYPE_MISMATCH(8),
    VF_PROTOBUF_FORMAT_FAILURE(9),
    UNRECOGNIZED(-1);
    
    public static final int VF_ANNOTATION_LOAD_FAILURE_VALUE = 3;
    public static final int VF_ANNOTATION_MISSING_FAILURE_VALUE = 5;
    public static final int VF_ANNOTATION_PARSE_FAILURE_VALUE = 4;
    public static final int VF_ANY_LOOKUP_FAILURE_VALUE = 7;
    public static final int VF_COLLECTION_BASIS_QUERY_FAILURE_VALUE = 2;
    public static final int VF_COLLECTION_BASIS_REJECTED_VALUE = 1;
    public static final int VF_FIELD_NOT_ANNOTATED_VALUE = 6;
    public static final int VF_FIELD_TYPE_MISMATCH_VALUE = 8;
    public static final int VF_PROTOBUF_FORMAT_FAILURE_VALUE = 9;
    public static final int VF_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.protos.collection_basis_verifier.logging.VerificationFailureEnum$VerificationFailure.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public VerificationFailureEnum$VerificationFailure findValueByNumber(int i) {
            return VerificationFailureEnum$VerificationFailure.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class VerificationFailureVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new VerificationFailureVerifier();

        private VerificationFailureVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return VerificationFailureEnum$VerificationFailure.forNumber(i) != null;
        }
    }

    VerificationFailureEnum$VerificationFailure(int i) {
        this.value = i;
    }

    public static VerificationFailureEnum$VerificationFailure forNumber(int i) {
        switch (i) {
            case 0:
                return VF_UNKNOWN;
            case 1:
                return VF_COLLECTION_BASIS_REJECTED;
            case 2:
                return VF_COLLECTION_BASIS_QUERY_FAILURE;
            case 3:
                return VF_ANNOTATION_LOAD_FAILURE;
            case 4:
                return VF_ANNOTATION_PARSE_FAILURE;
            case 5:
                return VF_ANNOTATION_MISSING_FAILURE;
            case 6:
                return VF_FIELD_NOT_ANNOTATED;
            case 7:
                return VF_ANY_LOOKUP_FAILURE;
            case 8:
                return VF_FIELD_TYPE_MISMATCH;
            case 9:
                return VF_PROTOBUF_FORMAT_FAILURE;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return VerificationFailureVerifier.INSTANCE;
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
