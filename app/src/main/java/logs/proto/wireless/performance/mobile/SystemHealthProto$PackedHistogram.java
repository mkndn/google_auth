package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PackedHistogram extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$PackedHistogram DEFAULT_INSTANCE;
    public static final int LOWER_BOUND_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int POPULATION_FIELD_NUMBER = 1;
    private int populationMemoizedSerializedSize = -1;
    private int lowerBoundMemoizedSerializedSize = -1;
    private Internal.IntList population_ = emptyIntList();
    private Internal.IntList lowerBound_ = emptyIntList();

    static {
        SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram = new SystemHealthProto$PackedHistogram();
        DEFAULT_INSTANCE = systemHealthProto$PackedHistogram;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PackedHistogram.class, systemHealthProto$PackedHistogram);
    }

    private SystemHealthProto$PackedHistogram() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLowerBound(int i) {
        ensureLowerBoundIsMutable();
        this.lowerBound_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addPopulation(int i) {
        ensurePopulationIsMutable();
        this.population_.addInt(i);
    }

    private void ensureLowerBoundIsMutable() {
        Internal.IntList intList = this.lowerBound_;
        if (!intList.isModifiable()) {
            this.lowerBound_ = GeneratedMessageLite.mutableCopy(intList);
        }
    }

    private void ensurePopulationIsMutable() {
        Internal.IntList intList = this.population_;
        if (!intList.isModifiable()) {
            this.population_ = GeneratedMessageLite.mutableCopy(intList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$PackedHistogram();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001'\u0002'", new Object[]{"population_", "lowerBound_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PackedHistogram.class) {
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
            super(SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE);
        }

        public Builder addLowerBound(int i) {
            copyOnWrite();
            ((SystemHealthProto$PackedHistogram) this.instance).addLowerBound(i);
            return this;
        }

        public Builder addPopulation(int i) {
            copyOnWrite();
            ((SystemHealthProto$PackedHistogram) this.instance).addPopulation(i);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
