package com.google.protos.collection_basis_verifier.logging;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionBasisSpec;
import com.google.protos.android.privacy.AndroidPrivacyAnnotationsEnums$CollectionUseCase;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VerificationFailureLogOuterClass$VerificationFailureLog extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int ANY_URL_FIELD_NUMBER = 5;
    public static final int APP_NAME_FIELD_NUMBER = 1;
    public static final int APP_VERSION_CODE_FIELD_NUMBER = 12;
    public static final int BASIS_EXPRESSION_FIELD_NUMBER = 13;
    public static final int BASIS_FIELD_NUMBER = 8;
    public static final int DATA_LENGTH_FIELD_NUMBER = 4;
    private static final VerificationFailureLogOuterClass$VerificationFailureLog DEFAULT_INSTANCE;
    public static final int FEATURE_ID_FIELD_NUMBER = 3;
    public static final int FIELD_PATH_FIELD_NUMBER = 9;
    private static volatile Parser PARSER = null;
    public static final int PROTO_ID_FIELD_NUMBER = 2;
    public static final int SETTING_NAME_FIELD_NUMBER = 10;
    public static final int STACK_TRACE_FIELD_NUMBER = 11;
    public static final int USE_CASE_FIELD_NUMBER = 7;
    public static final int VERIFICATION_FAILURE_FIELD_NUMBER = 6;
    private int appVersionCode_;
    private AndroidPrivacyAnnotationsEnums$CollectionBasisSpec basisExpression_;
    private int bitField0_;
    private long dataLength_;
    private long featureId_;
    private long protoId_;
    private int useCase_;
    private int verificationFailure_;
    private String appName_ = "";
    private String anyUrl_ = "";
    private Internal.LongList fieldPath_ = emptyLongList();
    private String settingName_ = "";
    private String stackTrace_ = "";

    static {
        VerificationFailureLogOuterClass$VerificationFailureLog verificationFailureLogOuterClass$VerificationFailureLog = new VerificationFailureLogOuterClass$VerificationFailureLog();
        DEFAULT_INSTANCE = verificationFailureLogOuterClass$VerificationFailureLog;
        GeneratedMessageLite.registerDefaultInstance(VerificationFailureLogOuterClass$VerificationFailureLog.class, verificationFailureLogOuterClass$VerificationFailureLog);
    }

    private VerificationFailureLogOuterClass$VerificationFailureLog() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllFieldPath(Iterable iterable) {
        ensureFieldPathIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.fieldPath_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFieldPath(long j) {
        ensureFieldPathIsMutable();
        this.fieldPath_.addLong(j);
    }

    private void ensureFieldPathIsMutable() {
        Internal.LongList longList = this.fieldPath_;
        if (!longList.isModifiable()) {
            this.fieldPath_ = GeneratedMessageLite.mutableCopy(longList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnyUrl(String str) {
        str.getClass();
        this.bitField0_ |= 32;
        this.anyUrl_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppName(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.appName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppVersionCode(int i) {
        this.bitField0_ |= 2;
        this.appVersionCode_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBasisExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec) {
        androidPrivacyAnnotationsEnums$CollectionBasisSpec.getClass();
        this.basisExpression_ = androidPrivacyAnnotationsEnums$CollectionBasisSpec;
        this.bitField0_ |= 512;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDataLength(long j) {
        this.bitField0_ |= 16;
        this.dataLength_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFeatureId(long j) {
        this.bitField0_ |= 8;
        this.featureId_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProtoId(long j) {
        this.bitField0_ |= 4;
        this.protoId_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStackTrace(String str) {
        str.getClass();
        this.bitField0_ |= 2048;
        this.stackTrace_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUseCase(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase) {
        this.useCase_ = androidPrivacyAnnotationsEnums$CollectionUseCase.getNumber();
        this.bitField0_ |= 128;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVerificationFailure(VerificationFailureEnum$VerificationFailure verificationFailureEnum$VerificationFailure) {
        this.verificationFailure_ = verificationFailureEnum$VerificationFailure.getNumber();
        this.bitField0_ |= 64;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (VerificationFailureLogOuterClass$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new VerificationFailureLogOuterClass$VerificationFailureLog();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\f\u0000\u0001\u0001\r\f\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0002\u0003ဂ\u0003\u0004ဂ\u0004\u0005ဈ\u0005\u0006ဌ\u0006\u0007ဌ\u0007\t\u0014\nဈ\n\u000bဈ\u000b\fင\u0001\rဉ\t", new Object[]{"bitField0_", "appName_", "protoId_", "featureId_", "dataLength_", "anyUrl_", "verificationFailure_", VerificationFailureEnum$VerificationFailure.internalGetVerifier(), "useCase_", AndroidPrivacyAnnotationsEnums$CollectionUseCase.internalGetVerifier(), "fieldPath_", "settingName_", "stackTrace_", "appVersionCode_", "basisExpression_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (VerificationFailureLogOuterClass$VerificationFailureLog.class) {
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

    public String getAnyUrl() {
        return this.anyUrl_;
    }

    public String getAppName() {
        return this.appName_;
    }

    public int getAppVersionCode() {
        return this.appVersionCode_;
    }

    public AndroidPrivacyAnnotationsEnums$CollectionBasisSpec getBasisExpression() {
        AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec = this.basisExpression_;
        return androidPrivacyAnnotationsEnums$CollectionBasisSpec == null ? AndroidPrivacyAnnotationsEnums$CollectionBasisSpec.getDefaultInstance() : androidPrivacyAnnotationsEnums$CollectionBasisSpec;
    }

    public long getDataLength() {
        return this.dataLength_;
    }

    public long getFeatureId() {
        return this.featureId_;
    }

    public List getFieldPathList() {
        return this.fieldPath_;
    }

    public long getProtoId() {
        return this.protoId_;
    }

    public String getSettingName() {
        return this.settingName_;
    }

    public String getStackTrace() {
        return this.stackTrace_;
    }

    public AndroidPrivacyAnnotationsEnums$CollectionUseCase getUseCase() {
        AndroidPrivacyAnnotationsEnums$CollectionUseCase forNumber = AndroidPrivacyAnnotationsEnums$CollectionUseCase.forNumber(this.useCase_);
        return forNumber == null ? AndroidPrivacyAnnotationsEnums$CollectionUseCase.UC_DEFAULT : forNumber;
    }

    public VerificationFailureEnum$VerificationFailure getVerificationFailure() {
        VerificationFailureEnum$VerificationFailure forNumber = VerificationFailureEnum$VerificationFailure.forNumber(this.verificationFailure_);
        return forNumber == null ? VerificationFailureEnum$VerificationFailure.VF_UNKNOWN : forNumber;
    }

    public boolean hasAnyUrl() {
        return (this.bitField0_ & 32) != 0;
    }

    public boolean hasAppName() {
        return (this.bitField0_ & 1) != 0;
    }

    public boolean hasAppVersionCode() {
        return (this.bitField0_ & 2) != 0;
    }

    public boolean hasBasisExpression() {
        return (this.bitField0_ & 512) != 0;
    }

    public boolean hasDataLength() {
        return (this.bitField0_ & 16) != 0;
    }

    public boolean hasFeatureId() {
        return (this.bitField0_ & 8) != 0;
    }

    public boolean hasProtoId() {
        return (this.bitField0_ & 4) != 0;
    }

    public boolean hasSettingName() {
        return (this.bitField0_ & 1024) != 0;
    }

    public boolean hasStackTrace() {
        return (this.bitField0_ & 2048) != 0;
    }

    public boolean hasUseCase() {
        return (this.bitField0_ & 128) != 0;
    }

    public boolean hasVerificationFailure() {
        return (this.bitField0_ & 64) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(VerificationFailureLogOuterClass$VerificationFailureLog.DEFAULT_INSTANCE);
        }

        public Builder addAllFieldPath(Iterable iterable) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).addAllFieldPath(iterable);
            return this;
        }

        public Builder addFieldPath(long j) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).addFieldPath(j);
            return this;
        }

        public boolean hasVerificationFailure() {
            return ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).hasVerificationFailure();
        }

        public Builder setAnyUrl(String str) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setAnyUrl(str);
            return this;
        }

        public Builder setAppName(String str) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setAppName(str);
            return this;
        }

        public Builder setAppVersionCode(int i) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setAppVersionCode(i);
            return this;
        }

        public Builder setBasisExpression(AndroidPrivacyAnnotationsEnums$CollectionBasisSpec androidPrivacyAnnotationsEnums$CollectionBasisSpec) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setBasisExpression(androidPrivacyAnnotationsEnums$CollectionBasisSpec);
            return this;
        }

        public Builder setDataLength(long j) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setDataLength(j);
            return this;
        }

        public Builder setFeatureId(long j) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setFeatureId(j);
            return this;
        }

        public Builder setProtoId(long j) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setProtoId(j);
            return this;
        }

        public Builder setStackTrace(String str) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setStackTrace(str);
            return this;
        }

        public Builder setUseCase(AndroidPrivacyAnnotationsEnums$CollectionUseCase androidPrivacyAnnotationsEnums$CollectionUseCase) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setUseCase(androidPrivacyAnnotationsEnums$CollectionUseCase);
            return this;
        }

        public Builder setVerificationFailure(VerificationFailureEnum$VerificationFailure verificationFailureEnum$VerificationFailure) {
            copyOnWrite();
            ((VerificationFailureLogOuterClass$VerificationFailureLog) this.instance).setVerificationFailure(verificationFailureEnum$VerificationFailure);
            return this;
        }

        /* synthetic */ Builder(VerificationFailureLogOuterClass$1 verificationFailureLogOuterClass$1) {
            this();
        }
    }
}
