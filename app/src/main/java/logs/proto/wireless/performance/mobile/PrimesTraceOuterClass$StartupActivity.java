package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$StartupActivity extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CREATED_MS_FIELD_NUMBER = 2;
    private static final PrimesTraceOuterClass$StartupActivity DEFAULT_INSTANCE;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int RESUMED_MS_FIELD_NUMBER = 4;
    public static final int STARTED_MS_FIELD_NUMBER = 3;
    private int bitField0_;
    private long createdMs_;
    private String name_ = "";
    private long resumedMs_;
    private long startedMs_;

    static {
        PrimesTraceOuterClass$StartupActivity primesTraceOuterClass$StartupActivity = new PrimesTraceOuterClass$StartupActivity();
        DEFAULT_INSTANCE = primesTraceOuterClass$StartupActivity;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$StartupActivity.class, primesTraceOuterClass$StartupActivity);
    }

    private PrimesTraceOuterClass$StartupActivity() {
    }

    public static PrimesTraceOuterClass$StartupActivity getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCreatedMs(long j) {
        this.bitField0_ |= 2;
        this.createdMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.name_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResumedMs(long j) {
        this.bitField0_ |= 8;
        this.resumedMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartedMs(long j) {
        this.bitField0_ |= 4;
        this.startedMs_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PrimesTraceOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PrimesTraceOuterClass$StartupActivity();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"bitField0_", "name_", "createdMs_", "startedMs_", "resumedMs_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$StartupActivity.class) {
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

    public long getCreatedMs() {
        return this.createdMs_;
    }

    public long getResumedMs() {
        return this.resumedMs_;
    }

    public long getStartedMs() {
        return this.startedMs_;
    }

    public boolean hasCreatedMs() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasResumedMs() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasStartedMs() {
        return (this.bitField0_ & 4) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PrimesTraceOuterClass$StartupActivity.DEFAULT_INSTANCE);
        }

        public long getCreatedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).getCreatedMs();
        }

        public long getResumedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).getResumedMs();
        }

        public long getStartedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).getStartedMs();
        }

        public boolean hasCreatedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).hasCreatedMs();
        }

        public boolean hasResumedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).hasResumedMs();
        }

        public boolean hasStartedMs() {
            return ((PrimesTraceOuterClass$StartupActivity) this.instance).hasStartedMs();
        }

        public Builder setCreatedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupActivity) this.instance).setCreatedMs(j);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupActivity) this.instance).setName(str);
            return this;
        }

        public Builder setResumedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupActivity) this.instance).setResumedMs(j);
            return this;
        }

        public Builder setStartedMs(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$StartupActivity) this.instance).setStartedMs(j);
            return this;
        }

        /* synthetic */ Builder(PrimesTraceOuterClass$1 primesTraceOuterClass$1) {
            this();
        }
    }
}
