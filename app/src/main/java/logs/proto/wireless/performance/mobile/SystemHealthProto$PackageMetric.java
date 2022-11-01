package logs.proto.wireless.performance.mobile;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemHealthProto$PackageMetric extends GeneratedMessageLite implements MessageLiteOrBuilder {
    public static final int CACHE_SIZE_FIELD_NUMBER = 1;
    public static final int CODE_SIZE_FIELD_NUMBER = 2;
    public static final int DATA_PARTITION_SIZE_BYTES_FIELD_NUMBER = 11;
    public static final int DATA_SIZE_FIELD_NUMBER = 3;
    private static final SystemHealthProto$PackageMetric DEFAULT_INSTANCE;
    public static final int DIR_STATS_FIELD_NUMBER = 10;
    public static final int EXTERNAL_CACHE_SIZE_FIELD_NUMBER = 4;
    public static final int EXTERNAL_CODE_SIZE_FIELD_NUMBER = 5;
    public static final int EXTERNAL_DATA_SIZE_FIELD_NUMBER = 6;
    public static final int EXTERNAL_MEDIA_SIZE_FIELD_NUMBER = 7;
    public static final int EXTERNAL_OBB_SIZE_FIELD_NUMBER = 8;
    public static final int PACKAGE_NAME_FIELD_NUMBER = 9;
    private static volatile Parser PARSER;
    private int bitField0_;
    private long cacheSize_;
    private long codeSize_;
    private long dataSize_;
    private long externalCacheSize_;
    private long externalCodeSize_;
    private long externalDataSize_;
    private long externalMediaSize_;
    private long externalObbSize_;
    private String packageName_ = "";
    private Internal.ProtobufList dirStats_ = emptyProtobufList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DirStats extends GeneratedMessageLite implements MessageLiteOrBuilder {
        private static final DirStats DEFAULT_INSTANCE;
        public static final int DIR_PATH_FIELD_NUMBER = 1;
        public static final int HASHED_DIR_PATH_FIELD_NUMBER = 3;
        private static volatile Parser PARSER = null;
        public static final int SIZE_BYTES_FIELD_NUMBER = 2;
        public static final int STORAGE_CONTEXT_FIELD_NUMBER = 4;
        private int bitField0_;
        private long sizeBytes_;
        private int storageContext_;
        private int hashedDirPathMemoizedSerializedSize = -1;
        private String dirPath_ = "";
        private Internal.LongList hashedDirPath_ = emptyLongList();

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public enum StorageContext implements Internal.EnumLite {
            UNKNOWN(0),
            CREDENTIAL_ENCRYPTED(1),
            DEVICE_ENCRYPTED(2);
            
            public static final int CREDENTIAL_ENCRYPTED_VALUE = 1;
            public static final int DEVICE_ENCRYPTED_VALUE = 2;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap internalValueMap = new Internal.EnumLiteMap() { // from class: logs.proto.wireless.performance.mobile.SystemHealthProto.PackageMetric.DirStats.StorageContext.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public StorageContext findValueByNumber(int i) {
                    return StorageContext.forNumber(i);
                }
            };
            private final int value;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: PG */
            /* loaded from: classes.dex */
            public final class StorageContextVerifier implements Internal.EnumVerifier {
                static final Internal.EnumVerifier INSTANCE = new StorageContextVerifier();

                private StorageContextVerifier() {
                }

                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i) {
                    return StorageContext.forNumber(i) != null;
                }
            }

            StorageContext(int i) {
                this.value = i;
            }

            public static StorageContext forNumber(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return CREDENTIAL_ENCRYPTED;
                    case 2:
                        return DEVICE_ENCRYPTED;
                    default:
                        return null;
                }
            }

            public static Internal.EnumVerifier internalGetVerifier() {
                return StorageContextVerifier.INSTANCE;
            }

            @Override // com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            @Override // java.lang.Enum
            public String toString() {
                StringBuilder sb = new StringBuilder("<");
                sb.append(getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)));
                sb.append(" number=").append(getNumber());
                return sb.append(" name=").append(name()).append('>').toString();
            }
        }

        static {
            DirStats dirStats = new DirStats();
            DEFAULT_INSTANCE = dirStats;
            GeneratedMessageLite.registerDefaultInstance(DirStats.class, dirStats);
        }

        private DirStats() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllHashedDirPath(Iterable iterable) {
            ensureHashedDirPathIsMutable();
            AbstractMessageLite.addAll(iterable, (List) this.hashedDirPath_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDirPath() {
            this.bitField0_ &= -2;
            this.dirPath_ = getDefaultInstance().getDirPath();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHashedDirPath() {
            this.hashedDirPath_ = emptyLongList();
        }

        private void ensureHashedDirPathIsMutable() {
            Internal.LongList longList = this.hashedDirPath_;
            if (!longList.isModifiable()) {
                this.hashedDirPath_ = GeneratedMessageLite.mutableCopy(longList);
            }
        }

        public static DirStats getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDirPath(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.dirPath_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSizeBytes(long j) {
            this.bitField0_ |= 2;
            this.sizeBytes_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStorageContext(StorageContext storageContext) {
            this.storageContext_ = storageContext.getNumber();
            this.bitField0_ |= 4;
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DirStats();
                case 2:
                    return new Builder(null);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003(\u0004ဌ\u0002", new Object[]{"bitField0_", "dirPath_", "sizeBytes_", "hashedDirPath_", "storageContext_", StorageContext.internalGetVerifier()});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser parser = PARSER;
                    if (parser == null) {
                        synchronized (DirStats.class) {
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

        public String getDirPath() {
            return this.dirPath_;
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
            private Builder() {
                super(DirStats.DEFAULT_INSTANCE);
            }

            public Builder addAllHashedDirPath(Iterable iterable) {
                copyOnWrite();
                ((DirStats) this.instance).addAllHashedDirPath(iterable);
                return this;
            }

            public Builder clearDirPath() {
                copyOnWrite();
                ((DirStats) this.instance).clearDirPath();
                return this;
            }

            public Builder clearHashedDirPath() {
                copyOnWrite();
                ((DirStats) this.instance).clearHashedDirPath();
                return this;
            }

            public String getDirPath() {
                return ((DirStats) this.instance).getDirPath();
            }

            public Builder setDirPath(String str) {
                copyOnWrite();
                ((DirStats) this.instance).setDirPath(str);
                return this;
            }

            public Builder setSizeBytes(long j) {
                copyOnWrite();
                ((DirStats) this.instance).setSizeBytes(j);
                return this;
            }

            public Builder setStorageContext(StorageContext storageContext) {
                copyOnWrite();
                ((DirStats) this.instance).setStorageContext(storageContext);
                return this;
            }

            /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
                this();
            }
        }
    }

    static {
        SystemHealthProto$PackageMetric systemHealthProto$PackageMetric = new SystemHealthProto$PackageMetric();
        DEFAULT_INSTANCE = systemHealthProto$PackageMetric;
        GeneratedMessageLite.registerDefaultInstance(SystemHealthProto$PackageMetric.class, systemHealthProto$PackageMetric);
    }

    private SystemHealthProto$PackageMetric() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllDirStats(Iterable iterable) {
        ensureDirStatsIsMutable();
        AbstractMessageLite.addAll(iterable, (List) this.dirStats_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDirStats() {
        this.dirStats_ = emptyProtobufList();
    }

    private void ensureDirStatsIsMutable() {
        Internal.ProtobufList protobufList = this.dirStats_;
        if (!protobufList.isModifiable()) {
            this.dirStats_ = GeneratedMessageLite.mutableCopy(protobufList);
        }
    }

    public static SystemHealthProto$PackageMetric getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCacheSize(long j) {
        this.bitField0_ |= 1;
        this.cacheSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCodeSize(long j) {
        this.bitField0_ |= 2;
        this.codeSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDataSize(long j) {
        this.bitField0_ |= 4;
        this.dataSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDirStats(int i, DirStats dirStats) {
        dirStats.getClass();
        ensureDirStatsIsMutable();
        this.dirStats_.set(i, dirStats);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalCacheSize(long j) {
        this.bitField0_ |= 8;
        this.externalCacheSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalCodeSize(long j) {
        this.bitField0_ |= 16;
        this.externalCodeSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalDataSize(long j) {
        this.bitField0_ |= 32;
        this.externalDataSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalMediaSize(long j) {
        this.bitField0_ |= 64;
        this.externalMediaSize_ = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExternalObbSize(long j) {
        this.bitField0_ |= 128;
        this.externalObbSize_ = j;
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (SystemHealthProto$1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new SystemHealthProto$PackageMetric();
            case 2:
                return new Builder(null);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\n\t\u0000\u0001\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007\n\u001b", new Object[]{"bitField0_", "cacheSize_", "codeSize_", "dataSize_", "externalCacheSize_", "externalCodeSize_", "externalDataSize_", "externalMediaSize_", "externalObbSize_", "dirStats_", DirStats.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser = PARSER;
                if (parser == null) {
                    synchronized (SystemHealthProto$PackageMetric.class) {
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

    public DirStats getDirStats(int i) {
        return (DirStats) this.dirStats_.get(i);
    }

    public int getDirStatsCount() {
        return this.dirStats_.size();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends GeneratedMessageLite.Builder implements MessageLiteOrBuilder {
        private Builder() {
            super(SystemHealthProto$PackageMetric.DEFAULT_INSTANCE);
        }

        public Builder addAllDirStats(Iterable iterable) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).addAllDirStats(iterable);
            return this;
        }

        public Builder clearDirStats() {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).clearDirStats();
            return this;
        }

        public DirStats getDirStats(int i) {
            return ((SystemHealthProto$PackageMetric) this.instance).getDirStats(i);
        }

        public int getDirStatsCount() {
            return ((SystemHealthProto$PackageMetric) this.instance).getDirStatsCount();
        }

        public Builder setCacheSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setCacheSize(j);
            return this;
        }

        public Builder setCodeSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setCodeSize(j);
            return this;
        }

        public Builder setDataSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setDataSize(j);
            return this;
        }

        public Builder setDirStats(int i, DirStats.Builder builder) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setDirStats(i, (DirStats) builder.build());
            return this;
        }

        public Builder setExternalCacheSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setExternalCacheSize(j);
            return this;
        }

        public Builder setExternalCodeSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setExternalCodeSize(j);
            return this;
        }

        public Builder setExternalDataSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setExternalDataSize(j);
            return this;
        }

        public Builder setExternalMediaSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setExternalMediaSize(j);
            return this;
        }

        public Builder setExternalObbSize(long j) {
            copyOnWrite();
            ((SystemHealthProto$PackageMetric) this.instance).setExternalObbSize(j);
            return this;
        }

        /* synthetic */ Builder(SystemHealthProto$1 systemHealthProto$1) {
            this();
        }
    }
}
