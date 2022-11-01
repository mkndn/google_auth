package com.google.knowledge.graph.protomesh;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Protomesh$StagingProto extends GeneratedMessageLite.ExtendableMessage implements MessageLiteOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 8;
    public static final int BYTES_VALUE_FIELD_NUMBER = 12;
    private static final Protomesh$StagingProto DEFAULT_INSTANCE;
    public static final int DOUBLE_VALUE_FIELD_NUMBER = 1;
    public static final int FIXED32_VALUE_FIELD_NUMBER = 7;
    public static final int FIXED64_VALUE_FIELD_NUMBER = 6;
    public static final int FLOAT_VALUE_FIELD_NUMBER = 2;
    public static final int INT32_VALUE_FIELD_NUMBER = 5;
    public static final int INT64_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser PARSER = null;
    public static final int SFIXED32_VALUE_FIELD_NUMBER = 15;
    public static final int SFIXED64_VALUE_FIELD_NUMBER = 16;
    public static final int SINT32_VALUE_FIELD_NUMBER = 17;
    public static final int SINT64_VALUE_FIELD_NUMBER = 18;
    public static final int STRING_VALUE_FIELD_NUMBER = 9;
    public static final int UINT32_VALUE_FIELD_NUMBER = 13;
    public static final int UINT64_VALUE_FIELD_NUMBER = 4;
    private byte memoizedIsInitialized = 2;
    private String stringValue_ = "";
    private ByteString bytesValue_ = ByteString.EMPTY;

    static {
        Protomesh$StagingProto protomesh$StagingProto = new Protomesh$StagingProto();
        DEFAULT_INSTANCE = protomesh$StagingProto;
        GeneratedMessageLite.registerDefaultInstance(Protomesh$StagingProto.class, protomesh$StagingProto);
    }

    private Protomesh$StagingProto() {
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (Protomesh$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Protomesh$StagingProto();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Protomesh$StagingProto.class) {
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
            super(Protomesh$StagingProto.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(Protomesh$1 protomesh$1) {
            this();
        }
    }
}
