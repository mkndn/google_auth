package com.google.common.logging.proto2api;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClickTrackingCgi$ClickTrackingCGI extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final ClickTrackingCgi$ClickTrackingCGI DEFAULT_INSTANCE;
    public static final int DO_NOT_LOG_URL_FIELD_NUMBER = 10;
    public static final int ELEMENT_INDEX_FIELD_NUMBER = 5;
    public static final int MESSAGE_SET_EXTENSION_FIELD_NUMBER = 15872052;
    public static final int NON_ARCHIVAL_VE_INDEX_FIELD_NUMBER = 11;
    public static final int ODELAY_SE_LINKER_FIELD_NUMBER = 8;
    public static final int PAGE_START_FIELD_NUMBER = 7;
    private static volatile Parser PARSER = null;
    public static final int RESULT_FPRINT_FIELD_NUMBER = 12;
    public static final int RESULT_GROUP_ELEMENT_INDEX_FIELD_NUMBER = 9;
    public static final int RESULT_INDEX_FIELD_NUMBER = 6;
    public static final int VE_EVENT_ID_FIELD_NUMBER = 13;
    public static final int VE_INDEX_FIELD_NUMBER = 1;
    public static final int VE_OFFSET_IDENTIFIER_FIELD_NUMBER = 15;
    public static final int VE_TYPE_FIELD_NUMBER = 2;
    public static final int YOUTUBE_VE_COUNTER_FIELD_NUMBER = 14;
    private int bitField0_;
    private Eventid$ClientEventIdMessage veEventId_;
    private byte memoizedIsInitialized = 2;
    private int veIndex_ = -1;
    private int nonArchivalVeIndex_ = -1;
    private int elementIndex_ = -1;
    private int resultIndex_ = -1;
    private int resultGroupElementIndex_ = -1;
    private int youtubeVeCounter_ = -1;

    static {
        ClickTrackingCgi$ClickTrackingCGI clickTrackingCgi$ClickTrackingCGI = new ClickTrackingCgi$ClickTrackingCGI();
        DEFAULT_INSTANCE = clickTrackingCgi$ClickTrackingCGI;
        GeneratedMessageLite.registerDefaultInstance(ClickTrackingCgi$ClickTrackingCGI.class, clickTrackingCgi$ClickTrackingCGI);
    }

    private ClickTrackingCgi$ClickTrackingCGI() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (ClickTrackingCgi$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ClickTrackingCgi$ClickTrackingCGI();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\r\r\u0001\u0000\u0000\u0001\rᐉ\u000b", new Object[]{"bitField0_", "veEventId_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (ClickTrackingCgi$ClickTrackingCGI.class) {
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
            super(ClickTrackingCgi$ClickTrackingCGI.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(ClickTrackingCgi$1 clickTrackingCgi$1) {
            this();
        }
    }
}
