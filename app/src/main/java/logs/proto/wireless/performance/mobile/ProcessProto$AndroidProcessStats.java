package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessProto$AndroidProcessStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final ProcessProto$AndroidProcessStats DEFAULT_INSTANCE;
    public static final int IS_IN_FOREGROUND_FIELD_NUMBER = 2;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_ELAPSED_TIME_MS_FIELD_NUMBER = 1;
    public static final int PROCESS_NAME_FIELD_NUMBER = 4;
    public static final int THREAD_COUNT_FIELD_NUMBER = 3;
    private int bitField0_;
    private boolean isInForeground_;
    private long processElapsedTimeMs_;
    private String processName_ = "";
    private int threadCount_;

    static {
        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats = new ProcessProto$AndroidProcessStats();
        DEFAULT_INSTANCE = processProto$AndroidProcessStats;
        GeneratedMessageLite.registerDefaultInstance(ProcessProto$AndroidProcessStats.class, processProto$AndroidProcessStats);
    }

    private ProcessProto$AndroidProcessStats() {
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsInForeground(boolean z) {
        this.bitField0_ |= 2;
        this.isInForeground_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessElapsedTimeMs(long j) {
        this.bitField0_ |= 1;
        this.processElapsedTimeMs_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProcessName(String str) {
        str.getClass();
        this.bitField0_ |= 8;
        this.processName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setThreadCount(int i) {
        this.bitField0_ |= 4;
        this.threadCount_ = i;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ProcessProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ProcessProto$AndroidProcessStats();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004ဈ\u0003", new Object[]{"bitField0_", "processElapsedTimeMs_", "isInForeground_", "threadCount_", "processName_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ProcessProto$AndroidProcessStats.class) {
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
            super(ProcessProto$AndroidProcessStats.DEFAULT_INSTANCE);
        }

        public Builder setIsInForeground(boolean z) {
            copyOnWrite();
            ((ProcessProto$AndroidProcessStats) this.instance).setIsInForeground(z);
            return this;
        }

        public Builder setProcessElapsedTimeMs(long j) {
            copyOnWrite();
            ((ProcessProto$AndroidProcessStats) this.instance).setProcessElapsedTimeMs(j);
            return this;
        }

        @Deprecated
        public Builder setProcessName(String str) {
            copyOnWrite();
            ((ProcessProto$AndroidProcessStats) this.instance).setProcessName(str);
            return this;
        }

        public Builder setThreadCount(int i) {
            copyOnWrite();
            ((ProcessProto$AndroidProcessStats) this.instance).setThreadCount(i);
            return this;
        }

        /* synthetic */ Builder(ProcessProto$1 processProto$1) {
            this();
        }
    }
}
