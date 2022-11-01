package com.google.protos.android.privacy;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidPrivacyAnnotationsEnums$CollectionBasisSpec extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int AND_SPEC_FIELD_NUMBER = 2;
    public static final int BASIS_FIELD_NUMBER = 1;
    private static final AndroidPrivacyAnnotationsEnums$CollectionBasisSpec DEFAULT_INSTANCE;
    public static final int OR_SPEC_FIELD_NUMBER = 3;
    private static volatile Parser PARSER;
    private int orAndBasisCase_ = 0;
    private Object orAndBasis_;

    static {
        AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec = new AndroidPrivacyAnnotationsEnums$CollectionBasisSpec();
        DEFAULT_INSTANCE = androidPrivacyAnnotationsEnums$CollectionBasisSpec;
        GeneratedMessageLite.registerDefaultInstance(AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.class, androidPrivacyAnnotationsEnums$CollectionBasisSpec);
    }

    private AndroidPrivacyAnnotationsEnums$CollectionBasisSpec() {
    }

    public static AndroidPrivacyAnnotationsEnums$CollectionBasisSpec getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAndSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
        androidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getClass();
        this.orAndBasis_ = androidPrivacyAnnotationsEnums$CollectionBasisAndSpec;
        this.orAndBasisCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis) {
        this.orAndBasis_ = Integer.valueOf(androidPrivacyAnnotationsEnums$CollectionBasis.getNumber());
        this.orAndBasisCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOrSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec) {
        androidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getClass();
        this.orAndBasis_ = androidPrivacyAnnotationsEnums$CollectionBasisOrSpec;
        this.orAndBasisCase_ = 3;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AndroidPrivacyAnnotationsEnums$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AndroidPrivacyAnnotationsEnums$CollectionBasisSpec();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဿ\u0000\u0002ြ\u0000\u0003ြ\u0000", new Object[]{"orAndBasis_", "orAndBasisCase_", AndroidPrivacyAnnotationsEnums$CollectionBasis.internalGetVerifier(), AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.class, AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.class) {
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

    public AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec getAndSpec() {
        if (this.orAndBasisCase_ == 2) {
            return (AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec) this.orAndBasis_;
        }
        return AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec.getDefaultInstance();
    }

    public AndroidPrivacyAnnotationsEnums$CollectionBasis getBasis() {
        if (this.orAndBasisCase_ == 1) {
            AndroidPrivacyAnnotationsEnums$CollectionBasis forNumber = AndroidPrivacyAnnotationsEnums$CollectionBasis.forNumber(((Integer) this.orAndBasis_).intValue());
            return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE : forNumber;
        }
        return AndroidPrivacyAnnotationsEnums$CollectionBasis.CB_NONE;
    }

    public AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec getOrSpec() {
        if (this.orAndBasisCase_ == 3) {
            return (AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec) this.orAndBasis_;
        }
        return AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec.getDefaultInstance();
    }

    public boolean hasAndSpec() {
        return this.orAndBasisCase_ == 2;
    }

    public boolean hasBasis() {
        return this.orAndBasisCase_ == 1;
    }

    public boolean hasOrSpec() {
        return this.orAndBasisCase_ == 3;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.DEFAULT_INSTANCE);
        }

        public Builder setAndSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisAndSpec androidPrivacyAnnotationsEnums$CollectionBasisAndSpec) {
            copyOnWrite();
            ((AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) this.instance).setAndSpec(androidPrivacyAnnotationsEnums$CollectionBasisAndSpec);
            return this;
        }

        public Builder setBasis(AndroidPrivacyAnnotationsEnums$CollectionBasis androidPrivacyAnnotationsEnums$CollectionBasis) {
            copyOnWrite();
            ((AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) this.instance).setBasis(androidPrivacyAnnotationsEnums$CollectionBasis);
            return this;
        }

        public Builder setOrSpec(AndroidPrivacyAnnotationsEnums$CollectionBasisOrSpec androidPrivacyAnnotationsEnums$CollectionBasisOrSpec) {
            copyOnWrite();
            ((AndroidPrivacyAnnotationsEnums$CollectionBasisSpec) this.instance).setOrSpec(androidPrivacyAnnotationsEnums$CollectionBasisOrSpec);
            return this;
        }

        /* synthetic */ Builder(AndroidPrivacyAnnotationsEnums$1 androidPrivacyAnnotationsEnums$1) {
            this();
        }
    }
}
