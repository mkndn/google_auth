package com.google.wireless.android.play.playlog.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogSamplingRulesProto$LogSamplingRules extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final LogSamplingRulesProto$LogSamplingRules DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int RULES_FIELD_NUMBER = 1;
    private Internal.ProtobufList rules_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Rule extends GeneratedMessageLite implements MessageLiteOrBuilder {
        public static final int APPLIED_SAMPLING_RATE_OVERRIDE_FIELD_NUMBER = 5;
        public static final int CORRELATION_TOKEN_FIELD_NUMBER = 2;
        private static final Rule DEFAULT_INSTANCE;
        public static final int EVENT_CODE_FIELD_NUMBER = 1;
        public static final int KEEP_DENOMINATOR_FIELD_NUMBER = 4;
        public static final int KEEP_NUMERATOR_FIELD_NUMBER = 3;
        private static volatile Parser PARSER;
        private int bitField0_;
        private String correlationToken_ = "";
        private int eventCode_;
        private long keepDenominator_;
        private long keepNumerator_;

        static {
            Rule rule = new Rule();
            DEFAULT_INSTANCE = rule;
            GeneratedMessageLite.registerDefaultInstance(Rule.class, rule);
        }

        private Rule() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (LogSamplingRulesProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Rule();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003", new Object[]{"bitField0_", "eventCode_", "correlationToken_", "keepNumerator_", "keepDenominator_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Rule.class) {
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

        public String getCorrelationToken() {
            return this.correlationToken_;
        }

        public int getEventCode() {
            return this.eventCode_;
        }

        public long getKeepDenominator() {
            return this.keepDenominator_;
        }

        public long getKeepNumerator() {
            return this.keepNumerator_;
        }

        public boolean hasEventCode() {
            return (this.bitField0_ & 1) != 0;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(Rule.DEFAULT_INSTANCE);
            }

            /* synthetic */ Builder(LogSamplingRulesProto$1 logSamplingRulesProto$1) {
                this();
            }
        }
    }

    static {
        LogSamplingRulesProto$LogSamplingRules logSamplingRulesProto$LogSamplingRules = new LogSamplingRulesProto$LogSamplingRules();
        DEFAULT_INSTANCE = logSamplingRulesProto$LogSamplingRules;
        GeneratedMessageLite.registerDefaultInstance(LogSamplingRulesProto$LogSamplingRules.class, logSamplingRulesProto$LogSamplingRules);
    }

    private LogSamplingRulesProto$LogSamplingRules() {
    }

    public static LogSamplingRulesProto$LogSamplingRules getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static LogSamplingRulesProto$LogSamplingRules parseFrom(byte[] bArr) {
        return (LogSamplingRulesProto$LogSamplingRules) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (LogSamplingRulesProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new LogSamplingRulesProto$LogSamplingRules();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"rules_", Rule.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (LogSamplingRulesProto$LogSamplingRules.class) {
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

    public List getRulesList() {
        return this.rules_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(LogSamplingRulesProto$LogSamplingRules.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(LogSamplingRulesProto$1 logSamplingRulesProto$1) {
            this();
        }
    }
}
