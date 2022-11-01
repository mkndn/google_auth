package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitInfo extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final ApplicationExitInfo DEFAULT_INSTANCE;
    public static final int IMPORTANCE_FIELD_NUMBER = 4;
    public static final int LOW_MEMORY_KILL_SUPPORTED_FIELD_NUMBER = 8;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_NAME_FIELD_NUMBER = 1;
    public static final int PSS_KB_FIELD_NUMBER = 6;
    public static final int REASON_FIELD_NUMBER = 2;
    public static final int RSS_KB_FIELD_NUMBER = 7;
    public static final int STATUS_FIELD_NUMBER = 3;
    public static final int TIMESTAMP_MILLIS_FIELD_NUMBER = 5;
    private int bitField0_;
    private int importance_;
    private boolean lowMemoryKillSupported_;
    private String processName_ = "";
    private long pssKb_;
    private int reason_;
    private long rssKb_;
    private int status_;
    private long timestampMillis_;

    /* compiled from: PG */
    /* renamed from: logs.proto.wireless.performance.mobile.ApplicationExitInfo$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Importance implements Internal.EnumLite {
        IMPORTANCE_UNSET(0),
        FOREGROUND(1),
        FOREGROUND_SERVICE(2),
        TOP_SLEEPING(3),
        VISIBLE(4),
        PERCEPTIBLE(5),
        CANT_SAVE_STATE(6),
        SERVICE(7),
        CACHED(8),
        GONE(9);
        
        public static final int CACHED_VALUE = 8;
        public static final int CANT_SAVE_STATE_VALUE = 6;
        public static final int FOREGROUND_SERVICE_VALUE = 2;
        public static final int FOREGROUND_VALUE = 1;
        public static final int GONE_VALUE = 9;
        public static final int IMPORTANCE_UNSET_VALUE = 0;
        public static final int PERCEPTIBLE_VALUE = 5;
        public static final int SERVICE_VALUE = 7;
        public static final int TOP_SLEEPING_VALUE = 3;
        public static final int VISIBLE_VALUE = 4;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.ApplicationExitInfo.Importance.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Importance findValueByNumber(int i) {
                return Importance.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class ImportanceVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ImportanceVerifier();

            private ImportanceVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Importance.forNumber(i) != null;
            }
        }

        Importance(int i) {
            this.value = i;
        }

        public static Importance forNumber(int i) {
            switch (i) {
                case 0:
                    return IMPORTANCE_UNSET;
                case 1:
                    return FOREGROUND;
                case 2:
                    return FOREGROUND_SERVICE;
                case 3:
                    return TOP_SLEEPING;
                case 4:
                    return VISIBLE;
                case 5:
                    return PERCEPTIBLE;
                case 6:
                    return CANT_SAVE_STATE;
                case 7:
                    return SERVICE;
                case 8:
                    return CACHED;
                case 9:
                    return GONE;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ImportanceVerifier.INSTANCE;
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
    public enum Reason implements Internal.EnumLite {
        REASON_UNSET(0),
        ANR(6),
        CRASH(4),
        CRASH_NATIVE(5),
        DEPENDENCY_DIED(12),
        EXCESSIVE_RESOURCE_USAGE(9),
        EXIT_SELF(1),
        INITIALIZATION_FAILURE(7),
        LOW_MEMORY(3),
        PERMISSION_CHANGE(8),
        SIGNALED(2),
        USER_REQUESTED(10),
        USER_STOPPED(11),
        OTHER(13),
        UNKNOWN(14);
        
        public static final int ANR_VALUE = 6;
        public static final int CRASH_NATIVE_VALUE = 5;
        public static final int CRASH_VALUE = 4;
        public static final int DEPENDENCY_DIED_VALUE = 12;
        public static final int EXCESSIVE_RESOURCE_USAGE_VALUE = 9;
        public static final int EXIT_SELF_VALUE = 1;
        public static final int INITIALIZATION_FAILURE_VALUE = 7;
        public static final int LOW_MEMORY_VALUE = 3;
        public static final int OTHER_VALUE = 13;
        public static final int PERMISSION_CHANGE_VALUE = 8;
        public static final int REASON_UNSET_VALUE = 0;
        public static final int SIGNALED_VALUE = 2;
        public static final int UNKNOWN_VALUE = 14;
        public static final int USER_REQUESTED_VALUE = 10;
        public static final int USER_STOPPED_VALUE = 11;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.ApplicationExitInfo.Reason.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Reason findValueByNumber(int i) {
                return Reason.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class ReasonVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new ReasonVerifier();

            private ReasonVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return Reason.forNumber(i) != null;
            }
        }

        Reason(int i) {
            this.value = i;
        }

        public static Reason forNumber(int i) {
            switch (i) {
                case 0:
                    return REASON_UNSET;
                case 1:
                    return EXIT_SELF;
                case 2:
                    return SIGNALED;
                case 3:
                    return LOW_MEMORY;
                case 4:
                    return CRASH;
                case 5:
                    return CRASH_NATIVE;
                case 6:
                    return ANR;
                case 7:
                    return INITIALIZATION_FAILURE;
                case 8:
                    return PERMISSION_CHANGE;
                case 9:
                    return EXCESSIVE_RESOURCE_USAGE;
                case 10:
                    return USER_REQUESTED;
                case 11:
                    return USER_STOPPED;
                case 12:
                    return DEPENDENCY_DIED;
                case 13:
                    return OTHER;
                case 14:
                    return UNKNOWN;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return ReasonVerifier.INSTANCE;
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
        ApplicationExitInfo applicationExitInfo = new ApplicationExitInfo();
        DEFAULT_INSTANCE = applicationExitInfo;
        GeneratedMessageLite.registerDefaultInstance(ApplicationExitInfo.class, applicationExitInfo);
    }

    private ApplicationExitInfo() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImportance(Importance importance) {
        this.importance_ = importance.getNumber();
        this.bitField0_ |= 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLowMemoryKillSupported(boolean z) {
        this.bitField0_ |= 128;
        this.lowMemoryKillSupported_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.processName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPssKb(long j) {
        this.bitField0_ |= 32;
        this.pssKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReason(Reason reason) {
        this.reason_ = reason.getNumber();
        this.bitField0_ |= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRssKb(long j) {
        this.bitField0_ |= 64;
        this.rssKb_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus(int i) {
        this.bitField0_ |= 4;
        this.status_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimestampMillis(long j) {
        this.bitField0_ |= 16;
        this.timestampMillis_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ApplicationExitInfo();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004ဌ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဇ\u0007", new Object[]{"bitField0_", "processName_", "reason_", Reason.internalGetVerifier(), "status_", "importance_", Importance.internalGetVerifier(), "timestampMillis_", "pssKb_", "rssKb_", "lowMemoryKillSupported_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ApplicationExitInfo.class) {
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

    public String getProcessName() {
        return this.processName_;
    }

    public Reason getReason() {
        Reason forNumber = Reason.forNumber(this.reason_);
        return forNumber == null ? Reason.REASON_UNSET : forNumber;
    }

    public long getTimestampMillis() {
        return this.timestampMillis_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(ApplicationExitInfo.DEFAULT_INSTANCE);
        }

        public Builder setImportance(Importance importance) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setImportance(importance);
            return this;
        }

        public Builder setLowMemoryKillSupported(boolean z) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setLowMemoryKillSupported(z);
            return this;
        }

        public Builder setProcessName(String str) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setProcessName(str);
            return this;
        }

        public Builder setPssKb(long j) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setPssKb(j);
            return this;
        }

        public Builder setReason(Reason reason) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setReason(reason);
            return this;
        }

        public Builder setRssKb(long j) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setRssKb(j);
            return this;
        }

        public Builder setStatus(int i) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setStatus(i);
            return this;
        }

        public Builder setTimestampMillis(long j) {
            copyOnWrite();
            ((ApplicationExitInfo) this.instance).setTimestampMillis(j);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
