package com.google.common.logging;

import com.google.common.logging.proto2api.Eventid$ClientEventIdMessage;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientVisualElements$ClientVisualElementsProto extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CORE_EVENT_METADATA_FIELD_NUMBER = 9;
    private static final ClientVisualElements$ClientVisualElementsProto DEFAULT_INSTANCE;
    public static final int EPHEMERAL_PARENT_EVENT_ID_FIELD_NUMBER = 6;
    public static final int EVENT_ID_FIELD_NUMBER = 1;
    public static final int GRAFTS_FIELD_NUMBER = 7;
    public static final int GRAFT_TYPE_FIELD_NUMBER = 5;
    public static final int PARENT_EVENT_ID_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int REQUEST_CONTEXT_FIELD_NUMBER = 4;
    public static final int SNAPSHOT_PARENT_EVENT_ID_FIELD_NUMBER = 8;
    public static final int VISUAL_ELEMENTS_FIELD_NUMBER = 2;
    private int bitField0_;
    private CoreEventMetadataOuterClass$CoreEventMetadata coreEventMetadata_;
    private Eventid$ClientEventIdMessage eventId_;
    private Eventid$ClientEventIdMessage parentEventId_;
    private VisualElementLite$ClientRequestContext requestContext_;
    private Eventid$ClientEventIdMessage snapshotParentEventId_;
    private byte memoizedIsInitialized = 2;
    private Internal.ProtobufList visualElements_ = emptyProtobufList();
    private String ephemeralParentEventId_ = "";

    static {
        ClientVisualElements$ClientVisualElementsProto clientVisualElements$ClientVisualElementsProto = new ClientVisualElements$ClientVisualElementsProto();
        DEFAULT_INSTANCE = clientVisualElements$ClientVisualElementsProto;
        GeneratedMessageLite.registerDefaultInstance(ClientVisualElements$ClientVisualElementsProto.class, clientVisualElements$ClientVisualElementsProto);
    }

    private ClientVisualElements$ClientVisualElementsProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ClientVisualElements$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ClientVisualElements$ClientVisualElementsProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\t\u0005\u0000\u0000\u0005\u0001ᐉ\u0000\u0003ᐉ\u0001\u0004ᐉ\u0004\bᐉ\u0003\tᐉ\u0007", new Object[]{"bitField0_", "eventId_", "parentEventId_", "requestContext_", "snapshotParentEventId_", "coreEventMetadata_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClientVisualElements$ClientVisualElementsProto.class) {
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
            super(ClientVisualElements$ClientVisualElementsProto.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(ClientVisualElements$1 clientVisualElements$1) {
            this();
        }
    }
}
