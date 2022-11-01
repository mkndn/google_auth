package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$SamplingParameters extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$SamplingParameters DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int PER_TIME_INTERVAL_SAMPLING_PROBABILITY_FIELD_NUMBER = 1;
    public static final int SAMPLE_RATE_PERMILLE_FIELD_NUMBER = 2;
    public static final int SAMPLING_STRATEGY_FIELD_NUMBER = 3;
    private int bitField0_;
    private long sampleRatePermille_;
    private int samplingStrategy_;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum SamplingStrategy implements Internal.EnumLite {
        UNKNOWN(0),
        SAMPLING_STRATEGY_FLOOR(1),
        SAMPLING_STRATEGY_ALWAYS_ON(2),
        SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY(3),
        SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY(5),
        SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY(4);
        
        public static final int SAMPLING_STRATEGY_ALWAYS_ON_VALUE = 2;
        public static final int SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY_VALUE = 4;
        public static final SamplingStrategy SAMPLING_STRATEGY_EVENT_PROBABILITY;
        public static final int SAMPLING_STRATEGY_EVENT_PROBABILITY_VALUE = 4;
        public static final int SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY_VALUE = 5;
        public static final int SAMPLING_STRATEGY_FLOOR_VALUE = 1;
        public static final int SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY_VALUE = 3;
        public static final int UNKNOWN_VALUE = 0;
        private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.SystemHealthProto.SamplingParameters.SamplingStrategy.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SamplingStrategy findValueByNumber(int i) {
                return SamplingStrategy.forNumber(i);
            }
        };
        private final int value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SamplingStrategyVerifier implements Internal.EnumVerifier {
            static final Internal.EnumVerifier INSTANCE = new SamplingStrategyVerifier();

            private SamplingStrategyVerifier() {
            }

            @Override // com.google.protobuf.Internal.EnumVerifier
            public boolean isInRange(int i) {
                return SamplingStrategy.forNumber(i) != null;
            }
        }

        static {
            SamplingStrategy samplingStrategy;
            SAMPLING_STRATEGY_EVENT_PROBABILITY = samplingStrategy;
        }

        SamplingStrategy(int i) {
            this.value = i;
        }

        public static SamplingStrategy forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return SAMPLING_STRATEGY_FLOOR;
                case 2:
                    return SAMPLING_STRATEGY_ALWAYS_ON;
                case 3:
                    return SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY;
                case 4:
                    return SAMPLING_STRATEGY_DYNAMIC_EVENT_PROBABILITY;
                case 5:
                    return SAMPLING_STRATEGY_FIXED_EVENT_PROBABILITY;
                default:
                    return null;
            }
        }

        public static Internal.EnumVerifier internalGetVerifier() {
            return SamplingStrategyVerifier.INSTANCE;
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
        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = new SystemHealthProto$SamplingParameters();
        DEFAULT_INSTANCE = systemHealthProto$SamplingParameters;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$SamplingParameters.class, systemHealthProto$SamplingParameters);
    }

    private SystemHealthProto$SamplingParameters() {
    }

    public static SystemHealthProto$SamplingParameters getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static SystemHealthProto$SamplingParameters parseFrom(byte[] bArr) {
        return (SystemHealthProto$SamplingParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSampleRatePermille(long j) {
        this.bitField0_ |= 2;
        this.sampleRatePermille_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSamplingStrategy(SamplingStrategy samplingStrategy) {
        this.samplingStrategy_ = samplingStrategy.getNumber();
        this.bitField0_ |= 4;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$SamplingParameters();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0002\u0003\u0002\u0000\u0000\u0000\u0002ဂ\u0001\u0003ဌ\u0002", new Object[]{"bitField0_", "sampleRatePermille_", "samplingStrategy_", SamplingStrategy.internalGetVerifier()});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$SamplingParameters.class) {
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

    public long getSampleRatePermille() {
        return this.sampleRatePermille_;
    }

    public SamplingStrategy getSamplingStrategy() {
        SamplingStrategy forNumber = SamplingStrategy.forNumber(this.samplingStrategy_);
        return forNumber == null ? SamplingStrategy.UNKNOWN : forNumber;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE);
        }

        public Builder setSampleRatePermille(long j) {
            copyOnWrite();
            ((SystemHealthProto$SamplingParameters) this.instance).setSampleRatePermille(j);
            return this;
        }

        public Builder setSamplingStrategy(SamplingStrategy samplingStrategy) {
            copyOnWrite();
            ((SystemHealthProto$SamplingParameters) this.instance).setSamplingStrategy(samplingStrategy);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
