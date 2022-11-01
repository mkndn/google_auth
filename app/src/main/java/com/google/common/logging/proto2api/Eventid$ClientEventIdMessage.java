package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Eventid$ClientEventIdMessage extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int BASE_FIELD_NUMBER = 1;
    public static final int CLIENT_COUNTER_FIELD_NUMBER = 2;
    private static final Eventid$ClientEventIdMessage DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private Eventid$EventIdMessage base_;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;

    static {
        Eventid$ClientEventIdMessage eventid$ClientEventIdMessage = new Eventid$ClientEventIdMessage();
        DEFAULT_INSTANCE = eventid$ClientEventIdMessage;
        GeneratedMessageLite.registerDefaultInstance(Eventid$ClientEventIdMessage.class, eventid$ClientEventIdMessage);
    }

    private Eventid$ClientEventIdMessage() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Eventid$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Eventid$ClientEventIdMessage();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "base_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Eventid$ClientEventIdMessage.class) {
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
            super(Eventid$ClientEventIdMessage.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(Eventid$1 eventid$1) {
            this();
        }
    }
}
