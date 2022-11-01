package com.google.common.logging;

import com.google.common.logging.proto2api.Eventid$ClientEventIdMessage;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoreEventMetadataOuterClass$CoreEventMetadata extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final CoreEventMetadataOuterClass$CoreEventMetadata DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int ROOT_EVENT_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private byte memoizedIsInitialized = 2;
    private Eventid$ClientEventIdMessage rootEventId_;

    static {
        CoreEventMetadataOuterClass$CoreEventMetadata coreEventMetadataOuterClass$CoreEventMetadata = new CoreEventMetadataOuterClass$CoreEventMetadata();
        DEFAULT_INSTANCE = coreEventMetadataOuterClass$CoreEventMetadata;
        GeneratedMessageLite.registerDefaultInstance(CoreEventMetadataOuterClass$CoreEventMetadata.class, coreEventMetadataOuterClass$CoreEventMetadata);
    }

    private CoreEventMetadataOuterClass$CoreEventMetadata() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (CoreEventMetadataOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new CoreEventMetadataOuterClass$CoreEventMetadata();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"bitField0_", "rootEventId_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (CoreEventMetadataOuterClass$CoreEventMetadata.class) {
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
            super(CoreEventMetadataOuterClass$CoreEventMetadata.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(CoreEventMetadataOuterClass$1 coreEventMetadataOuterClass$1) {
            this();
        }
    }
}
