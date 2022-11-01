package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$TimerMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$TimerMetric DEFAULT_INSTANCE;
    public static final int DURATION_MS_FIELD_NUMBER = 1;
    public static final int END_STATUS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;

    static {
        SystemHealthProto$TimerMetric systemHealthProto$TimerMetric = new SystemHealthProto$TimerMetric();
        DEFAULT_INSTANCE = systemHealthProto$TimerMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$TimerMetric.class, systemHealthProto$TimerMetric);
    }

    private SystemHealthProto$TimerMetric() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$TimerMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$TimerMetric.class) {
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
            super(SystemHealthProto$TimerMetric.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
