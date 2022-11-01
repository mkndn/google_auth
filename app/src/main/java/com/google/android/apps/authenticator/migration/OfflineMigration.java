package com.google.android.apps.authenticator.migration;

import com.google.android.apps.authenticator.migration.OfflineMigrationEnums;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OfflineMigration {

    /* compiled from: PG */
    /* renamed from: com.google.android.apps.authenticator.migration.OfflineMigration$1  reason: invalid class name */
    /* loaded from: classes.dex */
    /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MigrationPayload extends GeneratedMessageLite implements MigrationPayloadOrBuilder {
        public static final int BATCH_ID_FIELD_NUMBER = 5;
        public static final int BATCH_INDEX_FIELD_NUMBER = 4;
        public static final int BATCH_SIZE_FIELD_NUMBER = 3;
        private static final MigrationPayload DEFAULT_INSTANCE;
        public static final int OTP_PARAMETERS_FIELD_NUMBER = 1;
        private static volatile Parser PARSER = null;
        public static final int VERSION_FIELD_NUMBER = 2;
        private int batchId_;
        private int batchIndex_;
        private int bitField0_;
        private int version_;
        private Internal.ProtobufList otpParameters_ = emptyProtobufList();
        private int batchSize_ = 1;

        static {
            MigrationPayload migrationPayload = new MigrationPayload();
            DEFAULT_INSTANCE = migrationPayload;
            GeneratedMessageLite.registerDefaultInstance(MigrationPayload.class, migrationPayload);
        }

        private MigrationPayload() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllOtpParameters(Iterable iterable) {
            ensureOtpParametersIsMutable();
            AbstractMessageLite.addAll(iterable, (List) this.otpParameters_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addOtpParameters(int i, OtpParameters otpParameters) {
            otpParameters.getClass();
            ensureOtpParametersIsMutable();
            this.otpParameters_.add(i, otpParameters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBatchId() {
            this.bitField0_ &= -9;
            this.batchId_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBatchIndex() {
            this.bitField0_ &= -5;
            this.batchIndex_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBatchSize() {
            this.bitField0_ &= -3;
            this.batchSize_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOtpParameters() {
            this.otpParameters_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearVersion() {
            this.bitField0_ &= -2;
            this.version_ = 0;
        }

        private void ensureOtpParametersIsMutable() {
            Internal.ProtobufList protobufList = this.otpParameters_;
            if (!protobufList.isModifiable()) {
                this.otpParameters_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
        }

        public static MigrationPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static MigrationPayload parseDelimitedFrom(InputStream inputStream) {
            return (MigrationPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MigrationPayload parseFrom(ByteString byteString) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Parser parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeOtpParameters(int i) {
            ensureOtpParametersIsMutable();
            this.otpParameters_.remove(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBatchId(int i) {
            this.bitField0_ |= 8;
            this.batchId_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBatchIndex(int i) {
            this.bitField0_ |= 4;
            this.batchIndex_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBatchSize(int i) {
            this.bitField0_ |= 2;
            this.batchSize_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOtpParameters(int i, OtpParameters otpParameters) {
            otpParameters.getClass();
            ensureOtpParametersIsMutable();
            this.otpParameters_.set(i, otpParameters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setVersion(int i) {
            this.bitField0_ |= 1;
            this.version_ = i;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new MigrationPayload();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002င\u0000\u0003င\u0001\u0004င\u0002\u0005င\u0003", new Object[]{"bitField0_", "otpParameters_", OtpParameters.class, "version_", "batchSize_", "batchIndex_", "batchId_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (MigrationPayload.class) {
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

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public int getBatchId() {
            return this.batchId_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public int getBatchIndex() {
            return this.batchIndex_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public int getBatchSize() {
            return this.batchSize_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public OtpParameters getOtpParameters(int i) {
            return (OtpParameters) this.otpParameters_.get(i);
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public int getOtpParametersCount() {
            return this.otpParameters_.size();
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public List getOtpParametersList() {
            return this.otpParameters_;
        }

        public OtpParametersOrBuilder getOtpParametersOrBuilder(int i) {
            return (OtpParametersOrBuilder) this.otpParameters_.get(i);
        }

        public List getOtpParametersOrBuilderList() {
            return this.otpParameters_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public int getVersion() {
            return this.version_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public boolean hasBatchId() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public boolean hasBatchIndex() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public boolean hasBatchSize() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
        public boolean hasVersion() {
            return (this.bitField0_ & 1) != 0;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MigrationPayloadOrBuilder {
            private Builder() {
                super(MigrationPayload.DEFAULT_INSTANCE);
            }

            public Builder addAllOtpParameters(Iterable iterable) {
                copyOnWrite();
                ((MigrationPayload) this.instance).addAllOtpParameters(iterable);
                return this;
            }

            public Builder addOtpParameters(int i, OtpParameters.Builder builder) {
                copyOnWrite();
                ((MigrationPayload) this.instance).addOtpParameters(i, (OtpParameters) builder.build());
                return this;
            }

            public Builder clearBatchId() {
                copyOnWrite();
                ((MigrationPayload) this.instance).clearBatchId();
                return this;
            }

            public Builder clearBatchIndex() {
                copyOnWrite();
                ((MigrationPayload) this.instance).clearBatchIndex();
                return this;
            }

            public Builder clearBatchSize() {
                copyOnWrite();
                ((MigrationPayload) this.instance).clearBatchSize();
                return this;
            }

            public Builder clearOtpParameters() {
                copyOnWrite();
                ((MigrationPayload) this.instance).clearOtpParameters();
                return this;
            }

            public Builder clearVersion() {
                copyOnWrite();
                ((MigrationPayload) this.instance).clearVersion();
                return this;
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public int getBatchId() {
                return ((MigrationPayload) this.instance).getBatchId();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public int getBatchIndex() {
                return ((MigrationPayload) this.instance).getBatchIndex();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public int getBatchSize() {
                return ((MigrationPayload) this.instance).getBatchSize();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public OtpParameters getOtpParameters(int i) {
                return ((MigrationPayload) this.instance).getOtpParameters(i);
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public int getOtpParametersCount() {
                return ((MigrationPayload) this.instance).getOtpParametersCount();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public List getOtpParametersList() {
                return Collections.unmodifiableList(((MigrationPayload) this.instance).getOtpParametersList());
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public int getVersion() {
                return ((MigrationPayload) this.instance).getVersion();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public boolean hasBatchId() {
                return ((MigrationPayload) this.instance).hasBatchId();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public boolean hasBatchIndex() {
                return ((MigrationPayload) this.instance).hasBatchIndex();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public boolean hasBatchSize() {
                return ((MigrationPayload) this.instance).hasBatchSize();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.MigrationPayloadOrBuilder
            public boolean hasVersion() {
                return ((MigrationPayload) this.instance).hasVersion();
            }

            public Builder removeOtpParameters(int i) {
                copyOnWrite();
                ((MigrationPayload) this.instance).removeOtpParameters(i);
                return this;
            }

            public Builder setBatchId(int i) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setBatchId(i);
                return this;
            }

            public Builder setBatchIndex(int i) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setBatchIndex(i);
                return this;
            }

            public Builder setBatchSize(int i) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setBatchSize(i);
                return this;
            }

            public Builder setOtpParameters(int i, OtpParameters.Builder builder) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setOtpParameters(i, (OtpParameters) builder.build());
                return this;
            }

            public Builder setVersion(int i) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setVersion(i);
                return this;
            }

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public Builder addOtpParameters(int i, OtpParameters otpParameters) {
                copyOnWrite();
                ((MigrationPayload) this.instance).addOtpParameters(i, otpParameters);
                return this;
            }

            public Builder setOtpParameters(int i, OtpParameters otpParameters) {
                copyOnWrite();
                ((MigrationPayload) this.instance).setOtpParameters(i, otpParameters);
                return this;
            }

            public Builder addOtpParameters(OtpParameters.Builder builder) {
                copyOnWrite();
                ((MigrationPayload) this.instance).addOtpParameters((OtpParameters) builder.build());
                return this;
            }

            public Builder addOtpParameters(OtpParameters otpParameters) {
                copyOnWrite();
                ((MigrationPayload) this.instance).addOtpParameters(otpParameters);
                return this;
            }
        }

        public static Builder newBuilder(MigrationPayload migrationPayload) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(migrationPayload);
        }

        public static MigrationPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MigrationPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static MigrationPayload parseFrom(CodedInputStream codedInputStream) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static MigrationPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addOtpParameters(OtpParameters otpParameters) {
            otpParameters.getClass();
            ensureOtpParametersIsMutable();
            this.otpParameters_.add(otpParameters);
        }

        public static MigrationPayload parseFrom(InputStream inputStream) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static MigrationPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static MigrationPayload parseFrom(ByteBuffer byteBuffer) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static MigrationPayload parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static MigrationPayload parseFrom(byte[] bArr) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static MigrationPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (MigrationPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface MigrationPayloadOrBuilder extends MessageLiteOrBuilder {
        int getBatchId();

        int getBatchIndex();

        int getBatchSize();

        OtpParameters getOtpParameters(int i);

        int getOtpParametersCount();

        List getOtpParametersList();

        int getVersion();

        boolean hasBatchId();

        boolean hasBatchIndex();

        boolean hasBatchSize();

        boolean hasVersion();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OtpParameters extends GeneratedMessageLite implements OtpParametersOrBuilder {
        public static final int ALGORITHM_FIELD_NUMBER = 4;
        public static final int COUNTER_FIELD_NUMBER = 7;
        private static final OtpParameters DEFAULT_INSTANCE;
        public static final int DIGITS_FIELD_NUMBER = 5;
        public static final int ISSUER_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser PARSER = null;
        public static final int SECRET_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 6;
        private int algorithm_;
        private int bitField0_;
        private long counter_;
        private int digits_;
        private int type_;
        private ByteString secret_ = ByteString.EMPTY;
        private String name_ = "";
        private String issuer_ = "";

        static {
            OtpParameters otpParameters = new OtpParameters();
            DEFAULT_INSTANCE = otpParameters;
            GeneratedMessageLite.registerDefaultInstance(OtpParameters.class, otpParameters);
        }

        private OtpParameters() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAlgorithm() {
            this.bitField0_ &= -9;
            this.algorithm_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCounter() {
            this.bitField0_ &= -65;
            this.counter_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDigits() {
            this.bitField0_ &= -17;
            this.digits_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIssuer() {
            this.bitField0_ &= -5;
            this.issuer_ = getDefaultInstance().getIssuer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.bitField0_ &= -3;
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSecret() {
            this.bitField0_ &= -2;
            this.secret_ = getDefaultInstance().getSecret();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.bitField0_ &= -33;
            this.type_ = 0;
        }

        public static OtpParameters getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static OtpParameters parseDelimitedFrom(InputStream inputStream) {
            return (OtpParameters) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OtpParameters parseFrom(ByteString byteString) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Parser parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAlgorithm(OfflineMigrationEnums.Algorithm algorithm) {
            this.algorithm_ = algorithm.getNumber();
            this.bitField0_ |= 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCounter(long j) {
            this.bitField0_ |= 64;
            this.counter_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDigits(OfflineMigrationEnums.DigitCount digitCount) {
            this.digits_ = digitCount.getNumber();
            this.bitField0_ |= 16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssuer(String str) {
            str.getClass();
            this.bitField0_ |= 4;
            this.issuer_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIssuerBytes(ByteString byteString) {
            this.issuer_ = byteString.toStringUtf8();
            this.bitField0_ |= 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.bitField0_ |= 2;
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            this.name_ = byteString.toStringUtf8();
            this.bitField0_ |= 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSecret(ByteString byteString) {
            byteString.getClass();
            this.bitField0_ |= 1;
            this.secret_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(OfflineMigrationEnums.OtpType otpType) {
            this.type_ = otpType.getNumber();
            this.bitField0_ |= 32;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new OtpParameters();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ည\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဃ\u0006", new Object[]{"bitField0_", "secret_", "name_", "issuer_", "algorithm_", OfflineMigrationEnums.Algorithm.internalGetVerifier(), "digits_", OfflineMigrationEnums.DigitCount.internalGetVerifier(), "type_", OfflineMigrationEnums.OtpType.internalGetVerifier(), "counter_"});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (OtpParameters.class) {
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

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public OfflineMigrationEnums.Algorithm getAlgorithm() {
            OfflineMigrationEnums.Algorithm forNumber = OfflineMigrationEnums.Algorithm.forNumber(this.algorithm_);
            return forNumber == null ? OfflineMigrationEnums.Algorithm.ALGORITHM_UNSPECIFIED : forNumber;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public long getCounter() {
            return this.counter_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public OfflineMigrationEnums.DigitCount getDigits() {
            OfflineMigrationEnums.DigitCount forNumber = OfflineMigrationEnums.DigitCount.forNumber(this.digits_);
            return forNumber == null ? OfflineMigrationEnums.DigitCount.DIGIT_COUNT_UNSPECIFIED : forNumber;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public String getIssuer() {
            return this.issuer_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public ByteString getIssuerBytes() {
            return ByteString.copyFromUtf8(this.issuer_);
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public ByteString getSecret() {
            return this.secret_;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public OfflineMigrationEnums.OtpType getType() {
            OfflineMigrationEnums.OtpType forNumber = OfflineMigrationEnums.OtpType.forNumber(this.type_);
            return forNumber == null ? OfflineMigrationEnums.OtpType.OTP_TYPE_UNSPECIFIED : forNumber;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasAlgorithm() {
            return (this.bitField0_ & 8) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasCounter() {
            return (this.bitField0_ & 64) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasDigits() {
            return (this.bitField0_ & 16) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasIssuer() {
            return (this.bitField0_ & 4) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasName() {
            return (this.bitField0_ & 2) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasSecret() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 32) != 0;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements OtpParametersOrBuilder {
            private Builder() {
                super(OtpParameters.DEFAULT_INSTANCE);
            }

            public Builder clearAlgorithm() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearAlgorithm();
                return this;
            }

            public Builder clearCounter() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearCounter();
                return this;
            }

            public Builder clearDigits() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearDigits();
                return this;
            }

            public Builder clearIssuer() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearIssuer();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearName();
                return this;
            }

            public Builder clearSecret() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearSecret();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((OtpParameters) this.instance).clearType();
                return this;
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public OfflineMigrationEnums.Algorithm getAlgorithm() {
                return ((OtpParameters) this.instance).getAlgorithm();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public long getCounter() {
                return ((OtpParameters) this.instance).getCounter();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public OfflineMigrationEnums.DigitCount getDigits() {
                return ((OtpParameters) this.instance).getDigits();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public String getIssuer() {
                return ((OtpParameters) this.instance).getIssuer();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public ByteString getIssuerBytes() {
                return ((OtpParameters) this.instance).getIssuerBytes();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public String getName() {
                return ((OtpParameters) this.instance).getName();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public ByteString getNameBytes() {
                return ((OtpParameters) this.instance).getNameBytes();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public ByteString getSecret() {
                return ((OtpParameters) this.instance).getSecret();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public OfflineMigrationEnums.OtpType getType() {
                return ((OtpParameters) this.instance).getType();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasAlgorithm() {
                return ((OtpParameters) this.instance).hasAlgorithm();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasCounter() {
                return ((OtpParameters) this.instance).hasCounter();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasDigits() {
                return ((OtpParameters) this.instance).hasDigits();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasIssuer() {
                return ((OtpParameters) this.instance).hasIssuer();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasName() {
                return ((OtpParameters) this.instance).hasName();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasSecret() {
                return ((OtpParameters) this.instance).hasSecret();
            }

            @Override // com.google.android.apps.authenticator.migration.OfflineMigration.OtpParametersOrBuilder
            public boolean hasType() {
                return ((OtpParameters) this.instance).hasType();
            }

            public Builder setAlgorithm(OfflineMigrationEnums.Algorithm algorithm) {
                copyOnWrite();
                ((OtpParameters) this.instance).setAlgorithm(algorithm);
                return this;
            }

            public Builder setCounter(long j) {
                copyOnWrite();
                ((OtpParameters) this.instance).setCounter(j);
                return this;
            }

            public Builder setDigits(OfflineMigrationEnums.DigitCount digitCount) {
                copyOnWrite();
                ((OtpParameters) this.instance).setDigits(digitCount);
                return this;
            }

            public Builder setIssuer(String str) {
                copyOnWrite();
                ((OtpParameters) this.instance).setIssuer(str);
                return this;
            }

            public Builder setIssuerBytes(ByteString byteString) {
                copyOnWrite();
                ((OtpParameters) this.instance).setIssuerBytes(byteString);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((OtpParameters) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((OtpParameters) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setSecret(ByteString byteString) {
                copyOnWrite();
                ((OtpParameters) this.instance).setSecret(byteString);
                return this;
            }

            public Builder setType(OfflineMigrationEnums.OtpType otpType) {
                copyOnWrite();
                ((OtpParameters) this.instance).setType(otpType);
                return this;
            }

            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }
        }

        public static Builder newBuilder(OtpParameters otpParameters) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(otpParameters);
        }

        public static OtpParameters parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static OtpParameters parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static OtpParameters parseFrom(CodedInputStream codedInputStream) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static OtpParameters parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static OtpParameters parseFrom(InputStream inputStream) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OtpParameters parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static OtpParameters parseFrom(ByteBuffer byteBuffer) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static OtpParameters parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static OtpParameters parseFrom(byte[] bArr) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static OtpParameters parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return (OtpParameters) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OtpParametersOrBuilder extends MessageLiteOrBuilder {
        OfflineMigrationEnums.Algorithm getAlgorithm();

        long getCounter();

        OfflineMigrationEnums.DigitCount getDigits();

        String getIssuer();

        ByteString getIssuerBytes();

        String getName();

        ByteString getNameBytes();

        ByteString getSecret();

        OfflineMigrationEnums.OtpType getType();

        boolean hasAlgorithm();

        boolean hasCounter();

        boolean hasDigits();

        boolean hasIssuer();

        boolean hasName();

        boolean hasSecret();

        boolean hasType();
    }

    private OfflineMigration() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
