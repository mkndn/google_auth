package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Eventid$EventIdMessage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final Eventid$EventIdMessage DEFAULT_INSTANCE;
    public static final int MESSAGE_SET_EXTENSION_FIELD_NUMBER = 4156379;
    private static volatile Parser PARSER = null;
    public static final int PROCESS_ID_FIELD_NUMBER = 3;
    public static final int SERVER_IP_FIELD_NUMBER = 2;
    public static final int TIME_USEC_FIELD_NUMBER = 1;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private int processId_;
    private int serverIp_;
    private long timeUsec_;

    static {
        Eventid$EventIdMessage eventid$EventIdMessage = new Eventid$EventIdMessage();
        DEFAULT_INSTANCE = eventid$EventIdMessage;
        GeneratedMessageLite.registerDefaultInstance(Eventid$EventIdMessage.class, eventid$EventIdMessage);
    }

    private Eventid$EventIdMessage() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Eventid$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Eventid$EventIdMessage();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔂ\u0000\u0002ᔆ\u0001\u0003ᔆ\u0002", new Object[]{"bitField0_", "timeUsec_", "serverIp_", "processId_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Eventid$EventIdMessage.class) {
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
            super(Eventid$EventIdMessage.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(Eventid$1 eventid$1) {
            this();
        }
    }
}
