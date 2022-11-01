package com.google.android.apps.authenticator.migration;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OfflineMigrationEnums {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Algorithm implements Internal.EnumLite {
        ALGORITHM_UNSPECIFIED(0),
        SHA1(1),
        SHA256(2),
        SHA512(3),
        MD5(4);
        
        public static final int ALGORITHM_UNSPECIFIED_VALUE = 0;
        public static final int MD5_VALUE = 4;
        public static final int SHA1_VALUE = 1;
        public static final int SHA256_VALUE = 2;
        public static final int SHA512_VALUE = 3;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.android.apps.authenticator.migration.OfflineMigrationEnums.Algorithm.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Algorithm findValueByNumber(int i) {
                return Algorithm.forNumber(i);
            }
        };
        private final int value;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class AlgorithmVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new AlgorithmVerifier();

            private AlgorithmVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Algorithm.forNumber(i) != null;
            }
        }

        Algorithm(int i) {
            this.value = i;
        }

        public static Algorithm forNumber(int i) {
            switch (i) {
                case 0:
                    return ALGORITHM_UNSPECIFIED;
                case 1:
                    return SHA1;
                case 2:
                    return SHA256;
                case 3:
                    return SHA512;
                case 4:
                    return MD5;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return AlgorithmVerifier.INSTANCE;
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum DigitCount implements Internal.EnumLite {
        DIGIT_COUNT_UNSPECIFIED(0),
        SIX(1),
        EIGHT(2);
        
        public static final int DIGIT_COUNT_UNSPECIFIED_VALUE = 0;
        public static final int EIGHT_VALUE = 2;
        public static final int SIX_VALUE = 1;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.android.apps.authenticator.migration.OfflineMigrationEnums.DigitCount.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public DigitCount findValueByNumber(int i) {
                return DigitCount.forNumber(i);
            }
        };
        private final int value;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class DigitCountVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new DigitCountVerifier();

            private DigitCountVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return DigitCount.forNumber(i) != null;
            }
        }

        DigitCount(int i) {
            this.value = i;
        }

        public static DigitCount forNumber(int i) {
            switch (i) {
                case 0:
                    return DIGIT_COUNT_UNSPECIFIED;
                case 1:
                    return SIX;
                case 2:
                    return EIGHT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return DigitCountVerifier.INSTANCE;
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum OtpType implements Internal.EnumLite {
        OTP_TYPE_UNSPECIFIED(0),
        HOTP(1),
        TOTP(2);
        
        public static final int HOTP_VALUE = 1;
        public static final int OTP_TYPE_UNSPECIFIED_VALUE = 0;
        public static final int TOTP_VALUE = 2;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.android.apps.authenticator.migration.OfflineMigrationEnums.OtpType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public OtpType findValueByNumber(int i) {
                return OtpType.forNumber(i);
            }
        };
        private final int value;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class OtpTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new OtpTypeVerifier();

            private OtpTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return OtpType.forNumber(i) != null;
            }
        }

        OtpType(int i) {
            this.value = i;
        }

        public static OtpType forNumber(int i) {
            switch (i) {
                case 0:
                    return OTP_TYPE_UNSPECIFIED;
                case 1:
                    return HOTP;
                case 2:
                    return TOTP;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap internalGetValueMap() {
            return internalValueMap;
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return OtpTypeVerifier.INSTANCE;
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

    private OfflineMigrationEnums() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
