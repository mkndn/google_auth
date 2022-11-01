package com.google.wireless.android.play.playlog.proto;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum ClientAnalytics$QosTierConfiguration$QosTier implements Internal.EnumLite {
    DEFAULT(0),
    UNMETERED_ONLY(1),
    UNMETERED_OR_DAILY(2),
    FAST_IF_RADIO_AWAKE(3),
    NEVER(4);
    
    public static final int DEFAULT_VALUE = 0;
    public static final int FAST_IF_RADIO_AWAKE_VALUE = 3;
    public static final int NEVER_VALUE = 4;
    public static final int UNMETERED_ONLY_VALUE = 1;
    public static final int UNMETERED_OR_DAILY_VALUE = 2;
    private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.wireless.android.play.playlog.proto.ClientAnalytics$QosTierConfiguration$QosTier.1
        @Override // com.google.protobuf.Internal.EnumLiteMap
        public ClientAnalytics$QosTierConfiguration$QosTier findValueByNumber(int i) {
            return ClientAnalytics$QosTierConfiguration$QosTier.forNumber(i);
        }
    };
    private final int value;

    ClientAnalytics$QosTierConfiguration$QosTier(int i) {
        this.value = i;
    }

    public static ClientAnalytics$QosTierConfiguration$QosTier forNumber(int i) {
        switch (i) {
            case 0:
                return DEFAULT;
            case 1:
                return UNMETERED_ONLY;
            case 2:
                return UNMETERED_OR_DAILY;
            case 3:
                return FAST_IF_RADIO_AWAKE;
            case 4:
                return NEVER;
            default:
                return null;
        }
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
