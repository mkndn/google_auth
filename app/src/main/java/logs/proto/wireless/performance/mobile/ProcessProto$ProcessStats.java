package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessProto$ProcessStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANDROID_PROCESS_STATS_FIELD_NUMBER = 1;
    private static final ProcessProto$ProcessStats DEFAULT_INSTANCE;
    public static final int IOS_PROCESS_STATS_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private ProcessProto$AndroidProcessStats androidProcessStats_;
    private int bitField0_;

    static {
        ProcessProto$ProcessStats processProto$ProcessStats = new ProcessProto$ProcessStats();
        DEFAULT_INSTANCE = processProto$ProcessStats;
        GeneratedMessageLite.registerDefaultInstance(ProcessProto$ProcessStats.class, processProto$ProcessStats);
    }

    private ProcessProto$ProcessStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAndroidProcessStats(ProcessProto$AndroidProcessStats processProto$AndroidProcessStats) {
        processProto$AndroidProcessStats.getClass();
        this.androidProcessStats_ = processProto$AndroidProcessStats;
        this.bitField0_ |= 1;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ProcessProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ProcessProto$ProcessStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"bitField0_", "androidProcessStats_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ProcessProto$ProcessStats.class) {
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
            super(ProcessProto$ProcessStats.DEFAULT_INSTANCE);
        }

        public Builder setAndroidProcessStats(ProcessProto$AndroidProcessStats processProto$AndroidProcessStats) {
            copyOnWrite();
            ((ProcessProto$ProcessStats) this.instance).setAndroidProcessStats(processProto$AndroidProcessStats);
            return this;
        }

        /* synthetic */ Builder(ProcessProto$1 processProto$1) {
            this();
        }
    }
}
