package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PrimesStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$PrimesStats DEFAULT_INSTANCE;
    public static final int ESTIMATED_COUNT_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PRIMES_DEBUG_MESSAGE_FIELD_NUMBER = 3;
    public static final int PRIMES_EVENT_FIELD_NUMBER = 1;
    private int bitField0_;
    private PrimesDebugMessage primesDebugMessage_;
    private int primesEvent_;
    private byte memoizedIsInitialized = 2;
    private int estimatedCount_ = 1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PrimesDebugMessage extends GeneratedMessageLite implements MessageLiteOrBuilder {
        private static final PrimesDebugMessage DEFAULT_INSTANCE;
        private static volatile Parser PARSER = null;
        public static final int PREVIOUS_CRASH_FIELD_NUMBER = 1;
        public static final int PRIMES_HEAP_DUMP_CALIBRATION_STATUS_FIELD_NUMBER = 3;
        public static final int PRIMES_HEAP_DUMP_EVENT_FIELD_NUMBER = 2;
        private int bitField0_;
        private byte memoizedIsInitialized = 2;
        private SystemHealthProto$CrashMetric previousCrash_;

        static {
            PrimesDebugMessage primesDebugMessage = new PrimesDebugMessage();
            DEFAULT_INSTANCE = primesDebugMessage;
            GeneratedMessageLite.registerDefaultInstance(PrimesDebugMessage.class, primesDebugMessage);
        }

        private PrimesDebugMessage() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PrimesDebugMessage();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "previousCrash_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (PrimesDebugMessage.class) {
                            parser = PARSER;
                            if (parser == null) {
                                parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                                PARSER = parser;
                            }
                        }
                    }
                    return parser;
                case 6:
                    return Byte.valueOf(this.memoizedIsInitialized);
                case 7:
                    this.memoizedIsInitialized = obj == null ? (byte) 0 : (byte) 1;
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(PrimesDebugMessage.DEFAULT_INSTANCE);
            }

            /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
                this();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum PrimesEvent implements Internal.EnumLite {
        UNKNOWN(0),
        PRIMES_INITIALIZED(1),
        PRIMES_CRASH_MONITORING_INITIALIZED(2),
        PRIMES_FIRST_ACTIVITY_LAUNCHED(3),
        PRIMES_CUSTOM_LAUNCHED(4);
        
        public static final int PRIMES_CRASH_MONITORING_INITIALIZED_VALUE = 2;
        public static final int PRIMES_CUSTOM_LAUNCHED_VALUE = 4;
        public static final int PRIMES_FIRST_ACTIVITY_LAUNCHED_VALUE = 3;
        @Deprecated
        public static final int PRIMES_INITIALIZED_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.SystemHealthProto.PrimesStats.PrimesEvent.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PrimesEvent findValueByNumber(int i) {
                return PrimesEvent.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class PrimesEventVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new PrimesEventVerifier();

            private PrimesEventVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return PrimesEvent.forNumber(i) != null;
            }
        }

        PrimesEvent(int i) {
            this.value = i;
        }

        public static PrimesEvent forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return PRIMES_INITIALIZED;
                case 2:
                    return PRIMES_CRASH_MONITORING_INITIALIZED;
                case 3:
                    return PRIMES_FIRST_ACTIVITY_LAUNCHED;
                case 4:
                    return PRIMES_CUSTOM_LAUNCHED;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return PrimesEventVerifier.INSTANCE;
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
        SystemHealthProto$PrimesStats systemHealthProto$PrimesStats = new SystemHealthProto$PrimesStats();
        DEFAULT_INSTANCE = systemHealthProto$PrimesStats;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PrimesStats.class, systemHealthProto$PrimesStats);
    }

    private SystemHealthProto$PrimesStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEstimatedCount(int i) {
        this.bitField0_ |= 2;
        this.estimatedCount_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPrimesEvent(PrimesEvent primesEvent) {
        this.primesEvent_ = primesEvent.getNumber();
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$PrimesStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ဌ\u0000\u0002င\u0001\u0003ᐉ\u0002", new Object[]{"bitField0_", "primesEvent_", PrimesEvent.internalGetVerifier(), "estimatedCount_", "primesDebugMessage_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PrimesStats.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return Byte.valueOf(this.memoizedIsInitialized);
            case 7:
                this.memoizedIsInitialized = obj == null ? (byte) 0 : (byte) 1;
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$PrimesStats.DEFAULT_INSTANCE);
        }

        public Builder setEstimatedCount(int i) {
            copyOnWrite();
            ((SystemHealthProto$PrimesStats) this.instance).setEstimatedCount(i);
            return this;
        }

        public Builder setPrimesEvent(PrimesEvent primesEvent) {
            copyOnWrite();
            ((SystemHealthProto$PrimesStats) this.instance).setPrimesEvent(primesEvent);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
