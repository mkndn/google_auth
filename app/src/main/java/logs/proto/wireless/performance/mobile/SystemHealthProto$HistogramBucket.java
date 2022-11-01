package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$HistogramBucket extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int COUNT_FIELD_NUMBER = 1;
    private static final SystemHealthProto$HistogramBucket DEFAULT_INSTANCE;
    public static final int MAX_FIELD_NUMBER = 3;
    public static final int MIN_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private int bitField0_;
    private int count_;
    private int max_;
    private int min_;

    static {
        SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket = new SystemHealthProto$HistogramBucket();
        DEFAULT_INSTANCE = systemHealthProto$HistogramBucket;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$HistogramBucket.class, systemHealthProto$HistogramBucket);
    }

    private SystemHealthProto$HistogramBucket() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCount(int i) {
        this.bitField0_ |= 1;
        this.count_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMax(int i) {
        this.bitField0_ |= 4;
        this.max_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMin(int i) {
        this.bitField0_ |= 2;
        this.min_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$HistogramBucket();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"bitField0_", "count_", "min_", "max_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$HistogramBucket.class) {
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

    public int getCount() {
        return this.count_;
    }

    public int getMax() {
        return this.max_;
    }

    public int getMin() {
        return this.min_;
    }

    public boolean hasMax() {
        return (this.bitField0_ & 4) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$HistogramBucket.DEFAULT_INSTANCE);
        }

        public Builder setCount(int i) {
            copyOnWrite();
            ((SystemHealthProto$HistogramBucket) this.instance).setCount(i);
            return this;
        }

        public Builder setMax(int i) {
            copyOnWrite();
            ((SystemHealthProto$HistogramBucket) this.instance).setMax(i);
            return this;
        }

        public Builder setMin(int i) {
            copyOnWrite();
            ((SystemHealthProto$HistogramBucket) this.instance).setMin(i);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
