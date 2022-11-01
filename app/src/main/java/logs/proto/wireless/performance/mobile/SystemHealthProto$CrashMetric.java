package logs.proto.wireless.performance.mobile;

import com.google.common.logging.proto2api.Logrecord$ThrowableProto;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$CrashMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ACTIVE_COMPONENT_NAME_FIELD_NUMBER = 3;
    public static final int CRASH_CLASS_NAME_FIELD_NUMBER = 7;
    public static final int CRASH_TYPE_FIELD_NUMBER = 5;
    private static final SystemHealthProto$CrashMetric DEFAULT_INSTANCE;
    public static final int EXCEPTION_FIELD_NUMBER = 9;
    public static final int HASHED_STACK_TRACE_FIELD_NUMBER = 6;
    public static final int HASHED_STACK_TRACE_FOR_LISTNR_FIELD_NUMBER = 8;
    public static final int HAS_CRASHED_FIELD_NUMBER = 1;
    public static final int NATIVE_CRASH_INFO_FIELD_NUMBER = 10;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 2;
    public static final int THREAD_NAME_FIELD_NUMBER = 4;
    private int bitField0_;
    private int crashType_;
    private Logrecord$ThrowableProto exception_;
    private boolean hasCrashed_;
    private ProcessProto$ProcessStats processStats_;
    private byte memoizedIsInitialized = 2;
    private String activeComponentName_ = "";
    private String threadName_ = "";
    private String crashClassName_ = "";

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum CrashType implements Internal.EnumLite {
        UNKNOWN(0),
        NULL_POINTER_EXCEPTION(1),
        OUT_OF_MEMORY_ERROR(2),
        OTHER_RUNTIME_EXCEPTION(3),
        OTHER_ERROR(4),
        NATIVE_CRASH(5);
        
        public static final int NATIVE_CRASH_VALUE = 5;
        public static final int NULL_POINTER_EXCEPTION_VALUE = 1;
        public static final int OTHER_ERROR_VALUE = 4;
        public static final int OTHER_RUNTIME_EXCEPTION_VALUE = 3;
        public static final int OUT_OF_MEMORY_ERROR_VALUE = 2;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.SystemHealthProto.CrashMetric.CrashType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CrashType findValueByNumber(int i) {
                return CrashType.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class CrashTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CrashTypeVerifier();

            private CrashTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return CrashType.forNumber(i) != null;
            }
        }

        CrashType(int i) {
            this.value = i;
        }

        public static CrashType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return NULL_POINTER_EXCEPTION;
                case 2:
                    return OUT_OF_MEMORY_ERROR;
                case 3:
                    return OTHER_RUNTIME_EXCEPTION;
                case 4:
                    return OTHER_ERROR;
                case 5:
                    return NATIVE_CRASH;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CrashTypeVerifier.INSTANCE;
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
        SystemHealthProto$CrashMetric systemHealthProto$CrashMetric = new SystemHealthProto$CrashMetric();
        DEFAULT_INSTANCE = systemHealthProto$CrashMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$CrashMetric.class, systemHealthProto$CrashMetric);
    }

    private SystemHealthProto$CrashMetric() {
    }

    public static SystemHealthProto$CrashMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActiveComponentName(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.activeComponentName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCrashClassName(String str) {
        str.getClass();
        this.bitField0_ |= 128;
        this.crashClassName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCrashType(CrashType crashType) {
        this.crashType_ = crashType.getNumber();
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setException(Logrecord$ThrowableProto logrecord$ThrowableProto) {
        logrecord$ThrowableProto.getClass();
        this.exception_ = logrecord$ThrowableProto;
        this.bitField0_ |= 256;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasCrashed(boolean z) {
        this.bitField0_ |= 1;
        this.hasCrashed_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessStats(ProcessProto$ProcessStats processProto$ProcessStats) {
        processProto$ProcessStats.getClass();
        this.processStats_ = processProto$ProcessStats;
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setThreadName(String str) {
        str.getClass();
        this.bitField0_ |= 8;
        this.threadName_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$CrashMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0000\u0001\u0001ဇ\u0000\u0002ဉ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဌ\u0004\u0007ဈ\u0007\tᐉ\b", new Object[]{"bitField0_", "hasCrashed_", "processStats_", "activeComponentName_", "threadName_", "crashType_", CrashType.internalGetVerifier(), "crashClassName_", "exception_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$CrashMetric.class) {
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

    public Logrecord$ThrowableProto getException() {
        Logrecord$ThrowableProto logrecord$ThrowableProto = this.exception_;
        return logrecord$ThrowableProto == null ? Logrecord$ThrowableProto.getDefaultInstance() : logrecord$ThrowableProto;
    }

    public boolean hasException() {
        return (this.bitField0_ & 256) != 0;
    }

    public boolean hasHasCrashed() {
        return (this.bitField0_ & 1) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$CrashMetric.DEFAULT_INSTANCE);
        }

        public Builder setActiveComponentName(String str) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setActiveComponentName(str);
            return this;
        }

        public Builder setCrashClassName(String str) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setCrashClassName(str);
            return this;
        }

        public Builder setCrashType(CrashType crashType) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setCrashType(crashType);
            return this;
        }

        public Builder setException(Logrecord$ThrowableProto logrecord$ThrowableProto) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setException(logrecord$ThrowableProto);
            return this;
        }

        public Builder setHasCrashed(boolean z) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setHasCrashed(z);
            return this;
        }

        public Builder setProcessStats(ProcessProto$ProcessStats.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setProcessStats((ProcessProto$ProcessStats) builder.build());
            return this;
        }

        public Builder setThreadName(String str) {
            copyOnWrite();
            ((SystemHealthProto$CrashMetric) this.instance).setThreadName(str);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
