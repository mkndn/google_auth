package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$Span extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CONSTANT_NAME_FIELD_NUMBER = 1;
    public static final int CPU_TIME_MS_FIELD_NUMBER = 10;
    private static final PrimesTraceOuterClass$Span DEFAULT_INSTANCE;
    public static final int DURATION_MS_FIELD_NUMBER = 5;
    public static final int HASHED_NAME_FIELD_NUMBER = 8;
    public static final int ID_FIELD_NUMBER = 2;
    public static final int METRIC_EXTENSION_FIELD_NUMBER = 11;
    public static final int NAME_FIELD_NUMBER = 9;
    public static final int PARENT_ID_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int SPAN_TYPE_FIELD_NUMBER = 7;
    public static final int START_TIME_MS_FIELD_NUMBER = 4;
    public static final int THREAD_ID_FIELD_NUMBER = 6;
    private int bitField0_;
    private long hashedName_;
    private byte memoizedIsInitialized = 2;
    private String constantName_ = "";
    private String name_ = "";

    static {
        PrimesTraceOuterClass$Span primesTraceOuterClass$Span = new PrimesTraceOuterClass$Span();
        DEFAULT_INSTANCE = primesTraceOuterClass$Span;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$Span.class, primesTraceOuterClass$Span);
    }

    private PrimesTraceOuterClass$Span() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashedName() {
        this.bitField0_ &= -3;
        this.hashedName_ = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearName() {
        this.bitField0_ &= -5;
        this.name_ = getDefaultInstance().getName();
    }

    public static PrimesTraceOuterClass$Span getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashedName(long j) {
        this.bitField0_ |= 2;
        this.hashedName_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setName(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.name_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PrimesTraceOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PrimesTraceOuterClass$Span();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\t\u0003\u0000\u0000\u0000\u0001ဈ\u0000\bစ\u0001\tဈ\u0002", new Object[]{"bitField0_", "constantName_", "hashedName_", "name_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$Span.class) {
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

    public String getConstantName() {
        return this.constantName_;
    }

    public String getName() {
        return this.name_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PrimesTraceOuterClass$Span.DEFAULT_INSTANCE);
        }

        public Builder clearHashedName() {
            copyOnWrite();
            ((PrimesTraceOuterClass$Span) this.instance).clearHashedName();
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((PrimesTraceOuterClass$Span) this.instance).clearName();
            return this;
        }

        public String getConstantName() {
            return ((PrimesTraceOuterClass$Span) this.instance).getConstantName();
        }

        public String getName() {
            return ((PrimesTraceOuterClass$Span) this.instance).getName();
        }

        public Builder setHashedName(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$Span) this.instance).setHashedName(j);
            return this;
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((PrimesTraceOuterClass$Span) this.instance).setName(str);
            return this;
        }

        /* synthetic */ Builder(PrimesTraceOuterClass$1 primesTraceOuterClass$1) {
            this();
        }
    }
}
