package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$StartupMeasurements;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesTraceOuterClass$PrimesTrace extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BACKEND_TRACE_ID_FIELD_NUMBER = 7;
    public static final int COLLECTION_ERROR_FIELD_NUMBER = 5;
    private static final PrimesTraceOuterClass$PrimesTrace DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SPANS_FIELD_NUMBER = 2;
    public static final int STARTUP_MEASUREMENTS_FIELD_NUMBER = 6;
    public static final int TRACE_ID_FIELD_NUMBER = 1;
    public static final int TRACE_SAMPLING_RATE_FIELD_NUMBER = 4;
    public static final int TRACE_TYPE_FIELD_NUMBER = 3;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private Internal.ProtobufList spans_ = emptyProtobufList();
    private PrimesTraceOuterClass$StartupMeasurements startupMeasurements_;
    private long traceId_;
    private int traceType_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TraceType implements Internal.EnumLite {
        UNKNOWN(0),
        TIKTOK_TRACE(1),
        MINI_TRACE(2),
        PRIMES_TRACE(3);
        
        public static final int MINI_TRACE_VALUE = 2;
        public static final int PRIMES_TRACE_VALUE = 3;
        public static final int TIKTOK_TRACE_VALUE = 1;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.PrimesTraceOuterClass.PrimesTrace.TraceType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TraceType findValueByNumber(int i) {
                return TraceType.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class TraceTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new TraceTypeVerifier();

            private TraceTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return TraceType.forNumber(i) != null;
            }
        }

        TraceType(int i) {
            this.value = i;
        }

        public static TraceType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return TIKTOK_TRACE;
                case 2:
                    return MINI_TRACE;
                case 3:
                    return PRIMES_TRACE;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return TraceTypeVerifier.INSTANCE;
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
        PrimesTraceOuterClass$PrimesTrace primesTraceOuterClass$PrimesTrace = new PrimesTraceOuterClass$PrimesTrace();
        DEFAULT_INSTANCE = primesTraceOuterClass$PrimesTrace;
        GeneratedMessageLite.registerDefaultInstance(PrimesTraceOuterClass$PrimesTrace.class, primesTraceOuterClass$PrimesTrace);
    }

    private PrimesTraceOuterClass$PrimesTrace() {
    }

    private void ensureSpansIsMutable() {
        Internal.ProtobufList protobufList = this.spans_;
        if (!protobufList.isModifiable()) {
            this.spans_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static PrimesTraceOuterClass$PrimesTrace getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpans(int i, PrimesTraceOuterClass$Span primesTraceOuterClass$Span) {
        primesTraceOuterClass$Span.getClass();
        ensureSpansIsMutable();
        this.spans_.set(i, primesTraceOuterClass$Span);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStartupMeasurements(PrimesTraceOuterClass$StartupMeasurements primesTraceOuterClass$StartupMeasurements) {
        primesTraceOuterClass$StartupMeasurements.getClass();
        this.startupMeasurements_ = primesTraceOuterClass$StartupMeasurements;
        this.bitField0_ |= 16;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTraceId(long j) {
        this.bitField0_ |= 1;
        this.traceId_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTraceType(TraceType traceType) {
        this.traceType_ = traceType.getNumber();
        this.bitField0_ |= 2;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PrimesTraceOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PrimesTraceOuterClass$PrimesTrace();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0006\u0004\u0000\u0001\u0001\u0001စ\u0000\u0002Л\u0003ဌ\u0001\u0006ဉ\u0004", new Object[]{"bitField0_", "traceId_", "spans_", PrimesTraceOuterClass$Span.class, "traceType_", TraceType.internalGetVerifier(), "startupMeasurements_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PrimesTraceOuterClass$PrimesTrace.class) {
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

    public PrimesTraceOuterClass$Span getSpans(int i) {
        return (PrimesTraceOuterClass$Span) this.spans_.get(i);
    }

    public int getSpansCount() {
        return this.spans_.size();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PrimesTraceOuterClass$PrimesTrace.DEFAULT_INSTANCE);
        }

        public PrimesTraceOuterClass$Span getSpans(int i) {
            return ((PrimesTraceOuterClass$PrimesTrace) this.instance).getSpans(i);
        }

        public int getSpansCount() {
            return ((PrimesTraceOuterClass$PrimesTrace) this.instance).getSpansCount();
        }

        public Builder setSpans(int i, PrimesTraceOuterClass$Span.Builder builder) {
            copyOnWrite();
            ((PrimesTraceOuterClass$PrimesTrace) this.instance).setSpans(i, (PrimesTraceOuterClass$Span) builder.build());
            return this;
        }

        public Builder setStartupMeasurements(PrimesTraceOuterClass$StartupMeasurements.Builder builder) {
            copyOnWrite();
            ((PrimesTraceOuterClass$PrimesTrace) this.instance).setStartupMeasurements((PrimesTraceOuterClass$StartupMeasurements) builder.build());
            return this;
        }

        public Builder setTraceId(long j) {
            copyOnWrite();
            ((PrimesTraceOuterClass$PrimesTrace) this.instance).setTraceId(j);
            return this;
        }

        public Builder setTraceType(TraceType traceType) {
            copyOnWrite();
            ((PrimesTraceOuterClass$PrimesTrace) this.instance).setTraceType(traceType);
            return this;
        }

        /* synthetic */ Builder(PrimesTraceOuterClass$1 primesTraceOuterClass$1) {
            this();
        }
    }
}
