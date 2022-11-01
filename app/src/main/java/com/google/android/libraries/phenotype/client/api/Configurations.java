package com.google.android.libraries.phenotype.client.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Configurations extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CONFIGURATION_VERSION_FIELD_NUMBER = 9;
    private static final Configurations DEFAULT_INSTANCE;
    public static final int DELETE_FLAG_FIELD_NUMBER = 5;
    public static final int EXPERIMENT_TOKEN_FIELD_NUMBER = 3;
    public static final int FLAG_FIELD_NUMBER = 4;
    public static final int IS_DELTA_FIELD_NUMBER = 8;
    private static volatile Parser PARSER = null;
    public static final int SERVER_TOKEN_FIELD_NUMBER = 1;
    public static final int SNAPSHOT_TOKEN_FIELD_NUMBER = 2;
    private int bitField0_;
    private long configurationVersion_;
    private boolean isDelta_;
    private String snapshotToken_ = "";
    private ByteString experimentToken_ = ByteString.EMPTY;
    private String serverToken_ = "";
    private Internal.ProtobufList flag_ = emptyProtobufList();
    private Internal.ProtobufList deleteFlag_ = GeneratedMessageLite.emptyProtobufList();

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.phenotype.client.api.Configurations$1  reason: invalid class name */
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

    static {
        Configurations configurations = new Configurations();
        DEFAULT_INSTANCE = configurations;
        GeneratedMessageLite.registerDefaultInstance(Configurations.class, configurations);
    }

    private Configurations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDeleteFlag(String str) {
        str.getClass();
        ensureDeleteFlagIsMutable();
        this.deleteFlag_.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFlag(Flag flag) {
        flag.getClass();
        ensureFlagIsMutable();
        this.flag_.add(flag);
    }

    private void ensureDeleteFlagIsMutable() {
        Internal.ProtobufList protobufList = this.deleteFlag_;
        if (!protobufList.isModifiable()) {
            this.deleteFlag_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    private void ensureFlagIsMutable() {
        Internal.ProtobufList protobufList = this.flag_;
        if (!protobufList.isModifiable()) {
            this.flag_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigurationVersion(long j) {
        this.bitField0_ |= 16;
        this.configurationVersion_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExperimentToken(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 2;
        this.experimentToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIsDelta(boolean z) {
        this.bitField0_ |= 8;
        this.isDelta_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setServerToken(String str) {
        str.getClass();
        this.bitField0_ |= 4;
        this.serverToken_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSnapshotToken(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.snapshotToken_ = str;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Configurations();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001ဈ\u0002\u0002ဈ\u0000\u0003ည\u0001\u0004\u001b\u0005\u001a\bဇ\u0003\tဂ\u0004", new Object[]{"bitField0_", "serverToken_", "snapshotToken_", "experimentToken_", "flag_", Flag.class, "deleteFlag_", "isDelta_", "configurationVersion_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (Configurations.class) {
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

    public long getConfigurationVersion() {
        return this.configurationVersion_;
    }

    public ByteString getExperimentToken() {
        return this.experimentToken_;
    }

    public List getFlagList() {
        return this.flag_;
    }

    public String getServerToken() {
        return this.serverToken_;
    }

    public String getSnapshotToken() {
        return this.snapshotToken_;
    }

    public boolean hasExperimentToken() {
        return (this.bitField0_ & 2) != 0;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(Configurations.DEFAULT_INSTANCE);
        }

        public Builder addDeleteFlag(String str) {
            copyOnWrite();
            ((Configurations) this.instance).addDeleteFlag(str);
            return this;
        }

        public Builder addFlag(Flag flag) {
            copyOnWrite();
            ((Configurations) this.instance).addFlag(flag);
            return this;
        }

        public Builder setConfigurationVersion(long j) {
            copyOnWrite();
            ((Configurations) this.instance).setConfigurationVersion(j);
            return this;
        }

        public Builder setExperimentToken(ByteString byteString) {
            copyOnWrite();
            ((Configurations) this.instance).setExperimentToken(byteString);
            return this;
        }

        public Builder setIsDelta(boolean z) {
            copyOnWrite();
            ((Configurations) this.instance).setIsDelta(z);
            return this;
        }

        public Builder setServerToken(String str) {
            copyOnWrite();
            ((Configurations) this.instance).setServerToken(str);
            return this;
        }

        public Builder setSnapshotToken(String str) {
            copyOnWrite();
            ((Configurations) this.instance).setSnapshotToken(str);
            return this;
        }

        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }
    }
}
