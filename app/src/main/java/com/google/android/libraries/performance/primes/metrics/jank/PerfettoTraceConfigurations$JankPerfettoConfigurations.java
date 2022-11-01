package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PerfettoTraceConfigurations$JankPerfettoConfigurations extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COUNTER_FIELD_NUMBER = 2;
    private static final PerfettoTraceConfigurations$JankPerfettoConfigurations DEFAULT_INSTANCE;
    public static final int FLUSHING_TO_PERFETTO_ENABLED_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int TRIGGER_NAME_FORMAT_STRING_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean flushingToPerfettoEnabled_;
    private String triggerNameFormatString_ = "";
    private Internal.ProtobufList counter_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Counter extends GeneratedMessageLite implements MessageLiteOrBuilder {
        private static final Counter DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private String name_ = "";
        private int type_;

        static {
            Counter counter = new Counter();
            DEFAULT_INSTANCE = counter;
            GeneratedMessageLite.registerDefaultInstance(Counter.class, counter);
        }

        private Counter() {
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (PerfettoTraceConfigurations$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Counter();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "type_", CounterType.internalGetVerifier(), "name_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (Counter.class) {
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

        public String getName() {
            return this.name_;
        }

        public CounterType getType() {
            CounterType forNumber = CounterType.forNumber(this.type_);
            return forNumber == null ? CounterType.COUNTER_UNKNOWN : forNumber;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(Counter.DEFAULT_INSTANCE);
            }

            /* synthetic */ Builder(PerfettoTraceConfigurations$1 perfettoTraceConfigurations$1) {
                this();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum CounterType implements Internal.EnumLite {
        COUNTER_UNKNOWN(0),
        COUNTER_EMPTY(1),
        COUNTER_JANKY_FRAME_COUNT(2),
        COUNTER_TOTAL_FRAME_COUNT(3),
        COUNTER_DROPPED_REPORT_COUNT(4),
        COUNTER_MAX_FRAME_DURATION_MS(5),
        COUNTER_TOTAL_JANKY_FRAME_DURATION_MS(6),
        COUNTER_TOTAL_FRAME_DURATION_MS(7);
        
        public static final int COUNTER_DROPPED_REPORT_COUNT_VALUE = 4;
        public static final int COUNTER_EMPTY_VALUE = 1;
        public static final int COUNTER_JANKY_FRAME_COUNT_VALUE = 2;
        public static final int COUNTER_MAX_FRAME_DURATION_MS_VALUE = 5;
        public static final int COUNTER_TOTAL_FRAME_COUNT_VALUE = 3;
        public static final int COUNTER_TOTAL_FRAME_DURATION_MS_VALUE = 7;
        public static final int COUNTER_TOTAL_JANKY_FRAME_DURATION_MS_VALUE = 6;
        public static final int COUNTER_UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations.JankPerfettoConfigurations.CounterType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CounterType findValueByNumber(int i) {
                return CounterType.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class CounterTypeVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new CounterTypeVerifier();

            private CounterTypeVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return CounterType.forNumber(i) != null;
            }
        }

        CounterType(int i) {
            this.value = i;
        }

        public static CounterType forNumber(int i) {
            switch (i) {
                case 0:
                    return COUNTER_UNKNOWN;
                case 1:
                    return COUNTER_EMPTY;
                case 2:
                    return COUNTER_JANKY_FRAME_COUNT;
                case 3:
                    return COUNTER_TOTAL_FRAME_COUNT;
                case 4:
                    return COUNTER_DROPPED_REPORT_COUNT;
                case 5:
                    return COUNTER_MAX_FRAME_DURATION_MS;
                case 6:
                    return COUNTER_TOTAL_JANKY_FRAME_DURATION_MS;
                case 7:
                    return COUNTER_TOTAL_FRAME_DURATION_MS;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return CounterTypeVerifier.INSTANCE;
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
        PerfettoTraceConfigurations$JankPerfettoConfigurations perfettoTraceConfigurations$JankPerfettoConfigurations = new PerfettoTraceConfigurations$JankPerfettoConfigurations();
        DEFAULT_INSTANCE = perfettoTraceConfigurations$JankPerfettoConfigurations;
        GeneratedMessageLite.registerDefaultInstance(PerfettoTraceConfigurations$JankPerfettoConfigurations.class, perfettoTraceConfigurations$JankPerfettoConfigurations);
    }

    private PerfettoTraceConfigurations$JankPerfettoConfigurations() {
    }

    public static PerfettoTraceConfigurations$JankPerfettoConfigurations parseFrom(byte[] bArr) {
        return (PerfettoTraceConfigurations$JankPerfettoConfigurations) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (PerfettoTraceConfigurations$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new PerfettoTraceConfigurations$JankPerfettoConfigurations();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b\u0003ဇ\u0001", new Object[]{"bitField0_", "triggerNameFormatString_", "counter_", Counter.class, "flushingToPerfettoEnabled_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (PerfettoTraceConfigurations$JankPerfettoConfigurations.class) {
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

    public List getCounterList() {
        return this.counter_;
    }

    public boolean getFlushingToPerfettoEnabled() {
        return this.flushingToPerfettoEnabled_;
    }

    public String getTriggerNameFormatString() {
        return this.triggerNameFormatString_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(PerfettoTraceConfigurations$JankPerfettoConfigurations.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(PerfettoTraceConfigurations$1 perfettoTraceConfigurations$1) {
            this();
        }
    }
}
