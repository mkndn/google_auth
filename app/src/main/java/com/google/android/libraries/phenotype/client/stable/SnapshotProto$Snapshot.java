package com.google.android.libraries.phenotype.client.stable;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SnapshotProto$Snapshot extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CONFIGURATION_VERSION_FIELD_NUMBER = 4;
    private static final SnapshotProto$Snapshot DEFAULT_INSTANCE;
    public static final int EXPERIMENT_TOKEN_FIELD_NUMBER = 2;
    public static final int FLAG_FIELD_NUMBER = 5;
    public static final int LAST_UPDATE_EPOCH_MILLIS_FIELD_NUMBER = 6;
    private static volatile Parser PARSER = null;
    public static final int SERVER_TOKEN_FIELD_NUMBER = 3;
    public static final int SNAPSHOT_TOKEN_FIELD_NUMBER = 1;
    private int bitField0_;
    private long configurationVersion_;
    private long lastUpdateEpochMillis_;
    private String snapshotToken_ = "";
    private ByteString experimentToken_ = ByteString.EMPTY;
    private String serverToken_ = "";
    private Internal.ProtobufList flag_ = emptyProtobufList();

    static {
        SnapshotProto$Snapshot snapshotProto$Snapshot = new SnapshotProto$Snapshot();
        DEFAULT_INSTANCE = snapshotProto$Snapshot;
        GeneratedMessageLite.registerDefaultInstance(SnapshotProto$Snapshot.class, snapshotProto$Snapshot);
    }

    private SnapshotProto$Snapshot() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addFlag(SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag) {
        snapshotProto$SnapshotFlag.getClass();
        ensureFlagIsMutable();
        this.flag_.add(snapshotProto$SnapshotFlag);
    }

    private void ensureFlagIsMutable() {
        Internal.ProtobufList protobufList = this.flag_;
        if (!protobufList.isModifiable()) {
            this.flag_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static SnapshotProto$Snapshot getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConfigurationVersion(long j) {
        this.bitField0_ |= 8;
        this.configurationVersion_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExperimentToken(ByteString byteString) {
        byteString.getClass();
        this.bitField0_ |= 2;
        this.experimentToken_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLastUpdateEpochMillis(long j) {
        this.bitField0_ |= 16;
        this.lastUpdateEpochMillis_ = j;
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
        switch (SnapshotProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SnapshotProto$Snapshot();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005\u001b\u0006ဂ\u0004", new Object[]{"bitField0_", "snapshotToken_", "experimentToken_", "serverToken_", "configurationVersion_", "flag_", SnapshotProto$SnapshotFlag.class, "lastUpdateEpochMillis_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SnapshotProto$Snapshot.class) {
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

    public int getFlagCount() {
        return this.flag_.size();
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SnapshotProto$Snapshot.DEFAULT_INSTANCE);
        }

        public Builder addFlag(SnapshotProto$SnapshotFlag snapshotProto$SnapshotFlag) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).addFlag(snapshotProto$SnapshotFlag);
            return this;
        }

        public Builder setConfigurationVersion(long j) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).setConfigurationVersion(j);
            return this;
        }

        public Builder setExperimentToken(ByteString byteString) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).setExperimentToken(byteString);
            return this;
        }

        public Builder setLastUpdateEpochMillis(long j) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).setLastUpdateEpochMillis(j);
            return this;
        }

        public Builder setServerToken(String str) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).setServerToken(str);
            return this;
        }

        public Builder setSnapshotToken(String str) {
            copyOnWrite();
            ((SnapshotProto$Snapshot) this.instance).setSnapshotToken(str);
            return this;
        }

        /* synthetic */ Builder(SnapshotProto$1 snapshotProto$1) {
            this();
        }
    }
}
