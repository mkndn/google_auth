package com.google.protos.experiments.proto;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypedFeatures$StringListParam extends GeneratedMessageLite implements MessageLiteOrBuilder {
    private static final TypedFeatures$StringListParam DEFAULT_INSTANCE;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    private static volatile Parser PARSER;
    private Internal.ProtobufList element_ = GeneratedMessageLite.emptyProtobufList();

    static {
        TypedFeatures$StringListParam typedFeatures$StringListParam = new TypedFeatures$StringListParam();
        DEFAULT_INSTANCE = typedFeatures$StringListParam;
        GeneratedMessageLite.registerDefaultInstance(TypedFeatures$StringListParam.class, typedFeatures$StringListParam);
    }

    private TypedFeatures$StringListParam() {
    }

    public static TypedFeatures$StringListParam parseFrom(byte[] bArr) {
        return (TypedFeatures$StringListParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (TypedFeatures$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new TypedFeatures$StringListParam();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"element_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (TypedFeatures$StringListParam.class) {
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

    public List getElementList() {
        return this.element_;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(TypedFeatures$StringListParam.DEFAULT_INSTANCE);
        }

        /* synthetic */ Builder(TypedFeatures$1 typedFeatures$1) {
            this();
        }
    }
}
