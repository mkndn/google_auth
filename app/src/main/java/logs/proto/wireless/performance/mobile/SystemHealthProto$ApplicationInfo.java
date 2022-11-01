package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$ApplicationInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int APPLICATION_PACKAGE_FIELD_NUMBER = 1;
    public static final int APPLICATION_VERSION_NAME_FIELD_NUMBER = 2;
    private static final SystemHealthProto$ApplicationInfo DEFAULT_INSTANCE;
    public static final int HARDWARE_VARIANT_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int PRIMES_VERSION_FIELD_NUMBER = 4;
    public static final int SHORT_PROCESS_NAME_FIELD_NUMBER = 5;
    private int bitField0_;
    private int hardwareVariant_;
    private long primesVersion_;
    private String applicationPackage_ = "";
    private String applicationVersionName_ = "";
    private String shortProcessName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum HardwareVariant implements Internal.EnumLite {
        UNKNOWN_HARDWARE_VARIANT(0),
        PHONE_OR_TABLET(1),
        WATCH(2),
        LEANBACK(3),
        AUTOMOTIVE(4);
        
        public static final int AUTOMOTIVE_VALUE = 4;
        public static final int LEANBACK_VALUE = 3;
        public static final int PHONE_OR_TABLET_VALUE = 1;
        public static final int UNKNOWN_HARDWARE_VARIANT_VALUE = 0;
        public static final int WATCH_VALUE = 2;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.SystemHealthProto.ApplicationInfo.HardwareVariant.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public HardwareVariant findValueByNumber(int i) {
                return HardwareVariant.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class HardwareVariantVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new HardwareVariantVerifier();

            private HardwareVariantVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return HardwareVariant.forNumber(i) != null;
            }
        }

        HardwareVariant(int i) {
            this.value = i;
        }

        public static HardwareVariant forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_HARDWARE_VARIANT;
                case 1:
                    return PHONE_OR_TABLET;
                case 2:
                    return WATCH;
                case 3:
                    return LEANBACK;
                case 4:
                    return AUTOMOTIVE;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return HardwareVariantVerifier.INSTANCE;
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
        SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo = new SystemHealthProto$ApplicationInfo();
        DEFAULT_INSTANCE = systemHealthProto$ApplicationInfo;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$ApplicationInfo.class, systemHealthProto$ApplicationInfo);
    }

    private SystemHealthProto$ApplicationInfo() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApplicationPackage(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.applicationPackage_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setApplicationVersionName(String str) {
        str.getClass();
        this.bitField0_ |= 2;
        this.applicationVersionName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHardwareVariant(HardwareVariant hardwareVariant) {
        this.hardwareVariant_ = hardwareVariant.getNumber();
        this.bitField0_ |= 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimesVersion(long j) {
        this.bitField0_ |= 8;
        this.primesVersion_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShortProcessName(String str) {
        str.getClass();
        this.bitField0_ |= 16;
        this.shortProcessName_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$ApplicationInfo();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဌ\u0002\u0004ဂ\u0003\u0005ဈ\u0004", new Object[]{"bitField0_", "applicationPackage_", "applicationVersionName_", "hardwareVariant_", HardwareVariant.internalGetVerifier(), "primesVersion_", "shortProcessName_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$ApplicationInfo.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$ApplicationInfo.DEFAULT_INSTANCE);
        }

        public Builder setApplicationPackage(String str) {
            copyOnWrite();
            ((SystemHealthProto$ApplicationInfo) this.instance).setApplicationPackage(str);
            return this;
        }

        public Builder setApplicationVersionName(String str) {
            copyOnWrite();
            ((SystemHealthProto$ApplicationInfo) this.instance).setApplicationVersionName(str);
            return this;
        }

        public Builder setHardwareVariant(HardwareVariant hardwareVariant) {
            copyOnWrite();
            ((SystemHealthProto$ApplicationInfo) this.instance).setHardwareVariant(hardwareVariant);
            return this;
        }

        public Builder setPrimesVersion(long j) {
            copyOnWrite();
            ((SystemHealthProto$ApplicationInfo) this.instance).setPrimesVersion(j);
            return this;
        }

        public Builder setShortProcessName(String str) {
            copyOnWrite();
            ((SystemHealthProto$ApplicationInfo) this.instance).setShortProcessName(str);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
