package com.google.common.logging;

import com.google.common.logging.proto2api.ClickTrackingCgi$ClickTrackingCGI;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VisualElementLite$ClientRequestContext extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int ANCESTRY_FIELD_NUMBER = 14;
    public static final int CARDINAL_DIRECTION_FIELD_NUMBER = 4;
    public static final int CLICK_TRACKING_CGI_FIELD_NUMBER = 16;
    public static final int CLIENT_INTERACTION_METADATA_FIELD_NUMBER = 15;
    private static final VisualElementLite$ClientRequestContext DEFAULT_INSTANCE;
    public static final int ELEMENT_INDEX_FIELD_NUMBER = 8;
    public static final int IMAGE_REFERRER_URL_FIELD_NUMBER = 10;
    public static final int IMAGE_URL_FIELD_NUMBER = 9;
    public static final int INTERACTION_CONTEXT_FIELD_NUMBER = 6;
    private static volatile Parser PARSER = null;
    public static final int PRIMARY_USER_ACTION_FIELD_NUMBER = 3;
    public static final int REFERRER_ID_FIELD_NUMBER = 13;
    public static final int RESULT_INDEX_FIELD_NUMBER = 7;
    public static final int THUMBNAIL_ID_FIELD_NUMBER = 12;
    public static final int TOGGLE_STATE_FIELD_NUMBER = 5;
    public static final int UI_TYPE_FIELD_NUMBER = 1;
    public static final int VED_FIELD_NUMBER = 11;
    public static final int VE_INDEX_FIELD_NUMBER = 2;
    private int bitField0_;
    private ClickTrackingCgi$ClickTrackingCGI clickTrackingCgi_;
    private byte memoizedIsInitialized = 2;
    private String ved_ = "";
    private int uiType_ = -1;
    private int veIndex_ = -1;
    private int interactionContext_ = -1;
    private int resultIndex_ = -1;
    private int elementIndex_ = -1;
    private String imageUrl_ = "";
    private String imageReferrerUrl_ = "";
    private String thumbnailId_ = "";
    private String referrerId_ = "";
    private Internal.ProtobufList ancestry_ = emptyProtobufList();

    static {
        VisualElementLite$ClientRequestContext visualElementLite$ClientRequestContext = new VisualElementLite$ClientRequestContext();
        DEFAULT_INSTANCE = visualElementLite$ClientRequestContext;
        GeneratedMessageLite.registerDefaultInstance(VisualElementLite$ClientRequestContext.class, visualElementLite$ClientRequestContext);
    }

    private VisualElementLite$ClientRequestContext() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (VisualElementLite$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new VisualElementLite$ClientRequestContext();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0010\u0010\u0001\u0000\u0000\u0001\u0010ᐉ\u0000", new Object[]{"bitField0_", "clickTrackingCgi_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (VisualElementLite$ClientRequestContext.class) {
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
    public final class Builder extends GeneratedMessageLite.ExtendableBuilder implements MessageLiteOrBuilder {
        private Builder() {
            super(VisualElementLite$ClientRequestContext.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(VisualElementLite$1 visualElementLite$1) {
            this();
        }
    }
}
