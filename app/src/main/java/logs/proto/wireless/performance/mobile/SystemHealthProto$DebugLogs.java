package logs.proto.wireless.performance.mobile;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$DebugLogs extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final SystemHealthProto$DebugLogs DEFAULT_INSTANCE;
    public static final int MESSAGE_ID_FIELD_NUMBER = 1;
    private static volatile Parser PARSER = null;
    public static final int THREAD_ID_FIELD_NUMBER = 3;
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 2;
    private int messageIdMemoizedSerializedSize = -1;
    private int timestampMsMemoizedSerializedSize = -1;
    private int threadIdMemoizedSerializedSize = -1;
    private Internal.LongList messageId_ = emptyLongList();
    private Internal.LongList timestampMs_ = emptyLongList();
    private Internal.IntList threadId_ = emptyIntList();

    static {
        SystemHealthProto$DebugLogs systemHealthProto$DebugLogs = new SystemHealthProto$DebugLogs();
        DEFAULT_INSTANCE = systemHealthProto$DebugLogs;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$DebugLogs.class, systemHealthProto$DebugLogs);
    }

    private SystemHealthProto$DebugLogs() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessageId(long j) {
        ensureMessageIdIsMutable();
        this.messageId_.addLong(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addThreadId(int i) {
        ensureThreadIdIsMutable();
        this.threadId_.addInt(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTimestampMs(long j) {
        ensureTimestampMsIsMutable();
        this.timestampMs_.addLong(j);
    }

    private void ensureMessageIdIsMutable() {
        Internal.LongList longList = this.messageId_;
        if (!longList.isModifiable()) {
            this.messageId_ = GeneratedMessageLite.mutableCopy(longList);
        }
    }

    private void ensureThreadIdIsMutable() {
        Internal.IntList intList = this.threadId_;
        if (!intList.isModifiable()) {
            this.threadId_ = GeneratedMessageLite.mutableCopy(intList);
        }
    }

    private void ensureTimestampMsIsMutable() {
        Internal.LongList longList = this.timestampMs_;
        if (!longList.isModifiable()) {
            this.timestampMs_ = GeneratedMessageLite.mutableCopy(longList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$DebugLogs();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0003\u0000\u0001%\u0002%\u0003'", new Object[]{"messageId_", "timestampMs_", "threadId_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$DebugLogs.class) {
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
            super(SystemHealthProto$DebugLogs.DEFAULT_INSTANCE);
        }

        public Builder addMessageId(long j) {
            copyOnWrite();
            ((SystemHealthProto$DebugLogs) this.instance).addMessageId(j);
            return this;
        }

        public Builder addThreadId(int i) {
            copyOnWrite();
            ((SystemHealthProto$DebugLogs) this.instance).addThreadId(i);
            return this;
        }

        public Builder addTimestampMs(long j) {
            copyOnWrite();
            ((SystemHealthProto$DebugLogs) this.instance).addTimestampMs(j);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
