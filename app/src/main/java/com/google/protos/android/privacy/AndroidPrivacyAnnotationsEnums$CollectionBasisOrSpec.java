package com.google.protos.android.privacy;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int AND_SPEC_FIELD_NUMBER = 2;
    public static final int BASIS_FIELD_NUMBER = 1;
    private static final AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec DEFAULT_INSTANCE;
    private static volatile Parser PARSER;
    private static final Internal.ListAdapter.Converter basis_converter_ = new Internal.ListAdapter.Converter() { // from class: com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.1
        @Override // com.google.protobuf.Internal.ListAdapter.Converter
        public AndroidPrivacyAnnotationsEnums$CollectionBasis convert(Integer num) {
            AndroidPrivacyAnnotationsEnums$CollectionBasis forNumber = AndroidPrivacyAnnotationsEnums$CollectionBasis.forNumber(num.intValue());
            return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE : forNumber;
        }
    };
    private Internal.IntList basis_ = emptyIntList();
    private Internal.ProtobufList andSpec_ = emptyProtobufList();

    static {
        AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec = new AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec();
        DEFAULT_INSTANCE = androidPrivacyAnnotationsEnums$CollectionBasisOrSpec;
        GeneratedMessageLite.registerDefaultInstance(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.class, androidPrivacyAnnotationsEnums$CollectionBasisOrSpec);
    }

    private AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAndSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
        androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getClass();
        ensureAndSpecIsMutable();
        this.andSpec_.add(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis) {
        androidPrivacyAnnotationsEnums$CollectionBasis.getClass();
        ensureBasisIsMutable();
        this.basis_.addInt(androidPrivacyAnnotationsEnums$CollectionBasis.getNumber());
    }

    private void ensureAndSpecIsMutable() {
        Internal.ProtobufList protobufList = this.andSpec_;
        if (!protobufList.isModifiable()) {
            this.andSpec_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureBasisIsMutable() {
        Internal.IntList intList = this.basis_;
        if (!intList.isModifiable()) {
            this.basis_ = GeneratedMessageLite.mutableCopy(intList);
        }
    }

    public static AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidPrivacyAnnotationsEnums$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001,\u0002\u001b", new Object[]{"basis_", AndroidPrivacyAnnotationsEnums$CollectionBasis.internalGetVerifier(), "andSpec_", AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.class) {
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

    public List getAndSpecList() {
        return this.andSpec_;
    }

    public AndroidPrivacyAnnotationsEnums$CollectionBasis getBasis(int i) {
        AndroidPrivacyAnnotationsEnums$CollectionBasis forNumber = AndroidPrivacyAnnotationsEnums$CollectionBasis.forNumber(this.basis_.getInt(i));
        return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE : forNumber;
    }

    public int getBasisCount() {
        return this.basis_.size();
    }

    public List getBasisList() {
        return new Internal.ListAdapter(this.basis_, basis_converter_);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.DEFAULT_INSTANCE);
        }

        public Builder addAndSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
            copyOnWrite();
            ((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) this.instance).addAndSpec(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec);
            return this;
        }

        public Builder addBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis) {
            copyOnWrite();
            ((AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) this.instance).addBasis(androidPrivacyAnnotationsEnums$CollectionBasis);
            return this;
        }

        /* synthetic */ Builder(AndroidPrivacyAnnotationsEnums$1 androidPrivacyAnnotationsEnums$1) {
            this();
        }
    }
}
