package logs.proto.wireless.performance.mobile;

import com.google.common.logging.proto2api.Logrecord$LogRecordProto;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$ClientErrorLoggingMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ACTIVE_COMPONENT_NAME_FIELD_NUMBER = 3;
    public static final int ALLOWED_PARAMETERS_FIELD_NUMBER = 6;
    private static final SystemHealthProto$ClientErrorLoggingMetric DEFAULT_INSTANCE;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 1;
    public static final int LOG_RECORD_FIELD_NUMBER = 5;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_STATS_FIELD_NUMBER = 2;
    public static final int THREAD_NAME_FIELD_NUMBER = 4;
    public static final int TRACE_SPANS_FIELD_NUMBER = 7;
    private int bitField0_;
    private Logrecord$LogRecordProto logRecord_;
    private byte memoizedIsInitialized = 2;
    private String activeComponentName_ = "";
    private String threadName_ = "";
    private Internal.ProtobufList allowedParameters_ = emptyProtobufList();
    private Internal.ProtobufList traceSpans_ = GeneratedMessageLite.emptyProtobufList();

    static {
        SystemHealthProto$ClientErrorLoggingMetric systemHealthProto$ClientErrorLoggingMetric = new SystemHealthProto$ClientErrorLoggingMetric();
        DEFAULT_INSTANCE = systemHealthProto$ClientErrorLoggingMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$ClientErrorLoggingMetric.class, systemHealthProto$ClientErrorLoggingMetric);
    }

    private SystemHealthProto$ClientErrorLoggingMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$ClientErrorLoggingMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0005\u0005\u0001\u0000\u0000\u0001\u0005ᐉ\u0004", new Object[]{"bitField0_", "logRecord_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$ClientErrorLoggingMetric.class) {
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
            super(SystemHealthProto$ClientErrorLoggingMetric.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
