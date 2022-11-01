package com.google.common.logging;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VisualElementLite$VisualElementLiteProto extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int AD_IMPRESSION_INDEX_FIELD_NUMBER = 149;
    public static final int CONTAINS_ELEMENTS_FIELD_NUMBER = 4;
    public static final int DATA_ELEMENT_FIELD_NUMBER = 232;
    private static final VisualElementLite$VisualElementLiteProto DEFAULT_INSTANCE;
    public static final int ELEMENT_INDEX_FIELD_NUMBER = 3;
    public static final int FEATURE_TREE_REF_FIELD_NUMBER = 11;
    public static final int LANGUAGE_FIELD_NUMBER = 17;
    private static volatile Parser PARSER = null;
    public static final int RESULT_INDEX_FIELD_NUMBER = 7;
    public static final int TARGET_URL_FIELD_NUMBER = 5;
    public static final int UI_TYPE_FIELD_NUMBER = 1;
    public static final int VISIBLE_FIELD_NUMBER = 6;
    private byte memoizedIsInitialized = 2;
    private int elementIndex_ = -1;
    private Internal.IntList containsElements_ = emptyIntList();
    private String targetUrl_ = "";
    private String language_ = "";
    private int adImpressionIndex_ = -1;

    static {
        VisualElementLite$VisualElementLiteProto visualElementLite$VisualElementLiteProto = new VisualElementLite$VisualElementLiteProto();
        DEFAULT_INSTANCE = visualElementLite$VisualElementLiteProto;
        GeneratedMessageLite.registerDefaultInstance(VisualElementLite$VisualElementLiteProto.class, visualElementLite$VisualElementLiteProto);
    }

    private VisualElementLite$VisualElementLiteProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (VisualElementLite$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new VisualElementLite$VisualElementLiteProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (VisualElementLite$VisualElementLiteProto.class) {
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
            super(VisualElementLite$VisualElementLiteProto.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(VisualElementLite$1 visualElementLite$1) {
            this();
        }
    }
}
