package com.google.experiments.mobile.base;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum AndroidBacking implements Internal.EnumLite {
    UNKNOWN(0),
    SHARED_PREFS(1),
    CONTENT_PROVIDER(2),
    PROCESS_STABLE(6),
    TIKTOK(4),
    DEVICE_CONFIG(5),
    PROCESS_STABLE_CONTENT_PROVIDER(3);
    
    public static final int CONTENT_PROVIDER_VALUE = 2;
    public static final int DEVICE_CONFIG_VALUE = 5;
    @Deprecated
    public static final int PROCESS_STABLE_CONTENT_PROVIDER_VALUE = 3;
    public static final int PROCESS_STABLE_VALUE = 6;
    public static final int SHARED_PREFS_VALUE = 1;
    public static final int TIKTOK_VALUE = 4;
    public static final int UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.experiments.mobile.base.AndroidBacking.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public AndroidBacking findValueByNumber(int i) {
            return AndroidBacking.forNumber(i);
        }
    };
    private final int value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AndroidBackingVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new AndroidBackingVerifier();

        private AndroidBackingVerifier() {
        }

        @Override // com.google.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i) {
            return AndroidBacking.forNumber(i) != null;
        }
    }

    AndroidBacking(int i) {
        this.value = i;
    }

    public static AndroidBacking forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN;
            case 1:
                return SHARED_PREFS;
            case 2:
                return CONTENT_PROVIDER;
            case 3:
                return PROCESS_STABLE_CONTENT_PROVIDER;
            case 4:
                return TIKTOK;
            case 5:
                return DEVICE_CONFIG;
            case 6:
                return PROCESS_STABLE;
            default:
                return null;
        }
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return AndroidBackingVerifier.INSTANCE;
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
